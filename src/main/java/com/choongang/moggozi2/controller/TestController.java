package com.choongang.moggozi2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	
	
	@GetMapping("video")
	public String video() {
		return "video";
	}
	
	@GetMapping("snslogin")
	public String snstest() {
		return "snslogin";
	}

	
}
