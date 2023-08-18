<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원정보조회</title>
		<link rel="stylesheet" href="../resources/css/main.css"> 
	</head>
	<body>
		<h1>회원정보조회</h1>
		<fieldset>
			<legend>회원 상세 정보</legend>
			<ul>
				<li>
					<label for="memberId">아이디</label>
					<span>${member.memberId }</span>
				</li>
				<li>
					<label for="memberName">이름</label>
					<span>${member.memberName }</span>
				</li>
				<li>
					<label for="memberAge">나이</label>
					<span>${member.memberAge }</span>
				</li>
				<li>
					<label for="memberGender">성별</label>
					<c:if test="${member.memberGender eq 'M' }"><span>남</span></c:if>
					<c:if test="${member.memberGender eq 'F' }"><span>여</span></c:if>
				</li>
				<li>
					<label for="memberEmail">이메일</label>
					<span>${member.memberEmail }</span>
				</li>
				<li>
					<label for="memberPhone">전화번호</label>
					<span>${member.memberPhone }</span>
				</li>
				<li>
					<label for="memberAddress">주소</label>
					<span>${member.memberAddress }</span>
				</li>
				<li>
					<label for="memberHobby">취미</label>
					<span>${member.memberHobby }</span>
				</li>
				<li>
					<label for="memberDate">가입날짜</label>
					<span>${member.memberDate }</span>
				</li>
			</ul>
		</fieldset>
		<a href="/member/update.kh?memberId=${member.memberId}">회원정보수정</a>	
		<a href="/member/delete.kh?memberId=${member.memberId}">회원탈퇴</a>
	</body>
</html>