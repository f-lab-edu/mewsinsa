package com.mewsinsa.global.error.exception.order;

import com.fasterxml.jackson.databind.ser.Serializers.Base;
import com.mewsinsa.global.error.exception.BaseException;
import com.mewsinsa.global.response.DetailedStatus;

public class DuplicatedUsedCouponException extends BaseException {

  public DuplicatedUsedCouponException() {
    super(DetailedStatus.DUPLICATED_ISSUED_COUPON);
  }
}
