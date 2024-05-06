package com.choongang.moggozi2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choongang.moggozi2.entity.AdminNotice;
import com.choongang.moggozi2.entity.ReplyNotice;


public interface ReplyNoticeRepository extends JpaRepository<ReplyNotice, Long> {


	List<ReplyNotice> findByNotiNo(AdminNotice notiNo);
	List<ReplyNotice> findByNotiNoNotiNo(Integer notiNo);
	 
}
