package com.spring.myweb.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.myweb.command.SnsBoardVO;
import com.spring.myweb.snsboard.service.ISnsBoardService;
import com.spring.myweb.util.PageVO;

import lombok.extern.slf4j.Slf4j;

@RestController //그냥 Controller적으면 레스폰스바디를 메서드마다 써줘야함
@RequestMapping("/snsboard")
@Slf4j
public class SnsBoardController {

	@Autowired
	private ISnsBoardService service;
	
	
	//우린 레스트컨트롤러다. 메서드선언했을 때 리턴값이 뷰 리졸버한테 가는게 아님. 클라이언트로바로감
	
	
	@GetMapping("/snsList") //게시판처음들어가면 리스트가보이겠지..
	public ModelAndView snsList(){ //뷰리졸버한테 안가니 void, string 다 안됨..
		ModelAndView mv = new ModelAndView();
		mv.setViewName("snsboard/snsList");
		return mv; //윗주소로 보내달라!
	}
	
	
	
	
	
	
	
	@PostMapping("/upload")
	public String upload(MultipartFile file, SnsBoardVO vo) { //jsp에서 fetch로 작성한 작성자, 글내용, 첨부한 파일이 이 메서드로 날라온다. 이미지는 file로, witer랑 content는 vo로.
		service.insert(vo, file); //서비스야 인서트좀.
		
		return "success";
	}
	
	
	
	//글 목록불러오기. 페이지 총 개수 얻을 필요 X (즉, 더보기필요X). 무한으로 불러올 것이니 더 이상 불러올게 없으면 안불러오게할꺼임.
	@GetMapping("/{page}")
	public List<SnsBoardVO> getList(@PathVariable int page){ 
		log.info("/snsboard/getList: GET");
		PageVO vo = new PageVO();
		vo.setPageNum(page);
		vo.setCpp(3);
		
		
		return service.getList(vo);
	}
	
	
	
	
	
	//게시글의 이미지 파일을 전송 요청.
	//이 요청은 img 태그의 src라는 속성을 통해서 요청이 들어오고 있다.
	//snsList.jsp 페이지가 로딩되면서, 글 목록을 가져오도고 있고, 스크립트를 이용해서 화면을 꾸밀 때,
	//img 태그의 src에 작성된 요청 url을 통해 자동으로 요청이 들어오게 된다.
	//요청을 받아주는 메서드를 선언하여 경로에 지정된 파일을 보낼 예정이다.
	
	@GetMapping("/display/{fileLoca}/{fileName}")
	public ResponseEntity<byte[]> getFile(@PathVariable String fileLoca, @PathVariable String fileName){
		
		log.info("filename: " + fileName);
		log.info("fileLoca: " + fileLoca);
		
		File file = new File("C:/test/upload/" + fileLoca + "/" + fileName); //내가 가지고올 이미지를 지목했다.
		log.info(file.toString());
		//위 파일을 바이트 배열 단위로 잘게 짤라서 리턴해야 한다.
		//그 전에 객체 하나 생성해주자.
		
		//리스폰스엔터티란? 응답에 대한 여러가지 정보를 전달을 할 수 있는 객체이다.
		//응답 내용, 응답이 성공했는지에 대한 여부, 응답에 관련된 여러 설정들을 지원 한다.
		ResponseEntity<byte[]> result = null; //근데 객체 생성 전에 null주자
		
		//응답도 헤더파일이 있다.
		HttpHeaders headers = new HttpHeaders();
		
		//probeContentType : 매개값으로 전달받은 파일의 타입이 무엇인지를 문자열로 반환.
		//사용자에게 보여주고자 하는 데이터가 어떤 파일인지에 따라 응답 상태 코드를 다르게 리턴하고
		//컨텐트 타입을 제공해서 화면단에서 판단할 수 있게 처리할 수 있다.
		try {
			headers.add("Content-type", Files.probeContentType(file.toPath())); //내가 전송하고자 하는 file의 경로를 probe메서드한테 전달하면 내가 지정한 경로의 파일이 자동으로 Content-Type으로 바꿔준다.
			headers.add("merong", "hello");
			//ResponseEntity객체에 전달하고자 하는 파일을 byte[]로 변환해서 전달.
			//header 내용도 같이 포함, 응답 상태 코드를 원하는 형태로 전달이 가능.
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK); //result에다가 객체 생성. File file = new File("C:/test/upload/" + fileLoca + "/" + fileName); 이걸 변환해서 매개값으로 줘야하는데..변환어떻게해? Filecopy를 사용하자. 그리고 헤더스도...또.
		} catch (IOException e) {
			e.printStackTrace();
			result = new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR); //문제가 발생했다면 파일 전달이 안되겠지.
		} 
			return result;
		
	}
	
	
	
	
	
	
}
