package com.mewsinsa.global.response;

public enum HttpStatusEnum {

  //==Success==//
  OK(200, "OK"),
  CREATED(201, "CREATED"),
  ACCEPTED(202, "ACCEPTED"),
  NO_CONTENT(204, "NO_CONTENT"),

  //==Client Error==//
  BAD_REQUEST(400, "BAD REQUEST"),
  UNAUTHORIZED(401, "UNAUTHORIZED"),
  FORBIDDEN(403, "FORBIDDEN"),
  NOT_FOUND(404, "NOT_FOUND"),

  //==Server Error==//
  INTERNAL_SERER_ERROR(500, "INTERNAL_SERVER_ERROR");


  //==Attributes=//
  private int statusCode;
  private String code;


  //==Constructor==//

  HttpStatusEnum(int statusCode, String code) {
    this.statusCode = statusCode;
    this.code = code;
  }
}
