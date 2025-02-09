<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<c:set var="dt" value="<%=System.currentTimeMillis()%>" />

<link rel="stylesheet" href="/resources/admin/css/sd-room.css">
<link rel="stylesheet" href="/resources/admin/css/sub.css">
<jsp:include page="../../admin/layout/header.jsp" />

<!-- FullCalendar CDN -->
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.11/index.global.min.js'></script>
<script src='https://cdn.jsdelivr.net/npm/@fullcalendar/google-calendar@6.1.11/index.global.min.js'></script>


<!-- moment.js CDN -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.30.1/moment.min.js"></script>



<div class="sub_in">
  <div class="sub_title">예약 현황</div>
  <div id="calendar"></div>
  
</div>




                <style>
                .list{list-style:none;margin-bottom:20px;
                border:1px solid #eee;padding:4px}
                .list .row{display:flex;flex-wrap:nowrap;}
                .list .row .row_tit{width:24%;}
                </style>
                <style>
                .fc-daygrid-day-frame {
                  height: 120px; /* 날짜 칸 높이 설정 */
                  overflow-y: auto; /* 세로 스크롤 추가 */
                  overflow-x:auto;
                }

                  </style>

<script src="${contextPath}/js/admin/room/calendar.js"></script>

<jsp:include page="../../admin/layout/footer.jsp" />




