package com.mewsinsa.global.error.exception.coupon;

import com.mewsinsa.global.error.exception.BaseException;
import com.mewsinsa.global.response.DetailedStatus;

public class FailToUseCouponException extends BaseException {

  public FailToUseCouponException() {
    super(DetailedStatus.INTERNAL_SERER_ERROR);
  }
}
