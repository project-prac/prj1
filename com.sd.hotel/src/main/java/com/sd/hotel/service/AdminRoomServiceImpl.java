package com.sd.hotel.service;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sd.hotel.dto.RoomDetailDto;
import com.sd.hotel.dto.RoomDto;
import com.sd.hotel.dto.RoomImgDto;
import com.sd.hotel.mapper.RoomMapper;
import com.sd.hotel.utils.MyFileUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class AdminRoomServiceImpl implements AdminRoomService {

	private final RoomMapper roomMapper;
	private final MyFileUtils myFileUtils;
	
	public AdminRoomServiceImpl(RoomMapper roomMapper, MyFileUtils myFileUtils) {
		super();
		this.roomMapper = roomMapper;
		this.myFileUtils = myFileUtils;
	}




	@Override
	public List<RoomDto> getRoomList() {

		List<RoomDto> roomList = roomMapper.getRoomList();
		return roomList;
		
	
	}
	
	
	@Override
	public List<RoomDetailDto> getRoomDetailList() {
		List<RoomDetailDto> roomDetailList = roomMapper.getRoomDetailList();
		return roomDetailList;
	}
	
	@Override
	public List<RoomImgDto> getRoomImgList() {
		
		List<RoomImgDto> roomImgList = roomMapper.getRoomImgList();
		return roomImgList;
	}
	
	
	
	@Override
	public int modifyRoomInfo(HttpServletRequest request) {
		
		int roomNo = Integer.parseInt(request.getParameter("roomNo"));
		String roomName = request.getParameter("roomName");
		String info = request.getParameter("info");
		int price = Integer.parseInt( request.getParameter("price"));
		int people = Integer.parseInt(request.getParameter("people"));
		
		RoomDto room = RoomDto.builder()
														.roomNo(roomNo)
														.roomName(roomName)
														.info(info)
														.price(price)
														.people(people)
													.build();
		
		
		int updateRoom = roomMapper.modifyRoomInfo(room);
		
		return updateRoom;
	}
	
	@Override
	public int roomNoRegister(HttpServletRequest request, HttpServletResponse response) {

		int roomNo = Integer.parseInt(request.getParameter("roomNo"));
		String roomName= request.getParameter("roomName");
		int depth= Integer.parseInt(request.getParameter("depth"));
		String parentName= request.getParameter("parentName");
		
		RoomDto room = RoomDto.builder()
														.roomNo(roomNo)
														.roomName(roomName)
														.depth(depth)
														.parentName(parentName)
													.build();
														
		int insertRoom = roomMapper.roomNoRegister(room);
		
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			if(insertRoom == 1) {
				out.println("alert('추가되었습니다.');");
				out.println("location.href='" + request.getContextPath() + "/admin/room/roomList.page';");
			}else {
				out.println("alert('객실추가를 실패했습니다.');");
				out.println("history.back();");
			}
			out.println("</script>");
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return insertRoom;
	}
	
	
	@Override
	public Map<String, Object> roomTypeRegister(MultipartHttpServletRequest request) {
		
		Map<String, Object> responseDetailMap = new HashMap<>();
		
		try {
			
			String roomName= request.getParameter("roomName");
			int depth= Integer.parseInt(request.getParameter("depth"));
			String parentName= request.getParameter("parentName");
			String info = request.getParameter("info");
			int price = Integer.parseInt(request.getParameter("price"));
			int people = Integer.parseInt(request.getParameter("people"));
			int roomNum = Integer.parseInt(request.getParameter("roomNum"));
			
			RoomDto room = RoomDto.builder()
															.roomName(roomName)
															.depth(depth)
															.parentName(parentName)
															.info(info)
															.price(price)
															.people(people)
															.roomNum(roomNum)
														.build();
															
			int insertRoom = roomMapper.roomTypeRegister(room);
			int registeredRoomNo = room.getRoomNo();
			
			if (insertRoom == 1) {
				
				for (int i = 0; i < roomNum; i++) {
					RoomDetailDto detailRoom = RoomDetailDto.builder()
              .roomNo(registeredRoomNo)
              .roomName(roomName)
              .build();
					roomMapper.roomDetailRegister(detailRoom);
				}
				
				System.out.println("hello");
				// 첨부파일
				List<MultipartFile> files = request.getFiles("files");
				
				for (MultipartFile multipartFile : files) {

          if (!multipartFile.isEmpty()) {
              String uploadPath = myFileUtils.getUploadPath();
              File dir = new File(uploadPath);
              if (!dir.exists()) {
                  dir.mkdirs();
              }

              String originalFilename = multipartFile.getOriginalFilename();
              String filesystemName = myFileUtils.getFilesystemName(originalFilename);
              
              
              File file = new File(dir, filesystemName);

              multipartFile.transferTo(file);

              RoomImgDto roomImg = RoomImgDto.builder()
                      .uploadPath(uploadPath)
                      .roomNo(registeredRoomNo)
                      .roomImgName(originalFilename)
                      .filesystemName(filesystemName)
                      .build();
              roomMapper.roomImgRegister(roomImg);  // DB 저장
              
          }
      }
      responseDetailMap.put("success", true);
			}else {
        responseDetailMap.put("success", false);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			responseDetailMap.put("success", false);
      responseDetailMap.put("errorMessage", e.getMessage());
		}
		return responseDetailMap;

			
	}
  

}
