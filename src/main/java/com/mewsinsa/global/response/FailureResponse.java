package com.mewsinsa.global.response;

import com.mewsinsa.global.response.SuccessResponse.Builder;

public class FailureResponse {
  private HttpStatusEnum httpStatus;
  private String errorMessage;

  //==Constructor==//
  // builder를 통해서만 생성되도록 private으로 정의
  private FailureResponse(Builder builder) {
    this.httpStatus = builder.httpStatus;
    this.errorMessage = builder.errorMessage;
  }


  //==Builder==//
  public static class Builder {
    HttpStatusEnum httpStatus;
    String errorMessage;

    // httpStatus에 대한 정보는 반드시 필요
    public Builder(HttpStatusEnum httpStatus) {
      this.httpStatus = httpStatus;
    }

    Builder errorMessage(String errorMessage) {
      this.errorMessage = errorMessage;
      return this;
    }

    public FailureResponse build() {
      return new FailureResponse(this);
    }
  }
}
