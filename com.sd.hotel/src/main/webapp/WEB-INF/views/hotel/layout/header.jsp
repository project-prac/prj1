<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<c:set var="dt" value="<%=System.currentTimeMillis()%>" />
<c:set var="member" value="${pageContext.request.userPrincipal.name}"/>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>SD Hotel</title>
<!-- Link -->
  <link rel="stylesheet" href="/resources/hotel/css/bootstrap.css">
  <link rel="stylesheet" href="/resources/hotel/vendors/linericon/style.css">
  <link rel="stylesheet" href="/resources/hotel/css/font-awesome.min.css">
  <link rel="stylesheet" href="/resources/hotel/vendors/owl-carousel/owl.carousel.min.css">
  <link rel="stylesheet" href="/resources/hotel/vendors/bootstrap-datepicker/bootstrap-datetimepicker.min.css">
  <link rel="stylesheet" href="/resources/hotel/vendors/nice-select/css/nice-select.css">
  <link rel="stylesheet" href="/resources/hotel/vendors/owl-carousel/owl.carousel.min.css">
  <!-- main css -->
  <link rel="stylesheet" href="/resources/hotel/css/style.css">
  <link rel="stylesheet" href="/resources/hotel/css/responsive.css">

</head>
<body>

<!-- 
<sec:authorize access="isAuthenticated()">
    <p>안녕하세요, <sec:authentication property="name"/>님!</p>
    <a href="${contextPath}/logout">로그아웃</a>
  </sec:authorize>
 -->

	<!--================Header Area =================-->
	
	<div><p>안녕하세요, <sec:authentication property="name"/>님!</p></div>
	<header class="header_area">
		<div class="container" id="hotel-header-container">
			<nav class="navbar navbar-expand-lg navbar-light">
				<!-- Brand and toggle get grouped for better mobile display -->

				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse offset"
					id="navbarSupportedContent">
					<ul class="nav navbar-nav menu_nav ml-auto">
						<li class="nav-item active"><a class="nav-link"
							href="/">Home</a></li>
						<li class="nav-item"><a class="nav-link" href="about.html">About
								us</a></li>
						<li class="nav-item"><a class="nav-link"
							href="accomodation.html">Accomodation</a></li>
						<li class="nav-item"><a class="nav-link" href="gallery.html">Gallery</a></li>
						<li class="nav-item submenu dropdown"><a href="#"
							class="nav-link dropdown-toggle" data-toggle="dropdown"
							role="button" aria-haspopup="true" aria-expanded="false">Blog</a>
							<ul class="dropdown-menu">
								<li class="nav-item"><a class="nav-link" href="blog.html">Blog</a></li>
								<li class="nav-item"><a class="nav-link"
									href="blog-single.html">Blog Details</a></li>
							</ul></li>
						<li class="nav-item"><a class="nav-link" href="${contextPath}/user/signup.page">Join</a></li>
						<li class="nav-item"><a class="nav-link" href="contact.html">Contact</a></li>
					</ul>
				</div>
			  <div class="sub-navbar">
			   <ul class="navbar-nav">
				   <sec:authorize access="isAnonymous()">
				       <li class="nav-item">
				           <a href="${contextPath}/user/login.page">로그인</a>
				       </li>
				       <li class="nav-item">
				           <a href="${contextPath}/user/signup.page">회원가입</a>
				       </li>
				   </sec:authorize>
				
				   <sec:authorize access="isAuthenticated()">
				       <li class="nav-item">
				           <a href="${contextPath}/user/logout">로그아웃</a>
				       </li>
				       <li class="nav-item">
				           <a href="${contextPath}/user/mypage.page">마이페이지</a>
				       </li>
				   </sec:authorize>
				</ul>
			  </div>
			</nav>
			
		</div>
	</header>
	<!--================Header Area =================-->

