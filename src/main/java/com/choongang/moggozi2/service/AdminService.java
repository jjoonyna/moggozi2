package com.choongang.moggozi2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.choongang.moggozi2.entity.Admin;
import com.choongang.moggozi2.repository.UserRepository;


@Service
public class AdminService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public void joinProcess(Admin admin) {
	    // db에 이미 동일한 userid를 가진 회원이 존재하는지 중복 검사
		boolean isUser = userRepository.existsByUsername(admin.getUsername());
	    if (isUser) {
	        System.out.println("중복 아이디가 존재합니다.");
	    	return;
	    }
	    
	    Admin data = new Admin();
	    data.setUsername(admin.getUsername());
	    data.setUsernick(admin.getUsernick());
	    data.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
	    data.setRole("ADMIN");
	    
	    userRepository.save(data);
	}

}
