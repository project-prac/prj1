package com.sd.hotel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sd.hotel.service.AdminService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminController {

	private final AdminService adminService;
	
	/* 코드 수정 중 */
	
	@GetMapping("/index")
	public String indexPage() {
		return "admin/index";
	}
	
	@GetMapping("/mypage")
	public String getMypage() {
		return "admin/mypage";
	}
	
	
	//직원추가 
	@GetMapping("/employees/new")
	public String addEmployeePage() {
		return "admin/employees/new";
	}
	
	@PostMapping("/employees")
	public void addEmployee(HttpServletRequest request, HttpServletResponse response) {
		adminService.addEmployee(request, response);
	}
	
	@GetMapping(value="/getId", produces="application/json")
	public ResponseEntity<Map<String, String>> getId() {
		String getId = adminService.getId();
		Map<String, String> response = new HashMap<>();
		response.put("userId", getId);
		
		return ResponseEntity.ok(response);
	}
	
	
	/* ------ */
	
	
	
	
	


	
	@PostMapping("modifyAdminPw.do")
	public Map<String, Object> modifyFirstPw(HttpServletRequest request, HttpServletResponse response){
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			adminService.
			response.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("success", false);

		}
		
		return response;
	}
	


	
	
	

	
}
