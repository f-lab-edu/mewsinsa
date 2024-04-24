package com.mewsinsa.display.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mewsinsa.product.domain.ProductOption;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import java.util.HashMap;
import java.util.List;
import org.hibernate.validator.constraints.Range;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductDetailResponseDto {
  @Range(min=1, max=Long.MAX_VALUE)
  private Long productId;
  @NotEmpty
  private String productName;

  @Range(min=1, max=Long.MAX_VALUE)
  private Long brandId;
  @NotEmpty
  private String brandName;

  @Range(min=0, max=10000000)
  private Long originalPrice;
//  @Size(min=1)
  private List<ProductOption> productOptionList;
  @Range(min=0, max=Long.MAX_VALUE)
  private Long countOfLike;
  @Range(min=0, max=Long.MAX_VALUE)
  private Long countOfClick;
  @NotEmpty
  private String category;
  @NotEmpty
  private String subcategory;
  @Null
  @Range(min=0, max=10000000)
  private Long promotionPrice;
  @Null
  @Range(min=0, max=500000)
  private Long couponDiscount;

  private HashMap<String, Long> tierPrice;

  //==Constructor==//
  private ProductDetailResponseDto(Builder builder) {
    this.productId = builder.productId;
    this.productName = builder.productName;
    this.brandId = builder.brandId;
    this.brandName = builder.brandName;
    this.originalPrice = builder.originalPrice;
    this.productOptionList = builder.productOptionList;
    this.countOfLike = builder.countOfLike;
    this.countOfClick = builder.countOfClick;
    this.category = builder.category;
    this.subcategory = builder.subcategory;
    this.promotionPrice = builder.promotionPrice;
    this.couponDiscount = builder.couponDiscount;
    this.tierPrice = builder.tierPrice;
  }

  //==Getter==//
  public Long getProductId() {
    return productId;
  }

  public String getProductName() {
    return productName;
  }

  public Long getBrandId() {
    return brandId;
  }

  public String getBrandName() {
    return brandName;
  }

  public Long getOriginalPrice() {
    return originalPrice;
  }

  public List<ProductOption> getProductOptionList() {
    return productOptionList;
  }

  public Long getCountOfLike() {
    return countOfLike;
  }

  public Long getCountOfClick() {
    return countOfClick;
  }

  public String getCategory() {
    return category;
  }

  public String getSubcategory() {
    return subcategory;
  }

  public Long getPromotionPrice() {
    return promotionPrice;
  }

  public Long getCouponDiscount() {
    return couponDiscount;
  }

  public HashMap<String, Long> getTierPrice() {
    return tierPrice;
  }

  //==Builder==//
  public static class Builder {
    private Long productId;
    private String productName;
    private Long brandId;
    private String brandName;
    private Long originalPrice;
    private List<ProductOption> productOptionList;
    private Long countOfLike;
    private Long countOfClick;
    private String category;
    private String subcategory;
    private Long promotionPrice;
    private Long couponDiscount;
    private HashMap<String, Long> tierPrice;

    public Builder productId(Long productId) {
      this.productId = productId;
      return this;
    }

    public Builder productName(String productName) {
      this.productName = productName;
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

    public Builder originalPrice(Long originalPrice) {
      this.originalPrice = originalPrice;
      return this;
    }

    public Builder productOptionList(
        List<ProductOption> productOptionList) {
      this.productOptionList = productOptionList;
      return this;
    }

    public Builder countOfLike(Long countOfLike) {
      this.countOfLike = countOfLike;
      return this;
    }

    public Builder countOfClick(Long countOfView) {
      this.countOfClick = countOfView;
      return this;
    }

    public Builder category(String category) {
      this.category = category;
      return this;
    }

    public Builder subcategory(String subcategory) {
      this.subcategory = subcategory;
      return this;
    }

    public Builder promotionPrice(Long promotionPrice) {
      this.promotionPrice = promotionPrice;
      return this;
    }

    public Builder couponDiscount(Long couponDiscount) {
      this.couponDiscount = couponDiscount;
      return this;
    }

    public Builder tierPrice(HashMap<String, Long> tierPrice) {
      this.tierPrice = tierPrice;
      return this;
    }

    public ProductDetailResponseDto build() {
      return new ProductDetailResponseDto(this);
    }

  }


}
