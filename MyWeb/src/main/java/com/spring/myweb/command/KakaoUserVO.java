package com.spring.myweb.command;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KakaoUserVO {
	
	/*
	  @JsonProperty
	  Rest API 통신을 진행하다 보면, 데이터를 주고 받을 때, JSON 형태의 데이터를 주로 이용한다.
	  JSON은 스네이크 케이스 방식을 사용하고, 서버단에서는 자바객체니까 카멜 케이스 방식을 사용한다.
	  서로 표현 방식이 다름에 따라 데이터의 key가 달라지는 경우가 발생한다.
	  이 때, @JsonProperty를 사용하면 값을 매칭할 수 있다.
	 */
	
	
	
	
	@JsonProperty("id") //문서에 id라고 적혀있기에, userId라고 할 수 없어서 id라고 해준 것이다.
	private long id;
	
	@JsonProperty("connected_at")
	private LocalDateTime connectedAt; //제이슨으로 날라오는 데이터 중에, 언제 로그인 했는지 connectedAt이라는게있다.
	
	@JsonProperty("kakao_account") //이런 이름의 제이슨데이터가 있으니 KakaoAccount로 포장을해라! 라는 얘기.
	private KakaoAccount kakaoAccount; //클래스타입이다. 문서를 보면 connected_at은 그냥 값 하나다. kakao_account는 객체형태로 있다. 클래스를 하나 더 선언하기엔 좀 그러니, 내부클래스사용하자 
	
	@Getter
	@Setter
	@ToString
	static class KakaoAccount {
		
		private String email;
		private Profile profile;    //문서를 보면 kakao_account 객체 안에 profile(프로필)도 객체로 이루어져 있으니 얘도 클래스로 가져 와야 한다.
		@Getter
		@Setter 
		@ToString
	static class Profile {
			
			private String nickname;
			@JsonProperty("profile_image_url") //위 email과 profile과 nickname은 이름이 같기 떄문에 @JsonProperty 안붙여도됨.
			private String profileImageUrl;
			@JsonProperty("thumbnail_image_url")
			private String thumbnailImageUrl; //제이슨데이터가 뭘로 오는지 문서로보자. thumbnail로 온다.
	
			
		
		
		}
		
	}
	
	
	
	
	
}
