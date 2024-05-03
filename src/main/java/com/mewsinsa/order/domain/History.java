package com.mewsinsa.order.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDateTime;
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class History {
  private Long orderId;
  private Long orderedProductId;
  private LocalDateTime orderedAt;
  private LocalDateTime paymentConfirmedAt;
  private LocalDateTime refundRequestedAt;
  private LocalDateTime refundCompletedAt;
  private LocalDateTime releaseRequestedAt;
  private LocalDateTime releaseStartedAt;
  private LocalDateTime releaseCompletedAt;
  private LocalDateTime deliveryCompletedAt;
  private String status;

  //==Constructor==//
  public History() {
  }

  public History(Builder builder) {
    this.orderId = builder.orderId;
    this.orderedProductId = builder.orderedProductId;
    this.orderedAt = builder.orderedAt;
    this.paymentConfirmedAt = builder.paymentConfirmedAt;
    this.refundRequestedAt = builder.refundRequestedAt;
    this.refundCompletedAt = builder.refundCompletedAt;
    this.releaseRequestedAt = builder.releaseRequestedAt;
    this.releaseStartedAt = builder.releaseStartedAt;
    this.releaseCompletedAt = builder.releaseCompletedAt;
    this.deliveryCompletedAt = builder.deliveryCompletedAt;
    this.status = builder.status;
  }

  //==Getter==//
  public Long getOrderId() {
    return orderId;
  }

  public Long getOrderedProductId() {
    return orderedProductId;
  }

  public LocalDateTime getOrderedAt() {
    return orderedAt;
  }

  public LocalDateTime getPaymentConfirmedAt() {
    return paymentConfirmedAt;
  }

  public LocalDateTime getRefundRequestedAt() {
    return refundRequestedAt;
  }

  public LocalDateTime getRefundCompletedAt() {
    return refundCompletedAt;
  }

  public LocalDateTime getReleaseRequestedAt() {
    return releaseRequestedAt;
  }

  public LocalDateTime getReleaseStartedAt() {
    return releaseStartedAt;
  }

  public LocalDateTime getReleaseCompletedAt() {
    return releaseCompletedAt;
  }

  public LocalDateTime getDeliveryCompletedAt() {
    return deliveryCompletedAt;
  }

  public String getStatus() {
    return status;
  }

  //==Builder==//
  public static class Builder {
    private Long orderId;
    private Long orderedProductId;
    private LocalDateTime orderedAt;
    private LocalDateTime paymentConfirmedAt;
    private LocalDateTime refundRequestedAt;
    private LocalDateTime refundCompletedAt;
    private LocalDateTime releaseRequestedAt;
    private LocalDateTime releaseStartedAt;
    private LocalDateTime releaseCompletedAt;
    private LocalDateTime deliveryCompletedAt;
    private String status;


    public Builder orderId(Long orderId) {
      this.orderId = orderId;
      return this;
    }


    public Builder orderedProductId(Long orderedProductId) {
      this.orderedProductId = orderedProductId;
      return this;
    }

    public Builder orderedAt(LocalDateTime orderedAt) {
      this.orderedAt = orderedAt;
      return this;
    }

    public Builder status(String status) {
      this.status = status;
      return this;
    }

    public History build() {
      return new History(this);
    }
  }
}
