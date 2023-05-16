package com.spring.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.basic.model.UserVO;

@Controller
@RequestMapping("/response")
public class ResponseController {

	
	//얘도 메서드선언할꺼고 요청 들어왔을 떄 실행 할 메서드 선언해서 전담할 것이다.
	//위에 컨트롤러로 지정하자 ~ 리퀘스트도 하자
	
	
	@GetMapping("/res-ex01") //리스폰스의 res-ex01이라는 요청이 들어왔다. 즉, 보이드 처리니 레스폰스 폴더의 res-ex01폴더의 응답이 나가겠구나~
	public void resEx01() {  //views폴더의 response폴더를 만들고 res-ex01 jsp파일을 만들어주자
	}
	
	
	
//	@GetMapping("/test")
//	public void test(@RequestParam("age") int age) {
//		
//	}
	
	
	//1. Model 객체를 사용하여 응답할 화면에 데이터 전송하기
	//근데 생략 해서 써보자
//	@GetMapping("/test")
//	public void test(int age, Model model) { //매개값으로 날라온 age를 이 /test 경로에 보내고싶다면? 모델 객체를 쓰자.
		//Model객체 의 model을 주세요~ 매개값으로 받은 파라미터매개값을 model에 담을 것이다
//		model.addAttribute("age", age); //model에 "age"를 담았다.
//		model.addAttribute("nick", "멍멍이"); //model에 "nick"을 담았다.
		
		//담아 놓기만 하면 /test.jsp로 자동으로 간다.
		//response파일에 test.jsp를 만들고 확인하자
//	}
	
	
	
	
	//일단 위에꺼 주석처리하고
	//2. @ModelAttribute를 사용한 화면에 데이터 전송 처리 (훨씬 간편함)
	//@RequestParam + model.addAttribute처럼 동작.
	@GetMapping("/test")
	public void test(@ModelAttribute("age") int age, Model model) { //괄호안에는 model에 담을 때 짓고 싶은 이름. 파라미터로 담은 age는 담을 필요 없다. 받은거 바로 꽂자. 즉, 
		//model.addAttribute("age", age); 할필요 없다.
		model.addAttribute("nick", "멍멍이");
		//모델어트리뷰트는 age란 이름으로 파라미터를 직접 받아서 떄려 넣었다. add할 필요가 없다.
	}
	
	
	
	
	
	
	
	@GetMapping("/test2")  //text2.jsp파일로~
	public void test2(@ModelAttribute("info") UserVO vo) { 
		System.out.println("메서드 내의 콘솔 출력 : " + vo); //파라미터값 받아서 info라는 이름으로 모델에 담아 vo를 꺼내보자. 즉, 모델.에드어트리뷰트 생략!
		//test2.jsp로가서 작성해주자
	}
	
	
	
	
	
	
	
	
	
	//3. ModelAndView 객체를 활용한 처리 ( 뷰 화면도 직접 지정할 수 있다.)   리턴도 보이드도 아니다.
	
	@GetMapping("/test3") //test3요청이 들어오면 이 메서드 실행할 것임. res-ex01.jsp에서 a태그로 달아줬다.
	public ModelAndView test3() { //리턴타입을 modelandview로잡음
		ModelAndView mv = new ModelAndView(); //모델앤드뷰 객체 생성
		mv.addObject("userName", "김철수"); //김철수를 담자
		mv.addObject("userAge", 30); //30을 담자
		mv.setViewName("/response/test3"); //이 경로로 응답 할꺼야~ 즉, 뷰네임직접지정가능
		return mv;
	}
	
	
	
	
	
	
	
	//////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	//이번엔 리다이렉트 처리를 해보자 
	//폼 화면을 열어주는 메서드 만들어보자~
	@GetMapping("/login")
	public String login() {
		System.out.println("/login: GET 요청 발생!");
		return "response/res-redirect-form"; //사용자에게 응답하고싶은 경로가 url이랑 다르지. void였으면 /login.jsp라고 해야했지만 파일명을 다르게 했다. String으로 해서 return값을 적어줬다.
		//jsp파일 만들러가자. response폴더에 만들어주자
	}
	
	
	//res-redirect-form.jsp파일은 포스트방식. 겟 썼으니 포스트도써줘야함
	@PostMapping("/login")
	public String login(@RequestParam("userId") String id, //그냥 리퀘스트파람 사용해보자~
					    @RequestParam("userPw") String pw,
			@RequestParam("userPwChk") String pwChk, /* Model model */ //(리다이렉트가 모델이 먹는지 안먹는지~)
					    RedirectAttributes ra) {  
		
		System.out.println("/login: POST 요청 발생!");
		System.out.println("ID: " + id);
		System.out.println("PW: " + pw);
		System.out.println("CHK: " + pwChk);
		
		
		
		
		
		if(id.equals("")) { //모델안에 간단한 메시지 띄워서 전달해주려한다. (리다이렉트가 모델이 먹는지 안먹는지~)
			//리다이렉트 상황에서, model 객체를 사용하게 되면
			//model 내부의 데이터가 재 요청이 들어올 때 파라미터 값으로 붙어서 들어온다.
			//데이터가 url 주소 뒤에 ?와 함께 노출되어 전달된다.
			
			//노출이 되면 안되니까 그럴때는 모델을 지우고 RedirectAttributes ra를 추가해주고 아래를 써주자
			//즉, redirect 사오항에서 일회성으로 데이터를 전송할 떄 사용하는 메서드는 Flash이다.
			//url 뒤에 데이터가 붙지 않는다. 한 번 이용한 후에는 알아서 소멸한다.
			ra.addFlashAttribute("msg", "아이디는 필수값이에요!");
			
			
			
			//model.addAttribute("msg", "아이디는 필수값입니다!"); //msg를 jsp에서꺼내보자
		}
		
		
		
		
		
		return "redirect:/response/login"; //위는 전부 포워드와 비슷하지만, 리다이렉트 사용하고 싶으면 매개값으로 레스폰스 객체를 받을 필요가 없다. 뒤에는 다시 요청 들어올 경로를 적으면 된다.
		//리다이렉트는 뒤에있는 response/login은 뷰리졸버에게 전달되는 경로가 아니다. 리다이렉트가 아니라면 뷰 리졸버에게 전달될 파일 경로로 사용이 되겠지.
	
		//response/login으로 직접 브라우저에서 들어가보면
		//GET요청발생하면서 폼이 나오고 폼에다가 다 적고 엔터치면
		//POST요청발생했다가 다시 return으로 리다이렉트 하는 경로가 호출이되니 화면상으로는 변화가 안되지만
		//응답나갔다가 재요청이 발생하는, GET 요청이 다시 발생한다!
		
		 /*
		    /login: GET 요청 발생!
			/login: POST 요청 발생!
			ID: abc1234
			PW: aaa1111
			CHK: aaa1111
			/login: GET 요청 발생!
		 */
		
		
		
		//그러나 리다이렉트는 모델을 사용할 수 없다. 위에서 추가로 작성해서 확인해보자. 플래쉬 메서드를 사용해서 먹게해보자
	}
	
	
	
	
	
}
