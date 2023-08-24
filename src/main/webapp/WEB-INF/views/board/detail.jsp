<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시글 등록</title>
		<link rel="stylesheet" href="../resources/css/main.css"> 
	</head>
	<body>
		<h1>공지 등록</h1>
			<ul>
				<li>
					<label>제목</label>
					<span>${board.boardTitle }</span>
				</li>
				<li>
					<label>작성자</label>
					<span>${board.boardWriter }</span>
				</li>
				<li>
					<label>내용</label>
					<p>${board.boardContent }</p>
				</li>
				<li>
					<label>첨부파일</label>
					<!-- img파일보임 -->
					<img alt="첨부파일" src="../resources/uploadFiles/${board.boardFileRename }">
					<!-- 다운로드는 실제Rename값으로, 사용자에게 보여지는 값은 FileName으로 지정 -->
					<a href="${board.boardFilePath }" download>${board.boardFileName }</a>
				</li>
			</ul>
			<div>
				<button type="button" onclick="showModifyPage();">수정하기</button>
				<button type="button" onclick="showModifyList();">목록이동</button>
				<button>삭제하기</button>
			</div>
			<!-- 댓글 등록 -->
			<form action="/board/addReply.kh" method="post">
				<table width="500" border="1">
					<tr>
						<td><textarea rows="3" cols="55"></textarea></td>
						<td><input type="submit" value="완료"></td>
					</tr>
				</table>
			</form>
			<!-- 댓글 목록 -->
			<table width="500" border="1">
				<tr>
					<td>일용자</td>
					<td>첫 댓글입니다</td>
					<td>2023-08-24</td>
					<td>
						<a href="#">수정하기</a>
						<a href="#">삭제하기</a>
					</td>
				</tr>
				<tr>
					<td>이용자</td>
					<td>첫 댓글입니다</td>
					<td>2023-08-24</td>
					<td>
						<a href="#">수정하기</a>
						<a href="#">삭제하기</a>
					</td>
				</tr>
				<tr>
					<td>삼용자</td>
					<td>첫 댓글입니다</td>
					<td>2023-08-24</td>
					<td>
						<a href="#">수정하기</a>
						<a href="#">삭제하기</a>
					</td>
				</tr>
			</table>
			<script>
				function showModifyList() {
					location.href="/board/list.kh";
				}
				function showModifyPage() {
					const boardNo = "${board.boardNo }";
					location.href="/board/modify.kh?boardNo="+boardNo;
				}
			</script>	
	</body>
</html>