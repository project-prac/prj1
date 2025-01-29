package com.sd.hotel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sd.hotel.dto.PaymentDto;
import com.sd.hotel.dto.ReservationDto;
import com.sd.hotel.dto.RoomDto;
import com.sd.hotel.dto.RoomSearchDto;

import jakarta.servlet.http.HttpServletRequest;

@Service
public interface UserRoomService {

	List<RoomDto> getSearchResult(String checkInDate, String checkOutDate, int guestCount);
	int roomReserve(ReservationDto reservationDto);
	int roomUpdate(int roomNo);
	
	List<ReservationDto> getReservedRoom(int memberNo);
	ReservationDto getReservedRoomDetail(int reservationNo);
	
	
	int cancelReservation(int reservationNo,int roomNo );
	int roomPay(PaymentDto paymentDto);
	int statusCompleted(int reservationNo, String status);
	
	
	//PaymentDto getPayInfoByResNo(int reservationNo);
	
}
