package com.choongang.moggozi2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.choongang.moggozi2.repository.UserRepository;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private UserRepository userrepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	

    // 메일로 임시 비밀번호 전송
    public void sendSimpleEmail(String useremail, String newPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(useremail);
        message.setSubject("[모꼬지] 임시 비밀번호입니다.");
        message.setText("유저의 임시 비밀번호는 " + newPassword + "입니다. 로그인 후 반드시 비밀번호를 변경해주세요.");
        mailSender.send(message);
    }

    // 메일로 아이디 전송
	public void sendMyIdByEmail(String useremail, String username) {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(useremail);
        message.setSubject("[모꼬지] 아이디입니다.");
        message.setText("유저의 아이디는 " + username + "입니다.");
        mailSender.send(message);
	}
}

