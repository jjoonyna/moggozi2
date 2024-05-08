package com.choongang.moggozi2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.choongang.moggozi2.entity.AdminNotice;
import com.choongang.moggozi2.entity.ReplyNotice;

public interface ReplyNoticeRepository extends JpaRepository<ReplyNotice, Long> {


	List<ReplyNotice> findByNotiNo(Integer notiNo);
	 
}
