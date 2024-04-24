package com.mewsinsa.auth.jwt.exception;

public class LoginFailureException extends RuntimeException {
  String id;

  public LoginFailureException(String id, String message) {
    super(message);
    this.id = id;
  }

  public LoginFailureException(String message) {
    super(message);
  }
}
