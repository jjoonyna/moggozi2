package com.choongang.moggozi2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choongang.moggozi2.entity.UserDTO;


public interface NewUserRepository extends JpaRepository<UserDTO, String>{
	boolean existsByUsername(String username);

	UserDTO findByUsername(String username);
	
}
