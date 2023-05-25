package com.spring.myweb.snsboard.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spring.myweb.command.SnsBoardVO;
import com.spring.myweb.util.PageVO;

public interface ISnsBoardService {

	
	//등록
		void insert(SnsBoardVO vo, MultipartFile file); //인서트니까 보이드
		
		
		
		//목록
		List<SnsBoardVO> getList(PageVO paging); //여러개니까 리스트. 페이징은 들어가는데 처리는 앞에서랑 조금 다르게하자.
		
		
		
		//상세
		SnsBoardVO getDetail(int bno); //글 정보 하나만 받을꺼니 SnsBoardVO로 받자.
		
		
		
		
		//삭제
		void delete(int bno);
	
	
	
	
}
