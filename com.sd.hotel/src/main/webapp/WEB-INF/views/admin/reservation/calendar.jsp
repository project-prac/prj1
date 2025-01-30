<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<c:set var="dt" value="<%=System.currentTimeMillis()%>" />

<link rel="stylesheet" href="/resources/admin/css/sd-room.css">

<jsp:include page="../../admin/layout/header.jsp" />

<!-- FullCalendar CDN -->
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.11/index.global.min.js'></script>
<script src='https://cdn.jsdelivr.net/npm/@fullcalendar/google-calendar@6.1.11/index.global.min.js'></script>



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
                <h4>달력페이지</h4>
                <p id="today"></p>
                
                <style>
                .list{list-style:none;margin-bottom:20px;
                border:1px solid #eee;padding:4px}
                .list .row{display:flex;flex-wrap:nowrap;}
                .list .row .row_tit{width:24%;}
                </style>
              <div class="">
              
              
                <style>
                .fc-daygrid-day-frame {
								  height: 120px; /* 날짜 칸 높이 설정 */
								  overflow-y: auto; /* 세로 스크롤 추가 */
								  overflow-x:auto;
								}

									</style>
              
                <div id="calendar"></div>
              
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



<script src="${contextPath}/js/calendar.js"></script>

<jsp:include page="../../admin/layout/footer.jsp" />




