package com.mewsinsa.display.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DisplayProductResponseDto {
  private Long productId;
  private String productName;
  private Long brandId;
  private String brandName;
  private String category;
  private String subcategory;
  private Long originalPrice;
  private Long productLike;
  private Long productClick;
  private Long promotionPrice;
  private Long couponDiscountAmount;
  private Integer discountRate;
  private Boolean hasGift;

  //==Getter==//
  public Long getProductId() {
    return productId;
  }

  public String getProductName() {
    return productName;
  }

  public String getBrandName() {
    return brandName;
  }

  public String getCategory() {
    return category;
  }

  public String getSubcategory() {
    return subcategory;
  }

  public Long getOriginalPrice() {
    return originalPrice;
  }

  public Long getProductLike() {
    return productLike;
  }

  public Long getProductClick() {
    return productClick;
  }

  public Long getPromotionPrice() {
    return promotionPrice;
  }

  public Long getCouponDiscountAmount() {
    return couponDiscountAmount;
  }

  public Long getBrandId() {
    return brandId;
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

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public void setSubcategory(String subcategory) {
    this.subcategory = subcategory;
  }

  public void setOriginalPrice(Long originalPrice) {
    this.originalPrice = originalPrice;
  }

  public void setProductLike(Long productLike) {
    this.productLike = productLike;
  }

  public void setProductClick(Long productClick) {
    this.productClick = productClick;
  }

  public void setPromotionPrice(Long promotionPrice) {
    this.promotionPrice = promotionPrice;
  }

  public void setCouponDiscountAmount(Long couponDiscountAmount) {
    this.couponDiscountAmount = couponDiscountAmount;
  }

  public void setBrandId(Long brandId) {
    this.brandId = brandId;
  }

  public void setHasGift(Boolean hasGift) {
    this.hasGift = hasGift;
  }

  public void setDiscountRate(Integer discountRate) {
    this.discountRate = discountRate;
  }
}
