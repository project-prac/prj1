<!-- admin Header -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<c:set var="dt" value="<%=System.currentTimeMillis()%>" />
<c:set var="user" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userDetailDto}" />
<c:set var="admin" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userDto}" />

<%@ taglib prefix="sec"
  uri="http://www.springframework.org/security/tags"%>


<!DOCTYPE html>
<html lang="ko">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Star Admin2 </title>
  <!-- plugins:css 
  <link rel="stylesheet" href="/resources/admin/vendors/feather/feather.css">
  <link rel="stylesheet" href="/resources/admin/vendors/mdi/css/materialdesignicons.min.css">
  <link rel="stylesheet" href="/resources/admin/vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" href="/resources/admin/vendors/typicons/typicons.css">
  <link rel="stylesheet" href="/resources/admin/vendors/simple-line-icons/css/simple-line-icons.css">
  <link rel="stylesheet" href="/resources/admin/vendors/css/vendor.bundle.base.css">
  <!-- endinject -->
  <!-- Plugin css for this page 
  <link rel="stylesheet" href="/resources/admin/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
  <link rel="stylesheet" href="/resources/admin/js/select.dataTables.min.css">-->
  <!-- End plugin css for this page -->
  <!-- inject:css -->
  <!--  <link rel="stylesheet" href="/resources/admin/css/vertical-layout-light/style.css">-->
  <link rel="stylesheet" href="/resources/admin/css/header.css">

  
  
<!--jQuery-->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- header.js -->
<script src="/resources/js/admin/header.js"></script>
  
  
  
</head>
<body>

  <header id="header">
    <div class="header_in">
      <div class="hi">안녕하세요, 
        <c:if test="${admin.role eq 'ROLE_ADMIN'}">${admin.name} 님!</c:if>
        <c:if test="${admin.role eq 'ROLE_USER'}">${user.name} 님!</c:if>
      </div>
      
      <div class="left_area">
        <sec:authorize access="isAuthenticated()">
          <a href="${contextPath}/user/logout">로그아웃</a>
        </sec:authorize>
      </div>
    </div>
  </header>
  
  <div class="container">
  
	  <div class="left_nav">
	    <ul class="nav_ul">
	      <c:if test="${admin.role eq 'ROLE_ADMIN'}">
	        <li class="nav_li">
	          <a href="${contextPath}/admin/employees/new">직원추가</a>
	        </li>
	      </c:if>
	      <li class="nav_li">
	        <a href="${contextPath}/admin/room/list">객실목록</a>
	      </li>
	      <li class="nav_li">
	        <a href="javascript:;">예약관련 ▼</a>
	        <ul>
	          <li>
	            <a href="${contextPath}/admin/reservation/todayCheckIn.page">오늘의 체크인</a>
	          </li>
	          <li>
	            <a href="${contextPath}/admin/reservation/calendar.page">예약전체보기</a>
	          </li>
	          <li>
	            <a href="${contextPath}/admin/reservation/reservedList.page">예약목록</a>
	          </li>
	        </ul>
	      </li>
	    </ul>
	  </div>
	  
    <div class="sub">


