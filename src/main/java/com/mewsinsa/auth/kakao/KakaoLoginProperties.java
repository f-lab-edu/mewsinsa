package com.mewsinsa.auth.kakao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 카카오 소셜 로그인에 필요한 정보를 담고 있는 Bean입니다.
 */
@Component
@PropertySource("classpath:application-auth.properties")
public class KakaoLoginProperties {
  @Value("${kakao.login.api_key}")
  private String kakaoLoginApiKey;

  @Value("${kakao.login.redirect_uri}")
  private String redirectUri;

  @Value("${kakao.login.uri.code}")
  private String codeReqeustUri;

  @Value("${kakao.login.uri.base}")
  private String kakaoAuthBaseUri;

  @Value("${kakao.login.uri.token}")
  private String tokenRequestUri;

  //==Getter==//
  public String getKakaoLoginApiKey() {
    return kakaoLoginApiKey;
  }

  public String getRedirectUri() {
    return redirectUri;
  }

  public String getCodeReqeustUri() {
    return codeReqeustUri;
  }

  public String getKakaoAuthBaseUri() {
    return kakaoAuthBaseUri;
  }

  public String getTokenRequestUri() {
    return tokenRequestUri;
  }
}
