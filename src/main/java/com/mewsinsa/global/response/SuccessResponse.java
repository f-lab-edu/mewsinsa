package com.mewsinsa.global.response;

public class SuccessResponse {
  private HttpStatusEnum httpStatus;
  private String message;
  private Object data;

  //==Constructor==//
  // builder를 통해서만 생성되도록 private으로 정의
  private SuccessResponse(Builder builder) {
    this.httpStatus = builder.httpStatus;
    this.message = builder.message;
    this.data = builder.data;
  }

  //==Getter==//
  public HttpStatusEnum getHttpStatus() {
    return httpStatus;
  }

  public String getMessage() {
    return message;
  }

  public Object getData() {
    return data;
  }

  //==Builder==//
  public static class Builder {
    HttpStatusEnum httpStatus;
    String message;
    Object data;

    // httpStatus에 대한 정보는 반드시 필요
    public Builder(HttpStatusEnum httpStatus) {
      this.httpStatus = httpStatus;
    }

    public Builder message(String message) {
      this.message = message;
      return this;
    }

    public Builder data(Object data) {
      this.data = data;
      return this;
    }

    public SuccessResponse build() {
      return new SuccessResponse(this);
    }
  }


}
