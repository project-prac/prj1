package com.sd.hotel.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sd.hotel.dto.MemberDto;

@Mapper //Mapping 파일에 기재된 SQL을 호출하기 위한 인터페이스
public interface MemberMapper {
	
	MemberDto getMemberByMap(Map<String, Object> map); 
	int insertMember(MemberDto member);
	MemberDto getMemberById(String userId);

	
	int updateMember(MemberDto member);
	int updatePw(String userId, String newpw);
	
}
