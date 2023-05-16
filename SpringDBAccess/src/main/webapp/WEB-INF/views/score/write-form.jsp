<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>



	<h2>시험 점수 등록</h2>
	<form method="post"> <%--폼태그의 액션이 없으면 화면 보여주는 URL을 재활용 하겠다라는 얘기다.  --%>
		<p>
			# 이름: <input type="text" name="stuName"> <br> # 국어: <input
				type="text" name="kor"> <br> # 영어: <input type="text"
				name="eng"> <br> # 수학: <input type="text" name="math">
			<br> <input type="submit" value="확인">
		</p>
	</form>



</body>
</html>