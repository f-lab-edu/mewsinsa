package com.mewsinsa.product.controller.dao;

public class AddProductOptionDAO {
  private String productOptionName;
  private Long productId;
  private Long stock;

  //==Constructors==//

  public AddProductOptionDAO(String productOptionName, Long productId, Long stock) {
    this.productOptionName = productOptionName;
    this.productId = productId;
    this.stock = stock;
  }

  public AddProductOptionDAO() {
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
