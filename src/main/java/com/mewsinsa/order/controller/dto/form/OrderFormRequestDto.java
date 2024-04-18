package com.mewsinsa.order.controller.dto.form;

import jakarta.validation.constraints.Size;
import java.util.List;

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
