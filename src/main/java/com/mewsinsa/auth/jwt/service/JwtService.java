package com.mewsinsa.auth.jwt.service;

import com.mewsinsa.auth.jwt.JwtProvider;
import com.mewsinsa.auth.jwt.controller.dto.LogoutTokenDto;
import com.mewsinsa.auth.jwt.controller.dto.RefreshTokenDto;
import com.mewsinsa.auth.jwt.domain.JwtToken;
import com.mewsinsa.auth.jwt.repository.LogoutTokenRepository;
import com.mewsinsa.auth.jwt.repository.RefreshTokenRepository;
import com.mewsinsa.member.domain.Member;
import com.mewsinsa.member.repository.MemberRepository;
import java.time.LocalDateTime;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
  Logger log = LoggerFactory.getLogger(getClass());
  private final RefreshTokenRepository refreshTokenRepository;
  private final LogoutTokenRepository logoutTokenRepository;
  private final MemberRepository memberRepository;
  private final JwtProvider jwtProvider;

  public JwtService(RefreshTokenRepository refreshTokenRepository,
      LogoutTokenRepository logoutTokenRepository,
      MemberRepository memberRepository, JwtProvider jwtProvider) {
    this.refreshTokenRepository = refreshTokenRepository;
    this.logoutTokenRepository = logoutTokenRepository;
    this.memberRepository = memberRepository;
    this.jwtProvider = jwtProvider;
  }


  public RefreshTokenDto getRefreshToken(Long memberId) {
    return refreshTokenRepository.findRefreshTokenByMemberId(memberId);
  }

  public JwtToken login(Long memberId) {
    // 1. 멤버 찾기
    // 2. 리프레시 토큰이 있는지 확인 (DB)
    // 3-1. 있으면 액세스만 발행 (멤버의 닉네임, 어드민 여부 가져와서 액세스 토큰 생성)
    // 3-2. 없으면 멤버의 닉네임, 어드민 여부 가져와서, 토큰 생성

    // 멤버 찾기
    Member member = memberRepository.findMemberById(memberId);

    // 리프레시 토큰이 있는지 확인
    RefreshTokenDto refreshToken = getRefreshToken(memberId);
    JwtToken jwtToken = null;
    if(refreshToken == null) { // 리프레시 토큰이 없는 경우 -> 새로 발행
      jwtToken = jwtProvider.createJwtToken(memberId, member.getNickname(), member.getAdmin());

    } else if(refreshToken.getExpiration().compareTo(new Date()) < 0) {
      // 리프레시 토큰의 기한 지남. 폐기 후 다시 발행
      refreshTokenRepository.deleteRefreshToken(memberId);
      jwtToken = jwtProvider.createJwtToken(memberId, member.getNickname(), member.getAdmin());
    } else {
      // 리프레시 토큰이 유효한 경우 -> 리프레시 토큰으로 액세스 토큰만 재발행
      String accessToken = jwtProvider.createAccessToken(memberId, member.getNickname(), member.getAdmin());
      jwtToken = new JwtToken(accessToken, refreshToken.getTokenValue());
    }

    return jwtToken;
  }


  // TODO: sign in -> 회원 이름, 이메일만 넘기고 나머지 정보를 적도록 함



  // TODO: logout -> 회원의 액세스 토큰을 logout된 토큰 DB에 저장
  public void logout(Long memberId, String accessToken) {
    try {
      // 멤버의 리프레시 토큰 먼저 삭제
      logoutTokenRepository.deleteLogoutToken(memberId);

      // logoutToken을 DB에 저장
      LogoutTokenDto logoutToken = new LogoutTokenDto(memberId, accessToken, LocalDateTime.now());
      logoutTokenRepository.addLogoutToken(logoutToken);
    } catch(Exception e) {
      throw new IllegalArgumentException("로그아웃에 실패하였습니다.", e);
    }
  }

  // TODO: 인가(헤더를 읽고 로그인된 사용자인지 판단하는 로직, 관리자 여부도 확인)
  // Controller에서 @RequestHeader로 헤더 읽고 Authorization의 액세스 토큰의 기한을 보고 로그인 사용자인지 판단
  // 주문, 회원 정보 조회, 장바구니, 매거진 글쓰기 등등 로그인 안된 사용자는 인터셉터로 걸러야 됨

}
