package com.spring.myweb.user.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spring.myweb.command.UserVO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/config/db-config.xml") //설정부르기. 여기에 마이바티스설정이 돼있으니 mapper라는 변수를 생성할 수 있다.
public class UserMapperTest {
	
	//설정을 불러왔으니
	@Autowired
	private IUserMapper mapper;
	//를 사용할 수 있다.
	
	
	//이제 진짜 테스트 진행하자
	@Test
	@DisplayName("회원 가입을 진행 했을 때 회원가입에 성공해야 한다.")
	void registTest() {
		UserVO vo = new UserVO();
		//일단 낫널 값만 주자. 널을 받아도 되는애들은 안주고.
		vo.setUserId("abc1234");
		vo.setUserPw("aaa1111!");
		vo.setUserName("홍길동");
		mapper.join(vo);
		//이제 join메서드 완성하러가자. UserMapper.xml로
		
	}
	
	
	
		@Test
		@DisplayName("존재하는 회원 아이디를 조회했을 시 1이 리턴이 되어야 한다.")
		void checkIdTest() {
			/*
			 * UserVO vo = new UserVO(); int id = mapper.idCheck(id);
			 * 
			 * for(UserVO vo : id) { System.out.println(id); }
			 */
			
			
		}
		
		
		@Test
		@DisplayName("존재하는 회원 아이디와 올바른 비밀번호를 입력했을 시 회원의 정보가 리턴되어야 한다.")
		void loginTest() {
			
		}
	
	
		
		@Test
		@DisplayName("존재하지 않는 회원의 아이디를 입력하면 null이 올 것이다.")
		void getInfoTest() {
			
		}
		
		
		
		@Test
		@DisplayName("id를 제외한 회원의 정보를 수정할 수 있다.") //값은 의미로주자
		void updateTest() {
			UserVO vo = new UserVO();
		}
	
	
	
	
	
	
}
