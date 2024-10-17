package com.sd.hotel.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sd.hotel.dto.RoomDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public interface AdminRoomService {
	
	List<RoomDto> getRoomList();
	int modifyRoomInfo(HttpServletRequest request, HttpServletResponse response );
	int roomNoRegister(HttpServletRequest request, HttpServletResponse response);
	Map<String, Object> roomTypeRegister(HttpServletRequest request);
	//int roomDetailRegister();
}
