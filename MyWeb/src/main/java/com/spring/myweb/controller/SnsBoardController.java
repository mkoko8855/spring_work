package com.spring.myweb.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
		
	
	
		
		//상세보기 처리
		@GetMapping("/content/{bno}")
		public SnsBoardVO getContent(@PathVariable int bno) {
			return service.getDetail(bno);
		}
	
	
		
		
		//삭제하기
		@DeleteMapping("/{bno}") 
		public String delete(@PathVariable int bno, HttpSession session) { 
			
			//id꺼내오자
			String id = (String) session.getAttribute("login");
			
			SnsBoardVO vo = service.getDetail(bno);
			
			
			//비교하자
			if(id == null || !id.equals(vo.getWriter())) { ////로그인을 아예 안한 사람일수도있다. 그럼 위 String id 에 널이올수도있으니 널체크도해야한다.  
				return "noAuth";
			} //이 if문을 실행시키지 않았다는 것은 로그인X, 작성자와 로그인 중인 사용자가 일치한다는 뜻이다.
			
			//이제 삭제진행
			service.delete(bno);
			
			//글이 삭제되었다면 더 이상 이미지도 존재할 필요가 없으므로
			//이미지도 함께 지목해서 삭제해주세요. DB삭제하고, 마지막에 글에 달려있던 이미지도 같이 지우자
			//이미지 지우는 법은
			File file = new File(vo.getUploadPath()+vo.getFileLoca() + "/" + vo.getFileName()); //"지우고자 하는 파일의 경로를 문자열로."
			return file.delete() ? "success" : "fail"; // -> 삭제가 성공했다면 true, 실패하면 false. 
		}
	
			
		@GetMapping("/download/{fileLoca}/{fileName}")
		public ResponseEntity<byte[]> download(@PathVariable String fileLoca, @PathVariable String fileName){
			
			//파일 객체 생성하자
			File file = new File("C:/test/upload/" + fileLoca + "/" + fileName);
			
			ResponseEntity<byte[]> result = null;
			
			//헤더파일에 작성을 해주면됨
			HttpHeaders header = new HttpHeaders();
			
			//헤더에 응답하는 본문을 브라우저가 어떻게 표시해야 할 지 알려주는 헤더 정보를 추가한다.
			//inline인 경우, 웹 페이지 화면에 표시되고 attachment인 경우에는 다운로드를 제공한다.
			//다운로드를 제공하기 위해, 사용자의 브라우저가 무엇인지 알아야 한다.
			
			//request객체의 getHeader("User-Agent") -> 단어를 뽑아서 브라우저 이름을 확인
	        //ie: Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko  
	        //chrome: Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36

	        //파일명한글처리(Chrome browser) 크롬
	        //header.add("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") );
	        //파일명한글처리(Edge) 엣지 
	        //header.add("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
	        //파일명한글처리(Trident) IE
	        //Header.add("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", " "));
			
			//우린 uuid(영문과 숫자로 이뤄진 랜뎜문자열)로 지정했기에 위에 인코딩 코드 안써도됨
			
			header.add("Content-Disposition", "attachment; filename=" + fileName); //화면이 표기가 되어야함. 근데 content-disposition을 안써줘도 기본값으로 헤더에 나감. 아무튼 사용자가 파일을 다운 받는 파일 이름은 우리가 지정한 파일이름으로 나감.
			
			try {
				result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);//객체생성하고
			} catch (IOException e) {
				e.printStackTrace();
				result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //500에러에 대한 준비를 화면단에서 하면 됨. 그럼 500에러가 전달될테고 브라우저는 응답 정보가500이 왔으니 에러페이지가 준비됐으니 연결되겠지.
			} 
			return result;
		}
}
