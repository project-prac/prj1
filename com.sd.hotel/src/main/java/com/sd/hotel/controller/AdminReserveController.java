package com.sd.hotel.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sd.hotel.dto.ReservationDto;
import com.sd.hotel.dto.RoomDto;
import com.sd.hotel.service.AdminReserveService;
import com.sd.hotel.service.AdminRoomService;

import lombok.RequiredArgsConstructor;




@RequiredArgsConstructor
@RequestMapping("/admin/reservation")
@Controller
public class AdminReserveController {
	
	private final AdminReserveService ademinReserveService;
	private final AdminRoomService adminRoomService;
	
	
	
	/* 코드 수정 중 */
	
	//오늘체크인페이지 가기
	@GetMapping("/todayCheckIn.page")
	public String goReservationPage() {
		return "admin/reservation/todayCheckIn";
	}
	
	
	@PostMapping("/reservation.do")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> reservationPage() {
		
		LocalDate today = LocalDate.now();
		
		List<ReservationDto> reserves = ademinReserveService.getResByDate(today);
		
		Map<String, Object> response = new HashMap<>();
		response.put("reserves", reserves);
		
		return ResponseEntity.ok(response);
	}
	
	
	//상태가 reserved 말고 completed인 것만 가져오게
	@PostMapping("/todayCheckIn.do")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> todayCheckIn() {
		
		LocalDate today = LocalDate.now();
		
		List<ReservationDto> reserves = ademinReserveService.getResCheckIn(today);
		
		Map<String, Object> response = new HashMap<>();
		response.put("reserves", reserves);
		
		return ResponseEntity.ok(response);
	}
	
	
	// 달력페이지
	@GetMapping("/calendar.page")
	public String goCalendarPage() {
		
		return "admin/reservation/calendar";
	}
	
	
	//예약 정보 가져오기
	
	@PostMapping("/resList.do")
	public ResponseEntity<Map<String, Object>> getResList(@RequestBody Map<String, String> params){
		
		String date = params.get("reservationDate");		
		List<ReservationDto> reserves = ademinReserveService.getResAll(date);
		
		Map<String, Object> response = new HashMap<>();
		response.put("reserves", reserves);
		
		return ResponseEntity.ok(response);
	}
	

	
	// reservedList(예약목록 페이지) 로 넘어가기 (달력쪽에서)
	
	@GetMapping("/reservedList.do")
	public String goReservedListPage(@RequestParam String date,
																		Model model) {
    //System.out.println("Selected date: " + date);
    
    model.addAttribute("date", date);
		return "admin/reservation/reservedList";
	}
	
	
}
