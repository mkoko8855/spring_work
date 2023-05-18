<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>




<%@ include file="../include/header.jsp"%>
<section>
	<div class="container">
		<div class="row">
			<div class="col-lg-6 col-md-9 col-sm-12 join-form">
				<div class="titlebox">회원가입</div>
				<form action="">
					<div class="form-group">
						<!--사용자클래스선언-->
						<label for="id">아이디</label>
						<div class="input-group">
							<!--input2탭의 input-addon을 가져온다 -->
							<input type="text" class="form-control" id="userId"
								placeholder="아이디를 (영문포함 4~12자 이상)">
							<div class="input-group-addon">
								<button type="button" class="btn btn-primary" id="idCheckBtn">아이디중복체크</button>
							</div>
						</div>
						<span id="msgId"></span>
						<!--자바스크립트에서 추가-->
					</div>
					<div class="form-group">
						<!--기본 폼그룹을 가져온다-->
						<label for="password">비밀번호</label> <input type="password"
							class="form-control" id="userPw"
							placeholder="비밀번호 (영 대/소문자, 숫자 조합 8~16자 이상)"> <span
							id="msgPw"></span>
						<!--자바스크립트에서 추가-->
					</div>
					<div class="form-group">
						<label for="password-confrim">비밀번호 확인</label> <input
							type="password" class="form-control" id="pwConfirm"
							placeholder="비밀번호를 확인해주세요."> <span id="msgPw-c"></span>
						<!--자바스크립트에서 추가-->
					</div>
					<div class="form-group">
						<label for="name">이름</label> <input type="text"
							class="form-control" id="userName" placeholder="이름을 입력하세요.">
					</div>
					<!--input2탭의 input-addon을 가져온다 -->
					<div class="form-group">
						<label for="hp">휴대폰번호</label>
						<div class="input-group">
							<select class="form-control phone1" id="userPhone1">
								<option>010</option>
								<option>011</option>
								<option>017</option>
								<option>018</option>
							</select> <input type="text" class="form-control phone2" id="userPhone2"
								placeholder="휴대폰번호를 입력하세요.">
						</div>
					</div>
					<div class="form-group email-form">
						<label for="email">이메일</label><br>
						<div class="input-group">
							<input type="text" class="form-control" id="userEmail1"
								placeholder="이메일"> <select class="form-control"
								id="userEmail2">
								<option>@naver.com</option>
								<option>@daum.net</option>
								<option>@gmail.com</option>
								<option>@hanmail.com</option>
								<option>@yahoo.co.kr</option>
							</select>
							<div class="input-group-addon">
								<button type="button" id="mail-check-btn"
									class="btn btn-primary">이메일 인증</button>
							</div>
						</div>
					</div>
					<div class="mail-check-box">
						<input type="text" class="form-control mail-check-input"
							placeholder="인증번호 6자리를 입력하세요." maxlength="6" disabled="disabled">
						<span id="mail-check-warn"></span>
					</div>


					<!--readonly 속성 추가시 자동으로 블락-->
					<div class="form-group">
						<label for="addr-num">주소</label>
						<div class="input-group">
							<input type="text" class="form-control" id="addrZipNum"
								placeholder="우편번호" readonly>
							<div class="input-group-addon">
								<button type="button" class="btn btn-primary">주소찾기</button>
							</div>
						</div>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="addrBasic"
							placeholder="기본주소">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="addrDetail"
							placeholder="상세주소">
					</div>

					<!--button탭에 들어가서 버튼종류를 확인한다-->
					<div class="form-group">
						<button type="button" class="btn btn-lg btn-success btn-block">회원가입</button>
					</div>

					<div class="form-group">
						<button type="button" class="btn btn-lg btn-info btn-block">로그인</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>

<%@ include file="../include/footer.jsp"%>






