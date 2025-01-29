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
public class RoomImgDto {
	private int roomImgNo, roomNo;
	private String roomImgName, uploadPath, filesystemName;
	private transient long size; // DB에 매핑되지 않는 필드
}
