package com.sd.hotel.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sd.hotel.dto.ConfigAdminDto;

@Mapper
public interface ConfigAdminMapper {
	ConfigAdminDto getAdminById(String userId);
	int updatePw(String userId);
}
