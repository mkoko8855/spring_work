<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<!-- 겟과 포스트를 구분해서 리퀘스트가 어떻게 이뤄지나 보자 -->

	<h2>Request 컨트롤러를 이용한 요청 처리 작업 중~ </h2>
	
	<form action="/basic/request/basic01">
		<input type="submit" value="GET 요청!">
	</form>


	<form action="/basic/request/basic01" method="post">
		<input type="submit" value="POST 요청!">
	</form>

	<!-- 작성 후, RequestController.java 파일로 가서 (표시)메서드 적어주자 -->
	
	





</body>
</html>