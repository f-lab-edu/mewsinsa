package com.mewsinsa.global.error.exception.order;

import com.mewsinsa.global.error.exception.BaseException;
import com.mewsinsa.global.response.DetailedStatus;

public class InvalidProductOptionException extends BaseException {

  public InvalidProductOptionException() {
    super(DetailedStatus.INVALID_PRODUCT_OPTION_ID);
  }
}
