package com.sd.hotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	// 암호화 알고리즘 - 단반향,

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		// 경로에 대한 인가작업
		http.authorizeHttpRequests((auth) -> auth

				.requestMatchers("/admin/**").hasRole("ADMIN")
				.requestMatchers("/user/mypage.page").hasAnyRole("USER","ADMIN")

				.anyRequest().permitAll());

		/*
		 * authenticated() :로그인한 사용자만 접근가능
		 */

		// 로그인페이지 설정
		http.formLogin((auth) -> auth.usernameParameter("userId").passwordParameter("password")
				.loginPage("/user/login.page").permitAll() // 로그인 페이지 모두에게 접근 가능
				.loginProcessingUrl("/user/login.do") // loginProcessingUrl 의 기본값은 /login, login.do명령이 들어오면 스프링시큐리티 실시!
				.defaultSuccessUrl("/", true) // 로그인 성공시 이동할 url
				.successHandler(new SavedRequestAwareAuthenticationSuccessHandler())
				.failureHandler(new CustomFailureHandler())
				.permitAll());

		// 로그아웃 설정
		http.logout((auth) -> auth.logoutUrl("/user/logout").logoutSuccessUrl("/").deleteCookies("JSESSIONID")
				.invalidateHttpSession(true) // 세션 무효화
				.clearAuthentication(true) // 인증 정보 제거
		);

		// ※ csrf : 사이트 위.변조 방지설정
		http.csrf((auth) -> auth.disable());

		http.sessionManagement(
				(auth) -> auth.invalidSessionUrl("/user/login.page").maximumSessions(1).expiredUrl("/user/login.page"));

		return http.build();
	}

}
