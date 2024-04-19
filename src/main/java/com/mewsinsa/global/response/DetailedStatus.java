package com.mewsinsa.global.response;

public enum DetailedStatus {

  //==Success==//
  OK(200, "S001"),
  CREATED(201, "S002"),
  ACCEPTED(202, "S003"),
  NO_CONTENT(204,"S004"),


  //==Common==//
  // Client
  BAD_REQUEST(400, "BAD REQUEST"),
  UNAUTHORIZED(401, "UNAUTHORIZED"),
  FORBIDDEN(403, "FORBIDDEN"),
  NOT_FOUND(404, "NOT_FOUND"),

  // Server
  INTERNAL_SERER_ERROR(500, "INTERNAL_SERVER_ERROR"),

  //==Auth, Jwt==//
  EXPIRED_TOKEN(401, "A001"), // Auth
  INVALID_TOKEN(401, "A002"),
  NON_EXSISTENT_MEMBER(401, "A003"),
  INCORRECT_PASSWORD(401, "A004"),
  NO_TOKEN(401, "A005"),
  DUPLICATE_MEMBER_INFO(400, "A006"), // 회원가입 시에 기존 회원과 중복되는 정보를 입력

  //==Order==//
  OUT_OF_STOCK(400, "O001"), // Order
  NOT_APPLICAPABLE_COUPN(400, "O002"),
  INVALID_PRODUCT_OPTION_ID(400, "O003"),
  NON_CANCELLABLE_ORDER(400, "O004"),
  NON_EXSISTENT_ORDER(400, "O005"), // 존재하지 않는 주문
  INVALIED_ORDER(400, "O006"), // 주문이 잘못된 경우

  //==Invalid Argument==//
  TYPE_MISMATCH(400, "V001"),
  NOT_VALIDATED(400, "V002"),
  ILLEGAL_ARGUMENT(400, "V003"),

  //==Illegal State==//
  ILLEGAL_STATE(400, "I003");


  //==Attributes=//
  private int statusCode;
  private String code;



  //==Constructor==//

  DetailedStatus(int statusCode, String code) {
    this.statusCode = statusCode;
    this.code = code;
  }

  //==Getter==//
  public int getStatusCode() {
    return statusCode;
  }

  public String getCode() {
    return code;
  }
}
