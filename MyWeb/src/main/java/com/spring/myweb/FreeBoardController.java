package com.spring.myweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.myweb.command.FreeBoardVO;
import com.spring.myweb.freeboard.service.IFreeBoardService;

@Controller
@RequestMapping("/freeboard") // 얘는freeboard로시작해야해~
public class FreeBoardController {

	// 컨트롤러는 서비스와 의존관계가있으니
	@Autowired
	private IFreeBoardService service; // 잊지말고 freeboardservice로 @Service써주자. 주입ㄱㄱ

	// 메서드 하나씩 작성할껀데. 화면은 아무것도없고. 그러면 테스트와 함께 요청을 보낼거고
	// 그 요청을 받아내는지 테스트를 진행해보자. src/test/java에서 클래스를 추가하자. 아까는 mapper를 테스트했고
	// 이번엔 controller를 테스트하자. FreeBoardControllerTest를 만들었다~

	// 목록 화면 짜보자
	@GetMapping("/freeList")
	public void freeList(Model model) {
		model.addAttribute("boardList", service.getList());
		// 서비스는 컨트롤러에게 요청을 할 것이고 프리보드서비스로가자
		/*
		 * 가서 최상단에 이거 써주자
		 * 
		 * @Autowired private IFreeBoardMapper mapper;
		 */

		/*
		 * @Override public List<FreeBoardVO> getList() { return mapper.getList();} 이것도
		 * 써주자
		 */

		// 그리고 프리보드테스트컨테이너가서 testList메서드를 써주자

	}

		
	
	//글쓰기 페이지 열어주는 메서드
	@GetMapping("/regist")
	public String regist() { 		//void니까 jsp파일이 regist.jsp여야만함 그래서 귀찮으니 스트링으로
		return "freeboard/freeRegist";
	}
	
	
	
	
	// 글 등록 처리(post엿으니)
	@PostMapping("/regist")
	public String regist(FreeBoardVO vo) {
		//서비스야 레지스트좀해라 vo줄게
		service.regist(vo); //vo가 없으니 위에 FreeBoardVo vo를 적어주면됨
		//이제 프리보드서비스로가자 > 등록완료했어
		
		//목록 요청이 다시들어와야해
		return "redirect:/freeboard/freeList";
		
		//메서드완성 끝
	
	}

	
	
	//글 상세보기 처리
	@GetMapping("/content/{bno}")
	public String getContent(@PathVariable int bno, Model model) {
		model.addAttribute("article", service.getContent(bno));
		//작성했으니 프리보드서비스 ㄱㄱ
		
		//다시와서
		return "freeboard/freeDetail";
	}
	
	
	
	//글 수정 페이지 이동 처리
	@PostMapping("/modify")
	public String modify(@ModelAttribute("article") FreeBoardVO vo) { //폼태그를이용
		//모델에 담아서보내줄꺼니 return "freeboard/freeModify"; 써주고,
		
		//아노테이션은 아티클이란 이름으로 FreeBoardVO vo를 받겠다 라는 것이다.
		
		
		return "freeboard/freeModify";
	}
	
	
	
	
	
	//글 수정 처리
	@PostMapping("/update")
	public String update(FreeBoardVO vo){			//리다이렉트처리해야하니 String
		service.update(vo);
		return "redirect:/freeboard/content/" + vo.getBno();
	}
	
	
	
	//글 삭제 처리
	@PostMapping("/delete")
	public String delete(int bno) {
		service.delete(bno);
		return "redirect:/freeboard/freeList";
	}
	
	
}

