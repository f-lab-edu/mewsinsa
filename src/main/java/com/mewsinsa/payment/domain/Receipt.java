package com.mewsinsa.payment.domain;

import java.time.LocalDateTime;

public class Receipt {
  private Long receiptId;
  private Long orderId;
  private Long totalPrice;
  private String paymentMethod;
  private LocalDateTime paidAt;
  private Long points;
  private Long usedPoints;
  private Boolean usePointsInAdvance;
  private Boolean isRefunded;

  //==Constructor==//
  public Receipt() {
  }
  public Receipt(Builder builder) {
    this.receiptId = builder.receiptId;
    this.orderId = builder.orderId;
    this.totalPrice = builder.totalPrice;
    this.paymentMethod = builder.paymentMethod;
    this.paidAt = builder.paidAt;
    this.points = builder.points;
    this.usedPoints = builder.usedPoints;
    this.usePointsInAdvance = builder.usePointsInAdvance;
    this.isRefunded = builder.isRefunded;
  }

  //==Getter==//
  public Long getReceiptId() {
    return receiptId;
  }

  public Long getOrderId() {
    return orderId;
  }

  public Long getTotalPrice() {
    return totalPrice;
  }

  public String getPaymentMethod() {
    return paymentMethod;
  }

  public LocalDateTime getPaidAt() {
    return paidAt;
  }

  public Long getPoints() {
    return points;
  }

  public Long getUsedPoints() {
    return usedPoints;
  }

  public Boolean getUsePointsInAdvance() {
    return usePointsInAdvance;
  }

  public Boolean getRefunded() {
    return isRefunded;
  }

  //==Setter==//
  public void setRefunded(Boolean refunded) {
    isRefunded = refunded;
  }

  //==Builder==//
  public static class Builder {
    private Long receiptId;
    private Long orderId;
    private Long totalPrice;
    private String paymentMethod;
    private LocalDateTime paidAt;
    private Long points;
    private Long usedPoints;
    private Boolean usePointsInAdvance;
    private Boolean isRefunded;

    public Builder receiptId(Long receiptId) {
      this.receiptId = receiptId;
      return this;
    }

    public Builder orderId(Long orderId) {
      this.orderId = orderId;
      return this;
    }

    public Builder totalPrice(Long totalPrice) {
      this.totalPrice = totalPrice;
      return this;
    }

    public Builder paymentMethod(String paymentMethod) {
      this.paymentMethod = paymentMethod;
      return this;
    }

    public Builder createdAt(LocalDateTime paidAt) {
      this.paidAt = paidAt;
      return this;
    }

    public Builder points(Long points) {
      this.points = points;
      return this;
    }

    public Builder usedPoints(Long usedPoints) {
      this.usedPoints = usedPoints;
      return this;
    }

    public Builder usePointsInAdvance(Boolean usePointsInAdvance) {
      this.usePointsInAdvance = usePointsInAdvance;
      return this;
    }

    public Builder refunded(Boolean refunded) {
      isRefunded = refunded;
      return this;
    }

    public Receipt build() {
      return new Receipt(this);
    }
  }
}
