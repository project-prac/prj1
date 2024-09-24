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
public class MemberDto {
	private int memberNo;
	private String memberId, name, password, tel, gender, memberEmail, role;
	private Date birth;
}
