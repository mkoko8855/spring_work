package com.spring.myweb.command;

import java.time.LocalDateTime;

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
	
	//다 만들었으면 맵퍼를 작성하자~ 인터페이스로 IUserMapper를만들자~
	
	
}
