package com.choongang.moggozi2.controller;


import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.choongang.moggozi2.entity.CommonBoard;
import com.choongang.moggozi2.entity.CustomUserDetails;
import com.choongang.moggozi2.service.BoardService;

@Controller
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/boardlist")
    public String boardlist(Authentication auth, @RequestParam(value="page", defaultValue="1")int page, Model model) {
 	    String username = null;
 	    String usernick = null;

 	    if (auth != null) {
 	        username = auth.getName();

 	        // usernick 가져오기
 	        if (auth.getPrincipal() instanceof UserDetails) {
 	            usernick = ((CustomUserDetails) auth.getPrincipal()).getUsernick();
 	        }
 	    }

 	    // 뷰로 사용자 이름과 usernick 전달
 	    model.addAttribute("username", username);
 	    model.addAttribute("usernick", usernick); // usernick 추가
	    	
    	
    	
    	int limit = 10;
    	int listcount = (int)boardService.getCount();
    	int start = (page-1) * limit;	// 각 page별 추출할 시작번호 : 0,10,20...
    	
    	List<CommonBoard> boardlist = boardService.getList(start);
    	
    	// 총페이지
    	int pageCount = listcount / limit + ((listcount % limit == 0) ? 0 : 1);
    	int startPage = ((page - 1) / 10) * limit + 1; // 1,11,21...
    	int endPage = startPage + 10 - 1;
    	if(endPage > pageCount)
    	   endPage = pageCount;
    	
    	model.addAttribute("page", page);
    	model.addAttribute("listcount", listcount);
    	model.addAttribute("boardlist", boardlist);
    	model.addAttribute("pageCount", pageCount);
    	model.addAttribute("startPage", startPage);
    	model.addAttribute("endPage", endPage);
    	
        return "boardlist";
    }
    
    @GetMapping("/boardcontent")
    	public String boardcontent(Authentication auth, @RequestParam Integer boardNo, int page, Model model) {
	        
    		String username = null;
	 	    String usernick = null;
	
	 	    if (auth != null) {
	 	        username = auth.getName();
	
	 	        // usernick 가져오기
	 	        if (auth.getPrincipal() instanceof UserDetails) {
	 	            usernick = ((CustomUserDetails) auth.getPrincipal()).getUsernick();
	 	        }
	 	    }

 	    // 뷰로 사용자 이름과 usernick 전달
 	    model.addAttribute("username", username);
 	    model.addAttribute("usernick", usernick); // usernick 추가
    	
    		boardService.increaseViews(boardNo);
    		
    		CommonBoard commonBoard = boardService.getContent(boardNo);
    		
    		model.addAttribute("CommonBoard", commonBoard);
    		model.addAttribute("boardNo", boardNo);
    		model.addAttribute("page", page);
    		
            return "boardcontent";
    }

    @GetMapping("/boardinsertform")
    public String boardinsertform(Authentication auth, Model model) {
        String username = null;
 	    String usernick = null;

 	    if (auth != null) {
 	        username = auth.getName();

 	        // usernick 가져오기
 	        if (auth.getPrincipal() instanceof UserDetails) {
 	            usernick = ((CustomUserDetails) auth.getPrincipal()).getUsernick();
 	        }
 	    }

 	    // 뷰로 사용자 이름과 usernick 전달
 	    model.addAttribute("username", username);
 	    model.addAttribute("usernick", usernick); // usernick 추가
	    	
    	return "boardinsertform";
    }
    
    @PostMapping(value = "/boardinsert", consumes = "multipart/form-data")
    public String insertForm(Model model,
                             @RequestParam("category") String category,
                             @RequestParam("boardSubject") String boardSubject,
                             @RequestParam("boardContent") String boardContent,
                             @RequestParam("usernick") String usernick,
                             @RequestParam(value="boardFile", required=false) MultipartFile boardFile) {
    	
        try {
            // 파일 업로드를 수행
            String fileName = "";
            if (boardFile != null && !boardFile.isEmpty()) {
                // 업로드된 파일이 있을 경우에만 파일을 저장
                // 업로드된 파일을 저장할 디렉토리 설정
                String uploadDir = "/upload1/";
                File uploadDirFile = new File(uploadDir);
                if (!uploadDirFile.exists()) {
                    uploadDirFile.mkdirs(); // 디렉토리가 존재하지 않으면 생성
                }
                fileName = boardFile.getOriginalFilename();
                File uploadFile = new File(uploadDir + fileName);
                boardFile.transferTo(uploadFile);
            }
            // 게시글 정보를 DB에 저장
            CommonBoard commonBoard = new CommonBoard();
            commonBoard.setBoardSubject(boardSubject);
            commonBoard.setUsernick(usernick);
            commonBoard.setBoardContent(boardContent);
            commonBoard.setCategory(category);
            commonBoard.setBoardFile(fileName);
            boardService.saveCommonBoard(commonBoard);
            
            // 게시글 등록이 성공한 경우, 게시글 목록 페이지로 리다이렉트
            return "redirect:/boardlist";
        } catch (IOException e) {
            e.printStackTrace();
            // 파일 업로드 실패 시 처리
            return "redirect:/boardlist";
        }
    }


    @GetMapping("/boardupdateform")
    public String boardupdateform(Authentication auth, @RequestParam Integer boardNo, int page, Model model) {
    	
    	
    	
 	    String username = null;
 	    String usernick = null;

 	    if (auth != null) {
 	        username = auth.getName();

 	        // usernick 가져오기
 	        if (auth.getPrincipal() instanceof UserDetails) {
 	            usernick = ((CustomUserDetails) auth.getPrincipal()).getUsernick();
 	        }
 	    }

 	    // 뷰로 사용자 이름과 usernick 전달
 	    model.addAttribute("username", username);
 	    model.addAttribute("usernick", usernick); // usernick 추가
	
	 	    
			CommonBoard commonBoard = boardService.getContent(boardNo);
			
		    	
			model.addAttribute("boardNo", boardNo);
			model.addAttribute("commonBoard", commonBoard);
			model.addAttribute("page", page);

		return "boardupdateform";
    }

    @PostMapping("/boardupdate")
    public String boardupdate(@RequestParam("boardNo") Integer boardNo,
                              @RequestParam(value = "boardFile", required = false) MultipartFile boardFile,
                              @RequestParam("category") String category,
                              @RequestParam("boardSubject") String boardSubject,
                              @RequestParam("boardContent") String boardContent,
                              @RequestParam("boardDate") Timestamp boardDate,
                              @RequestParam("page") int page) {
        // 해당 게시글 번호로 게시글을 조회합니다.

        CommonBoard commonBoard = boardService.getContent(boardNo);
        
        // 게시글이 존재하면 업데이트를 수행합니다.
        if (commonBoard != null) {
            try {
                // 파일 업로드를 수행합니다.
                if (boardFile != null && !boardFile.isEmpty()) {
                    // 업로드된 파일을 저장할 디렉토리 설정
                    String uploadDir = "/upload/";
                    File uploadDirFile = new File(uploadDir);
                    if (!uploadDirFile.exists()) {
                        uploadDirFile.mkdirs(); // 디렉토리가 존재하지 않으면 생성합니다.
                    }
                    String fileName = boardFile.getOriginalFilename();
                    File uploadFile = new File(uploadDir + fileName);
                    boardFile.transferTo(uploadFile);
                    
                    // 업데이트된 파일 정보를 설정합니다.
                    commonBoard.setBoardFile(uploadDir + fileName);
                }
                
                // 업데이트된 내용을 설정합니다.
                commonBoard.setCategory(category);
                commonBoard.setBoardSubject(boardSubject);
                commonBoard.setBoardContent(boardContent);
                commonBoard.setBoardDate(boardDate);
                
                // 게시글을 업데이트합니다.
                boardService.saveCommonBoard(commonBoard);
                
                // 게시글 업데이트가 성공한 경우, 해당 게시글 상세 페이지로 리다이렉트합니다.
                return "redirect:boardcontent?boardNo=" + commonBoard.getBoardNo()+"&page="+page;
            } catch (IOException e) {
                e.printStackTrace();
                // 파일 업로드 실패 시 처리
                return "redirect:boardcontent?boardNo=" + boardNo+"&page="+page;
            }
        } else {
            // 게시글이 존재하지 않는 경우에 대한 처리
            return "redirect:boardlist";
        }
    }

    
    @GetMapping("/boarddelete")
    public String boarddelete(Authentication auth, @RequestParam Integer boardNo, int page, Model model) {
    	
    	String username = null;
 	    String usernick = null;

 	    if (auth != null) {
 	        username = auth.getName();

 	        // usernick 가져오기
 	        if (auth.getPrincipal() instanceof UserDetails) {
 	            usernick = ((UserDetails) auth.getPrincipal()).getUsername();
 	        }
 	    }
 	    // 뷰로 사용자 이름과 usernick 전달
 	    model.addAttribute("username", username);
 	    model.addAttribute("usernick", usernick); // usernick 추가
		
		System.out.println(boardNo);
		
    	boardService.boarddelete(boardNo);
    	
	    model.addAttribute("page",page);
    	return "redirect:boardlist";
    }
    
}