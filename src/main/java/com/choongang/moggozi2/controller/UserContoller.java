package com.choongang.moggozi2.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


import com.choongang.moggozi2.entity.CustomUserDetails;
import com.choongang.moggozi2.entity.AdminNotice;
import com.choongang.moggozi2.entity.Mokkoji;
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

	
	/*
	 * 로그인&회원가입 폼
	 */
    @GetMapping("signup")
    public String signupP() {
        return "user/signup";
    }
    

    @PostMapping("/joinProc")
    public String joinProcess(User user) {
        System.out.println(user.getUsername());
        service.newuserjoin(user);
        
        return "redirect:main";
    }

    
    
    /*
     * 메인 페이지 
     */
    @GetMapping("/")
    public String loginPage(Model model,
                            @RequestParam(defaultValue = "0") int page, Authentication auth) {
        if (page < 0) {
            page = 0; // 음수인 경우 기본값 0으로 설정
        }
        

        int pageSize = 9; // 페이지 크기 설정
        
        String username = null;
 	    String usernick = null;
 	    String role = null;
 	    
 	    if (auth != null) {
 	        username = auth.getName();
 	        role = auth.getAuthorities().stream().findFirst().orElse(null).getAuthority();
 	        
 	        // usernick 가져오기
 	        if (auth.getPrincipal() instanceof CustomUserDetails) {
 	            usernick = ((CustomUserDetails) auth.getPrincipal()).getUsernick();
 	        }}

        System.out.println(username);
 	    System.out.println(usernick);
 	    System.out.println(role);
        
        // 목록을 불러오는 로직
        Page<Mokkoji> mokkojiPage = mokkojiService.findAllMokkoji(PageRequest.of(page, pageSize));
        model.addAttribute("usernick", usernick);
        model.addAttribute("mokkojiList", mokkojiPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", mokkojiPage.getTotalPages());

        return "main"; // 결과를 보여줄 뷰 이름
    }

    
   
    /*
     * 로그인 페이지 
     */
    @GetMapping("/main")
    public String mainPage(Model model,
                           @RequestParam(name = "keyword", required = false) String keyword,
                           @RequestParam(name = "category", required = false) String category,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           Authentication authentication) {

        String username = null;
        String usernick = null;
        String role = null;

        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            username = authentication.getName();
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            if (!authorities.isEmpty()) {
                GrantedAuthority auth = authorities.iterator().next();
                role = auth.getAuthority();
            }

            // 사용자의 닉네임 가져오기
            if (authentication.getPrincipal() instanceof UserDetails) {
                usernick = ((CustomUserDetails) authentication.getPrincipal()).getUsernick();
            }
        }

        if (role != null && role.equals("ADMIN")) {
            // 관리자 페이지로 이동
            model.addAttribute("username", username);
            model.addAttribute("role", role);
            model.addAttribute("usernick", usernick);

            System.out.println(username);
            System.out.println(usernick);
            System.out.println(role);

            return "admin/mainAdmin";
        } else {
            // 사용자 페이지 로직 수행
            int pageSize = 9; // 페이지당 아이템 수

            // 페이지 번호가 음수이면 0으로 설정
            if (page < 0) {
                page = 0;
            }

            // 목록을 불러오는 로직
            Page<Mokkoji> mokkojiPage = mokkojiService.findAllMokkoji(PageRequest.of(page, pageSize));
         
            model.addAttribute("mokkojiList", mokkojiPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", mokkojiPage.getTotalPages());

            // 사용자 페이지로 이동
            model.addAttribute("username", username);
            model.addAttribute("role", role);
            model.addAttribute("usernick", usernick);
            return "main"; // 사용자 페이지로 이동
        }
    }


    @GetMapping("/mypage")
    public String mypageP(Authentication auth, Model model) {
        
    	String username = null;
 	    String usernick = null;
 	    String role = null;
 	    
 	    if (auth != null) {
 	        username = auth.getName();
 	        role = auth.getAuthorities().stream().findFirst().orElse(null).getAuthority();
 	        
 	        // usernick 가져오기
 	        if (auth.getPrincipal() instanceof CustomUserDetails) {
 	            usernick = ((CustomUserDetails) auth.getPrincipal()).getUsernick();
 	        }}
 	    
 	   User user = service.findById(username);
        // 뷰로 사용자 이름과 usernick 전달
        model.addAttribute("username", username);
        model.addAttribute("usernick", usernick);
        model.addAttribute("userph", user.getUserph());
        model.addAttribute("userzip", user.getUserzip());
        model.addAttribute("useryear", user.getUseryear());
        model.addAttribute("useraddress1", user.getUseraddress1());
        model.addAttribute("useraddress2", user.getUseraddress2());
        model.addAttribute("useremail", user.getUseremail());
        model.addAttribute("usergender", user.getUsergender());
        
        model.addAttribute("role", role);


        return "user/mypage";
    }
    
    //정보수정 페이지 이동
    @GetMapping("/myinfoupdate")
    public String myinfoupdate(Authentication auth, Model model) {
    	String username = null;
 	    String usernick = null;
 	    String role = null;
 	    
 	    if (auth != null) {
 	        username = auth.getName();
 	        role = auth.getAuthorities().stream().findFirst().orElse(null).getAuthority();
 	        
 	        // usernick 가져오기
 	        if (auth.getPrincipal() instanceof CustomUserDetails) {
 	            usernick = ((CustomUserDetails) auth.getPrincipal()).getUsernick();
 	        }}
 	  User user = service.findById(username);
 	    
 	  model.addAttribute("username", username);
      model.addAttribute("usernick", usernick);
      model.addAttribute("userph", user.getUserph());
      model.addAttribute("userzip", user.getUserzip());
      model.addAttribute("useryear", user.getUseryear());
      model.addAttribute("useraddress1", user.getUseraddress1());
      model.addAttribute("useraddress2", user.getUseraddress2());
      model.addAttribute("useremail", user.getUseremail());
      model.addAttribute("usergender", user.getUsergender());
      

 	   return "user/myinfoupdate";
    }
    
    
    //정보수정
    @PostMapping("/infoUpdate")
    public String myinfoupdate(Authentication auth, User user) {
    	String username = null;
 	    String usernick = null;
 	    String role = null;
 	    
 	    if (auth != null) {
 	        username = auth.getName();
 	        role = auth.getAuthorities().stream().findFirst().orElse(null).getAuthority();
 	        
 	        // usernick 가져오기
 	        if (auth.getPrincipal() instanceof CustomUserDetails) {
 	            usernick = ((CustomUserDetails) auth.getPrincipal()).getUsernick();
 	        }}
 	    User db = service.findById(user.getUsername());
 	    if(bCryptPasswordEncoder.matches(user.getPassword(), db.getPassword())) {
 	    	user.setRole(role);
 	    	user.setPassword(db.getPassword());
 	    	user.setUsergender(db.getUsergender());
 	    	service.update(user);
 	    	return "redirect:mypage";
 	    }
 	    else {
 	    	return "redirect:mypage";
 	    }
    }
    
    //비번변경 페이지 이동
    @GetMapping("/mypwdchange")
    public String mypwdchange(Authentication auth, Model model) {
    	String username = null;
 	    String usernick = null;
 	    String role = null;
 	    
 	    if (auth != null) {
 	        username = auth.getName();
 	        role = auth.getAuthorities().stream().findFirst().orElse(null).getAuthority();
 	        
 	        // usernick 가져오기
 	        if (auth.getPrincipal() instanceof CustomUserDetails) {
 	            usernick = ((CustomUserDetails) auth.getPrincipal()).getUsernick();
 	        }}
 	  model.addAttribute("username", username);
      model.addAttribute("usernick", usernick);
 	   return "user/mypwdchange";
    }
    
    //비번변경
    @PostMapping("/pwdchange")
    public String pwdchange(Authentication auth, User user,@RequestParam("newpassword") String newpwd) {
    	String username = null;
 	    String usernick = null;
 	    String role = null;
 	    
 	    if (auth != null) {
 	        username = auth.getName();
 	        role = auth.getAuthorities().stream().findFirst().orElse(null).getAuthority();
 	        
 	        // usernick 가져오기
 	        if (auth.getPrincipal() instanceof CustomUserDetails) {
 	            usernick = ((CustomUserDetails) auth.getPrincipal()).getUsernick();
 	        }}
 	    User db = service.findById(user.getUsername());
 	    if(bCryptPasswordEncoder.matches(user.getPassword(), db.getPassword())) {
 	    	String pwd = bCryptPasswordEncoder.encode(newpwd);
 	    	user.setPassword(pwd);
 	    	user.setRole(role);
 	    	user.setUserzip(db.getUserzip());
 	    	user.setUseraddress1(db.getUseraddress1());
 	    	user.setUseraddress2(db.getUseraddress2());
 	    	user.setUseremail(db.getUseremail());
 	    	user.setUsergender(db.getUsergender());
 	    	user.setUserph(db.getUserph());
 	    	user.setUseryear(db.getUseryear());
 	    	service.update(user);
 	    }
 	   
    	return "redirect:main";
    }
    
    
    //회원탈퇴 페이지 이동
    @GetMapping("/mydelete")
    public String mydelete(Authentication auth, Model model) {
    	String username = null;
 	    String usernick = null;
 	    String role = null;
 	    
 	    if (auth != null) {
 	        username = auth.getName();
 	        role = auth.getAuthorities().stream().findFirst().orElse(null).getAuthority();
 	        
 	        // usernick 가져오기
 	        if (auth.getPrincipal() instanceof CustomUserDetails) {
 	            usernick = ((CustomUserDetails) auth.getPrincipal()).getUsernick();
 	        }}
 	  model.addAttribute("username", username);
      model.addAttribute("usernick", usernick);
 	   return "user/mydelete";
    }
    
    //회원 탈퇴
    @PostMapping("deleteok")
    public String deleteok(Authentication auth, User user) {
    	String username = null;
 	    String usernick = null;
 	    String role = null;
 	    
 	    if (auth != null) {
 	        username = auth.getName();
 	        role = auth.getAuthorities().stream().findFirst().orElse(null).getAuthority();
 	        
 	        // usernick 가져오기
 	        if (auth.getPrincipal() instanceof CustomUserDetails) {
 	            usernick = ((CustomUserDetails) auth.getPrincipal()).getUsernick();
 	        }}
 	    User db = service.findById(user.getUsername());
 	    if(bCryptPasswordEncoder.matches(user.getPassword(), db.getPassword())) {
 	    	service.delete(user.getUsername());
 	    }
    	return "main";
    }
    
    
    @GetMapping("mymoim")
    public String mymoim(Authentication auth, Model model) {
    	String username = null;
 	    String usernick = null;
 	    String role = null;
 	    
 	    if (auth != null) {
 	        username = auth.getName();
 	        role = auth.getAuthorities().stream().findFirst().orElse(null).getAuthority();
 	        
 	        // usernick 가져오기
 	        if (auth.getPrincipal() instanceof CustomUserDetails) {
 	            usernick = ((CustomUserDetails) auth.getPrincipal()).getUsernick();
 	        }}
 	    
 	    List<Mokkoji> mokkoji = mokkojiService.findAllMokkoji(usernick);
 	    System.out.println(mokkoji.get(0).getMokkojiTitle());
 	    model.addAttribute("mokkoji",mokkoji);
 	    
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
	    public String askWritePage(Model model, Authentication auth) {
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
	
	        return "user/askWrite";
	    }

	 	/*
	 	 * 1:1문의 작성 
	 	 */
	 	@PostMapping("/askWriteResult")
		 public String askWriteResult(@RequestParam("category") String category,
		                                 @RequestParam("usernick") String usernick,
		                                 @RequestParam("username") String username,
		                                 @RequestParam("notiTitle") String notiTitle,
		                                 @RequestParam("notiContent") String notiContent,
		                                 @RequestParam("notiAt") String notiAt) {
	 		
	 		 String categoryValue = "a"; // 변수에 값을 할당합니다.
	
		     // 공지사항 엔티티 객체 생성
		     AdminNotice notice = new AdminNotice();
		     notice.setCategory(category);
		     notice.setUsernick(usernick);
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
		     return "redirect:/user/myqnaList";
		 }

    
	 	@GetMapping("/myqnaList")
	 	public String adminAskList(Authentication auth, @RequestParam(defaultValue = "0") int page, Model model) {
	 	    
	 		  String username = null;
		 	    String usernick = null;
		 	    String role = null;
		 	    
		 	    if (auth != null) {
		 	        username = auth.getName();
		 	        role = auth.getAuthorities().stream().findFirst().orElse(null).getAuthority();
		 	        
		 	        // usernick 가져오기
		 	        if (auth.getPrincipal() instanceof CustomUserDetails) {
		 	            usernick = ((CustomUserDetails) auth.getPrincipal()).getUsernick();
		 	        }}

	 	    // 뷰로 사용자 이름과 usernick 전달
	 	    model.addAttribute("username", username);
	 	    model.addAttribute("usernick", usernick); // usernick 추가
	 	    model.addAttribute("role", role);

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
	 	@GetMapping("/myqnaDetail")
	 	public String getReplyByNotiNo(@RequestParam("id") Integer id, @RequestParam(name = "usernick", required = false) String usernick, Model model) {
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
	 	public String noticeUserList(@RequestParam(defaultValue = "1") int pageNum, 
	 	                             @RequestParam(defaultValue = "10") int pageSize,
	 	                             Authentication auth, Model model) {

	 	    String username = null;
	 	    String usernick = null;
	 	    String role = null;

	 	    if (auth != null) {
	 	        username = auth.getName();
	 	        role = auth.getAuthorities().stream().findFirst().orElse(null).getAuthority();

	 	        // usernick 가져오기
	 	        if (auth.getPrincipal() instanceof UserDetails) {
	 	            usernick = ((UserDetails) auth.getPrincipal()).getUsername();
	 	        }
	 	    }

	 	    // 뷰로 사용자 이름과 usernick 전달
	 	    model.addAttribute("username", username);
	 	    model.addAttribute("usernick", usernick); // usernick 추가
	 	    model.addAttribute("role", role);

	 	    // 페이지 번호를 0부터 시작하는 인덱스로 변환합니다.
	 	    int page = pageNum - 1;

	 	    // 페이지 번호가 음수이면 0으로 설정
	 	    if (page < 0) {
	 	        page = 0;
	 	    }

	 	    // 페이지 번호와 페이지 크기를 기반으로 페이징 객체를 생성합니다.
	 	    Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "notiDate"));

	 	    // 페이징 처리된 공지사항 목록을 가져옵니다.
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

	 	    // 페이징 정보도 모델에 추가합니다.
	 	    model.addAttribute("currentPage", pageNum); // 페이지 번호를 그대로 사용합니다.
	 	    model.addAttribute("totalPages", noticePage.getTotalPages());

	 	    // user/noticeUserList 템플릿으로 이동합니다.
	 	    return "user/noticeUserList";
	 	}



	 	
	 	
	 	/*
	 	 * 공지사항 상세보기 
	 	 */
	 	@GetMapping("/noticeUserDetail")
	 	public String noticeUserDetail(@RequestParam Integer id, @RequestParam(name = "usernick", required = false) String usernick, Model model) {
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

 	
