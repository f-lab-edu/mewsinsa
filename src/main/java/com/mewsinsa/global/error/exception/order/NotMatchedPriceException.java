package com.mewsinsa.global.error.exception.order;

import com.mewsinsa.global.error.exception.BaseException;
import com.mewsinsa.global.response.DetailedStatus;

public class NotMatchedPriceException extends BaseException {

  public NotMatchedPriceException() {
    super(DetailedStatus.NOT_MATCHED_PRICE);
  }
}
