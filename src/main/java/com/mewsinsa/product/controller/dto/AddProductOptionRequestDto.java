package com.mewsinsa.product.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class AddProductOptionRequestDto {
  @NotEmpty
  private String productOptionName;
  @Range(min=0, max=Long.MAX_VALUE)
  private Long stock;

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
