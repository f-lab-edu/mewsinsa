package com.mewsinsa.global.error.exception.order;

import com.mewsinsa.global.error.exception.BaseException;
import com.mewsinsa.global.response.DetailedStatus;

public class NotApplicapableCouponException extends BaseException {

  public NotApplicapableCouponException() {
    super(DetailedStatus.NOT_APPLICAPABLE_COUPN);
  }
}
