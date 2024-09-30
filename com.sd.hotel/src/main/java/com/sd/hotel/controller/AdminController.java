package com.sd.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminController {

	@GetMapping("/adminMypage.page")
	public String getMypage() {
		return "admin/adminMypage";
	}
	
	@PostMapping("/modifyAdminPw.do")
	public String modifyAdminPw() {
		
		return "admin/adminMypage";
	}
	
}
