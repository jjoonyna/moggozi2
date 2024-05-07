package com.choongang.moggozi2.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.choongang.moggozi2.entity.Mokkoji;

@Repository
public interface MokkojiRepository extends JpaRepository<Mokkoji, Integer> {

    // 제목과 카테고리를 기준으로 모꼬지를 검색하여 페이징 처리된 결과를 반환합니다.
    Page<Mokkoji> findByMokkojiTitleContainingAndMokkojiCategory(String keyword, String category, Pageable pageable);

    // 제목과 카테고리를 기준으로 모꼬지를 검색하여 리스트 형태로 반환합니다.
    List<Mokkoji> findByMokkojiTitleContainingAndMokkojiCategory(String keyword, String category);

    // 제목을 기준으로 모꼬지를 검색하여 페이징 처리된 결과를 반환합니다.
    Page<Mokkoji> findByMokkojiTitleContaining(String keyword, Pageable pageable);

    // 제목을 기준으로 모꼬지를 검색하여 리스트 형태로 반환합니다.
    List<Mokkoji> findByMokkojiTitleContaining(String keyword);

    // 제목과 카테고리를 기준으로 검색된 모꼬지의 수를 반환합니다.
    int countByMokkojiTitleContainingAndMokkojiCategory(String keyword, String category);

    // 제목을 기준으로 검색된 모꼬지의 수를 반환합니다.
    int countByMokkojiTitleContaining(String keyword);
    
    // 특정 필드(mokkojiNo)의 수를 카운트하는 쿼리 정의
    @Query("SELECT COUNT(mokkoji_no) FROM Mokkoji")
    int countMokkojiNo();

	
    
}