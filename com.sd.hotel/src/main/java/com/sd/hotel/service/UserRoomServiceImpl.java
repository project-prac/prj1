package com.sd.hotel.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sd.hotel.dto.PaymentDto;
import com.sd.hotel.dto.ReservationDto;
import com.sd.hotel.dto.RoomDto;
import com.sd.hotel.dto.RoomSearchDto;
import com.sd.hotel.mapper.PayMapper;
import com.sd.hotel.mapper.RoomMapper;
import com.sd.hotel.mapper.SearchMapper;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserRoomServiceImpl implements UserRoomService {

	private final SearchMapper searchMapper;
	private final RoomMapper roomMapper;
	private final PayMapper  payMapper;
	
	@Override
	public List<RoomDto> getSearchResult(String checkInDate, String checkOutDate, int guestCount) {

		List<RoomDto> roomList = searchMapper.getSearchResult(checkInDate, checkOutDate, guestCount);
		System.out.println("roomList :::" + roomList);
		return roomList;
	}
	
	
	// 예약테이블에 추가(reserved)
	@Override
	public int roomReserve(ReservationDto reservationDto) {
		
		reservationDto.setStatus("reserved");
		
		System.out.println("reservationDto ::::" + reservationDto);
		
		return roomMapper.roomReserve(reservationDto);
	}
	
	//객실수 변경
	@Override
	public int roomUpdate(int roomNo) {
		
		return roomMapper.roomUpdate(roomNo);
	}
	
	
	// 사용자의 예약객실 목록 가져오기
	@Override
	public List<ReservationDto> getReservedRoom(int memberNo) {

		
		String reserved = "reserved";
		String completed = "completed";
		List<ReservationDto> reservedRooms = roomMapper.getReservedRoom(memberNo, reserved, completed);
		
		System.out.println("??"+roomMapper.getReservedRoom(memberNo,reserved,completed));
		
		return roomMapper.getReservedRoom(memberNo,reserved,completed);
	}
	
	@Override
	public ReservationDto getReservedRoomDetail(int reservationNo) {

		System.out.println("service단"+reservationNo);
		System.out.println("SERVICE단"+roomMapper.getReservedRoomDetail(reservationNo));
		
		return roomMapper.getReservedRoomDetail(reservationNo);
	}
	
	
	
	// 예약/결제 내역 삭제
	@Override
	public int cancelReservation(int reservationNo, int roomNo) {

		String status = "canceled";
		System.out.println("예약 결제 내역 삭제 service:"+roomMapper.cancelReservation(reservationNo, status));
		
		try {

			int updateResTable = roomMapper.cancelReservation(reservationNo, status);
			int updateRoomTable = roomMapper.roomRemoveUpdate(roomNo);
			
			if(updateResTable > 0 && updateRoomTable > 0) {
				return 1;
			}else {
				 throw new RuntimeException("테이블 업데이트 실패: updateResTable=" + updateResTable + ", updateRoomTable=" + updateRoomTable);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0; 
		}
		
	}
	
	@Override
	public int roomPay(PaymentDto paymentDto) {

		return payMapper.roomPay(paymentDto);
	}
	
	
	@Override
	public int statusCompleted(int reservationNo, String status) {

		
		return payMapper.statusCompleted(reservationNo, status);
	}
}




















