package com.sd.hotel.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sd.hotel.dto.ReservationDto;
import com.sd.hotel.mapper.AdminReserveMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminReserveServiceImpl implements AdminReserveService {

	private final AdminReserveMapper adminReserveMapper;
	

	
	@Override
	public List<ReservationDto> getResByDate(LocalDate today) {
		
		
		
		List<ReservationDto> reserves = adminReserveMapper.getResByDate(today);
		System.out.println( "service reserves:"+reserves);
		
		
		return  adminReserveMapper.getResByDate(today);
	}
	
	@Override
	public List<ReservationDto> getResAll() {
		
		
		
		return adminReserveMapper.getResAll();
	}
}
