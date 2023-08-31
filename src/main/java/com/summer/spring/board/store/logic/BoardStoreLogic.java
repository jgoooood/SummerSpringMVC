package com.summer.spring.board.store.logic;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.summer.spring.board.domain.Board;
import com.summer.spring.board.domain.PageInfo;
import com.summer.spring.board.store.BoardStore;

@Repository
public class BoardStoreLogic implements BoardStore {

	@Override
	public int insertBoard(SqlSession session, Board board) {
		int result = session.insert("BoardMapper.insertBoard", board);
		return result;
	}

	@Override
	public int selectListCount(SqlSession session) {
		int totalCount = session.selectOne("BoardMapper.selectListCount");
		return totalCount;
	}

	@Override
	public List<Board> selectBoardList(SqlSession session, PageInfo pInfo) {
		int limit = pInfo.getRecordCountPerPage();
		int offset = (pInfo.getCurrentPage() -1)*limit;
		RowBounds rowBound = new RowBounds(offset, limit);
		List<Board> bList = session.selectList("BoardMapper.selectBoardList", null, rowBound);
		return bList;
	}

	@Override
	public Board selectBoardOneByNo(SqlSession session, Integer boardNo) {
		Board board = session.selectOne("BoardMapper.selectBoardOneByNo", boardNo);
		return board;
	}

	@Override
	public int deleteBoard(SqlSession session, Board board) {
		int result = session.update("BoardMapper.deleteBoard", board);
		return result;
	}

	@Override
	public int updateBoard(SqlSession session, Board board) {
		int result = session.update("BoardMapper.updateBoard", board);
		return result;
	}

}
