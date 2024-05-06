package com.choongang.moggozi2.controller;

import java.sql.Timestamp;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.choongang.moggozi2.entity.AdminNotice;
import com.choongang.moggozi2.entity.ReplyNotice;
import com.choongang.moggozi2.entity.User;
import com.choongang.moggozi2.repository.MokkojiRepository;
import com.choongang.moggozi2.repository.UserRepository;
import com.choongang.moggozi2.service.AdminNoticeService;
import com.choongang.moggozi2.service.MokkojiService;
import com.choongang.moggozi2.service.UserService;



@Controller
public class UserContoller {

	@Autowired
	private UserService service;

	@Autowired
	private MokkojiService mokkojiService;
	
	@Autowired
    private AdminNoticeService adminNoticeService;
	
	@Autowired
	private UserRepository userrepository;
	
	@Autowired
    private MokkojiRepository mokkojiRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/signup")
    public String signupP() {
        return "user/signup";
    }

    @PostMapping("/joinProc")
    public String joinProcess(User user) {
        System.out.println(user.getUsername());
        service.newuserjoin(user);

        return "redirect:mypage";
    }

    /*
     * 로그인 페이지 
     */
    @GetMapping("/")
    public String loginPage() {
    	return "main"; 
    }  
    
    /*
     * 로그인 페이지 
     */
    @GetMapping("/main")
    public String adminMainPage(Model model) {
    	
    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		String role = auth.getAuthority();
		
		
		System.out.println("username = " + username);
		System.out.println("role = " + role);

    	model.addAttribute("username", username);
    	model.addAttribute("role", role);
    	
    	if(role.equals("ADMIN")) {
    		return "admin/mainAdmin";
    	}
    	return "main"; 
    }  

    @GetMapping("/mypage")
    public String mypageP(Model model) {
    	
    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		String role = auth.getAuthority();
		
		
		System.out.println("username = " + username);
		System.out.println("role = " + role);
    	
//    	if(auth != null) {
//    		String username = auth.getName();
//    		String role = auth.getAuthorities();
//    	}
		
	    // 뷰로 사용자 이름 전달
		model.addAttribute("username", username);
	    model.addAttribute("role", role);
    	
    	
    	
        return "user/mypage";
    }
    @GetMapping("/myboard")
    public String myboard(Model model) {
    	
    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    	Iterator<? extends GrantedAuthority> iter = authorities.iterator();
    	GrantedAuthority auth = iter.next();
    	String role = auth.getAuthority();
    	
    	
    	System.out.println("username = " + username);
    	System.out.println("role = " + role);
    	

    	// 뷰로 사용자 이름 전달
    	model.addAttribute("username", username);
    	model.addAttribute("role", role);
    	
    	
    	
    	return "user/myboard";
    }
    @GetMapping("/mymoim")
    public String mymoim(Model model) {
    	
    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    	Iterator<? extends GrantedAuthority> iter = authorities.iterator();
    	GrantedAuthority auth = iter.next();
    	String role = auth.getAuthority();
    	
    	
    	System.out.println("username = " + username);
    	System.out.println("role = " + role);
    	

    	
    	// 뷰로 사용자 이름 전달
    	model.addAttribute("username", username);
    	model.addAttribute("role", role);
    	
    	
    	
    	return "user/mymoim";
    }
    
    
    ///////////////////////////////////////////////
    ///////////////////////////////////////////////
    ///////////////////////////////////////////////
    ///////////////////////////////////////////////
    ///////////////////////////////////////////////
    ///////////////////////////////////////////////
    
	 /*
	  * 1:1문의 작성 폼
	  */
	 @GetMapping("/askWrite")
	    public String askWritePage(Model model) {
		 
		 
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
		    
		    
	        return "user/askWrite";
	    }

