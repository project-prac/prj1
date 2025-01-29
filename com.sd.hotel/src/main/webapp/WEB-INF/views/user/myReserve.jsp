<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />


<link rel="shortcut icon" href="/resources/admin/images/favicon.png" />

<jsp:include page="../hotel/layout/header.jsp" />
<link rel="stylesheet" href="/resources/hotel/css/sub.css">

<!-- Start -->
<div class="sub mypage">
	<div class="sub_in">
		<div class="sub_title">예약목록</div>
		<c:choose>
      <c:when test="${empty reservedRoom}">
       현재 예약된 내역이 없습니다.
      </c:when>
		  
		  <c:otherwise>
		  
		    <p class="res_count">
		      현재 예약 건수 : <span>${reservedRoom.size()}건</span>
		    </p>

		    <div class="reserved_list">
         <c:forEach items="${reservedRoom}" var="room" varStatus="vs">
				  <div class="list">
				    
					   <div class="list_con">
					      <input type="hidden" value="${room.memberNo}" name="memberNo">
								<div class="con">
									<p class="con_title">예약번호 :</p>
									<p class="con_con">${room.reservationNo}</p>
								</div>
							  
							  <div class="con">
								  <p class="con_title">예약날짜 :</p>
								  <p class="con_con">${room.reservedDate}</p>
							  </div>
							  <div class="con">
								  <p class="con_title">체크인 / 체크아웃 : </p>
								  <p class="con_con">
									 ${room.checkInDate}
		                         ~ ${room.checkOutDate}
								  </p>
							   </div>
							   <div class="con">
								  <p class="con_title">상태 :</p>
								  <p class="con_con">
										
		                  <input type="hidden" id="reservationNo" value="${room.reservationNo}" name="reservationNo">
		                  <input type="hidden" id="roomNo" value="${room.roomNo}" name="roomNo">
		                      
		                 <c:choose>
		                  <c:when test="${room.status eq 'reserved'}">
		                    
			                  예약 완료 / 결제 대기 : 결제를 완료하시면 예약이 확정됩니다.
			                   <button type="button" class="btn_pay">결제하기</button>
		                     <button type="button" class="btn_detail"> 예약 상세보기 </button>
		                     <button class="btn_remove" type="button">  예약 취소하기 </button>
		                  </c:when>
		                  <c:otherwise>
		                  결제완료
		                  
                       <input type="hidden" value="${room.reservationNo}" name="reservationNo">
                       <!--<button type="submit">
                        상세보기 
                       </button>  -->
		                  <a href="${contextPath}/user/myReserveDetail.do">
		                  </a>
		                  
		                  </c:otherwise>
		                </c:choose>
								   </p>
							    </div>

					       </div>
					
				  </div>
			   </c:forEach>
	 	   </div>
      </c:otherwise>
    </c:choose>
	</div>
</div>

<!-- Finish -->



<script>
  const removeResult = '${deleteMessage}'; // JSP에서 치환

  /*
  function submitForm(url){
	    const form = document.getElementById('actionForm');
	    form.action = url;
	    
	    const reservationNo = document.getElementById('');
	    const roomNo = document.getElementById('');
	    
	  }
  */
  
	  
</script>

 <script src="${contextPath}/resources/js/myReserve.js"></script> 
<jsp:include page="../hotel/layout/footer.jsp" />