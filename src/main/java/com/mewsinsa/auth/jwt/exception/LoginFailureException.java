package com.mewsinsa.auth.jwt.exception;

public class LoginFailureException extends RuntimeException {
  String id;
  String message;

  public LoginFailureException(String id, String message) {
    this.id = id;
    this.message = message;
  }
}
