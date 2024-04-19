package com.mewsinsa.product.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddProductOptionDto {
  private String productOptionName;
  private Long productId;
  private Long stock;

  //==Constructors==//

  public AddProductOptionDto(String productOptionName, Long productId, Long stock) {
    this.productOptionName = productOptionName;
    this.productId = productId;
    this.stock = stock;
  }

  public AddProductOptionDto() {
  }

  //==Constructors 끝==//


  //==Getter==//

  public String getProductOptionName() {
    return productOptionName;
  }

  public Long getProductId() {
    return productId;
  }

  public Long getStock() {
    return stock;
  }

  //==Getter 끝==//


  //==Setter==//

  public void setProductOptionName(String productOptionName) {
    this.productOptionName = productOptionName;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public void setStock(Long stock) {
    this.stock = stock;
  }

  //==Setter 끝==//

}
