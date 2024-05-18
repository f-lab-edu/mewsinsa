package com.mewsinsa.global.error.exception.coupon;

import com.mewsinsa.global.error.exception.BaseException;
import com.mewsinsa.global.response.DetailedStatus;

public class CouponOutOfRemainingException extends BaseException {

  public CouponOutOfRemainingException() {
    super(DetailedStatus.OUT_OF_REMAINING);
  }
}
