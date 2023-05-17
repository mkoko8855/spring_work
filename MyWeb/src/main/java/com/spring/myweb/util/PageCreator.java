package com.spring.myweb.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageCreator { //얘는 페이징 처리가 들어갈 떄 생성이 된다. 결국 calcDateOfPage메서드를 부르기 위해 생성했다. 메서드를 부르기 전에 값을 불러와야 하니까 아래 생성자 하나 더 추가해주자

	//버튼 알고리즘을 구현하기 위해 무엇이 필요했는가?
	//사용자가 선택한 페이지 번호(pageNum), 한 화면에 보여줄 게시물의 개수(Cpp)가 필요했다.
	//그 정보는 전부 PageVO가 들고 있다.
	private PageVO paging;
	
	//그리고 게시물의 총 개수가 필요하고 끝페이지와 시작페이지도 필요하다
	private int articleTotalCount, endPage, beginPage;
	
	
	//그리고 다음 버튼과 이전 버튼의 활성화 여부 표시가 필요하다
	private boolean prev, next;
	
	//그리고 버튼의 개수
	private final int buttonNum = 5;
	
	
	//생성자를 뽑아서 모든 값을 받는게 아니라 PageCreator가 불릴 때 pageVo가 전달돼야 하니까.. 그리고 articleTotalcount도 전달돼야하니까 만들자
	public PageCreator(PageVO paging, int articleTotalCount) {
		this.paging = paging;
		this.articleTotalCount = articleTotalCount;
		calcDataOfPage();
	}
	
	
	
	
	//paging과 articleTotalCount는 이미 이 메서드에 와있을 것이다.
	private void calcDataOfPage() {  
		//제일먼저 구현해야할 것은 끝페이지 번호이다.
		//사용자가 예를 들어 지금 7페이지라면, 나누기 5하면 1.몇 나올테니 올림하면 2다. 2*5=10. 즉, 끝페이지는 10페이지임.
		endPage = (int)(Math.ceil(paging.getPageNum() / (double) buttonNum) * buttonNum);
		//끝페이지 번호 구했으니 시작 페이지도 구할 수 있음
		//사용자가 7페이지라면, 끝페이지는 10이니까 10 - 5 + 1하니까 6이다.
		beginPage = endPage - buttonNum + 1; //즉, 시작 페이지는 6이다.
		
		//시작 페이지 구했으면 이전 버튼 활성화 여부가 나오겠다.
		//prev는 버튼 여부로, 시작페이지가 1이면 활성화된다.
		prev = (beginPage ==1) ? false : true;
		
		//게시물의 총 개수가 보정 전 끝페이지 번호 * 한 화면의 보여줄 게시물의 개수 보다 더 작다면 false
		//끝페이지는 10. 한화면의 게시물을 10개씩 보여준다고 치면 10페이지 까지는 총 100개의게시물을 보여주겠지.
		next = articleTotalCount <= (endPage * paging.getCpp()) ? false : true;
		
		
		//이제 끝페이지 보정
		if(!next) {
			endPage =(int)Math.ceil(articleTotalCount / (double)paging.getCpp()); //끝페이지에 보정하겠다
			//총 게시물이 87개라면 끝페이지 번호는 9까지만 나오면 된다.
		}
		
		
		//근데 calcDateOfPage() 메서드는 언제불러?
		//
		
	}
	
	
	
	
}
