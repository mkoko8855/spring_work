<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%--c태그선언안되있으니 그냥 EL태그로쓰자 --%>
	<form action="${pageContext.request.contextPath}/score/selectOne"> <!-- 겟으로보내고싶으면 새로운 url을 적어. 사용자가 서브밋 누르면 이렇게 요청 보내겠다 라는 뜻 -->
		<p>
			# 조회 할 학번 : <input type="text" name="stuId" size="5">		
			<input type="submit" value="확인">
		</p>
	</form>


		<p style="color: red;">
			${msg}
		</p>









</body>
</html>