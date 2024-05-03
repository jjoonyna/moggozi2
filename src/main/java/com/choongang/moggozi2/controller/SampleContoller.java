package com.choongang.moggozi2.controller;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.choongang.moggozi2.entity.UserDTO;
import com.choongang.moggozi2.repository.MokkojiRepository;
import com.choongang.moggozi2.repository.NewUserRepository;
import com.choongang.moggozi2.service.MokkojiService;
import com.choongang.moggozi2.service.UserService;

@Controller
public class SampleContoller {

	@Autowired
	private UserService service;

	@Autowired
	private MokkojiService mokkojiService;
	
	@Autowired
	private NewUserRepository newrepository;
	
	@Autowired
    private MokkojiRepository mokkojiRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/signup")
    public String signupP() {
        return "user/signup";
    }

    @PostMapping("/join")
    public String joinProcess(UserDTO userDTO) {
        System.out.println(userDTO.getUsername());
        service.newuserjoin(userDTO);

        return "redirect:mypage";
    }

    /*
     * 로그인 페이지 
     */
    @GetMapping("/mainlogin")
    public String loginPage() {
    	return "main"; 
    }  
    
    /*
     * 관리자 메인 페이지 
     */
    @GetMapping("/")
    public String adminMainPage(Model model) {
    	
    	String id = SecurityContextHolder.getContext().getAuthentication().getName();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		String role = auth.getAuthority();

    	model.addAttribute("id", id);
    	model.addAttribute("role", role);
    	
    	return "main"; 
    }  
}