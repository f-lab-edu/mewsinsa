package com.mewsinsa.promotion.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDateTime;
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
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

  //==Constructor==//
  public Promotion() {
  }

  public Promotion(Long promotionId, String promotionName, Boolean promotionType,
      Integer discountRate, Long discountAmount, LocalDateTime startedAt, LocalDateTime expiredAt) {
    this.promotionId = promotionId;
    this.promotionName = promotionName;
    this.promotionType = promotionType;
    this.discountRate = discountRate;
    this.discountAmount = discountAmount;
    this.startedAt = startedAt;
    this.expiredAt = expiredAt;
  }

  //==Getter==//
  public Long getPromotionId() {
    return promotionId;
  }

  public String getPromotionName() {
    return promotionName;
  }

  public Boolean getPromotionType() {
    return promotionType;
  }

  public Integer getDiscountRate() {
    return discountRate;
  }

  public Long getDiscountAmount() {
    return discountAmount;
  }

  public LocalDateTime getStartedAt() {
    return startedAt;
  }

  public LocalDateTime getExpiredAt() {
    return expiredAt;
  }

  //==Setter==//
  public void setPromotionId(Long promotionId) {
    this.promotionId = promotionId;
  }

  public void setPromotionName(String promotionName) {
    this.promotionName = promotionName;
  }

  public void setPromotionType(Boolean promotionType) {
    this.promotionType = promotionType;
  }

  public void setDiscountRate(Integer discountRate) {
    this.discountRate = discountRate;
  }

  public void setDiscountAmount(Long discountAmount) {
    this.discountAmount = discountAmount;
  }

  public void setStartedAt(LocalDateTime startedAt) {
    this.startedAt = startedAt;
  }

  public void setExpiredAt(LocalDateTime expiredAt) {
    this.expiredAt = expiredAt;
  }
}
