<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

    <%@ include file="../include/header.jsp" %>
    
    <section>
        <div class="container-fluid">
            <div class="row">
                <!--lg에서 9그리드, xs에서 전체그리드-->   
                <div class="col-lg-9 col-xs-12 board-table">
                    <div class="titlebox">
                        <p>자유게시판</p>
                    </div>
                    <hr>
                    
                    
                    
                    <!--form select를 가져온다 -->
                    <!-- 사용자가 검색 한 그 값도 창에 남길 수 있다. 써보자! -->
               <form action="<c:url value='/freeboard/freeList' />">
		 		    <div class="search-wrap">
                       <button type="submit" class="btn btn-info search-btn">검색</button>
                       <input type="text" name="keyword" class="form-control search-input" value="${pc.paging.keyword}"> <!-- 이 value값써야 검색 한 후에도 창에 잠깐 남아 있을거임. 즉, 작성자 해놓고 abc 써놓고 검색을 눌러도 abc가 남아있음 -->
                       <select name="condition" class="form-control search-select">
                            <option value="title" ${pc.paging.condition == 'title' ? 'selected' : ''}>제목</option>
                            <option value="content" ${pc.paging.condition == 'content' ? 'selected' : ''}>내용</option>
                            <option value="writer" ${pc.paging.condition == 'writer' ? 'selected' : ''}>작성자</option>
                            <option value="titleContent" ${pc.paging.condition == 'titleContent' ? 'selected' : ''}>제목+내용</option>
                            <!-- 제목 내용 작성자 제목+내용은 사용자에게 보여지는 내용이다. -->
                            <!-- SQL에 WHERE절을 이용해 WHERE title LIKE ? 와 같이 쓰겠지. -->
                            
                            <!-- 이 폼태그에는 keyword와 condition만을 값을 받는다. -->
                       </select>
                    </div>
		       </form>
                   
                   
                   
                   
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th class="board-title">제목</th>
                                <th>작성자</th>
                                <th>등록일</th>
                                <th>수정일</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var ="vo" items="${boardList}">
                            	<tr>
	                            	<td>${vo.bno}</td>
	                            	<td>
	                            		<a href="${pageContext.request.contextPath}/freeboard/content/${vo.bno}?pageNum=${pc.paging.pageNum}&cpp=${pc.paging.cpp}&keyword=${pc.paging.keyword}&condition=${pc.paging.condition}">${vo.title}</a> <!-- 이렇게 쓰고 프리보드컨트롤러가서 글 상세보기 처리 를 보면 겟맵핑자리를 좀 다르게 적었음 -->
	                            	</td>
	                            	<td>${vo.writer}</td>
	                            	<td>
	                            		<fmt:parseDate value="${vo.regDate}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime" type="both"/>
	                            		<fmt:formatDate value="${parsedDateTime}" pattern="yyyy년 MM월 dd일 HH시 mm분"/>
	                            	</td>	
	                            	<td>
                                    <fmt:parseDate value="${vo.updateDate}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedUpdateTime" type="both"/>
                                    <fmt:formatDate value="${parsedUpdateTime}" pattern="yyyy년 MM월 dd일 HH시 mm분"/>
                                </td>
                            	</tr>
                            </c:forEach>
                        </tbody>
                        
                    </table>


                    <!--페이지 네이션을 가져옴 (페이징 하는 곳)-->
		    <form action="${pageContext.request.contextPath}/freeboard/freeList" name="pageForm"> <!-- 아래 버튼들이 공통적으로 가져야할 공통. 일일이 a태그달일이없겠지~ 그리고 submit이 없기에 스크립트를 쓰기 위해 name달아주자-->
                    <div class="text-center">
                    <hr>
                    <ul id="pagination" class="pagination pagination-sm">
                    	<c:if test="${pc.prev}"> <!-- false면 안나와도돼. true면나오고. -->
                        	<li><a href="#" data-pagenum="${pc.beginPage-1}">이전</a></li> <!-- 자바스크립트로끌어올 수 있는 data-. 아래다가 스크립트적어줄건데 그떄 필요하다--> 
                    	</c:if>
                    	
                    	<c:forEach var="num" begin="${pc.beginPage}" end="${pc.endPage}"> <!-- 제어변수는 num으로 정해보았다. --> <!-- 스텝은 생략하면 1이다. -->
                        <li class="${pc.paging.pageNum == num ? 'active' : ''}"> <!-- 사용자가 선택한 페이지번호가 반복문돌리고있는 페이지넘과 같으면 액티브해주고 아니면 비워라! 그러면 그 액티브가 색칠해주는것이다 -->
                        <a href="#" data-pagenum="${num}">${num}</a> <!-- 오른쪽num이 3이면 왼쪽도 3번이여야하니까..이 줄은 둘다 num이겠지 -->
                        </li>
                        </c:forEach>
                        
                        <c:if test="${pc.next}"> <!-- 넥스트가 트루면 보여주고 폴스면 보여주지마~ -->
                        	<li><a href="#" data-pagenum="${pc.endPage+1}" >다음</a></li>
                        </c:if>
                    </ul>
                    <button type="button" class="btn btn-info" onclick="location.href='${pageContext.request.contextPath}/freeboard/regist'">글쓰기</button>
                    </div>
                    
                    
                    <input type="hidden" name="pageNum" value="${pc.paging.pageNum}">
                    <input type="hidden" name="cpp" value="${pc.paging.cpp}">
                    <input type="hidden" name="keyword" value="${pc.paging.keyword}">
                    <input type="hidden" name="condition" value="${pc.paging.condition}">
	                    
                    
		    </form>

                </div>
            </div>
        </div>
	</section>

