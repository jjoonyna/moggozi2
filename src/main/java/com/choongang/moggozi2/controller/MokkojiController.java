package com.choongang.moggozi2.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.choongang.moggozi2.entity.CustomUserDetails;
import com.choongang.moggozi2.entity.Mokkoji;
import com.choongang.moggozi2.entity.User;
import com.choongang.moggozi2.service.MokkojiService;

@Controller
public class MokkojiController {
	
	@Autowired
	private EntityManager em;
    
	
    private final MokkojiService mokkojiService;

    @Autowired
    public MokkojiController(MokkojiService mokkojiService) {
        this.mokkojiService = mokkojiService;
    }
    
    @GetMapping("/result")
    public String result(Authentication authentication, Model model) {
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
         model.addAttribute("usernick",usernick);
         
        return "result";
    }

    @PostMapping("/upload")
    public String handleFileUpload(Authentication authentication,
    							   @RequestParam("mokkojiImages") MultipartFile mokkojiImages,
                                   @RequestParam("usernick") String nick,
                                   @RequestParam("mokkojiPerson") Integer mokkojiPerson,
                                   @RequestParam("mokkojiTitle") String mokkojiTitle,
                                   @RequestParam("mokkojiIntro") String mokkojiIntro,
                                   @RequestParam("mokkojiCategory") String mokkojiCategory) {

   	 	String username = null;
        String usernick = null;

        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            username = authentication.getName();
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            if (!authorities.isEmpty()) {
                GrantedAuthority auth = authorities.iterator().next();
            }

            // 사용자의 닉네임 가져오기
            if (authentication.getPrincipal() instanceof UserDetails) {
                usernick = ((CustomUserDetails) authentication.getPrincipal()).getUsernick();
            }
        }
        

        
	        Mokkoji mokkoji = new Mokkoji();
	        
	        // 내가 정해둔 이미지 경로를 설정
	        String customImagePath = "../images/thumbnail.jpg";
	        mokkoji.setMokkojiImages(customImagePath);


            
		     // 현재 시간을 가져옵니다.
		     Timestamp now = new Timestamp(System.currentTimeMillis());
	
		     // 엔티티 객체에 현재 시간을 할당합니다.
		     mokkoji.setMokkojiDate(now);
            
		    
		    mokkoji.setUsernick(nick);
            mokkoji.setMokkojiPerson(mokkojiPerson);
            mokkoji.setMokkojiTitle(mokkojiTitle);
            mokkoji.setMokkojiIntro(mokkojiIntro);
            mokkoji.setMokkojiCategory(mokkojiCategory);
            mokkojiService.saveMokkoji(mokkoji);
            // 파일 업로드 성공 시 처리
            return "redirect:/main";
    }
    
//    @GetMapping("/main")
//    public String main(@RequestParam(defaultValue = "0") int page, Model model) {
//        if (page < 0) {
//            page = 0; // 음수인 경우 기본값 0으로 설정
//        }
//
//        int pageSize = 9; // 페이지 크기 설정
//
//        Page<Mokkoji> mokkojiPage = mokkojiService.findAllMokkoji(PageRequest.of(page, pageSize));
//        model.addAttribute("mokkojiList", mokkojiPage.getContent());
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPages", mokkojiPage.getTotalPages());
//
//        return "main"; // 결과를 보여줄 뷰 이름
//    }
//    
    @GetMapping("/search")
    public String searchMokkoji(
            @RequestParam(name = "keyword") String keyword,
            @RequestParam(name = "category", required = false) String category,
            @RequestParam(name = "page", defaultValue = "0") int page,
            Model model,
            Authentication auth
    ) { 
    	
    	String username = null;
  	    String usernick = null;
  	    
  	    if (auth != null) {
  	        username = auth.getName();
  	        
  	        // usernick 가져오기
  	        if (auth.getPrincipal() instanceof CustomUserDetails) {
  	            usernick = ((CustomUserDetails) auth.getPrincipal()).getUsernick();
  	        }}
        
    	
    	
    	
    	
        int pageSize = 9; // 페이지당 아이템 수

        // 페이지 번호가 음수이면 0으로 설정
        if (page < 0) {
            page = 0;
        }

        // 검색 결과의 총 수 조회
        int totalResults = mokkojiService.countSearchResults(keyword, category);

        // 페이지 요청 객체 생성
        Pageable pageable = PageRequest.of(page, pageSize);

        // 검색 및 페이징 처리
        Page<Mokkoji> searchResults = mokkojiService.searchMokkoji(keyword, category, pageable);

        // 모델에 결과를 추가하여 뷰로 전달
        model.addAttribute("keyword", keyword);
        model.addAttribute("category", category);
        model.addAttribute("mokkojiList", searchResults.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", searchResults.getTotalPages());
        model.addAttribute("totalResults", totalResults);
        return "main"; // 검색 결과를 보여줄 뷰 이름
    }
}