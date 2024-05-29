package com.mewsinsa.global.error.exception.coupon;

import com.mewsinsa.global.error.exception.BaseException;
import com.mewsinsa.global.response.DetailedStatus;

public class DuplicatedIssuedCouponException extends BaseException {

  public DuplicatedIssuedCouponException() {
    super(DetailedStatus.DUPLICATED_ISSUED_COUPON);
  }
}
