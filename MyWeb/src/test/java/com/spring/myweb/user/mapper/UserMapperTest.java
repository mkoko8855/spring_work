package com.spring.myweb.user.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spring.myweb.command.UserVO;
import com.spring.myweb.util.PageVO;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/config/db-config.xml") // 설정부르기. 여기에 마이바티스설정이 돼있으니 mapper라는 변수를 생성할
@Slf4j											// 수 있다.
public class UserMapperTest {
	
	// 설정을 불러왔으니
	@Autowired
	private IUserMapper mapper;
	// 를 사용할 수 있다.

	// 이제 진짜 테스트 진행하자
	@Test
	@DisplayName("회원 가입을 진행 했을 때 회원가입에 성공해야 한다.")
	void registTest() {
		UserVO vo = new UserVO();
		// 일단 낫널 값만 주자. 널을 받아도 되는애들은 안주고.
		vo.setUserId("ccc1234");
		vo.setUserPw("aaa1111!");
		vo.setUserName("홍길동");
		mapper.join(vo);
		// 이제 join메서드 완성하러가자. UserMapper.xml로
	}

	
	@Test
	@DisplayName("존재하는 회원 아이디를 조회했을 시 1이 리턴이 되어야 한다.")
	void checkIdTest() {
		String id = "abc1234";
		assertEquals(1, mapper.idCheck(id));
	}

	

	@Test
	@DisplayName("존재하는 회원 아이디와 올바른 비밀번호를 입력했을 시 회원의 정보가 리턴되어야 한다.")
	void loginTest() {
		String id = "abc1234";
		String pw = "aaa1111!";
		
		
		/*
		 * 1. Map으로 포장하는 방법 
		 * Map<String, String> data = new HashMap<>(); 
		 * data.put("id", "abc1234"); 
		 * data.put("pw", "aaa1111!"); 
		 * 그리고나서 맵퍼한테 이 맵을 보내는 것이다.
		 * assertNotnull(mapper.login(data));
		 */
		
		
		
		//두번쨰방법은 그냥 이대로하고 실행해봐도됨~
		
		
		
		
		assertNotNull(mapper.login(id));
	}

	
	
	
	@Test
	@DisplayName("존재하지 않는 회원의 아이디를 입력하면 null이 올 것이다.")
	void getInfoTest() {

		PageVO paging = new PageVO();
		//그러면 페이지는 1번. cpp는 10이겠지 불러보자
		UserVO vo = mapper.getInfo("abc1234", paging); //그럼이제 userVO를 주겠지
		
		log.info(vo.toString());
		
		
		//assertNull(mapper.getInfo("merong"));
		
	}

	
	
	
	@Test
	@DisplayName("id를 제외한 회원의 정보를 수정할 수 있다.") // 값은 의미로주자
	void updateTest() {
		UserVO vo = new UserVO();
		
		vo.setUserId("abc1234");
		vo.setUserPw("aaa1111!");
		vo.setUserName("홍수정");
		vo.setUserEmail1("abc1234");
		vo.setUserEmail2("naver.com");
		
		mapper.updateUser(vo);
		
		//assertEquals(mapper.getInfo("abc1234").getUserName(), vo.getUserName()); 
		
		
	}

	
	
	
	
}
