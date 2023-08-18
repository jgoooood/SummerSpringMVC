package com.summer.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.summer.spring.member.domain.Member;
import com.summer.spring.member.service.MemberService;

@Controller
public class MemberController {
	
	//service객체 어노테이션으로 의존성주입
	@Autowired
	private MemberService service;
	
	@RequestMapping(value = "/member/register.kh", method=RequestMethod.GET)
	public String showRegisterForm() {
		return "member/register";
	}
	
//			@RequestParam을 @ModelAttribute Member member 한줄로 대체함
//			, @RequestParam("memberId") String memberId
//			, @RequestParam("memberPw") String memberPw
//			, @RequestParam("memberName") String memberName
//			, @RequestParam("memberAge") int memberAge
//			, @RequestParam("memberGender") String memberGender
//			, @RequestParam("memberEmail") String memberEmail
//			, @RequestParam("memberPhone") String memberPhone
//			, @RequestParam("memberAddr") String memberAddr
//			, @RequestParam("memberHobby") String memberHobby) {
	@RequestMapping(value="/member/register.kh", method=RequestMethod.POST)
	public String insertMember(Model model
			, @ModelAttribute Member member) {
		try {
			int result = service.insertMember(member);
			if(result > 0) {
				//성공하면 로그인 페이지
				//home.jsp가 로그인할 수 있는 페이지가 됨
				return "redirect:/index.jsp";
			} else {
				//실패하면 에러페이지로 이동
				model.addAttribute("msg", "회원가입이 완료되지 않았습니다.");
				model.addAttribute("error", "회원가입 실패");
				model.addAttribute("url", "/member/register.kh");
				return "common/errorPage";
			}
		} catch (Exception e) {
			model.addAttribute("msg", "관리자에게 문의해주세요");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/member/register.kh");
			return "common/errorPage";
		}
	}
	
	@RequestMapping(value="/member/login.kh", method=RequestMethod.POST)
	public String memberLoginCheck(
			@ModelAttribute Member member
			, HttpSession session
			, Model model) {
		try {
			//int로 체크하는 방식 
			//int result = service.checkMemberLogin(member);
			// SELECT * FROM MEMBER_TBL WHERE MEMBER_ID=? AND MEMBER_PW = ?
			//if(result > 0) {
				// 로그인 성공 : 메인페이지 이동
			//	session.setAttribute("memberId", member.getMemberId()); //성공하면 세션에 아이디 저장
			//	return "redirect:/index.jsp";
			Member mOne = service.checkMemberLogin(member);
			if(mOne != null) {
				session.setAttribute("memberId", mOne.getMemberId());
				session.setAttribute("memberName", mOne.getMemberName());
				return "redirect:/index.jsp";
			} else {
				model.addAttribute("msg", "로그인이 완료되지 않았습니다.");
				model.addAttribute("error", "로그인 실패");
				model.addAttribute("url", "/member/login.kh");
				return "common/errorPage";
			}	
		} catch (Exception e) {
			model.addAttribute("msg", "관리자에게 문의해주세요");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/member/register.kh");
			return "common/errorPage";
		}
		
	}
	
	@RequestMapping(value="/member/logout.kh", method=RequestMethod.GET)
	public String logout(HttpSession session, Model model) {
		if(session != null) {
			session.invalidate();
			return "redirect:/index.jsp";
		} else {
			model.addAttribute("msg", "로그아웃을 완료하지 못했습니다.");
			model.addAttribute("error", "로그아웃 실패");
			model.addAttribute("url", "/index.jsp");
			return "common/errorPage";
		}
	}
	
	@RequestMapping(value="/member/mypage.kh", method=RequestMethod.GET)
	public String showMypage(@RequestParam("memberId") String memberId, Model model) {
		try {
			Member mOne = service.selectOneById(memberId);
			if(mOne != null) {
				model.addAttribute("member", mOne);
				return "member/mypage";
			} else {
				model.addAttribute("msg", "회원정보를 불러올 수 없습니다.");
				model.addAttribute("error", "회원정보조회 실패");
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
/*	기존 회원탈퇴 방식
	@RequestMapping(value="/member/delete.kh", method=RequestMethod.GET)
	public String outServiceMember(@RequestParam("memberId") String memberId
			, Model model) {
		try {
			int result = service.deleteMember(memberId);
			if(result > 0) {
				//회원탈퇴하면 로그아웃메소드가 호출되도록 매핑경로 입력
				return "redirect:/member/logout.kh";
			} else {
				model.addAttribute("msg", "회원탈퇴를 완료하지 못했습니다.");
				model.addAttribute("error", "회원탈퇴실패");
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
*/
	@RequestMapping(value="/member/delete.kh", method=RequestMethod.GET)
	public String outServiceMember(@RequestParam("memberId") String memberId
			, Model model) {
		try {
			int result = service.deleteMember(memberId);
			if(result > 0) {
				return "redirect:/member/logout.kh";
			} else {
				model.addAttribute("msg", "회원탈퇴를 완료하지 못했습니다.");
				model.addAttribute("error", "회원탈퇴실패");
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
	
	@RequestMapping(value="/member/update.kh", method=RequestMethod.GET)
	public String showModifyForm(String memberId, Model model) {
		Member mOne = service.selectOneById(memberId);
		try {
			if(mOne != null) {
				model.addAttribute("member", mOne);
				return "member/modify";
			} else {
				model.addAttribute("msg", "회원정보를 불러올 수 없습니다.");
				model.addAttribute("error", "회원정보조회 실패");
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
	
	@RequestMapping(value="/member/update.kh", method=RequestMethod.POST)
	public String updateMember(@ModelAttribute Member member, Model model) {
		int result = service.updateMember(member);
		try {
			if(result > 0) {
				return "redirect:/member/mypage.kh?memberId="+member.getMemberId();
			} else {
				model.addAttribute("msg", "수정이 완료되지 않았습니다.");
				model.addAttribute("error", "회원정보 수정 실패");
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
}
