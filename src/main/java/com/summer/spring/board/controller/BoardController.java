package com.summer.spring.board.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.servlet.ModelAndView;

import com.summer.spring.board.domain.Board;
import com.summer.spring.board.domain.PageInfo;
import com.summer.spring.board.domain.Reply;
import com.summer.spring.board.service.BoardService;
import com.summer.spring.board.service.ReplyService;

@Controller
public class BoardController {

	@Autowired
	private BoardService bService;
	@Autowired
	private ReplyService rService;
	
	// ModelAndView : 페이지이동, 데이터 보낼 수 있는 클래스 -> 매개변수 mv 필요 
	// 메소드로 다양한 기능을 사용해서 페이지 이동-> return mv;
	// 기존 : return "board/write";
	
	@RequestMapping(value="/board/list.kh", method=RequestMethod.GET)
	public ModelAndView showBoardList(
			@RequestParam(value="page", required=false, defaultValue="1") Integer currentPage
			, ModelAndView mv) {
		Integer totalCount = bService.getListCount();
		PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
		List<Board> bList = bService.selectBoardList(pInfo);
//		mv.addObject("pInfo", pInfo);
//		mv.addObject("bList", bList);
//		mv.setViewName("board/list");
		// 한 줄로 작성가능
		mv.addObject("pInfo", pInfo).addObject("bList", bList).setViewName("board/list");
		
		return mv;
	}
	
	private PageInfo getPageInfo(Integer currentPage, Integer totalCount) {
		int recordCountPerPage = 10;
		int naviCountPerPage = 10;
		int naviTotalCount = (int)Math.ceil((double)totalCount / recordCountPerPage); //Math.ceil올림
		int startNavi = (((int)((double)currentPage / naviCountPerPage+0.9))-1)*naviCountPerPage +1;
		int endNavi = startNavi + naviCountPerPage - 1;
		if(endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		}
		PageInfo pInfo = new PageInfo(currentPage, totalCount, naviTotalCount, recordCountPerPage, naviCountPerPage, startNavi, endNavi);
		return pInfo;
	}

	@RequestMapping(value="/board/write.kh", method=RequestMethod.GET)
	public ModelAndView showWriteForm(ModelAndView mv) {
		mv.setViewName("board/write");
		return mv;
	}
	
	@RequestMapping(value="/board/write.kh", method=RequestMethod.POST)
	public ModelAndView boardRegister(
			ModelAndView mv
			, @ModelAttribute Board board
			, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile
			,HttpServletRequest request) {
		try {
			if(uploadFile != null && !uploadFile.getOriginalFilename().equals("")) {
				//파일정보(이름, 리네임, 경로, 크기) 및 파일저장
				Map<String, Object> bMap = this.saveFile(request, uploadFile);
				board.setBoardFileName((String)bMap.get("fileName"));
				board.setBoardFileRename((String)bMap.get("fileRename"));
				board.setBoardFilePath((String)bMap.get("filePath"));
				board.setBoardFileLength((long)bMap.get("fileLength"));
			}
			int result = bService.insertBoard(board);
			mv.setViewName("redirect:/board/list.kh");
		} catch (Exception e) {
//			model.addAttribute("msg", "게시글 등록이 완료되지 않았습니다.")
			mv.addObject("msg", "게시글 등록이 완료되지 않았습니다.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/board/write.kh");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="/board/detail.kh", method=RequestMethod.GET)
	public ModelAndView showDetailForm(
			ModelAndView mv
			, @RequestParam(value="boardNo") Integer boardNo
			, Model model) {
		try {
			Board board = bService.selectBoardOneByNo(boardNo);
			if(board != null) {
				//게시글이 있으면 리스트출력
				List<Reply> replyList = rService.selectReplyList(boardNo);
				if(replyList.size() > 0) { //댓글목록이 있으면 저장
					mv.addObject("rList", replyList);
				}
				mv.addObject("board", board);
				mv.setViewName("/board/detail");
			} else {
				mv.addObject("msg", "게시글 조회를 할 수 없습니다.");
				mv.addObject("error", "게시글 조회를 할 수 없습니다.");
				mv.addObject("url", "/board/list.kh");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", "게시글 조회를 할 수 없습니다.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/board/list.kh");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	

	private Map<String, Object> saveFile(HttpServletRequest request, MultipartFile uploadFile) throws Exception {
		Map<String, Object> fileMap = new HashMap<String, Object>();
		// resources 경로 구하기
		String root = request.getSession().getServletContext().getRealPath("resources");
		// 파일 저장 경로 구하기
		String savePath = root + "\\bUploadFiles";
		// 파일 이름 구하기
		String fileName = uploadFile.getOriginalFilename();
		// 파일 확장자 구하기
		String extension 
			= fileName.substring(fileName.lastIndexOf(".")+1);
		// 시간으로 파일 리네임
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileRename = sdf.format(new Date(System.currentTimeMillis()))+"."+extension;
		//파일저장전 폴더만들기
		File saveFolder = new File(savePath);
		if(!saveFolder.exists()) {
			saveFolder.mkdir();
		}
		//파일저장 후 length저장
		File saveFile = new File(savePath+"\\"+fileRename);
		uploadFile.transferTo(saveFile);
		long fileLength = uploadFile.getSize();
		//파일정보리턴
		fileMap.put("fileName", fileName);
		fileMap.put("fileRename", fileRename);
		fileMap.put("filePath", "../resources/bUploadFiles/"+fileRename);
		fileMap.put("fileLength", fileLength);
		return fileMap;
	}


}
