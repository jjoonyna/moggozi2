package com.choongang.moggozi2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.choongang.moggozi2.entity.Mokkoji;


public interface MokkojiRepository extends JpaRepository<Mokkoji, Integer> {
	
    // 특정 필드(mokkojiNo)의 수를 카운트하는 쿼리 정의
    @Query("SELECT COUNT(mokkoji_no) FROM Mokkoji")
    int countMokkojiNo();
	
	
	
 
}