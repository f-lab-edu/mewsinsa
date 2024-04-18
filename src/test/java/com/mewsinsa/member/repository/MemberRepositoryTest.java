package com.mewsinsa.member.repository;

import com.mewsinsa.member.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberRepositoryTest {
  @Autowired
  MemberRepository memberRepository;

  Logger log = LoggerFactory.getLogger(getClass());

  Long memberId;
  @BeforeEach
  void beforeEach() {
    // 새로운 멤버 넣기
    Member memberA = new Member(0L, "testid", "testpw", "testUser", "testNickname", "test2@naver.com",
        "01012341234", "", 1, false, 0L, null);
    memberRepository.addMember(memberA);
    memberId = memberA.getMemberId();
  }

  @AfterEach
  void afterEach() {
    memberRepository.deleteMember(memberId);
  }

  @DisplayName("아이디로 회원을 찾을 수 있다.")
  @Test
  void findMemberByIdTest() {
    Member findById = memberRepository.findMemberById(memberId);

    Assertions.assertThat(findById.getMemberId()).isEqualTo(memberId);
    Assertions.assertThat(findById.getMewsinsaId()).isEqualTo("testid");
    Assertions.assertThat(findById.getName()).isEqualTo("testUser");
  }

}