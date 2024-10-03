package com.sd.hotel.service;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sd.hotel.dto.EmployeeDto;
import com.sd.hotel.mapper.EmployeeMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class AdminServiceImpl implements AdminService {
	
	private final EmployeeMapper employeeMapper;
	private final PasswordEncoder passwordEncoder;
	
	public AdminServiceImpl(EmployeeMapper employeeMapper,PasswordEncoder passwordEncoder) {
		super();
		this.employeeMapper = employeeMapper;
		this.passwordEncoder = passwordEncoder;
	}
/*
	@Override
	public List<String> getIdList() {

		List<String> IdList = employeeMapper.getIdList();
		System.out.println(IdList);
		return IdList;
	}*/
	
	
	@Override
	public String getId() {
		
		String getId = employeeMapper.getId();
		System.out.println(getId);
		return getId;
	}
	
	
	@Override
	public void addEmployee(HttpServletRequest request,HttpServletResponse response) {
		
		String userId = request.getParameter("userId");
		String password = passwordEncoder.encode(request.getParameter("password"));
		String name = request.getParameter("name");
		Date birth = Date.valueOf(request.getParameter("birth"));
		String tel = request.getParameter("tel");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String role = request.getParameter("role");
		
		EmployeeDto emp = EmployeeDto.builder()
														.userId(userId)
														.password(password)
														.name(name)
														.birth(birth)
														.tel(tel)
														.gender(gender)
														.email(email)
														
													.build();
		
		int insertCount = employeeMapper.addEmployee(emp);
		
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			
			if(insertCount == 1) {
				out.println("alert('회원추가가 완료되었습니다.')");
				out.print("location.href='"+request.getContextPath()+"/admin/addEmployee.page';");
				
			}else {
				out.println("alert('회원추가가 실패했습니다.');");
				out.println("history.back();");
			}
			out.println("</script>");
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
