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
	
	int roomNoRegister(RoomDto roomDto);
	Map<String, Object> roomTypeRegister(MultipartHttpServletRequest request);
	
	
	//수정
	boolean modifyRoomInfo(RoomDto roomDto);
	//boolean modifyRoomNum(RoomDto roomDto);
	boolean modifyRoomImg(MultipartHttpServletRequest request);
	
	
	RoomDto getRoombyRoomNo(int roomNo);
	List<RoomImgDto> getRoomImgListByNo(int roomNo);
	
	//객실 삭제
	int deleteRoom(int roomNo);
}
