package com.choongang.moggozi2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {


	/*
	 * 메인 페이지 
	 */
    @GetMapping("/admin")
    public String adminPage() {
        return "admin/admin"; 
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
