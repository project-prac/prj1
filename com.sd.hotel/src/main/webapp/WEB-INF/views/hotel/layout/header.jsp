<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<c:set var="dt" value="<%=System.currentTimeMillis()%>" />
<c:set var="user"
	value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userDetailDto}" />
<c:set var="admin"
	value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userDto}" />
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>SD Hotel</title>



<!-- main css -->
<link rel="stylesheet" href="/resources/hotel/css/header.css">
<link rel="stylesheet" href="/resources/hotel/css/index.css">



<!--script-->
<!--jQuery  -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.2/gsap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.2/ScrollTrigger.min.js"></script>
<script src="${contextPath}/js/header.js"></script>

</head>
<body>



	<header id="header">
		<div class="header_in">
			<h1 class="logo">
				<a href="/">HOTEL</a>
			</h1>
			<nav class="nav">
				<ul class="nav_ul">
					<li class="nav_li"><a href="/">HOME</a></li>
					<li class="nav_li">
					 <a href="${contextPath}/hotel/room/room.do">객실</a>
					 <ul class="sub_ul">
					   <li><a href="">스탠다느</a></li>
					   <li><a href="">스탠다느11</a></li>
					   
					 </ul>
					</li>
				</ul>
			</nav>
			<div class="gnb">
			<sec:authorize access="isAuthenticated()">
				<p>
					안녕하세요,
					<sec:authentication property="name" />
					님!
				</p>
			</sec:authorize>
				<ul>
					<sec:authorize access="isAnonymous()">
						<li><a href="${contextPath}/user/login">로그인</a></li>
						<li><a href="${contextPath}/user/signup">회원가입</a></li>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<li><a href="${contextPath}/user/logout">로그아웃</a></li>
						<c:if test="${admin.role eq 'ROLE_ADMIN'}">
							<li><a href="${contextPath}/admin/index">관리자</a></li>
							<li><a href="${contextPath}/admin/mypage">비밀번호
									변경</a></li>
						</c:if>
						<c:if test="${user.role eq 'ROLE_USER'}">
							<li><a href="${contextPath}/user/me">마이페이지</a></li>
						</c:if>
						<c:if test="${user.role eq 'ROLE_MANAGER'}">
							<li><a href="${contextPath}/user/mypage.page">마이페이지</a></li>
						</c:if>
					</sec:authorize>

				</ul>
			</div>
		</div>
	</header>