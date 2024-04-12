package com.mewsinsa.auth.jwt.repository;

import com.mewsinsa.auth.jwt.controller.dto.RefreshTokenDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RefreshTokenRepository {

  // 회원의 아이디로 리프레시 토큰 찾기
  RefreshTokenDto findRefreshTokenByMemberId(@Param("memberId") Long memberId);

  // 리프레시 토큰 추가
  void addRefreshToken(RefreshTokenDto refreshToken);

  // 리프레시 토큰 지우기
  void deleteRefreshTokenByMemberId(@Param("memberId") Long memberId);

  public void deleteRefreshTokenByTokenValue(String refreshToken);
}
