package com.mewsinsa.display.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductDisplay {
  private Long productId;
  private Long originalPrice;
  private Long promotionPrice;
  private Long couponDiscountAmount;
  private Integer discountRate;
  private Boolean hasGift;

  //==Getter==//
  public Long getProductId() {
    return productId;
  }

  public Long getOriginalPrice() {
    return originalPrice;
  }

  public Long getPromotionPrice() {
    return promotionPrice;
  }

  public Long getCouponDiscountAmount() {
    return couponDiscountAmount;
  }

  public Integer getDiscountRate() {
    return discountRate;
  }

  public Boolean getHasGift() {
    return hasGift;
  }

  //==Setter==//
  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public void setOriginalPrice(Long originalPrice) {
    this.originalPrice = originalPrice;
  }

  public void setPromotionPrice(Long promotionPrice) {
    this.promotionPrice = promotionPrice;
  }

  public void setCouponDiscountAmount(Long couponDiscountAmount) {
    this.couponDiscountAmount = couponDiscountAmount;
  }

  public void setDiscountRate(Integer discountRate) {
    this.discountRate = discountRate;
  }

  public void setHasGift(Boolean hasGift) {
    this.hasGift = hasGift;
  }
}
