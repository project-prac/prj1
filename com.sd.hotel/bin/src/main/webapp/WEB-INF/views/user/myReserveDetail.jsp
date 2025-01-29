<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<c:set var="roomList" value="${reservedRoom}" />
<c:set var="room" value="${reservedRoom.room}" />


<!-- endinject -->
<link rel="shortcut icon" href="/resources/admin/images/favicon.png" />

<jsp:include page="../hotel/layout/header.jsp" />
<link rel="stylesheet" href="/resources/hotel/css/sub.css">

<!-- Start -->

<div class="sub mypage">
  <div class="sub_in">
    <div class="sub_title">예약 상세 내역</div>
      <div class="reserved_list">
        <div class="list_con">
        
          <div class="con">
            <p class="con_title">예약번호 :</p>
            <p class="con_con">${roomList.reservationNo}</p>
          </div>
          
          <div class="con">
            <p class="con_title">예약날짜 :</p>
            <p class="con_con">${roomList.reservedDate}</p>
          </div>
          
          <div class="con">
            <p class="con_title">체크인 / 체크아웃 :</p>
            <p class="con_con">
            ${roomList.checkInDate} ~
            ${roomList.checkOutDate}
            </p>
          </div>
          
          <div class="con">
            <p class="con_title">투숙인원 :</p>
            <p class="con_con">${roomList.guestCount}</p>
          </div>
          
          <div class="con">
            <p class="con_title">객실명 :</p>
            <p class="con_con">${room.roomName}</p>
          </div>
          
          <div class="con">
            <p class="con_title">상태</p>
            
            <input type="hidden" id="reservationNo" value="${roomList.reservationNo}" name="reservationNo"> 
            <input type="hidden" id="roomNo"  value="${room.roomNo}" name="roomNo"> 
            <c:choose>
              
              <c:when test="${roomList.status eq 'reserved'}">
                <p class="con_con ">
                  결제대기
                  <div class="btn_remove">예약취소</div>
                  <div class="btn_pay">결제하기</div>
                  
                </p>
              
              </c:when>
              <c:otherwise>
                <p class="con_con">
                  결제확정
                  <a href="">결제 취소</a>
                </p>
              
              </c:otherwise>
            </c:choose>
           
            
            
          </div>
          
        </div>
      </div>
      
      <p class="reservedDetail_p">예약자 정보</p>
      <div class="reserved_list">
      
        <div class="list_con">
          
          <div class="con">
            <p class="con_title"> 예약자 성명:</p>
            <p class="con_con">${roomList.resName}</p>
          </div>
          
          <div class="con">
            <p class="con_title">예약자 연락처 :</p>
            <p class="con_con"> ${roomList.resPhone}</p>
          </div>
          
          <div class="con">
            <p class="con_title">예약자 이메일:</p>
            <p class="con_con">${roomList.resMail}</p>
          </div>
          
          <div class="con">
            <p class="con_title">메모 :</p>
            <p class="con_con">${roomList.resNote}</p>
          </div>  
                            
        </div>
        
      </div>    
  </div>
</div>



<script src="${contextPath}/resources/js/myReserveDetail.js"></script> 
<jsp:include page="../hotel/layout/footer.jsp" />