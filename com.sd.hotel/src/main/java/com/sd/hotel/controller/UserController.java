package com.sd.hotel.controller;

import java.util.List;
import java.util.Map;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sd.hotel.dto.CustomUserDetails;
import com.sd.hotel.dto.MemberDto;
import com.sd.hotel.dto.ReservationDto;
import com.sd.hotel.service.UserRoomService;
import com.sd.hotel.service.UserService;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {
	
	private final UserService userService;
	private final UserRoomService userRoomService;
	
	
	// 회원가입
	
	@GetMapping("/signup")
	public String signup() {
		return "user/signup";
	}
	
	//@PostMapping(value="/checkId.do", produces="application/json")
	@PostMapping("/check-availability")
	public ResponseEntity<Map<String, Object>> checkId(@RequestBody Map<String, Object> params) {
		return userService.checkId(params);
	}

	/*
		GET 대신 POST사용이유
		· 데이터보안 : 민감한 데이터라 URL에 노출시키지 않기 위함
		· 요청길이제한 : POST는 본문(body)에 데이터를 담기에 길이제한이 없음
	*/
	

	//@PostMapping(value="/sendCode.do", produces="application/json")
	@PostMapping("code")
	public ResponseEntity<Map<String, Object>> sendCode(@RequestBody Map<String, Object> params){
		return userService.sendCode(params);
	}
	
	// 등록
	public void signup(HttpServletRequest request, HttpServletResponse response ) {
		userService.signup(request, response);
	}
	

	@GetMapping("/login")
	public String loginPage() {
		return "user/login";
	}
	
	
	// 마이페이지
	
	@GetMapping("/me")
	public String myPage() {
		return "user/mypage";
	}
	
	@GetMapping("/me/profile")
	public String myPageList() {
		return "user/profile";
	}
	
	@GetMapping("/me/profile/edit")
	public String getMypage() {
		return "user/edit";
	}
	
	@PatchMapping("/me")
	public String modifyMypage(HttpServletRequest request) {
		userService.modifyMember(request);
		//MemberDto member= userService.getMemberById(request.getParameter("userId"));
		return "redirect:/user/me/profile";
	}
	
	@GetMapping("/me/edit/password")
	public String modifyPwPage() {
		return "user/password";
	}
	
	@PatchMapping("/password")
	public String modifyPw(HttpServletRequest request, HttpServletResponse response) {
		
		userService.modifyPw(request, response);
		return "user/modifyPw";
	}
	
	/*마이페이지 내 예약 내역확인*/
	@GetMapping("/myReserve.do")
	public String myReservePage(Authentication authentication,Model model) {
		
		String userID = authentication.getName();
		int memberNo = userService.getMemberNo(userID);
		
		System.out.println(memberNo);
		
		List<ReservationDto> reservedRoom = userRoomService.getReservedRoom(memberNo);
		
		System.out.println("reservedRoom::"+reservedRoom);
		model.addAttribute("reservedRoom",reservedRoom );
		
		
		return "user/myReserve";
	}
	
	
	/*마이페이지 내 예약 상세보기*/
	@PostMapping("/myReserveDetail.do")
	public String getReservedDetail(@RequestParam("reservationNo") int reservationNo,  Model model) {
		
		ReservationDto reservedRoom = userRoomService.getReservedRoomDetail(reservationNo);
		model.addAttribute("reservedRoom",reservedRoom );
		
		return "user/myReserveDetail";
	}
	
	/* ↓ 코드 수정 
	@GetMapping("/reservations/{reservationNo}")
	public String getReservedDetail(@RequestParam("reservationNo") int reservationNo,  Model model) {
		
		ReservationDto reservedRoom = userRoomService.getReservedRoomDetail(reservationNo);
		model.addAttribute("reservedRoom",reservedRoom );
		
		return "user/myReserveDetail";
	}
	
	*/
}
