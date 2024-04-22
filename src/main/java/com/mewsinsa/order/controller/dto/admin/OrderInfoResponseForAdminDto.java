package com.mewsinsa.order.controller.dto.admin;

import com.mewsinsa.order.controller.dto.OrderDeliveryAddressDto;
import com.mewsinsa.order.controller.dto.OrderedProductDto;
import com.mewsinsa.order.domain.OrderedProduct;
import java.time.LocalDateTime;
import java.util.List;

public class OrderInfoResponseForAdminDto {
  private Long orderId;
  private Long memberId;
  private String memberName;
  private String receiverAddress;
  private String receiverName;
  private String receiverPhone;
  private List<OrderedProduct> orderedProductList;
  private Long totalPrice;
  private String paymentMethod;
  private LocalDateTime orderedAt;
  private String status;

  //==Getter==//
  public Long getOrderId() {
    return orderId;
  }

  public Long getMemberId() {
    return memberId;
  }


  public String getReceiverAddress() {
    return receiverAddress;
  }

  public String getReceiverName() {
    return receiverName;
  }

  public String getReceiverPhone() {
    return receiverPhone;
  }

  public List<OrderedProduct> getOrderedProductList() {
    return orderedProductList;
  }

  public Long getTotalPrice() {
    return totalPrice;
  }

  public String getPaymentMethod() {
    return paymentMethod;
  }

  public LocalDateTime getOrderedAt() {
    return orderedAt;
  }

  public String getStatus() {
    return status;
  }

  public String getMemberName() {
    return memberName;
  }

  //==Setter==//
  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }


  public void setReceiverAddress(String receiverAddress) {
    this.receiverAddress = receiverAddress;
  }

  public void setReceiverName(String receiverName) {
    this.receiverName = receiverName;
  }

  public void setReceiverPhone(String receiverPhone) {
    this.receiverPhone = receiverPhone;
  }

  public void setOrderedProductList(
      List<OrderedProduct> orderedProductList) {
    this.orderedProductList = orderedProductList;
  }

  public void setTotalPrice(Long totalPrice) {
    this.totalPrice = totalPrice;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public void setOrderedAt(LocalDateTime orderedAt) {
    this.orderedAt = orderedAt;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setMemberName(String memberName) {
    this.memberName = memberName;
  }
}
