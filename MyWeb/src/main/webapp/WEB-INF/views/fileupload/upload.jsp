<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	
	<!-- 파일 업로드는 기본적으로 post 방식 전송을 진행한다. 그리고 enctype(인코딩타입)을 "multipart/form-date"로 반드시 지정해야 한다. -->
	<form action="${pageContext.request.contextPath}/fileupload/upload_ok" method="post" enctype="multipart/form-data"> <!-- 이 요청을 보냄~ 참고로 파일은 기본적으로 전부 post여야만 한다. 그릐고 enctype="multipart/form-data" 필수로 적어줘야 한다. 그래야 첨부된 파일을 서버에 전송할 수 있다. 그리고 서블릿컨피그로가서도 빈 등록해주자 -->
		파일 선택 : <input type="file" name="file"> <br> 
				 <input type="submit" value="전송">
	</form>
	
	<hr>
	
	<form action="${pageContext.request.contextPath}/fileupload/upload_ok2" method="post" enctype="multipart/form-data">
		파일 선택 : <input type="file" multiple="multiple" name="files"> <br>  <!-- 멀티플속성은 파일을 여러개를 첨부가 가능하다. -->
				 <input type="submit" value="전송">
	</form>
	
	
	<hr>
	<!-- 아까는 타입="file" 하나로 여러개를 받았는데 이번엔 3개 각각 받아보자. 파일3개 선택지를 써주고 업로드컨트롤러로가자  -->
	<form action="${pageContext.request.contextPath}/fileupload/upload_ok3" method="post" enctype="multipart/form-data">
		파일 선택 : <input type="file" name="file"> <br>
		파일 선택 : <input type="file" name="file"> <br> 
		파일 선택 : <input type="file" name="file"> <br> 
				 <input type="submit" value="전송">
	</form>
	
	
	
	
	


</body>
</html>