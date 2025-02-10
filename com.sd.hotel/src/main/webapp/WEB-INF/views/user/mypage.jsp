<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

  <link rel="stylesheet" href="/resources/admin/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
  <link rel="stylesheet" href="/resources/admin/js/select.dataTables.min.css">
  <!-- End plugin css for this page -->
  <!-- inject:css -->
  
  <link rel="stylesheet" href="/resources/admin/css/vertical-layout-light/style.css">
  <!-- endinject -->
  <link rel="shortcut icon" href="/resources/admin/images/favicon.png" />

  <jsp:include page="../hotel/layout/header.jsp" />
  <link rel="stylesheet" href="/resources/hotel/css/sub.css">

<!-- myPageList -> profile.jsp 였음 -->

  <!-- Start -->
  <div class="sub mypage">
    <div class="sub_in">
      <div class="sub_title">마이페이지</div>
        <ul class="mypageInfo">
          <li>
            <p class="info_t">이름</p>
            <p class="info_c"><sec:authentication property="principal"  var="user"/>${user.name}</p>
          </li>
          <li>
            <p class="info_t">생년월일</p>
            <p class="info_c"><sec:authentication property="principal"  var="user"/>${user.birth}</p>
          </li>
          <li>
            <p class="info_t">연락처</p>
            <p class="info_c"><sec:authentication property="principal"  var="user"/>${user.tel}</p>
          </li>
          <li>
            <p class="info_t">이메일</p>
            <p class="info_c"><sec:authentication property="principal"  var="user"/>${user.mail}</p>
            
          </li>
          <p class="info_c"><sec:authentication property="principal"  var="user"/>${user.userNo}</p>
        </ul>
        <div class="">
          <a href="${contextPath}/user/me/edit" class="btna1">회원정보 변경</a>
        </div>
        <ul class="mypageList">
          <li><a href="${contextPath}/user/me/profile">내정보 ▶</a></li>
          <li><a href="${contextPath}/user/myReserve.do">예약내역 ▶</a></li>
          <li><a href="">결제내역 ▶</a></li>
        </ul>
    
    </div>
  </div>

<!-- Finish -->



<div class="container-scroller">
	<div class="container-fluid page-body-wrapper full-page-wrapper">
		<div class="content-wrapper d-flex align-items-center auth px-0">
			<div class="row w-100 mx-0">
				<div class=" mx-auto"> <!-- col-lg-4 -->
					<div class="auth-form-light text-left py-5 px-4 px-sm-5">
						
						<h4>마이페이지</h4>
						<h6 class="fw-light"></h6>
						<div class="card">
						  <div class="card-body">
					     <div class="table-responsive">
				        <table class="table table-striped">
				         
				          <tbody>
				            <tr>
				              <td>내 정보</td>
				              <td><a href="${contextPath}/user/mypage.page">내 정보</a></td>
				            </tr>
				            <tr>
                      <td>예약확인</td>
                      <td><a href="${contextPath}/user/myReserve.do">예약확인</a></td>
                    </tr>
                    <tr>
                      <td>이메일</td>
                      <td><sec:authentication property="principal"  var="user"/>${user.mail}</td>
                    </tr>
                    <tr>
                      <td>전화번호</td>
                      <td><sec:authentication property="principal"  var="user"/>${user.tel}</td>
                    </tr>
                    <tr>
                      <td>생년월일</td>
                      <td><sec:authentication property="principal"  var="user"/>${user.birth}</td>
                    </tr>
				          </tbody>
				       
				        </table>
					     </div>
						  </div>
						</div>

						<form class="pt-3"  >
							
							<div class="mt-3">
							 <div class="btn btn-block btn-primary  font-weight-medium" style="line-height:0;padding:0">
							   <a href="${contextPath}/user/modifyMypage.page" class="btna1">회원정보 변경</a>
							 </div>
							 <!--  
								<button 
									class="btn btn-block btn-primary btn-lg font-weight-medium" id="modify-btn"
									><a href="${contextPath}/user/modifyMypage.page">회원정보 변경</a></button>-->
							</div>
							<div
								class="my-2 d-flex justify-content-between align-items-center">
								
								
							</div>

						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- content-wrapper ends -->
	</div>
	<!-- page-body-wrapper ends -->
	
	
</div>



  <!-- plugins Admin:js -->
  <script src="/resources/admin/vendors/js/vendor.bundle.base.js"></script>
  <!-- endinject -->
  <!-- Plugin js for this page -->
  <script src="/resources/admin/vendors/chart.js/Chart.min.js"></script>
  <script src="/resources/admin/vendors/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
  <script src="/resources/admin/vendors/progressbar.js/progressbar.min.js"></script>

  <!-- End plugin js for this page -->
  <!-- inject:js -->
  <script src="/resources/admin/js/off-canvas.js"></script>
  <script src="/resources/admin/js/hoverable-collapse.js"></script>
  <script src="/resources/admin/js/template.js"></script>
  <script src="/resources/admin/js/settings.js"></script>
  <script src="/resources/admin/js/todolist.js"></script>
  <!-- endinject -->
  <!-- Custom js for this page-->
  <script src="/resources/admin/js/dashboard.js"></script>
  <script src="/resources/admin/js/Chart.roundedBarCharts.js"></script>
  <!-- End custom js for this page-->
  

  
<jsp:include page="../hotel/layout/footer.jsp" />