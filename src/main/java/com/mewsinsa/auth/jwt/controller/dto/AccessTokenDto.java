package com.mewsinsa.auth.jwt.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDateTime;
import java.util.Date;
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccessTokenDto {
  private Long memberId;
  private String tokenValue;
  private Date expiration;

  //==Constructor==//
  public AccessTokenDto() {
  }

  public AccessTokenDto(Long memberId, String tokenValue, Date expiration) {
    this.memberId = memberId;
    this.tokenValue = tokenValue;
    this.expiration = expiration;
  }

  //==Getter==//
  public Long getMemberId() {
    return memberId;
  }

  public String getTokenValue() {
    return tokenValue;
  }

  public Date getExpiration() {
    return expiration;
  }

  //==Setter==//
  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }

  public void setTokenValue(String tokenValue) {
    this.tokenValue = tokenValue;
  }

  public void setExpiration(Date expiration) {
    this.expiration = expiration;
  }
}
