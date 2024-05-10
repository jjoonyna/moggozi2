package com.choongang.moggozi2.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.choongang.moggozi2.entity.CustomUserDetails;



@Controller
public class VideoController {

	   @GetMapping("/video")
	    public String video(@RequestParam("mokkoji") String mokkojiNo,
	    					@RequestParam("roomname") String mokkojiTitle,
	    					Authentication auth,
	                        Model model) {
		   String username = null;
	 	    String usernick = null;
	 	    String role = null;
	 	    
	 	    if (auth != null) {
	 	        username = auth.getName();
	 	        role = auth.getAuthorities().stream().findFirst().orElse(null).getAuthority();
	 	        
	 	        // usernick 가져오기
	 	        if (auth.getPrincipal() instanceof CustomUserDetails) {
	 	            usernick = ((CustomUserDetails) auth.getPrincipal()).getUsernick();
	 	        }}
		   
		   
	        // 받아온 값들을 Model에 추가하여 View로 전달합니다.
		   model.addAttribute("mokkojiNo", mokkojiNo);
	        model.addAttribute("mokkojiTitle", mokkojiTitle);
	        model.addAttribute("usernick", usernick);

	        // 반환하는 문자열은 사용할 View의 이름을 나타냅니다.
	        return "video";
	    }
//	@GetMapping("video")
//	public String video() {
//		return "video";
//	}
	
	
}

