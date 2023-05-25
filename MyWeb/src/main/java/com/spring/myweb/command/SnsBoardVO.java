package com.spring.myweb.command;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/*
    CREATE TABLE snsboard(
	bno INT PRIMARY KEY AUTO_INCREMENT,
    writer VARCHAR(50) NOT NULL,
    upload_path VARCHAR(100) NOT NULL,
    file_location VARCHAR(100) NOT NULL,
    file_name VARCHAR(50) NOT NULL,
    file_real_name VARCHAR(50) NOT NULL,
    content VARCHAR(2000),
    reg_Date DATETIME DEFAULT CURRENT_TIMESTAMP
);
  
*/




@Getter
@Setter
@ToString
@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든값받을수있는생성자
public class SnsBoardVO {
	
	
	private int bno;
	private String writer; 
	private String uploadPath;
	private String fileLoca; //일자별로 폴더를 생성할 예정..EX)20230525
	private String fileName;
	private String fileRealName;
	private String content;
	private LocalDateTime regDate;
	
	
	
	
	
	
	
	
}
