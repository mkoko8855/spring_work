<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>




<%@ include file="../include/header.jsp"%>
<section>
	<div class="container">
		<div class="row">
			<div class="col-lg-6 col-md-9 col-sm-12 join-form">
				<div class="titlebox">회원가입</div>
				<form action="${pageContext.request.contextPath}/user/join" method="post" name="joinForm">
					<div class="form-group">
						<!--사용자클래스선언-->
						<label for="id">아이디</label>
						<div class="input-group">
							<!--input2탭의 input-addon을 가져온다 -->
							<input type="text" name="userId" class="form-control" id="userId"
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
						<label for="password" >비밀번호</label> <input type="password" name="userPw"
							class="form-control" id="userPw"
							placeholder="비밀번호 (영 대/소문자, 숫자 조합 8~16자 이상)"> <span
							id="msgPw"></span>
						<!--자바스크립트에서 추가-->
					</div>
					<div class="form-group">
						<label for="password-confrim">비밀번호 확인</label> 
						<input
							type="password" class="form-control" id="pwConfirm"
							placeholder="비밀번호를 확인해주세요."> <span id="msgPw-c"></span>
						<!--자바스크립트에서 추가-->
					</div>
					<div class="form-group">
						<label for="name">이름</label> <input type="text" name="userName"
							class="form-control" id="userName" placeholder="이름을 입력하세요.">
					</div>
					<!--input2탭의 input-addon을 가져온다 -->
					<div class="form-group">
						<label for="hp">휴대폰번호</label>
						<div class="input-group">
							<select name="userPhone1" class="form-control phone1" id="userPhone1">
								<option>010</option>
								<option>011</option>
								<option>017</option>
								<option>018</option>
							</select> <input type="text" name="userPhone2" class="form-control phone2" id="userPhone2"
								placeholder="휴대폰번호를 입력하세요.">
						</div>
					</div>
					<div class="form-group email-form">
						<label for="email">이메일</label><br>
						<div class="input-group">
							<input type="text" name="userEmail1" class="form-control" id="userEmail1"
								placeholder="이메일"> <select name="userEamil2" class="form-control"
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
							<input type="text" name="addrZipNum" class="form-control" id="addrZipNum"
								placeholder="우편번호" readonly>
							<div class="input-group-addon">
								<button type="button" class="btn btn-primary" onclick="searchAddress()">주소찾기</button>
							</div>
						</div>
					</div>
					<div class="form-group">
						<input type="text" name="addrBasic"  class="form-control" id="addrBasic"
							placeholder="기본주소">
					</div>
					<div class="form-group">
						<input type="text" name="addrDetail" class="form-control" id="addrDetail"
							placeholder="상세주소">
					</div>

					<!--button탭에 들어가서 버튼종류를 확인한다-->
					<div class="form-group">
						<button type="button" id="joinBtn" class="btn btn-lg btn-success btn-block">회원가입</button>
					</div>

					<div class="form-group">
						<button type="button" id="loginBtn" class="btn btn-lg btn-info btn-block">로그인</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>

<%@ include file="../include/footer.jsp"%>





