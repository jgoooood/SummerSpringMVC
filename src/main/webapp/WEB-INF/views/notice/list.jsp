<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 시간, 날짜를 원하는 형태로 변경가능 : 포맷태그 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지사항 리스트</title>
		<link rel="stylesheet" href="../resources/css/notice/notice.css"> 
	</head>
	<body>
		<h1>공지사항 리스트</h1>
		<table>
			<colgroup>
				<col width="10%">
				<col width="40%">
				<col width="20%">
				<col width="15%">
				<col width="15%">
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>첨부파일</th>
					<!-- <th>조회수</th> -->
				</tr>
			</thead>
			<tbody>
				<!-- list데이터는 items에 넣었고 var에서 설정한 변수로 list데이터에서 꺼낸
				값을 사용하고 i의 값은 varStatus로 사용 -->
				<c:forEach var="notice" items="${nList}" varStatus="i">
					<tr>
						<td>${i.count}</td>
						<!-- notice/detail.kh?noticeNo=${notice.noticeNo}-->
						<c:url var="detailUrl" value="/notice/detail.kh">
							<c:param name="noticeNo" value="${notice.noticeNo}"></c:param>
						</c:url>
						<td><a href="${detailUrl}">${notice.noticeSubject}</a></td>
						<td>${notice.noticeWriter}</td>
						<td>
							<!-- 포맷태그로 연,월,일만 나오도록 표시 -->
							<fmt:formatDate pattern="yyyy-MM-dd" value="${notice.nCreateDate}"/>
							<!-- ${notice.nCreateDate} -->
						</td>
						<td>
							<c:if test="${!empty notice.noticeFileName }">0</c:if>
							<c:if test="${empty notice.noticeFileName }">X</c:if>
						</td>
						<!-- 
						<td>
							포맷태그로 자바스크립트보다 간단하게 숫자표현 가능
							<fmt:formatNumber pattern="##,###,###" value="123000"></fmt:formatNumber>
						</td> 
						-->
					</tr>	
				</c:forEach>
			</tbody>
			<tfoot>
				<tr align="center">
					<td colspan="5">
					<c:forEach begin="${pInfo.startNavi }" end="${pInfo.endNavi }" var="p">
						<!-- 
						아래코드보다 c코어태그를 사용함 : url관리 쉽게하도록
						<a href="/notice/list.kh?page=${p} }">${p }</a> &nbsp;	
						 -->
						 <c:url var="pageUrl" value="/notice/list.kh">
						 	<c:param name="page" value="${p}"></c:param>
						 </c:url>
						 <a href="${pageUrl }">${p }</a>&nbsp;				
					</c:forEach>
				</tr>
				<tr>
					<td colspan="4">
						<form action="/notice/search.kh" method="get">
							<select name="searchCondition">
								<option value="all">전체</option>
								<option value="writer">작성자</option>
								<option value="title">제목</option>
								<option value="content">내용</option>
							</select>
							<input type="text" name="searchKeyword" placeholder="검색어를 입력하세요">
							<input type="submit" value="검색">
						</form>
					</td>
					<td>
						<button>글쓰기</button>
					</td>
				</tr>
		</table>
	</body>
</html>