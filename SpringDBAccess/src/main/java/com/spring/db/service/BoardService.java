package com.spring.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.db.model.BoardVO;
import com.spring.db.repository.IBoardMapper;

@Service
public class BoardService implements IBoardService {
	
	
	/*
	 * @Autowired
	 * 
	 * @Qualifier("boardDAO") //오토와이어드랑 함께 사용할 수 있는데, 이 변수에 들어갈 수 있는 bean이 여러개라면,
	 * //퀄리파이어 라는 아노테이션을 이용해서 직접 지목을 할 수 있다. 이 dao라는 빈은 boardDAO라는 빈이다. 라면 스프링이 헷갈리지
	 * //않고 boardDAO를 주입하게 된다. private IBoardDAO dao; //주입이 필요하니 오토와이어드 달아주자
	 */	  
	  //일단주석해주고
	  @Autowired
	  private IBoardMapper mapper; //인터페이스 제작 후 IBoardDAO랑 똑같이 맞춰주고, 아래도 다 mapper로 바꿔줘야한다.
								     //이것들을 다 xml파일로 하자.
	
	  
	@Override
	public void insertArticle(BoardVO vo) { 
		mapper.insertArticle(vo);
	}

	@Override
	public List<BoardVO> getArticles() {
		return mapper.getArticles();
	}

	
	@Override
	public BoardVO getArticle(int bno) {
		return mapper.getArticle(bno);
	}

	
	@Override
	public void deleteArticle(int bno) {
		mapper.deleteArticle(bno);
	}

	
	@Override
	public void updateArticle(BoardVO vo) {
		mapper.updateArticle(vo);
	}

}
