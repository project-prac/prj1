package com.sd.hotel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sd.hotel.dto.PaymentDto;
import com.sd.hotel.dto.ReservationDto;
import com.sd.hotel.dto.RoomDetailDto;
import com.sd.hotel.dto.RoomDto;
import com.sd.hotel.dto.RoomImgDto;

@Mapper
public interface PayMapper {

	int roomPay(PaymentDto paymentDto);
	int statusCompleted(int reservationNo, String status);
	//PaymentDto getPayInfoByResNo(int reservationNo); 
}
