package com.choongang.moggozi2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.choongang.moggozi2.entity.User;
import com.choongang.moggozi2.repository.UserRepository;


@Service
public class UserService {
	
	@Autowired
	private UserRepository userrepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void newuserjoin(User user) {
	    // 이미 등록된 사용자인지 확인
	    boolean isExistingUser = userrepository.existsByUsername(user.getUsername());
	    if (isExistingUser) {
	        System.out.println("중복된 아이디가 있으니 다른 아이디를 사용하세요.");
	        return; // 중복된 아이디가 있으면 처리 중단
	    }

	    // 중복된 아이디가 없으면 새로운 사용자를 저장
	    User newUser = new User();
	    newUser.setUsername(user.getUsername());
	    newUser.setUsernick(user.getUsernick());
	    newUser.setNormalname(user.getNormalname());
	    newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	    newUser.setUseraddress1(user.getUseraddress1());
	    newUser.setUseraddress2(user.getUseraddress2());
	    newUser.setUseremail(user.getUseremail());
	    newUser.setUserph(user.getUserph());
	    newUser.setUserzip(user.getUserzip());
	    newUser.setUseryear(user.getUseryear());
	    newUser.setUsergender(user.getUsergender());
	    newUser.setUsernick(user.getUsernick());
	    newUser.setRole("USER");
    
	    // 새로운 사용자 저장
	    userrepository.save(newUser);
	}
	public void snsuserjoin(User user) {
		 boolean isExistingUser = userrepository.existsByUsername(user.getUsername());
		    if (isExistingUser) {
		        System.out.println("중복된 아이디가 있으니 다른 아이디를 사용하세요.");
		        return; // 중복된 아이디가 있으면 처리 중단
		    }
		
		
		User data = new User();
		data.setUsername(user.getUsername());
		data.setNormalname(user.getNormalname());
		data.setUseremail(user.getUseremail());
		data.setUseryear(user.getUseryear());
		data.setUsergender(user.getUsergender());
		data.setUsernick(user.getUsernick());
		data.setRole("USER");
		
		userrepository.save(data);
	}
}
	
