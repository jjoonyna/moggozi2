package com.choongang.moggozi2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
	public ModelAndView loginCallback(@RequestParam("code") String code,Model model,Authentication authentication) {
	   return kakaoService.loginCallback(code,model,authentication);
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
