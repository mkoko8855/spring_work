<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- c태그사용하기우해 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- reg_date같은거 regdate로써주기위해 -->
<%@ include file="../include/header.jsp"%>

<section>
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-md-9 write-wrap">
				<div class="titlebox">
					<p>상세보기</p>
				</div>

				<form action="<c:url value='/freeboard/modify'/>" method="post">
					<div>
						<label>DATE</label>
						<c:if test="${article.updateDate == null}">
							<p>
								<fmt:parseDate value="${article.regDate}"
									pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime"
									type="both" />
								<fmt:formatDate value="${parsedDateTime}"
									pattern="yyyy년 MM월 dd일 HH시 mm분" />
							</p>
							<p>
								<fmt:parseDate value="${article.updateDate}"
									pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedUpdateTime"
									type="both" />
								<fmt:formatDate value="${parsedUpdateTime}"
									pattern="yyyy년 MM월 dd일 HH시 mm분" />
							</p>
						</c:if>
					</div>
					<div class="form-group">
						<label>번호</label> <input class="form-control" name="bno"
							value="${article.bno}" readonly>
					</div>
					<div class="form-group">
						<label>작성자</label> <input class="form-control" name="writer"
							value="${article.writer}" readonly>
					</div>
					<div class="form-group">
						<label>제목</label> <input class="form-control" name="title"
							value="${article.title}" readonly>
					</div>

					<div class="form-group">
						<label>내용</label>
						<textarea class="form-control" rows="10" name="content" readonly>${article.content}</textarea>
					</div>

					<button type="submit" class="btn btn-primary"
						onclick="return confirm('변경 페이지로 이동합니다. 라고 한번더 물어볼게요~')">변경</button>
					<button type="button" class="btn btn-dark"
						onclick="location.href='${pageContext.request.contextPath}/freeboard/freeList?pageNum=${p.pageNum}&cpp=${p.cpp}&keyword=${p.keyword}&condition=${p.condition}'">목록</button>
				</form>
			</div>
		</div>
	</div>
</section>




<!--댓글 영역 시작부분 (댓글시작) -->
<section style="margin-top: 80px;">
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-md-9 write-wrap">
				<form class="reply-wrap">
					<div class="reply-image">
						<img src="${pageContext.request.contextPath}/img/profile.png">
					</div>
					<!--form-control은 부트스트랩의 클래스입니다-->
					<div class="reply-content">
						<textarea class="form-control" rows="3" id="reply"></textarea>
						<div class="reply-group">

							<div class="reply-input">
								<input type="text" class="form-control" id="replyId"
									placeholder="이름"> <input type="password"
									class="form-control" id="replyPw" placeholder="비밀번호">
							</div>

							<button type="button" id="replyRegist" class="right btn btn-info">등록하기</button>
						</div>

					</div>
				</form>




				<!--여기에 접근 반복-->
				<!-- 댓글목록을 표헌하는 부분이다 -->
				<div id="replyList">
				
				
					
					
					<!-- 자바 스크립트 단에서 반복문을 이용해서 댓글의 개수만큼 반복 표현 -->
					<!-- <div class='reply-wrap'> 여기서부터@@@@
						<div class='reply-image'>
							<img src='../resources/img/profile.png'>
						</div>
						<div class='reply-content'>
							<div class='reply-group'>
								<strong class='left'>honggildong</strong> <small class='left'>2019/12/10</small>
								<a href='#' class='right'><span
									class='glyphicon glyphicon-pencil'></span>수정</a> <a href='#'
									class='right'><span class='glyphicon glyphicon-remove'></span>삭제</a>
							</div>
							<p class='clearfix'>여기는 댓글영역</p>
						</div>
					</div> 여기까지의@@@@ 내용이 반복이 돼야함 -->
					
					
					
					
					
				</div>
				
				<button type="button" class="form-control" id="moreList">더보기(페이징)</button>
				
				
			</div>
		</div>
	</div>
</section>




<!-- 모달 -->
<!-- 수정, 삭제 등을 클릭했을 떄 처리하는 부분 -->
<div class="modal fade" id="replyModal" role="dialog">
	<div class="modal-dialog modal-md">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="btn btn-default pull-right"
					data-dismiss="modal">닫기</button>
				<h4 class="modal-title">댓글수정</h4>
			</div>
			<div class="modal-body">
				<!-- 수정폼 id값을 확인하세요-->
				<div class="reply-content">
					<textarea class="form-control" rows="4" id="modalReply"
						placeholder="내용입력"></textarea>
					<div class="reply-group">
						<div class="reply-input">
							<input type="hidden" id="modalRno"> <input
								type="password" class="form-control" placeholder="비밀번호"
								id="modalPw">
						</div>
						<button class="right btn btn-info" id="modalModBtn">수정하기</button>
						<button class="right btn btn-info" id="modalDelBtn">삭제하기</button>
					</div>
				</div>
				<!-- 수정폼끝 -->
			</div>
		</div>
	</div>
