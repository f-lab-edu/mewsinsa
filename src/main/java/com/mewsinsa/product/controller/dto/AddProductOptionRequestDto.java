package com.mewsinsa.product.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddProductOptionRequestDto {
  @NotEmpty
  private String productOptionName;
  @Range(min=0, max=Long.MAX_VALUE)
  private Long stock;

  //==Constructor==//
  public AddProductOptionRequestDto(String productOptionName, Long stock) {
    this.productOptionName = productOptionName;
    this.stock = stock;
  }

  public AddProductOptionRequestDto() {
  }


  //==Getter==//
  public String getProductOptionName() {
    return productOptionName;
  }

  public Long getStock() {
    return stock;
  }


  //==Setter==//
  public void setProductOptionName(String productOptionName) {
    this.productOptionName = productOptionName;
  }

  public void setStock(Long stock) {
    this.stock = stock;
  }

}
