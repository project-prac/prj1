package com.sd.hotel.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sd.hotel.dto.ReservationDto;


public interface AdminReserveService {
	
	List<ReservationDto> getResByDate(LocalDate today);
	List<ReservationDto> getResAll();
}
