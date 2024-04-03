package com.mewsinsa.product.controller.dto;

public class ProductOptionRequestDto {
  private String productOptionName;
  private Long stock;

  //==Getter==//
  public String getProductOptionName() {
    return productOptionName;
  }

  public Long getStock() {
    return stock;
  }

  //==Getter 끝 ==//

  //==Setter==//

  public void setProductOptionName(String productOptionName) {
    this.productOptionName = productOptionName;
  }

  public void setStock(Long stock) {
    this.stock = stock;
  }

  //==Setter 끝==//
}
