package com.mewsinsa.auth.jwt.redis.repository;

import com.mewsinsa.auth.jwt.redis.dto.RedisAccessToken;
import com.mewsinsa.member.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// redis에 토큰이 제대로 저장되고 읽어지는지 테스트합니다.
@SpringBootTest
class RedisTokenRepositoryTest {
  @Autowired
  private RedisAccessTokenRepository redisTokenRepository;

  @DisplayName("redis에 토큰이 제대로 저장되는지 테스트합니다.")
  @Test
  void redisSaveTokenTest() {
    Member member = new Member.Builder()
        .memberId(123L)
        .name("sejeong")
        .nickname("clean")
        .email("test@test.com")
        .phone("010-1234-1234")
        .build();
    String accessTokenValue = "random string~~~";
    RedisAccessToken accessToken = new RedisAccessToken();
    accessToken.setAccessToken(accessTokenValue);
    accessToken.setMember(member);
    accessToken.setMemberId(123L);

    redisTokenRepository.save(accessToken);

    RedisAccessToken accessTokenFoundById = redisTokenRepository.findById("123").get();


    Assertions.assertThat(accessTokenFoundById.getMember().getName()).isEqualTo("sejeong");
    Assertions.assertThat(accessTokenFoundById.getMember().getEmail()).isEqualTo("test@test.com");

  }

}