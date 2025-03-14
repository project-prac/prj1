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
				              <td>아이디</td>
				              <td><sec:authentication property="principal"  var="user"/>${user.username}</td>
				            </tr>
				            <tr>
                      <td>성명</td>
                      <td><sec:authentication property="principal"  var="user"/>${user.name}</td>
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
							   <a href="${contextPath}/user/me/profile/edit" class="btna1">회원정보 변경</a>
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
	
	<sec:authentication property="name" />
</div>

<!-- msg -->

<c:if test="${not empty pwsuccess}">
    <script>alert('${pwsuccess}');</script>
</c:if>
<c:if test="${not empty pwfail}">
    <script>alert('${pwfail}');</script>
</c:if>
  

  
<jsp:include page="../hotel/layout/footer.jsp" />