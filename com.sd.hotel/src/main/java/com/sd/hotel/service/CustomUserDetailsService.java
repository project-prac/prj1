package com.sd.hotel.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sd.hotel.dto.ConfigAdminDto;
import com.sd.hotel.dto.CustomUserDetails;
import com.sd.hotel.dto.EmployeeDto;
import com.sd.hotel.dto.MemberDto;
import com.sd.hotel.mapper.ConfigAdminMapper;
import com.sd.hotel.mapper.EmployeeMapper;
import com.sd.hotel.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	/*
	 * UserDetailsService : 데이터베이스나 외부 시스템에서 사용자의 정보를 조회하여 반환하는데 사용
	 */
	private final MemberMapper memberMapper;
	private final ConfigAdminMapper adminMapper;
	private final EmployeeMapper employeeMapper;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

		System.out.println("userId:" + userId);
		/*
		MemberDto member = memberMapper.getMemberById(userId);
		ConfigAdminDto admin = adminMapper.getAdminById(userId);
		EmployeeDto emp = employeeMapper.getEmployeeById(userId);

		System.out.println(admin);

		if (admin != null) {
			return new CustomUserDetails(admin);
		}

		// UserDto member = memberMapper.getMemberById(userId);
		// UserDetailDto memberdetail = memberMapper.getMemberById(userId);*/

		// 코드 리팩토링
		CustomUserDetails userDetails = getUserDetails(userId);

		if(userDetails != null) {
			return userDetails;
		}


		System.out.println("사용자정보를 찾을 수 없습니다.");
		throw new UsernameNotFoundException("memberId 정보를 찾을 수 없습니다." + userId);
	}

	private CustomUserDetails getUserDetails(String userId) {

		ConfigAdminDto admin = adminMapper.getAdminById(userId);
		if (admin != null) {
			return new CustomUserDetails(admin);
		}
		
    MemberDto member = memberMapper.getMemberById(userId);
    if (member != null) {
        return new CustomUserDetails(member, member);
    }

    EmployeeDto emp = employeeMapper.getEmployeeById(userId);
    if (emp != null) {
        return new CustomUserDetails(emp, emp);
    }
    
    return null;  // 아무 정보도 찾을 수 없으면 null 반환

	}

}
