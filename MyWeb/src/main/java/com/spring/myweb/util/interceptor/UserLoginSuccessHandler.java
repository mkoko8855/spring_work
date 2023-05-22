package com.spring.myweb.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.spring.myweb.command.UserVO;

import lombok.extern.slf4j.Slf4j;

//인터셉터로 사용할 클래스는 HandlerIntercpetor 라는 인터페이스를 구현해야 한다.

@Slf4j
public class UserLoginSuccessHandler implements HandlerInterceptor {

//인터셉터컨피그에 빈등록해놨다.	
	
	
	//preHandle은 컨트롤러로 요청이 들어가기 전 처리해야 할 로직을 작성하게 된다.
	
	// /userLogin 이라는 요청이 마무리 된 후, 뷰리졸버로 전달이 되기 전에
	// 로그인 성공과 실패 여부에 따라 처리할 로직을 작성할 예정이다.
	@Override     //컨트롤러가 일처리를 하기 전인 preHandle(권한 검사할때)
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("나는 preHandle 이다!!!"); //실행순서보려고..이게 먼저 출력이 되고 컨트롤러것이 출력이 된다.
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	
	
	
	//postHandle은 컨트롤러를 나갈 떄 공통 처리해야 할 내용을 작성하게 된다.     //컨트롤러가 일처리를 한 후인 postHandle(컨트롤러가 제대로 처리했는지 검사하는 postHandle)
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, //이 메서드는 자동으로 호출이 된다.
			ModelAndView modelAndView) throws Exception { //유저컨트롤러에 //로그인 요청 이라는 메서드에 담긴 로그인성공하면 UserVO, 실패하면 null이라는 정보를 지금 우리가 작성하고 있는 이 핸들러가 대신 해줄 것이다.
		
		log.info("로그인 인터셉터가 동작했다!");
		log.info("요청 방식이 뭐야? " + request.getMethod()); //요청방식이 겟인지 포스트인지 파악할 수 있다. 근데 이걸 왜 확인해? 유저컨트롤러에 //로그인페이지로 이동 요청 메서드와  //로그인 요청 메서드가 둘다 겟과 포스트로 나눠져 있기 떄문이다.
		
		if(request.getMethod().equals("POST")) { //겟메서드의 리턴타입이 스트링이니 equals를 사용했다. (요청방식이 포스트니?)
			ModelMap map = modelAndView.getModelMap(); //getModelMap은 Model객체를 꺼내는 메서드이다.즉, 모델 만 꺼낼 것이다.
			//이제 모델한테 값달라하자(꺼내달라하자)
			String id = (String) map.get("user"); //모델 내에 user라는 이름의 데이터 꺼내기. 모델은 모든 걸 다 받으니 모든걸 리턴할 수 있어야 한다. 그래서 리턴 타입이 UserVO타입이다. (그러나 5/22. 조인을 강제하기 위해 id로바꿔줌)
			
			//이제 vo의 값이 널이라면 로그인 실패. vo에 객체가 담겨져 있다면 로그인 성공.
			//log.info("인터셉터 내부에서 user 확인: " + vo.toString());
			
			
			if(id != null) { //로그인 성공
				log.info("로그인 성공 로직이 동작합니다!");
				//로그인 성공한 회원에게 세션 데이터를 생성해서 로그인 유지를 하게 해 줌.
				//로그인 성공하면 세션받아오자
				HttpSession session = request.getSession(); 
				session.setAttribute("login", id);//로그인이라는 거에 id(5/22 조인을강제하기위해. vo가아님.) 를 저장한다.
				//위가 void이기 떄문에 usergLoin으로 간다고. 포워드로. 근데 로그인 성공했는데 굳이 로그인 페이지로 갈 필요 없잖아?
				//즉, 여기서 흐름을바꾸자~
				response.sendRedirect(request.getContextPath() + "/"); //폼컨트롤러가 요청을 받을 수 있도록 조작할거다
				
			} else { //id가 null이면 로그인을 실패 했다는 뜻
				modelAndView.addObject("msg", "loginFail"); //msg라는 이름으로 loginfail을 넣어놈(msg는 userjoin.jsp에 const로 선언했었다)
				
				//목적지가 userLogin.jsp이다.void니까.
				
			}
			
			
		}
	}
	
	
	
	
	
}
