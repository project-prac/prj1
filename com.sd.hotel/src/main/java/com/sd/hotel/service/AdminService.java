package com.sd.hotel.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.sd.hotel.dto.EmployeeDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AdminService {
	
	 //List<String getIdList();
	 String getId();
	 void addEmployee(HttpServletRequest request, HttpServletResponse response);
	 
}
