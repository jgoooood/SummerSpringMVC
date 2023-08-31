package com.summer.spring.board.service;

import java.util.List;
import java.util.Map;

import com.summer.spring.board.domain.Board;
import com.summer.spring.board.domain.PageInfo;

public interface BoardService {

	/**
	 * 게시글등록 Service
	 * @param board
	 * @return
	 */
	int insertBoard(Board board);

	/**
	 * 총 게시물 수 Service
	 * @return
	 */
	int getListCount();

	/**
	 * 게시물 리스트 Service
	 * @param pInfo
	 * @return
	 */
	List<Board> selectBoardList(PageInfo pInfo);

	/**
	 * 게시물 불러오기 Service
	 * @param boardNo
	 * @return
	 */
	Board selectBoardOneByNo(Integer boardNo);

	/**
	 * 게시글 삭제 Service
	 * @param board
	 * @return
	 */
	int deleteBoard(Board board);

	/**
	 * 게시글 수정 Service
	 * @param board
	 * @return
	 */
	int updateBoard(Board board);

}
