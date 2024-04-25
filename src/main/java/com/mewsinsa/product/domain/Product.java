package com.mewsinsa.product.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Product {
  private Long productId;
  private String productName;
  private Long brandId;
  private String category;
  private String subcategory;
  private Long price;
  private Long like;
  private Long click;

  //==Constructor ==//

  public Product(Long productId, String productName, Long brandId, String category, String subcategory,
      Long price, Long like, Long click) {
    this.productId = productId;
    this.productName = productName;
    this.brandId = brandId;
    this.category = category;
    this.subcategory = subcategory;
    this.price = price;
    this.like = like;
    this.click = click;
  }

  public Product(String productName, Long brandId, String category, String subcategory,
      Long price, Long like, Long click) {
    this.productName = productName;
    this.brandId = brandId;
    this.category = category;
    this.subcategory = subcategory;
    this.price = price;
    this.like = like;
    this.click = click;
  }


  //== Getter ==//
  public Long getProductId() {
    return productId;
  }

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

  public Long getPrice() {
    return price;
  }

  public Long getLike() {
    return like;
  }

  public Long getClick() {
    return click;
  }





}