package com.mewsinsa.order.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;
import org.hibernate.validator.constraints.Range;

public class OrderRequestDto {
  @NotEmpty
  private String receiverAddress;
  @NotEmpty
  private String receiverName;

  @Pattern(regexp = "[0-9]{3}-[0-9]{4}-[0-9]{4}")
  private String receiverPhone;
  @Size(min=1)
  private List<OrderedProductRequestDto> orderedProductList;

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

  public List<OrderedProductRequestDto> getOrderedProductList() {
    return orderedProductList;
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
      List<OrderedProductRequestDto> orderedProductList) {
    this.orderedProductList = orderedProductList;
  }

}
