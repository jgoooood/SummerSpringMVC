package com.summer.spring.notice.store.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.summer.spring.notice.domain.Notice;
import com.summer.spring.notice.domain.PageInfo;
import com.summer.spring.notice.store.NoticeStore;

@Repository
public class NoticeStoreLogic implements NoticeStore{

	@Override
	public int insertNotice(SqlSession session, Notice notice) {
		int result = session.insert("NoticeMapper.insertNotice", notice);
		return result;
	}

	@Override
	public List<Notice> selectNoticeList(SqlSession session, PageInfo pInfo) {
		//int limit = 10; // 가져오는 행의 갯수를 핸들링 : 보여줄 목록 개수를 의미함
		//int offset = (currentPage - 1) * limit; // 시작값
		int limit = pInfo.getRecordCountPerPage(); // pInfo에서 꺼내서 세팅
		int offset = (pInfo.getCurrentPage() - 1) * limit; // 시작값
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Notice> nList = session.selectList("NoticeMapper.selectNoticeList", null, rowBounds);
		return nList;
	}

	@Override
	public int selectListCount(SqlSession session) {
		int result = session.selectOne("NoticeMapper.selectListCount");
		return result;
	}

	@Override
	public List<Notice> selectNoticesByKeyword(SqlSession session, PageInfo pInfo, Map<String, String> paramMap) {
		int limit = pInfo.getRecordCountPerPage();
		int offset = (pInfo.getCurrentPage()-1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Notice> searchList = session.selectList("NoticeMapper.selectNoticesByKeyword", paramMap, rowBounds);
		return searchList;
	}

	@Override
	public int selectListCount(SqlSession session, Map<String, String> paramMap) {
		int result = session.selectOne("NoticeMapper.selectListByKeywordCount", paramMap);
		return result;
	}

	@Override
	public Notice selectNoticeByNo(SqlSession session, Integer noticeNo) {
		Notice noticeOne = session.selectOne("NoticeMapper.selectNoticeByNo", noticeNo);
		return noticeOne;
	}

	@Override
	public int updateNotice(SqlSession session, Notice notice) {
		int result = session.update("NoticeMapper.updateNotice", notice);
		return result;
	}

	/* 중복코드 많음 -> mybatis 동적쿼리 사용
	@Override
	public List<Notice> searchNoticesByAll(SqlSession session, String searchKeyword) {
		List<Notice> searchList = session.selectList("NoticeMapper.searchNoticesByAll", searchKeyword);
		return searchList;
	}
	
	@Override
	public List<Notice> searchNoticesByWriter(SqlSession session, String searchKeyword) {
		List<Notice> searchList = session.selectList("NoticeMapper.searchNoticesByWriter", searchKeyword);
		return searchList;
	}
	
	@Override
	public List<Notice> selectNoticesByTitle(SqlSession session, String searchKeyword) {
		List<Notice> searchList = session.selectList("NoticeMapper.selectNoticesByTitle", searchKeyword);
		return searchList;
	}

	@Override
	public List<Notice> searchNoticesByContent(SqlSession session, String searchKeyword) {
		List<Notice> searchList = session.selectList("NoticeMapper.searchNoticesByContent", searchKeyword);
		return searchList;
	}
	*/

	
	//컨트롤러에서 생성
	//public void generatePageNavi(int currentPage) {}
	


}
