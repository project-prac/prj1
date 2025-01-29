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



<div class="sub">
  <div class="sub_in">
  dgdg
  </div>
</div>



<!--================ Accomodation Area  =================-->
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

	/*position: absolute;*/
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


<form action="${contextPath}/hotel/room/roomSearch.do" method="POST">
<div class="container">
	<div id="calendar-container">
		<label for="checkin">체크인</label> 
		<input id="checkin" type="text"name="checkIn"placeholder="체크인 날짜"> 
		<label for="checkout">체크아웃</label>
		<input id="checkout" type="text"name="checkOut" placeholder="체크아웃 날짜">
	</div>
	
	<div class="date-display">
		<span id="nights">0</span>박
	</div>

	<div class="dropdown">
		<button type="button"id="guestButton">성인/어린이/유아 선택</button>
		
		<div id="guestDropdown" class="dropdown-content">
			<div>
				<label for="adults">성인: </label>
				<button type="button">-</button>
				<input id="adults" type="number" value="2" name="adults" readonly>
				<button type="button">+</button>
			</div>
			<div>
        <label for="infants">아동: </label>
        <button type="button">-</button>
        <input id="infants" type="number" value="1" name="infants" readonly>
        <button type="button">+</button>
      </div>
      <input id="guestCount" type="hidden" name="guestCount"> 
			
		</div>
	</div>
	<br>
	<div><button type="submit" id="search">검색</button></div>
</div>
</form>


<!--================Booking Tabel Area =================-->
<section class="research_result" >
  <div>
    <div class="img"></div>
    <div class="">
      <p>객실명:</p>
      <p>가격:</p>
    </div>
  </div>
</section>
<!--================Booking Tabel Area  =================-->




<script src="${contextPath}/js/flatpickr.js"></script>
<script src="${contextPath}/resources/js/roomSearch.js"></script>
<jsp:include page="../../hotel/layout/footer.jsp" />