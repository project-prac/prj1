package com.sd.hotel.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyFileUtils {
	
	@Value("${service.file.registerRoomUrl}")
  public String UP_DIR;
	
	//현재 날짜
	public static final LocalDate TODAY = LocalDate.now();
	
  // 객실등록이미지 경로 반환
  public String getUploadPath() {
    return UP_DIR+"registerRoomListImg" + DateTimeFormatter.ofPattern("/yyyy/MM/dd").format(TODAY);
  }
	
  //저장될 파일명 반환
  public String getFilesystemName(String originalFilename) {
    String extName = null;
    if(originalFilename.endsWith(".tar.gz")) {
      extName = ".tar.gz";
    } else {
      extName = originalFilename.substring(originalFilename.lastIndexOf("."));
    }
    return UUID.randomUUID().toString().replace("-", "") + extName;
  }
  
  // 임시 파일 경로 반환
  public String getTempPath() {
    return UP_DIR+"/temporary";
  }
  
  // 임시 파일 이름 반환 (확장자 제외)
  public String getTempFilename() {
    return System.currentTimeMillis() + "";
  }
  
  /*
  // 블로그 작성시 사용된 이미지가 저장될 경로 반환하기
  public String getBlogImageUploadPath() {
    return UP_DIR+"/blog" + DateTimeFormatter.ofPattern("/yyyy/MM/dd").format(TODAY);
  }
  
  // 블로그 이미지가 저장된 어제 경로를 반환
  public String getBlogImageUploadPathInYesterday() {
    return UP_DIR+"/blog" + DateTimeFormatter.ofPattern("/yyyy/MM/dd").format(TODAY.minusDays(1));
  }
  */
}
