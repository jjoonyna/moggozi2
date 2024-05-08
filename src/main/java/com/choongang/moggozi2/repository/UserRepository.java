package com.choongang.moggozi2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.choongang.moggozi2.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
    boolean existsByUsername(String username);

    User findByUsername(String username);
    
    @Query(value = "select * from user where username = :username", nativeQuery = true)
    public List<User> findByMypw(@Param("username") String username);

    @Query(value = "select * from user where userph = :userph", nativeQuery = true)
    public List<User> findByMyiid(@Param("userph") String userph);
    
    User findByUsernick(String usernick);

}
