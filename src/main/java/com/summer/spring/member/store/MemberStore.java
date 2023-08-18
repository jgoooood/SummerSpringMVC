package com.summer.spring.member.store;

import org.apache.ibatis.session.SqlSession;

import com.summer.spring.member.domain.Member;

public interface MemberStore {

	int insertMember(SqlSession session, Member member);

	int updateMember(SqlSession session, Member member);

	int deleteMember(SqlSession session, String memberId);

	//int checkMemberLogin(SqlSession session, Member member);
	Member checkMemberLogin(SqlSession session, Member member);

	Member selectOneById(SqlSession session, String memberId);

}
