package com.mewsinsa.auth.kakao.controller;

import com.mewsinsa.auth.jwt.controller.dto.AccessTokenResponseDto;
import com.mewsinsa.auth.jwt.domain.JwtToken;
import com.mewsinsa.auth.jwt.service.JwtService;
import com.mewsinsa.auth.kakao.controller.dto.KakaoTokenResponseDto;
import com.mewsinsa.auth.kakao.controller.dto.KakaoUserInfoDto;
import com.mewsinsa.auth.kakao.service.KakaoLoginService;
import com.mewsinsa.global.response.HttpStatusEnum;
import com.mewsinsa.global.response.SuccessResult;
import com.mewsinsa.global.response.SuccessResult.Builder;
import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/oauth/kakao")
@RestController
public class KakaoLoginController {
  Logger log = LoggerFactory.getLogger(getClass());

  private final KakaoLoginService kakaoLoginService;
  private final JwtService jwtService;

  public KakaoLoginController(KakaoLoginService kakaoLoginService, JwtService jwtService) {
    this.kakaoLoginService = kakaoLoginService;
    this.jwtService = jwtService;
  }

  @GetMapping("/code")
  public ResponseEntity<Object> kakaoLogin(@RequestParam(value = "code", required = false) String code,
      @RequestParam(value = "error", required = false) String error,
      @RequestParam(value = "error_description", required = false) String error_description,
      @RequestParam(value = "state", required = false) String state) {

    KakaoTokenResponseDto kakaoToken = kakaoLoginService.getToken(code);

    // 액세스 토큰으로 회원 정보 얻어오기
    KakaoUserInfoDto userInfo = kakaoLoginService.getUserInfo(kakaoToken);

    // 회원 정보로 회원인지 판단
    Long memberId = kakaoLoginService.findMemberIdByEmail(userInfo.getKakaoAccount().getEmail());

    if(memberId != null) { // 로그인 처리 -> 1. 리프레시 토큰이 존재하면 액세스 토큰 발행, 2. 없으면 새 토큰 발행. 리프레시는 DB에 저장

      // 토큰을 발급
      JwtToken jwtToken = jwtService.login(memberId);


      SuccessResult result = new Builder(HttpStatusEnum.CREATED)
          .message("로그인에 성공하여 jwt가 발행되었습니다.")
          .data(new AccessTokenResponseDto(jwtToken.getAccessToken())).build();

      return new ResponseEntity<>(result, HttpStatus.CREATED);

    } else { // 회원 가입으로 리다이렉트
      HttpHeaders headers = new HttpHeaders();
      headers.setLocation(URI.create("/auth/sign-in"));
      return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
  }

}
