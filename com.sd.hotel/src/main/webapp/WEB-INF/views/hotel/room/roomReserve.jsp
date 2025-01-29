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
<c:set var="room" value="${roomList}" />
<c:set var="roomInfo" value="${roomInfo}" />
<c:set var="mem" value="${mem}"/>


<div class="sub">
  <div class="sub_in">
    <!-- 검색 Start -->
    <div class="search_area">
      <form id="form_search"
			       action="${contextPath}/hotel/room/roomSearch.do" method="POST">
			  <div class="container">
			    <div id="calendar-container">
			      <label for="checkin">체크인</label> 
			      <input id="checkin" type="text" name="checkIn" placeholder="체크인 날짜"> 
			      <label for="checkout">체크아웃</label>
			      <input id="checkout" type="text" name="checkOut" placeholder="체크아웃 날짜">
			     </div>
			
				    <div class="date-display">
				      <span id="nights">0</span>박
				    </div>
			
				    <div class="dropdown">
				      <button id="guestButton">성인/어린이/유아 선택</button>
				      <div id="guestDropdown" class="dropdown-content">
				        <div>
				          <label for="adults">성인: </label>
				          <button>-</button>
				          <input id="adults" type="number" value="${adults}" name="adults" readonly>
				          <button>+</button>
				        </div>
				        <div>
				          <label for="infants">아동: </label>
				          <button>-</button>
				          <input id="infants" type="number" value="${infants}" name="infants" readonly>
				          <button>+</button>
				        </div>
				        <div>
				        <p>총인원 : </p>
				        </div>
				      </div>
				     </div>
			
			    <div>
			      <button type="submit" id="search">객실 다시 검색</button>
			    </div>
			  </div>
			</form>
			<!-- 검색 End -->
    </div>
    <!-- 객실+예약자 정보 Form -->
    <form id=""
      action="${contextPath}/hotel/room/roomReserve.do"
      method="POST">
      <!-- 검색 객실 정보Start -->
      <div class="roomInfo">
         <p class="title"> 객실 정보</p>
           <input type="hidden" class="inputClass" value="${roomInfo.roomNo}" name="roomNo" readonly/>
           <ul>
              <li>
                <p class="tit">객실명</p>
                <p class="con">
                  <input class="con_input" value="${roomInfo.roomName}" name="roomName" readonly/>
                </p>
              </li>
              <li>
                <p class="tit">체크인 날짜</p>
                <p class="con">
                  <input class="con_input" value="${checkIn}" name="checkInDate" readonly/>
                </p>
              </li>
              <li>
                <p class="tit">체크아웃 날짜</p>
                <p class="con">
                  <input class="con_input" value="${checkOut}" name="checkOutDate" readonly/>
                </p>
              </li>
            </ul>
      </div>
      
      <!-- 검색 객실 정보End -->
      
      <br><br>
      <!-- 예약자 정보Start -->
      <div class="roomInfo">
         <p class="title"> 예약자 정보</p>
         <sec:authentication property="principal"  var="user"/>
         <input type="hidden" class="" value="${mem.memberNo}" name="memberNo">
         <ul>
             <li>
               <p class="tit">이름</p>
               <p class="con">
                 <input class="con_reserve" placeholder="성함을 입력해주세요" value="${user.name}" name="resName" ></input>
               </p>
             </li>
             <li>
               <p class="tit">총 인원</p>
               <p class="con">
                  <input class="con_reserve" value="${guestCount}" name="guestCount" readonly></input>
               </p>
             </li>
             <li>
               <p class="tit">연락처</p>
               <p class="con">
                 <input class="con_reserve" value="${user.tel}" name="resPhone">
               </p>
             </li>
             <li>
               <p class="tit">이메일</p>
               <p class="con">
                 <input class="con_reserve" value="${user.mail}" name="resMail">
               </p>
             </li>
             <li>
               <p class="tit">메모</p>
               <p class="con">
                 <input class="con_reserve" type="text" name="resNote">
               </p>
             </li>
             <li>
               <p class="tit">결제금액</p>
               <p class="con">
                <fmt:formatNumber value="${roomInfo.price}" type="number" groupingUsed="true"/> 원
                 
               </p>
             </li>
          </ul>
         
      </div>
      

      <button type="submit">예약</button>
      <!-- 예약자 정보End -->
      
      <!-- 객실+예약자 정보 Form -->
    </form>
    
    
  </div>
</div>






<script src="js/jquery-3.2.1.min.js"></script>

<script
	src="vendors/bootstrap-datepicker/bootstrap-datetimepicker.min.js"></script>

<script src="${contextPath}/js/roomReserve.js"></script>
<jsp:include page="../../hotel/layout/footer.jsp" />