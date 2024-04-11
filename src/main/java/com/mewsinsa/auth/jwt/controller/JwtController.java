package com.mewsinsa.auth.jwt.controller;

import com.mewsinsa.auth.jwt.JwtProvider;
import com.mewsinsa.auth.jwt.controller.dto.SignInRequestDto;
import com.mewsinsa.auth.jwt.domain.JwtToken;
import com.mewsinsa.auth.jwt.service.JwtService;
import com.mewsinsa.global.response.FailureResult;
import com.mewsinsa.global.response.HttpStatusEnum;
import com.mewsinsa.global.response.SuccessResult;
import com.mewsinsa.global.response.SuccessResult.Builder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  public void logout(@RequestHeader(value=JwtProvider.ACCESS_HEADER_STRING, required=false) String accessToken) {
    String actualToken = accessToken.replaceFirst("Bearer ", ""); // Bearer 제거

    // 토큰 읽어서 memberId 알아내기
    Jws<Claims> claimsJws = Jwts.parser()
        .verifyWith(JwtProvider.getSigningKey())
        .build()
        .parseSignedClaims(actualToken);

    Long memberId = Long.parseLong(claimsJws.getPayload().getSubject());

    jwtService.logout(memberId); // refresh, access token을 DB에서 지워주기
  }

  @PostMapping("/sign-up")
  public void signIn(@RequestBody SignInRequestDto signInRequestDto) {
    jwtService.signUp(signInRequestDto);
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

    SuccessResult result = new Builder(HttpStatusEnum.CREATED)
        .message("access token이 재발급 되었습니다.")
        .data(jwtToken)
        .build();

    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }


}
