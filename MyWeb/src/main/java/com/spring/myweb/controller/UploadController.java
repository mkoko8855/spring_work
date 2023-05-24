package com.spring.myweb.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/fileupload")
@Slf4j
public class UploadController {

	
	@GetMapping("/upload") //업로드요청이들어오면
	public void form() { //url을 파일 경로로 사용한다. upload.jsp만들었다~
		
	}
	
	@PostMapping("/upload_ok") //파일은 모두 포스트방식이라했었다.
	public void upload(MultipartFile file) { //매개값으로 무엇을 받는가? > upload.jsp에서 폼태그로 이용해 파라미터 name="file"을 날릴텐데, 받자. MultipartFile함수를 이용해 file이라고 변수를 만들어줬다. 그리고 라이브러리 추가를 해줘야 한다. pom.xml에서 주석으로 파일 업로드 API랑, commons-io 라고 적은 곳들을 추가했다.
		
		String fileRealName = file.getOriginalFilename(); //파일 원본명
		long size = file.getSize(); //파일 크기
		//한번 확인해보자
		log.info("파일명: " + fileRealName);
		log.info("파일 크기 : " + size + "bytes");
		
		
		 /*
		 	파일 업로드는 사용자가 첨부한 파일은 DB에 저장하는 것을 선호하지 않는다.
		 	
		 	DB 용량을 파일 첨부에 사용하는 것은 비용적으로도 좋지 않기 때문이다.
		 	
		 	그렇기 때문에 상용되는 서비스들이 파일을 처리하는 방법은 따로 파일 전용 서버를 구축하여 저장하고,
		 	
		 	DB에는 파일의 저장 경로를 지정하는 것이 일반적이다. 
		 	
		 	파일 업로드 시, 파일명이 동일한 파일이 이미 존재할 수도 있고,
		 	
		 	사용자가 업로드 하는 파일명이 영어 이외의 언어로 되어있을 수 있다.
		 	
		 	타 언어를 지원하지 않는 환경에서는 정상 동작이 되지 않을 수 있다. (리눅스..)
		 	
		 	고유한 랜덤 문자를 통해 DB와 서버에 저장할 파일명을 새롭게 만들어 준다.
		 */
			
		UUID uuid = UUID.randomUUID(); 
		log.info("uuid: " + uuid.toString());
		
		String[] uuids = uuid.toString().split("-");
		log.info("생성된 고유 문자열(파일명): " + uuids[0]);
		
		
		String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length()); //확장자
		//우리가 만든 고유의 파일명과 확장자를 생성해줬다.
		
		
		log.info("확장자명: " + fileExtension);
		//DB에는 파일 경로를 저장, 실제 파일은 서버 컴퓨터의 로컬 경로에 저장하는 방식을 사용하겠다.
		
		
		
		
		
		//업로드하려는 폴더경로 하나를 지정하자
		String uploadFolder = "C:/test/upload";
		
		//객체한테 만들어달라고하자(자바IO 할떄 배운 File)
		File f = new File(uploadFolder); 
		if(!f.exists()) { //내가 지정한 위 경로가 실존하지 않는다면, 하나 만들어달라고해야겠지?
			log.info("폴더가 존재하지 않음!");
			f.mkdirs(); //만들어달라고하자 폴더가 존재하지 않으면 생성하라~
		}
		
		File saveFile = new File(uploadFolder + "/" + uuids[0] + fileExtension); //내가 최종적으로 만들어야하는 파일. (랜덤문자열이 섞인 번호에 + 확장자)
		
		
		//실제 파일 저장을 진행하자
		try {
			//매개값으로 받은 첨부 파일을 지정한 로컬 경로에 보내는 메서드이다.
			file.transferTo(saveFile); //첨부된 파일을 saveFile 경로로 보내주세요~
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	
	
	
	@PostMapping("upload_ok2")
	public String upload2(MultipartHttpServletRequest files) { //매개값으로 < 이걸받음 
		
		//첨부파일이 여러 개 전달이 되는 경우 1
		String uploadFolder = "C:/test/upload";
		
		List<MultipartFile> list = files.getFiles("files");
		
		
		for(MultipartFile m : list) {
			try {
				String fileRealName = m.getOriginalFilename(); //원본파일명 얻어내기
				long size = m.getSize(); //파일 크기도 알 수 있음
				
				//위에 폴더 만들었으니 생성문은 쓰지 말고 바로 꽂아넣자
				File saveFile = new File(uploadFolder + "/" + fileRealName);
				
				m.transferTo(saveFile); //위 new File(uploadFolder + "/" + fileRealName) 경로로 원본데이터 파일 명으로 보냄
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "fileupload/upload_ok";
		
	}
	
	
	
	
	@PostMapping("/upload_ok3")
	public String upload3(@RequestParam("file") List<MultipartFile> list) { //3개의 타입을 upload.jsp에서 적었었는데, 그 파일들을 List로 담아서 전송해준다는 것이다.
		
		String uploadFolder = "C:/test/upload";
		
		log.info(list.toString()); //리스트 내부를 한번 보자.
		
		for(MultipartFile m : list) {
			try {
				if(m.getSize() == 0) { //3개에서 1개만 보낸다면 콘솔창에는 에러가 뜬다. 방지하자.
					break; 
				}
				
				String fileRealName = m.getOriginalFilename(); 
				long size = m.getSize();
				File saveFile = new File(uploadFolder + "/" + fileRealName);
				
				m.transferTo(saveFile); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		return "fileupload/upload_ok";

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
