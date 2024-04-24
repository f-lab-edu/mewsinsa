package com.mewsinsa.auth.kakao.controller.dto;

public class KakaoCodeResponseDto {
  private String code;
  private String error;
  private String error_description;
  private String state;

  //==Getter==//
  public String getCode() {
    return code;
  }

  public String getError() {
    return error;
  }

  public String getError_description() {
    return error_description;
  }

  public String getState() {
    return state;
  }

  //==Setter==//
  public void setCode(String code) {
    this.code = code;
  }

  public void setError(String error) {
    this.error = error;
  }

  public void setError_description(String error_description) {
    this.error_description = error_description;
  }

  public void setState(String state) {
    this.state = state;
  }
}
