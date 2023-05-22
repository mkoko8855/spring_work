package com.spring.myweb.reply.mapper;

import java.util.List;
import java.util.Map;

import com.spring.myweb.command.ReplyVO;

public interface IReplyMapper {

	
	
	
	//댓글 등록. sql은 insert쓰니까 void
	void replyRegist(ReplyVO vo);//댓글 등록. sql은 insert쓰니까 void. 매개값으론 여러개니까 ReplyVO vo
	
	
	
	
	
	
	//목록 요청(페이징 할거임)
	List<ReplyVO>getList(Map<String, Object> data); //where절에 번호가 들어감. 파람 말고 맵으로 써보자! > 마이바티스에서는 매개값이 2개이상 이면 파람이던 맵이던 포장을 해야하니 맵을 썼다!
	
	
	
	
	
	
	//댓글 개수(페이징 때 필요하니까)
	int getTotal(int bno); //select count(*) from freereply where bno=?
	
	
	
	
	
	
	
	//비밀번호 확인(수정, 삭제할 때 맞으면 수정 삭제하기위해)
	boolean pwCheck(ReplyVO vo);  
	
	
	
	
	
	
	//댓글 수정
	void update(ReplyVO vo);
	
	
	
	
	
	
	
	//댓글 삭제
	void delete(int rno); //글번호가 아닌 댓글번호를 주자
	
	
	
	
	
	
	
}
