<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#boardUpdateTable{margin: auto; border-collapse: collapse; border-left: hidden; border-right: hidden;}
	#boardUpdateTable tr td{padding: 5px;}
</style>
</head>
<body>
	<c:import url="../common/menubar.jsp"/>
	
	<h1 align="center">게시글 수정</h1>
	
	<!-- 첨부파일도 수정 할 수도 있으니 Multipart/form-data encType 지정 -->
	<form action="bupdate.bo" method="post" enctype="Multipart/form-data">
		<input type="hidden" name="page" value="${ page }">
		<input type="hidden" name="boardId" value="${ board.boardId }">
		<input type="hidden" name="renameFileName" value="${ board.renameFileName }">
		<!-- renameFileName: 가지고있는 파일, 저장되어있는! 오리지널 파일네임과 리네임파일네임의 존재로 파일이 있는지 알 수 있다-->
		<table id="boardUpdateTable" border="1">
			<tr>
				<th>제목</th>
				<td>
					<input type="text" size="80" name="boardTitle" value="${ board.boardTitle }">
				</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>
					<input type="text" readonly value="${ board.nickName }" style="background: lightgrey;">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="80" name="boardContent">${ board.boardContent }</textarea></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td><!-- 보안상 <input type="file" value="???">넣을 수 없음 벨류에 -->
					<!--reloadFile: 수정할 파일에 대한 정보를 가지게된다.(새로운 파일 업로드) -->
					<!--파일있을땐 오리진파일네임,사이즈/ 파일 첨부하지않고 넣었을 때 mt상태 객체는 있으나 파일이 없어서  -->
					<input type="file" name="reloadFile">
					<c:if test="${ !empty board.originalFileName }">
						<br>현재 업로드한 파일 : 
						<a href="${ contextPath }/resources/buploadFiles/${ board.renameFileName }" download="${ board.originalFileName }">
							${ board.originalFileName }
						</a>
					</c:if>
					<br>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="수정하기"> &nbsp;
					<c:url var="blist" value="blist.bo">
						<c:param name="page" value="${ page }"/>
					</c:url>
					<button type="button" onclick="location.href='${ blist }'">목록으로</button>
					<button type="button" onclick="location.href='javascript:history.go(-1);'">이전페이지로</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>