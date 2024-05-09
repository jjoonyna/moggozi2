package com.choongang.moggozi2.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.choongang.moggozi2.entity.User;
import com.choongang.moggozi2.entity.AdminNotice;
import com.choongang.moggozi2.entity.CustomUserDetails;
import com.choongang.moggozi2.entity.ReplyNotice;
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
	    public String noticeWritePage(Model model, Authentication auth) {
		 
		 
		 String username = null;
	     String usernick = null; // 사용자 닉네임 변수 추가
	        
	        if(auth != null) {
	            username = auth.getName();
	            // 사용자의 닉네임 가져오기
	            if (auth.getPrincipal() instanceof UserDetails) {
	                usernick = ((CustomUserDetails) auth.getPrincipal()).getUsernick();
	            }
	        }
	
	        // 뷰로 사용자 이름 전달
	        model.addAttribute("username", username);
	        model.addAttribute("usernick", usernick);
	 
		    
	        return "admin/noticeWrite";
	    }

	 	/*
	 	 * 공지사항 작성 
	 	 */
	 	@PostMapping("/noticeWriteResult")
		 public String noticeWriteResult(@ModelAttribute AdminNotice notice) {
	
		 
		     
		     // 현재 시간을 가져옵니다.
	 		 Timestamp now = new Timestamp(System.currentTimeMillis());
	 		 
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
	 	public String adminNoticeList(@RequestParam(defaultValue = "0") int page, Authentication auth, Model model) {
	 	    
	 	    
	 	    
	 	    if(auth != null) {
	 	        String username = auth.getName();
	 	        String role = auth.getAuthorities().stream().findFirst().orElse(null).getAuthority();
	 	        
	 	        // usernick 가져오기
	 	        if (auth.getPrincipal() instanceof UserDetails) {
	 	            String usernick = ((UserDetails) auth.getPrincipal()).getUsername();
	            // 관리자 페이지로 이동
	            model.addAttribute("username", username);
	            model.addAttribute("role", role);
	            model.addAttribute("usernick", usernick);

	            
	 	    System.out.println("넘어왔다!!!!!1" + username);
	 	    System.out.println(usernick);
	 	    System.out.println(role);
	 	    
	 	    // 페이지 번호와 페이지 크기를 기반으로 페이징 객체를 생성합니다.
	 	    Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "notiDate"));

	 	     // 페이징 처리된 카테고리별 공지사항을 가져옵니다.
	 	     Page<AdminNotice> noticePage = adminNoticeService.findNoticeByCategory("n", pageable);

	 	    // 페이징된 데이터 리스트를 모델에 추가합니다.
	 	    List<AdminNotice> noticeList = noticePage.getContent();

	 	    // noti_impt 컬럼 값이 'Y'인 경우에 대해서 우선적으로 리스트에 추가합니다.
	 	    List<AdminNotice> importantNotices = new ArrayList<>();
	 	    List<AdminNotice> normalNotices = new ArrayList<>();
	 	    for (AdminNotice notice : noticeList) { 
	 	        
	 	        if ("중요".equals(notice.getNotiImpt()) && "n".equals(notice.getCategory())) {
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
	 	    }
	 	    return "redirect:/main";
	 	}

	 	
	 	/*
	 	 * 공지사항 상세보기 
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
	 	
	 	
	 	//////////////////////////////////////////////////////////////////
	 	//////////////////////////////////////////////////////////////////
	 	//////////////////////////////////////////////////////////////////
	 	//////////////////////////////////////////////////////////////////
	 	//////////////////////////////////////////////////////////////////
	 	//////////////////////////////////////////////////////////////////
	 	
	 	
	 
		 	 /*
		 	  * 1:1문의 리스트 불러오기
		 	  */
		 	 @GetMapping("/askList")
		     public String adminAskList(Authentication auth, @RequestParam(defaultValue = "0") int page, Model model) {

			 	    String username = null;
			 	    String usernick = null;
			 	    String role = null;
			 	    
			 	    if(auth != null) {
			 	        username = auth.getName();
			 	        role = auth.getAuthorities().stream().findFirst().orElse(null).getAuthority();
			 	        
			 	        // usernick 가져오기
			 	        if (auth.getPrincipal() instanceof UserDetails) {
			 	            usernick = ((UserDetails) auth.getPrincipal()).getUsername();

			 	    // 뷰로 사용자 이름과 usernick 전달
			 	    model.addAttribute("username", username);
			 	    model.addAttribute("usernick", usernick); // usernick 추가
			 	    model.addAttribute("role", role);

		         // 페이지 번호와 페이지 크기를 기반으로 페이징 객체를 생성합니다.
		         Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "notiDate"));

		         // 페이징 처리된 카테고리별 공지사항을 가져옵니다.
		         Page<AdminNotice> noticePage = adminNoticeService.findNoticeByCategory("a", pageable);

		         // 페이징된 데이터 리스트를 모델에 추가합니다.
		         List<AdminNotice> askList = noticePage.getContent();

		         // 모델에 리스트를 추가합니다.
		         model.addAttribute("askList", askList);

		         // 페이징 정보도 모델에 추가할 수 있습니다.
		         model.addAttribute("currentPage", page);
		         model.addAttribute("totalPages", noticePage.getTotalPages());

		         // admin/noticelist 템플릿으로 이동합니다.
		         return "admin/askList";
			 	        }
			 	    }
			 	 return "redirect:/main";
		     }
		 	 
		 	/*
		 	 * 1:1문의 상세보기 
		 	 */
		 	@GetMapping("/askDetail")
		 	public String askDetail(Authentication auth, @RequestParam("id") Integer id, Model model) {
		 	    // id를 사용하여 공지사항을 조회합니다.
		 	    Optional<AdminNotice> noticeOptional = adminNoticeService.findNoticeByNo(id);
		 	    
		 	    // Optional이 값을 갖고 있는지 확인합니다.
		 	    if (noticeOptional.isPresent()) {
		 	        AdminNotice askList = noticeOptional.get();
		 	        
		 	        // 조회된 공지사항을 모델에 추가합니다.
		 	        model.addAttribute("id", id);
		 	        model.addAttribute("notice", askList);
		 	    }
		 	   String username = null;
		 	    String usernick = null;
		 	    String role = null;
		 	    
		 	    if(auth != null) {
		 	        username = auth.getName();
		 	        role = auth.getAuthorities().stream().findFirst().orElse(null).getAuthority();
		 	        
		 	        // usernick 가져오기
		 	        if (auth.getPrincipal() instanceof UserDetails) {
		 	            usernick = ((UserDetails) auth.getPrincipal()).getUsername();

		 	    // 뷰로 사용자 이름과 usernick 전달
		 	    model.addAttribute("username", username);
		 	    model.addAttribute("usernick", usernick); // usernick 추가
		 	    model.addAttribute("role", role);
		 	    
		 	    
		 	    System.out.println("아이디" + username);
		 	    System.out.println("닉네임" + usernick);
		 	    System.out.println(role);
		 	    System.out.println(id);
		 	    
		 	    // noticeDetail 템플릿으로 이동합니다.
		 	    return "admin/askDetail";}}
		 	    return "redirect:/main";
		 	}
		 	
		 	////////////////////////////////////////////
		 	////////////////////////////////////////////
		 	////////////////////////////////////////////
		 	////////////////////////////////////////////
		 	////////////////////////////////////////////
		 	////////////////////////////////////////////
		 	////////////////////////////////////////////

		 	 /*
			  * 1:1문의 답변 작성 폼
			  */
			 	@GetMapping("/replyWrite")
			 	public String replyWritePage(Authentication auth, @RequestParam("id") Integer id, Model model) {
			 	    
			 	    String username = null;
			 	    String usernick = null;
			 	    String role = null;
			 	    
			 	    if (auth != null) {
			 	        username = auth.getName();
			 	        role = auth.getAuthorities().stream().findFirst().orElse(null).getAuthority();
			 	        
			 	        // usernick 가져오기
			 	        if (auth.getPrincipal() instanceof CustomUserDetails) {
			 	            usernick = ((CustomUserDetails) auth.getPrincipal()).getUsernick();
			 	        }
			 	        
			 	        // 뷰로 사용자 이름과 usernick 전달
			 	        model.addAttribute("username", username);
			 	        model.addAttribute("usernick", usernick); // usernick 추가
			 	        model.addAttribute("role", role);
			 	        model.addAttribute("id", id);
			 	        
			 	        System.out.println("넘어왔다!!!!!2" + username);
			 	        System.out.println("닉네임" + usernick);
			 	        System.out.println(role);
			 	        System.out.println(id);
			 	        
			 	        return "admin/replyWrite";
			 	    } else {
			 	        return "redirect:/main";
			 	    }
			 	}

			 	/*
			 	 * 1:1문의 답변 작성 
			 	 */
				 @PostMapping("/replyWriteResult")
				    public String askWriteResult(@RequestParam("notiNo") Integer id, @ModelAttribute ReplyNotice reply) {
						// 현재 시간을 가져옵니다.
					 	Timestamp now = new Timestamp(System.currentTimeMillis());
						 
						// 엔티티 객체에 현재 시간을 할당합니다.
						reply.setReplyDate(now);
						
				        // 답변을 저장
				        ReplyNotice savedReply = adminNoticeService.saveReply(reply);
				        
				        // 관련된 문의 가져오기
				        AdminNotice notice = adminNoticeService.findNoticeById(id);

				        System.out.println("savedReply : " + savedReply);
				
				        
				        // 문의와 답변의 번호를 대조하여 일치하는 경우, 문의의 상태를 "답변완료"로 변경
				        if (notice != null && savedReply.getNotiNo().equals(notice.getNotiNo())) {
				        	notice.setNotiAt("답변완료");
				            adminNoticeService.saveAdminAsk(notice);
				        }
				        
				        // 작성 성공 페이지로 리다이렉트
				        return "redirect:/askList";
				    }
		 	 

}
