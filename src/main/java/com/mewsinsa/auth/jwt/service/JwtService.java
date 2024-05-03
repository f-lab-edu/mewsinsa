package com.mewsinsa.auth.jwt.service;

import com.mewsinsa.auth.jwt.JwtProvider;
import com.mewsinsa.auth.jwt.controller.dto.RefreshTokenDto;
import com.mewsinsa.auth.jwt.controller.dto.SignUpRequestDto;
import com.mewsinsa.auth.jwt.domain.JwtToken;
import com.mewsinsa.auth.jwt.exception.DuplicateMemberInfoException;
import com.mewsinsa.auth.jwt.exception.IncorrectPasswordException;
import com.mewsinsa.auth.jwt.exception.InvalidTokenException;
import com.mewsinsa.auth.jwt.exception.NoTokenException;
import com.mewsinsa.auth.jwt.exception.NonExistentMemberException;
import com.mewsinsa.auth.jwt.redis.dto.RedisAccessToken;
import com.mewsinsa.auth.jwt.redis.dto.RedisRefreshToken;
import com.mewsinsa.auth.jwt.redis.repository.RedisAccessTokenRepository;
import com.mewsinsa.auth.jwt.redis.repository.RedisRefreshTokenRepository;
import com.mewsinsa.auth.jwt.repository.AccessTokenRepository;
import com.mewsinsa.auth.jwt.repository.RefreshTokenRepository;
import com.mewsinsa.member.domain.Member;
import com.mewsinsa.member.repository.MemberRepository;
import com.mewsinsa.member.service.MemberService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JwtService {
  Logger log = LoggerFactory.getLogger(getClass());
  private final MemberRepository memberRepository;
  private final JwtProvider jwtProvider;

  private final RedisAccessTokenRepository redisAccessTokenRepository;
  private final RedisRefreshTokenRepository redisRefreshTokenRepository;
  private final MemberService memberService;

  @Value("${jwt.sign_in.password.salt}")
  private String passwordSalt;

  public JwtService(
      MemberRepository memberRepository, JwtProvider jwtProvider,
      RedisAccessTokenRepository redisAccessTokenRepository,
      RedisRefreshTokenRepository redisRefreshTokenRepository, MemberService memberService) {
    this.memberRepository = memberRepository;
    this.jwtProvider = jwtProvider;
    this.redisAccessTokenRepository = redisAccessTokenRepository;
    this.redisRefreshTokenRepository = redisRefreshTokenRepository;
    this.memberService = memberService;
  }


  @Transactional
  public JwtToken login(Long memberId) {
    // 멤버 찾기
    Member member = memberRepository.findMemberById(memberId);
    System.out.println(memberId);

    if(member == null) {
      throw new NonExistentMemberException("회원이 존재하지 않습니다.");
    }

    // 토큰 발행
    return jwtProvider.createJwtToken(memberId, member.getNickname(), member.getAdmin());
  }


  public void signUp(SignUpRequestDto signUpRequestDto) {
    String encryptedPassword = getEncryptedPassword(signUpRequestDto.getPassword());

    Member member = new Member.Builder()
        .mewsinsaId(signUpRequestDto.getMewsinsaId())
        .password(encryptedPassword)
        .name(signUpRequestDto.getName())
        .nickname(signUpRequestDto.getNickname())
        .email(signUpRequestDto.getEmail())
        .phone(signUpRequestDto.getPhone())
        .profileImage(signUpRequestDto.getProfileImage())
        .tierId(signUpRequestDto.getTierId())
        .isAdmin(signUpRequestDto.getAdmin())
        .points(signUpRequestDto.getPoints())
        .build();

    // DB에 회원 정보 저장
    try {
      memberRepository.addMember(member);
    } catch (DataIntegrityViolationException e) {
      throw new DuplicateMemberInfoException("아이디 또는 이메일 또는 연락처가 기존 회원과 중복됩니다.");
    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  /**
   * @param password 유저 비밀번호
   * @return salt+password를 SHA-256으로 암호화 한 값
   */
  private String getEncryptedPassword(String password) {
    String saltedPassword = passwordSalt + password;
    String encryptedPassword = null;
    MessageDigest md = null;

    try {
      md = MessageDigest.getInstance("SHA-256");

      byte[] bytes = saltedPassword.getBytes(StandardCharsets.UTF_8);
      md.update(bytes);
      encryptedPassword = Base64.getEncoder().encodeToString(md.digest());

    } catch(NoSuchAlgorithmException e) {
      throw new IllegalArgumentException(e.getMessage());
    }

    return encryptedPassword;
  }



  @Transactional
  public void logout(Long memberId) {
    try {
      String strMemberId = Long.toString(memberId);
      // 멤버의 access, refresh 토큰 삭제
      redisAccessTokenRepository.deleteById(strMemberId);
      redisRefreshTokenRepository.deleteById(strMemberId);
    } catch(Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  // refreshToken을 읽고 accessToken을 재발급합니다.
  @Transactional
  public JwtToken reissueAccessToken(String refreshToken) {
    Jws<Claims> claims = jwtProvider.parseClaims(refreshToken);

    // 리턴 값이 null이라면 만료
    if(claims == null) {
      return null;
    }

    Long memberId = Long.parseLong(claims.getPayload().getSubject());
    Date expiration = claims.getPayload().getExpiration();
    String nickname = claims.getPayload().get("nickname", String.class);
    boolean isAdmin = Boolean.parseBoolean(claims.getPayload().get("isAdmin", String.class));

    // refreshToken과 DB에 저장된 refreshToken의 값이 일치하는지 확인
    Optional<RedisRefreshToken> foundRefreshToken = redisRefreshTokenRepository.findById(Long.toString(memberId));
    if(foundRefreshToken.isEmpty()
        ||!foundRefreshToken.get().getRefreshToken().equals(refreshToken)) {
      throw new InvalidTokenException("잘못된 리프레시 토큰입니다.");
    }

    // 재발급
    String accessToken = jwtProvider.createAccessToken(memberId, nickname, isAdmin);

    return new JwtToken(accessToken, refreshToken);
  }

  // 과정:
  // 1. mewsinsaId로 DB에서 회원 찾기
  // 2. 없으면?(null)이면 -> CustomException 던지기 (아이디 없음 A003)
  // 3. 있으면? -> 비밀번호를 해싱해서 멤버 비밀번호랑 비교하기
  // 4. 같으면? -> 토큰 발행
  // 5. 다르면? -> CustomException 던지기 (비밀번호가 틀림 A004)
  @Transactional
  public JwtToken mewsinsaLogin(String mewsinsaId, String password) {
    Member member = memberRepository.findMemberByMewsinsaId(mewsinsaId);
    if(member == null) {
      throw new NonExistentMemberException(mewsinsaId, "회원이 존재하지 않습니다.");
    }

    // 비밀번호를 해싱
    String encryptedPassword = getEncryptedPassword(password);
    if(!encryptedPassword.equals(member.getPassword())) { // 같지 않음
      throw new IncorrectPasswordException(mewsinsaId, "비밀번호가 틀립니다.");
    }

    // 토큰 발행
    return jwtProvider.createJwtToken(member.getMemberId(), member.getNickname(), member.getAdmin());
  }

  public Member findMemberByAccessToken(String accessToken) {
    Long memberId = memberService.getMemberIdByAccessToken(accessToken);
    String strMemberId = Long.toString(memberId);
    Optional<RedisAccessToken> accessTokenOptional = redisAccessTokenRepository.findById(strMemberId);

    if(accessTokenOptional.isEmpty()) {
      throw new NoTokenException("액세스 토큰이 존재하지 않습니다.");
    }

    RedisAccessToken redisAccessToken = accessTokenOptional.get();
    return redisAccessToken.getMember();
  }

}
