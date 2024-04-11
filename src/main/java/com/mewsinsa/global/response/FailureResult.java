package com.mewsinsa.global.response;

public class FailureResult {
  private DetailedStatus status;
  private String code;
  private String message;

  //==Constructor==//
  // builder를 통해서만 생성되도록 private으로 정의
  private FailureResult(Builder builder) {
    this.status = builder.status;
    this.code = builder.code;
    this.message = builder.message;
  }

  //==Getter==//
  public DetailedStatus getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

  public String getCode() {
    return code;
  }

  //==Builder==//
  public static class Builder {
    DetailedStatus status;
    String code;
    String message;

    public Builder() {

    }

    public Builder status(DetailedStatus status) {
      this.status = status;
      return this;
    }
    public Builder message(String message) {
      this.message = message;
      return this;
    }

    public Builder code(String code) {
      this.code = code;
      return this;
    }

    public FailureResult build() {
      return new FailureResult(this);
    }
  }
}
