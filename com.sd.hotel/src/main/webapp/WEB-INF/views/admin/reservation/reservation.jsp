<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<c:set var="dt" value="<%=System.currentTimeMillis()%>" />

<link rel="stylesheet" href="/resources/admin/css/sd-room.css">

<jsp:include page="../../admin/layout/header.jsp" />

<link href="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.7/main.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.7/main.min.js"></script>





<div class="main-panel">
  <div class="content-wrapper">
    <div class="row">
      <div class="col-sm-12">
        <div class="home-tab">
          <style>
          .tab-inner{width:100%;height:120px;background:#fff;}
          </style>
          <div class="tab-content tab-content-basic">
            <div class="card">
              <div class="card-body">
                <h4>오늘 체크인 목록</h4>
                
                <div class="">
                  <div class="list">
                    <div class="">예약번호</div>
                    <div class="">예약자명</div>
                    <div class="">객실명</div>
                    <div class="">체크인/체크아웃</div>
                    <div class="">인원수</div>
                    <div class="">예약자 연락처</div>
                  </div>
                
                </div>
                
                
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- content-wrapper ends -->
</div>



<script src="${contextPath}/js/adminReservation.js"></script>

<jsp:include page="../../admin/layout/footer.jsp" />




