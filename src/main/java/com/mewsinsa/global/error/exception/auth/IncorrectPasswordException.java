package com.mewsinsa.global.error.exception.auth;

import com.mewsinsa.global.error.exception.BaseException;
import com.mewsinsa.global.response.DetailedStatus;

public class IncorrectPasswordException extends BaseException {

  public IncorrectPasswordException() {
    super(DetailedStatus.INCORRECT_PASSWORD);
  }
}
