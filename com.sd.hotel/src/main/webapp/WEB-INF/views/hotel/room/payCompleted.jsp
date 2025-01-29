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
<c:set var="reserve" value="${reserve}" />
<c:set var="room" value="${room}" />


<div class="sub">
  <div class="sub_in">
    <div class="sub_title">결제완료</div>
    <p> 결제가 완료되었습니다. <br> 
      체크인날짜에 뵙겠습니다.<p>
    <div class="roomInfo">
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
              <input class="con_input" value="${reserve.checkOutDate }" name="checkOutDate" readonly/>
            </p>
          </li>
        </ul>
        
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
               <input class="con_input" value=""${reserve.resPhone} name="checkInDate" readonly/>
             </p>
           </li>
           <li>
             <p class="tit">예약자 이메일</p>
             <p class="con">
               <input class="con_input" value="${reserve.resMail}" name="checkOutDate" readonly/>
             </p>
           </li>
           <li>
             <p class="tit">결제금액</p>
             <p class="con">
               <input class="con_input" value="${room.price}" name="checkOutDate" readonly/>
             </p>
           </li>               
         </ul>           
    </div>
    
  </div>
</div>


<jsp:include page="../../hotel/layout/footer.jsp" />