package com.mewsinsa.order.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Order {
  private Long orderId;
  private Long memberId;
  private String receiverName;
  private String receiverAddress;
  private String receiverPhone;

  //==Contructor==//
  public Order() {
  }

  public Order(Builder builder) {
    this.orderId = builder.orderId;
    this.memberId = builder.memberId;
    this.receiverName = builder.receiverName;
    this.receiverAddress = builder.receiverAddress;
    this.receiverPhone = builder.receiverPhone;
  }

  //==Getter==//
  public Long getOrderId() {
    return orderId;
  }

  public Long getMemberId() {
    return memberId;
  }

  public String getReceiverName() {
    return receiverName;
  }

  public String getReceiverAddress() {
    return receiverAddress;
  }

  public String getReceiverPhone() {
    return receiverPhone;
  }

  //==Setter==//
  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }

  public void setReceiverName(String receiverName) {
    this.receiverName = receiverName;
  }

  public void setReceiverAddress(String receiverAddress) {
    this.receiverAddress = receiverAddress;
  }

  public void setReceiverPhone(String receiverPhone) {
    this.receiverPhone = receiverPhone;
  }

  //==Builder==//
  public static class Builder {
    private Long orderId;
    private Long memberId;
    private String receiverName;
    private String receiverAddress;
    private String receiverPhone;

    public Builder orderId(Long orderId) {
      this.orderId = orderId;
      return this;
    }

    public Builder memberId(Long memberId) {
      this.memberId = memberId;
      return this;
    }

    public Builder receiverName(String receiverName) {
      this.receiverName = receiverName;
      return this;
    }

    public Builder receiverAddress(String receiverAddress) {
      this.receiverAddress = receiverAddress;
      return this;
    }

    public Builder receiverPhone(String receiverPhone) {
      this.receiverPhone = receiverPhone;
      return this;
    }

    public Order build() {
      return new Order(this);
    }
  }
}
