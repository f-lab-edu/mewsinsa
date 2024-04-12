package com.mewsinsa.auth.jwt.interceptor;

import com.mewsinsa.auth.jwt.JwtProvider;
import com.mewsinsa.auth.jwt.exception.InvalidTokenException;
import com.mewsinsa.auth.jwt.exception.NoTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Component
public class AuthInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    // 인증이 필요없음
    if(!isAuthNeeded(handler)) {
      return true;
    }

    String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION);
    if(accessToken == null) {
      throw new NoTokenException("토큰이 없습니다. 로그인 해주세요.");
    }

    String actualToken = accessToken.replaceFirst("Bearer ", "");

    // 유효한 토큰인지 검사
    Jws<Claims> claimsJws;
    try {
      claimsJws = Jwts.parser()
          .verifyWith(JwtProvider.getSigningKey())
          .build()
          .parseSignedClaims(actualToken);

    } catch(ExpiredJwtException ex) {
      throw ex;
    } catch(JwtException ex) {
      throw new InvalidTokenException("잘못된 토큰입니다.");
    }

    return true;
  }

  /**
   * @return 메소드에 어노테이션이 붙어있는지 여부
   */
  private boolean isAuthNeeded(Object handler) {
    // js, css와 같은 view 관련 요청 -> 통과
    if (handler instanceof ResourceHttpRequestHandler) {
      return false;
    }
    HandlerMethod handlerMethod = (HandlerMethod) handler;

    // 메소드에 어노테이션이 붙어있지 않은 경우
    if (handlerMethod.getMethodAnnotation(Auth.class) == null) {
      return false;
    }

    return true;
  }
}
