<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    <!-- 이제c태그열수잇음 -->
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<h2>학생들의 전체 성적 조회</h2>
	
	<c:forEach var="stu" items="${sList}">
		<p>
			학번: ${stu.stuId}, 이름: ${stu.stuName}, 국어: ${stu.kor}, 영어: ${stu.eng}, 수학: ${stu.math}, 총점: ${stu.total}, 평균: ${stu.average}
			<a href="<c:url value='/score/delete?stuId=${stu.stuId}'/>">[삭제]</a>
		</p>
	</c:forEach>
	
	
	<a href="<c:url value='/score/register'/>"> 다른 점수 등록하기 </a>
	
	
		<script>
		const msg = '${msg}'; <%--자바스크립트에서도 EL을 활용할 수 있다. 그러나 자바코드는 안돼. EL안에서도안돼. 전달하는용으로는 활용할 수 있어. --%>
		if(msg === 'delSuccess'){
			alert('삭제가 완료되었습니다.');
		}
		</script>






</body>
</html>