package com.choongang.moggozi2.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import com.choongang.moggozi2.common.GoogleInfResponse;
import com.choongang.moggozi2.common.GoogleRequest;
import com.choongang.moggozi2.common.GoogleResponse;


@RestController
@CrossOrigin("*")
public class GoogleController {

	 @Value("${google.client.id}")
	 private String googleClientId;
	 @Value("${google.client.pw}")
	 private String googleClientPw;

	 @RequestMapping("google_login")
	    public RedirectView loginUrlGoogle(){
	        String reqUrl = "https://accounts.google.com/o/oauth2/v2/auth?client_id=" + googleClientId
	                + "&redirect_uri=http://localhost:80/google_login-callback&response_type=code&scope=email%20profile%20openid&access_type=offline";
	        return new RedirectView(reqUrl);
	    }

	 @RequestMapping("google_login-callback")
	    public RedirectView loginGoogle(@RequestParam(value = "code") String authCode){
	        RestTemplate restTemplate = new RestTemplate();
	        GoogleRequest googleOAuthRequestParam = GoogleRequest
	                .builder()
	                .clientId(googleClientId)
	                .clientSecret(googleClientPw)
	                .code(authCode)
	                .redirectUri("http://localhost:80/google_login-callback")
	                .grantType("authorization_code").build();
	        ResponseEntity<GoogleResponse> resultEntity = restTemplate.postForEntity("https://oauth2.googleapis.com/token",
	                googleOAuthRequestParam, GoogleResponse.class);
	        String jwtToken=resultEntity.getBody().getId_token();
	 		Map<String, String> map=new HashMap<>();
	        map.put("id_token",jwtToken);
	        ResponseEntity<GoogleInfResponse> resultEntity2 = restTemplate.postForEntity("https://oauth2.googleapis.com/tokeninfo",
	                map, GoogleInfResponse.class);
	        String email=resultEntity2.getBody().getEmail();       
	        System.out.println(email);
	        return new RedirectView("/main");
	    }
	
}
