<%@page import="com.sd.hotel.dto.RoomDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!-- 캘린더 js -->
<jsp:include page="../../hotel/layout/header.jsp" />
<link rel="stylesheet" href="/resources/hotel/css/roomSearch.css">



<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<c:set var="reserve" value="${roomReserve}" />
<c:set var="room" value="${room}" />



<div class="sub">
  <div class="sub_in">
    <div class="sub_title"> 결제 페이지 </div>
    
    <!-- 객실+예약자 정보 Form -->
    <form id=""
          action="${contextPath}/hotel/room/payCompleted.do"
          method="POST">
      <!-- 객실 정보 Start -->
      <div class="roomInfo">

          <input type="hidden" class="" value="${room.roomNo}" name="roomNo">
          <input type="hidden" class="" value="${reserve.memberNo}" name="memberNo">
          <input type="hidden" class="" value="${reserve.reservationNo}" name="reservationNo">
           <ul>
              <li>
                <p class="tit">객실명</p>
                <p class="con">
                  <input class="con_input" value="${room.roomName}" name="roomName" readonly/>
                </p>
              </li>
              <li>
                <p class="tit">체크인 날짜</p>
                <p class="con">
                  <input class="con_input" value="${reserve.checkInDate}" name="checkInDate" readonly/>
                </p>
              </li>
              <li>
                <p class="tit">체크아웃 날짜</p>
                <p class="con">
                  <input class="con_input" value="${reserve.checkOutDate}" name="checkOutDate" readonly/>
                </p>
              </li>
            </ul>
            <!-- 객실 정보 End -->
            <!-- 예약자 정보 Start -->
	           <ul>
	              <li>
	                <p class="tit">예약자 성함</p>
	                <p class="con">
	                  <input class="con_input" value="${reserve.resName}" name="roomName" readonly/>
	                </p>
	              </li>
	              <li>
	                <p class="tit">예약자 연락처</p>
	                <p class="con">
	                  <input class="con_input" value="${reserve.resPhone}" name="resPhone" readonly/>
	                </p>
	              </li>
	              <li>
	                <p class="tit">예약자 이메일</p>
	                <p class="con">
	                  <input class="con_input" value="${reserve.resMail}" name="resMail" readonly/>
	                </p>
	              </li>
                <li>
                  <p class="tit">결제금액</p>
                  <p class="con">
                    <input class="con_input" value="${room.price}" name="price" readonly/>
                  </p>
                </li>	              
	            </ul>
	            <!-- 예약자 정보 End -->                  
      </div>
      <button type="submit">결제하기</button>
    </form>
    <!-- 객실+예약자 정보 Form -->
    
    
  </div>
</div>






<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="js/jquery-3.2.1.min.js"></script>


<script src="${contextPath}/js/flatpickr.js"></script>
<script src="${contextPath}/js/roomSearch.js"></script>
<jsp:include page="../../hotel/layout/footer.jsp" />