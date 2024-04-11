package com.mewsinsa.global.error;

import com.mewsinsa.auth.jwt.exception.InvalidTokenException;
import com.mewsinsa.global.response.DetailedStatus;
import com.mewsinsa.global.response.FailureResult;
import com.mewsinsa.global.response.FailureResult.Builder;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {
  Logger log = LoggerFactory.getLogger(getClass());

  @ExceptionHandler(InvalidTokenException.class)
  protected ResponseEntity<FailureResult> handleInvalidTokenException (InvalidTokenException e) {
    final FailureResult result = new FailureResult.Builder()
        .status(DetailedStatus.INVALID_TOKEN)
        .code(DetailedStatus.INVALID_TOKEN.getCode())
        .message(e.getMessage())
        .build();

    return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(ExpiredJwtException.class)
  protected ResponseEntity<FailureResult> handleInvalidTokenException (ExpiredJwtException e) {
    final FailureResult result = new FailureResult.Builder()
        .status(DetailedStatus.EXPIRED_TOKEN)
        .code(DetailedStatus.EXPIRED_TOKEN.getCode())
        .message(e.getMessage())
        .build();

    return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(TypeMismatchException.class)
  protected ResponseEntity<FailureResult> handleTypeMismatchException(TypeMismatchException e) {
    final FailureResult result = new FailureResult.Builder()
        .status(DetailedStatus.TYPE_MISMATCH)
        .code(DetailedStatus.TYPE_MISMATCH.getCode())
        .message(e.getMessage())
        .build();

    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(IllegalStateException.class)
  protected ResponseEntity<FailureResult> handleIllegalExceptionHandle(IllegalStateException e) {
    FailureResult result = new Builder()
        .message(e.getMessage())
        .code(DetailedStatus.ILLEGAL_STATE.getCode())
        .status(DetailedStatus.ILLEGAL_STATE)
        .build();

    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
  }



  @ExceptionHandler(IllegalArgumentException.class)
  protected ResponseEntity<FailureResult> handleIllegalExceptionHandle(IllegalArgumentException e) {
    FailureResult result = new Builder()
        .message(e.getMessage())
        .code(DetailedStatus.ILLEGAL_ARGUMENT.getCode())
        .status(DetailedStatus.ILLEGAL_ARGUMENT)
        .build();

    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
  }





}
