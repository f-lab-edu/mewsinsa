package com.mewsinsa.auth.jwt;


import com.mewsinsa.auth.jwt.controller.dto.AccessTokenDto;
import com.mewsinsa.auth.jwt.controller.dto.RefreshTokenDto;

import com.mewsinsa.auth.jwt.domain.JwtToken;

import com.mewsinsa.auth.jwt.redis.dto.RedisAccessToken;
import com.mewsinsa.auth.jwt.redis.dto.RedisRefreshToken;
import com.mewsinsa.auth.jwt.redis.repository.RedisAccessTokenRepository;
import com.mewsinsa.auth.jwt.redis.repository.RedisRefreshTokenRepository;
import com.mewsinsa.member.domain.Member;
import com.mewsinsa.member.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class JwtProvider {
  public static final long ACCESSTOKEN_TIME = 1000 * 60 * 30; // 30분
  public static final long REFRESHTOKEN_TIME = 1000 * 60 * 60 * 24 * 14; // 2주
  public static final String ACCESS_PREFIX_STRING = "Bearer ";
  public static final String ACCESS_HEADER_STRING = "Authorization";
  public static final String REFRESH_HEADER_STRING = "RefreshToken";


  /**
   * application-jwt.properties에 정의되어 있습니다.
   */

  private static String key;
  private static String keyBase64Encoded; // properties에 정의된 값
  private static SecretKey signingKey;

  private final RedisAccessTokenRepository redisAccessTokenRepository;
  private final RedisRefreshTokenRepository redisRefreshTokenRepository;
  private final MemberRepository memberRepository;


  @Autowired
  public JwtProvider(@Value("${jwt.secret_key}") String keyParam,
      RedisAccessTokenRepository redisAccessTokenRepository,
      RedisRefreshTokenRepository redisRefreshTokenRepository, MemberRepository memberRepository) {
    key = keyParam;
    this.redisAccessTokenRepository = redisAccessTokenRepository;
    this.redisRefreshTokenRepository = redisRefreshTokenRepository;
    this.memberRepository = memberRepository;
    keyBase64Encoded = Base64.getEncoder().encodeToString(key.getBytes());
    signingKey = Keys.hmacShaKeyFor(keyBase64Encoded.getBytes());


  }

  //==Getter==//

  public String getKey() {
    return key;
  }

  //==SigningKey==//
  public static SecretKey getSigningKey() {
    return signingKey;
  }

  /**
   * @return JWT Token(액세스 토큰 + 리프레시 토큰)
   */
  public JwtToken createJwtToken(Long memberId, String nickname, Boolean isAdmin) {
    String accessToken = createAccessToken(memberId, nickname, isAdmin);
    String refreshToken = createRefreshToken(memberId);

    return new JwtToken(accessToken, refreshToken);
  }

  /**
   * 리프레시 토큰으로 액세스 토큰 재발급 요청이 왔을 때 호출됩니다.
   * @return 액세스 토큰
   */
  public String createAccessToken(Long memberId, String nickname, Boolean isAdmin) {
    Map<String, Object> claims = new HashMap<>();

    claims.put("memberId", memberId);
    claims.put("nickname", nickname);
    claims.put("isAdmin", Boolean.toString(isAdmin));

    Date expiration = new Date(System.currentTimeMillis() + ACCESSTOKEN_TIME);

    String accessToken = Jwts.builder()
        .subject(Long.toString(memberId))
        .claims(claims)
        .expiration(expiration)
        .signWith(this.getSigningKey())
        .compact();

    // 액세스 토큰을 DB에 저장
    Member member = memberRepository.findMemberById(memberId);
    redisAccessTokenRepository.save(new RedisAccessToken(memberId, accessToken, member));

    return ACCESS_PREFIX_STRING + accessToken;
  }

  /**
   * 외부에서 호출될 수 없습니다.
   * @return 리프레시 토큰
   */
  private String createRefreshToken(Long memberId) {
    Date expiration = new Date(System.currentTimeMillis() + REFRESHTOKEN_TIME);

    String refreshToken = Jwts.builder()
        .subject(Long.toString(memberId))
        .claim("memberId", memberId)
        .expiration(expiration)
        .signWith(getSigningKey())
        .compact();

    // 리프레시 토큰을 DB에 저장
    Member member = memberRepository.findMemberById(memberId);
    redisRefreshTokenRepository.save(new RedisRefreshToken(memberId, refreshToken, member));

    return refreshToken;
  }

  /**
   * 토큰의 클레임들을 가져옵니다.
   * @param token 액세스 또는 리프레시 토큰
   * @return 해당 토큰의 클레임들. parse에 실패할 경우 null을 반환합니다.
   */
  public Jws<Claims> parseClaims(String token) {
    token = token.replace("Bearer ", "");
    Jws<Claims> claimsJws;
    try {

      claimsJws = Jwts.parser()
          .verifyWith(getSigningKey())
          .build()
          .parseSignedClaims(token);

    } catch(ExpiredJwtException ex) {
      return null; // 만료되었음
    } catch(JwtException ex) {
      throw new IllegalArgumentException("잘못된 토큰입니다.");
    }

    return claimsJws;
  }


}
