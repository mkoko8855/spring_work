package com.spring.db.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.db.common.ScoreMapper;
import com.spring.db.model.ScoreVO;




@Repository
public class ScoreDAO implements IScoreDAO {

	//내부(중첩)클래스 (inner class)
	//두 클래스가 굉장히 긴밀한 관계가 있을 때 주로 선언.
	//해당 클래스 안에서만 사용할 클래스를 굳이 새 파일로 선언하지 않고 만들 수 있다.
	//ScoreMapper클래스로가서 따온 다음에 클래스 내부에 클래스를 선언해서 객체를 만들 수 있다.
	
	
	
	class ScoreMapper implements RowMapper<ScoreVO> { //제네릭이 달려있느데 이 <T>는 타입을 적자. 한 행의 데이터를 포장할 껀데, 무슨 객체로 포장할껀지 알려주면 된다. 우린 한 학생의 점수 정보를 ScoreVO로 포장할꺼다

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
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Spring-jdbc 방식의 처리 : JdbcTemplate를 활용한다!
	
	//데이터 연동 로직을 작성하자.
	
	//메이븐 레파지토리 사이트에서 spring-jdbc검색 후,
	//첫번째꺼 클릭 후, 5.3.18클릭. 코드 클릭 후, 복사 하고
	//와서 pom.xml에 붙여주자
	
	
	//그리고 다시 메이븐 레파지토리 사이트에서 hikaricp 검색 후,
	//제일 많이 쓰는 3.3.1클릭. 코드 클릭 후, 복사 하고
	//와서 pom.xml에 붙여주자
	
	
	
	//그리고 다시 메이븐 레파지토리 사이트에서 mysql connector 검색 후,
	//제일 많이 쓰는 두번째꺼 클릭 후, 8.0.33 가서 코드복사 고고
	//와서 pom.xml에 붙여주자
	
	
	
	//그리고 spring > root-context.xml가서 Hikari를 빈으로 등록하러 가자~
	
	
	//등록하고 와서,
	
	@Autowired
	private JdbcTemplate template; //이미 루트컨텍스트에 빈 등록 해놨다.
	//그럼 주입해야지. @Autowired적어주자 > 컨테이너를 돌아다니면서 JdbcTemplate이라는 타입에 맞는 것들을 찾아 template에 주입해라!
	
	
	
	
	
	@Override
	public void insertScore(ScoreVO vo) {
		//DB에 인서트하는것만 집중하면됨
		String sql = "INSERT INTO scores (stu_name, kor, eng, math, total, average) VALUES(?, ?, ?, ?, ?, ?)";
		template.update(sql, vo.getStuName(), vo.getKor(), vo.getEng(), vo.getMath(), vo.getTotal(), vo.getAverage());
	}

	
	@Override
	public List<ScoreVO> selectAllScores() {
		String sql = "SELECT * FROM scores ORDER BY stu_id ASC";
		//템플릿을 부르자. 인서트 업데이트 딜리트는 update를 부르지만 셀렉트문은 query다.
		return template.query(sql, new ScoreMapper()); //query쓰고 자동완성하면 Rowmapper 어쩌고 있다. 자동 완성한 다음, ScoreMapper클래스 하나 만들러가자.
		//얘는 쿼리 메서드는 List<ScoreVO>를 리턴해준다.
		//그럼 우리는 리턴을 받자 리턴도 추가해주자.
	}

	
	
	@Override
	public void deleteScore(int num) {
		String sql = "DELETE FROM scores WHERE stu_id = ?";
		template.update(sql, num);
	}

	
	
	
	@Override
	public ScoreVO selectOne(int num) { //리턴타입은 스코어DAO타입이다. 한명만조회하니.
		String sql = "SELECT * FROM scores WHERE stu_id=?";
		try {
			return template.queryForObject(sql, new ScoreMapper(), num); //조회결과가 하나일떄 쓰는 메서드는 쿼리가 아니라 쿼리폴오브젝트이다. 어떻게 포장할지 적으면 된다. ScoreMapper가 가지고있엇지? 그럼 쿼리폴오브젝트는 ScoreVO 객체 하나만 리턴해준다~  num은 ?값을 채워준 것이다.
		} catch (Exception e) {
			return null; //스코어컨트롤러가서 바꿔주자.
			
			//참고로 쿼리폴오브젝트는 예외처리를 해줘야 한다
		}
		
	}

}
