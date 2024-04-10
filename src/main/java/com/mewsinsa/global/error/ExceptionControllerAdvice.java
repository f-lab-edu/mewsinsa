package com.mewsinsa.global.error;

import com.mewsinsa.global.response.FailureResult;
import com.mewsinsa.global.response.FailureResult.Builder;
import com.mewsinsa.global.response.HttpStatusEnum;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {
  Logger log = LoggerFactory.getLogger(getClass());

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<FailureResult> illegalExceptionHandle(IllegalArgumentException e) {
    FailureResult result = new Builder()
        .message(e.getMessage())
        .status(HttpStatusEnum.BAD_REQUEST)
        .build();

    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
  }


}
