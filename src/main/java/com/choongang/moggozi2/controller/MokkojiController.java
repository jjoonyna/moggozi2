package com.choongang.moggozi2.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.choongang.moggozi2.entity.Mokkoji;
import com.choongang.moggozi2.service.MokkojiService;



@Controller
public class MokkojiController {
    
    private final MokkojiService mokkojiService;

    @Autowired
    public MokkojiController(MokkojiService mokkojiService) {
        this.mokkojiService = mokkojiService;
    }
    
    @GetMapping("/result")
    public String result() {
        return "result";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("mokkoji_images") MultipartFile mokkoji_images,
                                   @RequestParam("user_nickname") String user_nickname,
                                   @RequestParam("mokkoji_person") Integer mokkoji_person,
                                   @RequestParam("mokkoji_title") String mokkoji_title,
                                   @RequestParam("mokkoji_intro") String mokkoji_intro,
                                   @RequestParam("mokkoji_category") String mokkoji_category) {
        if (mokkoji_images.isEmpty()) {
            // 파일이 비어있을 경우 처리
            return "redirect:/uploadFailure";
        }

        try {
            // 업로드된 파일을 저장할 디렉토리 설정
            String uploadDir = "/upload/";
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs(); // 디렉토리가 존재하지 않으면 생성합니다.
            }
            String fileName = mokkoji_images.getOriginalFilename();
            File uploadFile = new File(uploadDir + fileName);
            mokkoji_images.transferTo(uploadFile);
            
            // 파일 업로드 성공 시 파일 정보를 데이터베이스에 저장
            Mokkoji mokkoji = new Mokkoji();
            mokkoji.setMokkoji_images(uploadDir + fileName);
            mokkoji.setUser_nickname(user_nickname);
            mokkoji.setMokkoji_person(mokkoji_person);
            mokkoji.setMokkoji_title(mokkoji_title);
            mokkoji.setMokkoji_intro(mokkoji_intro);
            mokkoji.setMokkoji_category(mokkoji_category);
            mokkojiService.saveMokkoji(mokkoji);
               
            // 파일 업로드 성공 시 처리
            return "redirect:/main";
        } catch (IOException e) {
            e.printStackTrace();
            // 파일 업로드 실패 시 처리
            return "redirect:/uploadFailure";
        }
    }
    
//    @GetMapping("/main")
//    public String allMokkogi(@RequestParam(defaultValue = "0") int page, Model model) {
//        // 페이지 번호와 페이지 크기를 기반으로 페이징 객체를 생성합니다.
//        Pageable pageable = PageRequest.of(page, 9);
//        
//        // 페이징 처리된 데이터를 가져옵니다.
//        Page<Mokkoji> mokkojiPage = mokkojiService.findAllMokkoji(pageable);
//        
//        // 페이징된 데이터 리스트를 모델에 추가합니다.
//        List<Mokkoji> mokkojiList = mokkojiPage.getContent();
//        
//        // 모델에 리스트를 추가합니다.
//        model.addAttribute("mokkojiList", mokkojiList);
//        
//        // 페이징 정보도 모델에 추가할 수 있습니다.
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPages", mokkojiPage.getTotalPages());
//        
//        // main 템플릿으로 이동합니다.
//        return "main";
//    }
    
}