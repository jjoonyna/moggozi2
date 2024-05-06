package com.choongang.moggozi2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.choongang.moggozi2.entity.CustomUserDetails;
import com.choongang.moggozi2.entity.User;
import com.choongang.moggozi2.repository.UserRepository;



@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User userData = userRepository.findByUsername(username);
		
		if(userData != null) {
			
			return new CustomUserDetails(userData);
			
		}
		
		
		return null;
	}


	
	

}
