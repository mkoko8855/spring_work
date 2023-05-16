<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>게시글 등록</h2>
    <form method="post">  <!-- 폼태그에 액션이 없으니 이 url을 재사용하겠다~ 겟과 포스트를 이용하는구나~ -->
        <p>
            # 작성자: <input type="text" name="writer"> <br>
            # 제목: <input type="text" name="title"> <br>
            # 내용: <textarea rows="3" name="content"></textarea> <br>
            <input type="submit" value="등록"> 
        </p>
    </form>


</body>
</html>