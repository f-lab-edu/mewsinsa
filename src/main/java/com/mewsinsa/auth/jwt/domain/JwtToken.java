package com.mewsinsa.auth.jwt.domain;

public class JwtToken {
  private String accessToken;
  private String refreshToken;

  //==Constructor==//
  public JwtToken(String accessToken, String refreshToken) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }

  //==Getter==//
  public String getAccessToken() {
    return accessToken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  //==Setter==//
  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
}
