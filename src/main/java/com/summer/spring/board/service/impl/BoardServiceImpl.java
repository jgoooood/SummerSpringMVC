package com.summer.spring.board.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.spring.board.domain.Board;
import com.summer.spring.board.domain.PageInfo;
import com.summer.spring.board.service.BoardService;
import com.summer.spring.board.store.BoardStore;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private SqlSession session;
	@Autowired
	private BoardStore bStore;
	
	@Override
	public int insertBoard(Board board) {
		int result = bStore.insertBoard(session, board);
		return result;
	}

	@Override
	public int getListCount() {
		int totalCount = bStore.selectListCount(session);
		return totalCount;
	}

	@Override
	public List<Board> selectBoardList(PageInfo pInfo) {
		List<Board> bList = bStore.selectBoardList(session, pInfo);
		return bList;
	}

	@Override
	public Board selectBoardOneByNo(Integer boardNo) {
		Board board = bStore.selectBoardOneByNo(session, boardNo);
		return board;
	}

}
