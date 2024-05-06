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
		boolean newUser = userrepository.existsByUsername(user.getUsername());
		if (newUser) {
			System.out.println("중복된 아이디가 있으니 다른아이디를...");
		return;
		}

		
		User data = new User();
		data.setUsername(user.getUsername());
		data.setNormalname(user.getNormalname());
		data.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		data.setUseraddress1(user.getUseraddress1());
		data.setUseraddress2(user.getUseraddress2());
		data.setUseremail(user.getUseremail());
		data.setUserph(user.getUserph());
		data.setUserzip(user.getUserzip());
		data.setUseryear(user.getUseryear());
		data.setUsergender(user.getUsergender());
		data.setUsernick(user.getUsernick());
		data.setRole("USER");
		
		userrepository.save(data);
		
		
	}
	
	
	public void snsuserjoin(User user) {
		boolean newUser = userrepository.existsByUsername(user.getUsername());
		if (newUser) {
			System.out.println("중복된 아이디가 있으니 다른아이디를...");
			return;
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
	
