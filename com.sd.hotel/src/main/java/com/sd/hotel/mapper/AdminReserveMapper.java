package com.sd.hotel.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sd.hotel.dto.PaymentDto;
import com.sd.hotel.dto.ReservationDto;
import com.sd.hotel.dto.RoomDetailDto;
import com.sd.hotel.dto.RoomDto;
import com.sd.hotel.dto.RoomImgDto;

@Mapper
public interface AdminReserveMapper {

	List<ReservationDto> getResByDate(LocalDate today);
	
	List<ReservationDto> getResAll(String date);
}
