<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



	<%@ include file="../include/header.jsp"%>



    <section>
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-7 col-xs-10 login-form">
                    <div class="titlebox">
                        로그인
                    </div>
                    <form method="post" name="loginForm">
                        <div class="form-group"><!--사용자클래스선언-->
                            <label for="id">아이디</label>
                            <input type="text" name="userId" class="form-control" id="id" placeholder="아이디">
                         </div>
                         <div class="form-group"><!--사용자클래스선언-->
                            <label for="id">비밀번호</label>
                            <input type="password" name="userPw" class="form-control" id="pw" placeholder="비밀번호">
                         </div>
                         <div class="form-group">
                            <button type="button" id="loginBtn" class="btn btn-info btn-block">로그인</button>
                            <button type="button" id="joinBtn" class="btn btn-primary btn-block">회원가입</button>
                         </div>
                         
                         
                         
                         <!-- 카카오사용할것임, 부트스트랩 사용. 프로퍼티 파일을 하나 파서 카카오디벨로퍼의 내 어플리케이션의 REST API 주소를 kakao.clientId = 공간에 써주러 가자. 보안의 client secret 코드도 넣어주자.  -->
                         <div class="col-lg-12 text-center mt-3">    <!-- div컬럼을 12개를 채운다는 뜻. mt는 마진3. -->
                         	<button type="button" onclick="location.href='${urlkakao}'">
                         		<img alt="카카오 로그인" src="${pageContext.request.contextPath}/img/kakao_login_medium_wide.png">
                         	</button>
                         </div>
                    
                    </form>                
                </div>
            </div>
        </div>
    </section>
    
    
    
    
    	<%@ include file="../include/footer.jsp"%>
    
		<script>
		
			const msg = '${msg}'; //회원 가입 완료 후, 리다이렉트의 addFlashAttribute로 msg 데이터가 전달 되는지 확인하는 것이다.
		
			if(msg === 'joinSuccess'){
				alert('회원 가입 정상 처리되었습니다.');
			} else if(msg === 'loginFail'){
				alert('로그인에 실패했습니다.');
			}
		
			
			//문제 : id, pw 입력란이 공백인지 아닌지 확인한 후, 공백이 아니라면 submit을 진행하세요.
			//요청 url은 /user/userLogin -> post로간다. (비동기방식으로X. 비동기를 남발하는건 좋은게 아님. 게시글 등록이 완료되면 목록으로 빠지는데 이걸 굳이 비동기로 할 필요는 없다. 따로 값을 보내줘야 하는 로직을 추가해야 하니까..)
			
			document.getElementById('loginBtn').onclick = () => {
				if(document.getElementById('id').value === ''){
					alert('아이디를 적어주셔야 로그인을 합니다.');
					return;
				}
				if(document.getElementById('pw').value === ''){
					alert('비밀번호를 작성하세요!');
				}
				
				document.loginForm.submit();
			}			
			
			
			document.getElementById('joinBtn').onclick = () => {
				location.href = '${pageContext.request.contextPath}/user/join'; //회원가입 누르면 이쪽으로 보내주겠다고~
			}
			
			
			
			
			
		</script>