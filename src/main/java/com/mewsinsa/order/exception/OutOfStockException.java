package com.mewsinsa.order.exception;

public class OutOfStockException extends OrderException {

  private Long productOptionId;
  private Long stock;
  private Long orderCount;
  public OutOfStockException(Long productOptionId, Long stock, Long orderCount) {
    this.productOptionId = productOptionId;
    this.stock = stock;
    this.orderCount = orderCount;
  }
}
