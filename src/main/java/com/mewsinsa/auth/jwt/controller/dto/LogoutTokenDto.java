package com.mewsinsa.auth.jwt.controller.dto;

import java.time.LocalDateTime;

public class LogoutTokenDto {
  private Long memberId;
  private String tokenValue;
  private LocalDateTime logoutAt;

  //==Constructor==//
  public LogoutTokenDto() {
  }

  public LogoutTokenDto(Long memberId, String tokenValue, LocalDateTime logoutAt) {
    this.memberId = memberId;
    this.tokenValue = tokenValue;
    this.logoutAt = logoutAt;
  }

  //==Getter==//
  public Long getMemberId() {
    return memberId;
  }

  public String getTokenValue() {
    return tokenValue;
  }

  public LocalDateTime getLogoutAt() {
    return logoutAt;
  }

  //==Setter==//
  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }

  public void setTokenValue(String tokenValue) {
    this.tokenValue = tokenValue;
  }

  public void setLogoutAt(LocalDateTime logoutAt) {
    this.logoutAt = logoutAt;
  }
}
