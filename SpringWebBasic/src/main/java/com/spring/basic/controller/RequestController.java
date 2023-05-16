package com.spring.basic.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.basic.model.UserVO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


//자동으로 스프링 컨테이너에 해당 클래스의 빈을 등록하느 아노테이션.
//빈을 등록해 놔야 HandlerMapping이 이 클래스의 객체를 검색할 수 있다.
@Controller
@RequestMapping("/request") //이렇게 하면 컨트롤러 자체에 공통URL로 맵핑 때려버리면 메서드 작성할 때 마다 /request는 생략할 수 있다. 자동으로 적용 된다.
public class RequestController {

    public RequestController() {
        System.out.println("RequestCon 생성!");
    }

    
    
    @RequestMapping("/request/test")
    public String testCall() {
        System.out.println("/request/text 요청이 들어옴!");
        return "test";
    }
    
    
    
    
    
    
    
    /*
     만약 사용자가 /request/req 요청을 보내 왔을 때
     views 폴더 아래에 request 폴더 안에 존재하는 
     req-ex01.jsp 파일로 응답을 줄 수 있게 메서드를 작성해보기
     */
    
    
//    (표시)메서드 작성하자. basic01을 받기위한 메서드 준비해주자
//    @RequestMapping(value="/request/basic01", method=RequestMethod.GET)
//    public String req() {
//        System.out.println("/request/req 요청이 들어옴: 얘는 GET방식.");
//        return "request/req-ex01";
//    }
    
    
    
    
	  //그러나 겟방식쓸꺼면 이렇게 쉽게 쓰면 된다
    @GetMapping("/request/basic01")
    public String req() {
        System.out.println("/request/req 요청이 들어옴: 얘는 GET방식.");
        return "request/req-ex01";
    }
    
    
    
    
//    (표시)메서드 작성하자. basic01을 받기위한 메서드 준비해주자
//    @RequestMapping(value = "/request/basic01", method=RequestMethod.POST)
//    public String basic() {
//    	System.out.println("/request/basic01 요청이 들어옴: 얘는 POST방식");
//    	return "request/req-ex01";
//    }
    
    
    
