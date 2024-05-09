package com.choongang.moggozi2.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.choongang.moggozi2.common.GoogleInfResponse;
import com.choongang.moggozi2.common.GoogleRequest;
import com.choongang.moggozi2.common.GoogleResponse;
import com.choongang.moggozi2.entity.CustomUserDetails;
import com.choongang.moggozi2.entity.User;
import com.choongang.moggozi2.repository.UserRepository;
import com.choongang.moggozi2.service.UserService;


@RestController
@CrossOrigin("*")
public class GoogleController {
	
	@Autowired
	private UserRepository newrepository;
	
	@Autowired
	private UserService service;

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
	    public ModelAndView loginGoogle(@RequestParam(value = "code") String authCode,Model model,Authentication authentication){
	       
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
	        
		 String username=resultEntity2.getBody().getEmail();   
		 String usernick=resultEntity2.getBody().getName();
		 User user = new User();
		 String role = "";
			
		 boolean newUser = newrepository.existsByUsername(username);
			
		 if(!newUser) {
			 user.setUsername(username);
			 user.setUsernick(usernick);
			 service.snsuserjoin(user);
		 }
		 
			
		 if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
			 username = authentication.getName();
			 Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			 if (!authorities.isEmpty()) {
				 GrantedAuthority auth = authorities.iterator().next();
				 role = auth.getAuthority();
			 }

	            // 사용자의 닉네임 가져오기
			 if (authentication.getPrincipal() instanceof UserDetails) {
				 usernick = ((CustomUserDetails) authentication.getPrincipal()).getUsernick();
			 }
		 }
			
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("/main");
			modelAndView.addObject("username", username);
			modelAndView.addObject("role", role);
			modelAndView.addObject("usernick", usernick);
			
			
			return modelAndView;
			
	    }
	
}
