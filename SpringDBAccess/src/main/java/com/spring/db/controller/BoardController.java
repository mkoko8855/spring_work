package com.spring.db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.db.model.BoardVO;
import com.spring.db.service.IBoardService;

//객체로활용하기위해 빈등록부터~(아래@~~)
@Controller
@RequestMapping("/board")
//@RequiredArgsConstructor //이거쓰고 바로아래 final로 먹이면 무조건 서비스가 주입 됨.
public class BoardController {

	//서비스계층이 필요하니
	@Autowired //야 스프링. IBoardService있으면 좀 해줘. 나이거 써야돼 라는 의미. (동시에 빈등록 필수다. 오토와이어드 붙여주고 IBaordDAO에도 @Respasitory 필수.
	//보드서비스에도 @Service 붙여줘야함
	private IBoardService service;
	
	
	
	
	//글 작성 화면을 열어주는 메서드
    @GetMapping("/write")
    public void write() { //보이드 니까 보드폴더의 write.jsp로가라 라는 의미다!
        System.out.println("/board/write: GET");
    }

    
    
    
    
    //작성된 글 등록 처리 요청
    //메서드 이름은 write() 입니다.
    //작성된 글을 DB에 등록 후 /board/list.jsp파일로 응답할 수 있도록
    //(글 목록 보여달라는 요청이 자동으로 들어올 수 있도록) 적절히 처리해 보세요.
    //아~ 즉, 리다이렉트를 쓰라는 말이구나~라고 생각함
    @PostMapping("/write")
    public String write(BoardVO vo) { //BoardVO vo로 받으면 편하겠지
    	System.out.println("/board/write: POST");
    	//이제데이터바로넣어주자 서비스계층이 아예 구현안됐으니 아래 리턴부터 써주고 서비스구현하러가자
    	service.insertArticle(vo);
    
    
    	return "redirect:/board/list"; //이제 서비스계층구현하러가자. IBoardDAO로 고고
    }
    
    
    
    
    
    
    

    //글 목록 화면 요청
    //메서드 이름 -> list()
    //DB 대용 리스트에서 가지고 온 글 목록을 list.jsp 파일로 전달해서
    //브라우저에 글 목록을 띄워 주시면 되겠습니다.
    //글 목록을 table을 사용해서 간단히 만들어 주세요. (글 번호는 인덱스 이용해서 달아주세요.)
    @GetMapping("/list")
    public void list(Model model) { //메서드부르면서 model도주세요~
    	System.out.println("/board/list: GET");
    	model.addAttribute("articles", service.getArticles()); //서비스로부터 전달받은 리스트를 articles라는 이름으로 model에 넣겠다.
    	//그럼이제 보드서비스로가자
    	//return dao.getArticles();이거써주자~ 
    	
    }

    
    
    
    
    
    
    
    //글 내용 상세보기 요청 처리 메서드
    //메서드 이름 -> content, 요청 url -> /content
    //DB 역할을 하는 리스트에서 글 번호에 해당하는 글 객체를 content.jsp로 보내주세요.
    //content.jsp에서 해당 글 정보를 모두 출력해 주세요. (글 번호도 같이)
    @GetMapping("/content") //a태그니 겟매핑. 포스트는 폼을 통해 들어옴.
    public void content(int boardNo, Model model) {  //글번호받자. 파라미터변수명과 변수명을 똑같이 맞추면 가능.
    	System.out.println("/board/content?boardNo=" + boardNo);
    	//모델에 담아서 보낼꺼지
    	model.addAttribute("article", service.getArticle(boardNo));
    }
    
    
    
    
    
    //글 수정하기 화면으로 이동 요청
    //메서드 이름은 modify(), url: /board/modify -> GET
    //수정하고자 하는 글 정보를 모두 받아와서 modify.jsp로 보내 주세요.(글 번호 같이)
    @GetMapping("/modify")
    public void modify(int boardNo, Model model) {
    	System.out.println("수정 페이지로 이동 요청! 번호: " + boardNo);
    	model.addAttribute("article", service.getArticle(boardNo));
    	//이 메서드 작성했으면 modify.jsp가자
    }

    
    
    
    
    
    
    
    //modify.jsp를 생성해서 form태그에 사용자가 처음에 작성했던 내용이 드러나도록
    //배치해 주시고 수정을 받아 주세요.
    //수정 처리하는 메서드: modify(), 요청 url: /modify -> POST
    //수정 처리 완료 이후 방금 수정한 글의 상세보기 요청이 다시 들어올 수 있도록 작성하세요.
    @PostMapping("/modify") 
    public String modify(BoardVO vo) { 	    	//리다이렉트니 리턴타입은 스트링. 매개값으로는 글번호.
    	System.out.println("글 수정 요청! 번호: " + vo.getBoardNo());
    	service.updateArticle(vo);
    	return "redirect:/board/content?boardNo=" + vo.getBoardNo(); //컨텐트라는 요청은 몇번글 상세보기인지 알아야함
    }

    
    
    
    
    
    
    
    
    //삭제는 알아서 작성해 주세요. (삭제 클릭하면 해당 글이 삭제될 수 있도록)
	@GetMapping("/delete")
	public String delete(int boardNo) { //매개값으로 글 번호가 날아옴
		service.deleteArticle(boardNo);
		return "redirect:/board/list";
	}
	
	
    
    
    
	
	
	
	
	
	
	
}
