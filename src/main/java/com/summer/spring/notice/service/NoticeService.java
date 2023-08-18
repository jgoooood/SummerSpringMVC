package com.summer.spring.notice.service;

import java.util.List;
import java.util.Map;

import com.summer.spring.notice.domain.Notice;
import com.summer.spring.notice.domain.PageInfo;

public interface NoticeService {

	/**
	 * 공지사항 등록 Service
	 * @param notice
	 * @return int
	 */
	int insertNotice(Notice notice);

	/**
	 * 공지사항리스트
	 * @return List<Notice>
	 */
	List<Notice> selectList(PageInfo pInfo);

	/**
	 * 공지사항 전체 개수 조회 Service
	 * @return
	 */
	int getListCount();

	/**
	 * 공지사항 전체 검색
	 * @param searchKeyword
	 * @return
	 */
	//List<Notice> searchNoticesByAll(String searchKeyword);

	/**
	 * 공지사항 작성자로 검색
	 * @param searchKeyword
	 * @return
	 */
	//List<Notice> searchNoticesByWriter(String searchKeyword);
	
	/**
	 * 공지사항 제목으로 검색 service
	 * @param searchKeyword
	 * @return
	 */
	//List<Notice> searchNoticesByTitle(String searchKeyword);

	/**
	 * 공지사항 내용으로 검색
	 * @param searchKeyword
	 * @return
	 */
	//List<Notice> searchNoticesByContent(String searchKeyword);

	/**
	 * 공지사항 조건에 따라 키워드로 검색 Service
	 * @param searchCondition
	 * @param searchKeyword
	 * @return
	 */
	List<Notice> searchNoticesByKeyword(PageInfo pInfo, Map<String, String> paramMap);

	/**
	 * 공지사항 검색 게시물 전체 개수 Service
	 * @param paramMap
	 * @return
	 */
	int getListCount(Map<String, String> paramMap);



}
