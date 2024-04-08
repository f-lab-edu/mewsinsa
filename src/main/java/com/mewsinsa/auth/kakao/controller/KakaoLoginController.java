package com.mewsinsa.auth.kakao.controller;

import com.mewsinsa.auth.kakao.controller.dto.KakaoTokenResponseDto;
import com.mewsinsa.auth.kakao.service.KakaoLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/oauth/kakao")
@RestController
public class KakaoLoginController {
  Logger log = LoggerFactory.getLogger(getClass());

  private final KakaoLoginService kakaoLoginService;

  public KakaoLoginController(KakaoLoginService kakaoLoginService) {
    this.kakaoLoginService = kakaoLoginService;
  }

  @GetMapping("/code")
  public void getKakaoAcceessToken(@RequestParam(value = "code", required = false) String code,
      @RequestParam(value = "error", required = false) String error,
      @RequestParam(value = "error_description", required = false) String error_description,
      @RequestParam(value = "state", required = false) String state) {

    KakaoTokenResponseDto kakaoToken = kakaoLoginService.getToken(code);



  }

}
