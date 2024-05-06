package com.choongang.moggozi2.controller;


import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import com.choongang.moggozi2.entity.User;
import com.choongang.moggozi2.repository.UserRepository;
import com.choongang.moggozi2.service.UserService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@RestController
public class NaverController {

	@Autowired
	private UserRepository newrepository;
	
	@Autowired
	private UserService service;
	
	
	@RequestMapping("/naver_login")
	public RedirectView naver_login(HttpSession session) {
	    String client_id = "VlSp23dfBqD_vMttWz1f";
	    String redirect_uri = "http://localhost:80/naver_login-callback";
	    SecureRandom random = new SecureRandom();
	    String state = new BigInteger(130, random).toString();
	    String login_url = "https://nid.naver.com/oauth2.0/authorize?response_type=code"
	            + "&client_id=" + client_id
	            + "&redirect_uri=" + redirect_uri
	            + "&state=" + state;

	    session.setAttribute("state", state);

	    return new RedirectView(login_url);
	}
	
//	@RequestMapping("/naver_login-callback")
//	public RedirectView naver_loginCallback(@RequestParam("code") String code, HttpSession session) throws Exception {
//	    String clientId = "VlSp23dfBqD_vMttWz1f";//애플리케이션 클라이언트 아이디값";
//	    String clientSecret = "ZnP13HrOf5";//애플리케이션 클라이언트 시크릿값";
//	    String state = (String) session.getAttribute("state");
//	    String redirectURI = URLEncoder.encode("http://localhost:80/naver_login-callback", "UTF-8");
//	    String apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code"
//	        + "&client_id=" + clientId
//	        + "&client_secret=" + clientSecret
//	        + "&redirect_uri=" + redirectURI
//	        + "&code=" + code
//	        + "&state=" + state;
//	    String accessToken = "";
//	    String refresh_token = "";
//	      URL url = new URL(apiURL);
//	      HttpURLConnection con = (HttpURLConnection)url.openConnection();
//	      con.setRequestMethod("GET");
//	      int responseCode = con.getResponseCode();
//	      BufferedReader br;
//	      if (responseCode == 200) { // 정상 호출
//	        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//	      } else {  // 에러 발생
//	        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
//	      }
//	      String inputLine;
//	      StringBuilder res = new StringBuilder();
//	      while ((inputLine = br.readLine()) != null) {
//	        res.append(inputLine);
//	      }
//	      br.close();
//	      if (responseCode == 200) {
//	        System.out.println(res.toString());
//	      }
//	  
//		return new RedirectView("/main");
//	}
	
	@RequestMapping("/naver_login-callback")
	public RedirectView naver_redirect(HttpServletRequest request) {
		// 네이버에서 전달해준 code, state 값 가져오기
	    String code = request.getParameter("code");
	    String state = request.getParameter("state");

	    // 세션에 저장해둔 state값 가져오기
	    String session_state = String.valueOf(request.getSession().getAttribute("state"));

		// CSRF 공격 방지를 위해 state 값 비교
	    if (!state.equals(session_state)) {
	        System.out.println("세션 불일치");
	        request.getSession().removeAttribute("state");
	        return new RedirectView("/error");
	    }

	    String tokenURL = "https://nid.naver.com/oauth2.0/token";
	    String client_id = "VlSp23dfBqD_vMttWz1f";
	    String client_secret = "ZnP13HrOf5";;

	    // body data 생성
	    MultiValueMap<String, String> parameter = new LinkedMultiValueMap<>();
	    parameter.add("grant_type", "authorization_code");
	    parameter.add("client_id", client_id);
	    parameter.add("client_secret", client_secret);
	    parameter.add("code", code);
	    parameter.add("state", state);

	    // request header 설정
	    HttpHeaders headers = new HttpHeaders();
	    // Content-type을 application/x-www-form-urlencoded 로 설정
	    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

	    // header 와 body로 Request 생성
	    HttpEntity<?> entity = new HttpEntity<>(parameter, headers);

	    try {
	        RestTemplate restTemplate = new RestTemplate();
	        // 응답 데이터(json)를 Map 으로 받을 수 있도록 관련 메시지 컨버터 추가
	        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

	        // Post 방식으로 Http 요청
	        // 응답 데이터 형식은 Hashmap 으로 지정
	        ResponseEntity<HashMap> result = restTemplate.postForEntity(tokenURL, entity, HashMap.class);
	        Map<String, String> resMap = result.getBody();

	        // 리턴받은 access_token 가져오기
	        String access_token = resMap.get("access_token");

	        String userInfoURL = "https://openapi.naver.com/v1/nid/me";
	        // Header에 access_token 삽입
	        headers.set("Authorization", "Bearer "+access_token);

	        // Request entity 생성
	        HttpEntity<?> userInfoEntity = new HttpEntity<>(headers);

	        // Post 방식으로 Http 요청
	        // 응답 데이터 형식은 Hashmap 으로 지정
	        ResponseEntity<String> userResult = restTemplate.postForEntity(userInfoURL, userInfoEntity, String.class);
	        String userResultMap = userResult.getBody();

	        //응답 데이터 확인
	        System.out.println(userResultMap);
	        
	        JsonParser parser = new JsonParser(); 
	        JsonElement element = parser.parse(userResultMap);
	        JsonObject respon = element.getAsJsonObject().get("response").getAsJsonObject();
			String email = respon.getAsJsonObject().get("email").getAsString();;
			String nickname = respon.getAsJsonObject().get("nickname").getAsString();;
			String name = respon.getAsJsonObject().get("name").getAsString();;
			String gender = respon.getAsJsonObject().get("gender").getAsString();;
			
			
			//db 저장
			User user = new User();
			boolean newUser = newrepository.existsByUsername(email);
			if(!newUser) {
				user.setUsername(email);
				user.setUsernick(nickname);
				user.setUsergender(gender);
				user.setNormalname(name);
				service.snsuserjoin(user);
			}

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
			// 세션에 저장된 state 값 삭제
	    request.getSession().removeAttribute("state");

	    return new RedirectView("/main");
	}
	
	
}
