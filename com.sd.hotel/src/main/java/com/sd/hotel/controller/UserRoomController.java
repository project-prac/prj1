package com.sd.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sd.hotel.service.AdminRoomService;
import com.sd.hotel.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@RequestMapping("/hotel/room")
@Controller
public class UserRoomController {
	
	private final AdminRoomService adminRoomService;

	
	@GetMapping("/room.do")
	public String roomPage(@RequestParam(value="roomNo", defaultValue="101")  Integer roomNo, Model model) {
		/*defaultValue 를 지정해주었느니 required=true 는 딱히 필요가없음*/
		
		model.addAttribute("roomList",adminRoomService.getRoomList());
		model.addAttribute("roomInfo",adminRoomService.getRoombyRoomNo(roomNo));
		model.addAttribute("roomImg",adminRoomService.getRoomImgListByNo(roomNo));
		
		return "hotel/room/room";
	}

	
}
