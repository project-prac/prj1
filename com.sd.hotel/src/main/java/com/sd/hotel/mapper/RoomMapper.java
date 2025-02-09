package com.sd.hotel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sd.hotel.dto.ReservationDto;
import com.sd.hotel.dto.RoomDetailDto;
import com.sd.hotel.dto.RoomDto;
import com.sd.hotel.dto.RoomImgDto;

@Mapper
public interface RoomMapper {
	List<RoomDto> getRoomList();
	List<RoomDetailDto> getRoomDetailList();
	List<RoomImgDto> getRoomImgList();
	List<RoomImgDto> getRoomImgListByNo(int roomNo);
	
	
	int roomNoRegister(RoomDto room);
	int roomTypeRegister(RoomDto room);
	int roomDetailRegister(RoomDetailDto detailRoom);
  int roomImgRegister(RoomImgDto roomImg);
  //int getDetailCount(int roomNo);
  
  
  int modifyRoomInfo(RoomDto room);
  int modifyRoomDetail(String roomName,int roomNo);
  int delteRoomDetail(int roomNo);
  
  //객실 삭제
  int deleteRoom(int roomNo);
  
  int deleteRoomImg(int roomImgNo);
  
  RoomDto getRoombyRoomNo(int roomNo);
  
  int roomReserve(ReservationDto reservationDto);
  int roomUpdate(int roomNo);
  
  List<ReservationDto>getReservedRoom(int memberNo, String reserved, String completed);
  
  ReservationDto getReservedRoomDetail(int reservationNo);
  
  int cancelReservation( @Param("reservationNo") int reservationNo, 
  		 									 @Param("status") String status );
  
  int roomRemoveUpdate(@Param("roomNo") int reservationNo);
 
 
  
}
