package com.mewsinsa.auth;

import com.mewsinsa.auth.kakao.KakaoLoginProperties;
import com.mewsinsa.auth.kakao.service.KakaoLoginService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KakaoLoginPropertiesTest {
  Logger log = LoggerFactory.getLogger(getClass());
  @Autowired
  KakaoLoginProperties kakaoBean;

  @Autowired
  KakaoLoginService kakaoLoginService;


}