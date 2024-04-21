package com.mewsinsa.coupon.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class IssuedCoupon {
  private Long issuedCouponId;
  private Boolean isUsed;
  private LocalDateTime issuedAt;
  private LocalDateTime usedAt;
  private Long couponId;
  private Long memberId;

  //==Getter==//
  public Long getIssuedCouponId() {
    return issuedCouponId;
  }

  public Boolean getUsed() {
    return isUsed;
  }

  public LocalDateTime getIssuedAt() {
    return issuedAt;
  }

  public LocalDateTime getUsedAt() {
    return usedAt;
  }

  public Long getCouponId() {
    return couponId;
  }

  public Long getMemberId() {
    return memberId;
  }

  //==Setter==//
  public void setIssuedCouponId(Long issuedCouponId) {
    this.issuedCouponId = issuedCouponId;
  }

  public void setUsed(Boolean used) {
    isUsed = used;
  }

  public void setIssuedAt(LocalDateTime issuedAt) {
    this.issuedAt = issuedAt;
  }

  public void setUsedAt(LocalDateTime usedAt) {
    this.usedAt = usedAt;
  }

  public void setCouponId(Long couponId) {
    this.couponId = couponId;
  }

  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }
}
