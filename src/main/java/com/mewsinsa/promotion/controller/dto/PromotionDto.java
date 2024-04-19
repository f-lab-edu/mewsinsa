package com.mewsinsa.promotion.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 프로모션 조회를 위한 DTO입니다.
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PromotionDto {

  private Long promotionId;
  private String promotionName;
  private Boolean promotionType;

  private Integer discountRate;
  private Long discountAmount;

  private LocalDateTime startedAt;
  private LocalDateTime expiredAt;

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
