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
public class ReservationDto {
	private int reservationNo, memberNo, roomNo, guestCount;
	private Date reservedDate, checkInDate, checkOutDate;
	private String status, resName ,resPhone, resMail, resNote;
	private RoomDto room;
}
