package com.spring.myweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.myweb.user.service.IUserService;

import lombok.extern.slf4j.Slf4j;

@Controller //이렇게 등록 해놔야 핸들러맵핑이 이 클래스를 찾을 수 있음

//공통URL맵핑도해주자
@RequestMapping("/user")
@Slf4j
public class UserController {

	@Autowired
	private IUserService service; //service라는 변수에 자동으로 주입해줘~
	
	
	
	//회원가입 페이지로 이동하는 메서드(join누르면감)
	@GetMapping("/userJoin")
	public void userJoin() {
		//말그대로 페이지 이동을 위한 메서드다. void처리했으니 user폴더의 userJoin으로간다.
	}
	
	
	
	
	//userjoin.jsp 에서 유저컨트롤러로 가자 -> 이제 유저컨트롤러로와서

	//아이디 중복 확인(비동기)
	//일반컨트롤러에서 비동기로 받기 위해 레스폰스바디
	@ResponseBody
	@PostMapping("/idCheck")
	public String idCheck(@RequestBody String userId) {
		log.info("화면단으로부터 전달된 값: " + userId);
		
		int result = service.idCheck(userId);
		if(result == 1) {
			return "duplicated";
		} else {
			return "ok";
		}
	}
	
	
	//이메일 인증
	@GetMapping("/mailCheck")
	@ResponseBody
	public String mailCheck(String email) {
		log.info("이메일 인증 요청 들어옴: " + email);
		return null;
	}
	
	
	
	
	
	
	
	
	
}
