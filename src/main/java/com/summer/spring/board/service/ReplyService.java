package com.summer.spring.board.service;

import java.util.List;

import com.summer.spring.board.domain.Reply;

public interface ReplyService {

	/**
	 * 댓글등록 Service
	 * @param reply
	 * @return
	 */
	int insertReply(Reply reply);


	/**
	 * 댓글 수정 service
	 * @param reply
	 * @return
	 */
	int updateReply(Reply reply);


	/**
	 * 댓글 목록 Service
	 * @param boardNo 
	 * @return
	 */
	List<Reply> selectReplyList(Integer boardNo);

}
