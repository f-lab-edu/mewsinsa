package com.mewsinsa.auth.kakao.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mewsinsa.auth.jwt.controller.dto.RefreshTokenDto;
import com.mewsinsa.auth.jwt.domain.JwtToken;
import com.mewsinsa.auth.jwt.repository.RefreshTokenRepository;
import com.mewsinsa.auth.kakao.KakaoLoginProperties;

import com.mewsinsa.auth.kakao.controller.dto.KakaoTokenResponseDto;
import com.mewsinsa.auth.kakao.controller.dto.KakaoUserInfoDto;
import com.mewsinsa.member.domain.Member;
import com.mewsinsa.member.repository.MemberRepository;
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
  private final MemberRepository memberRepository;

  public KakaoLoginService(KakaoLoginProperties kakaoLoginProperties,
      MemberRepository memberRepository) {
    this.kakaoLoginProperties = kakaoLoginProperties;
    this.memberRepository = memberRepository;
  }

  /**
   * 2. 토큰 얻기 단계
   * @param code 인증 코드
   * @return 카카오 토큰 정보
   */
  public KakaoTokenResponseDto getToken(String code) {
    // 토큰 요청 데이터 -> MultiValueMap
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "authorization_code");
    params.add("client_id", kakaoLoginProperties.getKakaoLoginApiKey());
    params.add("redirect_uri", kakaoLoginProperties.getRedirectUri());
    params.add("code", code);
//    params.add("client_secret", kakaoLoginProperties.getKakaoClientSecret());


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

  /**
   * 3. 사용자 정보 가져오기 단계
   * @param kakaoToken 카카오 토큰 정보
   * @return 유저의 정보
   */
  public KakaoUserInfoDto getUserInfo(KakaoTokenResponseDto kakaoToken) {

    // 액세스 토큰으로 회원정보를 가져옵니다.
    String response = WebClient.create(kakaoLoginProperties.getKakaoApiBaseUri())
        .post()
        .uri(kakaoLoginProperties.getKakaoApiUserInfoRequestUri())
        .header("Authorization", "Bearer " + kakaoToken.getAccessToken())
        .header("Content-type","application/x-www-form-urlencoded;charset=utf-8" ) //요청 헤더
        .retrieve()
        .bodyToMono(String.class)
        .block();


    //json 응답을 객체로 변환
    ObjectMapper objectMapper = new ObjectMapper();
    KakaoUserInfoDto userInfo = null;

    try {
      userInfo = objectMapper.readValue(response, KakaoUserInfoDto.class);
    } catch (JsonProcessingException e) {
      throw new IllegalStateException(e);
    }

    return userInfo;
  }


  /**
   * @param email 카카오에서 얻은 유저의 이메일
   * @return 회원 여부
   */
  public Long findMemberIdByEmail(String email) {
    Member memberByEmail = memberRepository.findMemberByEmail(email);

    if(memberByEmail != null) { // null이 아니면
      return memberByEmail.getMemberId();
    }

    return null;
  }




}
