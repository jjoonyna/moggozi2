package com.choongang.moggozi2.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.choongang.moggozi2.entity.CustomUserDetails;
import com.choongang.moggozi2.service.KakaoService;


@RestController
public class KakaoContoller {

	@Autowired
	public KakaoService kakaoService;

	@RequestMapping("kakao_login")
    public RedirectView goKakaoOAuth() {
       return kakaoService.goKakaoOAuth();
    }

	@RequestMapping("/kakao_login-callback")
	public RedirectView loginCallback(@RequestParam("code") String code, Model model,Authentication authentication) {
		
		 String username = null;
	        String usernick = null;
	        String role = null;

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
	        
	    	model.addAttribute("username", username);
            model.addAttribute("role", role);
            model.addAttribute("usernick", usernick);
	   return kakaoService.loginCallback(code);
	}	
	
	
	@RequestMapping("/kakao_logout")
    public String logout() {
       return kakaoService.logout();
    }	
	
	
	
	@RequestMapping("/kakao_profile")
    public String getProfile() {
       return kakaoService.getProfile();
    }	
	
	@RequestMapping("/kakao_authorize")
    public RedirectView goKakaoOAuth(@RequestParam("scope") String scope) {
		return kakaoService.goKakaoOAuth(scope);
    }	
	
	@RequestMapping("/kakao_friends")
    public String getFriends() {
       return kakaoService.getFriends();
    }	
	
	@RequestMapping("/kakao_message")
    public String message() {
       return kakaoService.message();
    }		
	
	@RequestMapping("/kakao_friends-message")
    public String friends_message(@RequestParam("uuids") String uuids) {
       return kakaoService.friendMessage(uuids);
    }	
	

}
