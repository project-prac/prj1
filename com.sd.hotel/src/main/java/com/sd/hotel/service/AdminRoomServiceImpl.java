package com.sd.hotel.service;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

		for (RoomImgDto img : roomImgList) {
			File file = new File(img.getUploadPath());
			if (file.exists()) {
				img.setSize(file.length());
			} else {
				img.setSize(0);
			}
		}

		return roomImgList;
	}

	@Override
	public int roomNoRegister(HttpServletRequest request, HttpServletResponse response) {

		int roomNo = Integer.parseInt(request.getParameter("roomNo"));
		String roomName = request.getParameter("roomName");
		int depth = Integer.parseInt(request.getParameter("depth"));
		String parentName = request.getParameter("parentName");

		RoomDto room = RoomDto.builder().roomNo(roomNo).roomName(roomName).depth(depth).parentName(parentName).build();

		int insertRoom = roomMapper.roomNoRegister(room);

		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			if (insertRoom == 1) {
				out.println("alert('추가되었습니다.');");
				out.println("location.href='" + request.getContextPath() + "/admin/room/roomList.page';");
			} else {
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

			String roomName = request.getParameter("roomName");
			int depth = Integer.parseInt(request.getParameter("depth"));
			String parentName = request.getParameter("parentName");
			String info = request.getParameter("info");
			int price = Integer.parseInt(request.getParameter("price"));
			int people = Integer.parseInt(request.getParameter("people"));
			int roomNum = Integer.parseInt(request.getParameter("roomNum"));

			RoomDto room = RoomDto.builder().roomName(roomName).depth(depth).parentName(parentName).info(info).price(price)
					.people(people).roomNum(roomNum).build();

			int insertRoom = roomMapper.roomTypeRegister(room);
			int registeredRoomNo = room.getRoomNo();

			if (insertRoom == 1) {

				for (int i = 0; i < roomNum; i++) {
					RoomDetailDto detailRoom = RoomDetailDto.builder().roomNo(registeredRoomNo).roomName(roomName).build();
					roomMapper.roomDetailRegister(detailRoom);
				}

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

						RoomImgDto roomImg = RoomImgDto.builder().uploadPath(uploadPath).roomNo(registeredRoomNo)
								.roomImgName(originalFilename).filesystemName(filesystemName).build();
						roomMapper.roomImgRegister(roomImg); // DB 저장

					}
				}
				responseDetailMap.put("success", true);
			} else {
				responseDetailMap.put("success", false);
			}

		} catch (Exception e) {
			e.printStackTrace();
			responseDetailMap.put("success", false);
			responseDetailMap.put("errorMessage", e.getMessage());
		}
		return responseDetailMap;

	}

	

	@Override
	public boolean modifyRoomInfo(MultipartHttpServletRequest request) {

		try {
			int roomNo = Integer.parseInt(request.getParameter("roomNo"));
			String roomName = request.getParameter("roomName");
			String info = request.getParameter("info");
			int price = Integer.parseInt(request.getParameter("price"));
			int people = Integer.parseInt(request.getParameter("people"));

			RoomDto room = RoomDto.builder()
															.roomNo(roomNo)
															.roomName(roomName)
															.info(info)
															.price(price)
															.people(people)
														.build();
			
			int updateRoomInfo =  roomMapper.modifyRoomInfo(room);
			return updateRoomInfo > 0;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
		
		
		
	}

	@Override
	@Transactional
	public boolean modifyRoomNum(MultipartHttpServletRequest request) {
		
		
		try {
			
			int roomNo = Integer.parseInt(request.getParameter("roomNo"));
			int originRoomNum = Integer.parseInt(request.getParameter("originNum"));
			int roomNum = Integer.parseInt(request.getParameter("roomNum"));
			String roomName = request.getParameter("roomName");
			
			if (originRoomNum > roomNum) {

				for (int i = 0; i < originRoomNum - roomNum; i++) {
					roomMapper.delteRoomDetail(roomNo);
				}

			} else if (originRoomNum < roomNum) {
				for (int i = 0; i < roomNum - originRoomNum; i++) {

					RoomDetailDto detailRoom = RoomDetailDto.builder().roomNo(roomNo).roomName(roomName).build();
					roomMapper.roomDetailRegister(detailRoom);

				}

			} else {
				roomMapper.modifyRoomDetail(roomName, roomNo);
			}
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	@Transactional
	public boolean modifyRoomImg(MultipartHttpServletRequest request) {

		try {
			
			int roomNo = Integer.parseInt(request.getParameter("roomNo"));

			// DB에 저장된 기존 이미지 목록 가져오기
			List<RoomImgDto> roomImgList = roomMapper.getRoomImgListByNo(roomNo);
			
			// DB에 있는 roomImgNo 리스트 생성
			List<Integer> dbImgNos = roomImgList.stream().map(RoomImgDto::getRoomImgNo).collect(Collectors.toList());
			
			
			// 남아있는 roomImgNo 리스트
			String[] clientImgNoStrings = request.getParameterValues("clientImgNos"); 
			
	    List<Integer> clientImgNos = (clientImgNoStrings != null && clientImgNoStrings.length > 0 && !clientImgNoStrings[0].isEmpty())
          ? Arrays.stream(clientImgNoStrings[0].split(","))
                  .filter(str -> !str.isEmpty()) // 빈 문자열 제거
                  .map(Integer::parseInt)
                  .collect(Collectors.toList())
          : new ArrayList<>(); // 비어있을 경우 빈 리스트로 초기화

			
			
			// 삭제할 이미지 구분 (DB에는 있지만 클라이언트에서 삭제된 이미지)
			
			List<Integer> deleteImgNos = dbImgNos.stream().filter(no -> !clientImgNos.contains(no))
					.collect(Collectors.toList());
		

			System.out.println("deleteImgNos::" +deleteImgNos);
			
			// 이미지 삭제
			for (int roomImgNo : deleteImgNos) {
				roomMapper.deleteRoomImg(roomImgNo);
			}

			// 새로 추가된 파일 리스트
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

					RoomImgDto roomImg = RoomImgDto.builder().uploadPath(uploadPath).roomNo(roomNo).roomImgName(originalFilename)
							.filesystemName(filesystemName).build();
					roomMapper.roomImgRegister(roomImg); // DB 저장

				}

			}
			
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}


	}
	
	
	@Override
	public RoomDto getRoombyRoomNo(int roomNo) {
		return roomMapper.getRoombyRoomNo(roomNo);
	}
	
	@Override
	public List<RoomImgDto> getRoomImgListByNo(int roomNo) {
		return roomMapper.getRoomImgListByNo(roomNo);
	}
	

}
