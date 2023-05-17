package com.spring.myweb.freeboard.mapper;
//class 3

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spring.myweb.command.FreeBoardVO;
import com.spring.myweb.util.PageVO;

@ExtendWith(SpringExtension.class)   //테스트 환경을 만들어주는 junit5 객체 로딩
@ContextConfiguration(locations = {  
		"file:src/main/webapp/WEB-INF/config/db-config.xml" //여기에 DB연동을 위한 마이바티스에 대한 정보들이 있다. db-config.xml가면 <!--마이바티스 SQL 동작을 위한~~--> 내용 부분이다.
})
public class FreeBoardMapperTest {

	
	//mapper테스트를 하는 부분이다.
	//IFreeBoardMapper 인터페이스 클래스에서 만든 5개의 메서드들을
	//바로 DB에 연동 요청을 보내보자
	
	@Autowired
	private IFreeBoardMapper mapper; //이 mapper변수에 내가 작성한 xml파일을 클래스로 변환하겠다. 그 객체의 등록을 mapper변수로 주입하겠다. 위 주석단 마이바티스 부분을!
	
	
	
	//단위 테스트(가장 작은테스트. unit test).
	//테스트 시나리오를 할 것이다.
	//테스트 시나리오에서는 단언기법(Assertion)을 사용한다.
	//Insert 부터 테스트하자. 세팅은 끝났음.
	@Test
	@DisplayName("Mapper 계층의 regist를 호출하면서 FreeBoardVO를 전달하면 데이터가 INSERT 된다.")
	void registTest() {
		//test는 기본적으로 given - when - then 패턴으로 구성되어 있다. (생략도 가능)
		//기븐에쓸거없으면 생략하던가~......
		
		//given에는 줄 값이 있으면 준다. given: 테스트를 위해 주어질 데이터(ex: parameter)
		for(int i=1; i<=200; i++) {
			FreeBoardVO vo = new FreeBoardVO();
			vo.setTitle("테스트 제목 " + i); //i는 구분을 위해. 1, 2, 3, 4, ..
			vo.setWriter("abc1234");
			vo.setContent("테스트 내용 입니다. " + i);
			
			//when: 테스트 실제 상황
			mapper.regist(vo);
		}
		
		//then: 테스트 결과를 확인. (귀찮으니 워크벤치에서 확인하자..)
		
	}
		
		
		
		
		//테스트 메서드(조회)를 작성하자. 글 목록전체를 불러오자. List<프리보드VO vo>getList();
		@Test
		@DisplayName("사용자가 원하는 페이지 번호에 맞는 글 목록을 불러 올 것이고, 게시물의 개수는 사용자가 원하는 만큼의 개수를 가진다.") 
		void getListTest() {
			
			PageVO vo = new PageVO();
			vo.setPageNum(7); //7한번줘보자
			
			//given단을 보자.
			//줄게 없으니 given은 건너 뛰자
			//when단을 보자. 불러보자
			//매퍼야 전체 다가져와.
			List<FreeBoardVO> list = mapper.getList(vo); //그럼 겟리스트가 우리에게 List를 준다. 앞에 List<>써서 받아보자
			
			//then단을 보자.
			//일단 조회가 진행되는지 체크를 해보기 위해
			/*
			 for(FreeBoardVO vo : list){
			 Sysouttem.(vo);
			 } 이 문장을 이렇게 쓰자
			 */ 
			list.forEach(article -> System.out.println(article)); //람다식표현
			
			assertEquals(vo.getCpp(), list.size()); //나는 같을 것이라 단언한다. 즉, 리스트의 사이즈가 하나 일 것이라고. 레지스트 테스트가 하나란 것이라고 단언할 수 있다. 그러면 이 테스트를 돌렸을 때 리스트의 사이즈가 1이 아니면 터진다. 1이면 통과.
			//한 화면에 보여줄 게시글 만큼만이니 vo.getCpp() 겠지.
		}
		
		
		
		@Test
		@DisplayName("글 번호가 2번인 글을 조회하면 글쓴이는 'abc1234'일 것이고 글 내용은 '메롱메롱' 일 것이다.")
		void getContentTest() {
			//기븐 단
			//글번호 값을 주자
			int bno = 4;
			//웬 단
			//맵퍼야 겟컨텐트좀해줘 bno줄게
			FreeBoardVO vo = mapper.getContent(bno); //bno줫으면 겟컨텐트의 리턴타입은 freeboardVO겠지
			//겟컨텐트 완성하러가자. FreeBoardMapper.xml ㄱㄱ
			
			//덴 단
			//assertEquals("abc1234", vo.getWriter());
			//assertEquals("메롱메롱", vo.getContent()); //메롱메롱이 vo의 getContent와 같다고 나는 단언한다.
			
			assertNull(vo); //위에 int bno=4;라고적었는데, 4번글이없으니 null이 온다는걸 단언한다. 라는 의미이다. 초록불 들어온다. 왜? 나는 3번글까지밖에 안적었으니까
		}
		
		
		@Test
		@DisplayName("글 번호가 1번인 글의 제목과 내용을 수정 후 다시 조회했을 때 제목이 수정한 제목으로 바뀌었음을 단언한다.")
		void updateTest() {
			//기븐단에는
			FreeBoardVO vo = new FreeBoardVO();
			vo.setBno(1);
			vo.setTitle("수정된 글 제목");
			vo.setContent("수정된 글 내용");
			
			//웬단에는
			mapper.update(vo);
			
			//검증단계는 덴 단에는
			//제목이 수정한 제목으로 바뀌었음을 단언한다 라고했지
			assertEquals(vo.getTitle(), mapper.getContent(1).getTitle()); //내가 vo에 세팅한 수정된 글 제목 이라는 것이 수정이 진행되고 나서의 타이틀 값과 같을 것이라 단언한다. getContent(1)은 1이 아니라 vo.getBno()로 해도 된다.
		}
	
		@Test
		@DisplayName("글 번호가 2번인 글을 삭제한 후에 리스트를 전체 조회했을 때 글의 개수는 1개 일 것이고, 2번 글을 조회 했을 때 null이 반환되어야 한다.")
		void deleteTest() {
			//기븐단에는
			int bno = 2;
			//웬단에는
			mapper.delete(bno);
			
			//검증단계인 덴 단에는
			assertNull(mapper.getContent(bno));
		}
		
		
		
		
		
		
		
		
		
		
		
}
