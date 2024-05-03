package com.choongang.moggozi2.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.choongang.moggozi2.entity.AdminNotice;
import com.choongang.moggozi2.service.AdminNoticeService;


@Controller
public class AdminNoticeController {
	
	@Autowired
    private final AdminNoticeService adminNoticeService;
	
	 @Autowired
	    public AdminNoticeController(AdminNoticeService adminNoticeService) {
	        this.adminNoticeService = adminNoticeService;
	    }
	 
	 /*
	  * 공지사항 작성 폼
	  */
	 @GetMapping("/noticeWrite")
	    public String noticeWritePage(Model model) {
		 
		 
		    // 현재 사용자의 인증 정보 가져오기
		    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    String username = authentication.getName(); // 현재 사용자의 아이디
		    
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			Iterator<? extends GrantedAuthority> iter = authorities.iterator();
			GrantedAuthority auth = iter.next();
			String role = auth.getAuthority();		 
			
		    // 뷰로 사용자 이름 전달
			model.addAttribute("username", username);
		    model.addAttribute("role", role);
		    
		    
	        return "admin/noticeWrite";
	    }

	 	/*
	 	 * 공지사항 작성 
	 	 */
	 	@PostMapping("/noticeWriteResult")
		 public String noticeWriteResult(@RequestParam("category") String category,
		                                 @RequestParam("notiWriter") String notiWriter,
		                                 @RequestParam("notiTitle") String notiTitle,
		                                 @RequestParam("notiImpt") String notiImpt,
		                                 @RequestParam("notiContent") String notiContent,
		                                 @RequestParam("notiHit") Integer notiHit) {
	
		     // 공지사항 엔티티 객체 생성
		     AdminNotice notice = new AdminNotice();
		     notice.setCategory(category);
		     notice.setNotiWriter(notiWriter);
		     notice.setNotiTitle(notiTitle);
		     notice.setNotiImpt(notiImpt);
		     notice.setNotiContent(notiContent);
		     notice.setNotiHit(notiHit);
		     
		     // 현재 시간을 가져옵니다.
		     LocalDateTime now = LocalDateTime.now();
	
		     // 엔티티 객체에 현재 시간을 할당합니다.
		     notice.setNotiDate(now);
	
		     // 공지사항 저장
		     adminNoticeService.saveAdminNotice(notice);
	
		     // 작성 성공 페이지로 리다이렉트
		     return "redirect:/noticeList";
		 }

	    /*
	     * 공지사항 리스트
	     */
	 	@GetMapping("/noticeList")
	 	public String adminNoticeList(@RequestParam(defaultValue = "0") int page, Model model) {
	 	    // 현재 사용자의 인증 정보 가져오기
	 	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	 	    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	 	    Iterator<? extends GrantedAuthority> iter = authorities.iterator();
	 	    GrantedAuthority auth = iter.next();
	 	    String role = auth.getAuthority();

	 	    String id = SecurityContextHolder.getContext().getAuthentication().getName();
	 	    // 뷰로 사용자 이름 전달
	 	    model.addAttribute("role", role);
			model.addAttribute("id", id);

	 	    // 페이지 번호와 페이지 크기를 기반으로 페이징 객체를 생성합니다.
	 	    Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "notiDate"));

	 	    // 페이징 처리된 카테고리별 공지사항을 가져옵니다.
	 	    Page<AdminNotice> noticePage = adminNoticeService.findAllNotice(pageable);

	 	    // 페이징된 데이터 리스트를 모델에 추가합니다.
	 	    List<AdminNotice> noticeList = noticePage.getContent();

	 	    // noti_impt 컬럼 값이 'Y'인 경우에 대해서 우선적으로 리스트에 추가합니다.
	 	    List<AdminNotice> importantNotices = new ArrayList<>();
	 	    List<AdminNotice> normalNotices = new ArrayList<>();
	 	    for (AdminNotice notice : noticeList) {
	 	        if ("중요".equals(notice.getNotiImpt())) {
	 	            importantNotices.add(notice);
	 	        } else {
	 	            normalNotices.add(notice);
	 	        }
	 	    }
	 	    importantNotices.addAll(normalNotices);

	 	    // 모델에 리스트를 추가합니다.
	 	    model.addAttribute("noticeList", importantNotices);

	 	    // 페이징 정보도 모델에 추가할 수 있습니다.
	 	    model.addAttribute("currentPage", page);
	 	    model.addAttribute("totalPages", noticePage.getTotalPages());

	 	    // admin/noticelist 템플릿으로 이동합니다.
	 	    return "admin/noticeList";
	 	}
	 	
	 	/*
	 	 * 상세보기 
	 	 */
	 	@GetMapping("/noticeDetail")
	 	public String noticeDetail(@RequestParam Integer id, Model model) {
	 	    // id를 사용하여 공지사항을 조회합니다.
	 	    AdminNotice notice = adminNoticeService.findNoticeById(id);
	 	    
	 	   // 조회수 증가
	 	    adminNoticeService.incrementNoticeHit(id);
	 	    
	 	    // 조회된 공지사항을 모델에 추가합니다.
	 	    model.addAttribute("notice", notice);
	 	    
	 	    // noticeDetail 템플릿으로 이동합니다.
	 	    return "admin/noticeDetail";
	 	}


	 	

}
