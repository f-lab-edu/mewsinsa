package com.mewsinsa.promotion.controller.dto;

import static com.mewsinsa.global.config.ConstantConfig.MAX_DISCOUNT_AMOUNT;
import static com.mewsinsa.global.config.ConstantConfig.MAX_DISCOUNT_RATE;
import static com.mewsinsa.global.config.ConstantConfig.MIN_DISCOUNT_AMOUNT;
import static com.mewsinsa.global.config.ConstantConfig.MIN_DISCOUNT_RATE;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.validator.constraints.Range;
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddPromotionRequestDto {
  @NotEmpty
  private String promotionName;
  @NotNull
  private Boolean promotionType;

  @Range(min=0, max=100)
  private Integer discountRate;
  @Range(min=0, max=500000)
  private Long discountAmount;
  @NotNull
  private LocalDateTime startedAt;
  @NotNull
  private LocalDateTime expiredAt;
  @NotNull
  private List<@Positive Long> promotionProductList;

  //==Constructor==//
  public AddPromotionRequestDto() {
  }

  public AddPromotionRequestDto(Builder builder) {
    this.promotionName = builder.promotionName;
    this.promotionType = builder.promotionType;
    this.discountRate = builder.discountRate;
    this.discountAmount = builder.discountAmount;
    this.startedAt = builder.startedAt;
    this.expiredAt = builder.expiredAt;
    this.promotionProductList = builder.promotionProductList;
  }



  //==Getter==//
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

  public List<Long> getPromotionProductList() {
    return promotionProductList;
  }

  //==Setter==//
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

  public void setPromotionProductList(List<Long> promotionProductList) {
    this.promotionProductList = promotionProductList;
  }

  //==Builder==//
  public static class Builder {
    private String promotionName;
    private Boolean promotionType;
    private Integer discountRate;
    private Long discountAmount;
    private LocalDateTime startedAt;
    private LocalDateTime expiredAt;
    private List<Long> promotionProductList;

    public void setPromotionProductList(List<Long> promotionProductList) {
      this.promotionProductList = promotionProductList;
    }

    //==Builder==//
    public Builder(String promotionName, LocalDateTime startedAt, LocalDateTime expiredAt,
        List<Long> promotionProductList) {
      this.promotionName = promotionName;
      this.startedAt = startedAt;
      this.expiredAt = expiredAt;
      this.promotionProductList = promotionProductList;
    }


    public Builder promotionType(boolean promotionType) {
      this.promotionType = promotionType;
      return this;
    }

    public Builder discountRate(Integer discountRate) {
      this.discountRate = discountRate;
      return this;
    }

    public Builder discountAmount(Long discountAmount) {
      this.discountAmount = discountAmount;
      return this;
    }

    public AddPromotionRequestDto build() {
      return new AddPromotionRequestDto(this);
    }

  }



}
