package com.mewsinsa.member.domain;

public enum Tier {
  NEWBIE(1,1, 0, "뉴비"),
  ROOKIE(2,1, 1, "루키"),
  MEMBER(3, 2, 1, "멤버"),
  BRONZE(4, 2, 2, "브론즈"),
  SILVER(5, 3, 2, "실버"),
  GOLD(6, 3, 3, "골드"),
  PLATINUM(7, 4,3, "플래티넘"),
  DIAMOND(8,4, 4, "다이아몬드");

  private final int tierId;
  private final int discountRate;
  private final int accumulationRate;
  private final String tierName;


  Tier(int tierId, int discountRate, int accumulationRate, String tierName) {
    this.tierId = tierId;
    this.discountRate = discountRate;
    this.accumulationRate = accumulationRate;
    this.tierName = tierName;
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

  public String getTierName() {
    return tierName;
  }
}
