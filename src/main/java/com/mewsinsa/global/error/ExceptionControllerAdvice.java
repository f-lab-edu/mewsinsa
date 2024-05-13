package com.mewsinsa.global.error;

import com.mewsinsa.auth.jwt.exception.DuplicateMemberInfoException;
import com.mewsinsa.auth.jwt.exception.IncorrectPasswordException;
import com.mewsinsa.auth.jwt.exception.InvalidTokenException;
import com.mewsinsa.auth.jwt.exception.NoTokenException;
import com.mewsinsa.auth.jwt.exception.NonExistentMemberException;
import com.mewsinsa.global.response.DetailedStatus;
import com.mewsinsa.global.response.ResponseResult;
import com.mewsinsa.global.response.ResponseResult.Builder;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {
  Logger log = LoggerFactory.getLogger(getClass());
  @ExceptionHandler(NonExistentMemberException.class)
  protected ResponseEntity<ResponseResult> handleNonExsistentMemberException(NonExistentMemberException e) {

      final ResponseResult result = new ResponseResult.Builder()
          .status(DetailedStatus.NON_EXSISTENT_MEMBER)
          .code(DetailedStatus.NON_EXSISTENT_MEMBER.getCode())
          .message(e.getMessage())
          .build();

      return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(IncorrectPasswordException.class)
  protected ResponseEntity<ResponseResult> handleIncorrectPasswordException(IncorrectPasswordException e) {

    final ResponseResult result = new ResponseResult.Builder()
        .status(DetailedStatus.INCORRECT_PASSWORD)
        .code(DetailedStatus.INCORRECT_PASSWORD.getCode())
        .message(e.getMessage())
        .build();

    return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(NoTokenException.class)
  protected ResponseEntity<ResponseResult> handleNoTokenException (NoTokenException e) {
    final ResponseResult result = new ResponseResult.Builder()
        .status(DetailedStatus.NO_TOKEN)
        .code(DetailedStatus.NO_TOKEN.getCode())
        .message(e.getMessage())
        .build();

    return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(InvalidTokenException.class)
  protected ResponseEntity<ResponseResult> handleInvalidTokenException (InvalidTokenException e) {
    final ResponseResult result = new ResponseResult.Builder()
        .status(DetailedStatus.INVALID_TOKEN)
        .code(DetailedStatus.INVALID_TOKEN.getCode())
        .message(e.getMessage())
        .build();

    return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(ExpiredJwtException.class)
  protected ResponseEntity<ResponseResult> handleInvalidTokenException (ExpiredJwtException e) {
    final ResponseResult result = new ResponseResult.Builder()
        .status(DetailedStatus.EXPIRED_TOKEN)
        .code(DetailedStatus.EXPIRED_TOKEN.getCode())
        .message(e.getMessage())
        .build();

    return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
  }
  @ExceptionHandler(DuplicateMemberInfoException.class)
  protected ResponseEntity<ResponseResult> handleOrderException(DuplicateMemberInfoException e) {
    final ResponseResult result = new ResponseResult.Builder()
        .status(DetailedStatus.DUPLICATE_MEMBER_INFO)
        .code(DetailedStatus.DUPLICATE_MEMBER_INFO.getCode())
        .message(e.getMessage())
        .build();

    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(TypeMismatchException.class)
  protected ResponseEntity<ResponseResult> handleTypeMismatchException(TypeMismatchException e) {
    final ResponseResult result = new ResponseResult.Builder()
        .status(DetailedStatus.TYPE_MISMATCH)
        .code(DetailedStatus.TYPE_MISMATCH.getCode())
        .message(e.getMessage())
        .build();

    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(IllegalStateException.class)
  protected ResponseEntity<ResponseResult> handleIllegalExceptionHandle(IllegalStateException e) {
    ResponseResult result = new Builder()
        .message(e.getMessage())
        .code(DetailedStatus.ILLEGAL_STATE.getCode())
        .status(DetailedStatus.ILLEGAL_STATE)
        .build();

    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
  }



  @ExceptionHandler(IllegalArgumentException.class)
  protected ResponseEntity<ResponseResult> handleIllegalExceptionHandle(IllegalArgumentException e) {
    ResponseResult result = new Builder()
        .message(e.getMessage())
        .code(DetailedStatus.ILLEGAL_ARGUMENT.getCode())
        .status(DetailedStatus.ILLEGAL_ARGUMENT)
        .build();

    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
  }





}
