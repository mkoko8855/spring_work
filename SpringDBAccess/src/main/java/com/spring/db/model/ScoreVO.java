package com.spring.db.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
  CREATE TABLE scores(
	stu_id INT PRIMARY KEY AUTO_INCREMENT, # NUMBER타입이 아니라 INT 타입이다.
    stu_name VARCHAR(30) NOT NULL,
    kor INT DEFAULT 0,
    eng INT DEFAULT 0,
    math INT DEFAULT 0,
    total INT DEFAULT 0,
    average DECIMAL(5, 2) 				   #고정자리실수형 > 총자리수 5 > 소수점은 2자리까지
);
  */






//롬복라이브러리 추가하자
//SpringWebBasic의 pom.xml가서 롬복라이브러리 긁어와서 추가해주자 그리고 어노테이션작업하자
@Getter
@Setter
@ToString
@AllArgsConstructor 
@NoArgsConstructor
public class ScoreVO {

	private int stuId;
	private String stuName;
	private int kor;
	private int eng;
	private int math;
	private int total;
	private double average;
	
	
	//총점, 평균을 구하는 메서드를 구해놓자.
	public void calcData() { //이 메서드는 사용자로부터 국 영 수를 받고 부를 예정.
		this.total = this.kor + this.eng + this.math;
		this.average = Math.round((this.total/3.0)*100) / 100.0; //소수점두자리까지만구하기위해.
	}
	
	
	
	
	
	
	
	
	
	
}









