package com.mewsinsa.global.error.exception.coupon;

import com.mewsinsa.global.error.exception.BaseException;
import com.mewsinsa.global.response.DetailedStatus;

public class NotIssuancePreiodException extends BaseException {

  public NotIssuancePreiodException() {
    super(DetailedStatus.NOT_ISSUANCE_PERIOD);
  }
}
