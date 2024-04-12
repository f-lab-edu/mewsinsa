package com.mewsinsa.auth.jwt.exception;

public class IncorrectPasswordException extends LoginFailureException {

  public IncorrectPasswordException(String id, String message) {
    super(id, message);
  }
}
