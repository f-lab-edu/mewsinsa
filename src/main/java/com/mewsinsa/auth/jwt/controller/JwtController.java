package com.mewsinsa.auth.jwt.controller;

import com.mewsinsa.auth.jwt.JwtProvider;
import com.mewsinsa.auth.jwt.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class JwtController {
  private final JwtProvider jwtProvider;
  private final JwtService jwtService;

  public JwtController(JwtProvider jwtProvider, JwtService jwtService) {
    this.jwtProvider = jwtProvider;
    this.jwtService = jwtService;
  }
//  public void logout(@RequestHeader(JwtProvider.ACCESS_HEADER_STRING) String accessToken) {
  @PostMapping("/logout")
  public void logout(@RequestBody String accessToken) {
    String actualToken = accessToken.replaceFirst("Bearer ", ""); // Bearer 제거

    // 토큰 읽어서 memberId 알아내기
    Jws<Claims> claimsJws = Jwts.parser()
        .verifyWith(jwtProvider.getSigningKey())
        .build()
        .parseSignedClaims(actualToken);

    Long memberId = Long.parseLong(claimsJws.getPayload().getSubject());

    jwtService.logout(memberId, accessToken); // logout DB에 저장하기
  }

}
