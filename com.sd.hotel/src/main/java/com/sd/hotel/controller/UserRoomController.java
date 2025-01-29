package com.sd.hotel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sd.hotel.dto.CustomUserDetails;
import com.sd.hotel.dto.MemberDto;
import com.sd.hotel.dto.PaymentDto;
import com.sd.hotel.dto.ReservationDto;
import com.sd.hotel.dto.RoomDto;
import com.sd.hotel.dto.RoomSearchDto;
import com.sd.hotel.service.AdminRoomService;
import com.sd.hotel.service.CustomAuthenticationProvider;
import com.sd.hotel.service.UserRoomService;
import com.sd.hotel.service.UserRoomServiceImpl;
import com.sd.hotel.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@RequestMapping("/hotel/room")
@Controller
public class UserRoomController {
	
	private final AdminRoomService adminRoomService;
	private final UserService userService;
	private final UserRoomService userRoomService;
	private final CustomAuthenticationProvider authenticationProvider;

	
	@GetMapping("/room.do")
	public String roomPage(@RequestParam(value="roomNo", defaultValue="101")  Integer roomNo, Model model) {
		/*defaultValue 를 지정해주었느니 required=true 는 딱히 필요가없음*/
		
		/*
		model.addAttribute("roomList",adminRoomService.getRoomList());
		model.addAttribute("roomInfo",adminRoomService.getRoombyRoomNo(roomNo));
		model.addAttribute("roomImg",adminRoomService.getRoomImgListByNo(roomNo));
		return "hotel/room/room";*/
		
    
    try {
      model.addAttribute("roomList", adminRoomService.getRoomList());
      model.addAttribute("roomInfo", adminRoomService.getRoombyRoomNo(roomNo));
      model.addAttribute("roomImg", adminRoomService.getRoomImgListByNo(roomNo));
    } catch (NumberFormatException e) {
        return "redirect:/error"; 
    }

    return "hotel/room/room";
		
	}
	
	

	@GetMapping("/roomSearch.page")
	public String roomReserch() {
		
		return "hotel/room/roomSearch";
	}
	/*
	@GetMapping("/roomSearchResult.page")
	public String roomReserchResult(Model model) {
		System.out.println("JSP에 전달된 데이터: " + model.getAttribute("searchDto"));
		return "hotel/room/roomSearchResult";
	}
	*/
	
	
	@PostMapping("/roomSearch.do")
	public String getSearchRoom(@RequestParam("checkIn") String checkIn,
															@RequestParam("checkOut") String checkOut,
															@RequestParam("guestCount") int guestCount,
															@RequestParam("adults") int adults,
															@RequestParam("infants") int infants,
															Model model,
															Authentication authentication){
		
		List<RoomDto> roomList = userRoomService.getSearchResult(checkIn,checkOut,guestCount);
		model.addAttribute("roomList", roomList);
		model.addAttribute("checkIn",checkIn);
		model.addAttribute("checkOut",checkOut);
		model.addAttribute("guestCount",guestCount);
		model.addAttribute("adults",adults);
		model.addAttribute("infants",infants);
		
		System.out.println("roomList : "+roomList);
		
	  return "hotel/room/roomSearchResult";
	}

	
	@PostMapping("/roomReserve.page")
	public String reservePage(@RequestParam("roomNo") int roomNo,
											      @RequestParam("checkIn") String checkIn,
											      @RequestParam("checkOut") String checkOut,
														@RequestParam("guestCount") int guestCount,
														@RequestParam("adults") int adults,
														@RequestParam("infants") int infants,
											      Model model,
											      Authentication authentication) {
		
		System.out.println("checkIncheckIncheckIn" + checkIn);
		model.addAttribute("checkIn",checkIn);
		model.addAttribute("checkOut",checkOut);
		model.addAttribute("guestCount",guestCount);
		model.addAttribute("adults",adults);
		model.addAttribute("infants",infants);
    model.addAttribute("roomInfo", adminRoomService.getRoombyRoomNo(roomNo));
    
    
    //System.out.println("authentication::" + authentication);
    //System.out.println(authentication.getName());
    
    String userId = authentication.getName();
    MemberDto mem = userService.getMemberById(userId);
    model.addAttribute("mem", mem);
    
    return "hotel/room/roomReserve";
	}
	
	

	
	
	
	/* 예약하기 */
	@PostMapping("/roomReserve.do")
	public String reserve(@ModelAttribute ReservationDto reservationDto,
												Model model){
		
	
		//예약테이블에 상태 reserved로 추가하고 ,결제 페이지로 보내기
		 userRoomService.roomReserve(reservationDto);

		//예약 내역 가져와서 페이지에 보여줘
		model.addAttribute("roomReserve",reservationDto);
		
		//객실정보 가져와
		int roomNo = reservationDto.getRoomNo();
		RoomDto room = adminRoomService.getRoombyRoomNo(roomNo);
		model.addAttribute("room",room);
		
		return "hotel/room/reservation";
	}
	
	/*예약 내역 결제페이지로 불러오기*/
	@PostMapping("goReservation.do")
	public String getReservedRoom(@RequestParam("reservationNo") int reservationNo,
																Model model){
		
		//예약 내역 가져와서 페이지에 보여줘
		ReservationDto reservedRoom = userRoomService.getReservedRoomDetail(reservationNo);
		model.addAttribute("roomReserve",reservedRoom);
		
		//객실정보 가져와
		int roomNo = reservedRoom.getRoomNo();
		RoomDto room = adminRoomService.getRoombyRoomNo(roomNo);
		model.addAttribute("room",room);
		
		
		return "hotel/room/reservation";
	}
	
	
	
	
	
	/* 결제 완료*/
	@PostMapping("/payCompleted.do")
	public String roomPay (@ModelAttribute ReservationDto reservationDto,
												 @ModelAttribute PaymentDto paymentDto,
												 Model model){
		
		try {
			
			/* payment테이블에 추가 */
			int insertPayment = userRoomService.roomPay(paymentDto);
			
			/* 예약테이블 status completed로 변경 */
			int reservationNo = reservationDto.getReservationNo();
			int roomNo = reservationDto.getRoomNo();
			String status = "completed";
			int updateStatus = userRoomService.statusCompleted(reservationNo, status);
			
			if(insertPayment > 0 && updateStatus > 0) {
				
				ReservationDto reserve = userRoomService.getReservedRoomDetail(reservationNo);
				RoomDto room = adminRoomService.getRoombyRoomNo(roomNo);
				System.out.println("reserve???!!!!"+reserve);
				model.addAttribute("reserve",reserve );
				
				model.addAttribute("room", room);
			
				
			}else {
				throw new RuntimeException("실패: insertPayment=" + insertPayment + ", updateStatus=" + updateStatus);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "hotel/room/payCompleted";
	}
	
	
	
	
	
	/* 예약 취소 */
	@PostMapping("/removeRoom.do")
	public String removeRoom(@RequestParam("reservationNo") int reservationNo,
													 @RequestParam("roomNo") int roomNo,
														RedirectAttributes redirectAttributes) {
		
		int deleteCount = userRoomService.cancelReservation(reservationNo, roomNo);
		
		if(deleteCount > 0) {
			redirectAttributes.addFlashAttribute("deleteMessage", "예약 내역이 삭제되었습니다.");
		}else {
			redirectAttributes.addFlashAttribute("errorMessage", "예약 내역이 삭제되었습니다.");
		}
		
	// 메시지 설정

		return "redirect:/user/myReserve.do";
	}

	
	
	
	
}
