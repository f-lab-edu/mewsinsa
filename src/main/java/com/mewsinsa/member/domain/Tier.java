package com.mewsinsa.member.domain;

public enum Tier {
  NEWBIE(1),
  ROOKIE(1),
  MEMBER(2),
  BRONZE(2),
  SILVER(3),
  GOLD(3),
  PLATINUM(4),
  DIAMOND(4);

  private final int discountRate;


  Tier(int discountRate) {
    this.discountRate = discountRate;
  }

  public int getDiscountRate() {
    return discountRate;
  }
}
