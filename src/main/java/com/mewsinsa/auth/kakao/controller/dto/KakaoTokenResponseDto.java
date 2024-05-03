package com.mewsinsa.auth.kakao.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KakaoTokenResponseDto {

  /**
   * tokenType: bearer로 고정
   */
  @JsonProperty("token_type")
  private String tokenType;
  @JsonProperty("access_token")
  private String accessToken;
  /**
   * 액세스 토큰과 ID 토큰의 만료 시간(초)
   */
  @JsonProperty("expires_in")
  private Integer expiresIn;
  @JsonProperty("refresh_token")
  private String refreshToken;
  /**
   * 리프레시 토큰 만료 시간(초)
   */
  @JsonProperty("refresh_token_expires_in")
  private Integer refreshTokenExpiresIn;

  @JsonProperty("id_token")
  private String idToken;
  @JsonProperty("scope")
  private String scope;

  //==Getter==//
  public String getTokenType() {
    return tokenType;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public Integer getExpiresIn() {
    return expiresIn;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public Integer getRefreshTokenExpiresIn() {
    return refreshTokenExpiresIn;
  }

  public String getIdToken() {
    return idToken;
  }

  public String getScope() {
    return scope;
  }

  //==Setter==//
  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public void setExpiresIn(Integer expiresIn) {
    this.expiresIn = expiresIn;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public void setRefreshTokenExpiresIn(Integer refreshTokenExpiresIn) {
    this.refreshTokenExpiresIn = refreshTokenExpiresIn;
  }

  public void setIdToken(String idToken) {
    this.idToken = idToken;
  }

  public void setScope(String scope) {
    this.scope = scope;
  }
}
