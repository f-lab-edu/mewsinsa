package com.mewsinsa.auth.jwt.exception;

public class NonExistentMemberException extends LoginFailureException {

  public NonExistentMemberException(String id, String message) {
    super(id, message);
  }

  public NonExistentMemberException(String message) {
    super(message);
  }
}
