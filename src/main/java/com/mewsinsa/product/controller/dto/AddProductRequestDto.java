package com.mewsinsa.product.controller.dto;

import static com.mewsinsa.global.config.ConstantConfig.MAX_PRICE_OF_PRODUCT;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.validator.constraints.Range;
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddProductRequestDto {

  @NotEmpty
  private String productName;
  @Range(min=1, max=Long.MAX_VALUE)
  private Long brandId;
  @NotEmpty
  private String category;
  @NotEmpty
  private String subcategory;
  @Range(min=0, max=10000000)
  private Long originalPrice;
  @Size(min=1)
  private List<AddProductOptionRequestDto> productOptionList;



  //==Constructors==//
  public AddProductRequestDto(String productName, Long brandId, String category,
      String subcategory, Long originalPrice,
      List<AddProductOptionRequestDto> productOptionList) {
    this.productName = productName;
    this.brandId = brandId;
    this.category = category;
    this.subcategory = subcategory;
    this.originalPrice = originalPrice;
    this.productOptionList = productOptionList;
  }

  public AddProductRequestDto() {
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

  public List<AddProductOptionRequestDto> getProductOptionList() {
    return productOptionList;
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

  public void setProductOptionList(
      ArrayList<AddProductOptionRequestDto> productOptionList) {
    this.productOptionList = productOptionList;
  }

}
