<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2> Model 객체에 대해 알아보기 </h2>
	
	<a href="/basic/response/test?age=30"> 테스트1 페이지로~</a>
	<!-- href를 받을 수 있는 메서드하나 만들러가자. 레스폰스 컨트롤러로 가자  -->
	

	<a href="/basic/response/test3">테스트3 페이지로~</a>


	
	<hr>
	
	
	
	<form action="/basic/response/test2">
		<input type="text" name="userId"> <br>
		<input type="text" name="userName"> <br>
		<input type="submit" value="확인">
	</form>


	
	
	





</body>
</html>