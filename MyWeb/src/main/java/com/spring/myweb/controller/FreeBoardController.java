package com.spring.myweb.controller;

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
import com.spring.myweb.util.PageCreator;
import com.spring.myweb.util.PageVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/freeboard") // 얘는freeboard로시작해야해~
@Slf4j //log4j의 부모를 불러주는 것을 권장한다. (롬복에서 준다). 이걸 쓰면 로그를 확인할 수 있다.
public class FreeBoardController {

	// 컨트롤러는 서비스와 의존관계가있으니
	@Autowired
	private IFreeBoardService service; // 잊지말고 freeboardservice로 @Service써주자. 주입ㄱㄱ

	// 메서드 하나씩 작성할껀데. 화면은 아무것도없고. 그러면 테스트와 함께 요청을 보낼거고
	// 그 요청을 받아내는지 테스트를 진행해보자. src/test/java에서 클래스를 추가하자. 아까는 mapper를 테스트했고
	// 이번엔 controller를 테스트하자. FreeBoardControllerTest를 만들었다~

	// 목록 화면 짜보자
	@GetMapping("/freeList")
	public void freeList(PageVO vo, Model model) {
		PageCreator pc = new PageCreator(vo, service.getTotal(vo)); //vo와 인트값을 받는 getTotal만 선언했었다.
		//위 마지막에 vo를 써줬다.  키워드와 컨디션에 맞게 값을 불러오기위해..그리고 아이프리보드매퍼와 아이프리보드서비스쪽도 변경하자.
		//이제 PageCreator의 calcDataOf뭐시기가 자동으로 돌겠다
		
		
		log.info(pc.toString()); //위에 아노테이션으로 @Slf4j를 부른 뒤 사용가능.
		
		
		model.addAttribute("boardList", service.getList(vo)); //getList()였는데 PageVO도 받을 수 있으니 vo 적어주자. 서비스쪽도 아이프리보드서비스 인터페이스 건들여주자.
		model.addAttribute("pc", pc);
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
	
	//@PathVariable은 URL 경로에 변수를 포함시켜 주는 방식이다.
	//그리고, NULL이나 공백이 들어갈 수 있는 파라미터라면 적용하지 않는 것을 추천한다. 
	//그리고, 파라미터 값에 .이 포함되어 있다면 . 뒤의 값은 잘린다는 것을 알아두세요!
	//{} 안에 변수명을 지어주시고, @PathVariable 괄호 안에 영역을 지목해서 값을 받아온다.
	@GetMapping("/content/{bno}") //즉, 이 {bno}부분에 NULL이나 공백이 들어올 수 있다면 추천하지 않는다는 것이다.
	
	public String getContent(@PathVariable int bno, @ModelAttribute("p") PageVO vo, Model model) {
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
		return "redirect:/freeboard/content/" + vo.getBno(); //원래는 content/뒤에 ?bno였는데 vo.getBno로바꿈
	}
	
	
	
	//글 삭제 처리
	@PostMapping("/delete")
	public String delete(int bno) {
		service.delete(bno);
		return "redirect:/freeboard/freeList";
	}
	
	
}

