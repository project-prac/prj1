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
public class PaymentDto {
	private int paymentNo, memberNo, reservationNo, paymentAmount;
	private Date paymentDate;
	private String paymentMethod, paymentStatus;
}
