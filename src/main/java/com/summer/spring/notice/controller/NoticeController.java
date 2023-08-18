package com.summer.spring.notice.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.summer.spring.notice.domain.Notice;
import com.summer.spring.notice.domain.PageInfo;
import com.summer.spring.notice.service.NoticeService;

@Controller
public class NoticeController {
	@Autowired
	private NoticeService service;
	
	@RequestMapping(value="/notice/insert.kh", method=RequestMethod.GET)
	public String showInserForm() {
		return "notice/insert";
	}
	
	@RequestMapping(value="/notice/insert.kh", method=RequestMethod.POST)
	public String insertNotice(
			@ModelAttribute Notice notice
			//pom.xml라이브러리 추가해줌->root-context.xml 라이브러리 빈등록진행
			, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile
			, HttpServletRequest request
			, Model model) {
		try {
			if(!uploadFile.getOriginalFilename().equals("")) {
				//================ 1. 파일 이름 ================
				String fileName = uploadFile.getOriginalFilename();
				String root = request.getSession().getServletContext().getRealPath("resources");
				//ServletContext 서블릿 정보가 저장된 객체
				//getRealPath : resources폴더의 경로를 가져옴 SummerSpringMVC/src/main/webapp/resources
				//업로드시 resources폴더에 저장됨
				//resources폴더에 업로드한 파일을 저장할 폴더saveFolder가 없을 경우 자동 생성
				String saveFolder = root + "\\uploadFiles";
				File folder = new File(saveFolder);
				if(!folder.exists()) { //폴더가 없으면
					folder.mkdir(); //폴더생성
				}
				
				//================ 2. 파일 경로 ================
				//파일 경로(내가 저장한 후에 그 경로에 DB에 저장하도록 준비하는 것)
				String savePath = saveFolder + "\\" + fileName;
				// 파일객체 생성
				File file = new File(savePath);
				// ********************** 실제파일저장
				uploadFile.transferTo(file); //파일저장완료
				
				// ================ 3. 파일 크기 ================
				long fileLength = uploadFile.getSize();
				
				// DB에 저장하기 위해 notice에 데이터를 set하는 부분
				notice.setNoticeFileName(fileName);
				notice.setNoticeFilePath(savePath);
				notice.setNoticeFileLength(fileLength);
			}
			int result = service.insertNotice(notice);
			if(result > 0) {
				return "redirect:/notice/list.kh";
			} else {
				model.addAttribute("msg", "공지등록이 완료되지 않았습니다.");
				model.addAttribute("error", "공지등록 실패");
				model.addAttribute("url", "/notice/insert.kh");
				return "common/errorPage";
			}		
		} catch (Exception e) {
			model.addAttribute("msg", "관리자에게 문의해주세요");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/notice/insert.kh");
			return "common/errorPage";
		}
	}
	
	@RequestMapping(value="/notice/list.kh", method=RequestMethod.GET)
	public String showNoticeList(
			@RequestParam(value="page", required=false, defaultValue="1") Integer currentPage
			, Model model) {
		try {
			//넘어오는 page값이 0이 아니면 page에 넣고, 아니면 1을 넣음
			//int currentPage = page != 0 ? page : 1; 대신
			//@RequestParam(value="page", required=false, defaultValue="1") Integer currentpage 사용가능
			//page값이 없으면 defaultValue값이 1로 세팅되고 Integer는 null체크를 위해서 사용
			int totalCount = service.getListCount();
			PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
			List<Notice> nList = service.selectList(pInfo);
			//nList유효성체크방법 2가지
			//1.isEmpty() 
			//2.size()
			if(nList.size() > 0) {
				model.addAttribute("pInfo", pInfo);
				model.addAttribute("nList", nList);
				return "notice/list";
			} else {
				model.addAttribute("msg", "데이터 조회가 완료되지 않았습니다.");
				model.addAttribute("error", "공지사항 목록 조회 실패");
				model.addAttribute("url", "/index.jsp");
				return "common/errorPage";
			}	
		} catch (Exception e) {
			model.addAttribute("msg", "관리자에게 문의해주세요");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/index.jsp");
			return "common/errorPage";
		}
	}
	
