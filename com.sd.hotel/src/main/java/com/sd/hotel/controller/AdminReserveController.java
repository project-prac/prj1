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
	
	@GetMapping("/reservation.page")
	public String goReservationPage() {
		
		return "admin/reservation/reservation";
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
	
	
	
	
	@GetMapping("/calendar.page")
	public String goCalendarPage() {
		
		return "admin/reservation/calendar";
	}
	
	
	// 객실 정보 가져오기
	
	@PostMapping("/roomList.do")
	public ResponseEntity<Map<String, Object>> getRoomList(){
		
		List<RoomDto> roomList = adminRoomService.getRoomList();
		
		Map<String, Object> response = new HashMap<>();
		response.put("roomList", roomList);
		
		return ResponseEntity.ok(response);
	}
	
	//예약 정보 가져오기
	/*
	@PostMapping("/resList.do")
	public ResponseEntity<Map<String, Object>> getResList(){
		
		//LocalDate today = LocalDate.now();
		
		List<ReservationDto> reserves = ademinReserveService.getResAll();
		List<RoomDto> roomList = adminRoomService.getRoomList();
		
		Map<String, Object> response = new HashMap<>();
		response.put("reserves", reserves);
		response.put("roomList", roomList);
		
		return ResponseEntity.ok(response);
	}*/
	
	//예약 정보 가져오기
	
	@PostMapping("/resList.do")
	public ResponseEntity<Map<String, Object>> getResList(@RequestBody Map<String, String> params){
		
		String reservationDate = params.get("reservationDate");
		System.out.println("reservationDate" +reservationDate);
		
		List<ReservationDto> reserves = ademinReserveService.getResAll(reservationDate);
		//List<RoomDto> roomList = adminRoomService.getRoomList();
		
		Map<String, Object> response = new HashMap<>();
		response.put("reserves", reserves);
		//response.put("roomList", roomList);
		
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping("/reservedList.do")
	public String goReservedListPage(@RequestParam String date,
																		Model model) {
    System.out.println("Selected date: " + date);
    
    
    model.addAttribute("date", date);
		return "admin/reservation/reservedList";
	}
	
	
}
