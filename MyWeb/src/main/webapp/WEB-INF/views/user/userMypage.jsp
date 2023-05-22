<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- 함수제공하는 기능이있음 -->


<%@ include file="../include/header.jsp"%>


<section>
	<!--Toggleable / Dynamic Tabs긁어옴-->
	<div class="container">
		<div class="row">
			<div class="col-sm-12 col-md-10 col-lg-9 myInfo">
				<div class="titlebox">MEMBER INFO</div>

				<ul class="nav nav-tabs tabs-style">
					<li class="active"><a data-toggle="tab" href="#info">내정보</a></li>
					<li><a data-toggle="tab" href="#myBoard">내글</a></li>
					<li><a data-toggle="tab" href="#menu2">Menu 2</a></li>
				</ul>

				<div class="tab-content">
					<div id="info" class="tab-pane fade in active">

						<p>*표시는 필수 입력 표시입니다</p>
						<form action="${pageContext.request.contextPath}/user/userUpdate/"
							method="post" name="updateForm">
							<table class="table">
								<tbody class="m-control">
									<tr>
										<td class="m-title">*ID</td>
										<td><input class="form-control input-sm" name="userId"
											value="${login}" readonly></td>
									</tr>
									<tr>
										<td class="m-title">*이름</td>
										<td><input class="form-control input-sm" name="userName"
											value="${userInfo.userName}"></td>
									</tr>
									<tr>
										<td class="m-title">*비밀번호</td>
										<td><input class="form-control input-sm" name="userPw"></td>
									</tr>
									<tr>
										<td class="m-title">*비밀번호확인</td>
										<td><input class="form-control input-sm" name="userPwChk"></td>
									</tr>
									<tr>
										<td class="m-title">*E-mail</td>
										<td><input class="form-control input-sm" id="userEmail1"
											name="userEmail1" value="${userInfo.userEmail1}"> <select
											class="form-control input-sm sel" id="userEmail2"
											name="userEmail2">
												<option
													${userInfo.userEmail2 == '@naver.com' ? 'selected' : ''}>@naver.com</option>
												<option
													${userInfo.userEmail2 == '@gmail.com' ? 'selected' : ''}>@gmail.com</option>
												<option
													${userInfo.userEmail2 == '@daum.net' ? 'selected' : ''}>@daum.net</option>
										</select>
											<button type="button" id="mail-check-btn"
												class="btn btn-primary">이메일 인증</button></td>
									</tr>
									<tr id="mailAuth">
										<td>인증번호 입력란</td>
										<td><input type="text"
											class="form-control mail-check-input"
											placeholder="인증번호 6자리를 입력하세요." maxlength="6"
											disabled="disabled">
											<button type="button" id="mail-auth-btn"
												class="btn btn-primary">인증 확인</button></td>
									</tr>
									<tr>
										<td class="m-title">*휴대폰</td>
										<td><select class="form-control input-sm sel"
											name="userPhone1">
												<option ${userInfo.userPhone1 == '010' ? 'selected' : ''}>010</option>
												<option ${userInfo.userPhone1 == '011' ? 'selected' : ''}>011</option>
												<option ${userInfo.userPhone1 == '017' ? 'selected' : ''}>017</option>
												<option ${userInfo.userPhone1 == '018' ? 'selected' : ''}>018</option>
										</select> <input class="form-control input-sm" name="userPhone2"></td>
									</tr>
									<tr>
										<td class="m-title">*우편번호</td>
										<td><input class="form-control input-sm"
											name="addrZipNum" value="${userInfo.addrZipNum}" readonly>
											<button type="button" class="btn btn-primary" id="addBtn">주소찾기</button>
										</td>
									</tr>
									<tr>
										<td class="m-title">*주소</td>
										<td><input class="form-control input-sm add"
											name="addrBasic" value="${userInfo.addrBasic}"></td>
									</tr>
									<tr>
										<td class="m-title">*상세주소</td>
										<td><input class="form-control input-sm add"
											name="addrDetail" value="${userInfo.addrDetail}"></td>
									</tr>
								</tbody>
							</table>
						</form>

						<div class="titlefoot">
							<button class="btn">수정</button>
							<button class="btn">목록</button>
						</div>
					</div>
					<!-- 첫번째 토글 끝 -->




					<!-- 두번째 토글 메뉴의 시작 -->
					<div id="myBoard" class="tab-pane fade">
						<p>*내 게시글 관리</p>
						<form>
							<table class="table">
								<thead>
									<tr>
										<td>번호</td>
										<td>제목</td>
										<td>작성일</td>
									</tr>
								</thead>



								<!-- sql보면 left join걸려있는데, 게시물 작성을 안해도 회원 정보가 와야 한다. 게시글이 여러개면 반복문이 좋겠지만 회원가입을 하고 글을 안썼다면 반복문 돌릴 필요가 없다. 
									 userInfo안에 boardList가 0보다 크다면 글을 쓴 것이고 그렇지 않다면 글을 쓰지 않았다는 것이다.
								 -->
								<tbody>
									<c:if test="${fn:length(userInfo.userBoardList) > 0}">
										<c:forEach var="vo" items="${userInfo.userBoardList}">
											<!-- freeboardVO 라는 vo로정함. vo로 하나씩 들어갈거임. 이제 뿌리자 -->
											<tr>
												<td>${vo.bno}</td>
												<td><a href="##">${vo.title}</a></td>
												<td><fmt:parseDate value="${vo.regDate}"
														pattern="yyyy-MM-dd'T'HH:mm:ss" var="parseDate"
														type="both" /> <fmt:formatDate value="${parsedDate}"
														pattern="yyyy년 MM월 dd일 HH:mm" /></td>
												<td>~~~~~</td>
											</tr>
										</c:forEach>


										<!-- freelist.jsp에서 긁어왔음. 페이징처리하는부분 -->
										<div class="text-center">
											<hr>
											<ul id="pagination" class="pagination pagination-sm">
												<c:if test="${pc.prev}">
													<!-- false면 안나와도돼. true면나오고. -->
													<li><a href="#" data-pagenum="${pc.beginPage-1}">이전</a></li>
													<!-- 자바스크립트로끌어올 수 있는 data-. 아래다가 스크립트적어줄건데 그떄 필요하다-->
												</c:if>

												<c:forEach var="num" begin="${pc.beginPage}"
													end="${pc.endPage}">
													<!-- 제어변수는 num으로 정해보았다. -->
													<!-- 스텝은 생략하면 1이다. -->
													<li class="${pc.paging.pageNum == num ? 'active' : ''}">
														<!-- 사용자가 선택한 페이지번호가 반복문돌리고있는 페이지넘과 같으면 액티브해주고 아니면 비워라! 그러면 그 액티브가 색칠해주는것이다 -->
														<a href="#" data-pagenum="${num}">${num}</a> <!-- 오른쪽num이 3이면 왼쪽도 3번이여야하니까..이 줄은 둘다 num이겠지 -->
													</li>
												</c:forEach>

												<c:if test="${pc.next}">
													<!-- 넥스트가 트루면 보여주고 폴스면 보여주지마~ -->
													<li><a href="#" data-pagenum="${pc.endPage+1}">다음</a></li>
												</c:if>
											</ul>
										</div>
										<c:if test="${fn:length(userInfo.userBoardList) <= 0}">
											<!-- 0보다 작거나 같다면 이 사람은 글을 아직 작성 안한 것 -->
											<h2>아직 글을 작성하지 않았습니다.</h2>
										</c:if>
									</c:if>
								</tbody>





							</table>
						</form>
					</div>
					<!-- 두번째 토글 끝 -->
					<div id="menu2" class="tab-pane fade">
						<h3>Menu 2</h3>
						<p>Some content in menu 2.</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>



<%@ include file="../include/footer.jsp"%>

