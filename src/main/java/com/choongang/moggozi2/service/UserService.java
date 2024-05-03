package com.choongang.moggozi2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.choongang.moggozi2.entity.UserDTO;
import com.choongang.moggozi2.repository.NewUserRepository;

@Service
public class UserService {
	
	@Autowired
	private NewUserRepository newrepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void newuserjoin(UserDTO userDTO) {
		boolean newUser = newrepository.existsByUsername(userDTO.getUsername());
		if (newUser) {
			System.out.println("중복된 아이디가 있으니 다른아이디를...");
		return;
		}

		
		UserDTO data = new UserDTO();
		data.setUsername(userDTO.getUsername());
		data.setNormalname(userDTO.getNormalname());
		data.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
		data.setUseraddress1(userDTO.getUseraddress1());
		data.setUseraddress2(userDTO.getUseraddress2());
		data.setUseremail(userDTO.getUseremail());
		data.setUserph(userDTO.getUserph());
		data.setUserzip(userDTO.getUserzip());
		data.setUseryear(userDTO.getUseryear());
		data.setUsergender(userDTO.getUsergender());
		data.setUsernick(userDTO.getUsernick());
		data.setRole("USER");
		
		newrepository.save(data);
		
		
	}
	public void snsuserjoin(UserDTO userDTO) {
		boolean newUser = newrepository.existsByUsername(userDTO.getUsername());
		if (newUser) {
			System.out.println("중복된 아이디가 있으니 다른아이디를...");
			return;
		}
		
		
		UserDTO data = new UserDTO();
		data.setUsername(userDTO.getUsername());
		data.setUsernick(userDTO.getUsernick());
		data.setUsergender(userDTO.getUsergender());
		data.setNormalname(userDTO.getNormalname());
		data.setRole("USER");
		
		newrepository.save(data);
		
	}
	
}
	