	//페이지 네비게이터 생성메소드
	// 기존 dao에서 생성하던 것을 컨트롤러에서 생성함
	public PageInfo getPageInfo(int currentPage, int totalCount) {
		PageInfo pInfo = null;
		//고정값
		int recordCountPerPage = 10; //페이지당 보여줄 게시물개수
		int naviCountPerPage = 10; //페이지당 보여줄 네비게이터 수
		//계산식
		int naviTotalCount;
		int startNavi;
		int endNavi;
		
		// 1. 변수하나를 소수점으로 변환해서 자동형변환->소수점으로 계산 + 0.9
		// 2. +0.9계산한 뒤 int로 강제형변환해서 소수점 자르고 정수로 변환
		naviTotalCount = (int)((double)totalCount / recordCountPerPage + 0.9);
		//자바 내장객체 : Math.ceil(double)totalCount/recordCountPerPage
		
		// currentPage값이 1~5일 때 startNavi가 1로 고정되도록 구해주는 식
		// 1. 변수하나를 소수점으로 변환해서 자동형변환->소수점으로 계산 + 0.9
		// 2. +0.9계산한 뒤 int로 강제형변환해서 소수점 자르고 정수로 변환
		// 3. 1,2를 계산한 값에 -1
		// 4. 1,2,3을 계산한 값에 * naviCountPerPage + 1
		startNavi = (((int)((double)currentPage / naviCountPerPage + 0.9))-1)*naviCountPerPage + 1;
		endNavi = startNavi + naviCountPerPage - 1;
		// endNavi는 startNavi에 무조건 naviCountPerPage값을 더하므로 실제 최대값보다 커질 수 있음
		if(endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		}
		pInfo = new PageInfo(currentPage, recordCountPerPage, naviCountPerPage, startNavi, endNavi, totalCount, naviTotalCount);
		return pInfo;
	}
	
	@RequestMapping(value="/notice/search.kh", method=RequestMethod.GET)
	public String searchNoticeList(
			@RequestParam("searchCondition") String searchCondition
			, @RequestParam("searchKeyword") String searchKeyword
			, @RequestParam(value="page", required=false, defaultValue="1") Integer currentPage
			, Model model) {
		// selectList는 세번째 인자값이 무조건 rowBounds로 들어가야함
		// 2개의 값을 하나의 변수로 다루는 방법
		// 1. VO클래스로 객체생성 -> 기존에 했던 방식
		// 2. HashMap 사용 -> 새로운 방식
		Map<String, String> paramMap = new HashMap<String, String>();
		//put()메소드를 사용해서 key-value 설정을 하는데 key값(파란색)이 mapper.xml에서 사용됨
		//mapper에 들어가있는 key값과 동일해야만 정상작동됨->오타발생시 동작안됨
		//paramMap변수에는 두가지 변수의 값이 들어가 있는 상태->한번에 값을 전달할 수 있음
		//긴 스위치 구문을 한줄로 대체할 수 있음.
		paramMap.put("searchCondition", searchCondition);
		paramMap.put("searchKeyword", searchKeyword);
		//검색어 키워드를 넘겨서 검색되는 행의 개수를 갖고와야함
		int totalCount = service.getListCount(paramMap); 
		PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
		List<Notice> searchList = service.searchNoticesByKeyword(pInfo, paramMap);
		/* 스위치 역할을 mpper.xml의 if태그로 적용됨
		List<Notice> searchList = new ArrayList<Notice>();
		switch(searchCondition) {
			case "all" : 
				//SELECT * FROM NOTICE_TBL WHERE NOTICE_SUBJECT like %'||#{noticeSubject}||'%' OR NOTICE_CONTENT like '%'||#{noticeContent}||'%' OR NOTICE_WRITER like '%'||#{noticeWriter}||'%'
				searchList = service.searchNoticesByAll(searchKeyword);
				break;
			case "writer" :
				//SELECT * FROM NOTICE_TBL WHERE NOTICE_WRITER like '%'||#{noticeWriter}||'%'
				searchList = service.searchNoticesByWriter(searchKeyword);
				 break;
			case "title" : 
				//SELECT * FROM NOTICE_TBL WHERE NOTICE_SUBJECT like '%'||#{noticeSubject}||'%'
				searchList = service.searchNoticesByTitle(searchKeyword);
				break;
			case "content" : 
				//SELECT * FROM NOTICE_TBL WHERE NOTICE_CONTENT like '%'||#{noticeContent}||'%'
				searchList = service.searchNoticesByContent(searchKeyword);
				break;
		} */
		if(!searchList.isEmpty()) {
			//jsp에서 가져온 searchCondition,searchKeyword를 다시 세팅해줌
			model.addAttribute("searchCondition", searchCondition);
			model.addAttribute("searchKeyword", searchKeyword);
			model.addAttribute("pInfo", pInfo);
			model.addAttribute("sList", searchList);
			return "notice/search";
		} else {
			model.addAttribute("msg", "데이터 조회가 완료되지 않았습니다.");
			model.addAttribute("error", "공지사항 제목으로 조회 실패");
			model.addAttribute("url", "/notice/list.kh");
			return "common/errorPage";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
