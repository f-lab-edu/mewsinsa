package com.mewsinsa.member.service;

import com.mewsinsa.auth.jwt.JwtProvider;
import com.mewsinsa.auth.jwt.controller.dto.AccessTokenDto;
import com.mewsinsa.auth.jwt.exception.NonExistentMemberException;
import com.mewsinsa.auth.jwt.redis.repository.RedisAccessTokenRepository;
import com.mewsinsa.auth.jwt.repository.AccessTokenRepository;
import com.mewsinsa.member.domain.Member;
import com.mewsinsa.member.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
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

    Member member = memberRepository.findMemberById(memberId);
    return member.getTierId();
  }


}
