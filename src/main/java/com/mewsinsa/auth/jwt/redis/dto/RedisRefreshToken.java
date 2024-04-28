package com.mewsinsa.auth.jwt.redis.dto;


import com.mewsinsa.member.domain.Member;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "refreshToken", timeToLive = 1209600L) // 2 weeks
public class RedisRefreshToken {
  @Id
  private Long memberId;
  private String refreshToken;
  private Member member;

  //==Constructor==//
  public RedisRefreshToken() {
  }

  public RedisRefreshToken(Long memberId, String refreshToken, Member member) {
    this.memberId = memberId;
    this.refreshToken = refreshToken;
    this.member = member;
  }

  //==Getter==//
  public String getRefreshToken() {
    return refreshToken;
  }

  public Member getMember() {
    return member;
  }

  public Long getMemberId() {
    return memberId;
  }

  //==Setter==//
  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public void setMember(Member member) {
    this.member = member;
  }

  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }
}
