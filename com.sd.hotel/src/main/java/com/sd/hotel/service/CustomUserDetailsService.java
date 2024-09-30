package com.sd.hotel.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sd.hotel.dto.ConfigAdminDto;
import com.sd.hotel.dto.CustomUserDetails;
import com.sd.hotel.dto.MemberDto;
import com.sd.hotel.mapper.ConfigAdminMapper;
import com.sd.hotel.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
	/*
	 UserDetailsService : 데이터베이스나 외부 시스템에서 사용자의 정보를 조회하여 반환하는데 사용
	*/
	private final MemberMapper memberMapper;
	private final ConfigAdminMapper adminMapper;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

		System.out.println("userId:"+userId);
		
		MemberDto member = memberMapper.getMemberById(userId);
		ConfigAdminDto admin = adminMapper.getAdminById(userId);
		
		System.out.println(admin);
		
		if(admin != null) {
			return new CustomUserDetails(admin);
		}
		
		//UserDto member = memberMapper.getMemberById(userId);
		//UserDetailDto memberdetail = memberMapper.getMemberById(userId);

		if(member != null) {
			return new CustomUserDetails(member, member);
		}
	
		
		
		System.out.println("사용자정보를 찾을 수 없습니다.");
		throw new UsernameNotFoundException("memberId 정보를 찾을 수 없습니다." + userId);
	}


	
}
