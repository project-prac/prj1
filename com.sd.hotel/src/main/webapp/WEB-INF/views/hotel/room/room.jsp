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
<!-- <link rel="stylesheet" href="/resources/hotel/css/room.css"> -->
<link rel="stylesheet" href="/resources/hotel/css/roomCss.css">

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />




<div class="sub">
  <div class="sub_in roomJsp" >
    
    <!-- == left nav == -->
		<div class="left-nav-area">
		  <p class="left-nav-title"> 객실</p>
		  <ul class="roomList">
		   <c:forEach items="${roomList}" var="roomParent">
		        <c:if test="${roomParent.depth == 0}">
		          <li class="depth1">
		            <p>${roomParent.roomName}</p>
		            <ul>
		              <c:forEach items="${roomList}" var="roomChild">
		                 <c:if test="${roomChild.parentName != '#' && roomParent.roomNo == roomChild.parentName}">
		                  <li class="roomName depth2" data-room-no="${roomChild.roomNo}">${roomChild.roomName}</li>
		                </c:if>
		              </c:forEach>
		            </ul>
		          </li>
		        </c:if>
		    </c:forEach>   
		  </ul>
		</div>
    <!-- == left nav End== -->
    
    <!-- Room상세 Start -->
    <div class="roomCon">
      <p class="title">${room.roomName}</p>
      
      <!--  
      <c:forEach items="${roomImg}" var="img">
       <img src="${img.uploadPath}/${img.filesystemName}">
      </c:forEach>

-->
      
      <div class="roomImg">
      
          <!-- Swiper -->

					  <div style="--swiper-navigation-color: #fff; --swiper-pagination-color: #fff" class="swiper mySwiper2">
					    <div class="swiper-wrapper">
					      <c:forEach items="${roomImg}" var="img">
					      <div class="swiper-slide">
					        <img src="${img.uploadPath}/${img.filesystemName}">
					      </div>
					      </c:forEach>
					      
					    </div>
					    <div class="swiper-button-next"></div>
					    <div class="swiper-button-prev"></div>
					  </div>
					  <div thumbsSlider="" class="swiper mySwiper">
					    <div class="swiper-wrapper bottom-swiper">
					      <c:forEach items="${roomImg}" var="img">
					      <div class="swiper-slide">
					        <img src="${img.uploadPath}/${img.filesystemName}" />
					      </div>
					      </c:forEach>
					    </div>
					  </div>
      
      
      
      </div>
      <div class="roomInfo">
        <p class="title"> 객실 정보</p>
        <ul>
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
    </div>
    
    
    <!-- Room상세 End -->
    
  </div>
</div>











<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
<script src="${contextPath}/resources/js/room.js"></script>
<jsp:include page="../../hotel/layout/footer.jsp" />