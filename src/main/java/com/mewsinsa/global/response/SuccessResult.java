package com.mewsinsa.global.response;

import org.springframework.web.bind.MethodArgumentNotValidException;

public class SuccessResult {
  private DetailedStatus status;
  private String message;
  private Object data;

  //==Constructor==//
  // builder를 통해서만 생성되도록 private으로 정의
  private SuccessResult(Builder builder) {
    this.status = builder.status;
    this.message = builder.message;
    this.data = builder.data;
  }

  //==Getter==//
  public DetailedStatus getHttpStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

  public Object getData() {
    return data;
  }

  //==Builder==//
  public static class Builder {
    DetailedStatus status;
    String message;
    Object data;

    // httpStatus에 대한 정보는 반드시 필요
    public Builder(DetailedStatus status) {
      this.status = status;
    }


    public Builder message(String message) {
      this.message = message;
      return this;
    }

    public Builder data(Object data) {
      this.data = data;
      return this;
    }

    public SuccessResult build() {
      return new SuccessResult(this);
    }
  }


}
