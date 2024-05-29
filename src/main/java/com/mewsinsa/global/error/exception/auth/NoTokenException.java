package com.mewsinsa.global.error.exception.auth;

import com.mewsinsa.global.error.exception.BaseException;
import com.mewsinsa.global.response.DetailedStatus;

public class NoTokenException extends BaseException {

  public NoTokenException() {
    super(DetailedStatus.NO_TOKEN);
  }
}
