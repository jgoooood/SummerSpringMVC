<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지사항 등록</title>
		<link rel="stylesheet" href="../resources/css/main.css"> 
	</head>
	<body>
		<h1>공지 등록</h1>
		<!-- 파일은 String으로 보낼수없음 :  enctype="multipart/form-data" 사용 
		파일을 잘라서 뭉텅이로 전송 함-->
		<form action="/notice/insert.kh" method="post" enctype="multipart/form-data">
			<ul>
				<li>
					<label>제목</label>
					<input type="text" name="noticeSubject">
				</li>
				<li>
					<label>작성자</label>
					<input type="text" name="noticeWriter">
				</li>
				<li>
					<label>내용</label>
					<textarea rows="4" cols="50" name="noticeContent"></textarea>
				</li>
				<li>
					<label>첨부파일</label>
					<!-- noticeFileName으로 name값을 넣지 못하는 이유 :
					String으로 받을 수 없고 uploadFile 객체로 받아서 변환작업이 필요-->
					<input type="file" name="uploadFile">
				</li>
			</ul>
			<div>
				<input type="submit" value="등록">
				<input type="reset" value="초기화">
			</div>	
		</form>
	</body>
</html>