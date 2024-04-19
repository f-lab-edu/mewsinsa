package com.mewsinsa.order.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public enum OrderStatus {
  BEFORE_PAYMENT("입금 전"),
  CONFIRMED_PAYMENT("입금 확인"),
  PREPARING_FOR_DELIVERY("출고 처리 중"),
  IN_TRANSIT("배송 중"),
  DELIVERY_COMPLETED("배송 완료"),
  WAITING_TO_CANCEL_ORDER("주문 취소 대기 중"),
  ORDER_CANCELLATION_COMPLETED("주문 취소 완료");


  private String statusDescription;

  OrderStatus(String statusDescription) {
    this.statusDescription = statusDescription;
  }

  public String getStatusDescription() {
    return statusDescription;
  }
}
