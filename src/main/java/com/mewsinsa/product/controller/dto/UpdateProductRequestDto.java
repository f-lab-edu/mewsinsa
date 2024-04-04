package com.mewsinsa.product.controller.dto;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class UpdateProductRequestDto {
  private Long productId;
  private String productName;
  private Long brandId;
  private String category;
  private String subcategory;
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

  //==Constructors 끝==//


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
//==Getter 끝==//


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
  //==Setter 끝 ==//

}
