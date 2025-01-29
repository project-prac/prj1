package com.sd.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/admin/reservation")
@Controller
public class AdminReserveController {

	@GetMapping("/reservation.page")
	public String reservationPage() {
		
		return "admin/reservation/reservation";
	}
	
}
