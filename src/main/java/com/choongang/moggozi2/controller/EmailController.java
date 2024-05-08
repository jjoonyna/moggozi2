package com.choongang.moggozi2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.choongang.moggozi2.entity.User;
import com.choongang.moggozi2.repository.UserRepository;
import com.choongang.moggozi2.service.EmailService;

@Controller
public class EmailController {
	
	@Autowired
	
	private EmailService emailService;
	
	@Autowired
	private UserRepository userrepository;
	
	
	 @GetMapping("/sendUserByEmail")
	    public String sendUserByEmail(Model model, 
	    									@RequestParam("useremail") String useremail,
	    									@RequestParam("username") String username) {
	        
		 	System.out.println("메일보내기전 확인 :"+username);
		 	System.out.println("메일보내기전 확인 :"+useremail);
		 
		 
	        // 비밀번호 생성 및 메일 전송
	        emailService.sendMyIdByEmail(useremail,username);

	        // 전송 결과 메시지를 모델에 추가하여 뷰에 전달
	        model.addAttribute("message", "아이디가 이메일로 전송되었습니다.");

	        return "user/find_ok_result"; // 임시 비밀번호 전송 완료 페이지로 이동
	    }
	 
	 @GetMapping("/sendPasswordByEmail")
	 public String sendPasswordByEmail(Model model, 
			 							@RequestParam("newPassword") String newPassword,
			 							@RequestParam("useremail") String useremail
			 							) {
		
		 // 비밀번호 생성 및 메일 전송
		 emailService.sendSimpleEmail(useremail,newPassword);
		 
		 // 전송 결과 메시지를 모델에 추가하여 뷰에 전달
		 model.addAttribute("message", "임시 비밀번호가 이메일로 전송되었습니다.");
		 
		 return "user/find_ok_result"; // 임시 비밀번호 전송 완료 페이지로 이동
	 }
	
}