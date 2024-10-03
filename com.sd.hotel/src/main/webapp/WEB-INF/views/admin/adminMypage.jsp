<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<c:set var="dt" value="<%=System.currentTimeMillis()%>" />
<c:set var="admin" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userDto}" />

<jsp:include page="../hotel/layout/header.jsp"/>



<style>
.stretch-card{margin:150px auto 40px;}
</style>
<div class="col-md-6 grid-margin stretch-card">
  <div class="card">
    <div class="card-body">
      <h4 class="card-title">비밀번호 변경하기</h4>
      <p class="card-description"></p>
      <form class="forms-sample"
             id = "frm-signup"
            method="POST"
            action="${contextPath}/admin/modifyAdminPw.do">
        <div class="form-group">
          <label for="exampleInputUsername1">성명</label> 
          <input
            type="text" class="form-control" id="inp-name"
            placeholder="Username" name="name" value="${admin.name}" >
        </div>
        <div class="form-group">
          <label for="InputId">아이디</label> <input
            type="text" class="form-control" id="InputId"
            placeholder="" name="userId" value="${admin.userId}"  readonly>
        </div>
        
        <div class="form-group">
          <label for="exampleInputUsername1">새 비밀번호</label> 
          <input
            type="text" class="form-control" id="inp-newpw"
            placeholder="새 비밀번호를 입력해주세요." name="password" >
        </div>
        
        <div class="form-group">
          <label for="exampleInputUsername1">새 비밀번호 확인</label> 
          <input
            type="text" class="form-control" id="inp-newpw2"
            placeholder="새 비밀번호를 재입력해주세요." name="name" >
        </div>
        
        
        <style>
        #verify-btn{visibility:hidden}
        </style>

        <button type="submit"class="btn btn-primary me-2">수정완료</button>
        <div><a class="btn btn-light" href="${contextPath}/user/mypage.page">취소</a>
        </div>
        <div><a class="btn btn-primary me-2" href="${contextPath}/user/modifyPw.page">비밀번호 변경하기</a></div>
      </form>
    </div>
  </div>
</div>

<style>
btn a{display:block;color:#000}
</style>




<jsp:include page="../hotel/layout/footer.jsp" />