package com.sd.hotel.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.sd.hotel.dto.EmployeeDto;

@Mapper
public interface EmployeeMapper {
	
	//List<String> getIdList();
	String getId();
	int addEmployee(EmployeeDto emp);
	
	EmployeeDto getEmployeeById(String userId);
	int updateEmployee(EmployeeDto emp);
	int updatePw(String userId, String newpw);
}
