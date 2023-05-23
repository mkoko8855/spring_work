package com.spring.myweb.reply.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.myweb.command.ReplyVO;
import com.spring.myweb.reply.mapper.IReplyMapper;
import com.spring.myweb.util.PageVO;

@Service
public class ReplyService implements IReplyService {

	@Autowired
	private IReplyMapper mapper;
	
	@Autowired
	private BCryptPasswordEncoder encoder;


	
	
	
	@Override
	public void replyRegist(ReplyVO vo) {
		//컨트롤러가 준달해준 ReplyVO안에 비밀번호를 새롭게 세팅 해주자(BCrypt~선언해주자)
		vo.setReplyPw(encoder.encode(vo.getReplyPw()));
		mapper.replyRegist(vo);
	}

	
	
	
	
	@Override
	public List<ReplyVO> getList(int bno, int pageNum) { //이제컨트롤러가 getList를 부르면서 bno랑 pageNum을 달라고 하네?
	
		PageVO vo = new PageVO();
		vo.setPageNum(pageNum); //화면에서 전달된 페이지 번호
		vo.setCpp(5); //댓글은 한 화면에 5개씩 보여주려고 작성.
		
		Map<String, Object> data = new HashMap<>();
		data.put("paging", vo); //페이징 쿼리를 위한 pageNum과 cpp를 보내는거고
		data.put("bno", bno); //몇번 글에 댓글을 가져올지에 대한 정보를 Map에 포장.
		
		//이제 mapper부르자~ data줄테니 sql만들어서돌려
		return mapper.getList(data);
		
		
		
	
	}

	
	
	
	@Override
	public int getTotal(int bno) {
		return mapper.getTotal(bno);
	}

	
	
	
	@Override
	public boolean pwCheck(ReplyVO vo) {
		String dbPw = mapper.pwCheck(vo.getRno());  //얀얀 메퍼얀 체크해라 vo줄겡
		
		return encoder.matches(vo.getReplyPw(), dbPw); //원래pw와 지금 dbPw
		
		//다르면 false가 리턴된다. 컨트롤러로 리턴이 될테니 컨트롤러로가자
		
		
		
	}

	
	
	
	@Override
	public void update(ReplyVO vo) {
		mapper.update(vo); //작성하고  sql쓰러 매퍼로가자
	}

	@Override
	public void delete(int rno) {
		mapper.delete(rno);

	}

}
