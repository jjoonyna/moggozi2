package com.choongang.moggozi2.controller;

<<<<<<< HEAD
import org.springframework.security.core.Authentication;
=======
import java.util.Collection;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
>>>>>>> 0c1804ff3d8d21542b39735c7f77b359d490ee8f
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
	    					Authentication authentication,
	                        Model model) {
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

