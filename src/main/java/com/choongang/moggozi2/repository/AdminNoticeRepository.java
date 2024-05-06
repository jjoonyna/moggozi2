package com.choongang.moggozi2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.choongang.moggozi2.entity.AdminNotice;


public interface AdminNoticeRepository extends JpaRepository<AdminNotice, Integer> {
		List<AdminNotice> findByCategory(String category);
	    Page<AdminNotice> findByCategory(String category, Pageable pageable);
	    Page<AdminNotice> findQnaByUsername(String username, Pageable pageable);
	    
	    
	    
	    @Query("SELECT an FROM AdminNotice an WHERE an.notiNo = :notiNo")
	    Optional<AdminNotice> findAdminNoticeWithRepliesByNotiNo(@Param("notiNo") Long notiNo);

	   

}
