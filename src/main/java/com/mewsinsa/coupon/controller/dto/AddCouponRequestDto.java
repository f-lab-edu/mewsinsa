package com.mewsinsa.coupon.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDateTime;
import java.util.List;
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddCouponRequestDto {
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
   * 쿠폰이 적용되는 상품의 id
   */
  private List<Long> couponProductList;

  //==Constructor==//
  public AddCouponRequestDto() {
  }

  //==Getter==//

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

  public List<Long> getCouponProductList() {
    return couponProductList;
  }

  //==Setter==//

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

  public void setCouponProductList(List<Long> couponProductList) {
    this.couponProductList = couponProductList;
  }
}
