package com.mewsinsa.auth.kakao.controller;

import com.mewsinsa.auth.jwt.JwtProvider;
import com.mewsinsa.auth.jwt.controller.dto.AccessTokenResponseDto;
import com.mewsinsa.auth.jwt.domain.JwtToken;
import com.mewsinsa.auth.jwt.service.JwtService;
import com.mewsinsa.auth.kakao.controller.dto.KakaoTokenResponseDto;
import com.mewsinsa.auth.kakao.controller.dto.KakaoUserInfoDto;
import com.mewsinsa.auth.kakao.service.KakaoLoginService;
import com.mewsinsa.global.response.DetailedStatus;
import com.mewsinsa.global.response.SuccessResult;
import com.mewsinsa.global.response.SuccessResult.Builder;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
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

    if(memberId != null) {

      // 토큰을 발급
      JwtToken jwtToken = jwtService.login(memberId);

      Map<String, String> jwtMap = new HashMap<>();
      jwtMap.put(JwtProvider.ACCESS_HEADER_STRING, jwtToken.getAccessToken());
      jwtMap.put(JwtProvider.REFRESH_HEADER_STRING, jwtToken.getRefreshToken());

      SuccessResult result = new Builder(DetailedStatus.CREATED)
          .message("로그인에 성공하여 jwt가 발행되었습니다.")
          .data(jwtMap).build();

      return new ResponseEntity<>(result, HttpStatus.CREATED);

    } else { // 회원 가입으로 리다이렉트
      Map<String, String> memberInfo = new HashMap<>();

      memberInfo.put("name", userInfo.getKakaoAccount().getName());
      memberInfo.put("email", userInfo.getKakaoAccount().getEmail());

      HttpHeaders headers = new HttpHeaders();
      headers.setLocation(URI.create("/auth/sign-in"));
      return new ResponseEntity<>(memberInfo, headers, HttpStatus.MOVED_PERMANENTLY);
    }
  }

}
