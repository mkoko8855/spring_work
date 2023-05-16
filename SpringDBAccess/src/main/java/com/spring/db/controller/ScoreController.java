package com.spring.db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.db.model.ScoreVO;
import com.spring.db.service.IScoreService;

//동작을 하기 위해 객체를 생성하고, 객체를 사용하기 위해선 준비~
@Controller
@RequestMapping("/score")
public class ScoreController {
	
	//할일이 있다. 서비스패키지의 SocreService에 @Service를 선언하고 다시 와서.
	//private IScoreService service = new ScoreService(); 라고 안하고
	@Autowired  //자동으로 연결하는 어노테이션도 추가하자
	private IScoreService service; //즉, 위 어노테이션은 너는 서비스 계층이라고 등록을 해주자. 컨트롤러는 서비스 클래스가 필요하기 때문에. 없으면 디비에 값을 넣지 못한다. 서비스객체가 컨트롤러객체에 주입이 되어야 한다고 객체 만들어주지말고 컨테이너에 등록한 것을 자동으로 주입시켜주자.
	
	//즉, 이 오토와이어는 컨트롤러와 서비스 계층 사이의 의존성 자동 주입을 위해 변수를 선언 해 준 것이다.
	//@Autowired는 스프링 컨테이너에 등록되어 있는 빈들 중, 해당 변수 타입에 맞는 객체를
	//자동으로 변수에 주입해 주는 스프링이 제공하는 아노테이션 이다.
	//얘는 위처럼 필드에 선언하던가, 생성자에 선언하던가 세터 메서드에 붙일 수 있다. 그러나 위처럼 필드로 선언하는게 쉽다.
	
	
	
	
	
	
	//폼열어주는
	//점수 등록 화면을 열어주는 처리를 하는 메서드
	@GetMapping("/register") //이름은뭐맘대로~
	public String register() {
		System.out.println("/score/register: GET");
		return "score/write-form"; //리턴타입이 스트링이니, 이 문자열은 뷰리졸버한테 간다. 그럼 어디열어줘? 뷰즈 안에있는 스코어 폴더의 write-form.jsp열어달라고해야지. 리다이렉트는 url적어야한다!
		// http://localhost:8181/db/score/register 하면 브라우저 열림~
	}
	
	
	
	//점수 등록 요청을 처리할 메서드
	@PostMapping("/register") 
	public String register(ScoreVO vo) { //매개변수 다르게하면 오버로딩 적용하면서 이름을 같게 할 수 있음 > 겟파라미터 4번할 필요도없고 생성자 선언도 필요없다..객체로 포장하자
		System.out.println("/score/register: POST"); //확인용
		System.out.println("vo: " + vo); //파라미터값 잘 들어왔는지 확인용
		//이제 DB에 때려넣어야지
		//컨트롤러가 할 일은 아니니 서비스계층과 레파지토리 계층이 필요하다.
		
		
		service.insertScore(vo); //이제 넘겼으니 그럼 스코어서비스에 있는 public void insertScore(ScoreVO vo){메서드가 실행된다.
		
		return "score/write-result"; //이 주소로 리턴할게요~
		
		//vo를 서비스와 레파지토리 계층으로 넘겨주자
		//서비스 인터페이스를 만들자. 즉, com.spring.db패키지에 IScoreService 인터페이스만들자~
	}
	
	
	
	
	//점수 전체 조회를 처리하는 요청 메서드
	//a태그로 요청 보냈으니
	@GetMapping("/list")
	public void list(Model model) {  //void는 url을 파일명으로 사용하겠다 는 것이다. 스코어폴더의 list.jsp로 응답 보낸다는 의도이다.
		System.out.println("/score/list: GET");
		List<ScoreVO> list = service.selectAllScores(); //서비스야 DAO한테 가서 점수 다 가져와
		//스코어서비스로가자~ 작성한 후 다시와서
		
		//담아서보내야지 list.jsp로
		//그래서 매개값에 Model model을 쓰자. 이건 모델좀줘~ DAO가 갖다준 LIST를 화면에 뿌릴꺼니까요!
		model.addAttribute("sList", list); //sList란 이름으로 list를 넣겠다.
		//list.jsp를 스코어폴더에 만들어주자~ 스코어폴더는 views안에있다.
	}
	
	
	
	
	
	
	//점수 개별 조회 페이지 요청 메서드
	//a태그로 보내지 겟맵핑
	@GetMapping("/search")
	public void search() { //보이드는 url을 파일 경로로 만들겠다는 거니 score안에 search.jsp를만들자
		System.out.println("/score/search: GET");
	}
	
	
	
	
	//점수 개별 조회를 처리할 메서드
	@GetMapping("/selectOne")
	public String selectOne(int stuId, Model model, RedirectAttributes ra) { //매개값으로는 int stuId가 온다. 리퀘스트파람은 생략해도된다했다. 대신 변수 이름을 파라미터 변수명(search.jsp에 name값)과 똑같이해야한다. 그리고 모델에 담아서 보낸다.
		//컨트롤러는 stuId를 받았다 그럼이제뭐해?
		//스코어서비스의 selectOne으로가자 > 작성 후 와서 
		//model.addAttribute("stu", service.selectOne(stuId)); 
		
		//그러나 ScoreDAO에서 예외처리 해주고 왔기 떄문에 null이 올 수도 있다. 바로 위꺼 주석처리하고 다시써주자
		ScoreVO vo = service.selectOne(stuId);
		if(vo == null) {
			ra.addFlashAttribute("msg", "학번 정보가 없습니다.");
			return "redirect:/score/search"; //score에 search라는 요청이 다시 들어오길 원해요~ . 즉, 여기 바로 위 메서드를 뜻함
		} else {
			model.addAttribute("stu", vo);
			return "score/search-result"; 
			
			//즉, ScoreDAO의 selectOne메서드가 예외가발생한다면 조회결과가없다는 것으로 인지하고 null을 넣게됨. 그럼 다시 학번을 입력할 수 있는 폼 창으로 돌려보낼 것이다.
		}
	}
		
		
		
		

		//삭제 처리를 완료하신 후, list 요청이 다시 컨트롤러로 들어갈 수 있도록 처리해보세요.(리다이렉트사용)
		//list요쳥이 다시 들어가서 list.jsp로 갔을 때, 삭제 이후에 간 것이 판단된다면
		//브라우저에 '삭제가 완료되었습니다' 문구를 빨간색으로 띄워보세요.
		//(RedirectAttributes 경고창으로 띄워도 좋아요.)
		@GetMapping("/delete")
		public String delete(int stuId, RedirectAttributes ra) {
			System.out.println("삭제할 학번: " + stuId);
			service.deleteScore(stuId);
			ra.addFlashAttribute("msg", "delSuccess");
			
			return "redirect:/score/list";
		}
	
}
