package com.mewsinsa.member.service;

import com.mewsinsa.auth.jwt.JwtProvider;
import com.mewsinsa.global.error.exception.auth.NoTokenException;
import com.mewsinsa.auth.jwt.redis.dto.RedisAccessToken;
import com.mewsinsa.auth.jwt.redis.repository.RedisAccessTokenRepository;
import com.mewsinsa.member.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

  private final RedisAccessTokenRepository redisAccessTokenRepository;
  private final MemberRepository memberRepository;
  private final JwtProvider jwtProvider;

  public MemberService(
      RedisAccessTokenRepository redisAccessTokenRepository,
      MemberRepository memberRepository,
      JwtProvider jwtProvider) {
    this.redisAccessTokenRepository = redisAccessTokenRepository;
    this.memberRepository = memberRepository;
    this.jwtProvider = jwtProvider;
  }

  public Long getMemberIdByAccessToken(String accessToken) {
    Jws<Claims> claimsJws = jwtProvider.parseClaims(accessToken);
    Claims payload = claimsJws.getPayload();
    String memberId = payload.getSubject();

    return Long.parseLong(memberId);
  }

  public Integer getTierIdByAccessToken(String accessToken) {
    Long memberId = getMemberIdByAccessToken(accessToken);
    String strMemberId = Long.toString(memberId);

    Optional<RedisAccessToken> accessTokenOptional = redisAccessTokenRepository.findById(strMemberId);

    if(accessTokenOptional.isEmpty()) {
      throw new NoTokenException();
    }

    RedisAccessToken redisAccessToken = accessTokenOptional.get();
    return redisAccessToken.getMember().getTierId();
  }


}
