package com.sd.hotel.controller;

import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sd.hotel.dto.RoomDto;
import com.sd.hotel.service.AdminRoomService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/admin/room")
@Controller
public class AdminRoomController {
	
	private final AdminRoomService adminRoomService;
	
	@GetMapping("/roomList.page")
	public String roomListPage() {
		return "admin/room/roomList";
	}
	
	// 객실 목록 불러오기
	@GetMapping("/roomList.do")
	public ResponseEntity<Map<String, Object>> getRoomList() {
		
		List<RoomDto> roomList = adminRoomService.getRoomList();
		
		Map<String, Object> response = new HashMap<>();
		response.put("roomList", roomList);
		return ResponseEntity.ok(response);  
	}

	// 객실 목록 수정하기
	@PostMapping("/roomModify.do")
	public String modifyRoom(HttpServletRequest request, HttpServletResponse response) {

		adminRoomService.modifyRoomInfo(request, response);
		
		return "admin/room/roomList";
	}
	
	//객실 대분류(roomNo :100 200 ..추가)
	@PostMapping("/roomNoRegister.do")
	public String roomNoRegister(HttpServletRequest request, HttpServletResponse response) {
		
		adminRoomService.roomNoRegister(request, response);
		return "admin/room/roomNoRegister";
	}
	
  //객실 유형분류()
	@PostMapping("/roomTypeRegister.do")
	@ResponseBody
	public Map<String, Object> roomTypeRegister(HttpServletRequest request) {
		Map<String, Object> response = new HashMap<>();
    try {
        adminRoomService.roomTypeRegister(request);
        response.put("success", true); // 처리 성공 시
    } catch (Exception e) {
        response.put("success", false); // 처리 실패 시
    }
    return response; // JSON 형식으로 응답
	}
	
}