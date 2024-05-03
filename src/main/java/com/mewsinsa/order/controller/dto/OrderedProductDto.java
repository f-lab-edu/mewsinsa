package com.mewsinsa.order.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.constraints.Range;
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderedProductDto {
  private Long productId;
  private Long productOptionId;
  private Long quantity;
  private Long couponId;
  private Long points;
  private Long couponDiscountAmount;
  private Long pieceOriginalPrice;
  private Long piecePromotionPrice;
  private Long pieceTierDiscountAmount;

  //==Getter==//
  public Long getProductId() {
    return productId;
  }

  public Long getProductOptionId() {
    return productOptionId;
  }

  public Long getQuantity() {
    return quantity;
  }

  public Long getCouponId() {
    return couponId;
  }

//  public Long getOrderId() {
//    return orderId;
//  }

  public Long getPoints() {
    return points;
  }

  public Long getCouponDiscountAmount() {
    return couponDiscountAmount;
  }

  public Long getPieceOriginalPrice() {
    return pieceOriginalPrice;
  }

  public Long getPiecePromotionPrice() {
    return piecePromotionPrice;
  }

  public Long getPieceTierDiscountAmount() {
    return pieceTierDiscountAmount;
  }

  //==Setter==//
  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public void setProductOptionId(Long productOptionId) {
    this.productOptionId = productOptionId;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  public void setCouponId(Long couponId) {
    this.couponId = couponId;
  }

//  public void setOrderId(Long orderId) {
//    this.orderId = orderId;
//  }

  public void setPoints(Long points) {
    this.points = points;
  }

  public void setCouponDiscountAmount(Long couponDiscountAmount) {
    this.couponDiscountAmount = couponDiscountAmount;
  }

  public void setPieceOriginalPrice(Long pieceOriginalPrice) {
    this.pieceOriginalPrice = pieceOriginalPrice;
  }

  public void setPiecePromotionPrice(Long piecePromotionPrice) {
    this.piecePromotionPrice = piecePromotionPrice;
  }

  public void setPieceTierDiscountAmount(Long pieceTierDiscountAmount) {
    this.pieceTierDiscountAmount = pieceTierDiscountAmount;
  }
}
