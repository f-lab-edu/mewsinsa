package com.mewsinsa.payment.domain;

public enum PaymentMethod {
  CREDIT_CARD("신용카드"),
  KAKAO_PAY("카카오페이"),
  NAVER_PAY("네이버페이");


  private String koreanMethodName;

  PaymentMethod(String koreanMethodName) {
    this.koreanMethodName = koreanMethodName;
  }

  public String getKoreanMethodName() {
    return koreanMethodName;
  }
}
