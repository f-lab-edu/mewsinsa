package com.mewsinsa.order.exception;

public class NotApplicapableCouponException extends OrderException {
  private Long productId;
  private Long couponId;

  public NotApplicapableCouponException(String message, Long productId, Long couponId) {
    super(message);

    this.productId = productId;
    this.couponId = couponId;
  }

  public Long getProductId() {
    return productId;
  }

  public Long getCouponId() {
    return couponId;
  }
}
