<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지사항 수정</title>
		<link rel="stylesheet" href="../resources/css/main.css"> 
	</head>
	<body>
		<h1>공지 수정</h1>
		<!-- 파일은 String으로 보낼수없음 :  enctype="multipart/form-data" 사용 
		파일을 잘라서 뭉텅이로 전송 함-->
		<form action="/notice/modify.kh" method="post" enctype="multipart/form-data">
			<!-- 수정할 때, 페이지 이동(redirect) 할 때 사용 -->
			<input type="hidden" name="noticeNo" value="${notice.noticeNo }">
			<!-- 기존 업로드 파일 체크할 때 사용 -->
			<input type="hidden" name="noticeFileName" value="${notice.noticeFileName }">
			<input type="hidden" name="noticeFileRename" value="${notice.noticeFileRename }">
			<input type="hidden" name="noticeFilePath" value="${notice.noticeFilePath }">
			<input type="hidden" name="noticeFileLength" value="${notice.noticeFileLength }">
			<ul>
				<li>
					<label>제목</label>
					<input type="text" name="noticeSubject" value="${notice.noticeSubject }">
				</li>
				<li>
					<label>작성자</label>
					<input type="text" name="noticeWriter" value="${notice.noticeWriter }" >
				</li>
				<li>
					<label>내용</label>
					<textarea rows="4" cols="50" name="noticeContent">${notice.noticeContent }</textarea>
				</li>
				<li>
					<label>첨부파일</label>
					<a href="../resources/uploadFiles/${notice.noticeFileRename }" download>${notice.noticeFileName }</a>
					<input type="file" name="uploadFile">
				</li>
			</ul>
			<div>
				<input type="submit" value="수정완료">
			</div>	
		</form>
	</body>
</html>