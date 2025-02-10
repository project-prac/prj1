<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<c:set var="dt" value="<%=System.currentTimeMillis()%>" />

<jsp:include page="../hotel/layout/header.jsp"/>
<link rel="stylesheet" href="/resources/hotel/css/sub_mem.css">


<style>
.stretch-card{margin:150px auto 40px;}
</style>

<div class="sub">
  <div class="sub_in">
    <div class="sub_title">회원가입</div>
    <form class="forms-sample"
             id = "frm-signup"
            method="POST"
            action="${contextPath}/user">
        <div class="form-group">
          <label for="exampleInputUsername1">성명</label> 
          <input
            type="text" class="form-control" id="inp-name"
            placeholder="Username" name="name">
        </div>
        <div class="form-group">
          <label for="InputId">아이디</label> <input
            type="text" class="form-control" id="InputId"
            placeholder="" name="userId">
          <button type="button" id="btn-id">중복확인</button>
            <p id="id-comment"></p>
        </div>
        
        <div class="form-group">
          <label for="exampleInputEmail1">이메일</label> <input
            type="email" class="form-control" id="InputEmail"
            placeholder="Email" name="email">
            <button type="button" id="btn-email">인증번호 보내기</button>
        </div>
        
        <div class="form-group">
          <label for="inp-code">이메일인증번호</label>
          <input id="inp-code" class="form-control" disabled>
        </div>
        <div class="form-group">
          <button type="button" id="btn-verify-code" disabled>인증하기</button>
        </div>
        
        <div class="form-group">
          <label for="inputPassword">비밀번호</label> <input
            type="password" class="form-control" id="inputPassword" name="password"
            placeholder="비밀번호를 입력해주세요">
        </div>
        <div class="form-group">
          <label for="inputPasswordConfirm">비밀번호 확인</label>
          <input type="password" class="form-control"
            id="inputPasswordConfirm" placeholder="비밀번호를 입력해주세요">
        </div>
        <div class="form-group">
          <label for="tel">전화번호</label>
          <input type="tel" class="form-control" id="tel" name="tel">
        </div>
        <div class="form-group">
          <label for="birth">생년월일</label>
          <input class="form-control" type="date"
             id="birth"
             name="birth"
             value="1900-01-01"
           />
        </div>
        <div class="form-group">
          <label for="gender">성별</label>
          <div style="display:flex;align-items: center">
            <label for="gender-man" class="radio-label" style="display:inline-block;">남자</label>
            <input type="radio" class="radio-input" id="gender-man" name="gender" value="남" 
            style="max-width:120px;">
            <label for="gender-woman" class="radio-label" style="display:inline-block;">여자</label>
            <input type="radio" class="radio-input" id="gender-woman" name="gender" value="여">
          </div>

        </div>
        <input type="hidden" name="role" value="ROLE_USER">

        <div class="form-check form-check-flat form-check-primary">
         
        </div>
        <button type="submit"class="btn btn-primary me-2">회원가입</button>
        <button class="btn btn-light">Cancel</button>
      </form>
  </div>
</div>




<style>

</style>

<script src="${contextPath}/resources/js/user/signup.js?dt=${dt}"></script>


<jsp:include page="../hotel/layout/footer.jsp" />