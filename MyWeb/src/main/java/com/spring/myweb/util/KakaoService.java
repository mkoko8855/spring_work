package com.spring.myweb.util;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.spring.myweb.command.KakaoUserVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KakaoService {
	
	//프로퍼티스에 있는 값들을 넣을것이다. 그래서 변수로 적어줬다. 서블릿컨피그.xml에 카카오를 추가 해 준 다음 이걸 썼다.
	@Value("${kakao.clientId}") //이게 프로퍼티스에 있는 kakao.clientId 이다.
	private String kakaoClientId;
	@Value("${kakao.clientSecret}")
	private String kakaoClientSecret;
	@Value("${kakao.redirectUri}")
	private String redirectUri;
	
	
	private String sessionState = "kakao_oauth_state"; //세션에 저장할떄 이름을 kakao_oauth_state로할것이다.
	
	
	//카카오 아이디로 로그인 인증 url 생성
	public String getAuthorizationUrl(HttpSession session) { //이 메서드는 유저컨트롤러가 부를 것이다.
		//이제 세션 유효성 검증을 위해 난수를 생성할 것이다.
		String state = UUID.randomUUID().toString();
		//생성된 난수값을 session에 저장하자.
		//매개값으로 세션이 오니,
		session.setAttribute(sessionState, state); 
		
		String requestUrl = String.format("https://kauth.kakao.com/oauth/authorize?client_id=%s&redirect_uri=%s&response_type=code&state=%s", kakaoClientId, redirectUri, state); //포맷은 printf와 같이 문자열 조합을 할 때 써줘야하는것
		//바로 위에값 3개말고도 state를 카카오API에 보낼 거임. 코드가 털려도 안전장치로 랜덤한 난수까지 포함해서 확인하기위해, 내가 보낸 요청이 제대로 왔는지 확인하려고 사용함
		
		return requestUrl;
	}

	
	//이 메서드는 카카오 아이디로 CallBack처리 및 AccessToken을 얻기 위한 메서드
	public String getAccessToken(HttpSession session, String code, String state) {
		
		log.info("getAccessToken이 호출됐다!");
		
		//요청보내자.
		//요청 uri
		String requestUri = "https://kauth.kakao.com/oauth/token"; //토큰달라는 요청. 
		
		//CallBack으로 전달받은 세션 검증용 난수값과 세션에 저장되어 있는 값이 일치하는지부터 확인하자.
		//그려려고 우리가 state를 썼었다.
		String sessionValue = (String) session.getAttribute(sessionState);
		
		if(sessionValue.equals(state)) { //로그인 할 때 만든 state와 성공 후 응답된 state가 일치하니? > 일치한다면
			//요청 헤더 설정
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8"); //headers에 추가하겠다. 카카오 문서에 기본 정보의  Content-type부분을 복사해서 붙여넣자.
	
			//요청 파라미터를 설정할 수 있는 객체를 생성하자
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			
			//문서에서 보내달라고했던 필수 파라미터인 grant_type, client_id, redirect_uri, code, client_secret를 붙여줘야함(카카오 디펠로프 문서에 있음)
			params.add("grant_type", "authorization_code");
			params.add("client_id", kakaoClientId);
			params.add("redirect_uri", redirectUri);
			params.add("code", code);
			params.add("client_secret", kakaoClientSecret);
			
			//요청 파라미터 설정이 끝났으니, 카카오 서버로 rest방식의 post 통신을 보내 줄 객체를 생성하자
			RestTemplate template = new RestTemplate();
			//요청 보내기 위해 객체를 하나 더.   params와 headers 전달 해 줄 것이다.
			HttpEntity<Object> requestEntity = new HttpEntity<Object>(params, headers);
			//통신을 보내면서 응답데이터를 바로 리턴 받을 수 있다.
			//첫번쨰 파라미터 값으로는 요청 url, 두번째는 요청 방식(method), 세번째는 헤더와 요청 파라미터 정보 엔티티 객체, 네번째는 응답 데이터를 받을 객체의 타입(vo를 받을건지 Striong으로 받을건지 map으로받을건지)..
			ResponseEntity<Map> responseEntity = template.exchange(requestUri, HttpMethod.POST, requestEntity, Map.class); //JSON데이터니까 Map으로받자
			
			//응답 데이터에서 필요한 정보를 받아보자
			//일단 다 확인하고 액세스토큰만 뽑자
			Map<String, Object> responseData = (Map<String, Object>) responseEntity.getBody(); //응답헤더정보보고싶으면 getHeaders()로받어. 우린 응답한 데이터가 궁금하니까 바디.
			log.info("토큰 요청 응답 데이터: {}", responseData);//바디에실려온 레스폰스데이타를 확인해보자
			
			return (String) responseData.get("access_token"); //이 access_token은 카카오 문서에써있어~
			
			
		
		} else {
			log.info("state 일치하지 않음!");
			return null; //더이상진행해주지않기위해 null.
		}
		
	}

	//rest방식으로 한번 더 보내자. 즉, 컨트롤러가 건내준 Access Token을 이용하여 카카오 사용자 프로필 API를 요청하자.
	public KakaoUserVO getUserProfile(String accessToken) {
		//문서에 REST API > 사용자 정보 가져오기 (기본정보) 보면 GET/POST/v2/user/me HTTP/1.1 어쩌고적혀있다. 보면서하자
		String requestUri = "https://kapi.kakao.com/v2/user/me";
		
		//요청 헤더 설정
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + accessToken);
		//겟방식이면 content-type 안해도됨. 서비스에 준거는 포스트방식이었기에 content타입을 설정해줬다.
		
		
		//요청 보내기
		//레스트템플릿으로 보내자. 다른걸로도 보낼 수 있다. 일단 레스트템플릿으로 보내자
		RestTemplate template = new RestTemplate();
		ResponseEntity<KakaoUserVO> responseEntity = template.exchange(
				requestUri,
				HttpMethod.GET,
				new HttpEntity<>(headers),
				//뭘로전달받을거야? 서비스에서는 Map.class여서 맵으로 줬었다.
				//근데 다른방식으로 받는다했으니 .. 스트링으로받아도된다. KakaoUserVO 만들고 값만들어주고오자. > 작성해주고 와서
				KakaoUserVO.class
			);
		
		
		//응답 바디 읽기
		KakaoUserVO responseData = responseEntity.getBody();
		log.info("user profile: {}", responseData); //데이터한번찍어보자

		return responseData;
		
	}
	
		//레스트템플릿 말고 다른 방법이 있다. (레스트가 더편함)
		//요청 헤더 설정이랑 요청 보내기 랑 응답바디읽기를 주석처리하고, 다른 방법(객체)를 사용하는 방법을 소개한다.
		/* 
		 public KakaoUserVO getUserProfile(String accessToken){
		 	
		 	
		 	REST API의 URL 설정시작.
		 	URL url = new URL("https://kapi.kakao.com/v2/user/me");  보내고자하는요청url적자
		 	
		 	요청을위한객체만들자, 즉 요청을 보내고 응답을 받는 객체 선언
		 	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		 	
		 	이커넥션객체를통해 세팅하자. 즉, 전송 방식 설정.
		 	connection.setRequestMethod("GET"); 겟방식으로보낼꺼니 겟.
		 	
		 	헤더 설정.
		 	connection.setRequestProperty("authorization", "Bearer " + accessToken);
		 	
		 	요청 후 응답 코드 확인
		 	int responseCode = connection.getResponseCode(); //이제 응답상태코드가 int로 전달됨
		 	log.info("code: " + responseCode); 응답이 잘 왔으면 200 이런식으로 왔겠지
		 	
		 	이제 데이터를 읽어내자. 즉, 응답과 함께 전달된 데이터를 BufferedReader를 이용해서 읽어내기.
		 	BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		 	
		 	한줄씩 읽자
		 	String line;
		 	
		 	JSON데이터를 문자열로 읽어서 한 줄씩 추가.
		 	StringBuilder sb = new StringBuilder();
		 	While((line = reader.readLine()) != null){ 한줄씩 읽다가 널이 아닐때까지 ..
		 		sb.append(line);
		 	}
		 	reader.close();
		 	
		 	이제 sb를 확인해보면, sb에 문자열 형태로 제이슨데이터가 다 올 것.
		 	
		 	
		 	log.info("response data: " + sb.toString()); > 근데 뽑아 낼떄는 제이슨 라이브러리가필요하다.
		 	
		 	메이븐 레파지토리가서 gson이라고 검색 > 또는 json-simple 검색 후 > 심플에서의 1.1.1버전에서 가져와서
		 	pom.xml에서 붙여넣자. > 메이븐 업데이트 후 > 자바 객체로 뽑아내보자
		 	
		 	이제 문자열로 이루어진 JSON 데이터를 변환해서 JSONobejct 타입으로 저장하자.
		 	JSONParser parser = new JSONParser();
		 	JSONObject jsonObject = (JSONObject)parser.parse(sb.toString()); 
		 	
		 	이제 필요한 데이터를 꺼내자
		 	JSONObject kakaoAccount = (JSONObject) jsonObject.get("kakao_account"); 
		 	JSONObject profile = (JSONObejct)kakaoAccount.get("profile");
		 	
		 	이제 꺼내자
		 	String nickname =(String) profile.get("nickname"); 오브젝트니 스트링으로 변환.
		 	String email =(String) kakaoAccount.get("email");
		 	String profileImage =(String)profile.get("profile_img_url");
		 	String thumbnailImage =(String) profile.get("thumbnail_img_url");
		 	
		 	
		 	return responseData;
		 }
		 */
	
	
	
}
