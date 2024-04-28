package com.mewsinsa.member.service;

import static org.junit.jupiter.api.Assertions.*;

import com.mewsinsa.auth.jwt.domain.JwtToken;
import com.mewsinsa.auth.jwt.service.JwtService;
import com.mewsinsa.member.domain.Member;
import com.mewsinsa.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {
  @Autowired
  private MemberService memberService;
  @Autowired
  private JwtService jwtService;

  @Autowired
  private MemberRepository memberRepository;

  private static Long memberId;

  @BeforeEach
  void saveMember() {
    Member member = new Member.Builder()
        .name("홍길동")
        .nickname("gildong")
        .password("temp password")
        .mewsinsaId("gildong123")
        .phone("010-1234-1234")
        .email("gildong@test.com")
        .tierId(1)
        .isAdmin(false)
        .points(0L)
        .build();
    memberRepository.addMember(member);

    memberId = member.getMemberId();
    System.out.println(memberId);
  }

  @AfterEach
  void deleteMember() {
    memberRepository.deleteMember(memberId);
  }

  @DisplayName("토큰을 파싱하여 멤버 아이디가 제대로 읽어지는지 확인합니다.")
  @Test
  void getMemberIdByAccessTokenTest() {
    // 로그인하기
    JwtToken jwtToken = jwtService.login(memberId);


    String accessToken = jwtToken.getAccessToken().replace("Bearer ", "");

    Long memberIdByAccessToken = memberService.getMemberIdByAccessToken(accessToken);
    Assertions.assertThat(memberIdByAccessToken).isEqualTo(memberId);
    System.out.println(memberIdByAccessToken);
  }
}