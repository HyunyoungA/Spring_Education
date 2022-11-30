<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- <%@ page session="false" %>세션 무료화 --%>
<html>
<head>
	<title>Home</title>
	<style>#tb{margin: auto; width: 700px;}</style>
</head>
<body>
	<c:import url="common/menubar.jsp"/>
	
	<h1 align="center">게시글 TOP 5 목록</h1>
	<table id="tb" border="1">
		<thead>
			<tr>
				<th>CUST_NO</th>
				<th>CUST_NM</th>
			</tr>
			<c:forEach var="blist" begin="0" end="2" items="${ blist }">
			<tr>
				<th>${blist.CUST_NO}</th>
				<th>${blist.CUST_NM}</th>
				
			</tr>
			</c:forEach>
		</thead>
		<tbody></tbody>
	</table>
	<script>
		function topList(){
			$.ajax({
				url: 'topList.bo',
				success: function(data){
					console.log(data);
					
					$tableBody = $('#tb tbody');
					$tableBody.html('');
					for(var i in data){
						var $tr = $('<tr>');
						var $bId = $('<td>').text(data[i].boardId);
						var $bTitle = $('<td>').text(data[i].boardTitle);
						var $bCreateDate = $('<td>').text(data[i].boardCreateDate);
						var $nickName = $('<td>').text(data[i].nickName);
						var $boardCount = $('<td>').text(data[i].boardCount);
						var $originalFileName = $('<td>').text(data[i].originalFileName);
						
						if(data[i].originalFileName != null){
							$originalFileName = $('<td>').text('◎');
						}
						
							$tr.append($tr);
							$tr.append($bId);
							$tr.append($bTitle);
							$tr.append($nickName);
							$tr.append($bCreateDate);
							$tr.append($boardCount);
							$tr.append($originalFileName);
							$tableBody.append($tr);
								
					}
				},
				error: function(data){
					console.log(data);
				}
			});
		}
		$(function(){
			topList();
			
			setInterval(function(){
				topList();
			}, 5000);
		});
	</script>
	<script>
		$(function(){//세션에 담아서 msg도 유지가 되고있는 상태이다->프론트단이다 JSP->java코드
			//백단과 프론트단이 같이 쓰이기 힘들다->돌긴하지만 생각한 로직대로 돌진않는단
			//url변경을 위해location.href="home.do" 사용
			var msg = '${msg}';
			if(msg != ''){
				alert(msg);
<%-- 				<% System.out.println("테스트"); session.removeAttribute("msg"); %> --%>
				location.href="home.do";
			}
		});
	
	</script>
</body>
</html>
