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
public class MemberDto implements UserDto, UserDetailDto{
	
	private int memberNo;
	private String userId, name, password, tel, gender, email, role;
	private Date birth;
	
	
	
}