<%@ include file="../include/footer.jsp" %>


	<script>
		window.onload = function(){ //브라우저 창이 로딩이 완료가 된 후, 실행할 것을 보장하는 이벤트. 즉, 혹시 모르니 보험으로 써주자..
			
			//만약에 사용자가 페이지 관련 버튼을 클릭 했을 때,
			//기존에는 각각의 a태그의 href에다가 각각 다른 url을 작성해서 보내줬다면,
			//이제는 클릭한 그 버튼이 무엇인지를 확인해서 그 버튼에 맞는 페이지 정보를
			//자바스크립트로 끌고와서 요청을 보내 주겠다. (값을 담아서 submit떄려주겠다~)
			//그래서 위에 data- 을 썼다.
			
			//5개씩보여줬다면 버튼이 7갠데 일일이 하지말고 이벤트전파사용하자
			document.getElementById('pagination').addEventListener('click', e => {
				if(!e.target.matches('a')){ //이벤트가 발생한 타겟이 a태그가 아니라면,
					return; //끝내. 즉, a태그에다가만 이벤트 발생하게 할것이다. 7개를 data- 속성으로 구분할 것이다.
				}
				
				e.preventDefault(); //a태그의 고유 기능을 중지하자.
				
				//현재 이벤트가 발생한 요소(이전 또는 다음 또는 페이지 버튼이겠지)
				//그 요소의 data-pagenum의 값을 얻어서 변수에 저장하자.
				const value = e.target.dataset.pagenum; //값을가져오겠지~
				
				//이제 이전, 다음, 페이지 버튼 중의 pagenum값을 value변수로 받았으니 form action에 묻혀줘야지.
				//묻혀주려면 form태그를 지목해야겠지. 그리고 폼 태그 안에 hidden을 써줬다.
				//즉, 페이지 버튼들을 감싸고 있는 폼 태그를 지목하여 그 안에 숨겨져 있는
				//pageNum이라는 input태그의 value에 위에서 얻은 data-pageNum값을 삽입한 후
				//submit을 진행하자.
				document.pageForm.pageNum.value = value;
				document.pageForm.submit(); //그러고나서 submit해주겠다~
				
				
				
			});
			
			
		}
		
	
	</script>