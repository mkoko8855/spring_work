package com.spring.myweb.util;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;




@Component //이것도 빈 등록 아노테이션이다. 
@Slf4j
public class MailSenderService {

	
	@Autowired
	private JavaMailSender mailSender; //아까 만든 객체를 이쪽으로 주입(오토와이어드)해줘~
	private int authNum;
	
	//인증번호를 위한 난수를 만들자
	//난수 발생(마음대로, 복잡한 난수를 만들고 싶으면 복잡하게 만들어도돼)
	public int makeRandomNumber() {
		//난수의 범위 : 111111 ~ 999999 (알파벳쓸거면 반복문써야함)
		Random r = new Random();
		int checkNum = r.nextInt(888888) + 111111;
		
		log.info("인증번호: " + checkNum);
		return checkNum;
		
	}
	
	
	
	//메서드 하나 더 만들거야. 이메일 양식을 결정하는 메서드
	//즉, 회원 가입 시 사용할 이메일 양식
	public String joinEmail(String email) {  //이 조인이메일은 컨트롤러가 부를 것이니 authNum을 돌려줘야함. 리턴도써줘~
		
		authNum = makeRandomNumber();
		//이 어쓰넘을 화면단으로 넘겨야 함
		
		String setFrom = "mkoko8855@naver.com"; //email-config에 설정한 발신용 이메일 주소를 입력 해야 한다.
		String toMail = email; //수신 받을 이메일(가입하고자 하는 사람의 이메일) > 이거는 자바스크립트한테 받겠지 비동기 방식으로 전달받아서 여기 구문에 세팅한다.
		String title = "회원 가입 인증 이메일 입니다."; //이메일 제목
		String content = "홈페이지를 방문해 주셔서 감사합니다." +
                "<br><br>" + 
                "인증 번호는 <strong>" + authNum + "</strong> 입니다." +
                "<br>" +
                "해당 인증 번호를 인증번호 확인란에 기입해 주세요."; //이메일에 삽입할 내용.
		
	mailSend(setFrom, toMail, title, content);
	return Integer.toString(authNum); //정수를 문자열로 리턴.
	
	}
	
	
	
	//이제 메일을 안보냈음. 준비만 해놓을 거 뿐.
	//이제 이메일 전송하는 메서드를 만들자
	private void mailSend(String setFrom, String toMail, String title, String content) {
		//joinEmail이 mailSend를 해주고 마지막으로 리턴해줄거임
		
		
		
		try {
			//이메일 전송
			MimeMessage message = mailSender.createMimeMessage(); //주입받은 mailSender을 여기서 사용!
			//얘만으로는 부족하기 떄문에
			
			//기타 설정들을 담당할 MimeMessageHelper 객체를 생성.
	        //생성자의 매개값으로는 MimeMessage 객체, bool, 문자 인코딩 설정
	        //true 매개값을 전달하면 MultiPart 형식의 메세지 전달이 가능. (첨부 파일)
	        //값을 전달하지 않는다면 단순 텍스트만 사용.
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");	//텍스트만 전달할꺼면 false, 이미지나 오디오나 gif라면 true. 마지막은 인코딩.

			
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			
			//true -> html형식으로 전송, 값을 안주면 단순 텍스트로 전달을 한다.
			helper.setText(content, true);

			//메일 전송
			mailSender.send(message);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		} 
		
	}
	
	
	
	
	
	
	
	
}
