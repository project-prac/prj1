package com.sd.hotel.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.sd.hotel.dto.CustomUserDetails;
import com.sd.hotel.dto.MemberDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {

	// 가입 및 탈퇴
	ResponseEntity<Map<String, Object>> checkId(Map<String, Object> params);
	ResponseEntity<Map<String, Object>> sendCode(Map<String, Object> params);
	void signup(HttpServletRequest request, HttpServletResponse response);

	//로그인 
	public MemberDto getMemberById(String memberId);
	
	
	// 로그인 사용자 정보 수정
	void modifyMember(HttpServletRequest request);
	int modifyPw(HttpServletRequest request);
	
}
