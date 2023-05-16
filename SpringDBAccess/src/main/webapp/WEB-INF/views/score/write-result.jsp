<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- c:를사용하던 el을 사용하던 하자. /db 형식으로 적지 말자 -->
	<!-- 컨텍스트 루트를 제외하고 연결이 가능하다. 나중에 컨텍스트 루트 변경 가능성을 대비한다! -->
	<h2>점수 등록 성공!</h2>
	<a href="<c:url value='/score/register'/>">다른 점수 등록하기</a> <!-- 컨텍스트루트가 메롱으로바뀌던지 뭐로바뀌던지 컨텍스트루트를 불러온다. 그 바뀐 컨텍스트루를 항상 적용시킨다. -->
	<a href="${pageContext.request.contextPath}/score/list">점수 전체 조회하기</a> <!-- 이 페이지에서 사용되는 컨텍스트패스를 불러와. 그럼 나중에 뭘로바꾸던 항상 적용된다. 결국 위와 똑같은 내용. -->
	<a href="<c:url value='/score/search'/>">점수 개별 조회하기</a>
	
	<!-- 스코어 컨트롤러에서 받아주러 가자~ -->


</body>
</html>