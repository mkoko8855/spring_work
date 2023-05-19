package com.spring.myweb.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.myweb.command.UserVO;
import com.spring.myweb.user.mapper.IUserMapper;

import lombok.extern.slf4j.Slf4j;


@Service //빈등록해줘야함
@Slf4j
public class UserService implements IUserService {

	//유저서비스는 매퍼를 원하니(관계가있으니)
	@Autowired
	private IUserMapper mapper; //자동주입을 스프링에게 명령하겠다
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	
	@Override
	public int idCheck(String id) {
		return mapper.idCheck(id);
	}

	
	
	@Override
	public void join(UserVO vo) {

		//회원 비밀번호를 암호화 해서 인코딩을 진행할거다(메이븐레파지토리에서 pom.xml에 암호화관련 코드 써놨음)
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		log.info("암호화 하기 전 비밀번호: " + vo.getUserPw());
		
		//비밀번호를 암호화 해서 vo객체에 다시 저장할 것이다.
		String securePw = encoder.encode(vo.getUserPw()); //날것의 비번을 주면 encoder가 hash의 알고리즘 방식으로 인코딩을 진행해준다~
		log.info("암호화 후 비밀번호: " + securePw);
		vo.setUserPw(securePw); //전달 한 다음 
		mapper.join(vo); //조인진행~
	}

	
	
	@Override
	public UserVO login(String id, String pw) {
		//id정보를 기반으로 회원의 정보를 조회
		UserVO vo = getInfo(id); //겟인포를 불러서 id를 전달해주자. vo한테.
		//vo가 null일 가능성도 있잖아?
		if(vo != null) { //사용자가 아이디를 제대로 입력 했다면
			String dbPw = vo.getUserPw(); //DB에서 가져온 암호화 된 비밀번호이다. DB에서는 암호화된 비밀번호로만 이루어져 있다.
			
			//날 것의 비밀번호와 암호화된 비밀번호의 일치 여부를 알려주는 matches()
			if(encoder.matches(pw, dbPw)) { //비번과 암호화된 비번이 일치하면
				return vo; //vo를주고
			}
		}
		return null; //아니면 null줄 것이다.
	}

	
	
	
	
	@Override
	public UserVO getInfo(String id) {
		
		return mapper.getInfo(id);
	}

	
	
	
	
	@Override
	public void updateUser(UserVO vo) {

	}

}
