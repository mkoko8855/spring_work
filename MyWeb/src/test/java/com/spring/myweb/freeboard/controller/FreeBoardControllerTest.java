package com.spring.myweb.freeboard.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/config/servlet-config.xml", "file:src/main/webapp/WEB-INF/config/db-config.xml"})
@WebAppConfiguration 															//이거붙여놓으면 자동으로 ctx를 주입 할 수 있다.
public class FreeBoardControllerTest {
	
	 /*
	  	테스트 환경에서 가상의 DispatcherServlet을 사용하기 위한 객체를 자동으로 주입한다.
	  	WebApplicationContext객체는 Spring에서 제공하는 servlet들을 사용할 수 있도록
	  	정보를 저장하는 객체이다.
	 */
	@Autowired
	private WebApplicationContext ctx;
	
	
	
	//웹 어플리케이션을 서버에 배포하지 않아도 스프링 MVC 동작을 구현할 수 있는 가상의 구조를 만들어 준다.
	private MockMvc mockMvc; //가상의, 모조의 MVC다. 화면같은것도 제대로 구축해놓은게 없으니 가상의 mvc구조를 빌려 온 것이다. 
	
	
	
	
	@BeforeEach //테스트 시작 시 항상 먼저 구동되는 기능들을 선언. 얘부터먼저돌아라~
	public void setup() { //셋업이라는 메서드를 다른 메서드 실행 전에 항상 먼저 이 메서드가 실행 될 것임.
		//가상 구조를 먼저 세팅하고 나서 테스트를 진행 하겠다.
		//위로가서 movkMvc라는 변수를 하나 선언부터 하자.
		//선언하고와서.
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build(); //스프링 컨테이너에 등록된 모든 빈과 의존성 주입까지 로드해서 사용이 가능한 코드이다.
		
		
		//전부 로딩안하고 컨테이너 하나만 쓰고 싶고 수동으로 주입하고 싶다하면,
		// 	    @Autowired
		//위에다가 private FreeBaordController controller; 하고
		//여기로와서 this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build(); 적으면됨
		//이건 테스트할 컨트롤러를 수동으로 주입. 하나의 컨트롤러 자체만 테스트를 진행 할 때 사용.
	}
	
	
		
		//이제 테스트 돌려보자
		@Test
		@DisplayName("/freeboard/freeList 요청 처리 과정 테스트")
		void testList() throws Exception {
			//컨트롤러에 요청을 넣어 볼 거다.
			//그 요청에 따른 값이 무엇이 오는지 체크해보자
			//FreeBoardController로가자
			
			ModelAndView mv = 
			mockMvc.perform(MockMvcRequestBuilders.get("/freeboard/freeList"))
			.andReturn() //위 경로를 통해 결과를 돌려받고 getModelandview메서드를 통해 결과를 받은 것 중에서 모델계층을 전달 받을 수 있다.
			.getModelAndView();
			System.out.println("Model 내에 저장한 데이터: " + mv.getModelMap());
			System.out.println("응답 처리를 위해 사용할 페이지: " + mv.getViewName());
			
			//즉, 테스트리스트 메서드를 실행하면 목mvc가 동작을하는데 리퀘스트빌드의 객체의 메서드인 get을 이용하면 서브를 안타도 겟 요청이 온 것처럼 진행할 수 있다. freeboard/freelist 요청을 넣어봄.
			//모델에 담아서 체크를 해보겠다는 것이다
		}
		
		
		
		@Test
		@DisplayName("게시글 등록 요청 처리 과정을 테스트")
		void testInsert() throws Exception {
			//등록은 모델에 담아서 뭘 주진 않지만 응답페이지에서 확인 한번 해봐야 겠지.
			//글 등록이 된다음에 대부분 목록요청이 다시 오기를 바라겠지. 테스트해보자
			String viewPage = mockMvc.perform(MockMvcRequestBuilders.post("/freeboard/freeRegist").param("title", "테스트 새 글 제목").param("content", "테스트 새 글 내용").param("writer", "user01")).andReturn().getModelAndView().getViewName(); //폼태그로올꺼니 post
			//즉, 목mvc에게 부탁한다. 리퀘스트빌더를 이용해서 post요청을 넣었다. 이 요청과함께 같이 전달되는 파라미터값은 3가지다. 이 대로 퍼폼을 할 것이다. 퍼폼한 결과값을 받을거고 그 결과값에서 모델앤뷰를 꺼내고 그 안에 있는 뷰네임을 어떻게 세팅이 되어있는지 확인할 것이다. 모델에 담아서 전달하는 것이 아니라 모델맵을 쓰지 않았다.
			
			System.out.println("viewName: " + viewPage);
			//이제 프리보드컨트롤러 가서 만들어줘야지
		}
	
		
		
		@Test
		@DisplayName("3번 글 상세 보기 요청을 넣으면 컨트롤러는 DB에서 가지고 온 글 객체를 model에 담아서 JSP로 이동시킬 것이다.")
		//    /freeboard/content -> get
		void testContent() throws Exception {
			ModelAndView mv = mockMvc.perform(MockMvcRequestBuilders.get(".freeboard/content").param("bno", "3")).andReturn().getModelAndView();
			System.out.println("Model: " + mv.getModelMap());
			assertEquals("freeboard/freeDetail", mv.getViewName());
		}
		
		
		
		
		@Test
		@DisplayName("3번 글의 제목과 내용을 수정하는 요청을 post방식으로 전송하면 수정이 진행되고, 수정된 글의 상세보기 페이지로 이동할 것이다.") 
		//    /freeboard/modify -> post
		public void testModify() throws Exception {
			String bno = "3";
			String viewPage = mockMvc.perform(MockMvcRequestBuilders.post("/freeboard/modify").param("title", "수정된 테스트 글 제목").param("content", "수정된 테스트 글 내용").param("bno", "3")).andReturn().getModelAndView().getViewName();
		//시스아웃대신에 어썰트로확인해도됨
		assertEquals("redirect:/freeboard/content?bno=" + bno, viewPage);
		}
		
		
		
		
		
		@Test
		@DisplayName("3번 글을 삭제하면 목록 재요청이 발생할 것이다.")
		//    /freeboard/delete -> post       //변수선언없이 바로 때려보자. 이미 값이 삭제되는건 맵퍼계층에서 확인했다
		void testDelete() throws Exception { 
			assertEquals("redirect:/freeboard/freeList", mockMvc.perform(MockMvcRequestBuilders.post("/freeboard/delete").param("bno", "3")).andReturn().getModelAndView().getViewName());
			
		}
		
		
		
	
	
}
