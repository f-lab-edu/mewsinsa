package com.mewsinsa.global.error.exception.auth;

import com.mewsinsa.global.error.exception.BaseException;
import com.mewsinsa.global.response.DetailedStatus;

public class DuplicateMemberInfoException extends BaseException {

  public DuplicateMemberInfoException() {
    super(DetailedStatus.DUPLICATE_MEMBER_INFO);
  }
}
