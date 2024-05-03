package com.mewsinsa.order.exception;

public class NonExsistentOrderException extends OrderException {

  public NonExsistentOrderException(String message) {
    super(message);
  }
}
