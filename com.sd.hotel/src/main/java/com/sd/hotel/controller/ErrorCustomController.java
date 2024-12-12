package com.sd.hotel.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ErrorCustomController implements ErrorController {


	@GetMapping(value = "/error")
	public String handleError(HttpServletRequest request, Model model) {

		//System.out.println("hi");
		// 에러코드 획득
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		// └ 에러의 상태코드 가져옴
		//System.out.println(status.toString());
		// 에러코드 상태 정보
		HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));
		// └ 가져온 상태 코드로 HttpStatus 객체를 생성

		if (status != null) {
			int statusCode = Integer.valueOf(status.toString());

			// 404
			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				// System.out.println("404");
				model.addAttribute("code", status.toString());
				model.addAttribute("msg", httpStatus.toString());
				model.addAttribute("timestamp", new Date());
				return "error";
			}
		}
		return "error/error";
	}

}

/*추후수정 예정*/
