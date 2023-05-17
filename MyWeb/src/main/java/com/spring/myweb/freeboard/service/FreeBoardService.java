package com.spring.myweb.freeboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.myweb.command.FreeBoardVO;
import com.spring.myweb.freeboard.mapper.IFreeBoardMapper;
import com.spring.myweb.util.PageVO;

@Service
public class FreeBoardService implements IFreeBoardService { //이거만들었으니 컨트롤러 만들어주자. FreeBoardController만들러고고
	
	@Autowired
	private IFreeBoardMapper mapper;
	
	
	@Override
	public void regist(FreeBoardVO vo) {
		mapper.regist(vo);
	}

	
	@Override
	public List<FreeBoardVO> getList(PageVO vo) { //얘도 PageVO vo도 받겠지
		return mapper.getList(vo); //그럼이제 얘도 컨트롤러가 건네준 vo를 주자
	}

	
	@Override
	public int getTotal(PageVO vo) { //vo를받고
		return mapper.getTotal(vo); //매퍼한테vo주자
	}
	
	
	@Override
	public FreeBoardVO getContent(int bno) {
		return mapper.getContent(bno);
	}

	@Override
	public void update(FreeBoardVO vo) {
		mapper.update(vo);

	}

	@Override
	public void delete(int bno) {
		mapper.delete(bno); //딜레트해줘 bno줄게
	}

}
