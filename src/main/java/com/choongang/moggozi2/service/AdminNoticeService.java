package com.choongang.moggozi2.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.choongang.moggozi2.entity.AdminNotice;
import com.choongang.moggozi2.repository.AdminNoticeRepository;


@Service
@Transactional
public class AdminNoticeService {
	
	@Autowired
	private AdminNoticeRepository adminNoticeRepository;
	
    @Autowired
    public AdminNoticeService(AdminNoticeRepository adminNoticeRepository) {
        this.adminNoticeRepository = adminNoticeRepository;
    }
    
    public void saveAdminNotice(AdminNotice notice) {
    	adminNoticeRepository.save(notice);//insert,update
    }

    public Page<AdminNotice> findAllNotice(Pageable pageable) {
        try {
            return adminNoticeRepository.findAllByOrderByNotiDateDesc(pageable);
        } catch (Exception e) {
            // 예외 처리 로직 추가
            e.printStackTrace();
            return Page.empty(); // 또는 원하는 대체값을 반환
        }
    }

    public AdminNotice findNoticeById(Integer id) {
        return adminNoticeRepository.findById(id).orElse(null);
    }

	public void incrementNoticeHit(Integer id) {
		
		 Optional<AdminNotice> optionalNotice = adminNoticeRepository.findById(id);
	        if (optionalNotice.isPresent()) {
	            AdminNotice notice = optionalNotice.get();
	            notice.setNotiHit(notice.getNotiHit() + 1); // 조회수 증가
	            adminNoticeRepository.save(notice);
	        } else {
	            // 공지사항이 존재하지 않는 경우 예외 처리
	            throw new RuntimeException("공지사항을 찾을 수 없습니다. ID: " + id);
	        }
		
	}


	



}
