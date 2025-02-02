<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<c:set var="dt" value="<%=System.currentTimeMillis()%>" />
<c:set var="admin"
	value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userDto}" />

<jsp:include page="../hotel/layout/header.jsp" />
<link rel="stylesheet" href="/resources/hotel/css/sub_mem.css">



<style>
.stretch-card {
	margin: 150px auto 40px;
}
</style>

<div class="sub">
  <div class="sub_in">
    <div class="sub_title">관리자 비밀번호 변경</div>
    
            <form class="forms-sample" id="frm-signup" method="POST"
        action="${contextPath}/admin/modifyAdminPw.do">
        <div class="form-group">
          <label for="exampleInputUsername1">성명</label> <input type="text"
            class="form-control" id="inp-name" placeholder="Username"
            name="name" value="${admin.name}">
        </div>
        <div class="form-group">
          <label for="InputId">아이디</label> <input type="text"
            class="form-control" id="InputId" placeholder="" name="userId"
            value="${admin.userId}" readonly>
        </div>

        <c:if test="${admin.password eq '1111'}">
          <div class="form-group">
            <label for="exampleInputUsername1">새 비밀번호</label> <input
              type="text" class="form-control" id="inp-newpw"
              placeholder="새 비밀번호를 입력해주세요." name="password">
          </div>

          <div class="form-group">
            <label for="exampleInputUsername1">새 비밀번호 확인</label> <input
              type="text" class="form-control" id="inp-newpw2"
              placeholder="새 비밀번호를 재입력해주세요." name="name">
          </div>

          <button type="submit" id="submitBtn" class="btn btn-primary me-2">수정완료</button>
        </c:if>

        <div>
          <a class="btn btn-light" href="${contextPath}/user/mypage.page">취소</a>
        </div>
        <c:if test="${admin.password ne '1111'}">
          <div>
            <a class="btn btn-primary me-2"
              href="${contextPath}/user/modifyPw.page">비밀번호 변경하기</a>
          </div>
        </c:if>
      </form>
  </div>
</div>


        <style>
#verify-btn {
  visibility: hidden
}
</style>
<style>
btn a {
	display: block;
	color: #000
}
</style>

<script>

var newpw = document.getElementById('inp-newpw');
var newpw2 = document.getElementById('inp-newpw2');

var pwCheck = false;
var newPwCheck = false;
var newPwCheck2 = false;

const fnCheck = (e) => {

    //e.preventDefault();

	  if(newpw == ''){
	      e.preventDefault();
	      alert('새 비밀번호를 입력해주세요.');
	      return;
	  }
    
	  if(newpw.value != newpw2.value){
	      e.preventDefault();
	      alert("비밀번호가 맞지 않습니다.")
	      return;
	  }
	  
	  
	  if(!newpw2){
	    e.preventDefault();
	    alert('새비밀번호 확인란을 입력해주세요.');
	    return;
	  }
	  
	}
document.getElementById('submitBtn').addEventListener('click', fnCheck);


</script>


<jsp:include page="../hotel/layout/footer.jsp" />