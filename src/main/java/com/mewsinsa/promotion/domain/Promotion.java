package com.mewsinsa.promotion.domain;

import java.time.LocalDateTime;

public class Promotion {
  private Long promotionId;
  private String promotionName;
  /**
   * true: 정률할인
   * false: 정액할인
   */
  private Boolean promotionType;

  /**
   * 할인율. promotionType이 false일 경우 null가능
   */
  private Integer discountRate;
  /**
   * 할인금액. promotionType이 true일 경우 null가능
   */
  private Long discountAmount;


  /**
   * 프로모션 시작 시점
   */
  private LocalDateTime startedAt;

  /**
   * 프로모션 종료 시점
   */
  private LocalDateTime expiredAt;


}
