package com.mewsinsa.order.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;
import org.hibernate.validator.constraints.Range;
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderRequestDto {
  @NotEmpty
  private String receiverAddress;
  @NotEmpty
  private String receiverName;

  @Pattern(regexp = "[0-9]{3}-[0-9]{4}-[0-9]{4}")
  private String receiverPhone;
  @Size(min=1)
  private List<OrderedProductDto> orderedProductList;

  @NotNull
  private Long usedPoints;
  @NotNull
  private Boolean usePointsInAdvance;

  //==Getter==//
  public String getReceiverAddress() {
    return receiverAddress;
  }

  public String getReceiverName() {
    return receiverName;
  }

  public String getReceiverPhone() {
    return receiverPhone;
  }

  public List<OrderedProductDto> getOrderedProductList() {
    return orderedProductList;
  }

  public Long getUsedPoints() {
    return usedPoints;
  }

  public Boolean getUsePointsInAdvance() {
    return usePointsInAdvance;
  }

  //==Setter==//
  public void setReceiverAddress(String receiverAddress) {
    this.receiverAddress = receiverAddress;
  }

  public void setReceiverName(String receiverName) {
    this.receiverName = receiverName;
  }

  public void setReceiverPhone(String receiverPhone) {
    this.receiverPhone = receiverPhone;
  }

  public void setOrderedProductList(
      List<OrderedProductDto> orderedProductList) {
    this.orderedProductList = orderedProductList;
  }

  public void setUsedPoints(Long usedPoints) {
    this.usedPoints = usedPoints;
  }

  public void setUsePointsInAdvance(Boolean usePointsInAdvance) {
    this.usePointsInAdvance = usePointsInAdvance;
  }
}