    //그러나 포스트 방식도 쉽게 하자
    @PostMapping("/request/basic01")
    public String basic() {
    	System.out.println("/request/basic01 요청이 들어옴: 얘는 POST방식");
    	return "request/req-ex01";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // 여기서부터 /request안써도됨. 맨 위에 맵핑해놈
    
    
    
    //컨트롤러 내의 메서드 타입을 void로 선언하면 요청이 들어온 URL값을 뷰 리졸버에게 전달한다.
    @GetMapping("/join")
    public void register() { 
    	//지금까지는 스트링이었지만 void다. 리턴이 없다. 
    	//근데 리턴이 있어야 파일 지목해서 응답을 줄 수 있지만,
    	//타입이 보이드라면 urI가 파일 경로가 된다. 즉, /reqeust/join 얘가 파일 경로가 된다. 이 경로를 뷰 리졸버에게 전달한다.
    	System.out.println("/request/join: GET");
        //이제 join.jsp로가자. 근데 방식이 포스트다. 아래다가 다시 써주자
    }
    
    
    //요청 URI 주소가 같더라도, 전송 방식에 따라 맵핑을 다르게 하기 때문에
    //같은 주소를 사용하는 것이 가능하다. (주로 GET은 화면처리, POST는 입력값 처리를 할 때 사용한다)
    
    
    
    
    
    
    
    
    
    
    
    
    
    
     /*
       스프링에서 요청과 함께 전달된 데이터를 처리하는 방식
       1. 전통적인 jsp/servlet 방식의 파라미터 읽기 처리 방법.
       - HttpServletRequest 객체를 활용 (우리가 jsp에서 사용하던 방식)
     */
    
    
    //그러나 이 아래 방식은 1번 방식인데, 안쓴다. 주석처리 하자
    
//    @PostMapping("/join") //포스트방식으로 /join 요청이 들어오면 사용자가 뭔가를 입력하고 서브밋으로 요청을 보내면 요청과 함께 사용자의 입력값도 같이 전송이 된다. 라는 것이다.
//    public void register(HttpServletRequest request) { //조인 요청이들어오면 이 메서드를 불러주세요 라고 매핑했는데, httpservletRequest타입의 객체도 좀 같이 가져다 주세요 라는 말이다.
    	//그럼 핸들러맵핑이 HttpservletReqeust객체를 같이 가져다 준다.
    	//리퀘스트객체를 받으면 떄리면되지
//    	System.out.println("/request/join: POST");
//    	System.out.println("ID: " + request.getParameter("userId"));
//    	System.out.println("PW: " + request.getParameter("userPw"));
//    	System.out.println("HOBBY: " + Arrays.toString(request.getParameterValues("hobby")));
//    }
    
    
    //이제 스트링의 방식이다. jsp방식이 아닌, 이제 1번 방식이 아닌, 2번째 방식이다.
//    @PostMapping("/join")
//    public void register(@RequestParam("userId") String id, //괄호 안은 register를 부를 떄 전달하는 값이다. 스프링에게 요청하자. 유저id 라는 파라미터값을 String id에다가 주고, pw도 pw에다가 주고..이런식이다.
//    					 @RequestParam("userPw") String pw,
//    					 @RequestParam("hobby") List<String> hobbies){
    	//근데 파라미터값을 String id의 id라는 변수명을 같게하면 앞에 @Request안써도됨 꿀이다.
    	//즉, @RequestParam("userId") String id 이게 아니라 그냥 String userId만 써도 된다.
    	//대신에 List에서는 안먹는다.
    	//List는 @RequestParam(value="hobby", required = false, defaultValue = "no hobby person") List<String> hobby){
    	
//    	System.out.println("ID: " + id);
//    	System.out.println("PW: " + pw);
//    	System.out.println("HOBBY: " + hobbies); //리스트로했으니 arraystoString 안써도딤. 그냥찍으면돼
    		//콘솔창으로 확인해보면 다 잘 출력 된다.
//    }
    
    
      //편하게써보자
      //즉,
      //2번 째 방법. @RequestParam 아노테이션을 이용한 요청 파라미터 처리.
    
//    @PostMapping("/join")
//    public void register(String userId,
//                         String userPw,
//                            @RequestParam(value = "hobby", required = false, defaultValue = "no hobby person") List<String> hobby) {//안들어올수도있다.
//       System.out.println("/request/join: POST");
//        System.out.println("ID: " + userId);
//        System.out.println("Pw: " + userPw);
//        System.out.println("HOBBY: " + hobby);
//    }
    
    
    
    //3번 째 방법.
    //커맨드 객체를 활용한 파라미터 처리
    // - 파라미터 데이터와 연동이 되는 VO클래스가 필요하다.
    //메서드를 쓰기 전에 VO클래스 만들자  > UserVO 클래스 만들어줬다.
    //포스트방식으로넘어오니까
    
    @PostMapping("/join")
    public void register(UserVO vo) { //포스트방식의 /join이 호출되면 만든 UserVO 주세요.
    	//그럼 세터 메서드를 불러올 텐데, 파라미터 변수와 동일한 것들만 가져온다. 그래서 파라미터 변수명을 맞춰줘야 한다.
    	//그럼 출력 결과를 확인해보자~
    	System.out.println(vo); //근데 한글이 깨진다. web.xml로가서 추가해주자
    	
    	
    	// web.xml 가서 <!-- 한글 인코딩 필터 설정(톰캣 내부의 한글처리) --> 로 적었다. 확인해보자~
   
    
    	//근데 게터세터 만들기 귀찮아....
    	
    	//브라우저에 lombok이라고 검색하자.
    	//Project Lombok이 뜰 것이다. 누르자
    	//다운로드 가서 1.18.26 이 최신이다. 받자.
    	//바탕화면으로 놓고 복사하거나 자르고 work폴더에 STS.exe누르고 붙여넣기하자. 그리고 lombok을 더블클릭.
    	//그리고 스페시피 어쩌고에서 sts.exe를 추가해주자 그리고 끄자
    	
    	//그리고 구글에 메이븐 레파지토리로 쳐서 들어가서 lombok 검색하자
    	//1.18.26 찾고 가서 주소같은거 복사하고 pom.xml로 가서 붙여넣자.
    	//dependencies 안에 아무곳이나 넣자. 무조건 저 곳 안에 넣어야 한다.
    	//그리고 저장하고 메이븐 업데이트까지 해줘야한다. alt+f5를 눌러주자
    	
    	//완료하고 UserVO로 가자 게터세터랑 투스링 한거 다지우자
    	
    	/* 다 지우고 
    	@Getter 
		@Setter 
		@ToString 
		@NoArgsConstructor
		@AllArgsConstructor
		@EqualsAndHashCode 
    	추가 하면 끝. 
    	
    	대신에, 롬복은 모든 팀원들이 사용해야 한다.
    	*/
    	
    }
    
    
    
    
    
    

    
    
    
}