<!-- 다음API에서 이 코드를 끌고오자 -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	
	
	
	<script>
	let code = '';  //이메일 전송 인증번호 저장을 위한 변수

	let idFlag, pwFlag; //이건왜? 정규표현식 유효성 검사 여부를 판단하기 위한 변수




	//아이디 중복 체크
	document.getElementById('idCheckBtn').onclick = function(){
		
		//클릭한다면, 입력한게 뭔지 가져와야지
		const userId = document.getElementById('userId').value; //userId의 value를 가져와!
		
		//그리고 나서 검증한번해주고
		if(userId === ''){
			alert('아이디는 필수 값 입니다.')
			return; //이벤트 강제 종료
		} if(!idFlag){
			alert('똑바로 적어주세요');
			return;
		}
		
		//이제 비동기방식을 이용해서, 사용자가 작성한 것을 서버로 보낼 예정이다.
		//서버에서는 DB와 연동해서 중복이 됐는지 안됐는지 판단해서 뷰페이지가 아닌, 지금 userJoin이라는 화면 자체에 문자열을 보내준다.
		
		
		
		//즉, 아이디 중복확인 비동기 요청 준비(얘는 전통적 방식. )
		
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
		
		
		
		
		
		
		
		
		
		/* 얘는 전통적 방식보다 간결한 방식.
		
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
		fetch('${pageContext.request.contextPath}/user/idCheck', reqObj) //요청
		//요청에 성공했다면 then함수를 호출.
		.then(res => res.text()) //요청 완료 후 응답 정보에서 텍스트만 빼기
			.then(data => { //프로미스객체에서 데이터를 전달받아서 데이터를한번 찍어보겠다
				
				//즉즉즉즉, 패치실행후 성공했으면 then, 그 다음에 응답 객체 받아서 text만 뺀 Promise 객체로부터 data를 전달 받았다는 순서이다.

				//전달 받았으니 검증해야지. (아이디 중복 여부)
				if(data === 'ok'){
					//더 이상 아이디를 작성할 수 없도록 막아주겠다.
					document.getElementById('userId').setAttribute('readonly', true);
					//그리고 더 이상 버튼을 누를 수 없도록 비활성화 하겠다.
					document.getElementById('idCheckBtn').setAttribute('disabled', true);
					//그리고 메세지 남기기
					document.getElementById('msgId').textContent = '사용 가능한 아이디 입니다.';
				} else { //엘스는 아이디가 중복이잖아.
					document.getElementById('msgId').textContent = '중복 된 아이디 입니다.';
				}
			}); 
		
	} //아이디 중복 확인 끝!
	
	
	
	
	//인증 번호를 이메일로 전송시켜보자
	//사용자는 이메일을 인증했을꺼고 전송 버튼을 누를 것이다!
	document.getElementById('mail-check-btn').onclick = function(){
		//이메일 주소를 만들어주자
		const email = document.getElementById('userEmail1').value + document.getElementById('userEmail2').value;  //유저 이메일1, 이메일2 를 불러올것이다. 그리고 붙이자. 그럼 완성된 이메일 주소겠지
		console.log('완성된 email: ' + email);
	
		fetch('${pageContext.request.contextPath}/user/mailCheck?email=' + email) //패치를 이용하여 GET방식으로 이메일을 넘기자! (아까 포스트방식 해봤으니까)
 		//겟방식은 패치함수가 이게 끝이다. 
 		//여기로 또 프로미스가 리턴이 되겠지. then으로 값을 처리하면 되겠지.
		.then(res => res.text()) //요청이 성공했다면 응답객체를 받을 수 있다. 이 줄에서 res써주자
			.then(data => { //텍스트를 받았다면 데이터를 끄집어 내겠다.
				console.log('인증번호: ' + data);
				
			
			//비활성된 인증번호의 입력창을 활성화 시키자
			document.querySelector('.mail-check-input').disabled = false;
			code = data; //인증번호를 전역변수에 저장.   code는 맨 위에서 선언했다.
			alert('인증번호가 전송 되었습니다. 확인 후 입력란에 정확히 입력하세요');
			
			}); //비동기 끝.
			
	}; //인증번호 이벤트 끝.

	
	
	//이제 인증번호 검증
	//이벤트 타입은 blur이다. blur란? focus가 벗어나는 경우 발생한다.
	document.querySelector('.mail-check-input').onblur = function(e){
		console.log('blur 이벤트 발생 확인!');
		
		//사용자가 입력한 인증 번호 가져오기.
		const inputCode = e.target.value;
		
		//위로가보면 span태그가 있다. 결과 메시지 남겨 줄 것이다.
		const $resultMsg = document.getElementById('mail-check-warn');
		
		//동작안할수도 있으니 확인 한번 해주고.
		console.log('사용자가 입력한 값: ' + inputCode);
		
		if(inputCode === code){
			$resultMsg.textContent = '인증번호가 일치합니다.';
			$resultMsg.style.color = 'green';
			//이메일 인증을 더 이상 못하게 버튼 비활성
			document.getElementById('mail-check-btn').disabled = true;
			document.getElementById('userEmail1').setAttribute('readonly', true);
			document.getElementById('userEmail2').setAttribute('readonly', true);
			e.target.style.display = 'none'; //인증번호 입력창 숨기기
			
			//초기값을 사용자가 선택한 값으로 무조건 설정하는 방법(select태그에서 readonly 대용으로 사용한다)
			//즉, select가 readonly가 안보인다. (현재 userEmail2 자리에는 리드온리가 안되지만 셀렉처리가됨)
			//항상 2개 같이 써야 하는데, 일단 요소를 먼저 취득
			const email2 = document.getElementById('userEmail2'); //이건 그냥 요소부터얻고
			email2.setAttribute('onFocus', 'this.initialSelect = this.selectedIndex'); //셋어트리뷰트로 속성걸어주자
			//즉, 이벤트를 인라인방식으로 먹였는데. 사용자가 선택한 그 인덱스 값을 초기화 값으로 세팅 해주겟다 라는 뜻이다.
			email2.setAttribute('onChange', 'this.selectedIndex = this.initialSelect');
			
			
			
			
			
			
		} else {   //사용자가 인증번호가 틀렸다면
			$resultMsg.textContent = '인증번호를 다시 확인해 주세요.';
			$resultMsg.style.color = 'red';
			e.target.focus(); //그러고 나서 다시 입력할 수 있도록 포커싱 주기.
		}
		
	} //인증 번호 끝
	
	
	
	
	
	
	
	
	//다음 주소 API 사용해 보기 (scrpit src 추가 해야 한다(위에추가했음))
	function searchAddress() { //메서드명은 우리 마음대로 바꿔주고~
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                
                
                
                
                
                /*
                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }
				*/    //위 주석 부분은 필요가 없음.
                
                
                
                
                
                
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('addrZipNum').value = data.zonecode; //우편번호
                document.getElementById("addrBasic").value = addr; 	//기본주소.  addr은 아까 도로명인지 지번인지 선택한 값을 위에서 받음
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("addrDetail").focus();
            }
        }).open();
    } //주소찾기 api 끝.
    
    
    
    
    
    
    
    
    
    //폼 데이터 검증 (회원 가입 버튼을 눌렀을 때)
	document.getElementById('joinBtn').onclick = function(){
    	
    	if(idFlag && pwFlag){ //둘중 하나라도 false니? 정규표현식을 통과했으면 각각 true가 오겠지. 이걸 통과 해야 중복체크 등등을 검사한다. 하나라도 통과하지 못한다면 else로빠짐.
    		//입력값 검증
        	if(!document.getElementById('userId').getAttribute('readonly')){ //리드온리(중복체크안했다라는소리)가 걸려있지 않니?
        		alert('아이디 중복 체크는 필수 입니다.');
        		return; //종료
        	}
    	
        	if(document.getElementById('userPw').value !== document.getElementById('pwConfirm').value){ //혹시 입력한 비밀번호 값과 비밀번호 확인란과 값이 다르니? 
        		alert('비밀번호 확인란을 확인하세요!')
        		return
        	}
        	
        	if(document.getElementById('userName').value === ''){
        		alert('이름은 필수값입니다.');
        		return;
        	}

        	if(!document.getElementById('mail-check-btn').disabled){ //이메일 인증을 안한사람이구나
        		alert('이메일 인증을 완료해 주세요.');
        		return;
        	}
        	
			if(confirm('회원 가입을 진행합니다.')){
				document.joinForm.submit();
			} else { //취소버튼누르면
				return;
			}
        	
    	} else { //정규표현식 통과 못함
    		alert('입력값을 다시 한 번 확인하세요!');
    	}
    	
    	
    	
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

			idFlag = true;
			
			
		} else {
			document.getElementById("userId").style.borderColor = "red";
			document.getElementById("msgId").innerHTML = "부적합한 아이디 입니다.";
		
			idFlag = false;
		}
	}
	
	
	/*비밀번호 형식 검사 스크립트*/
	var pw = document.getElementById("userPw");
	pw.onkeyup = function() {
		var regex = /^[A-Za-z0-9+]{8,16}$/;
		if (regex.test(document.getElementById("userPw").value)) {
			document.getElementById("userPw").style.borderColor = "green";
			document.getElementById("msgPw").innerHTML = "사용가능합니다";
	
			pwFlag = true;
		
		} else {
			document.getElementById("userPw").style.borderColor = "red";
			document.getElementById("msgPw").innerHTML = "불가능합니다.";
		
			pwFlag = false;
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
