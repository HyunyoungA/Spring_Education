<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<h1 align="center">Spring에서의 aJax 사용</h1>
	
	<ol>
		<li>
			서버 쪽 컨트롤러로 값 보내기
			<button id="test1">테스트</button>
			<script>
				$('#test1').on('click', function(){
					$.ajax({
						url: 'test1.do',
						data: {name: '강건강', age:20},
						type: 'post',
						success: function(data){
							console.log(data);
							if(data == 'ok'){
								alert('전송성공');
							} else{
								alert('전송실패');
							}
						},
						error: function(data){
							console.log(data);
						}
					});
				});
			</script>
		</li>
		<li>
			컨트롤러에서 리턴하는 JSON객체 받아 출력하기
			<button id="test2">테스트</button>
			<div id="d2"></div>
			<script>
				$('#test2').click(function(){
					$.ajax({
						url: 'test2.do',
// 						dataType: 'json', //data = JSON.parse(data); 두가지 인코딩 방법!
						success: function(data){
// 							data = JSON.parse(data);
// 							console.log(data);
// 							//String으로 들어가는 곳에 디코딩해줘야 한다. 띄어쓰기에 +가 들어간다 : url방식으로 +가 들어가서 이걸 띄어쓰기로 바꿔줘야한다.모든 + 를 띄어쓰기로 변경 replaceAll()
// 							$('#d2').html('번호: ' + data.no
// 										+ '<br>제목 : ' + data.title	
// 										+ '<br>작성자 : ' + decodeURIComponent(data.writer)
// 										+ '<br>내용 : ' + decodeURIComponent(data.content.replaceAll('+', ' ')));
							console.log(data);
							$('#d2').html('번호: ' + data.no
										+ '<br>제목 : ' + data.title	
										+ '<br>작성자 : ' + data.writer
										+ '<br>내용 : ' + data.content);
						},
						error: function(data){
							console.log(data);
						}
					});
				});
			</script>
			
			
			
			
			
		</li>
	</ol>
	
</body>
</html>
