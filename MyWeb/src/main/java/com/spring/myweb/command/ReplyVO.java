package com.spring.myweb.command;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
rno INT PRIMARY KEY AUTO_INCREMENT,
bno INT,  #외래키

FOREIGN KEY (bno)
REFERENCES freeboard(bno)
ON DELETE CASCADE,

reply VARCHAR(1000),
reply_id VARCHAR(50),
reply_pw VARCHAR(50),
reply_date DATETIME DEFAULT CURRENT_TIMESTAMP,
update_date DATETIME DEFAULT NULL
*/


@Getter
@Setter
@ToString
public class ReplyVO {

	private int rno;
	private int bno; //글번호
	private String reply; //댓글내용
	private String replyId; //댓글남긴사람
	private String replyPw; //댓글비밀번호
	private LocalDateTime replayDate;
	private LocalDateTime updateDate;
	
	//매퍼테스트는 시간상 생략!
	
	
}
