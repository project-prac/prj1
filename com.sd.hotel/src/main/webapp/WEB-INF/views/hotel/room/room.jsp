<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<c:set var="dt" value="<%=System.currentTimeMillis()%>" />
<c:set var="room" value="${roomInfo}" />

<jsp:include page="../../hotel/layout/header.jsp" />
<link rel="stylesheet" href="/resources/hotel/css/room.css">

<!--================Breadcrumb Area =================-->
<section class="breadcrumb_area">
	<div class="overlay bg-parallax" data-stellar-ratio="0.8"
		data-stellar-vertical-offset="0" data-background=""></div>
	<div class="container">
		<div class="page-cover text-center">
			<h2 class="page-cover-tittle">Accomodation</h2>
			<ol class="breadcrumb">
				<li><a href="index.html">Home</a></li>
				<li class="active">Accomodation</li>
			</ol>
		</div>
	</div>
</section>
<!--================Breadcrumb Area =================-->

<!-- == left nav == -->
<div class="left-nav-area">
	<ul class="">
	 <c:forEach items="${roomList}" var="roomParent">
      <c:choose>
        <c:when test="${roomParent.depth == 0}">
          <li>${roomParent.roomName}
            <ul>
              <c:forEach items="${roomList}" var="roomChild">
                 <c:if test="${roomChild.parentName != '#' && roomParent.roomNo == roomChild.parentName}">
                  <li class="roomName" data-room-no="${roomChild.roomNo}">${roomChild.roomName}</li>
                </c:if>
              </c:forEach>
            </ul>
          </li>
        </c:when>
      </c:choose>
    </c:forEach>		
	</ul>
</div>

<!--================ Accomodation Area  =================-->
<section class="accomodation_area section_gap">
	<div class="container">
		<div class="section_title text-center">
			<h2 class="title_color">${room.roomName}</h2>
			<c:forEach items="${roomImg}" var="img">
			 <img src="${img.uploadPath}/${img.filesystemName}">
			</c:forEach>
			<p>We all live in an age that belongs to the young at heart. Life
				that is becoming extremely fast,</p>
		</div>
		
		<style>
		.area{display:flex;}
		</style>
		
		<div class="row mb_30">
			<div class="col-md-6 d_flex align-items-center" >
				<div class="accomodation_item text-left">
					<div class="hotel_img">
						<img src="image/room1.jpg" alt="">
					</div>
					<a href="#"><h4 class="sec_h4">${room.roomName}</h4></a>
					<div class="area">
					가격 :
					 <h5>
           <fmt:formatNumber type="number" maxFractionDigits="3" value="${room.price}" /> 원
           </h5>
					</div>
					<div class="area">
					정보 : ${room.info}
					</div>
					<div class="area">
					인원 : ${room.people}
					</div>
					
				</div>
			</div>
			
		</div>
	</div>
</section>
<!--================ Accomodation Area  =================-->
<!--================Booking Tabel Area =================-->
<section class="hotel_booking_area">
	<div class="container">
		<div class="row hotel_booking_table">
			<div class="col-md-3">
				<h2>
					Book<br> Your Room
				</h2>
			</div>
			<div class="col-md-9">
				<div class="boking_table">
					<div class="row">
						<div class="col-md-4">
							<div class="book_tabel_item">
								<div class="form-group">
									<div class='input-group date' id='datetimepicker11'>
										<input type='text' class="form-control"
											placeholder="Arrival Date" /> <span
											class="input-group-addon"> <i class="fa fa-calendar"
											aria-hidden="true"></i>
										</span>
									</div>
								</div>
								<div class="form-group">
									<div class='input-group date' id='datetimepicker1'>
										<input type='text' class="form-control"
											placeholder="Departure Date" /> <span
											class="input-group-addon"> <i class="fa fa-calendar"
											aria-hidden="true"></i>
										</span>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="book_tabel_item">
								<div class="input-group">
									<select class="wide">
										<option data-display="Adult">Adult</option>
										<option value="1">Old</option>
										<option value="2">Younger</option>
										<option value="3">Potato</option>
									</select>
								</div>
								<div class="input-group">
									<select class="wide">
										<option data-display="Child">Child</option>
										<option value="1">Child</option>
										<option value="2">Baby</option>
										<option value="3">Child</option>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="book_tabel_item">
								<div class="input-group">
									<select class="wide">
										<option data-display="Child">Number of Rooms</option>
										<option value="1">Room 01</option>
										<option value="2">Room 02</option>
										<option value="3">Room 03</option>
									</select>
								</div>
								<a class="book_now_btn button_hover" href="#">Book Now</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!--================Booking Tabel Area  =================-->





<script src="${contextPath}/resources/js/roomList.js"></script>
<jsp:include page="../../hotel/layout/footer.jsp" />