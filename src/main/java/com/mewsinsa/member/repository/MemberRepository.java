package com.mewsinsa.member.repository;

import com.mewsinsa.member.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Mapper
@Repository
public interface MemberRepository {
  public Member findMemberByEmail(@Param("userEmail") String userEmail);
  public Member findMemberById(@Param("memberId") Long memberId);
  public Member findMemberByMewsinsaId(@Param("mewsinsaId") String mewsinsaId);
  public void addMember(Member member);
  public void deleteMember(@Param("memberId") Long memberId);
  public Member findMemberByAccessToken(@Param("accessToken") String accessToken);
  public void updateMemberPoints(@Param("memberId") Long memberId, @Param("points") Long points);
}
