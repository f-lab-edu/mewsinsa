package com.mewsinsa.auth.jwt.controller.dto;

public class LoginRequestDto {
  private String id;
  private String password;

  public String getId() {
    return id;
  }

  public String getPassword() {
    return password;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
