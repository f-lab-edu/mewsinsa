package com.mewsinsa.order.controller.dto.admin;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDateTime;
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderListResponseForAdminDto {
  private Long orderId;
  private Long memberId;
  private String memberName;
  private String receiverName;
  private String receiverAddress;
  private String receiverPhone;
  private LocalDateTime orderedAt;

  //==Getter==//
  public Long getOrderId() {
    return orderId;
  }

  public Long getMemberId() {
    return memberId;
  }

  public String getMemberName() {
    return memberName;
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

  public LocalDateTime getOrderedAt() {
    return orderedAt;
  }

  //==Setter==//
  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }

  public void setMemberName(String memberName) {
    this.memberName = memberName;
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

  public void setOrderedAt(LocalDateTime orderedAt) {
    this.orderedAt = orderedAt;
  }
}
