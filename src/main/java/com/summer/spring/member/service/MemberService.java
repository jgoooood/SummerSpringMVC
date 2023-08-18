package com.summer.spring.member.service;

import com.summer.spring.member.domain.Member;

public interface MemberService {

	/**
	 * 회원가입
	 * @param member
	 * @return
	 */
	int insertMember(Member member);

	/**
	 * 회원정보수정
	 * @param member
	 * @return
	 */
	int updateMember(Member member);

	/**
	 * 회원탈퇴 service, update로 할 예정
	 * @param memberId
	 * @return
	 */
	int deleteMember(String memberId);

	/**
	 * 로그인
	 * @param member
	 * @return
	 */
	//int checkMemberLogin(Member member);
	Member checkMemberLogin(Member member);

	/**
	 * 아이디정보조회
	 */
	Member selectOneById(String memberId);

}
