package com.mewsinsa.product.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.validator.constraints.Range;
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UpdateProductRequestDto {
  @NotNull
  private Long productId;
  @NotEmpty
  private String productName;
  @NotNull
  private Long brandId;
  @NotEmpty
  private String category;
  @NotEmpty
  private String subcategory;
  @Range(min=0, max=10000000)
  private Long originalPrice;


  //==Constructors==//
  public UpdateProductRequestDto(Long productId, String productName, Long brandId, String category,
      String subcategory, Long originalPrice) {
    this.productId = productId;
    this.productName = productName;
    this.brandId = brandId;
    this.category = category;
    this.subcategory = subcategory;
    this.originalPrice = originalPrice;
  }

  public UpdateProductRequestDto() {
  }



  //==Getter==//
  public String getProductName() {
    return productName;
  }

  public Long getBrandId() {
    return brandId;
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


  public Long getProductId() {
    return productId;
  }


  //==Setter==//
  public void setProductName(String productName) {
    this.productName = productName;
  }

  public void setBrandId(Long brandId) {
    this.brandId = brandId;
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

  public void setProductId(Long productId) {
    this.productId = productId;
  }

}
