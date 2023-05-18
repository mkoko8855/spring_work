package com.spring.myweb.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.myweb.command.UserVO;
import com.spring.myweb.user.mapper.IUserMapper;


@Service //빈등록해줘야함
public class UserService implements IUserService {

	//유저서비스는 매퍼를 원하니(관계가있으니)
	@Autowired
	private IUserMapper mapper; //자동주입을 스프링에게 명령하겠다
	
	
	@Override
	public int idCheck(String id) {
		return mapper.idCheck(id);
	}

	@Override
	public void join(UserVO vo) {
		
	}

	@Override
	public UserVO login(String id, String pw) {
		return null;
	}

	@Override
	public UserVO getInfo(String id) {
		return null;
	}

	@Override
	public void updateUser(UserVO vo) {

	}

}
