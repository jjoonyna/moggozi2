package com.choongang.moggozi2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choongang.moggozi2.entity.Admin;


public interface UserRepository extends JpaRepository<Admin, String> {

	boolean existsByUsername(String username);

	Admin findByUsername(String username);
	
	

	
	
}
