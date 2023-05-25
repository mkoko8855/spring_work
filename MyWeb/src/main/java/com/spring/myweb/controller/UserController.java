package com.spring.myweb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.myweb.command.UserVO;
import com.spring.myweb.freeboard.service.IFreeBoardService;
import com.spring.myweb.user.service.IUserService;
import com.spring.myweb.util.MailSenderService;
import com.spring.myweb.util.PageCreator;
import com.spring.myweb.util.PageVO;

import lombok.extern.slf4j.Slf4j;

@Controller //이렇게 등록 해놔야 핸들러맵핑이 이 클래스를 찾을 수 있음

//공통URL맵핑도해주자
@RequestMapping("/user")
@Slf4j
public class UserController {

	@Autowired
	private IUserService service; //service라는 변수에 자동으로 주입해줘~
	
	@Autowired
	private IFreeBoardService boardService; //게시글 총 개수를 갖다줄수있어야하기떄문에 이렇게선언해보자 
	
	@Autowired
	private MailSenderService mailService; //얘도 주입 받을꺼다.(이메일 전송을 담당하는 변수)
	
	
	
	
	//회원가입 페이지로 이동하는 메서드(join누르면감)
	@GetMapping("/userJoin")
	public void userJoin() {
		System.out.println("회원가입 페이지로 이동");
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
	@ResponseBody //이 메서드가 데이터를 직접 던지려는 메서드구나 를 알 수 있음.
	public String mailCheck(String email) { //파라미터 값으로 url에 email이 들어올 것임
		log.info("이메일 인증 요청 들어옴: " + email);
		
		//메일센더서비스 클래스에 @Component로 등록해놨으니 
		//mailService.joinEmail(email); //멜서비스야 이메일좀 보내줘.
		
		
		
		
		return mailService.joinEmail(email);
		
		//즉, 사용자가 이메일 인증번호 보내달라고 요청이 들어왔으니
		//서버는 이 요청을 받아 메일서비스야 이메일좀보내줘~그리고 인증번호를 위한 난수좀알려줘
		//서버는 그걸 리턴해서 클라이언트로 던져준다.
	}
	
	
	
	
	
	//회원 가입 처리 (레스폰스 바디가 아니라 그냥 포스트맵핑ㄱㄱ 왜냐면 폼태그에 포스트라고했으니까)
	@PostMapping("/join")
	public String join(UserVO vo, RedirectAttributes ra) { 		//리다이렉트해줄꺼니 리턴타입 스트링
		// log.info("param: " + vo); 말고 vo를 다른 방법으로 확인해보고 싶다면
		   log.info("param: {}", vo.toString());
		
		 service.join(vo);
		 ra.addFlashAttribute("msg", "joinSuccess"); //회원 가입이 성공하고 로그인 페이지로 가는 사람은 msg값이 있는거고 메뉴에 있는 로그인 눌러서 간 사람은 msg가 없겠지. 구분하기 위해 적어줬다.
		 
		 
		 return "redirect:/user/userLogin"; //회원가입 처리가 완료되면 로그인 페이지로 이동.
	}
	
	
	
	
	//로그인 페이지로 이동 요청
	@GetMapping("/userLogin")
	public void login() { //보이드는 jsp파일은 userlogin.jsp니까
	
		//데이터를 날라줄 필요가 없으니..적을건없다. userLogin.jsp로이동하겠지~
	}
	
	
	
	
	
	//로그인 요청이 들어왔다!
	@PostMapping("/userLogin")
	public void login(String userId, String userPw, Model model){ 		//로그인에 성공했는데 왜 다시돌아가려고 보이드를쓸까.
		log.info("나는 UserController의 login이다!"); //실행 순서 보려고..유저로그인석세드파일에도 적어놨음..
		
		model.addAttribute("user", service.login(userId, userPw)); //모델에 user라는 이름으로 서비스에게 id와 pw를 주면서 로그인 처리를 진행해라~
		//로그인에 성공했다면 로그인 성공한 사람의 userId와 userPw를 준다.	
		//아디나 비번이 틀려서 로그인에 실패했다면 null을 준다.
	}//UserLoginSuccessHandler클래스랑 연관되어있으니 확인하자
	
	
	
	
	
	
	//마이페이지 이동 요청. 
	@GetMapping("/userMypage")
	public void userMypage(HttpSession session, Model model, PageVO vo) {
		// 5/22 조인을 강제사용하겠다고했음(vo를 id로바꿔줌)
		
		//세션은 리퀘스트로뽑아야지. 핸들러어댑터한테 얘기하자. HttpSession session.    그리고 모델도줘  Model model
		
		// 세션 데이터에서 id를 뽑아야 sql을 돌릴 수 있다.
		String id = (String) session.getAttribute("login");
		vo.setLoginId(id);//현재 로그인 중인 id를 주자. 
		PageCreator pc = new PageCreator(vo, boardService.getTotal(vo));
		
		model.addAttribute("userInfo", service.getInfo(id, vo)); 		//서비스야 겟인포좀. id랑  page vo줄게. (getInfo메서드는 userVO를 준다)
		model.addAttribute("pc", pc);
		
		//그럼 UserSurvice클래스에서 getInfo가 불린다!
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
