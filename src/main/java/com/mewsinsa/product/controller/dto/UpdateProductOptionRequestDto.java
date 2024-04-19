package com.mewsinsa.product.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UpdateProductOptionRequestDto {
  @Range(min=1, max=Long.MAX_VALUE)
  private Long productOptionId;
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

  public Long getProductOptionId() {
    return productOptionId;
  }

  //==Setter==//
  public void setProductOptionName(String productOptionName) {
    this.productOptionName = productOptionName;
  }

  public void setStock(Long stock) {
    this.stock = stock;
  }

  public void setProductOptionId(Long productOptionId) {
    this.productOptionId = productOptionId;
  }
}
