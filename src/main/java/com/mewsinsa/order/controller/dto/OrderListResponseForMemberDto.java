package com.mewsinsa.order.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDateTime;
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderListResponseForMemberDto {
  private Long orderedProductId;
  private Long brandId;
  private String brandName;
  private Long productId;
  private String productName;
  private String productOptionName;
  private LocalDateTime orderedAt;
  private Long orderId;
  private Long price; // 상품 1개 가격
  private Long quantity;

  //==Getter==//

  public Long getOrderedProductId() {
    return orderedProductId;
  }

  public Long getBrandId() {
    return brandId;
  }

  public String getBrandName() {
    return brandName;
  }

  public Long getProductId() {
    return productId;
  }

  public String getProductName() {
    return productName;
  }

  public String getProductOptionName() {
    return productOptionName;
  }

  public LocalDateTime getOrderedAt() {
    return orderedAt;
  }

  public Long getOrderId() {
    return orderId;
  }

  public Long getPrice() {
    return price;
  }

  public Long getQuantity() {
    return quantity;
  }

  //==Setter==//

  public void setOrderedProductId(Long orderedProductId) {
    this.orderedProductId = orderedProductId;
  }

  public void setBrandId(Long brandId) {
    this.brandId = brandId;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public void setProductOptionName(String productOptionName) {
    this.productOptionName = productOptionName;
  }

  public void setOrderedAt(LocalDateTime orderedAt) {
    this.orderedAt = orderedAt;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public void setPrice(Long price) {
    this.price = price;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }
}
