package com.mewsinsa.auth.jwt.repository;

import com.mewsinsa.auth.jwt.controller.dto.LogoutTokenDto;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LogoutTokenRepository {
  public LogoutTokenDto findLogoutTokenByTokenValue(@Param("accessToken") String accessToken);

  public LogoutTokenDto findLogoutTokenByMemberId(@Param("memberId") Long memberId);

  public void addLogoutToken(LogoutTokenDto logoutToken);

  public void deleteLogoutToken(@Param("memberId") Long memberId);

}
