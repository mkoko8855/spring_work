package com.spring.myweb.test;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
//@Controller 로 우리가 빈 등록을 했었는데, 일단 주석처리하고 빈등록을 @RestController로해보자
@RestController // 이러면 메서드마다 ResponseBody를 붙이지 않아도 된다. 동기와 비동기를 같이 사용하고 싶다면 레스트컨트롤러 안쓰고 레스폰스바디로
				// 비동기 처리를 할 메서드만 ResponseBody를 붙여주면 됨.
@RequestMapping("/rest")
@Slf4j
public class RestControllerTest {

	// pom.xml에서 <!-- jackson-databind: 데이터를 JSON 형태로 파싱해 주는 라이브러리 --> 추가 해주고 왔다~ >
	// JSON형태로 바꿔주는거임

	@GetMapping("/hello")
	// @ResponseBody
	// @ResponsBody는 메서드가 리턴하는 데이터를 뷰리졸버에게 전달하지 않고 클라이언트에게 해당 데이터를 바로 응답하게 한다.
	// 비동기 통신에서 주로 많이 사용한다. 메서드를 String , void만이 아니라 여러가지로 쓸 수 있다.
	// 그러나 맨 위에도 설명 했듯이, 컨트롤러 빈 등록 시 @RestController를 사용하면 모든 메서드에 @ResponseBody를 붙인
	// 것과 같다.
	// 그리고, @RestController 내에서 일반적인 jsp파일을 이용하여 응답하고 싶다면
	// return type을 ModelAndView 객체로 처리하면 된다.
	public String hello() {
		return "hello world!";
//      /rest에 /hello요청 보내면 hello world로 리턴이 되겠구나. 이것이 원래 스트링 방식이었다.
		// 그러나 페이지는 머물러있는상태에서(비동기상태) 리턴이 뷰페이지로 사용하는게 아니라 이제 hello world라는 문자열 자체를 화면쪽으로
		// 던져주고 싶다!
		// 이렇게 하려면 @ResponseBody가 필요하다
	}

	@GetMapping("/hobby")
	// @ResponseBody //이거 붙이면 List데이터를 화면쪽으로 직접 전달하겠다 라는 의미다.
	public List<String> hobby() {
		List<String> hobby = Arrays.asList("축구", "영화감상", "수영"); // asList라는 메서드로 바로 때려넣기
		return hobby;
		// JSON을 붙여줬으니 스크립트에서도 자바언어인 리스트값들이 잘 나온다.
	}

	// 리스트도 리턴 해봤으니 맵을 한번 리턴해보자
	@GetMapping("/study")
	// @ResponseBody
	public Map<String, Object> study() { // 오브젝트타입은 아무거나 다받음
		Map<String, Object> subject = new HashMap<>();
		subject.put("자바", "java");
		subject.put("jsp", "java server pages");
		subject.put("스프링", "spring framework");
		return subject;
	}
	

	// ModelAndView처리 해보자
	@GetMapping("/mv")
	public ModelAndView mv() {
		ModelAndView model = new ModelAndView();
		model.setViewName("temp/test"); // 뷰리졸버가 jsp한테 응답가도록 되는지 확인용. temp의 test.jsp로 응답이 가길 원한다. 뷰페이지로 응답이 가는지 확인하자.
		return model;
		// 404에러뜬다. 가는거 확인 완료!
	}

/////////////////////////////////////////////////////////////////////

	@GetMapping("/view")
	public ModelAndView viewPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("test/test1");
		return mv;
	}

	
	
	//@RequestBody는 클라이언트 쪽에서 전송하는 JSON데이터를 서버에서 사용하는 자바 언어에 맞게 변환하여 받을 수 있게 해 주는 아노테이션이다.
	// 이번엔 자바스크립트 이용해서 처리해보자. WEB-INF의 test폴더만들어서 test1.jsp를 만들어주자. 다 완성후, 다시 오자.
	@PostMapping("/getObject") 
	public Person getObject(@RequestBody Person p) {   //포스트방식으로 getObject 요청이 들어오면서 JSON객체가 넘어온다. @RequestBody를 쓰면 JSON 데이터를 자바 데이터로 바꾸어 준다. 그래서 얘가 읽을 수 있다.
		
		log.info(p.toString());	
		
		p.setName("변경이름");
		p.setAge(100);
		
		return p;
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
