package com.mewsinsa.order.controller.dto;

public class OrderDeliveryAddressDto {
  private String receiverName;
  private String receiverPhone;
  private String receiverAddress;

  //==Gertter==//
  public String getReceiverName() {
    return receiverName;
  }

  public String getReceiverPhone() {
    return receiverPhone;
  }

  public String getReceiverAddress() {
    return receiverAddress;
  }

  //==Setter==//
  public void setReceiverName(String receiverName) {
    this.receiverName = receiverName;
  }

  public void setReceiverPhone(String receiverPhone) {
    this.receiverPhone = receiverPhone;
  }

  public void setReceiverAddress(String receiverAddress) {
    this.receiverAddress = receiverAddress;
  }
}
