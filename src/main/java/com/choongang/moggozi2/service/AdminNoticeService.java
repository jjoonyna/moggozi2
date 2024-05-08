package com.choongang.moggozi2.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.choongang.moggozi2.entity.AdminNotice;
import com.choongang.moggozi2.entity.ReplyNotice;
import com.choongang.moggozi2.repository.AdminNoticeRepository;
import com.choongang.moggozi2.repository.ReplyNoticeRepository;

@Service
@Transactional
public class AdminNoticeService {
	
    private final AdminNoticeRepository adminNoticeRepository;
    private final ReplyNoticeRepository replyNoticeRepository;

    @Autowired
    public AdminNoticeService(AdminNoticeRepository adminNoticeRepository, ReplyNoticeRepository replyNoticeRepository) {
        this.adminNoticeRepository = adminNoticeRepository;
        this.replyNoticeRepository = replyNoticeRepository;
    }
    
    public void saveAdminNotice(AdminNotice notice) {
    	adminNoticeRepository.save(notice);//insert,update
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

	public void saveAdminAsk(AdminNotice notice) {
		adminNoticeRepository.save(notice);
	}

	public Page<AdminNotice> findAllAsk(Pageable pageable) {
		 return adminNoticeRepository.findAll(pageable);
	}

	public Page<AdminNotice> findNoticeByCategory(String category, Pageable pageable) {
		return adminNoticeRepository.findByCategory(category, pageable);
	}

	public ReplyNotice saveAdminReply(ReplyNotice notice) {
		return replyNoticeRepository.save(notice);
	}

	public Page<AdminNotice> findQnaByUsername(String username, Pageable pageable) {
		return adminNoticeRepository.findQnaByUsername(username, pageable);
	}

	public ReplyNotice saveReply(ReplyNotice reply) {
		return replyNoticeRepository.save(reply);
	}

	public Optional<AdminNotice> findNoticeByNo(Integer id) {
		return Optional.ofNullable(adminNoticeRepository.findById(id).orElse(null));
	}

	public List<ReplyNotice> findRepliesByNotiNo(Integer notiNo) {
		return replyNoticeRepository.findByNotiNo(notiNo);
	}




}
