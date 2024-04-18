package com.mewsinsa.order.controller.dto;

import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.constraints.Range;

public class OrderedProductDto {

  private Long productOptionId;
  private Long productOptionCount;
  private Long appliedCouponId;
  private Long orderedProductId;
  private Long piecePrice;

  //==Constructor==//
  public OrderedProductDto() {
  }

  public OrderedProductDto(Long productOptionId, Long productOptionCount, Long appliedCouponId,
      Long orderedProductId, Long piecePrice) {
    this.productOptionId = productOptionId;
    this.productOptionCount = productOptionCount;
    this.appliedCouponId = appliedCouponId;
    this.orderedProductId = orderedProductId;
    this.piecePrice = piecePrice;
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
}
