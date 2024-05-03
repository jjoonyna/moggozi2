package com.choongang.moggozi2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.choongang.moggozi2.entity.AdminNotice;

public interface AdminNoticeRepository extends JpaRepository<AdminNotice, Integer> {
    Page<AdminNotice> findAllByOrderByNotiDateDesc(Pageable pageable);
}
