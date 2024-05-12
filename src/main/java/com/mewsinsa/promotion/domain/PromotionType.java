package com.mewsinsa.promotion.domain;

public enum PromotionType {
  FIXED_RATE("rate"),
  FIXED_AMOUNT("amount");
  private final String type;

  PromotionType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}
