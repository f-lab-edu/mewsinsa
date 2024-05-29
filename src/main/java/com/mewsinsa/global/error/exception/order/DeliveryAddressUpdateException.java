package com.mewsinsa.global.error.exception.order;

import com.mewsinsa.global.error.exception.BaseException;
import com.mewsinsa.global.response.DetailedStatus;

public class DeliveryAddressUpdateException extends BaseException {

  public DeliveryAddressUpdateException() {
    super(DetailedStatus.INTERNAL_SERER_ERROR);
  }

}
