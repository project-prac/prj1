package com.sd.hotel.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.web.filter.reactive.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	// application.properties 파일의 설정값 저장
	@Value("${service.file.registerRoomUrl}")
	public String UP_DIR;

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/index").setViewName("index");
		registry.addViewController("/admin/index").setViewName("admin/index");
		/*
		 * registry.addViewController("/user/mypage").setViewName("user/mypage");
		 * registry.addViewController("/user/modifyPassword").setViewName(
		 * "user/modifyPassword");
		 * registry.addViewController("/manager").setViewName("manager");
		 * registry.addViewController("/login").setViewName("login/loginPage");
		 * registry.addViewController("/employee/add").setViewName(
		 * "employee/addEmployee");
		 */
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/");

		registry.addResourceHandler(UP_DIR + "**").addResourceLocations("file://" + UP_DIR);
		// registry.addResourceHandler(UP_DIR+"blog/**")
		// .addResourceLocations("file://" + UP_DIR);
	}

	

}