package com.mewsinsa.product.controller.dto;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AddProductRequestDto {
  private Long productId;
  private String productName;
  private Long brandId;
  private String category;
  private String subcategory;
  private Long originalPrice;
  private ArrayList<ProductOptionRequestDto> productOptionList;



  //==Constructors==//


  public AddProductRequestDto(Long productId, String productName, Long brandId, String category,
      String subcategory, Long originalPrice,
      ArrayList<ProductOptionRequestDto> productOptionList) {
    this.productId = productId;
    this.productName = productName;
    this.brandId = brandId;
    this.category = category;
    this.subcategory = subcategory;
    this.originalPrice = originalPrice;
    this.productOptionList = productOptionList;
  }

  public AddProductRequestDto() {
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

  public ArrayList<ProductOptionRequestDto> getProductOptionList() {
    return productOptionList;
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

  public void setProductOptionList(
      ArrayList<ProductOptionRequestDto> productOptionList) {
    this.productOptionList = productOptionList;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }
  //==Setter 끝 ==//

}
