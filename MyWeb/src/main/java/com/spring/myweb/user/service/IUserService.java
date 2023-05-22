package com.spring.myweb.user.service;

import com.spring.myweb.command.UserVO;
import com.spring.myweb.util.PageVO;

public interface IUserService {

	
	//회원가입 할 때, 필요한 것들 적어주자
	
	
	
	
	
	//아이디 중복 확인
	int idCheck(String id); //int로 리턴해도됨. sql이 SELECT count(*) FROM users WHERE user_id = ? 로 들어갈 수도 있기 때문이다. 조회가 안되면 0, 조회가 되면 1
	
	
	
	
	
	
	//회원 가입
	void join(UserVO vo); //리턴은 sql에서 할거없으니 보이드로~
	
	
	
	
	
	//로그인
	//MyBatis로 DB연동을 진행할 떄, 파라미터 값이 2개 이상일 때 그냥 보내시면
	//에러가 발생하기 때문에 조치가 필요하다.
	String login(String id, String pw); //(5/22 이것도 String으로바꿔줌. 조인을 강제하기위해)  그러나 Mybatis는 값을 2개 줄때는 인식을 못한다. 값을 2개 이상 있을 때는 맵핑을 해야 한다. 즉, id와 pw가 누군지 알려줘야 한다. 
	//즉, 3가지 방법이 있는데
	//1. Map으로 포장하는 것이다. > String id, String pw가 아닌 Map<String, String> map을 써주고 UserMapperTest가서 실험ㄱㄱ
	
	//2. @Param을 이용해서 이름을 붙여주는 방법. (xml파일에서 해당 값을 지목할 수 있는 이름 붙이기) -> String id, String pw가 아니라 @param("id") String id, @param("pw") String pw); 라고 이름을 지어주면서 만들자. (메롱도 되지만 값을 UserMapper.xml에서 #{merong} 이라고 적어야함)
	
	//3. 클래스를 디자인해서 객체 하나만 매개값으로 보내는 방법.(즉, String id, String pw가 아니라 UserVO vo로 해도 된다.)
	
	//위 3개중 하나를 상황에 맞게 적절하게 선택하면 된다.
	
	
	
	
	//회원 정보 얻어오기
	//나중에 작성 글 목록까지 불러올 것이다
	UserVO getInfo(String id, PageVO vo); //유저서비스도 PageVO vo써주자
	
	
	
	
	
	//회원 정보 수정
	void updateUser(UserVO vo);
	
	
	
	//맵퍼 작성했으니 SQL써보자 그리고 TEST할꺼다.
	//mappers폴더에 UserMapper.xml파일을 만들어주자
	
	
	
	
	
	
}
