package com.mewsinsa.order.controller.dto.form;

import com.mewsinsa.delivery.domain.DeliveryAddress;
import java.util.List;

public class OrderFormResponseDto {
  private DeliveryAddress deliveryAddress;
  private List<OrderedProductInfoDto> orderedProductInfoList;

  private Long totalTierDiscountAmount;
  private Long totalPoints;

  public OrderFormResponseDto(DeliveryAddress deliveryAddress,
      List<OrderedProductInfoDto> orderedProductInfoList, Long totalTierDiscountAmount,
      Long totalPoints) {
    this.deliveryAddress = deliveryAddress;

    this.orderedProductInfoList = orderedProductInfoList;
    this.totalTierDiscountAmount = totalTierDiscountAmount;
    this.totalPoints = totalPoints;
  }

  //==Getter==//
  public DeliveryAddress getDeliveryAddress() {
    return deliveryAddress;
  }

  public List<OrderedProductInfoDto> getOrderedProductInfoList() {
    return orderedProductInfoList;
  }

  public Long getTotalTierDiscountAmount() {
    return totalTierDiscountAmount;
  }

  public Long getTotalPoints() {
    return totalPoints;
  }

  //==Setter==//
  public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
    this.deliveryAddress = deliveryAddress;
  }

  public void setOrderedProductInfoList(
      List<OrderedProductInfoDto> orderedProductInfoList) {
    this.orderedProductInfoList = orderedProductInfoList;
  }

  public void setTotalTierDiscountAmount(Long totalTierDiscountAmount) {
    this.totalTierDiscountAmount = totalTierDiscountAmount;
  }

  public void setTotalPoints(Long totalPoints) {
    this.totalPoints = totalPoints;
  }
}
