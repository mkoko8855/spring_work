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

import com.spring.myweb.command.KakaoUserVO;
import com.spring.myweb.command.UserVO;
import com.spring.myweb.freeboard.service.IFreeBoardService;
import com.spring.myweb.user.service.IUserService;
import com.spring.myweb.util.KakaoService;
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
	
	@Autowired
	private KakaoService kakaoService;
	
	
	
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
	public void login(Model model, HttpSession session) { //보이드는 jsp파일은 userlogin.jsp니까
	
		//데이터를 날라줄 필요가 없으니..적을건없다. userLogin.jsp로이동하겠지~
		
		
		//카카오 URL을 만들어서 userLogin.jsp로 보내야 한다. 그리고 프로퍼티에 작성한 것을 클래스로 땡겨오자. 카카오로그인서비스를 하나 만들어주자.
		//그러나 카카오 로그인을 하는 사람이 있으니 카카오 로그민 url을 만들어서 전송해줘야 한다.
		//login() 에 model이랑 session추가해주자.
		
		//카카오서비스 작성해주고, 다시 와서 부르자, 부르려면 객체가 필요하다.
		String kakaoAuthUrl = kakaoService.getAuthorizationUrl(session);
		log.info("카카오 로그인 url은 이렇습니다. : {}" , kakaoAuthUrl);
		model.addAttribute("urlkakao", kakaoAuthUrl); //모델에 담아서 userLogin.jsp로 보낼예정. jsp가서 location.href=공간에 적어주자. ${}는 모델값을 빼낼 떄 사용했었다.
	}
	
	
	//카카오 로그인 성공 시 callback 되는 요청이다.
	@GetMapping("/kakao_callback")
	public void callbackKakao(String code, String state, HttpSession session, Model model) { //4개의 매개값을 준비.
		
		log.info("로그인 성공! callbackKakao를 호출했다!");
		log.info("인가 코드: {}", code);
		
		//이제 전송된 코드를 가지고 사용자의 정보를 받을 수 있는 토큰사용.
		String accessToken = kakaoService.getAccessToken(session, code, state);//서비스에서 작성한 토큰이 왔을것이다~~
		log.info("access 토큰값: {}", accessToken);
		
		//이제 토큰까지 받아냈다. accessToken으로 사용자의 정보를 가져올 수 있다!!
		//accessToken을 이용해서 로그인 사용자 정보를 읽어 오자.
		KakaoUserVO vo = kakaoService.getUserProfile(accessToken);
		
		//여기까지가 카카오 로그인 api가 제공하는 기능의 끝이다.
		
		//로그아웃 같은건 따로 해줘야한다. 로그인 이라는 이름의 세션데이터날리면 된다.
		//카카오 로그인 했던 사람은 카카오API에 요청을 해줘야 한다. 연결 끊어 달라고.
		//문서에 나와있다. 오른쪽 목차에 로그아웃을 보고 직접 해보던가 하자! 어드민키는 사용X
		
		//그냥 로그아웃이 있고 카카오 계정과 함께 로그아웃이 있다.
		//기본 로그아웃은 토큰을 만료시켜 해당 사용자 정보로 더 이상 카카오 API를 호출이 불가 한 거고
		//계정 로그아웃은 반드시 아이디와 비밀번호를 다시 입력해서 들어와야 한다는 것이다. 아예 만료시켜버리는 것이다.
		
		//추가 입력 정보가 필요하다면 추가 입력할 수 있는 페이지로 보내셔서 입력을 더 받아서
		//데이터베이스에 데이터를 집어넣던가 하자.
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
