package com.mewsinsa.auth.jwt.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.mewsinsa.auth.jwt.controller.dto.RefreshTokenDto;
import com.mewsinsa.auth.jwt.domain.JwtToken;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.UUID;
import javax.crypto.SecretKey;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RefreshTokenRepositoryTest {
  Logger log = LoggerFactory.getLogger(getClass());

  String tokenId;
  String tokenValue;
  Long memberId = 2L;
  SecretKey testSignKey = Jwts.SIG.HS256.key().build();

  @Autowired
  RefreshTokenRepository refreshTokenRepository;

  @BeforeEach
  void addRefreshToken() {
    Date expiration = new Date(System.currentTimeMillis() + 1000L * 60L * 60);
    tokenId = UUID.randomUUID().toString();
    tokenValue = Jwts.builder()
        .id(tokenId)
        .subject(Long.toString(memberId))
        .expiration(expiration)
        .signWith(testSignKey)
        .compact();

    RefreshTokenDto refreshToken = new RefreshTokenDto(tokenValue, memberId, expiration);
    refreshTokenRepository.addRefreshToken(refreshToken);
  }


  @AfterEach
  void deleteRefreshToken() {
    refreshTokenRepository.deleteRefreshTokenByMemberId(memberId);
  }

  @Test
  void uuidTest() {
    UUID uuid = UUID.randomUUID();
    String uuid_to_string = uuid.toString();
    log.info(uuid_to_string);
    log.info("length: " + uuid_to_string.length());
  }

  @DisplayName("회원의 아이디로 회원의 리프레시 토큰을 찾을 수 있다.")
  @Test
  void findRefreshTokenByMemberId() {
    // given
    Long memberId = 2L;

    // when
    RefreshTokenDto refreshToken = refreshTokenRepository.findRefreshTokenByMemberId(memberId);

    // then
    Assertions.assertThat(refreshToken.getTokenValue()).isEqualTo(tokenValue);
    Assertions.assertThat(refreshToken.getMemberId()).isEqualTo(memberId);
  }

  @DisplayName("멤버 아이디로 토큰을 DB에서 지울 수 있다.")
  @Test
  void deleteTokenByTokenId() {
    // when
    refreshTokenRepository.deleteRefreshTokenByMemberId(memberId);

    // then
    RefreshTokenDto deletedToken = refreshTokenRepository.findRefreshTokenByMemberId(memberId);
    Assertions.assertThat(deletedToken).isNull();
  }

}