package com.sd.hotel.service;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sd.hotel.dto.CustomUserDetails;

import lombok.RequiredArgsConstructor;

/*
  로그인폼에 입력된 ID와 비밀번호 일치하는지 확인후
  정보와 권한이 담긴 객체(UsernamePasswordAuthenticationToken ) 반환함
 */

@Service
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private final CustomUserDetailsService customUserDetailsService;
	private final PasswordEncoder passwordEncoder;


	// 실질적인 인증처리 , 정보 비교 후 객체 반환
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		// 로그인폼에 입력된 사용자정보가져옴
		String memberId = authentication.getName();
		String password = (String) authentication.getCredentials();

		CustomUserDetails user = (CustomUserDetails) customUserDetailsService.loadUserByUsername(memberId);
		// memberID로 DB내 정보조회
		
		//System.out.println("user:"+user);
		
		if (password == null) {
      System.out.println("비밀번호가 제공되지 않았습니다. 기존 인증을 유지합니다.");
      return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
		}
		
		if (!passwordEncoder.matches(password, user.getPassword())) {
			System.out.println("비밀번호가 잘못 되었습니다!.");
			throw new BadCredentialsException("Invalid username or password");
		}
		System.out.println("로그인성공!!!!!!!!!!!");
		//return new UsernamePasswordAuthenticationToken(memberId, password, user.getAuthorities());
		return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
		//UsernamePasswordAuthenticationToken (첫번째 인자 :사용자 객체로 넣어줄것)
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
