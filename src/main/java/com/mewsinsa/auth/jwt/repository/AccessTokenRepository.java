package com.mewsinsa.auth.jwt.repository;

import com.mewsinsa.auth.jwt.controller.dto.AccessTokenDto;
import com.mewsinsa.member.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccessTokenRepository {
  public AccessTokenDto findAccessTokenByTokenValue(@Param("accessToken") String accessToken);

  public AccessTokenDto findAccessTokenByMemberId(@Param("memberId") Long memberId);

  public void addAccessToken(AccessTokenDto accessToken);

  public void deleteAccessTokenByMemberId(@Param("memberId") Long memberId);

  public void deleteAccessTokenByTokenValue(String accessToken);

  public Member findMemberByAccessTokenValue(@Param("accessToken") String accessToken);
}
