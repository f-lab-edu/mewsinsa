package com.mewsinsa.global.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ConstantConfig {
  //==Coupon==//
  public static final boolean FIXED_AMOUNT = false;
  public static final boolean FIXED_RATE = true;

  //==Product==//
  public static final Long MAX_PRICE_OF_PRODUCT = 10000000L;
  public static final Long MIN_PRICE_OF_PRODUCT = 0L;
  public static final Integer MAX_DISCOUNT_RATE = 100;
  public static final Integer MIN_DISCOUNT_RATE = 0;

  // 최대 정액할인금액 50만원
  public static final Integer MAX_DISCOUNT_AMOUNT = 500000;
  public static final Integer MIN_DISCOUNT_AMOUNT = 0;

}
