package com.spring.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.db.model.ScoreVO;
import com.spring.db.repository.IScoreDAO;
import com.spring.db.repository.IScoreMapper;


@Service
public class ScoreService implements IScoreService {

	//일단 내비두고 DAO를 만들러가자. com.spring.db패키지에서 인터페이스만들자. 레파지토리 패키지를 만들고 IScoreDAO를 만들자
	
	@Autowired
	private IScoreMapper mapper;
	
	
	
	@Override
	public void insertScore(ScoreVO vo) {
		vo.calcData(); //총점과평균계산을 해주고 이제 디비에 들어갈 준비가 끝났다. 이제 DAO가필요하다. 
		System.out.println("service: " + vo);
		mapper.insertScore(vo);
	}

	@Override
	public List<ScoreVO> selectAllScores() {
		//딱히 할게 없으니 부르기만하자
		return mapper.selectAllScores(); //스코어DAO로가자
	}

	@Override
	public void deleteScore(int num) {
		mapper.deleteScore(num);
	}

	@Override
	public ScoreVO selectOne(int num) { // stuId를 넘겨받았다. 
		
		return mapper.selectOne(num); //dao야 이거줄게. 이제 다시 스코어DAO로가자
	}

}
