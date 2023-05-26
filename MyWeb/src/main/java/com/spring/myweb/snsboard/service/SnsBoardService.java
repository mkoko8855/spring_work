package com.spring.myweb.snsboard.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.myweb.command.SnsBoardVO;
import com.spring.myweb.snsboard.mapper.ISnsBoardMapper;
import com.spring.myweb.util.PageVO;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class SnsBoardService implements ISnsBoardService {

	
	@Autowired
	private ISnsBoardMapper mapper;
	
	
	
	
	@Override
	public void insert(SnsBoardVO vo, MultipartFile file) {

		//날짜별로 폴더를 생성해서 관리할 예정이다.
		//오늘날짜부터얻자~
		LocalDateTime now = LocalDateTime.now(); //오늘날짜정보가 담긴 객체를 얻음
		//데이트타임포매터로 원하는 날짜로 맞추자
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
		String fileLoca = now.format(dtf); //이 포맷의 리턴타입은 String이다. 
		
		
		//기본 경로도 세팅해주자 C:/test/upload 로 사용해보자.
		String uploadPath = "C:/test/upload/"; //여기 뒤에 fileLoca가 붙을 것임
		
		//폴더 없으면 새롭게 생성해!
		File folder = new File(uploadPath + fileLoca); //위에 마지막에 /그어놨으니 yyyyMMdd 로 생성될거임
		if(!folder.exists()) folder.mkdirs(); //경로가 존재하지 않으면 새롭게 생성해주겠다.
		
		//파일명 따로 만들어주자. 즉, 저장될 파일명은 UUID를 이용한 파일명으로 저장하자.
		//UUID가 제공하는 랜덤 문자열에 -을 제거하고 전부 사용하겠다.
		//일단 확장자 얻어야 하니 원본 파일명부터 얻자
		String fileRealName = file.getOriginalFilename();
		
		UUID uuid = UUID.randomUUID();
		String uuids = uuid.toString().replaceAll("-", ""); //uuid를 문자열로 바꾸고 -을 전부 비어있게 만들자. 그러면 하이푼이 다 지워짐
		
		
		//이제 확장자 얻자
		String fileExtension = fileRealName.substring(fileRealName.lastIndexOf(".")); //끝까지 추출해라. 그럼 확장자가 나옴.
		log.info("저장할 폴더 경로: " + uploadPath);
		log.info("실제 파일명: " + fileRealName);
		log.info("날짜로 된 폴더명: " + fileLoca);
		log.info("확장자: " + fileExtension);
		log.info("고유랜덤문자: " + uuids);
		
		//고유랜덤문자랑 확장자 합쳐
		String fileName = uuids + fileExtension;
		log.info("변경해서 저장할 완성된 파일명: " + fileName);
		
		//우리가 지정한 경로로 사용자의 파일을 전송하자. 즉, 업로드한 파일을 지정한 로컬 경로로 전송.
		File saveFile = new File(uploadPath + fileLoca + "/" + fileName); //uploadPath는 슬래쉬붙여놨고 fileLoca는 날짜였고 슬래쉬 하나 붙이고 uuid로만들어준 파일명을 넣어주자.
		try {
			file.transferTo(saveFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//파일 전송 완료했고, DB에도 세팅해줘야겠지.
		vo.setUploadPath(uploadPath);
		vo.setFileLoca(fileLoca);
		vo.setFileName(fileName);
		vo.setFileRealName(fileRealName);
		//매퍼야 인서트해라
		mapper.insert(vo);
		
		//매퍼xml로가자
		
	}

	
	
	@Override
	public List<SnsBoardVO> getList(PageVO paging) {
		return mapper.getList(paging);
	}

	
	
	@Override
	public SnsBoardVO getDetail(int bno) {
		return mapper.getDetail(bno);
	}

	
	
	@Override
	public void delete(int bno) {
		mapper.delete(bno);
	}

}
