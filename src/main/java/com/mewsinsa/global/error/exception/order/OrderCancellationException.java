package com.mewsinsa.global.error.exception.order;

import com.mewsinsa.global.error.exception.BaseException;
import com.mewsinsa.global.response.DetailedStatus;

public class OrderCancellationException extends BaseException {

  public OrderCancellationException() {
    super(DetailedStatus.NON_CANCELLABLE_ORDER);
  }
}
