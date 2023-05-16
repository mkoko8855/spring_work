<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>




	<h2> 여기는 test.jsp 페이지 입니다.</h2>
	<h3> 모델이 전달 됐는지 확인을 해봅시다</h3>
	
	<p>
		지정된 별명은 ${nick} 입니다. 그리고 나이는 ${age}세 입니다.
	</p>

	<%--출력 결과 : 
	여기는 test.jsp 페이지 입니다.
	모델이 전달 됐는지 확인을 해봅시다
	지정된 별명은 멍멍이 입니다. 그리고 나이는 30세 입니다. --%> 


</body>
</html>