package com.mewsinsa.global.error.exception.auth;

import com.mewsinsa.global.error.exception.BaseException;
import com.mewsinsa.global.response.DetailedStatus;

public class NonExistentMemberException extends BaseException {

  public NonExistentMemberException() {
    super(DetailedStatus.NON_EXSISTENT_MEMBER);
  }
}
