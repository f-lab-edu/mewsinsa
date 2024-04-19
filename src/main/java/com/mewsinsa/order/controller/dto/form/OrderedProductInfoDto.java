package com.mewsinsa.order.controller.dto.form;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mewsinsa.coupon.domain.Coupon;
import java.util.List;
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderedProductInfoDto {
  private Long productId;
  private String productName;
  private Long productOptionId;
  private String productOptionName;
  private Long brandId;
  private String brandName;
  private Long quantity;
  private Long points;
  private Long discountAmount;
  private Long unitOriginalPrice;
  private Long unitPromotionPrice;
  private Long unitTierDiscountAmount;
  private Long pieceOriginalPrice;
  private Long piecePromotionPrice;
  private Long pieceTierDiscountAmount;

  private List<Coupon> couponList;

  //==Constructor==//

  public OrderedProductInfoDto() {
  }

  public OrderedProductInfoDto(Builder builder) {
    this.productId = builder.productId;
    this.productName = builder.productName;
    this.productOptionId = builder.productOptionId;
    this.productOptionName = builder.productOptionName;
    this.brandId = builder.brandId;
    this.brandName = builder.brandName;
    this.quantity = builder.quantity;
    this.points = builder.points;
    this.discountAmount = builder.discountAmount;
    this.unitOriginalPrice = builder.unitOriginalPrice;
    this.unitPromotionPrice = builder.unitPromotionPrice;
    this.pieceOriginalPrice = builder.pieceOriginalPrice;
    this.piecePromotionPrice = builder.piecePromotionPrice;
    this.couponList = builder.couponList;
    this.unitTierDiscountAmount = builder.unitTierDiscountAmount;
    this.pieceTierDiscountAmount = builder.pieceTierDiscountAmount;
  }

  //==Getter==//
  public Long getProductId() {
    return productId;
  }

  public String getProductName() {
    return productName;
  }

  public Long getProductOptionId() {
    return productOptionId;
  }

  public String getProductOptionName() {
    return productOptionName;
  }

  public Long getBrandId() {
    return brandId;
  }

  public String getBrandName() {
    return brandName;
  }

  public Long getQuantity() {
    return quantity;
  }

  public Long getPoints() {
    return points;
  }

  public Long getDiscountAmount() {
    return discountAmount;
  }

  public Long getUnitOriginalPrice() {
    return unitOriginalPrice;
  }

  public Long getUnitPromotionPrice() {
    return unitPromotionPrice;
  }

  public Long getPieceOriginalPrice() {
    return pieceOriginalPrice;
  }

  public Long getPiecePromotionPrice() {
    return piecePromotionPrice;
  }

  public Long getUnitTierDiscountAmount() {
    return unitTierDiscountAmount;
  }

  public Long getPieceTierDiscountAmount() {
    return pieceTierDiscountAmount;
  }

  public List<Coupon> getCouponList() {
    return couponList;
  }

  //==Setter==//
  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public void setProductOptionId(Long productOptionId) {
    this.productOptionId = productOptionId;
  }

  public void setProductOptionName(String productOptionName) {
    this.productOptionName = productOptionName;
  }

  public void setBrandId(Long brandId) {
    this.brandId = brandId;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  public void setPoints(Long points) {
    this.points = points;
  }

  public void setDiscountAmount(Long discountAmount) {
    this.discountAmount = discountAmount;
  }

  public void setUnitOriginalPrice(Long unitOriginalPrice) {
    this.unitOriginalPrice = unitOriginalPrice;
  }

  public void setUnitPromotionPrice(Long unitPromotionPrice) {
    this.unitPromotionPrice = unitPromotionPrice;
  }

  public void setPieceOriginalPrice(Long pieceOriginalPrice) {
    this.pieceOriginalPrice = pieceOriginalPrice;
  }

  public void setPiecePromotionPrice(Long piecePromotionPrice) {
    this.piecePromotionPrice = piecePromotionPrice;
  }

  public void setUnitTierDiscountAmount(Long unitTierDiscountAmount) {
    this.unitTierDiscountAmount = unitTierDiscountAmount;
  }

  public void setPieceTierDiscountAmount(Long pieceTierDiscountAmount) {
    this.pieceTierDiscountAmount = pieceTierDiscountAmount;
  }

  public void setCouponList(List<Coupon> couponList) {
    this.couponList = couponList;
  }

  //==Builder==//
  public static class Builder {
    private Long productId;
    private String productName;
    private Long productOptionId;
    private String productOptionName;
    private Long brandId;
    private String brandName;
    private Long quantity;
    private Long points;
    private Long discountAmount;
    private Long unitOriginalPrice;
    private Long unitPromotionPrice;
    private Long unitTierDiscountAmount;
    private Long pieceOriginalPrice;
    private Long piecePromotionPrice;
    private Long pieceTierDiscountAmount;
    private List<Coupon> couponList;


    public Builder productId(Long productId) {
      this.productId = productId;
      return this;
    }

    public Builder productName(String productName) {
      this.productName = productName;
      return this;
    }

    public Builder productOptionId(Long productOptionId) {
      this.productOptionId = productOptionId;
      return this;
    }

    public Builder productOptionName(String productOptionName) {
      this.productOptionName = productOptionName;
      return this;
    }

    public Builder brandId(Long brandId) {
      this.brandId = brandId;
      return this;
    }

    public Builder brandName(String brandName) {
      this.brandName = brandName;
      return this;
    }

    public Builder quantity(Long quantity) {
      this.quantity = quantity;
      return this;
    }

    public Builder points(Long points) {
      this.points = points;
      return this;
    }

    public Builder discountAmount(Long discountAmount) {
      this.discountAmount = discountAmount;
      return this;
    }

    public Builder unitOriginalPrice(Long unitOriginalPrice) {
      this.unitOriginalPrice = unitOriginalPrice;
      return this;
    }

    public Builder unitPromotionPrice(Long unitPromotionPrice) {
      this.unitPromotionPrice = unitPromotionPrice;
      return this;
    }

    public Builder pieceOriginalPrice(Long pieceOriginalPrice) {
      this.pieceOriginalPrice = pieceOriginalPrice;
      return this;
    }

    public Builder piecePromotionPrice(Long piecePromotionPrice) {
      this.piecePromotionPrice = piecePromotionPrice;
      return this;
    }

    public Builder couponList(List<Coupon> couponList) {
      this.couponList = couponList;
      return this;
    }

    public Builder unitTierDiscountAmount(Long unitTierDiscountAmount) {
      this.unitTierDiscountAmount = unitTierDiscountAmount;
      return this;
    }

    public Builder pieceTierDiscountAmount(Long pieceTierDiscountAmount) {
      this.pieceTierDiscountAmount = pieceTierDiscountAmount;
      return this;
    }

    public OrderedProductInfoDto build() {
      return new OrderedProductInfoDto(this);
    }
  }
}
