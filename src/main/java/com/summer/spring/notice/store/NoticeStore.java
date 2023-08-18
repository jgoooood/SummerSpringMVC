package com.summer.spring.notice.store;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.summer.spring.notice.domain.Notice;
import com.summer.spring.notice.domain.PageInfo;

public interface NoticeStore {

	/**
	 * 공지사항 등록 Store
	 * @param session
	 * @param notice
	 * @return int
	 */
	int insertNotice(SqlSession session, Notice notice);

	/**
	 * 공지사항 리스트
	 * @param session
	 * @return List<Notice>
	 */
	List<Notice> selectNoticeList(SqlSession session, PageInfo pInfo);

	/**
	 * 공지사항 개수 조회 Store
	 * @param session 
	 * @return
	 */
	int selectListCount(SqlSession session);

	
	/**
	 * 공지사항 전체 조회
	 * @param session
	 * @param searchKeyword
	 * @return
	 */
	//List<Notice> searchNoticesByAll(SqlSession session, String searchKeyword);

	/**
	 * 공지사항 작성자 조회
	 * @param session
	 * @param searchKeyword
	 * @return
	 */
	//List<Notice> searchNoticesByWriter(SqlSession session, String searchKeyword);
	
	/**
	 * 공지사항 제목으로 조회 Store
	 * @param session
	 * @param searchKeyword
	 * @return
	 */
	//List<Notice> selectNoticesByTitle(SqlSession session, String searchKeyword);

	/**
	 * 공지사항 내용으로 조회
	 * @param session
	 * @param searchKeyword
	 * @return
	 */
	//List<Notice> searchNoticesByContent(SqlSession session, String searchKeyword);
	
	
	/**
	 * 공지사항 조건에 따라 키워드로 검색 Service
	 * @param session
	 * @param searchCondition
	 * @param searchKeyword
	 * @return
	 */
	List<Notice> selectNoticesByKeyword(SqlSession session, PageInfo pInfo, Map<String, String> paramMap);

	/**
	 * 공지사항 검색 게시물 전체 개수 Store
	 * @param session
	 * @param paramMap
	 * @return
	 */
	int selectListCount(SqlSession session, Map<String, String> paramMap);



}
