package com.spring.db.common;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.db.model.ScoreVO;

import lombok.AllArgsConstructor;

//JdbcTemplate에서 SELECT 쿼리를 위한 ResultSet 사용을 편하게 하기 위한
//클래스를 생성 해주는 것이다. JdbcTEmplate에게 객체를 전달 하는 것이다.
//RowMapper라는 인터페이스를 구현 해야 한다.
public class ScoreMapper implements RowMapper<ScoreVO> { //제네릭이 달려있느데 이 <T>는 타입을 적자. 한 행의 데이터를 포장할 껀데, 무슨 객체로 포장할껀지 알려주면 된다. 우린 한 학생의 점수 정보를 ScoreVO로 포장할꺼다

	@Override
	public ScoreVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("mapRow 메서드 호출!");
		System.out.println("rowNum: " + rowNum); //메서드가 실행될때마다 한번 확인해보려고 출력문적은거임
		
		//객체하나생성하자
		//SocreVO클래스 가서 @AllArgsConstructor 떄려주자
		//다시 가서 @NoArgsConstructor까지 만들어주자
		//이렇게포장하는거야
		ScoreVO vo = new ScoreVO(
				rs.getInt("stu_id"),
				rs.getString("stu_name"),
				rs.getInt("kor"),
				rs.getInt("eng"),
				rs.getInt("math"),
				rs.getInt("total"),
				rs.getDouble("average")
				); 
		return vo; 
	} 

	
	
	
	//근데 ScoreDAO 맨위에 이걸 전체로 붙여줬다. 클래스 내부에 클래스를 선언해준 것이다.
	//이건 의미가 없는 클래스일수도..
	
	
	
	
	
	
	
}
