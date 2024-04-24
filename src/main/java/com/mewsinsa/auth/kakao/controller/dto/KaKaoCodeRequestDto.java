package com.mewsinsa.auth.kakao.controller.dto;

public class KaKaoCodeRequestDto {
  private String clientId;
  private String redirectUri;
  private String responseType;
  private String scope;

  //==Constructor==//
  private KaKaoCodeRequestDto(Builder builder) {
    this.clientId = builder.clientId;
    this.redirectUri = builder.redirectUri;
    this.responseType = builder.responseType;
    this.scope = builder.scope;
  }

  //==Getter==//
  public String getClientId() {
    return clientId;
  }

  public String getRedirectUri() {
    return redirectUri;
  }

  public String getResponseType() {
    return responseType;
  }

  public String getScope() {
    return scope;
  }

  //==Builder==//
  public static class Builder {
    private String clientId;
    private String redirectUri;
    private String responseType;
    private String scope;

    public Builder clientId(String clientId) {
      this.clientId = clientId;
      return this;
    }

    public Builder redirectUri(String redirectUri) {
      this.redirectUri = redirectUri;
      return this;
    }

    public Builder responseType(String responseType) {
      this.responseType = responseType;
      return this;
    }

    public Builder scope(String scope) {
      this.scope = scope;
      return this;
    }

    public KaKaoCodeRequestDto build() {
      return new KaKaoCodeRequestDto(this);
    }
  }

}
