package com.mewsinsa.order.exception;

public class InvalidProductOptionException extends OrderException {
  private Long productOptionId;

  public InvalidProductOptionException(String message, Long productOptionId) {
    super(message);
    this.productOptionId = productOptionId;
  }

  public Long getProductOptionId() {
    return productOptionId;
  }
}
