package com.sd.hotel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sd.hotel.dto.RoomDto;
import com.sd.hotel.dto.RoomSearchDto;

@Mapper
public interface SearchMapper {
	List<RoomDto> getSearchResult( @Param("checkIn") String checkIn, 
      													 @Param("checkOut") String checkOut, 
      													 @Param("guestCount") int guestCount);
}
