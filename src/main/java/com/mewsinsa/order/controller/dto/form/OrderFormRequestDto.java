package com.mewsinsa.order.controller.dto.form;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Size;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderFormRequestDto {
  @Size(min=1)
  private List<OrderFormProductDto> orderedProductList;

  //==Getter==//
  public List<OrderFormProductDto> getOrderedProductList() {
    return orderedProductList;
  }

  //==Setter==//
  public void setOrderedProductList(
      List<OrderFormProductDto> orderedProductList) {
    this.orderedProductList = orderedProductList;
  }
}
