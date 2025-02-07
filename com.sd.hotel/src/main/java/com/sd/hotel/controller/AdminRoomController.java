package com.sd.hotel.controller;

import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sd.hotel.dto.RoomDetailDto;
import com.sd.hotel.dto.RoomDto;
import com.sd.hotel.dto.RoomImgDto;
import com.sd.hotel.service.AdminRoomService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/admin/room")
@Controller
public class AdminRoomController {

	private final AdminRoomService adminRoomService;

	
	/* 코드 수정 중 */
	
	@GetMapping("/list")
	public String roomListPage() {
		return "admin/room/list";
	}

	// 객실 목록 불러오기
	@GetMapping("/data")
	public ResponseEntity<Map<String, Object>> getRoomList() {

		List<RoomDto> roomList = adminRoomService.getRoomList();
		List<RoomDetailDto> roomDetailList = adminRoomService.getRoomDetailList();
		List<RoomImgDto> roomImgList = adminRoomService.getRoomImgList();

		Map<String, Object> response = new HashMap<>();
		response.put("roomList", roomList);
		response.put("roomImgList", roomImgList);
		response.put("roomDetailList", roomDetailList);

		return ResponseEntity.ok(response);
	}
	
	
	// 객실 대분류(roomNo :100 200 ..추가)
	@PostMapping("/categories")
	public String roomNoRegister(@ModelAttribute RoomDto roomDto, RedirectAttributes redirectAttributes) {

		int registercount = adminRoomService.roomNoRegister(roomDto);
		
		
		if(registercount > 0) {
			redirectAttributes.addFlashAttribute("roomNoSuccess", "객실이 추가 되었습니다.");
		}else {
			redirectAttributes.addFlashAttribute("roomNoFail", "객실추가에 실패했습니다.");
		}
		
		return "redirect:/admin/room/list";
	}

	// 객실 유형분류()
	@PostMapping("/types")
	@ResponseBody
	public Map<String, Object> roomTypeRegister(MultipartHttpServletRequest request) {
		Map<String, Object> response = new HashMap<>();
		try {
			adminRoomService.roomTypeRegister(request);
			response.put("success", true); // 처리 성공 시
		} catch (Exception e) {
			response.put("success", false); // 처리 실패 시
		}
		return response; // JSON 형식으로 응답
	}
	
	
	/* ----  */
	
	

	
	
	//객실목록 (100,101,102.. 불러오기) 
	/*
	@PostMapping("/roomListByCategory.do")
	public ResponseEntity<Map<String, Object>> getRoomLists(){
		
		List<RoomDto> roomList = adminRoomService.getRoomList();
		
		Map<String, Object> response = new HashMap<>();
		response.put("roomList", roomList);
		
		return ResponseEntity.ok(response);
	}
	삭제
	
	*/

	@PutMapping("/{roomNo}")
	public ResponseEntity<Map<String, Object>> modifyRoom(
			@PathVariable int roomNo,
			RoomDto roomDto,
			MultipartHttpServletRequest request) {

		roomDto.setRoomNo(roomNo);
		
		Map<String, Object> response = new HashMap<>();

		try {
			// 객실정보
			boolean infoResult = adminRoomService.modifyRoomInfo(roomDto);

			// 객실 수 -detailRoom 관련해서 추가, 삭제 했었으나 더이상 사용 x
			// boolean numResult = adminRoomService.modifyRoomNum(roomDto);

			// 객실이미지
			boolean imgResult = adminRoomService.modifyRoomImg(request);
			System.out.println(imgResult);

			if (infoResult && imgResult) {
				response.put("success", true);
				return ResponseEntity.ok(response); // 200 OK 응답
			} else {
				response.put("success", false);
				response.put("errorMessage", "에러");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}

		} catch (Exception e) {
			response.put("success", false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}

	}
	
	
	// 객실 삭제
	@DeleteMapping("/delete/{roomNo}")
	public ResponseEntity<Map<String, Object>> delteRoom(@PathVariable int roomNo){
		
		
		System.out.println(roomNo);
		Map<String, Object> response = new HashMap<>();
		
		int deleteCount = adminRoomService.deleteRoom(roomNo);
		
		
		if(deleteCount > 0) {
			
			response.put("status", "success");
      response.put("message", "객실이 성공적으로 삭제되었습니다.");
			return ResponseEntity.ok(response);
			
		}else {
			
			response.put("status", "fail");
      response.put("message", "객실 삭제에 실패했습니다.");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		
	}
	
	
}
