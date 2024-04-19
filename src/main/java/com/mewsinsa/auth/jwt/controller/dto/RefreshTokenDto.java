package com.mewsinsa.auth.jwt.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.Date;
import java.util.UUID;
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RefreshTokenDto {

  private String tokenValue;
  private Long memberId;
  private Date expiration;

  //==Construct==//
  public RefreshTokenDto() {
  }

  public RefreshTokenDto(String tokenValue, Long memberId, Date expiration) {
    this.tokenValue = tokenValue;
    this.memberId = memberId;
    this.expiration = expiration;
  }

  //==Getter==//

  public String getTokenValue() {
    return tokenValue;
  }

  public Long getMemberId() {
    return memberId;
  }

  public Date getExpiration() {
    return expiration;
  }

  //==Setter==//

  public void setTokenValue(String tokenValue) {
    this.tokenValue = tokenValue;
  }

  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }

  public void setExpiration(Date expiration) {
    this.expiration = expiration;
  }
}
