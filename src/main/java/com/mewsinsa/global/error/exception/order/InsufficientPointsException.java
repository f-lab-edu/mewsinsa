package com.mewsinsa.global.error.exception.order;

import com.mewsinsa.global.error.exception.BaseException;
import com.mewsinsa.global.response.DetailedStatus;

public class InsufficientPointsException extends BaseException {

  public InsufficientPointsException() {
    super(DetailedStatus.INSUFFICIENT_POINTS);
  }
}
