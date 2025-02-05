<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<c:set var="dt" value="<%=System.currentTimeMillis()%>" />

<link rel="stylesheet" href="/resources/admin/css/sd-room.css">
<link rel="stylesheet" href="/resources/admin/css/reservation.css">
<link rel="stylesheet" href="/resources/admin/css/sub.css">

<jsp:include page="../../admin/layout/header.jsp" />

<c:set var="date" value="${date}"/>


<div class="sub_in">
  <div class="sub_title">예약현황</div>
  <p id="today">${date}</p>
  <ul id="list_checkIn">

  </ul>
  
</div>


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
                <h4>예약현황</h4>
                
                
                <style>
                .list{list-style:none;margin-bottom:20px;
                border:1px solid #eee;padding:4px}
                .list .row{display:flex;flex-wrap:nowrap;}
                .list .row .row_tit{width:24%;}
                </style>
                

                
                
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




