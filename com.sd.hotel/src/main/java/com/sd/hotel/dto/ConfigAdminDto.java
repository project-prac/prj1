package com.sd.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ConfigAdminDto implements UserDto{
	private int adminNo ;
	private String userId, name, password, role;
}
