package com.sd.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sd.hotel.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/hotel/room")
@Controller
public class UserRoomController {

	@GetMapping("/room.page")
	public String roomPage() {
		return"hotel/room/room";
	}
	
}
