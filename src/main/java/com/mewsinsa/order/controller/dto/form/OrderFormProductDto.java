package com.mewsinsa.order.controller.dto.form;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mewsinsa.coupon.domain.Coupon;
import jakarta.validation.constraints.Positive;
import java.util.List;
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderFormProductDto {
  @Positive
  private Long productOptionId; // 상품 옵션 Id
  @Positive
  private Long quantity; // 상품 개수


  //==Getter==//
  public Long getProductOptionId() {
    return productOptionId;
  }

  public Long getQuantity() {
    return quantity;
  }

  //==Setter==//
  public void setProductOptionId(Long productOptionId) {
    this.productOptionId = productOptionId;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

}
