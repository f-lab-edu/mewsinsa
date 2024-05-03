package com.mewsinsa.coupon.exception;

import com.mewsinsa.global.response.DetailedStatus;

public class FailToIssueCouponException extends RuntimeException {
  private final DetailedStatus status;
  public FailToIssueCouponException(DetailedStatus status) {
    this.status = status;
  }

  public DetailedStatus getStatus() {
    return status;
  }
}
