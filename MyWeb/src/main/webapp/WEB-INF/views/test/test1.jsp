<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	
	<!-- 사용자에게 입력받고 비동기방식으로 처리해보자  -->	
	
	이름: <input type="text" name="name"> <br>
	
	나이: <input type="text" name="age">  <br>
	
	취미: 
	<input type="checkbox" name="hobby" value="soccer"> 축구
	<input type="checkbox" name="hobby" value="music"> 음악감상
	<input type="checkbox" name="hobby" value="game"> 게임	
	
	
	
	<button id="send">요청보내기!</button>
	
	
	<script>
	
		const $sendBtn = document.getElementById('send');
		$sendBtn.onclick = function(e){
			
			const name = document.querySelector('input[name=name]').value;
			const age = document.querySelector('input[name=age]').value;
			const hobby = document.querySelectorAll('input[name=hobby]');
			
			const arr = []; //비어있는 배열. hobby가 nodelist니까 
			[...hobby].forEach($check => {
				//$check라는 변수로 하나씩 들어올때마다
				if($check.checked){
					arr.push($check.value);
				}
			});
			
			
			console.log(name);
			console.log(age);
			console.log(arr);
			//이 console.log들을 JSON으로 변환해서 자바쪽으로 보내줄거임
			
			
			
			
			//# http 요청을 서버로 보내는 방법
			//1. XMLHttpRequest라는 객체를 생성하자
			const xhr = new XMLHttpRequest();
			//2. 객체를 생성했다면, 요청을 어떤 방식으로 보낼지 얘기해주자
			//요청방식에는 GET방식이 있고 POST방식이 있고 PUT방식이 있고 DELETE방식이 있다.
			//GET은 조회할 때, POST는 등록할 때, PUT은 수정할 떄, DELETE는 삭제할 때 많이 사용한다.
			
			//일단 GET방식으로 요청 넣어보자
			xhr.open('POST', '${pageContext.request.contextPath}/rest/getObject'); //GET다음 URL적어주면됨
			
			
			
			
			//이 console.log들을 JSON으로 변환해서 자바쪽으로 보내줄거임. 계속해보자.
			//3. 즉, 서버로 전송할 데이터를 제작한다.
			//데이터의 형식은 JSON 이여야 한다.
			//일단 객체화부터.
			const data = {
					//자바스크립트의 객체는 키와 밸류로 이루어져있으니
					'name' : name,
					'age' : age,
					'hobby' : arr
			}; //그러나 아직 이 객체들은 JSON 이 아니라 자바스크립트 객체이니까 
			   //이제 JSON으로 바꿔주자
			   
			//JS -> JSON으로 변경하는 메서드의 이름은 JSON.stringify(arg);    ()안에는 변환하고자 하는 것을 써주자
			const sendData = JSON.stringify(data);
			
			//이제 sendData를 보내 줄 것이다.
			
			//이제 전송할 데이터가 무슨 형태인지를 요청 헤더에 지정을 해줘야 한다.
			//즉, 요청을 받는서버 입장에서는 넘어오는 데이터가 뭔지 알아야 판단하겠지
			xhr.setRequestHeader('content-type', 'application/json');
			//즉, 전송하고자 하는 내용의 타입은 JSON타입이라고 말을 해 주는 것이다.
			
			
			//4. 준비됐으니 서버에 요청 전송하자
			xhr.send(sendData);
			
			
			//5. 요청을 보낸 다음에 응답 정보를 확인하자
			xhr.onload = function(){  //로딩이 완료가 되었다면?
				//응답코드를 확인할것이다
				console.log(xhr.status); //그럼 응답상태 코드를 확인할 수 있다.
				
				//응답 데이터를 확인하고싶다면
				console.log(xhr.response); //응답된 데이터도 확인할 수 있다.
			}
			
		}
	</script>
	
	
	
	
	







</body>
</html>