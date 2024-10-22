package com.sd.hotel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sd.hotel.dto.RoomDetailDto;
import com.sd.hotel.dto.RoomDto;
import com.sd.hotel.dto.RoomImgDto;

@Mapper
public interface RoomMapper {
	List<RoomDto> getRoomList();
	List<RoomDetailDto> getRoomDetailList();
	List<RoomImgDto> getRoomImgList();
	
	int roomNoRegister(RoomDto room);
	int roomTypeRegister(RoomDto room);
	int roomDetailRegister(RoomDetailDto detailRoom);
  int roomImgRegister(RoomImgDto roomImg);
  //int getDetailCount(int roomNo);
  
  
  int modifyRoomInfo(RoomDto room);
  int modifyRoomDetail(String roomName,int roomNo);
  int delteRoomDetail(int roomNo,int deleteCount);
}
