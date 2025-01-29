package com.sd.hotel.dto;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RoomDto {
	private int roomNo, depth, price, people, availableRoom,reservedRoom, totalRoom ,guestCount;
	private String roomName, parentName, info;
	private List<RoomImgDto> images;
}
