package com.spring.myweb.reply.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.spring.myweb.command.ReplyVO;
import com.spring.myweb.reply.mapper.IReplyMapper;
import com.spring.myweb.util.PageVO;

public class ReplyService implements IReplyService {

	@Autowired
	private IReplyMapper mapper;
	
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
		return false;
	}

	
	
	
	@Override
	public void update(ReplyVO vo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int rno) {
		// TODO Auto-generated method stub

	}

}
