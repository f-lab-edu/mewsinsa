package com.mewsinsa.order.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderedProduct {
  private Long productOptionId;
  private Long productOptionCount;
  private Long appliedCouponId;
  private Long orderedProductId;
  private Long piecePrice; // 개당 주문 가격
  private Long orderId;

  //==Constructor==//
  public OrderedProduct() {
  }

  public OrderedProduct(Long productOptionId, Long productOptionCount,
      Long appliedCouponId, Long piecePrice, Long orderId) {
    this.productOptionId = productOptionId;
    this.productOptionCount = productOptionCount;
    this.appliedCouponId = appliedCouponId;
    this.piecePrice = piecePrice;
    this.orderId = orderId;
  }

  //==Getter==//
  public Long getProductOptionId() {
    return productOptionId;
  }

  public Long getProductOptionCount() {
    return productOptionCount;
  }

  public Long getAppliedCouponId() {
    return appliedCouponId;
  }

  public Long getOrderedProductId() {
    return orderedProductId;
  }

  public Long getPiecePrice() {
    return piecePrice;
  }

  public Long getOrderId() {
    return orderId;
  }

  //==Setter==//
  public void setProductOptionId(Long productOptionId) {
    this.productOptionId = productOptionId;
  }

  public void setProductOptionCount(Long productOptionCount) {
    this.productOptionCount = productOptionCount;
  }

  public void setAppliedCouponId(Long appliedCouponId) {
    this.appliedCouponId = appliedCouponId;
  }

  public void setOrderedProductId(Long orderedProductId) {
    this.orderedProductId = orderedProductId;
  }

  public void setPiecePrice(Long piecePrice) {
    this.piecePrice = piecePrice;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }
}
