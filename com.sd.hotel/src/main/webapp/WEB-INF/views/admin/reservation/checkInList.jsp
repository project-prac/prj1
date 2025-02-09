<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<c:set var="dt" value="<%=System.currentTimeMillis()%>" />

<link rel="stylesheet" href="/resources/admin/css/sd-room.css">
<link rel="stylesheet" href="/resources/admin/css/sub.css">
<jsp:include page="../../admin/layout/header.jsp" />

<link href="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.7/main.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.7/main.min.js"></script>

   <style>
   .list{list-style:none;margin-bottom:20px;
   border:1px solid #eee;padding:4px}
   .list .row{display:flex;flex-wrap:nowrap;}
   .list .row .row_tit{width:24%;}
   </style>

<div class="sub_in">
  <div class="sub_title">오늘의 체크인</div>
     <p id="today"></p>
     <ul id="list_checkIn">
  
     </ul>
</div>








<script src="${contextPath}/js/todayCheckIn.js"></script>

<jsp:include page="../../admin/layout/footer.jsp" />




