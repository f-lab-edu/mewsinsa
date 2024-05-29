package com.mewsinsa.global.error.exception.auth;

import com.mewsinsa.global.error.exception.BaseException;
import com.mewsinsa.global.response.DetailedStatus;

public class InvalidTokenException extends BaseException {

  public InvalidTokenException() {
    super(DetailedStatus.INVALID_TOKEN);
  }
}
