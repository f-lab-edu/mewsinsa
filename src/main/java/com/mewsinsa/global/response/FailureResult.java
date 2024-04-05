package com.mewsinsa.global.response;

public class FailureResult {
  private HttpStatusEnum status;
  private String message;

  //==Constructor==//
  // builder를 통해서만 생성되도록 private으로 정의
  private FailureResult(Builder builder) {
    this.status = builder.status;
    this.message = builder.message;
  }

  //==Getter==//
  public HttpStatusEnum getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

  //==Builder==//
  public static class Builder {
    HttpStatusEnum status;
    String message;

    public Builder() {

    }

    public Builder status(HttpStatusEnum status) {
      this.status = status;
      return this;
    }
    public Builder message(String message) {
      this.message = message;
      return this;
    }

    public FailureResult build() {
      return new FailureResult(this);
    }
  }
}
