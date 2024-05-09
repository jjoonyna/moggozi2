package com.choongang.moggozi2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.choongang.moggozi2.entity.CustomUserDetails;
import com.choongang.moggozi2.entity.User;
import com.choongang.moggozi2.repository.UserRepository;

import java.util.Date; // Date import 추가

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    User userData = userRepository.findByUsername(username);

	    if (userData != null) {
	        // usernick과 로그인 시간을 함께 가져와서 UserDetails에 추가
	        String usernick = userData.getUsernick();
	        Date loginTime = new Date(); // 현재 시간을 가져옴
	        return new CustomUserDetails(userData, usernick, loginTime);
	    }

	    throw new UsernameNotFoundException("User not found with username: " + username);
	}
}