<script>

	//아이디 중복 체크
	document.getElementById('idCheckBtn').onclick = function(){
		
		//클릭한다면, 입력한게 뭔지 가져와야지
		const userId = document.getElementById('userId').value; //userId의 value를 가져와!
		
		//그리고 나서 체크한번해주고
		if(userId === ''){
			alert('아이디는 필수 값 입니다.')
			ruturn; //이벤트 강제 종료
		}
		
		//이제 비동기방식을 이용해서, 사용자가 작성한 것을 서버로 보낼 예정이다.
		//서버에서는 DB와 연동해서 중복이 됐는지 안됐는지 판단해서 뷰페이지가 아닌, 지금 userJoin이라는 화면 자체에 문자열을 보내준다.
		
		//즉, 아이디 중복확인 비동기 요청 준비
		/* const xhr = new XMLHttpRequest();
		
		//서버 요청 정보 설정
		xhr.open('POST', '${pageContext.request.contextPath}/user/idCheck');
		
		
		//요청 정보를 헤더에 설정
		xhr.setRequestHeader('context-type', 'text/plain'); //객체가 아니라 문자 하나만 보낼꺼니 application이 아니라 text/plainㄱㄱ
		
		
		//보내자
		xhr.send(userId);
		
		
		//이제 유저컨트롤러로가자
		
		
		
		//다적고 다시와서 요청에 대한 응답을 확인.
		xhr.onload = () => {
			console.log(xhr.status);
			console.log(xhr.response);
		} */
		
		
		
		/*
		
		#fetch API : 자바스크립트에서 제공하는 비동기 통신 함수.
		즉, 함수를 이용해서 비동기 통신을 하는 방식이다.
		쉽다!
		
		- Promise 객체를 자동으로 리턴하여 손쉽게 통신의 응답데이터를 소비할 수 있게 해 준다.
		(Promise: 비동기 통신의 순서를 보장하는 문법이다)
		
		비동기는 우리가 작성한 순서대로 진행되지 않는다.
		동기방식은 순서가 정해져있지만 화면은 고정되어있는 상태에서 사용자가 뭘 누르느냐에 따르기 떄문에
		비동기 통신을 중복한다면 promise방식을 사용하면, 비동기도 순서를 정해줄 수 있다.
		
		패치는 Promise를 자동으로 지원한다.
		
		fetch함수가 리턴하는 Promise 객체는 단순한 응답 JSON 데이터 뿐 아니라
		전체적이고, 포괄적인 응답 정보를 가지고 있다.
		
		따라서 서버가 응답한 여러 정보 중,
		JSON 데이터만 소비하려면 JSON() 이라는 메서드를 사용한다.
		
		만약 문자열 데이터라면 text() 메서드를 사용한다.
		*/
		
		//fetch('url', {요청 관련 정보 객체})
		
		/* fetch('${pageContext.request.contextPath}/user/idCheck', {
			method: 'post',
			headers: {
				'Content-Type' : 'application/json'
			},
			body: userId
			
		}).then(res => {  //then은 Promise 객체의 상태가 요청 성공 일 시, 데이터 후속 처리 진행한다는 의미다.
			
			//변수 대충 res로 선언하면, 응답에 대한 정보가 res로들어감. 즉, 패치라는 함수의 결과가 res의 매개값으로 전달이 된다. 얘를 꺼내보면 확인가능.
			
			//즉, fetch 함수를 통해 비동기 통신이 실행되고,
			//요청이 완료 된 후 then() 이라는 함수의 매개값으로 응답에 관련된
			//함수를 콜백 방식으로 전달한다. 그리고 나서 실행하라 이라는 뜻이다(then의뜻).
			//함수의 매개변수를 선언하면 해당 매개변수로 응답에 관련된 전반적인 정보를 가진 응답 헤더 파일이 리턴이 된다(위에 feat함수는 전체적이고 포괄적인 정보라 했으니).
			console.log(res);
			//`console.log(res.text());
			return res.text()
		}).then(data => {
			console.log(data);
		}); */
		

		const reqObj = {
				method: 'post',
				headers: {
					'Content-Type' : 'text/plain'
				},
				body: userId
		};
		
		//비동기 요청 보내기
		fetch('${pageContext.request.contextPath}/user/idCheck', reqObj)
		//요청에 성공했다면 then함수를 호출.
		.then(res => res.text()) //요청 완료 후 응답 정보에서 텍스트만 빼기
			.then(data => { //프로미스객체에서 데이터를 전달받아서 데이터를한번 찍어보겠다
				
				//즉즉즉즉, 패치실행후 성공했으면 then, 그 다음에 응답 객체 받아서 text만 뺀 Promise 객체로부터 data를 전달 받았다는 순서이다.

				
				if(data === 'ok'){
					//더 이상 아이디를 작성할 수 없도록 막아주겠다.
					document.getElementById('userId').setAttribute('readonly', true);
					//그리고 더 이상 버튼을 누를 수 없도록 비활성화 하겠다.
					document.getElementById('idCheckBtn').setAttribute('disabled', true);
					//그리고 메세지 남기기
					document.getElementById('msgId').textContent = '사용 가능한 아이디 입니다.'
				} else { //엘스는 아이디가 중복이잖아.
					document.getElementById('msgId').textContent = '중복 된 아이디 입니다.'
				}
			}); 
		
	} //아이디 중복 확인 끝!
	
	
	
	
	//인증 번호를 이메일로 전송시켜보자
	//사용자는 이메일을 인증했을꺼고 전송 버튼을 누를 것이다!
	document.getElementById('mail-check-btn').onclick = function(){
		//이메일 주소를 만들어주자
		const email = document.getElementById('userEmail1').value + document.getElementById('userEmail2').value;  //유저 이메일1, 이메일2 를 불러올것이다. 그리고 붙이자. 그럼 완성된 이메일 주소겠지
		console.log('완성된 email: ' + email);
	
		fetch('${pageContext.request.contextPath}/user/mailCheck?email=' + email); //패치를 이용하여 GET방식으로 이메일을 넘기자! (아까 포스트방식 해봤으니까)
 		//겟방식은 패치함수가 이게 끝이다. 
 		//여기로 또 프로미스가 리턴이 되겠지. then으로 값을 처리하면 되겠지.
		.then()
	}

	
	
	
	
	
		
	
	











	/*아이디 형식 검사 스크립트*/
	var id = document.getElementById("userId");
	id.onkeyup = function() { 
		/*자바스크립트의 정규표현식 입니다*/
		/*정규표현식 : 문자열 내의 특정 문자 조합을 찾기 위한 패턴입니다.
		            특정 규칙이 있는 문자열 집합을 규칙을 직접 지정하여 탐색하게 해 주는
		            메타 문자 입니다.
		*/
		/*test메서드를 통해 비교하며, 매칭되면 true, 아니면 false반*/
		var regex = /^[A-Za-z0-9+]{4,12}$/; /*정규표현식의 시작과 끝.  $이 끝이다. []안에 있는 것이 찾고자 하는 문자의 규칙이다. 그 문자는 4자이상 12자 이하 로 설정한다. 구글에 자바스크립트 id 정규표현식 이런식으로 쳐서 가져다가 쓰면 됨*/
		if (regex.test(document.getElementById("userId").value)) { /*검증하고자하는 값을 넣어주면 됨. 맞으면 트루겠지*/
			document.getElementById("userId").style.borderColor = "green";
			document.getElementById("msgId").innerHTML = "아이디중복체크는 필수 입니다";

		} else {
			document.getElementById("userId").style.borderColor = "red";
			document.getElementById("msgId").innerHTML = "부적합한 아이디 입니다.";
		}
	}
	/*비밀번호 형식 검사 스크립트*/
	var pw = document.getElementById("userPw");
	pw.onkeyup = function() {
		var regex = /^[A-Za-z0-9+]{8,16}$/;
		if (regex.test(document.getElementById("userPw").value)) {
			document.getElementById("userPw").style.borderColor = "green";
			document.getElementById("msgPw").innerHTML = "사용가능합니다";
		} else {
			document.getElementById("userPw").style.borderColor = "red";
			document.getElementById("msgPw").innerHTML = "불가능합니다.";
		}
	}
	/*비밀번호 확인검사*/
	var pwConfirm = document.getElementById("pwConfirm");
	pwConfirm.onkeyup = function() {
		var regex = /^[A-Za-z0-9+]{8,16}$/;
		if (document.getElementById("pwConfirm").value == document
				.getElementById("userPw").value) {
			document.getElementById("pwConfirm").style.borderColor = "green";
			document.getElementById("msgPw-c").innerHTML = "비밀번호가 일치합니다";
		} else {
			document.getElementById("pwConfirm").style.borderColor = "red";
			document.getElementById("msgPw-c").innerHTML = "비밀번호 확인란을 확인하세요";
		}
	}
</script>
