package com.spring.myweb.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.myweb.command.ReplyVO;
import com.spring.myweb.reply.service.IReplyService;

@RestController //알아서 비동기로 됨
@RequestMapping("/reply")
public class ReplyController {

	
	@Autowired
	private IReplyService service;
	
	//페이지는 열어줄 필요가 없는 컨트롤러다. 댓글은 글 상세보기를 해야 들어가니까.
	
	
	
	//댓글 등록
	@PostMapping("/regist")
	public String replyRegist(@RequestBody ReplyVO vo){ //화면단에서 비동기 방식으로 JSON데이터가 날라오니까 해석하기위해 @requstBodyㄱㄱ
		service.replyRegist(vo); 
		
		return "regSuccess"; //댓글 등록 후 regSuccess라는 문자를 간단하게 전달해주는 용.
	}
	
	
	
	//목록 요청(페이징 포함)
	@GetMapping("/getList/{bno}/{pageNum}") 
	public Map<String, Object> getList(@PathVariable int bno, @PathVariable int pageNum){ //위 경로상의 값을 뗴오기 위해 @PathVariable
		
		// 1. getList 메서드가 글 번호, 페이지 번호를 경로에서 떼온다. (이건완료)
		// 2. Mapper 인터페이스에 복수의 값을 전달하기 위해 Map을 쓸지 Param을 쓸지 선택을 하는데,
		//    우리는 Map으로 쓰기로 했다.
		// 3. ReplyMapper.xml에 sql문을 페이징 쿼리로 작성.
		// 4. 클라이언트 측으로 DB에서 조회한 댓글 목록을 보낼 때, 왜 리턴타입이 Map인가?
		//    페이징을 위한 댓글의 총 개수도 함께 보내줘야 한다. 복수개의 값을 리턴하기 위해서
		//    선택을 해야 하는데, 리턴 타입을 Map으로 줄지 VO(객체)형식으로 줄 지를 결정해야 한다.
		//    즉, 댓글 목록 리스트와 전체 댓글 개수를 함께 전달할 예정이다.
		
		List<ReplyVO> list = service.getList(bno, pageNum); //이걸 넘겨주자
		int total = service.getTotal(bno); 
		//위 두개를 MAP으로 감싸서 클라이언트한테 던져주자!
		
		return null;
	}
		
	
	
	
	
	
	
	
	
	
	
}
