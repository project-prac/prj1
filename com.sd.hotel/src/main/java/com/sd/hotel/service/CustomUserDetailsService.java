package com.sd.hotel.service;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sd.hotel.dto.CustomUserDetails;
import com.sd.hotel.dto.MemberDto;
import com.sd.hotel.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
	/*
	 UserDetailsService : 데이터베이스나 외부 시스템에서 사용자의 정보를 조회하여 반환하는데 사용
	*/
	private final MemberMapper memberMapper;

	@Override
	public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {

		MemberDto member = memberMapper.getMemberById(memberId);

		if(member != null) {
			return new CustomUserDetails(member);
		}
		System.out.println("사용자정보를 찾을 수 없습니다.");
		throw new UsernameNotFoundException("memberId 정보를 찾을 수 없습니다." + memberId);
	}


	
}
