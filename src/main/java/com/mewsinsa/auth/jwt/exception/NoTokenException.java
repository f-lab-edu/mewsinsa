package com.mewsinsa.auth.jwt.exception;

public class NoTokenException extends RuntimeException {
  public NoTokenException(String message) {
    super(message);
  }
}
