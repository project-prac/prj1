<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<c:set var="dt" value="<%=System.currentTimeMillis()%>" />
<c:set var="user" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userDetailDto}" />

<jsp:include page="../hotel/layout/header.jsp"/>
<link rel="stylesheet" href="/resources/hotel/css/sub_mem.css">

<!-- modifyMypage.jsp 였음 -->
<style>
.stretch-card{margin:150px auto 40px;}
</style>

<div class="sub">
  <div class="sub_in">
    <div class="sub_title">내 정보수정</div>
      <form class="forms-sample"
             id = "frm-signup"
            method="POST"
            action="${contextPath}/user/me">
      <input type ="hidden" name="_method" value="PATCH" >
        <div class="form-group">
          <label for="exampleInputUsername1">성명</label> 
          <input
            type="text" class="form-control" id="inp-name"
            placeholder="Username" name="name" value="${user.name}" >
        </div>
        <div class="form-group">
          <label for="InputId">아이디</label> <input
            type="text" class="form-control" id="InputId"
            placeholder="" name="userId" value="${user.userId}"  readonly>
        </div>
        
        <div class="form-group">
          <label for="exampleInputEmail1">이메일</label> <input
            type="email" class="form-control" id="InputEmail"
            name="email" value="${user.email}" readonly>
            <button type="button" id="modify-mail">수정</button>
            <button type="button" id="verify-btn" >인증번호 보내기</button>
        </div>
        <style>
        #verify-btn{visibility:hidden}
        </style>
        
        <div class="form-group">
          <label for="inp-code">이메일인증번호</label>
          <input id="inp-code" class="form-control" disabled>
        </div>
        <div class="form-group">
          <button type="button" id="btn-verify-code" disabled>인증하기</button>
        </div>

        <div class="form-group">
          <label for="tel">전화번호</label>
          <input type="tel" class="form-control" id="tel" name="tel" value="${user.tel}">
        </div>
        <div class="form-group">
          <label for="birth">생년월일</label>
          <input class="form-control" type="date"
             id="birth"
             name="birth"
             value="${user.birth}"
           />
        </div>
        <input type="hidden" name="role" value="${user.role}">
        
       <!--  <input type="hidden" name="role" value="ROLE_USER">
        <input type="hidden" name="password" value=${user.password}> -->
        


        <button type="submit"class="btn btn-primary me-2">수정완료</button>
        <div><a class="btn btn-light" href="${contextPath}/user/mypage.page">취소</a>
        </div>
        <div><a class="btn btn-primary me-2" href="${contextPath}/user/me/edit/password">비밀번호 변경하기</a></div>
      </form>
    
  </div>
</div>



<style>
btn a{display:block;color:#000}
</style>

<script src="${contextPath}/resources/js/modifyMypage.js?dt=${dt}"></script>


<jsp:include page="../hotel/layout/footer.jsp" />