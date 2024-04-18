package com.mewsinsa.member.domain;

public enum Tier {
  NEWBIE(1,1, 0),
  ROOKIE(2,1, 1),
  MEMBER(3, 2, 1),
  BRONZE(4, 2, 2),
  SILVER(5, 3, 2),
  GOLD(6, 3, 3),
  PLATINUM(7, 4,3 ),
  DIAMOND(8,4, 4);

  private final int tierId;
  private final int discountRate;
  private final int accumulationRate;


  Tier(int tierId, int discountRate, int accumulationRate) {
    this.tierId = tierId;
    this.discountRate = discountRate;
    this.accumulationRate = accumulationRate;
  }

  public int getTierId() {
    return tierId;
  }
  public int getDiscountRate() {
    return discountRate;
  }
  public int getAccumulationRate() {
    return accumulationRate;
  }

}
