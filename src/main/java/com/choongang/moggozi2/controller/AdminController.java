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

import com.choongang.moggozi2.entity.Admin;
import com.choongang.moggozi2.repository.MokkojiRepository;
import com.choongang.moggozi2.service.AdminService;
import com.choongang.moggozi2.service.MokkojiService;


@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminservice;

	@Autowired
	private MokkojiService mokkojiService;
	
    @Autowired
    private MokkojiRepository mokkojiRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    /*
     * 로그인 페이지 
     */
    @GetMapping("/login")
    public String loginPage() {
    	return "admin/loginAdmin"; 
    }  

    /*
     * 관리자 메인 페이지 
     */
    @GetMapping("/mainpage")
    public String adminMainPage(Model model) {
    	
    	String id = SecurityContextHolder.getContext().getAuthentication().getName();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		String role = auth.getAuthority();

        // mokkoji_no 필드의 수를 카운트하여 모델에 추가
        int mokkojiCount = mokkojiRepository.countMokkojiNo();
        
        model.addAttribute("mokkojiCount", mokkojiCount);
    	model.addAttribute("id", id);
    	model.addAttribute("role", role);
    	
    	return "admin/mainAdmin"; 
    }  

    @GetMapping("/join")
    public String joinP() {

        return "join";
    }


    @PostMapping("/joinProc")
    public String joinProcess(Admin admin) {

        System.out.println(admin.getUsername());

        adminservice.joinProcess(admin);


        return "redirect:/login";
    }

    /*
     * 404 페이지 
     */
    @GetMapping("/404")
    public String errorPage() {
    	return "admin/404"; 
    }
    /*
     * forgot-password 페이지 
     */
    @GetMapping("/forgot-password")
    public String forgotPasswordPage() {
    	return "admin/forgot-password"; 
    }
    
    /*
     * 
     * 공지사항 페이지 
     * 
     * 공지사항 CRUD
     * 
     */
    @GetMapping("/notice")
    public String noticePage() {
    	return "admin/notice"; 
    }
    
    /*
     * 
     * 1:1문의 페이지 
     * 
     * list 
     * 답변 작성
     * 문의 상태(미답변/답변중/보류/종료)
     * 
     */
    @GetMapping("/userService")
    public String userServicePage() {
    	return "admin/userService"; 
    }
    
    /*
     * 
     * 회원관리 페이지 
     * 
     * list
     * 회원 상태(일반/탈퇴/휴면/차단)
     * 
     */
    @GetMapping("/userManagement")
    public String userManagementPage() {
    	return "admin/userManagement"; 
    }
    
    /*
     * 게시물관리 페이지 
     */
    @GetMapping("/postManagement")
    public String postManagementPage() {
    	return "admin/postManagement"; 
    }
}
