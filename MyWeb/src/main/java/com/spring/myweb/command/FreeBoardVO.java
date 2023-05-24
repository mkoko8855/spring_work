package com.spring.myweb.command;

import java.time.LocalDateTime;
//class 1

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 CREATE TABLE freeboard(
	bno INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(300) NOT NULL,
    writer VARCHAR(50) NOT NULL,
    content VARCHAR(3000) NOT NULL,
    reg_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_date DATETIME DEFAULT NULL          #수정된 날짜
); 
  
  
 
*/

@Getter
@Setter
@ToString
public class FreeBoardVO {
	
	private int bno;
	private String title;
	private String writer;
	private String content;
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
	
	//하나의 게시물이 몇 개의 댓글을 포함하는가에 대한 값.
	private int replyCnt;
}
