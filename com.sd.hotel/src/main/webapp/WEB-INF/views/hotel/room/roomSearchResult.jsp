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
<link rel="stylesheet" href="/resources/hotel/css/index.css">

<link rel="stylesheet" href="/resources/hotel/css/roomSearch.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />


<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>

<c:set var="room" value="${roomList}" />



<div class="sub">
  <div class="sub_in searchResult">
    
    <!-- 검색 Start -->
    <div class="search_area">
      <form id="form_search"
            action="${contextPath}/hotel/room/roomSearch.do" method="POST">
			  <div class="container">
			    <div id="calendar-container">
			      <label for="checkin">체크인</label> 
			      <input id="checkin" type="text"name="checkIn"placeholder="체크인 날짜" value="${checkIn}"> 
			      <label for="checkout">체크아웃</label>
			      <input id="checkout" type="text"name="checkOut" placeholder="체크아웃 날짜" value="${checkOut}">
			    </div>
			
			    <div class="date-display">
			      <span id="nights">0</span>박
			    </div>
			
			    <div class="dropdown">
			      <button id="guestButton">성인/어린이/유아 선택</button>
			
			      <div id="guestDropdown" class="dropdown-content">
			        <div>
			          <label for="adults">성인: </label>
			          <button type="button">-</button>
			          <input id="adults" type="number" value="${adults}" name="adults" readonly>
			          <button type="button">+</button>
			        </div>
			        <div>
			          <label for="infants">아동: </label>
			          <button type="button">-</button>
			          <input id="infants" type="number" value="${infants}" name="infants" readonly>
			          <button type="button">+</button>
			        </div>
			        <input id="guestCount" type="hidden" name="guestCount">
			
			      </div>
			    </div>
			
			    <div>
			      <button type="submit" id="search">다시 검색</button>
			    </div>
			  </div>
			</form>
    
    </div>
    <!-- 검색 End -->
    
    <!-- 검색결과 Start  -->
      <!--================Booking Tabel Area =================-->
      <div class="sub_title">검색결과</div>
      
      
			<div class="research_result">
			  <c:forEach var="room" items="${roomList}">
			    <div class="list">
			      <form action="${contextPath}/hotel/room/roomReserve.page"
			        method="POST">
			        <div class="img">
			           <div class="swiper mySwiper">
								    <div class="swiper-wrapper">
								    <c:forEach var="image" items="${room.images}">
		                  
		                    <div class="swiper-slide">
		                      <img src="${image.uploadPath}/${image.filesystemName}"
                        alt="Room Image" />
		                    
		                    </div>
		                </c:forEach>
								      

								    </div>
								    <div class="swiper-button-next"></div>
								    <div class="swiper-button-prev"></div>
								  </div>
			        
			        
			          
			        </div>
			        <input type="hidden" value="${room.roomNo}" name="roomNo"></input> 
			        <input type="hidden" value="${checkIn}" name="checkIn">  
			        <input type="hidden" value="${checkOut}" name="checkOut">  
			        <input type="hidden" value="${guestCount}" name="guestCount">  
			        <input type="hidden" value="${adults}" name="adults">  
			        <input type="hidden" value="${infants}" name="infants">  
			        
				      <div class="roomInfo">
				        <p class="title"> 객실 정보</p>
				        <ul>
				          <li>
                    <p class="tit">객실명</p>
                    <p class="con"> ${room.roomName}</p>
                  </li>
				          <li>
				            <p class="tit">정보</p>
				            <p class="con"> ${room.info}</p>
				          </li>
				          <li>
				            <p class="tit">인원</p>
				            <p class="con"> ${room.guestCount}</p>
				          </li>
				          <li>
				            <p class="tit">가격</p>
				            <p class="con">
				              <fmt:formatNumber type="number" maxFractionDigits="3" value="${room.price}" /> 원
				            </p>
				          </li>
				        </ul>
				      </div>
			      
			          <button type="submit">예약하기</button>
			      </form>
			    </div>
			  </c:forEach>
			</div>
			<!--================Booking Tabel Area  =======-->
    <!-- 검색결과 End -->
    
  </div>
</div>




<style>
body {
	font-family: Arial, sans-serif;
}

.container {
	margin: 20px;
}

.dropdown {
	/*position: relative;*/
	display: inline-block;
}

.dropdown-content {
	/*	display: none;
	position: absolute;*/
	background-color: #f9f9f9;
	min-width: 200px;
	box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
	z-index: 1;
	padding: 10px;
}

.dropdown-content.show {
	display: block;
}

.dropdown-content input {
	width: 40px;
	text-align: center;
}

.date-display {
	margin-top: 10px;
}
</style>









<script
	src="vendors/bootstrap-datepicker/bootstrap-datetimepicker.min.js"></script>



<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
<script src="${contextPath}/js/flatpickr.js"></script>
<script src="${contextPath}/js/roomSearch.js"></script>
<jsp:include page="../../hotel/layout/footer.jsp" />