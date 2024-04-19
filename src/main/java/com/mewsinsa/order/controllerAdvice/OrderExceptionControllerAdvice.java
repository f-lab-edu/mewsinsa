package com.mewsinsa.order.controllerAdvice;

import com.mewsinsa.global.response.DetailedStatus;
import com.mewsinsa.global.response.FailureResult;
import com.mewsinsa.order.exception.InvalidProductOptionException;
import com.mewsinsa.order.exception.NonExsistentOrderException;
import com.mewsinsa.order.exception.NotApplicapableCouponException;
import com.mewsinsa.order.exception.OrderCancellationException;
import com.mewsinsa.order.exception.OrderException;
import com.mewsinsa.order.exception.OutOfStockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderExceptionControllerAdvice {
  @ExceptionHandler(OutOfStockException.class)
  protected ResponseEntity<FailureResult> handleOutOfStockException(OutOfStockException e) {

    final FailureResult result = new FailureResult.Builder()
        .status(DetailedStatus.OUT_OF_STOCK)
        .code(DetailedStatus.OUT_OF_STOCK.getCode())
        .message(e.getMessage())
        .build();

    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NotApplicapableCouponException.class)
  protected ResponseEntity<FailureResult> handleInvalidCouponException(
      NotApplicapableCouponException e) {

    final FailureResult result = new FailureResult.Builder()
        .status(DetailedStatus.NOT_APPLICAPABLE_COUPN)
        .code(DetailedStatus.NOT_APPLICAPABLE_COUPN.getCode())
        .message(e.getMessage() + " / productId: " + e.getProductId() + " couponId: " + e.getCouponId())
        .build();

    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidProductOptionException.class)
  protected ResponseEntity<FailureResult> handleInvalidProductOptionException(InvalidProductOptionException e) {
    final FailureResult result = new FailureResult.Builder()
        .status(DetailedStatus.INVALID_PRODUCT_OPTION_ID)
        .code(DetailedStatus.INVALID_PRODUCT_OPTION_ID.getCode())
        .message(e.getMessage() + " / productId: " + e.getProductOptionId())
        .build();

    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(OrderCancellationException.class)
  protected ResponseEntity<FailureResult> handleOrderCancellationException(OrderCancellationException e) {
    final FailureResult result = new FailureResult.Builder()
        .status(DetailedStatus.NON_CANCELLABLE_ORDER)
        .code(DetailedStatus.NON_CANCELLABLE_ORDER.getCode())
        .message(e.getMessage() + " / 현재 상태: " + e.getStatus())
        .build();

    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NonExsistentOrderException.class)
  protected ResponseEntity<FailureResult> handleOrderException(NonExsistentOrderException e) {
    final FailureResult result = new FailureResult.Builder()
        .status(DetailedStatus.NON_CANCELLABLE_ORDER)
        .code(DetailedStatus.NON_CANCELLABLE_ORDER.getCode())
        .message(e.getMessage())
        .build();

    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(OrderException.class)
  protected ResponseEntity<FailureResult> handleOrderException(OrderException e) {

    final FailureResult result = new FailureResult.Builder()
        .status(DetailedStatus.INVALIED_ORDER)
        .code(DetailedStatus.INVALIED_ORDER.getCode())
        .message(e.getMessage())
        .build();

    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
  }

}
