package com.mewsinsa.auth.jwt.controller;

import com.mewsinsa.auth.jwt.JwtProvider;
import com.mewsinsa.auth.jwt.controller.dto.LoginRequestDto;
import com.mewsinsa.auth.jwt.controller.dto.SignInRequestDto;
import com.mewsinsa.auth.jwt.domain.JwtToken;
import com.mewsinsa.auth.jwt.service.JwtService;
import com.mewsinsa.global.response.DetailedStatus;
import com.mewsinsa.global.response.SuccessResult;
import com.mewsinsa.global.response.SuccessResult.Builder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class JwtController {
  Logger log = LoggerFactory.getLogger(getClass());

  private final JwtService jwtService;

  public JwtController(JwtService jwtService) {
    this.jwtService = jwtService;
  }

  @PostMapping("/logout")
  public ResponseEntity<SuccessResult> logout(@RequestHeader(value=JwtProvider.ACCESS_HEADER_STRING, required=false) String accessToken) {

    if(accessToken == null) {
        throw new IllegalStateException();
    }

    String actualToken = accessToken.replaceFirst("Bearer ", ""); // Bearer 제거
    Jws<Claims> claimsJws;
    try {
      // 토큰 읽어서 memberId 알아내기
      claimsJws = Jwts.parser()
          .verifyWith(JwtProvider.getSigningKey())
          .build()
          .parseSignedClaims(actualToken);
    } catch(JwtException e) {
      throw new IllegalStateException();
    }

    Long memberId = Long.parseLong(claimsJws.getPayload().getSubject());

    jwtService.logout(memberId); // refresh, access token을 DB에서 지워주기

    SuccessResult result = new Builder(DetailedStatus.OK)
        .message("로그아웃 되었습니다.")
        .build();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @PostMapping("/sign-up")
  public ResponseEntity<SuccessResult> signUp(@RequestBody SignInRequestDto signInRequestDto) {
    jwtService.signUp(signInRequestDto);

    SuccessResult result = new Builder(DetailedStatus.CREATED)
        .message("회원 가입에 성공하였습니다.")
        .build();

    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }


  @PostMapping("/reissue-access-token")
  public ResponseEntity<Object> reissueAccessToken(@RequestHeader(value=JwtProvider.REFRESH_HEADER_STRING, required=false) String refreshToken) {
    if(refreshToken == null) { // 메인페이지로
      HttpHeaders headers = new HttpHeaders();
      headers.setLocation(URI.create("/"));
      return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    JwtToken jwtToken = jwtService.reissueAccessToken(refreshToken);

    if(jwtToken == null) { // 메인 페이지로
      HttpHeaders headers = new HttpHeaders();
      headers.setLocation(URI.create("/"));
      return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    SuccessResult result = new Builder(DetailedStatus.CREATED)
        .message("access token이 재발급 되었습니다.")
        .data(jwtToken)
        .build();

    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }


  @PostMapping("/login")
  public ResponseEntity<SuccessResult> login(@RequestBody LoginRequestDto loginRequestDto) {
    JwtToken jwtToken = jwtService.mewsinsaLogin(loginRequestDto.getId(),
        loginRequestDto.getPassword());

    Map<String, String> jwtMap = new HashMap<>();
    jwtMap.put(JwtProvider.ACCESS_HEADER_STRING, jwtToken.getAccessToken());
    jwtMap.put(JwtProvider.REFRESH_HEADER_STRING, jwtToken.getRefreshToken());

    SuccessResult result = new Builder(DetailedStatus.CREATED)
        .message("로그인에 성공하여 jwt가 발행되었습니다.")
        .data(jwtMap).build();

    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }


}
