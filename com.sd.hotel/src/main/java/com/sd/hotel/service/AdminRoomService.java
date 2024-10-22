package com.sd.hotel.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sd.hotel.dto.RoomDetailDto;
import com.sd.hotel.dto.RoomDto;
import com.sd.hotel.dto.RoomImgDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public interface AdminRoomService {
	
	List<RoomDto> getRoomList();
	List<RoomDetailDto> getRoomDetailList();
	List<RoomImgDto> getRoomImgList();
	
	int roomNoRegister(HttpServletRequest request, HttpServletResponse response);
	Map<String, Object> roomTypeRegister(MultipartHttpServletRequest request);
	
	
	//수정
	Map<String, Object> modifyRoomInfo(MultipartHttpServletRequest request);
	
	
	Map<String, Object> modifyRoomNum(MultipartHttpServletRequest request);
}
