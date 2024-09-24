package com.sd.hotel.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomSuccessHandler implements AuthenticationSuccessHandler{
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {
	
	// SavedRequest를 통해 원래의 요청 URL을 가져옴
    SavedRequest savedRequest = (SavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");

    String redirectUrl = savedRequest != null ? savedRequest.getRedirectUrl() : "/";
    
    // 리다이렉트 URL이 특정 조건을 만족하는 경우 해당 페이지로 이동
    if (redirectUrl.contains("mypage.page")) {
        response.sendRedirect("/user/mypage.page");
    } else {
        response.sendRedirect("/");
    }
	}
}
