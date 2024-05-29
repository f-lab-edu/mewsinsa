package com.mewsinsa.global.error.exception;

import com.mewsinsa.global.response.DetailedStatus;

public class BaseException extends RuntimeException {
  protected DetailedStatus status;

  public DetailedStatus getStatus() {
    return status;
  }

  public BaseException(DetailedStatus status) {
    this.status = status;
  }

  public BaseException(DetailedStatus status, String message) {
    super(message);
    this.status = status;
  }
}
