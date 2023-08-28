package com.summer.spring.board.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.summer.spring.board.domain.Reply;

public interface ReplyStore {

	/**
	 * 댓글등록 Store
	 * @param session
	 * @param reply
	 * @return
	 */
	int insertReply(SqlSession session, Reply reply);

	/**
	 * 댓글 목록 Store
	 * @param session
	 * @param boardNo 
	 * @return
	 */
	List<Reply> selectReplyList(SqlSession session, Integer boardNo);

	/**
	 * 댓글 수정 Store
	 * @param session
	 * @param reply
	 * @return
	 */
	int updateReply(SqlSession session, Reply reply);

}