</div>



<%@ include file="../include/footer.jsp"%>




<script>
	window.onload = function() { //창이 열리면 자바스크립트가 시작되어라!
	
		//댓글을 등록한다 라는 가정이라면?
		document.getElementById('replyRegist').onclick = function() {
			
			console.log('댓글 등록 이벤트 발생!');
		
			
			
			const bno = '${article.bno}';   //현재 게시글 번호(댓글은 게시글번호가필요하니까~)
			const reply = document.getElementById('reply').value;  //댓글내용의 값도 받아오고
			const replyId = document.getElementById('replyId').value; //작성자도 받아오고
			const replyPw = document.getElementById('replyPw').value; //댓글비번도 받아오고
	
			//빈칸만 아니면 넘겨주자~
			if(reply === '' || replyId === '' || replyPw === ''){ //셋중에 하나라도 빈게 true라면
				alert('이름, 비밀번호, 내용을 입력 하셔야 합니다.');
				return; //이벤트 종료시켜버림
			}
			
			
			//내용을 다 적었으니 fetch를부르자~ 전송은 post로~
			const reqObj = {
					method : 'post',
					headers: {
						'Content-Type': 'application/JSON'
					},
					body: JSON.stringify({
						'bno' : bno,
						'reply' : reply,
						'replyId' : replyId,
						'replyPw' : replyPw //이들은 자바 스크립트 객체이다. 이걸 서버로 넘길껀데, 자바언어로 이루어져있는데 이건 못받겠지? 그래서 JSON으로 표현하자
					})
			};
			
			
			fetch('${pageContext.request.contextPath}/reply/regist', reqObj) //패치라는 함수를 통해 비동기방식으로 요청을 보내는 거고, then으로 받자
			.then(res => res.text()) //then : 요청이 성공했다면, catch : 요청에 실패했다면,  아무튼 성공했다면 응답객체가 전달이 된다. 그 응답객체를 res라는 변수에 받아줄것이다.

			.then(data => {
				console.log('통신 성공!: ' + data);
				document.getElementById('reply').value = ''; //빈문자열을 넣어서 텍스트를 비워주자
				document.getElementById('replyId').value = '';
				document.getElementById('replyPw').value = '';
				
				//이제 댓글 등록 완료 후, 입력창이 비워질 것이고 댓글 목록 함수를 호출해서 비동기 식으로 목록을 표현하자
				getList(1, true); //목록 요청(목록 함수 호출) > 1은 내림차순이니 가장 최신글까지 불러오는것. true는 누적된거필요없이 다시 새롭게 렌더링해서 보여달라고.
			});
			
		}//댓글 등록 이벤트 끝.
	
		
		
		//비동기 방식이라 어디다써도 상관없음
		
		//더보기 버튼 처리(클릭 시 전역 변수 page(바로아래있는 변수)에 +1한 값을 요청할거다.)
		document.getElementById('moreList').onclick = () => {
			getList(++page, false); //리셋의 여부는 false다.
			//근데 왜 false를 줄까?
			//더보기 잖아. 댓글을 누적해서 보여줘야 하니까.
			//1페이지의 댓글 내용 밑에 2페이지를 누적해서 깔아야하니까.
			//1페이지 내용을 없애고 2페이지를 보여주자는 것이 아니니까.
			//그래서 false를 준다.
		}
		
		
		
		
		
		
		
		
		
		
		let page = 1; //전역 의미로 사용할 페이지 번호
		let strAdd =''; //화면에 그려넣을 태그를 문자열의 형태로 추가할 변수
		const $replyList = document.getElementById('replyList'); //변수선언하면서 지목 동시에.
		
		
		
		getList(1, true); //게시글 상세보기 화면에 처음 진입 했을 시, 댓글 리스트를 한번 불러오자
		
		
		//getList는 댓글 목록을 가져올 함수.
		//getLit의 매개값으로는 요청된 페이지 번호와 화면을 리셋할 것인지의 여부를 boolean타입의 reset으로 받겠다. 트루면 화면 리셋.
		//리셋은 왜필요한가? > 페이지가 그대로 머물면서 댓글이 밑에 계속 쌓이기 때문에 상황에 따라서 페이지를 리셋해서 새롭게 그려낼 것인지, 누적해서 쌓을 것인지에 대한 여부를 판단해야한다.
		function getList(pageNum, reset){ //getList라는 함수 하나 선언. > 몇번 페이지의 댓글을 가져와야 하는가?, 리셋은 화면을 리셋을 할것인가 누적해서보여줄것인가 인 것이 reset함수이다.
			
				strAdd = ''; //strAdd를 초기화하겠다.
				
				//서버에 요청 보내면서 댓글을 받아 낼껀데, 일단 글 번호를 알아야 하겠다.
				const bno = '${article.bno}'; //게시글 번호 받기
				//그리고 나서 요청을 보내 줄 것이다. 겟방식으로 댓글 목록을 요청(비동기).
				fetch('${pageContext.request.contextPath}/reply/getList/' + bno + '/' + pageNum) //이건 /MyWeb/reply/getList/10/1 을 의미한다. 즉, url만으로 요청을 파악하는 rest방식이다. 요청방식은 겟방식이고, rest통신에서 get은 조회를 뜻한다. post는 insert, put은 udate, delete는 delete를 뜻한다.
				//이제 컨트롤러가서 //목록 요청(페이징 포함) 메서드 완성해주자
				
				.then(res => res.json())
				.then(data => {
					console.log(data);
					
					//이제는 응답을 받은 다음 값을 받자
					let total = data.total; //총 댓글 수
					let replyList = data.list; //댓글 리스트
					
					
					//반복문 이용해서 화면에 뿌리자. 위 html태그에서 <!-- 여기서부터@@@@ --> 부분을 찾아서 뭘 반복할건지 확인하자. 주석으로 처리하고 반복할꺼임.
					//data.total, list로 받아왔는데 댓글이 없을 수도 있잖아
					//즉, 응답 데이터의 길이가 0과 같거나 더 작으면 함수를 종료.
					if(replyList.length <= 0){
						return; //종료
					}
					
					
					
					//insert나 update(수정), delete 작업 후에는
					//댓글 내용 태그를 누적하고 있는 strAdd라는 변수를 초기화해서
					//마치 화면이 리셋된 것 처럼 보여줘야 한다.
					//댓글 등록 후 누적이 돼야 하는 곳이 아니니까.
					//그래서 조건문을 쓰자
					if(reset){ //reset이 트루라면
						
						//반복문으로 밀기
						while($replyList.firstChild){ //replyList라는 부모의 firstChild를 찾았을 때, 제거.
							$replyList.firstChild.remove();
						} 
						
						
						page = 1; //페이지도 1로 돌려놓겠다.
					}
					
					
					
					//총 댓글의 개수가 더 많으면 더보기는 계속 존재해야 하지만, 전역변수인 페이지도 계속 요청하고 있는 상황이다.
					//즉, 페이지 번호 *(곱) 이번 요청으로 받은 댓글 수보다 전체 댓글 개수가 작다면 더보기 버튼은 없어도 된다.
					console.log('현재 페이지: ' + page);
					if(total <= page * 5){
						document.getElementById('moreList').style.display = 'none';
					} else {
						document.getElementById('moreList').style.display = 'block';
					}
					
					
					
					
					
					
					
					//replyList의 개수만큼 태그를 문자열 형태로 직접 그림.
					//그리고, 중간에 들어갈 댓글 글쓴이, 날짜, 댓글 내용은 목록에서 꺼내서 표현.
					for(let i=0; i<replyList.length; i++){ //cpp = 5로 설정했기에, 최대 댓글은 즉, replyList에는 5개가 있겠다.
						strAdd +=
						`<div class='reply-wrap'> 
						<div class='reply-image'>
							<img src='${pageContext.request.contextPath}/img/profile.png'>
						</div>
						<div class='reply-content'>
							<div class='reply-group'>
								<strong class='left'>` + replyList[i].replyId +`</strong> 
								<small class='left'>` + replyList[i].replyDate +`</small>
								<a href='` + replyList[i].rno + `' class='right replyDelete'><span class='glyphicon glyphicon-remove'></span>삭제</a> &nbsp;
									<a href='` + replyList[i].rno + `' class='right replyModify'><span class='glyphicon glyphicon-pencil'></span>수정</a> 
									</div>
							<p class='clearfix'>`+ replyList[i].reply + `</p>
						</div>
					</div>`;
						
					}
					
					//id가 replyList라는 div 영역에 문자열 형식으로 모든 댓글을 추가하자.
					if(!reset){
						document.getElementById('replyList').insertAdjacentHTML('beforeend', strAdd); //위치(포지션), 값 주면 된다. afterbegin..은 기억안나면 구글링부터하자)
					} else{
						document.getElementById('replyList').insertAdjacentHTML('afterbegin', strAdd); //위치(포지션), 값 주면 된다. afterbegin..은 기억안나면 구글링부터하자)
					}
					
				}); 
				
		} //getList() 끝
		
	
		
		
		
		//수정과 삭제 처리를 하자 (모달)
		//부모요소에 이벤트걸어서 전파할것임
		/* 	document.querySelector('.replyModify').addEventListener('click', function(e){
			//일단 부모에 걸지 않고 직접 수정을 해보겠다
			//a태그이니
			e.preventDefault(); //a가 동작하지 않도록. 콘솔 로그만뜨게
			console.log('수정 버튼 이벤트 발생!');
		}); 
		이거 동작 안함.  .replyModfiy는 실제 존재하는 요소가 아니다.
		#replyList를 써야 동작한다.
		왜 저게 실제 존재하는 요소가 아니냐면,
		비동기 통신을 통해 생성되는 요소이다.
		그러다보니 이벤트가 등록되는 시점보다 fetch 함수의 실행이 먼저 끝날 것이라는
		보장이 없기 떄문에 해당 방식은 이벤트 등록이 불가능하다.
		이 때는, 이미 실제로 존재하는 #replyList에 이벤트를 등록하고,
		이벤트를 자식에게 위임하여 사용하는 addEventListener를 통해 처리를 해야 한다.
		*/
		
		
		document.getElementById('replyList').addEventListener('click', e => {
			e.preventDefault(); //태그의 고유 기능을 가지고 있다면 중지.
			
			//1. 이벤트가 발생한 target이 a태그가 아니라면 이벤트 종료
			if(!e.target.matches('a')){ //a가 아니라면 이벤트 진행 시키지 않을 것이다. (a태그)
				return;
			}
			
			//2. a태그가 두 개 (수정, 삭제)이므로 어떤 링크 인지를 확인.
			//그리고 댓글이 여러개잖아. -> 수정, 삭제가 발생하는 댓글이 몇 번인지도 확인을 해야 한다.
			const rno = e.target.getAttribute('href');
			console.log('댓글 번호: ' + rno);
			document.getElementById('modalRno').value = rno; //모달 내부에 숨겨진 input 태그에 댓글 번호를 담아주자.
			
			
			//그리고 우리는 댓글 내용도 얻어와야 한다. (p태그 clreafix에 있는거.)
			const content = e.target.parentNode.nextElementSibling.textContent;
			console.log('댓글 내용: ' + content);
			//즉, e.target은 수정이나 삭제겠지. 둘중 하나 중 거기의 부모 요소인 reply-group태그의 다음 형제 요소의 text내용들을 가져와라! 라는 의미.
			
			
			
			//3. 모달 창 하나를 이용해서 상황에 따라 수정 / 삭제 모달을 구분하기 위해
			//조건문을 작성. (목적은 모달 하나로 수정, 삭제를 같이 처리할 것이다.)
			//그러기 위해 디자인을 디자인 조정하자.
			if(e.target.classList.contains('replyModify')){  // <!--모달-->가면 replyModify로이름있을거임
		
				//수정 버튼을 눌렀으므로 수정 모달 형식을 꾸며주겠다.
				
				//제어하자
				document.querySelector('.modal-title').textContent = '댓글 수정';
				//제이쿼리라면 $('.modal-title).text('modify'); 로 쓸 수 있음
				
				document.getElementById('modalReply').style.display = 'inline'; //댓글창
				
				document.getElementById('modalReply').value = content;
				
				//버튼숨겨주자. 수정 버튼이면 삭제 버튼은 숨겨주자
				document.getElementById('modalModBtn').style.display = 'inline';
				document.getElementById('modalDelBtn').style.display = 'none';
				
				
				
				$('#replyModal').modal('show'); //제이쿼리를 이용해서 bootstrap 모달을 여는 방법. 닫을 떄는 모달 함수에 hide주면 닫힌다.
			} else {
				//삭제 버튼을 눌렀으므로 삭제 모달 형식으로 꾸며줌.
				document.querySelector('.modal-title').textContent = '댓글 삭제';
				document.getElementById('modalReply').style.display = 'none'; //댓글창을 삭제때는 안보여줘도되니까
				//document.getElementById('modalReply').value = content;      //댓글창을 안보여주니 필요없음
				document.getElementById('modalModBtn').style.display = 'none';  //수정버튼은 숨겨주고
				document.getElementById('modalDelBtn').style.display = 'inline';
				$('#replyModal').modal('show');
			}
			
			
		}); //수정 or 삭제 버튼 클릭 이벤트 끝


		
		
		//이제 사용자가 모달 이벤트 열어서 수정이나 삭제를 하겠다.
		//수정 처리 함수. (수정 모달을 열어서 수정 내용을 작성 후 수정 버튼을 클릭했을 때, 그 때의 이벤트를 처리하자)
		document.getElementById('modalModBtn').onclick = () => {
			
			const reply = document.getElementById('modalReply').value;
			const rno = document.getElementById('modalRno').value;
			const replyPw = document.getElementById('modalPw').value;

			if(reply === '' || replyPw === ''){
				alert('내용, 비밀번호를 확인하세요. 왜아무것도 안썼어요?');
				return;
			}

			const reqObj = {
				method: 'put',
				headers: {
					'Content-Type' : 'application/json'
				},
				body: JSON.stringify({
					'reply' : reply,
					'replyPw' : replyPw
				})
			}

			fetch('${pageContext.request.contextPath}/reply/' + rno, reqObj)  //글번호만 담자. 전송 방식은 reqObj처럼 객체 만들어서 써도 되고, 바로 써도 되긴함..
				//요청 보내고 응답 받는거에 성공했다면,
				.then(res => res.text()) //단순 문자만 줬기 때문에 (제이슨이면 제이슨으로받아야함)
			
				.then(data => {
					if(data === 'pwFail'){ //sts  서비스의   return "pwFail"; 부분이다.
						alert('비밀번호가 틀렸습니다. 확인해 주세요');
						document.getElementById('modalPw').value = ''; //사용자가 기존에 입력했던 비밀번호를 지우자
						document.getElementById('modalPw').focus(); //그리고 비밀번호를 바로 입력할 수 있게 포커스주자.
					} else {
						alert('정상 수정 되었습니다.')
						document.getElementById('modalReply').value = '';
						document.getElementById('modalPw').value = '';
						//제이쿼리 문법으로 부트스트랩 모달 닫아주기
						$('#replyModal').modal('hide');
						
						//수정된 내용을 가지고 다시 목록을 띄워줘야하니까
						getList(1, true); //true는 재렌더링을 하겠따는 소리다.
					}
				});
			

		} //end update event
		
		
		
		
		//삭제 이벤트
		document.getElementById('modalDelBtn').onclick =() => {
			
			/*
			1. 모달창에 rno값, replyPw값을 얻습니다.
			2. fetch 함수를 이용해서 DELETE방식으로 reply/{rno} 요청.
			3. 서버에서는 요청을 받아서 비밀번호를 확인하고, 비밀번호가 맞으면
			   삭제를 진행하시면 됩니다.
			4. 만약 비밀번호가 틀렸다면, 문자열을 반환해서 '비밀번호가 틀렸습니다.' 경고창을 띄우세요.
			
			삭제가 완료되면 모달 닫고 목록 요청을 다시 보내세요. (reset의 여부를 잘 판단)
			*/
			
			const rno1 = document.getElementById('modalRno').value;
			const replyPw1 = document.getElementById('modalPw').value;
			
			/* if(replyPw1 === ''){
				alert('비밀번호 적어라')
			} else {
				alert('')
			} */
			
			const reqObj1 = {
					method: 'delete',
					headers: {
						'Content-Type' : 'application/json'
					},
					body: JSON.stringify({
						'replyPw' : replyPw
					})
				}
			
			
			fetch('${pageContext.request.contextPath}/reply/' + rno, reqObj1)
			.then(res => res.text())
				.then(data => {
					if(data === 'zzz'){
						alert('비밀번호가 틀렸습니다. 확인해 주세요');
						document.getElementById('modalPw').focus();
				} else {
					document.getElementById('modalRno').value = '';
					$('#replyModal').modal('hide');
				}
			
			
			
		}
		
		
		

		
		
		
		
		
		
	

	} //window.onload 괄호 끝
</script>

	




