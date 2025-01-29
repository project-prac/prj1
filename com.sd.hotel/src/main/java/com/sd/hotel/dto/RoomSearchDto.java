package com.sd.hotel.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*테이블은 존재하지 않는 필터용 DTO*/

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RoomSearchDto {
	
	private String checkIn;    // 체크인 날짜 (yyyy-MM-dd)
  private String checkOut;   // 체크아웃 날짜 (yyyy-MM-dd)
  private int guestCount;   // 인원 수
	
	
	
}
