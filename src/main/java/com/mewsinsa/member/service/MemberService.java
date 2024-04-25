package com.mewsinsa.member.service;

import com.mewsinsa.auth.jwt.controller.dto.AccessTokenDto;
import com.mewsinsa.auth.jwt.exception.NonExistentMemberException;
import com.mewsinsa.auth.jwt.repository.AccessTokenRepository;
import com.mewsinsa.member.domain.Member;
import com.mewsinsa.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

  private final AccessTokenRepository accessTokenRepository;
  private final MemberRepository memberRepository;

  public MemberService(AccessTokenRepository accessTokenRepository,
      MemberRepository memberRepository) {
    this.accessTokenRepository = accessTokenRepository;
    this.memberRepository = memberRepository;
  }

  public Long getMemberIdByAccessToken(String accessToken) {
    AccessTokenDto accessTokenDto = accessTokenRepository.findAccessTokenByTokenValue(
        accessToken);

    if(accessTokenDto == null) {
      throw new NonExistentMemberException("존재하지 않는 토큰입니다.");
    }

    return accessTokenDto.getMemberId();
  }

  public Integer getTierIdByAccessToken(String accessToken) {
    Long memberId = getMemberIdByAccessToken(accessToken);

    Member member = memberRepository.findMemberById(memberId);
    return member.getTierId();
  }


}
