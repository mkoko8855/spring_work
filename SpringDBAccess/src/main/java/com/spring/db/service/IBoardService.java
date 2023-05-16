package com.spring.db.service;

import java.util.List;

import com.spring.db.model.BoardVO;

public interface IBoardService {

	
		//게시글 등록
		void insertArticle(BoardVO vo); //리턴타입은 보이드, 리턴한거받아야지? BoardVO
		
		//전체 게시글 조회
		List<BoardVO> getArticles();
		
		//게시글 상세 보기
		BoardVO getArticle(int bno); //글 하나만 받으니 리턴타입은 BoardVO.  매개값은? 몇번글인지 알아야 하니 int bno
		
		//게시글 삭제
		void deleteArticle(int bno);
		
		//게시글 수정
		void updateArticle(BoardVO vo);
	
	
	
	
	
	
	
	
	
}
