package com.sd.hotel.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RoomInfoDto {
	private int roomTypeNo, roomInfoNo, price, people;
	private String roomInfoName, Info;
}
