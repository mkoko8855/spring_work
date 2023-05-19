package com.spring.myweb.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class UserAuthHandler implements HandlerInterceptor {

	
	//회원 권한이 필요한 페이지 요청이 들어 왔을 떄, 컨트롤러 보다 먼저 요청을 가로채서 
	//확인할 인터셉터로 사용한다.
	//그럼 우리가 꺼내야 할 메서드는 프리핸들이다. 뽑자
	
	
	//글쓰기 화면, 마이페이지 화면으로 들어가는 요청을 가로채서 검사하도록 하자.
	//그리고 권한이 없다면, 로그인 페이지로 보내버리겠다.
	
	
	//프리핸들 뽑았다.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//세션에서 로그인 데이터를 얻은 후 확인해 보겠습니다.
		HttpSession session = request.getSession();
		
		if(session.getAttribute("login") == null) {
			//로그인 안했네?
			response.sendRedirect(request.getContextPath() + "/user/userLogin");
			return false; //프리핸들 메서드는 리턴 타입이 boolean이다. false가 리턴되면, 컨트롤러로 요청이 전달되지 않습니다.
		}
		
		
		
		//return true는 로그인을 했다면 통과 라는 의미이다.
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
}
