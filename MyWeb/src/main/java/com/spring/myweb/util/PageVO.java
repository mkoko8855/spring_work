package com.spring.myweb.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class PageVO {

	private int pageNum;
	private int cpp;
	
	
	
	//검색 요청이 필요한 필드를 추가.
	private String keyword;
	private String condition;
	//위 2가지를 추가해서 PageVO로 한번에 넘기게끔 어차피 getList들어오면 PageVO를 통해 페이징 처리할 거고, 검색 결과도 페이징할꺼니까. 안넘어오면 무시해서 안쓰면되고 사용자가 검색해서 있으면 SQL결과나오게끔 하면되고..
	
	
	
	
	


	//myPage에서 페이징을 구현하기 위한 필드를 추가
	private String loginId;
	//그럼이제 PageVO도 로그인 중인 id도 전달받을 수 있겠다. 


	
	
	
	
	
	public PageVO() {
		this.pageNum = 1;
		this.cpp = 10;
	}
	
	
	
	//get으로 시작하는 메서드 하나 선언해주자
	public int getPageStart() {
		/*
		  만약 사용자가 선택한 페이지번호가 1이면 뭘리턴해주면돼? 0이지
		  만약 사용자가 선택한 페이지번호가 2면 10을 리턴.
		  만약 사용자가 선택한 페이지번호가 3이면 20을 리턴
		  만약 사용자가 선택한 페이지번호가 n이면 (n-1)*cpp을 리턴
		  
		  즉,
		  pageNum:1 -> return 0
		  pageNum:2 -> return 10
		  pageNum:3 -> return 20
		  pageNum:n -> return (n-1)*cpp
		  
		 */
		return (pageNum - 1) * cpp;
		//그럼이제 프리보드매퍼.xml에서 얘를 불러주면 됨. #{pageStart}를 적으면 이 메서드를 부른단 뜻이다.
	}
	
	
	
}
