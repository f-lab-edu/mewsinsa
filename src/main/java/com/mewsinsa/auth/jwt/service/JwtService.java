package com.mewsinsa.auth.jwt.service;

import com.mewsinsa.auth.jwt.JwtProvider;
import com.mewsinsa.auth.jwt.controller.dto.RefreshTokenDto;
import com.mewsinsa.auth.jwt.controller.dto.SignInRequestDto;
import com.mewsinsa.auth.jwt.domain.JwtToken;
import com.mewsinsa.auth.jwt.exception.IncorrectPasswordException;
import com.mewsinsa.auth.jwt.exception.InvalidTokenException;
import com.mewsinsa.auth.jwt.exception.NonExistentMemberException;
import com.mewsinsa.auth.jwt.repository.AccessTokenRepository;
import com.mewsinsa.auth.jwt.repository.RefreshTokenRepository;
import com.mewsinsa.member.domain.Member;
import com.mewsinsa.member.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JwtService {
  Logger log = LoggerFactory.getLogger(getClass());
  private final RefreshTokenRepository refreshTokenRepository;
  private final AccessTokenRepository accessTokenRepository;
  private final MemberRepository memberRepository;
  private final JwtProvider jwtProvider;

  @Value("${jwt.sign_in.password.salt}")
  private String passwordSalt;

  public JwtService(RefreshTokenRepository refreshTokenRepository,
      AccessTokenRepository accessTokenRepository,
      MemberRepository memberRepository, JwtProvider jwtProvider) {
    this.refreshTokenRepository = refreshTokenRepository;
    this.accessTokenRepository = accessTokenRepository;
    this.memberRepository = memberRepository;
    this.jwtProvider = jwtProvider;
  }


  public RefreshTokenDto getRefreshToken(Long memberId) {
    return refreshTokenRepository.findRefreshTokenByMemberId(memberId);
  }

  @Transactional
  public JwtToken login(Long memberId) {
    // 멤버 찾기
    Member member = memberRepository.findMemberById(memberId);

    // 토큰 발행
    return jwtProvider.createJwtToken(memberId, member.getNickname(), member.getAdmin());
  }


  public void signUp(SignInRequestDto signInRequestDto) {
    String encryptedPassword = getEncryptedPassword(signInRequestDto.getPassword());

    Member member = new Member.Builder()
        .mewsinsaId(signInRequestDto.getMewsinsaId())
        .password(encryptedPassword)
        .name(signInRequestDto.getName())
        .nickname(signInRequestDto.getNickname())
        .email(signInRequestDto.getEmail())
        .phone(signInRequestDto.getPhone())
        .profileImage(signInRequestDto.getProfileImage())
        .tierId(signInRequestDto.getTierId())
        .isAdmin(signInRequestDto.getAdmin())
        .points(signInRequestDto.getPoints())
        .build();

    // DB에 회원 정보 저장
    try {
      memberRepository.addMember(member);
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
      // 멤버의 access, refresh 토큰 삭제
      accessTokenRepository.deleteAccessTokenByMemberId(memberId);
      refreshTokenRepository.deleteRefreshTokenByMemberId(memberId);
    } catch(Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  // TODO: refreshToken을 읽고 accessToken을 재발급합니다.
  @Transactional
  public JwtToken reissueAccessToken(String refreshToken) {
    Jws<Claims> claims = jwtProvider.parseClaims(refreshToken);

    // 리턴 값이 null이라면 만료
    if(claims == null) {
      // refresh Token을 DB에서 삭제
      refreshTokenRepository.deleteRefreshTokenByTokenValue(refreshToken);
      return null;
    }

    Long memberId = Long.parseLong(claims.getPayload().getSubject());
    Date expiration = claims.getPayload().getExpiration();
    String nickname = claims.getPayload().get("nickname", String.class);
    boolean isAdmin = Boolean.parseBoolean(claims.getPayload().get("isAdmin", String.class));

    // refreshToken과 DB에 저장된 refreshToken의 값이 일치하는지 확인
    RefreshTokenDto findRefreshToken = refreshTokenRepository.findRefreshTokenByMemberId(
        memberId);
    if(!findRefreshToken.getTokenValue().equals(refreshToken)) {
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

}
