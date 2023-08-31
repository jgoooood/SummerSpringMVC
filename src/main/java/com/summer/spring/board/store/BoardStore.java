package com.summer.spring.board.store;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.summer.spring.board.domain.Board;
import com.summer.spring.board.domain.PageInfo;

public interface BoardStore {

	/**
	 * 게시글등록 Store
	 * @param session
	 * @param board
	 * @return
	 */
	int insertBoard(SqlSession session, Board board);

	/**
	 * 게시물 수 구하기 Store
	 * @param session
	 * @return
	 */
	int selectListCount(SqlSession session);

	/**
	 * 게시물 리스트 Store
	 * @param session
	 * @param pInfo
	 * @return
	 */
	List<Board> selectBoardList(SqlSession session, PageInfo pInfo);

	/**
	 * 게시물조회 Store
	 * @param session
	 * @param boardNo
	 * @return
	 */
	Board selectBoardOneByNo(SqlSession session, Integer boardNo);

	/**
	 * 게시글삭제 Store
	 * @param session
	 * @param board
	 * @return
	 */
	int deleteBoard(SqlSession session, Board board);

	/**
	 * 게시글 수정 Store
	 * @param session
	 * @param board
	 * @return
	 */
	int updateBoard(SqlSession session, Board board);

}
