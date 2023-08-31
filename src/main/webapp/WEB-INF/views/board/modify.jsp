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
		<h1>게시글 수정</h1>
		<form action="/board/modify.kh" method="post" enctype="multipart/form-data">
			<!-- 수정할 때, 페이지 이동(redirect) 할 때 사용 -->
			<input type="hidden" name="boardNo" value="${board.boardNo }">
			<!-- 기존 업로드 파일 체크할 때 사용 -->
			<input type="hidden" name="boardFileName" value="${board.boardFileName }">
			<input type="hidden" name="boardFileRename" value="${board.boardFileRename }">
			<input type="hidden" name="boardFilePath" value="${board.boardFilePath }">
			<input type="hidden" name="boardFileLength" value="${board.boardFileLength }">
			<ul>
				<li>
					<label>제목</label>
					<input type="text" name="boardTitle" value="${board.boardTitle }">
				</li>
				<li>
					<label>작성자</label>
					<input type="text" name="boardWriter" value="${board.boardWriter }" >
				</li>
				<li>
					<label>내용</label>
					<textarea rows="4" cols="50" name="boardContent">${board.boardContent }</textarea>
				</li>
				<li>
					<label>첨부파일</label>
					<a href="../resources/buploadFiles/${board.boardFileRename }" download>${board.boardFileName }</a>
					<input type="file" name="uploadFile">
				</li>
			</ul>
			<div>
				<input type="submit" value="수정완료">
				<button type="button" onclick="showBoardList();">목록이동</button>
				<button type="button" onclick="javascript:history.go(-1);">뒤로가기</button>
			</div>	
		</form>
		<script>
			function showBoardList() {
				location.href="/board/list.kh";
			}
		</script>
	</body>
</html>