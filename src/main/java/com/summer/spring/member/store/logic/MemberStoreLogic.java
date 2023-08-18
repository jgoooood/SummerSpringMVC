package com.summer.spring.member.store.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.summer.spring.member.domain.Member;
import com.summer.spring.member.store.MemberStore;

@Repository
public class MemberStoreLogic implements MemberStore {

	@Override
	public int insertMember(SqlSession session, Member member) {
		int result = session.insert("MemberMapper.insertMember", member);
		return result;
	}

	@Override
	public int updateMember(SqlSession session, Member member) {
		int result = session.update("MemberMapper.updateMember", member);
		return result;
	}

/* 기존 회원탈퇴 방식 : 회원정보를 삭제
	@Override
	public int deleteMember(SqlSession session, String memberId) {
		int result = session.delete("MemberMapper.deleteMember", memberId);
		return result;
	}
*/
	@Override
	public int deleteMember(SqlSession session, String memberId) {
		//업데이트로 변경해서 데이터를 삭제하지 않고 회원유무를 변경함
		int result = session.update("MemberMapper.deleteMember", memberId);
		return result;
	}

/* 로그인체크를 result로 결과받음
	@Override
	public int checkMemberLogin(SqlSession session, Member member) {
		int result = session.selectOne("MemberMapper.checkMemberLogin", member);
		return result;
	}
 */
	@Override
	public Member checkMemberLogin(SqlSession session, Member member) {
		Member mOne = session.selectOne("MemberMapper.checkMemberLogin", member);
		return mOne;
	}

	@Override
	public Member selectOneById(SqlSession session, String memberId) {
		Member mOne = session.selectOne("MemberMapper.selectOneById", memberId);
		return mOne;
	}

}
