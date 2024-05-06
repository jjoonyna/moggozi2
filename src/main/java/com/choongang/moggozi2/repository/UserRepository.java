package com.choongang.moggozi2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choongang.moggozi2.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
	boolean existsByUsername(String username);

	User findByUsername(String username);
	
}
