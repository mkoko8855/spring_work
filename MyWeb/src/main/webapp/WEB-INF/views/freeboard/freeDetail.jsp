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




				<!--여기에접근 반복-->
				<!-- 댓글목록을 표헌하는 부분이다 -->
				<div id="replyList">
					<div class='reply-wrap'>
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
					</div>
				</div>
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
			.then(res => res.text()){ //then : 요청이 성공했다면, catch : 요청에 실패했다면,  아무튼 성공했다면 응답객체가 전달이 된다. 그 응답객체를 res라는 변수에 받아줄것이다.

			.then(data => {
				console.log('통신 성공!: ' + data);
				document.getElementById('reply').value = ''; //빈문자열을 넣어서 텍스트를 비워주자
				document.getElementById('replyId').value = '';
				document.getElementById('replyPw').value = '';
				
				//이제 등록 완료 후, 댓글 목록 함수를 호출해서 비동기 식으로 목록을 표현하자
				getList();
			});
			
			
			
			
			
		}//댓글 등록 이벤트 끝.
	
		
		//getList는 댓글 목록을 가져올 함수.
		//getLit의 매개값으로는 요청된 페이지 번호와 화면을 리셋할 것인지의 여부를 boolean타입의 reset으로 받겠다. 트루면 화면 리셋.
		//리셋은 왜필요한가? > 페이지가 그대로 머물면서 댓글이 밑에 계속 쌓이기 때문에 상황에 따라서 페이지를 리셋해서 새롭게 그려낼 것인지, 누적해서 쌓을 것인지에 대한 여부를 판단해야한다.
		function getList(pageNum, reset){ //getList라는 함수 하나 선언. > 몇번 페이지의 댓글을 가져와야 하는가?, 리셋은 화면을 리셋을 할것인가 누적해서보여줄것인가 인 것이 reset함수이다.
			
				//서버에 요청 보내면서 댓글을 받아 낼껀데, 일단 글 번호를 알아야 하겠다.
				const bno = '${article.bno}'; //게시글 번호 받기
				//그리고 나서 요청을 보내 줄 것이다. 겟방식으로 댓글 목록을 요청(비동기).
				fetch('${pageContext.request.contextPath}/reply/getList/' + bno + '/' + pageNum); //이건 /MyWeb/reply/getList/10/1 을 의미한다. 즉, url만으로 요청을 파악하는 rest방식이다. 요청방식은 겟방식이고, rest통신에서 get은 조회를 뜻한다. post는 insert, put은 udate, delete는 delete를 뜻한다.
				//이제 컨트롤러가서 //목록 요청(페이징 포함) 메서드 완성해주자
		
				
				
		}
	
	
	

	} //window.onload 괄호 끝
</script>





