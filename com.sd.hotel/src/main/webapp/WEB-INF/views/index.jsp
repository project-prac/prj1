<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<c:set var="dt" value="<%=System.currentTimeMillis()%>" />

<jsp:include page="./hotel/layout/header.jsp" />

<!-- flatpicker.js -->
<link rel="stylesheet"
  href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>


<section id="main">
	<div class="section_in main_in">
		<div class="search_area">
			<form id="form_search"
				action="${contextPath}/hotel/room/roomSearch.do" method="POST">
				<div class="container">
					<div id="calendar-container">
						<div>
							<label for="checkin">체크인</label> <br> <input id="checkin"
								type="text" name="checkIn" placeholder="체크인 날짜">
						</div>
						<div>
							<label for="checkout">체크아웃</label><br> <input id="checkout"
								type="text" name="checkOut" placeholder="체크아웃 날짜">
						</div>
						<div class="date-display">
							<span id="nights">0</span>박
						</div>
					</div>

					<hr class="hr1">


					<div class="dropdown">
						<!--<button type="button"id="guestButton">성인/어린이/유아 선택</button>-->

						<div id="guestDropdown" class="dropdown-content">
							<p>인원</p>
							<div>
								<label for="adults">성인: </label>
								<button type="button">-</button>
								<input id="adults" type="number" value="2" name="adults"
									readonly>
								<button type="button">+</button>
							</div>
							<div>
								<label for="infants">아동: </label>
								<button type="button">-</button>
								<input id="infants" type="number" value="1" name="infants"
									readonly>
								<button type="button">+</button>
							</div>
							<input id="guestCount" type="hidden" name="guestCount">

						</div>
					</div>
					<br>
					<div>
						<button type="submit" id="search">검색</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</section>

<section id="section1"></section>


<!-- js -->
<script src="${contextPath}/js/flatpickr.js"></script>
<script src="${contextPath}/resources/js/roomSearch.js"></script>
<!-- Footer -->
<jsp:include page="./hotel/layout/footer.jsp" />
