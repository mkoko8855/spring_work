package com.spring.myweb.command;

import java.time.LocalDateTime;
import java.util.List;

//CREATE TABLE users(
//		user_id VARCHAR(50) PRIMARY KEY,
//	    user_pw VARCHAR(50) NOT NULL,
//	    user_name VARCHAR(50) NOT NULL,
//	    user_phone1 VARCHAR(50),
//	    user_phone2 VARCHAR(50),
//	    user_email1 VARCHAR(50),
//	    user_email2 VARCHAR(50),
//	    addr_basic VARCHAR(300),
//	    addr_detail VARCHAR(300),
//	    addr_zip_num VARCHAR(50), #우편번호
//	    reg_date DATETIME DEFAULT CURRENT_TIMESTAMP #가입날짜
//	);

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserVO {

	private String userId; //마이바티스에 카멜케이스를 변환해주는 설정넣었으니 그냥써도됨
	private String userPw;
	private String userName;
	private String userPhone1;
	private String userPhone2;
	private String userEmail1;
	private String userEmail2;
	private String addrBasic;
	private String addrDetail;
	private String addrZipNum;
	private LocalDateTime regDate;
	
	
	//한 명의 회원은 글을 여러 개 작성할 수 있다.
	//마이 페이지에서는 특정 회원이 작성한 글 목록을 나타내야 한다.
	//회원 정보와 글 목록은 서로 다른 다른 테이블로 이루어져 있고
	//마이페이지에서는 해당 정보를 한 번의 DB 연동으로 가져올 수 있도록(조인) 하기위해
	//JOIN문법으로 테이블을 합친 뒤 원하는 컬럼을 선택해서 가져오는 것이다.
	
	//회원이 1이고 글이 n이니,   1이 UserVO이기 떄문에 UserVO안에 N의 값을 뜻하는 FeeBaordVO객체의 모음을 저장할 수 있는 리스트를 선언하자.
	//즉, 1:N관계의 테이블을 list형태로 선언하자.
	private List<FreeBoardVO> userBoardList; //게시글 하나의 내용을 뜻함. 근데 한명의 회원은 여러글을 작성할 수 있다. 즉, 하나의 UserVO가 freeBoardVO를 여러개 들수 있으니 리스트로선언.
	
	
	
	
	
	
	
	
	
	//다 만들었으면 맵퍼를 작성하자~ 인터페이스로 IUserMapper를만들자~
	
	
}
