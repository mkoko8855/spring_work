<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
		<!-- 스크롤 시,  -->
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.21/lodash.min.js"></script>
	
	
	<style type="text/css">
	section {
		margin-top: 70px;
	}
	
	/*부트스트랩 li의 drophover클래스 호버시에 드롭다운 적용코드*/
	ul.nav li.drophover:hover>ul.dropdown-menu {
		display: block;
		margin: 0;
	}
	
	.aside-inner {
		position: fixed;
		top: 70px;
		width: 180px;
	}
	
	.section-inner {
		border-right: 1px solid #ddd;
		border-left: 1px solid #ddd;
		background-color: white;
	}
	
	.section-inner p, .aside-inner p {
		margin: 0;
	}
	
	.title-inner {
		position: relative;
		padding: 15px 0;
	}
	
	.title-inner .profile {
		position: absolute; /*부모기준으로 위치지정 릴레이티브*/
		top: 15px;
		left: 0;
	}
	
	.title-inner .title {
		padding-left: 50px;
	}
	/*내용*/
	.content-inner {
		padding: 10px 0;
	}
	/* 이미지영역  */
	.image-inner img {
		width: 100%;
	}
	/*좋아요*/
	.like-inner {
		padding: 10px 0;
		border-bottom: 1px solid #ddd;
		border-top: 1px solid #ddd;
		margin-top: 10px;
	}
	
	.like-inner img {
		width: 20px;
		height: 20px;
	}
	
	.link-inner {
		overflow: hidden;
		padding: 10px 0;
	}
	
	.link-inner a {
		float: left;
		width: 33.3333%;
		text-align: center;
		text-decoration: none;
		color: #333333;
	}
	
	.link-inner i {
		margin: 0 5px;
	}
	
	/*767미만 사이즈에서 해당 css가 적용됨*/
	/*xs가 767사이즈   */
	@media ( max-width :767px) {
		aside {
			display: none;
		}
	}
	/* 파일업로드 버튼 바꾸기 */
	.filebox label {
		display: inline-block;
		padding: 6px 10px;
		color: #fff;
		font-size: inherit;
		line-height: normal;
		vertical-align: middle;
		background-color: #5cb85c;
		cursor: pointer;
		border: 1px solid #4cae4c;
		border-radius: none;
		-webkit-transition: background-color 0.2s;
		transition: background-color 0.2s;
	}
	
	.filebox label:hover {
		background-color: #6ed36e;
	}
	
	.filebox label:active {
		background-color: #367c36;
	}
	
	.filebox input[type="file"] {
		position: absolute;
		width: 1px;
		height: 1px;
		padding: 0;
		margin: -1px;
		overflow: hidden;
		clip: rect(0, 0, 0, 0);
		border: 0;
	}
	
	/* sns파일 업로드시 미리보기  */
	.fileDiv {
		height: 100px;
		width: 200px;
		display: none;
		margin-bottom: 10px;
	}
	
	.fileDiv img {
		width: 100%;
		height: 100%;
	}
	/* 모달창 조절 */
	.modal-body {
		padding: 0px;
	}
	
	.modal-content>.row {
		margin: 0px;
	}
	
	.modal-body>.modal-img {
		padding: 0px;
	}
	
	.modal-body>.modal-con {
		padding: 15px;
	}
	
	.modal-inner {
		position: relative;
	}
	
	.modal-inner .profile {
		position: absolute; 
		top: 0px;
		left: 0px;
	}
	
	.modal-inner .title {
		padding-left: 50px;
	}
	
	.modal-inner p {
		margin: 0px;
	}
	</style>
	
