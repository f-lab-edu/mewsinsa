package com.mewsinsa.order.exception;

public class OrderCancellationException extends OrderException {
  private String status;

  public OrderCancellationException(String message, String status) {
    super(message);
    this.status = status;
  }

  public String getStatus() {
    return status;
  }
}
