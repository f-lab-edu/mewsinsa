package com.mewsinsa.auth.jwt.redis.dto;


import com.mewsinsa.member.domain.Member;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "accessToken", timeToLive = 1800) // 30m
public class RedisAccessToken {
  @Id
  private Long memberId;
  private String accessToken;
  private Member member;

  //==Constructor==//
  public RedisAccessToken() {
  }

  public RedisAccessToken(Long memberId, String accessToken, Member member) {
    this.memberId = memberId;
    this.accessToken = accessToken;
    this.member = member;
  }

  //==Getter==//
  public String getAccessToken() {
    return accessToken;
  }

  public Member getMember() {
    return member;
  }

  public Long getMemberId() {
    return memberId;
  }

  //==Setter==//
  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public void setMember(Member member) {
    this.member = member;
  }

  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }
}
