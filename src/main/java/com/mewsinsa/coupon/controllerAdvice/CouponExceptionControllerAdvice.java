package com.mewsinsa.coupon.controllerAdvice;

import com.mewsinsa.coupon.exception.FailToIssueCouponException;
import com.mewsinsa.global.response.DetailedStatus;
import com.mewsinsa.global.response.FailureResult;
import com.mewsinsa.order.exception.NotApplicapableCouponException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CouponExceptionControllerAdvice {
  @ExceptionHandler(FailToIssueCouponException.class)
  protected ResponseEntity<FailureResult> handleFailToIssueCouponException(
      FailToIssueCouponException e) {

    final FailureResult result = new FailureResult.Builder()
        .status(e.getStatus())
        .code(e.getStatus().getCode())
        .message(e.getMessage())
        .build();

    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
  }

}
