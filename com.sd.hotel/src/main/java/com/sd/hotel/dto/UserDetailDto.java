package com.sd.hotel.dto;

import java.sql.Date;

public interface UserDetailDto {
  String getEmail();
  String getGender();
  String getTel();
  Date getBirth();
}
