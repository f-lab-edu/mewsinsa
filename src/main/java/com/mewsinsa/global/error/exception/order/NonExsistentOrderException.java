package com.mewsinsa.global.error.exception.order;

import com.mewsinsa.global.error.exception.BaseException;
import com.mewsinsa.global.response.DetailedStatus;

public class NonExsistentOrderException extends BaseException {

  public NonExsistentOrderException() {
    super(DetailedStatus.NON_EXSISTENT_ORDER);
  }
}
