package com.mewsinsa.coupon.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDateTime;
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Coupon {
  private Long couponId;
  private String couponName;
  /**
   * true: 정률할인
   * false: 정액할인
   */
  private Boolean couponType;

  /**
   * 할인율. couponType이 false일 경우 null가능
   */
  private Integer discountRate;
  /**
   * 할인금액. couponType이 true일 경우 null가능
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

  /**
   * 쿠폰 남은 수량
   */
  private Long remaining;


  //==Constructor==//
  public Coupon() {
  }

  public Coupon(Long couponId, String couponName, Boolean couponType, Integer discountRate,
      Long discountAmount, LocalDateTime startedAt, LocalDateTime expiredAt, Long remaining) {
    this.couponId = couponId;
    this.couponName = couponName;
    this.couponType = couponType;
    this.discountRate = discountRate;
    this.discountAmount = discountAmount;
    this.startedAt = startedAt;
    this.expiredAt = expiredAt;
    this.remaining = remaining;
  }

  //==Getter==//
  public Long getCouponId() {
    return couponId;
  }

  public String getCouponName() {
    return couponName;
  }

  public Boolean getCouponType() {
    return couponType;
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

  public Long getRemaining() {
    return remaining;
  }

  //==Setter==//
  public void setCouponId(Long couponId) {
    this.couponId = couponId;
  }

  public void setCouponName(String couponName) {
    this.couponName = couponName;
  }

  public void setCouponType(Boolean couponType) {
    this.couponType = couponType;
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

  public void setRemaining(Long remaining) {
    this.remaining = remaining;
  }
}
