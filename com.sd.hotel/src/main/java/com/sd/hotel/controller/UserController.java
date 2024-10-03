package com.sd.hotel.controller;

import java.util.Map;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sd.hotel.dto.CustomUserDetails;
import com.sd.hotel.dto.MemberDto;
import com.sd.hotel.service.UserService;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/signup.page")
	public String signup() {
		return "user/signup";
	}
	
	@PostMapping(value="/checkId.do", produces="application/json")
	public ResponseEntity<Map<String, Object>> checkId(@RequestBody Map<String, Object> params) {
		return userService.checkId(params);
	}

	/*
		GET 대신 POST사용이유
		· 데이터보안 : 민감한 데이터라 URL에 노출시키지 않기 위함
		· 요청길이제한 : POST는 본문(body)에 데이터를 담기에 길이제한이 없음
	*/
	

	@PostMapping(value="/sendCode.do", produces="application/json")
	public ResponseEntity<Map<String, Object>> sendCode(@RequestBody Map<String, Object> params){
		return userService.sendCode(params);
	}
	
	@PostMapping(value="/signup.do")
	public void signup(HttpServletRequest request, HttpServletResponse response ) {
		userService.signup(request, response);
	}
	

	@GetMapping("/login.page")
	public String loginPage() {
		return "user/login";
	}
	
	
	@GetMapping("/mypage.page")
	public String myPage() {
		return "user/mypage";
	}
	
	@GetMapping("/modifyMypage.page")
	public String getMypage() {
		return "user/modifyMypage";
	}
	
	@PostMapping("/modifyMypage.do")
	public String modifyMypage(HttpServletRequest request) {
		
		userService.modifyMember(request);
		//MemberDto member= userService.getMemberById(request.getParameter("userId"));
		
		return "user/mypage";
	}
	
	@GetMapping("/modifyPw.page")
	public String modifyPwPage() {
		return "user/modifyPw";
	}
	
	@PostMapping("/modifyPw.do")
	public String modifyPw(HttpServletRequest request, HttpServletResponse response) {
		
		userService.modifyPw(request, response);
		return "user/modifyPw";
	}

	
	
	
}
