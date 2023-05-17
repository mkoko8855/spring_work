package com.spring.myweb.freeboard.mapper;

import java.util.List;

import com.spring.myweb.command.FreeBoardVO;
import com.spring.myweb.util.PageVO;

//interface 2
public interface IFreeBoardMapper { //각 서비스가 어떤 과정을 거치는가.

	//글 등록
	void regist(FreeBoardVO vo);
	
	
	
	
	//글 목록
	List<FreeBoardVO> getList(PageVO vo);
	
	//총 게시물 수 구하기
	int getTotal(PageVO vo);
	
	
	
	
	//상세보기(리턴타입은 글 하나만 가져오니까 FreeBoardVO로)
	FreeBoardVO getContent(int bno);
	
	
	
	
	
	//수정
	void update(FreeBoardVO vo); //여러개는 프리보드VO로받자
	
	
	
	
	//삭제
	void delete(int bno);
	
	
	
	
	//그리고 메이븐 레파지토리에서 필요한 라이브러리를 다운 받을 것이다.
	//spring test검색 후, springTestContext Framework 클릭.
	//5.3.18로 가져오자. 메이븐 코드 복사 후, pom.xml로가자
	
	//즉,
	
	/*
	 * <!-- spring-test -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-test</artifactId>
			<version>${org.springframework-version}</version>
			<scope>test</scope>
		</dependency> 이렇게 붙여주자(이거 그대로 복사해도됨)
	 */
	
	
	
	//그리고 pom.xml에서 test라고 주석처리 된 부분 쪽은 주석처리하고
	//다시 메이븐 레파지토리가서 junit jupiter 쳐서 맨위에꺼받자. 5.8.2받자
	
	
	/*
	 		<!-- https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api -->
	<!-- junit5-jupiter-api(테스트환경을 구성해주고 여러 기능을 제공하는 라이브러리 -->
	<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.8.2</version>
    <scope>test</scope>
	</dependency>
	
	 이거 넣어주자
	 */
	

	
}
