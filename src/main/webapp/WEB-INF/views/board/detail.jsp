<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			<c:url var="delBoardUrl" value="/board/delete.kh">
				<c:param name="boardNo" value="${board.boardNo }"></c:param>
				<c:param name="boardWriter" value="${board.boardWriter }"></c:param>
			</c:url>
			<c:url var="modifyUrl" value="/board/modify.kh">
				<c:param name="boardNo" value="${board.boardNo }"></c:param>
			</c:url>
			<div>
				<!-- 로그인한 아이디와 작성자가 일치해야만 수정,삭제버튼 보이게 함 -->
				<c:if test="${board.boardWriter eq memberId }">
					<button type="button" onclick="showModifyPage('${modifyUrl}');">수정하기</button>
					<button type="button" onclick="deleteBoard('${delBoardUrl}');">삭제하기</button>
				</c:if>
				<button type="button" onclick="showModifyList();">목록이동</button>
				<button type="button" onclick="javascript:history.go(-1);">뒤로가기</button>
			</div>
			<!-- 댓글 등록 -->
			<form action="/reply/add.kh" method="post">
			<!-- 부모게시글의 번호를 넘겨줘야함 -->
				<input type="hidden" name="refBoardNo" value="${board.boardNo }">
				<table width="500" border="1">
					<tr>
						<td><textarea rows="3" cols="55" name="replyContent"></textarea></td>
						<td><input type="submit" value="완료"></td>
					</tr>
				</table>
			</form>
			<!-- 댓글 목록 -->
			<table width="500" border="1">
				<c:forEach items="${rList }" var="rList">
					<tr>
						<td>${rList.replyWriter}</td>
						<td>${rList.replyContent}</td>
						<td>${rList.rCreateDate}</td>
						<td>
							<a href="javascript:void(0);" onclick="showModifyForm(this);">수정하기</a>
<%-- 							<a href="javascript:void(0);" onclick="showModifyForm(this, '${rList.replyContent}');">수정하기</a> --%>
							<c:url var="delUrl" value="/reply/delete.kh">
								<!-- url태그로 쿼리스트링 만들어줌 -->
								<!-- 지우려는 댓글 작성자와 댓글번호를 특정해서 삭제-->
								<!-- 댓글작성자는 로그인한 아이디와 일치해야만 삭제 가능하도록 함 -->
								<c:param name="replyNo" value="${rList.replyNo }"></c:param>
								<c:param name="replyWriter" value="${rList.replyWriter }"></c:param>
								<!-- 성공하면 detail로 가기위해서 필요한 boardNo 세팅 -->
								<c:param name="refBoardNo" value="${rList.refBoardNo }"></c:param>
							</c:url>
							<a href="javascript:void(0);" onclick="deleteReply('${delUrl}');">삭제하기</a>
						</td>
					</tr>
					<tr id="replyModifyForm" style="display:none;">
						<!-- 방법1 form태그 이용
						<form action="/reply/update.kh" method="post">
							<input type="hidden" name="replyNo" value="${rList.replyNo }">
							<input type="hidden" name="refBoardNo" value="${rList.refBoardNo }">
							<td colspan="3"><input type="text" size="50" name="replyContent" value="${rList.replyContent }"></td>
							<td><input type="submit" value="완료"></td>
						</form>-->   
						<!-- 방법2 : dom프로그래밍이용 form태그 만들기-->
						<td colspan="3"><input id="replyContent" type="text" size="50" name="replyContent" value="${rList.replyContent }"></td>
						<!-- this는 완료버튼을 의미->수정할 값을 넘겨서 obj로 받는 것임-->
						<td><input type="button" onclick="replyModify(this,'${rList.replyNo}', '${rList.refBoardNo}');" value="완료"></td>
					</tr>
				</c:forEach>
			</table>
			<script>
				// 게시글
				function deleteBoard(delBoardUrl) {
					//alert(delBoardUrl);
					location.href = delBoardUrl;
				}
				
				function showModifyPage(modifyUrl) {
					//const boardNo = "${board.boardNo }";
					location.href=modifyUrl;
				}
				function showModifyList() {
					location.href="/board/list.kh";
				}
				
				//obj는 누르는 버튼을 this로 받을 수 있는 변수임
				function replyModify(obj, replyNo, refBoardNo) {
					//dom프로그래밍을 이용하는 방법
// 					<form action="/reply/update.kh" method="post">
// 							<input type="hidden" name="replyNo" value="달러{rList.replyNo }">
// 							<input type="hidden" name="refBoardNo" value="달러{rList.refBoardNo }">
// 							<td colspan="3"><input type="text" size="50" name="replyContent" value="달러{rList.replyContent }"></td>
// 							<td><input type="submit" value="완료"></td>
// 					</form>
					const form = document.createElement("form");
					form.action = "/reply/update.kh";
					form.method = "post";
					const input = document.createElement("input");
					input.type = "hidden";
					input.value = replyNo;
					input.name = "replyNo";
					const input2 = document.createElement("input");
					input2.type = "hidden";
					input2.value = refBoardNo;
					input2.name = "refBoardNo";
					const input3 = document.createElement("input");
					input3.type= "text";
					//***이 부분 코드를 this로 넘겨주고 obj변수로 받을 수 있게 수정해야함***** 
					//this를 이용해서 원하는 노드 찾기 -> 수정안하면 같은 내용으로만 수정
					//부모태그 td를 parentElement로 가져옴
					//부모태그 td의 이전 td태그를 previousElementSibling로 가져옴 
					//선택한 td태그의 자식태그인 input태그를 childNodes[0] 또는 children[0]으로 선택
					//value로 수정될 값을 가져옴
					//input3.value = document.querySelector("#replyContent").value;
					input3.value = obj.parentElement.previousElementSibling.childNodes[0].value;
					//input3.value = obj.parentElement.previousElementSibling.children[0].value;
					input3.name = "replyContent";
					//input타입 3개 붙이기
					form.appendChild(input);
					form.appendChild(input2);
					form.appendChild(input3);
					
					document.body.appendChild(form);
					form.submit();
				}
				
				function showModifyForm(obj) {
					obj.parentElement.parentElement.nextElementSibling.style.display="";	
				}
