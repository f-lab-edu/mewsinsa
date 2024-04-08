package com.mewsinsa.auth.kakao.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mewsinsa.auth.kakao.KakaoLoginProperties;

import com.mewsinsa.auth.kakao.controller.dto.KakaoTokenResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class KakaoLoginService {
  private Logger log = LoggerFactory.getLogger(getClass());

  private final KakaoLoginProperties kakaoLoginProperties;

  public KakaoLoginService(KakaoLoginProperties kakaoLoginProperties) {
    this.kakaoLoginProperties = kakaoLoginProperties;
  }

  public KakaoTokenResponseDto getToken(String code) {
    // 토큰 요청 데이터 -> MultiValueMap
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "authorization_code");
    params.add("client_id", kakaoLoginProperties.getKakaoLoginApiKey());
    params.add("redirect_uri", kakaoLoginProperties.getRedirectUri());
    params.add("code", code);


    // 웹 클라이언트로 요청보내기
    String response = WebClient.create(kakaoLoginProperties.getKakaoAuthBaseUri())
        .post()
        .uri(kakaoLoginProperties.getTokenRequestUri())
        .body(BodyInserters.fromFormData(params))
        .header("Content-type","application/x-www-form-urlencoded;charset=utf-8" ) //요청 헤더
        .retrieve()
        .bodyToMono(String.class)
        .block();

    //json 응답을 객체로 변환
    ObjectMapper objectMapper = new ObjectMapper();
    KakaoTokenResponseDto kakaoToken = null;

    try {
      kakaoToken = objectMapper.readValue(response, KakaoTokenResponseDto.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    return kakaoToken;

  }

}
