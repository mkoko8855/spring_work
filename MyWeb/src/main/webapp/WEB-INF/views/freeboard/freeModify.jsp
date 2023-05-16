<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	<%@ include file="../include/header.jsp" %>


    <section>
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-md-9 write-wrap">
                        <div class="titlebox">
                            <p>수정하기</p>
                        </div>
                        
                        <form action="${pageContext.request.contextPath}/freeboard/update" method="post" name="updateForm">
                            <div class="form-group">
                                <label>번호</label>
                                <input class="form-control" name="bno" value="${article.bno}" readonly>
                            </div>
                            <div class="form-group">
                                <label>작성자</label>
                                <input class="form-control" name="writer" value="${article.writer}" readonly>
                            </div>    
                            <div class="form-group">
                                <label>제목</label>
                                <input class="form-control" name="title" value="${article.title}">
                            </div>

                            <div class="form-group">
                                <label>내용</label>
                                <textarea class="form-control" rows="10" name="content">${article.content}</textarea>
                            </div>

                            <button type="button" id="listBtn" class="btn btn-dark">목록</button>    
                            <button type="button" id="updateBtn" class="btn btn-primary">변경</button> <!-- 폼태그안에는 서브밋이 2개가 올 수 없음 -->
                            <button type="button" id="delBtn" class="btn btn-info">삭제</button>
                    </form>
                </div>
            </div>
        </div>
        </section>
      
      
      
      
      	<%@ include file="../include/footer.jsp" %>
      	

        <script>
            //목록 으로 이동 처리
            document.getElementById('listBtn').onclick = function(){
                location.href="${pageContext.request.contextPath}/freeboard/freeList"
            }

            const $form = document.updateForm; //폼은 이렇게 name에 바로 요소 취득 가능


            //수정과 삭제를 구분하는 처리 (수정 버튼을 클릭했을 때 이벤트 처리를 해보자)
            document.getElementById('updateBtn').onclick = function(){
                if($form.title.value === ''){
                    alert('제목은 필수 항목 입니다.');
                    $form.title.focus();
                    return;
                } else if($form.content.value === ''){
                    alert('내용은 필수 항목 입니다.');
                    $form.content.focus();
                    return;
                } else {
                    $form.submit();
                }
            }


            //삭제 버튼을 눌렀을 때 이벤트 처리
            document.getElementById('delBtn').onclick = (e) => {  //이렇게써도됨~
                if(confirm('정말 삭제를 하시겠어요?')){
                    $form.setAttribute('action', '${pageContext.request.contextPath}/freeboard/delete'); //셋어트리뷰트는 속성을 바꿈. 즉, 삭제 버튼을 누르면 한번 눌어보고 그래도 확인을 누른다면 폼의 action을 /freeboard/delete로 바꾼다는 것이다.
                    $form.submit();
                }
            }
        </script>