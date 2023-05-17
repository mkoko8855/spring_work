package com.spring.myweb.user.mapper;

import com.spring.myweb.command.UserVO;

public interface IUserMapper {

	
	//회원가입 할 때, 필요한 것들 적어주자
	
	
	
	
	
	//아이디 중복 확인
	int idCheck(String id); //int로 리턴해도됨. sql이 SELECT count(*) FROM users WHERE user_id = ? 로 들어갈 수도 있기 때문이다. 조회가 안되면 0, 조회가 되면 1
	
	
	
	
	
	
	//회원 가입
	void join(UserVO vo); //리턴은 sql에서 할거없으니 보이드로~
	
	
	
	
	
	//로그인
	UserVO login(String id, String pw); //리턴은 UserVO로~
	
	
	
	
	
	//회원 정보 얻어오기
	//나중에 작성 글 목록까지 불러올 것이다
	UserVO getInfo(String id);
	
	
	
	
	
	//회원 정보 수정
	void updateUser(UserVO vo);
	
	
	
	//맵퍼 작성했으니 SQL써보자 그리고 TEST할꺼다.
	//mappers폴더에 UserMapper.xml파일을 만들어주자
	
	
	
	
	
	
}