</head>
<body>
	<%@ include file="../include/header.jsp" %>
	<section>
		<div class="container">
			<div class="row">
				<aside class="col-sm-2">
					<div class="aside-inner">
						<div class="menu1">
							<p>
								<img src="${pageContext.request.contextPath}/img/profile.png">홍길동
							</p>
							<ul>
								<li>사이드메뉴1</li>
								<li>사이드메뉴2</li>
								<li>사이드메뉴3</li>
							</ul>
						</div>
						<div class="menu2">
							<p>둘러보기</p>
							<ul>
								<li>링크1</li>
								<li>링크2</li>
								<li>링크3</li>
								<li>링크4</li>
								<li>링크5</li>
							</ul>
						</div>
					</div>
				</aside>
				<div class="col-xs-12 col-sm-8 section-inner">
					<h4>게시물 만들기</h4>
					<!-- 파일 업로드 폼입니다 -->
					<div class="fileDiv">
						<img id="fileImg" src="${pageContext.request.contextPath}/img/img_ready.png">
					</div>
					<div class="reply-content">
						<textarea class="form-control" rows="3" name="content"
							id="content" placeholder="무슨 생각을 하고 계신가요?"></textarea>
						<div class="reply-group">
							<div class="filebox pull-left">
								<label for="file">이미지업로드</label> 
								<input type="file" name="file" id="file">
							</div>
							<button type="button" class="right btn btn-info" id="uploadBtn">등록하기</button>
						</div>
					</div>
					<!-- 파일 업로드 폼 끝 -->
					
					
					
					
					
					<div id="contentDiv">
						<!-- 비동기 방식으로 서버와 통신을 진행 한 후, 목록을 만들어서 붙일 예정. -->
						
						
						
						
						
						
						
						
					</div>
				</div>
				
				
				
				
				
				
				
				
				
				
				
				<!--우측 어사이드-->
				<aside class="col-sm-2">
					<div class="aside-inner">
						<div class="menu1">
							<p>목록</p>
							<ul>
								<li>목록1</li>
								<li>목록2</li>
								<li>목록3</li>
								<li>목록4</li>
								<li>목록5</li>
							</ul>
						</div>
					</div>
				</aside>
			</div>
		</div>
	</section>
	<%@ include file="../include/footer.jsp" %>
	
	
	
	
	
	
	<!-- 모달 -->
	<div class="modal fade" id="snsModal" role="dialog">
			<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-body row">
					<div class="modal-img col-sm-8 col-xs-6" >
						<img src="${pageContext.request.contextPath}/img/img_ready.png" id="snsImg" width="100%">
					</div>
					<div class="modal-con col-sm-4 col-xs-6">
						<div class="modal-inner">
						<div class="profile">
							<img src="${pageContext.request.contextPath}/img/profile.png">
						</div>
						<div class="title">
							<p id="snsWriter">테스트</p>
							<small id="snsRegdate">21시간전</small>
						</div>
						<div class="content-inner">
							<p id="snsContent">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam vulputate elit libero, quis mattis enim tincidunt non. Mauris consequat ante vel urna posuere consequat. </p>
						</div>
						<div class="link-inner">
							<a href="##"><i class="glyphicon glyphicon-thumbs-up"></i>좋아요</a>
							<a href="##"><i class="glyphicon glyphicon-comment"></i>댓글달기</a> 
							<a href="##"><i class="glyphicon glyphicon-share-alt"></i>공유하기</a>
						</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>








	<script>
	//등록 이벤트가 발생하면 실행할 함수를 선언하자
	//등록 하기 버튼 클릭 이벤트(id가 uploadBtn이었다.)
	document.getElementById('uploadBtn').onclick = () => {
		regist();
	}

	//등록을 담당하는 함수
	function regist() {
		//사용자가 입력한 값(파일 등등..)들을 서버로 보내줄거다. 페이지는 머물러있는 상태에서(비동기 방식으로 처리하겠다는 것이다).
		//로그인을 한 사람만 작성할 수 있게 처리할거니 따로 작성자를 적는 곳은 없었다.

		//세션에서 현재 로그인 중인 사용자의 정보(아이디).  (어차피 세션에는 아이디 밖에 없다)
		const userId = '${sessionScope.login}'; //코드의 명확성을 위해..세션스코프작성해줬다.

		//사용자가 이미지 첨부를 하는데, 이미지가 아니라 다른 파일을 올릴 수 있다.
		//확장자를 뽑아서 검사해주자. 즉, 자바스크립트로 첨부한 파일의 확장자를 체크하자.
		let file = document.getElementById('file').value; //'file'요소의 파일의 값을 달라 (위 html에 이미지업로드 아래에 id="file"로 되어있음)
		console.log(userId);
		console.log(file);
		
		
		//.을 제거한 확장자만 얻어낸 후 그것을 소문자로 일괄 변경하자
		file = file.slice(file.indexOf('.') + 1).toLowerCase();   //.에 +1을(하나 더 크게잡아야 잘리겠지)
		
		if(file !== 'jpg' && file !== 'png' && file !== 'jpeg' && file !== 'bmp'){//정제를 하고 난 값이 만약 jpg도 아니고 png도 아니고 jpeg도 아니고 bmp도아니라면
			alert('이미지 파일(jpg, png, jpeg, bmp)만 등록이 가능합니다.');
			document.getElementById('file').value = ''; //비우자
			return; //강제종료
		} else if(userId === ''){ //로그인 안한 사용자도 거절해야지. > 세션데이터가 없다면 로그인 안했다 라는 것이다.
			alert('로그인 필요한 서비스 입니다.');
			return;
		}
		
		/*  
			이제 검증 끝났고, 비동기 방식으로 서버에 보내주자
			비동기 방식 요청에서 Form을 생성해서 보내주는 방법.
			FormData 라는 객체를 활용해보자 
			
			그러나 무조건 이 방식이 옳은 것은 아니다.
			FormData는 비동기 방식이 꼭 필요한 경우에만 사용한다.
			비동기가 아닐 때에는 원래 Form태그 쓰면 된다. 등록도 비동기로 하고 싶기 때문에 자바스크립트에 FormData방식을 쓰는 것이다.
			
			대부분의 경우에는 Form태그(인풋타입으로 보냄)를 이용해서 전송하는 방식이 더 간편하고 더 자주 사용된다.
		*/
		
		//폼데이터 객체 생성.
		const formData = new FormData();
		//폼데이터 얻어오자
		const $data = document.getElementById('file'); //이미지 첨부할 수 있는 이미지태그인 input. 요소는 앞에 $로 표현해주자.
		
		console.log('data: ' + $data); //파일올려서 등록하고 확인해보자.
		
		//한 화면에 파일 업로드 버튼이 여러 개일 경우, 요소의 인덱스를 지목해서 가져올 수 있다. 
		//우린 파일 첨부 버튼이 하나고, id를 지목해서 가져오기 떄문에 인덱스를 쓸 필요가 없다.
		
		//여러개 사용하고 인풋태그도 여러개 사용 할꺼라면 > (쿼리셀렉터ALL로 클래스 이름이 .file인 애들을 불러서 이런 인덱스 표현으로 부를 수 있다.)
		//console.log('data[0]: ' + $data[0]);
		
		
		
		console.log($data.files); //얘는 파일 태그에 담긴 파일 정보를 확인하는 프로퍼티이다. <input type="file" multiple 이라면 인덱스로 써서 지목을 하면 된다.>
		console.log($data.files[0]); //사용자가 등록한 최종 파일의 정보. 우리는 파일 한개 씩이니까 0번인덱스로만 지목하면 되겠다.
		
		//즉, 만약 우리가 여러 파일을 하나의 태그로 받을 수 있도록 multiple속성을 사용했다면
		//files -> fileList에 여러 파일의 정보가 들어오게 된다.
		//FilesList는 유사 배열이기 때문에 인덱스를 이용해서 여러 파일 중 하나를 지목할 수 있다.
		//우리는 지금 multiple이 없기 때문에 [0] 이라고 작성하면, 사용자가 지금 등록한 마지막 객체를(최종 파일의 정보)바로 파일 정보를 얻을 수 있다.
		
		
		
		
		//아까 생성했던 FormData에 추가하자. 즉, FormData객체에 사용자가 업로드한 파일의 정보가 들어있는 객체를 전달하자.
		formData.append('file', $data.files[0]); //'file'은 인풋에 달았던 파라미터값이다.
		formData.append('content', document.getElementById('content').value); //글 내용 추가
		formData.append('writer', userId); //작성자 추가
		
		
		
		//폼데이터(작성자 , 글내용 , 이미지)준비했으니 fetch이용해서 비동기방식으로 넘겨주자
		fetch('${pageContext.request.contextPath}/snsboard/upload', {
			//객체정보도 보내주자
			method : 'post',
			//보내줘야할 데이터로 body를 적는데, 원래 headers에 json을 적었지만 이번에는 headers말고..
			body : formData
			//그럼 이제 객체 선언 완료
			//즉, FormData객체를 보낼 때는 따로 headers 설정을 진행하지 않는다.
			//컨트롤러로가서 @PostMapping("/upload") 써주자
			
		})
			
		//요청이 성공했다면 응답 객체가 올거고, 객체에서 res.text뽑자
		.then(res => res.text())
		.then(data => {
			console.log(data); //잘된다면 컨트롤러에서 작성한 return success가왔겠지
			document.getElementById('file').value = ''; //file input 비우기. 우리는 화면을 하나만 비동기 방식으로 사용하니까. 우리가 안지워주면 안지워짐.
			
			//사용자가 작성한 글 내용도 지워주자
			document.getElementById('content').value = '';
			
			//미리보기 영역도 감추자
			document.querySelector('.fileDiv').style.display = 'none';
			
			//방금 등록된 글 까지 포함한 목록을 불러와야 하니까..리스트부르자
			getList(1, true); //글 목록 함수 호출.  1페이지와 리셋의 여부는 트루로줘야 함.
		});
	}  //글 등록 끝.  end regist()

	
	//리스트 작업
	let str = '';
	let page = 1;
	let isFinish = false;
	
	
	const $contentDiv = document.getElementById('contentDiv');
	getList(1, true);
	
	
	function getList(page, reset){ //화면의 초기화 여부인 reset을 받을껀데,  
		str = ''; //str비워주고.
		console.log('page: ' + page);
		console.log('reset의 여부도 알려줘: ' + reset);
				
		//fetch함수의 목록을 겟방식으로 가져올꺼다.
		//똑같이 반복문 이용해서 뿌릴꺼다.
		fetch('${pageContext.request.contextPath}/snsboard/' + page) //글번호를가지고올필욘없지 몇페이진지만얘기하자, 겟방식이기 때문에 객체전달필요도X
		//컨트롤러가서 @GetMapping("/{pageNum}") 작성 하고 오자
		.then(res => res.json())
		.then(list => { //매개변수 명은 그냥 알아서 지어도됨. res도 마찬가지.
			console.log(list);
			console.log(list.length);
			
			if(list.length ===0){
				isFinish = true;
			}
			
			if(reset){
				while($contentDiv.firstChild){
					$contentDiv.firstChild.remove();
				}
				page =1; //리셋이 되었다면 페이지는 다시 1로.
			}
			
			
			//어차피 다 뿌릴꺼니까 향상포문써보자(객체는 in으로, 배열은 of로)
			for(vo of list){
				//어디다가뿌려? <div id="contentDiv">라고 되어 있는 곳이다. 저기가 글 목록이 뿌려질 공간이다.
				str +=
					`<div class="title-inner">
						<!--제목영역-->
						<div class="profile">
							<img src="${pageContext.request.contextPath}/img/profile.png">
						</div>
						<div class="title">
							<p>` + vo.writer + `</p>
							<small>` + vo.regDate + `</small> &nbsp;&nbsp;
							<a id="download" href="${pageContext.request.contextPath}/snsboard/download/` + vo.fileLoca + `/` + vo.fileName + `">이미지 다운로드</a>
						</div>
					</div>
					<div class="content-inner">
						<!--내용영역-->
						<p>` + vo.content + `</p>
					</div>
					<div class="image-inner">
						<!-- 이미지영역 -->
						<a href="` + vo.bno + `"> <!--페이지를 이동시켜줄 것은 아님. 이 링크를 클릭하면 모달을 열어줄 것이다.-->
						<img data-bno="` + vo.bno + `" src="${pageContext.request.contextPath}/snsboard/display/` + vo.fileLoca + `/` + vo.fileName + `">
						</a>
					</div>
					<div class="like-inner">
						<!--좋아요-->
						<img src="${pageContext.request.contextPath}/img/icon.jpg"> <span>522</span>
					</div>
					<div class="link-inner">
						<a href="##"><i class="glyphicon glyphicon-thumbs-up"></i>좋아요</a>
						<a data-bno="`+ vo.bno +`"id="comment" href="`+ vo.bno +`"><i class="glyphicon glyphicon-comment"></i>댓글달기</a> 
						<a id="delBtn" href="`+ vo.bno +`"><i class="glyphicon glyphicon-remove"></i>삭제하기</a>
					</div>`;
			}
			
			// id가 contentDiv라는 곳에 이미지 넣기로 했잖아?
			//afterbegin~
			if(!reset){ 
				document.getElementById('contentDiv').insertAdjacentHTML('beforeend', str);
			}else {
				document.getElementById('contentDiv').insertAdjacentHTML('afterbegin', str);
			}
			
		}); // end fetch
	}	//end getList()
	
	
		
		
	
	
		//상세보기 처리(모달창 열어줄 것임)
		//부모요소에다 걸어서 전파하자. 부묘오소는 contentDiv이다.
		document.getElementById('contentDiv').addEventListener('click', e => { //onclick써도됨
			e.preventDefault(); //a태그는 기능이 있으니 a태그 기능부터 죽여야한다. 즉, a의 고유 기능 중지
			console.log('contentDiv 클릭 후 target은누구야? 알려줘 : ' + e.target);
			
			
			if(!e.target.matches('.image-inner img') && !e.target.matches('.link-inner #comment') && !e.target.matches('.title #download')){ //만약 a태그가 아니라면
				console.log('여기는 이벤트 대상이 아니에요'); 
				return; //이벤트 종료하겠다.
			}
			
			
			if(e.target.matches('.title #download')){
				//이미지나 커멘트는 이걸 건너뜀. 이 로직은 다운로드를 위해서만듦.
				if(confirm('다운로드를 진행합니다.')){
					location.href = e.target.getAttribute('href');
					return; //종료
				} else {
					return; //종료
				}
			}
						
			
			
			
			
			
			//a가 아닌 것들은 걸러졌으니, a태그를 클릭했다라고 가정하고.  
			//글 번호부터 얻자
			const bno = e.target.dataset.bno;
			console.log('bno: ' + bno);
			
			//글 번호 얻었으니.. 글 번호 묻혀서 요청을 넣어주자
			//문제 : fetch 함수를 사용하여 글 상세 보기 요청을 비동기 식으로 요청하세요.
			//전달 받은 글 내용을 미리 준비한 모달창에 뿌릴 겁니다. (모달은 위에 있다)
			//값을 제 위치에 배치하시고 모달을 열어 달라. (부트스트랩 모달이기 떄문에 JQuery로 열어달라.)
			// url: /snsboard/content/글번호     메서드이름은 알아서. 컨트롤러도 작성.
				fetch('${pageContext.request.contextPath}/snsboard/content/' + bno) //get이니까 reqObj 안써도됨.
				.then(res => res.json())
				.then(data => {
					console.log(data); //JSON형태의 data가 왔는지 확인해보기
					
					const src = '${pageContext.request.contextPath}/snsboard/display/' + data.fileLoca + '/' + data.fileName;
					document.getElementById('snsImg').setAttribute('src', src);
					document.getElementById('snsWriter').textContent = data.writer;
					document.getElementById('snsRegdate').textContent = data.regDate;
					document.getElementById('snsContent').textContent = data.content;
					//뿌렸으면 모달 열기
				});
					$('#snsModal').modal('show');
				});

				
			
			
			
			
			
			
				
		//삭제 처리
		//삭제하기 링크를 클릭했을 떄 이벤트 발생을 시켜서
		//비동기 방식으로 삭제를 진행해 주세요. (삭제 버튼은 여러개이다.)
		//다하고, 서버쪽에서 권한을 확인 해 주세요. (작성자와 로그인 중인 사용자의 id를 비교해서 일치하는지의 여부를 판단해주세요)
		//일치하지 않는다면 문자열 "noAuth" 를 리턴, 삭제 완료하면 "success"를 리턴해주세요.
		//url은 /snsboard/글번호   method: DELETE
		//파일도 삭제를 진행해줘야한다. (컨트롤러에 작성해줄것)
		
			
		document.getElementById('contentDiv').addEventListener('click', e=>{
			e.preventDefault();
			if(!e.target.matches('.link-inner #delBtn')){
				return; //종료
			}
			
			const bno = e.target.getAttribute('href'); //글번호얻기
			
			fetch('${pageContext.request.contextPath}/snsboard/' + bno, {
				method : 'delete'
				//전달할데이터존재는 딱히없으니..reqobj객체안써도됨.
				
			})
			.then(res => res.text())
			.then(result => {
				if(result === 'noAuth'){ //noAuth가 온다면~
					alert('권한이 없습니다.')
				} else if(result === 'fail'){
					alert('삭제가 잘 안됐어요. 관리자에게 문의하세요')
				} else {
					alert('게시물이 정상적으로 삭제가 되었습니다.');
					getList(1, true);
				}
			});
		});
			
			
			
		
		
	
	
	
	
		//무한 스크롤 페이징
		//모든 게시판에 무한 스크롤 페이징 방식이 어울리는 것은 아니다.
		//사용자가 현재 위치를 알기가 힘들고, 원하는 페이지에 도달하기 위해
		//스크롤을 비효율적으로 많이 움직여야 할 수도 있다.
		//그렇기 때문에, 서비스하는 형식에 맞는 페이징 방식을 적용하면 된다.
		//window.onscroll = function(){
		//window.addEventListener = ('scroll', function(){ //브라우저 창에서 스크롤이 발생할 때마다
		//지금현재 사용자의 스크롤이 어느 부분에 위치했는지 계산한 다음, 바닥에 도달하면 getList를 부르면서 페이지를 하나 올려주고 reset을 false주자
		//console.log('스크롤 이벤트 발생!');
		//if(!isFinish){
			//윈도우(device)의 높이와 현재 스크롤 위치 값을 더한 뒤,
			//문서(컨텐츠)의 높이와 비교해서 같아졌다면 로직을 수행.
			//문서 높이 - 브라우저 창 높이 = 스크롤 창의 끝 높이    와 같다면 새로운 내용을 불러오기!
			//if(window.innerHeight + window.scrollY >= document.body.scrollHeight){
				//스크롤이 바닥에 닿았을 때, 페이지를 하나 올리고 겟리스트를 부르는게 새로운 내용을 불러오는 것이다.
				//reset 여부는 false를 줘서 누적해서 계속 불러오면 된다.
				//게시글을 한 번에 몇 개씩 불러 올지는 PageVO의 cpp를 조정하면 된다.
		/* 		console.log('페이징 발동!');
				getList(++page, false);
			} else {
				console.log('더 이상 불러올 목록이 없어요! 마지막 페이지 입니다.');
			}
		}
	} 
		
		원래 했던 무한 스크롤 페이징을 주석 처리 하고 다시 스로틀링을 구현한 코드르 적자
		
		쓰로틀링이란? 일정한 간격으로 함수를 실행.
				  사용자가 이벤트를 몇 번이나 발생시키든, 일정한 간격으로
				  한 번만 실행하도록 하는 기법이다.
				  마우스 움직임, 스크롤 이벤트 같은 짧은 주기로 자주 발생하는 경우에 사용하는 기법이다.
				  우리는 lodash 라는 라이브러리를 이용하여 구현할 것이다. 로다시는 맨위 헤더부분에 붙여놨다.
		*/
	
	
		const handleScroll = _.throttle(() => {
			console.log('throttle activate!');
			const scrollPosition = window.pageYOffset;
			const height = document.body.offsetHeight;
			const windowHeight = window.innerHeight;
			
			//더불러올 필요가 없는지 확인하자. 
			if(!isFinish){
				if(scrollPosition + windowHeight >= height * 0.9){ //스크롤 창이 90%에 도달하면 다음 페이지를 불러오자
					console.log('next Page call!');
				getList(++page, false);
				}
			}
		}, 1000); //1000밀리초 = 1초 > 1초에 한번 감지를 하자는 것.
		
	
		window.addEventListener('scroll', handleScroll);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		//자바 스크립트 파일 미리보기 기능
		function readURL(input) {
        	if (input.files && input.files[0]) {
        		
            	var reader = new FileReader(); //비동기처리를 위한 파읽을 읽는 자바스크립트 객체
            	//readAsDataURL 메서드는 컨텐츠를 특정 Blob 이나 File에서 읽어 오는 역할 (MDN참조)
	        	reader.readAsDataURL(input.files[0]); 
            	//파일업로드시 화면에 숨겨져있는 클래스fileDiv를 보이게한다
	            //$(".fileDiv").css("display", "block");
		document.querySelector('.fileDiv').style.display = 'block';            	

            	reader.onload = function(event) { //읽기 동작이 성공적으로 완료 되었을 때 실행되는 익명함수
                	//$('#fileImg').attr("src", event.target.result);
		document.getElementById('fileImg').setAttribute('src', event.target.result);		

                	console.log(event.target)//event.target은 이벤트로 선택된 요소를 의미
	        	}
        	}
	    }
		document.getElementById('file').onchange = function() {
	        readURL(this); //this는 #file자신 태그를 의미
	        
	    };
	</script>
	
	
	
</body>
</html>