package com.mewsinsa.global.error.exception.coupon;

import com.mewsinsa.global.error.exception.BaseException;
import com.mewsinsa.global.response.DetailedStatus;

public class FailToIssueCouponException extends BaseException {

  public FailToIssueCouponException() {
    super(DetailedStatus.NON_EXSISTENT_COUPON);
  }
}
