package com.sd.hotel.service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sd.hotel.dto.RoomDetailDto;
import com.sd.hotel.dto.RoomDto;
import com.sd.hotel.mapper.RoomMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class AdminRoomServiceImpl implements AdminRoomService {

	private final RoomMapper roomMapper;
	
	public AdminRoomServiceImpl(RoomMapper roomMapper) {
		super();
		this.roomMapper = roomMapper;
	}




	@Override
	public List<RoomDto> getRoomList() {

		List<RoomDto> roomList = roomMapper.getRoomList();
		return roomList;
		
	
	}
	
	
	@Override
	public int modifyRoomInfo(HttpServletRequest request, HttpServletResponse response) {
		
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
		
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			if(updateRoom == 1) {
				out.println("alert('수정되었습니다.');");
				out.println("location.href='" + request.getContextPath() + "/admin/room/roomList.page';");
			}else {
				out.println("alert('수정을 실패했습니다.');");
				out.println("history.back();");
			}
			out.println("</script>");
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
	public Map<String, Object> roomTypeRegister(HttpServletRequest request) {
		
		Map<String, Object> responseDetailMap = new HashMap<>();
				
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
		
		System.out.println("roomDto의roomNo:"+room.getRoomNo());
		
		int roomNo2 = room.getRoomNo();
		 //detail 추가 로직
		

		if (insertRoom == 1) {
			for (int i = 0; i < roomNum; i++) {
				System.out.println("for문시작");
				RoomDetailDto detailRoom = RoomDetailDto.builder()
        																				.roomNo(roomNo2)
                                                .roomName(roomName)
                                                .build();
        
        try {
        	roomMapper.roomDetailRegister(detailRoom);
          System.out.println(detailRoom);
		    } catch (Exception e) {
		        e.printStackTrace(); 
		    }
			}
			
			responseDetailMap.put("success", true);
			
		}else {
			responseDetailMap.put("success", false);
		}

		return responseDetailMap;
	}
  

}
