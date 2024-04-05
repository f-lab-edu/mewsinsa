package com.mewsinsa.product.domain;

public class ProductOption {
  Long productOptionId;
  Long productId;
  String optionName;
  Long stock;

  //==Constructor==//

  public ProductOption(Long id, Long productId, String optionName, Long stock) {
    this.productOptionId = id;
    this.productId = productId;
    this.optionName = optionName;
    this.stock = stock;
  }



  //== Getter ==//

  public Long getProductOptionId() {
    return productOptionId;
  }

  public Long getProductId() {
    return productId;
  }

  public String getOptionName() {
    return optionName;
  }

  public Long getStock() {
    return stock;
  }

}
