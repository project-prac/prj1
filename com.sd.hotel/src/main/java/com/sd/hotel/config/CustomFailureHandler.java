package com.sd.hotel.config;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/*
 * 로그인 실패처리
 * */

public class CustomFailureHandler extends SimpleUrlAuthenticationFailureHandler{

	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
   
		String errorMessage;
		//아이디 가져오기
		String userId = request.getParameter("userId");
		
		if (exception instanceof BadCredentialsException) {
      // 잘못된 사용자명 또는 비밀번호
			errorMessage = "아이디 또는 비밀번호를 확인해주세요.";
		} else if(exception instanceof UsernameNotFoundException) {
			errorMessage = "존재하지 않는 사용자입니다.";
		} else {
			errorMessage = "로그인에 실패했습니다. 관리자에게 문의하세요.";
		}
		
		
		//errorMessage= URLEncoder.encode(errorMessage,"UTF-8");//한글 인코딩 깨지는 문제 방지
		userId = URLEncoder.encode(userId,"UTF-8");
		
		request.getSession().setAttribute("userId", userId);
		request.getSession().setAttribute("errorMessage", errorMessage);
		
		/*
		System.out.println("세션?"+request.getSession().getAttribute("memberId"));
		System.out.println(errorMessage);
		System.out.println("에러?"+request.getSession().getAttribute("errorMessage"));
		*/
		
		//로그인 페이지로 다시 redirect
    //setDefaultFailureUrl("/user/login.page?error=true&exception=" + errorMessage);
		setDefaultFailureUrl("/user/login.page");
		super.onAuthenticationFailure(request, response, exception);
	}
	
}