//				function showModifyForm(obj, replyContent) {
					//방법2 : DOM프로그래밍을 이용해 아래 구문을 생성
					//동적으로 커스터마이징할 때 좀더 유용한 방법
// 					<tr id="replyModifyForm" style="display:none;">
// 						<td colspan="3"><input type="text" size="50" value="dollar{rList.replyContent }"></td>
// 						<td><input type="button" value="완료"></td>
// 					</tr>
					//input태그 생성
// 					const trTag = document.createElement("tr");
// 					const tdTag1 = document.createElement("td");
// 					tdTag1.colSpan = 3;
// 					const inputTag1 = document.createElement("input");
// 					inputTag1.type="text";
// 					inputTag1.size=50;
// 					inputTag1.value=replyContent;
// 					tdTag1.appendChild(inputTag1); //td태그와 input태그를 부모자식 관계로 설정해줌
// 					//button태그 생성
// 					const tdTag2 = document.createElement("td");
// 					const inputTag2 = document.createElement("input");
// 					inputTag2.type="button";
// 					inputTag2.value="완료";
// 					tdTag2.appendChild(inputTag2);
// 					trTag.appendChild(tdTag1);
// 					trTag.appendChild(tdTag2);
// 					console.log(trTag);
					//클릭한 a를 포함하고 있는 tr 다음에 수정폼이 있는 tr 추가하기
					//const prevTrTag = obj.parentElement.parentElement;
					//댓글수정창이 없으면 수정창 나오도록 동작->중복으로 나오지 않음
					/*if(!prevTrTag.nextElementSibling == null || !prevTrTag.nextElementSibling.querySelector("input")) { 
						prevTrTag.parentNode.insertBefore(trTag, prevTrTag.nextSibling);						
					}*/
					//prevTrTag.parentNode.insertBefore(trTag, prevTrTag.nextSibling);						
					
					//*******************************************************************					
					//방법1 : HTML태그, display:none 사용
					//document.querySelector("#replyModifyForm").style.display="";
					/*내가클릭한 태그(obj:a태그) :<a href="javascript:void(0);" onclick="showModifyForm(this);">수정하기</a>
					 arrow : obj의 부모태그(td태그)
					 <td>
					 	<a href="javascript:void(0);" onclick="showModifyForm(this);">수정하기</a>
						<a href="#">삭제하기</a>
					</td>
					arrow : obj의 부모태그(td태그)의 부모태그(tr태그)
					<tr>
						<td>dollar{rList.replyWriter}</td>
						<td>dollar{rList.replyContent}</td>
						<td>dollar{rList.rCreateDate}</td>
						<td>
							<a href="javascript:void(0);" onclick="showModifyForm(this);">수정하기</a>
							<a href="#">삭제하기</a>
						</td>
					</tr>
					arrow : obj의 부모태그(td태그)의 부모태그(tr태그)의 그 다음 태그
					<tr id="replyModifyForm" style="display:none;">
						<td colspan="3"><input type="text" size="50" value="dollar{rList.replyContent }"></td>
						<td><input type="button" value="완료"></td>
					</tr>
					nextElementSibling으로 숨겨진 replyModifyForm을 보이도록 style.display을 활성화해줌*/
					//obj.parentElement.parentElement.nextElementSibling.style.display="";
					
				//}
				function deleteReply(url) {
					//DELETE FROM REPLY_TBL WHERE REPLY_NO = 샵{replyNo } AND R_STATUS = 'Y';
					//UPDATE REPLY_TBL SET R_STATUS = 'N' WHERE REPLY_NO = 샵{replyNo };
					//alert(url); reply/delete.kh?replyNo=1 -> 삭제시킬 댓글 alert창에 뜸
					location.href = url;
				}
			</script>	
	</body>
</html>