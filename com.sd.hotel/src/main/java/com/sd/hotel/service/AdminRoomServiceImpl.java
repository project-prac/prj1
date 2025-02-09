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

import javax.management.RuntimeErrorException;

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
	public int roomNoRegister(RoomDto roomDto) {

		try {
			
			int insertRoom = roomMapper.roomNoRegister(roomDto);

			if(insertRoom > 0) {
				return 1;
			}else {
				throw new RuntimeException("실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
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
			int guestCount = Integer.parseInt(request.getParameter("guestCount"));
			int totalRoom = Integer.parseInt(request.getParameter("totalRoom"));

			
			RoomDto room = RoomDto.builder().roomName(roomName).depth(depth).parentName(parentName).info(info).price(price)
					.guestCount(guestCount).totalRoom(totalRoom).availableRoom(totalRoom).build();

			int insertRoom = roomMapper.roomTypeRegister(room);
			int registeredRoomNo = room.getRoomNo();

			if (insertRoom == 1) {
					
				/*
				for (int i = 0; i < totalRoom; i++) {
					RoomDetailDto detailRoom = RoomDetailDto.builder().roomNo(registeredRoomNo).roomName(roomName).build();
					roomMapper.roomDetailRegister(detailRoom);
				}*/

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
	public boolean modifyRoomInfo(RoomDto roomDto) {
		
		try {

			int updateRoomInfo =  roomMapper.modifyRoomInfo(roomDto);
			return updateRoomInfo > 0;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	//detailRoom 테이블 사용 안함 - 필요없는 로직
	/*
	@Override
	@Transactional
	public boolean modifyRoomNum(RoomDto roomDto) {
		
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
*/
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
	
	//객실 삭제
	@Override
	public int deleteRoom(int roomNo) {

		
		
		try {
			int deletecount = roomMapper.deleteRoom(roomNo);
			
			if(deletecount > 0) {
				return 1;
			}else {
				throw new RuntimeException("Fail");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
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
