<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<c:set var="dt" value="<%=System.currentTimeMillis()%>" />
<c:set var="user" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userDto}" />

<jsp:include page="../hotel/layout/header.jsp"/>
<link rel="stylesheet" href="/resources/hotel/css/sub_mem.css">

<!-- modifyPw.jsp였음 -->

<style>
.stretch-card{margin:150px auto 40px;}
</style>


<div class="sub">
  <div class="sub_in">
    <div class="sub_title">비밀번호 변경하기</div>
    
	    <form class="forms-sample"
	       id = "frm-signup"
	      method="POST"
	      action="${contextPath}/user/password">
	     <input type ="hidden" name="_method" value="PATCH" >
        <div class="form-group">
          <label for="exampleInputUsername1">현재 비밀번호</label> 
          <input
            type="text" class="form-control" id="inp-pw"
            placeholder="비밀번호를 입력해주세요." name="pw"  >
            <p class="check1"></p>
        </div>
        
        <div class="form-group">
          <label for="exampleInputUsername1">새 비밀번호</label> 
          <input
            type="text" class="form-control" id="inp-newpw"
            placeholder="새 비밀번호를 입력해주세요." name="newpw" >
        </div>
        
        <div class="form-group">
          <label for="exampleInputUsername1">새 비밀번호 확인</label> 
          <input
            type="text" class="form-control" id="inp-newpw2"
            placeholder="새 비밀번호를 재입력해주세요." name="newpw2" >
        </div>
        
        <input type="hidden" name="userId" value="${user.userId}">
        <input type="hidden" name="role" value="${user.role}">
        

        <button type="submit" id="submitBtn" class="btn btn-primary me-2">수정완료</button>
        <div><a class="btn btn-light" href="${contextPath}/user/mypage.page">취소</a>
        </div>
        
      </form>
    
  </div>
</div>

<style>
btn a{display:block;color:#000};
</style>



<script>

var newpw = document.getElementById('inp-newpw');


var pwCheck = false;
var newPwCheck = false;
var newPwCheck2 = false;

const fnPw2Check = () => {
	var newpw = document.getElementById('inp-newpw');
	var newpw2 = document.getElementById('inp-newpw2');
	
	if(newpw.value != newpw2.value){
		
	}
}

var newpw = document.getElementById('inp-newpw');
var newpw2 = document.getElementById('inp-newpw2');




const fnCheck = (e) => {
	//alert('?');
	//fnPwCheck();
	
	if(newpw.value != newpw2.value){
		  e.preventDefault();
	    alert("중지")
	}
	
	if(!inp-pw){
		e.preventDefault();
		alert('현재 비밀번호를 입력해주세요.');
		return;
	}
	
	if(!inp-newpw){
    e.preventDefault();
    alert('새 비밀번호를 입력해주세요.');
    return;
	}
	
	if(!inp-newpw2){
    e.preventDefault();
    alert('새비밀번호 확인란을 입력해주세요.');
    return;
  }
	
}

document.getElementById('submitBtn').addEventListener('click', fnCheck);

</script>


<jsp:include page="../hotel/layout/footer.jsp" />