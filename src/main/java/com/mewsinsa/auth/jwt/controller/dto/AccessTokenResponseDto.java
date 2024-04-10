package com.mewsinsa.auth.jwt.controller.dto;

public class AccessTokenResponseDto {
  private String accessToken;

  public AccessTokenResponseDto(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }
}
