package com.mewsinsa.order.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.hibernate.validator.constraints.Range;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderedProductRequestDto {
  @Range(min=1, max=Integer.MAX_VALUE)
  private Long productOptionId;
  @Range(min=1, max=Integer.MAX_VALUE)
  private Long productOptionCount;
  @Range(min=1, max=Integer.MAX_VALUE)
  private Long appliedCouponId;

  //==Constructor==//
  public OrderedProductRequestDto() {
  }

  public OrderedProductRequestDto(Long productOptionId, Long productOptionCount, Long appliedCouponId) {
    this.productOptionId = productOptionId;
    this.productOptionCount = productOptionCount;
    this.appliedCouponId = appliedCouponId;
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
}
