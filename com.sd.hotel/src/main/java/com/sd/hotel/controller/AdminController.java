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
	
	@GetMapping("/adminMypage.page")
	public String getMypage() {
		return "admin/adminMypage";
	}
	/*
	@PostMapping("/modifyAdminPw.do")
	public String modifyAdminPw() {
		
		
		
		return "admin/adminMypage";
	}*/
	
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
	
	@GetMapping("/index.page")
	public String indexPage() {
		return "admin/index";
	}
	
	@GetMapping("/addEmployee.page")
	public String addEmployeePage() {
		return "admin/addEmployee";
	}
	
	@PostMapping("/addEmployee.do")
	public void addEmployee(HttpServletRequest request, HttpServletResponse response) {
		
		adminService.addEmployee(request, response);
	}
	
	/*
	@PostMapping(value="/getId.do", produces="application/json")
	public ResponseEntity<List<String>> getId() {
		List<String> idList = adminService.getIdList();
		return ResponseEntity.ok(idList);
	}
	*/
	@PostMapping(value="/getId.do", produces="application/json")
	public ResponseEntity<Map<String, String>> getId() {
		String getId = adminService.getId();
		Map<String, String> response = new HashMap<>();
		response.put("userId", getId);
		
		return ResponseEntity.ok(response);
	}
	
	
	

	
}
