package com.mewsinsa.coupon.domain;

public enum CouponType {
  FIXED_RATE("rate"),
  FIXED_AMOUNT("amount");
  private final String type;

  CouponType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}
