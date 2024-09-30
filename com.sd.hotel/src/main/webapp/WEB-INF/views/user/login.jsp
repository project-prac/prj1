<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<c:set var="dt" value="<%=System.currentTimeMillis()%>" />

  <!-- plugins: Admin css 
  <link rel="stylesheet" href="/resources/admin/vendors/feather/feather.css">
  <link rel="stylesheet" href="/resources/admin/vendors/mdi/css/materialdesignicons.min.css">
  <link rel="stylesheet" href="/resources/admin/vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" href="/resources/admin/vendors/typicons/typicons.css">
  <link rel="stylesheet" href="/resources/admin/vendors/simple-line-icons/css/simple-line-icons.css">
  <link rel="stylesheet" href="/resources/admin/vendors/css/vendor.bundle.base.css">-->

  <link rel="stylesheet" href="/resources/admin/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
  <link rel="stylesheet" href="/resources/admin/js/select.dataTables.min.css">
  <!-- End plugin css for this page -->
  <!-- inject:css -->
  
  <link rel="stylesheet" href="/resources/admin/css/vertical-layout-light/style.css">
  <!-- endinject -->
  <link rel="shortcut icon" href="/resources/admin/images/favicon.png" />

<jsp:include page="../hotel/layout/header.jsp" />

<div class="container-scroller">
	<div class="container-fluid page-body-wrapper full-page-wrapper">
		<div class="content-wrapper d-flex align-items-center auth px-0">
			<div class="row w-100 mx-0">
				<div class="col-lg-4 mx-auto">
					<div class="auth-form-light text-left py-5 px-4 px-sm-5">
						
						<h4>로그인</h4>
						<h6 class="fw-light">Sign in to continue.</h6>
						<form class="pt-3" 
						      id = "frm-login"
						      method="POST"
						      action="${contextPath}/user/login.do" >
							<div class="form-group">
								<input type="text" class="form-control form-control-lg"
									id="inpId" placeholder="아이디" name="userId"
									value="${sessionScope.userId != null ? sessionScope.userId : ''}"  >
							</div>
							
							<c:if test="${not empty sessionScope.errorMessage}">
					        <script>alert('${sessionScope.errorMessage}');</script>
					    </c:if>
					    
					    <c:remove var="errorMessage" scope="session"/>
					    <c:remove var="userId" scope="session"/>

							<div class="form-group">
								<input type="password" class="form-control form-control-lg"
									id="exampleInputPassword1" placeholder="비밀번호" name="password">
							</div>
							<div class="mt-3">
								<button type="submit"
									class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn"
									>로그인</button>
							</div>
							<div
								class="my-2 d-flex justify-content-between align-items-center">
								<div class="form-check">
									<label class="form-check-label text-muted"> <input
										type="checkbox" class="form-check-input"> Keep me
										signed in
									</label>
								</div>
								<a href="#" class="auth-link text-black">Forgot password?</a>
							</div>
							<div class="mb-2">
								<button type="button"
									class="btn btn-block btn-facebook auth-form-btn">
									<a href="${contextPath}/user/signup.page">회원가입</a>
									
								</button>
							</div>
							<div class="text-center mt-4 fw-light">
								Don't have an account? <a href="register.html"
									class="text-primary">Create</a>
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
  <script src="${contextPath}/resources/login.js?dt=${dt}"></script>
<jsp:include page="../hotel/layout/footer.jsp" />