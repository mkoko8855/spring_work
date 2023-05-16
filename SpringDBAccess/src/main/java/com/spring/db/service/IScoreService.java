package com.spring.db.service;

import java.util.List;

import com.spring.db.model.ScoreVO;

public interface IScoreService {

	//점수 등록 기능
	void insertScore(ScoreVO vo); //매개값으론 컨트롤러가받은 ScoreVO vo를 그대로 전달하면되겠지.  
	
	
	
	//점수 전체 조회 기능
	List<ScoreVO>selectAllScores(); 
	
	
	
	
	//점수 삭제 기능
	void deleteScore(int num); //sql문이 delete들어갈꺼니 리턴할게없잖아 보이드. > 매개값으로는 누굴 삭제할지 알려줘야하니 프라이머리키인 int num(stuId).
	
	
	
	
	//점수 개별 조회 기능
	ScoreVO selectOne(int num);  //한명 조회하는거니까 scoreVO로. 매개값으로는 프라이머리키인거겠지.
	
	
	//이제 service패키지에 ScoreService클래스를 만들어주러가자. add로 인터페이스를 IScore로잡아주고 만들자~
	//즉, 인터페이스를 구현하는 클래스를 만들자~
	
	
	
	
	
	
	
}
