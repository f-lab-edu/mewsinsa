package com.mewsinsa.order.controller.dto;

import java.time.LocalDateTime;

public class OrderResponseDto {
  private Long orderId;
  private LocalDateTime orderedAt;

  //==Constructor==//
  public OrderResponseDto() {
  }

  public OrderResponseDto(Long orderId, LocalDateTime orderedAt) {
    this.orderId = orderId;
    this.orderedAt = orderedAt;
  }

  //==Getter==//
  public Long getOrderId() {
    return orderId;
  }

  public LocalDateTime getOrderedAt() {
    return orderedAt;
  }

}
