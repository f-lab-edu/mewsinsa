package com.mewsinsa.auth.jwt.service;

import static org.junit.jupiter.api.Assertions.*;

import com.mewsinsa.auth.jwt.redis.dto.RedisAccessToken;
import com.mewsinsa.auth.jwt.redis.dto.RedisRefreshToken;
import com.mewsinsa.auth.jwt.redis.repository.RedisAccessTokenRepository;
import com.mewsinsa.auth.jwt.redis.repository.RedisRefreshTokenRepository;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JwtServiceTest {
  @Autowired
  private JwtService jwtService;

  @Autowired
  private RedisAccessTokenRepository redisAccessTokenRepository;
  @Autowired
  private RedisRefreshTokenRepository redisRefreshTokenRepository;

  @DisplayName("로그인 테스트")
  @Test
  void loginTest() {
    jwtService.login(3L);

    RedisAccessToken accessToken = redisAccessTokenRepository.findById("3").get();
    RedisRefreshToken refreshToken = redisRefreshTokenRepository.findById("3").get();

    System.out.println(accessToken.getAccessToken());

    Assertions.assertThat(accessToken.getMemberId()).isEqualTo(3L);
    Assertions.assertThat(refreshToken.getMemberId()).isEqualTo(3L);
  }

}