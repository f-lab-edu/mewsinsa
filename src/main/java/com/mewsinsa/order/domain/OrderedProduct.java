package com.mewsinsa.order.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderedProduct {
  private Long orderedProductId;
  private Long productOptionId;
  private Long quantity;
  private Long couponId;
  private Long piecePrice; // 개당 주문 가격
  private Long orderId;
  private Boolean isCancelled;

  //==Constructor==//
  public OrderedProduct() {
  }

  public OrderedProduct(Long productOptionId, Long quantity,
      Long couponId, Long piecePrice, Long orderId, Boolean isCancelled) {
    this.productOptionId = productOptionId;
    this.quantity = quantity;
    this.couponId = couponId;
    this.piecePrice = piecePrice;
    this.orderId = orderId;
    this.isCancelled = isCancelled;
  }

  //==Getter==//
  public Long getProductOptionId() {
    return productOptionId;
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

  public Long getQuantity() {
    return quantity;
  }

  public Long getCouponId() {
    return couponId;
  }

  public Boolean getCancelled() {
    return isCancelled;
  }

  //==Setter==//
  public void setProductOptionId(Long productOptionId) {
    this.productOptionId = productOptionId;
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

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  public void setCouponId(Long couponId) {
    this.couponId = couponId;
  }

  public void setCancelled(Boolean cancelled) {
    isCancelled = cancelled;
  }
}