	 	/*
	 	 * 1:1문의 작성 
	 	 */
	 	@PostMapping("/askWriteResult")
		 public String askWriteResult(@RequestParam("category") String category,
		                                 @RequestParam("username") String username,
		                                 @RequestParam("notiTitle") String notiTitle,
		                                 @RequestParam("notiContent") String notiContent,
		                                 @RequestParam("notiAt") String notiAt) {
	 		
	 		 String categoryValue = "a"; // 변수에 값을 할당합니다.
	
		     // 공지사항 엔티티 객체 생성
		     AdminNotice notice = new AdminNotice();
		     notice.setCategory(category);
		     notice.setUsername(username);
		     notice.setNotiTitle(notiTitle);
		     notice.setNotiContent(notiContent);
		     notice.setNotiAt(notiAt);
		     
		     // 모델에 값을 추가합니다.
		     // 현재 시간을 가져옵니다.
		     Timestamp now = new Timestamp(System.currentTimeMillis());
	
		     // 엔티티 객체에 현재 시간을 할당합니다.
		     notice.setNotiDate(now);
	
		     // 공지사항 저장
		     adminNoticeService.saveAdminAsk(notice);
	
		     // 작성 성공 페이지로 리다이렉트
		     return "redirect:/myqnaList";
		 }

    
	 	@GetMapping("/myqnaList")
	 	public String adminAskList(@RequestParam(defaultValue = "0") int page, Model model) {
	 	    // 현재 사용자의 인증 정보 가져오기
	 	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	 	    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	 	    Iterator<? extends GrantedAuthority> iter = authorities.iterator();
	 	    GrantedAuthority auth = iter.next();
	 	    String role = auth.getAuthority();

	 	    String username = SecurityContextHolder.getContext().getAuthentication().getName();
	 	    // 뷰로 사용자 이름 전달
	 	    model.addAttribute("role", role);
	 	    model.addAttribute("username", username);

	 	    // 페이지 번호와 페이지 크기를 기반으로 페이징 객체를 생성합니다.
	 	    Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "notiDate"));
	 	    // 페이징 처리된 카테고리별 공지사항을 가져옵니다.
	 	    Page<AdminNotice> noticePage = adminNoticeService.findQnaByUsername(username, pageable);
	 	    System.out.println("noticePage" + noticePage);

	 	    // 페이징된 데이터 리스트를 모델에 추가합니다.
	 	    List<AdminNotice> askList = noticePage.getContent();

	 	    // 모델에 리스트를 추가합니다.
	 	    model.addAttribute("askList", askList);

	 	    // 페이징 정보도 모델에 추가할 수 있습니다.
	 	    model.addAttribute("currentPage", page);
	 	    model.addAttribute("totalPages", noticePage.getTotalPages());

	 	    // admin/noticelist 템플릿으로 이동합니다.
	 	    return "user/myqnaList";
	 	}

    
    /*
 	 * 1:1문의 상세보기 
 	 */
// 	@GetMapping("/myqnaDetail")
// 	public String getReplyByNotiNo(@RequestParam("notiNo") Integer notiNo, Model model) {
// 	    // ReplyNotice 테이블에서 해당 notiNo 값과 일치하는 답변 정보를 불러옵니다.
// 	    ReplyNotice reply = adminNoticeService.findReplyByNotiNo(notiNo);
// 	    
// 	    // 문의 테이블에서 해당 notiNo 값과 일치하는 문의 정보를 불러옵니다.
// 	    AdminNotice notice = adminNoticeService.findNoticeByNotiNo(notiNo);
// 	    
// 	    // reply와 notice가 모두 존재하고 notiNo 값이 일치하는 경우
// 	    if (reply != null && notice != null && reply.getNotiNo().equals(notice.getNotiNo())) {
// 	        // 모델에 답변과 문의 데이터를 추가하여 JSP로 전달합니다.
// 	        model.addAttribute("reply", reply);
// 	        model.addAttribute("notice", notice);
// 	    }
// 	    
// 	    return "user/myqnaDetail"; // 답변을 표시하는 JSP 페이지의 경로
// 	}
 	
	 	@GetMapping("/myqnaDetail")
	 	public String getReplyByNotiNo(@RequestParam("id") Integer id, Model model) {
	 	    // id를 사용하여 공지사항을 조회합니다.
	 	    AdminNotice notice = adminNoticeService.findNoticeById(id);
	 	    
	 	    System.out.println(notice);
	 	    System.out.println(id);
	 	    // 해당하는 공지사항과 관련된 답변을 가져옵니다.
	 	    if (notice != null) {
	 	        // 관련된 답변들을 가져옵니다.
	 	        List<ReplyNotice> replies = adminNoticeService.findRepliesByNotiNo(notice.getNotiNo());
	 	        System.out.println(replies);
	 	        
	 	        model.addAttribute("notice", notice);
	 	        // 답변이 존재하는지 확인합니다.
	 	        if (replies != null && !replies.isEmpty()) {
	 	            // 가져온 공지사항과 답변을 모델에 추가합니다.
	 	        	
	 	        	model.addAttribute("replies", replies);
	 	        	System.out.println("replies : " + replies);
	 	        } 
	
	 	        model.addAttribute("errorMessage", "해당하는 문의를 찾을 수 없습니다.");
	 	    }

	 	    // noticeDetail 템플릿으로 이동합니다.
	 	    return "user/myqnaDetail";
	 	}
	 	
	 	
	 	
	 	
	 	//////////////////////////////////////
	 	//////////////////////////////////////
	 	//////////////////////////////////////
	 	//////////////////////////////////////
	 	//////////////////////////////////////
	 	//////////////////////////////////////

	 	 /*
	     * 공지사항 리스트
	     */
	 	@GetMapping("/noticeUserList")
	 	public String noticeUserList(@RequestParam(defaultValue = "0") int page, Model model) {
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
	 	    return "user/noticeUserList";
	 	}
	 	
	 	/*
	 	 * 공지사항 상세보기 
	 	 */
	 	@GetMapping("/noticeUserDetail")
	 	public String noticeUserDetail(@RequestParam Integer id, Model model) {
	 	    // id를 사용하여 공지사항을 조회합니다.
	 	    AdminNotice notice = adminNoticeService.findNoticeById(id);
	 	    
	 	   // 조회수 증가
	 	    adminNoticeService.incrementNoticeHit(id);
	 	    
	 	    // 조회된 공지사항을 모델에 추가합니다.
	 	    model.addAttribute("notice", notice);
	 	    
	 	    // noticeDetail 템플릿으로 이동합니다.
	 	    return "user/noticeUserDetail";
	 	}





}

 	
