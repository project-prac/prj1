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


<c:set var="date" value="${date}"/>

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
                <h4>dddd</h4>
                <p id="today"> .. ${date}</p>
                
                <style>
                .list{list-style:none;margin-bottom:20px;
                border:1px solid #eee;padding:4px}
                .list .row{display:flex;flex-wrap:nowrap;}
                .list .row .row_tit{width:24%;}
                </style>
                
                <ul id="list_checkIn">
                <!-- 
                  <li class="list">
                    <div class="row">
                      <div class="row_tit"></div>
                    </div>
                    <div class="row">
                      <div class="row_tit">예약번호:</div>
                      <div id="resNo"></div>
                    </div>
                    <div class="row">
                      <div class="row_tit">예약자명:</div>
                      <div id="resName"></div>
                    </div>
                    <div class="row">
                      <div class="row_tit">연락처</div>
                      <div id="resPhone"></div>
                    </div>
                    <div class="row">
                      <div class="row_tit">객실명:</div>
                      <div id="roomName"></div>
                    </div>
                    <div class="row">
                      <div class="row_tit">체크인-체크아웃</div>
                      <div id="checkIn"></div>
                      <div id="checkOut"></div>
                    </div>
                    <div class="row">
                      <div class="row_tit">인원수</div>
                      <div id="guestCount"></div>
                    </div>
                    <div class="row">
                      <div class="row_tit"></div>
                    </div>
                  </li>   -->              
                </ul>
                
                
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- content-wrapper ends -->
</div>



<script src="${contextPath}/js/reservedList.js"></script>

<jsp:include page="../../admin/layout/footer.jsp" />




