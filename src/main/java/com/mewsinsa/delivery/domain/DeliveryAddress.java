package com.mewsinsa.delivery.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DeliveryAddress {
  private Long deliveryAddressId;
  private Long memberId;
  private String receiverName;
  private String receiverPhone;
  private String deliveryAddress;

  //==Getter==//
  public Long getDeliveryAddressId() {
    return deliveryAddressId;
  }

  public Long getMemberId() {
    return memberId;
  }

  public String getReceiverName() {
    return receiverName;
  }

  public String getReceiverPhone() {
    return receiverPhone;
  }

  public String getDeliveryAddress() {
    return deliveryAddress;
  }

  //==Setter==//
  public void setDeliveryAddressId(Long deliveryAddressId) {
    this.deliveryAddressId = deliveryAddressId;
  }

  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }

  public void setReceiverName(String receiverName) {
    this.receiverName = receiverName;
  }

  public void setReceiverPhone(String receiverPhone) {
    this.receiverPhone = receiverPhone;
  }

  public void setDeliveryAddress(String deliveryAddress) {
    this.deliveryAddress = deliveryAddress;
  }
}
