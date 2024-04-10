package com.mewsinsa.auth.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application-jwt.properties")
public class JwtProperties {
  @Value("${jwt.issuer}")
  private String issuer;
  @Value("${jwt.secret_key}")
  private String secretKey;



}
