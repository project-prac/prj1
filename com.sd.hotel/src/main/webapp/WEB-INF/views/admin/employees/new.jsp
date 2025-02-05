<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<c:set var="dt" value="<%=System.currentTimeMillis()%>" />

<link rel="stylesheet" href="/resources/admin/css/sub.css">
<jsp:include page="../../admin/layout/header.jsp" />

<div class="sub_in">
  <div class="sub_title">직원 추가</div>
     <form class="forms-sample"
            method="POST"
            id="addEmployeeForm"
            action="${contextPath}/admin/employees">
       <div class="form-group">
         <label for="exampleInputName1">아이디</label>
         <input type="text" class="form-control" id="userId" readonly name="userId">
       </div>
       <div class="form-group">
         <label for="exampleInputName1">이름</label>
         <input type="text" class="form-control" id="exampleInputName1" placeholder="Name" name="name">
       </div>
       <div class="form-group">
         <label for="exampleInputEmail3">이메일</label>
         <input type="email" class="form-control" id="exampleInputEmail3" placeholder="email" name="email">
       </div>
       <div class="form-group">
         <label for="exampleInputPassword4">연락처</label>
         <input type="tel" class="form-control" id="exampleInputPassword4" placeholder="phone number" name="tel">
       </div>
       <div class="form-group">
         <label for="exampleInputPassword4">생년월일</label>
         <input type="date" class="form-control" id="exampleInputPassword4" 
                 name="birth"  value="1950-01-01">
       </div>
       <div class="form-group">
         <label for="gender">성별</label>
           <div style="display:flex;align-items: center">
             <label for="gender-man" style="display:inline-block;width:120px;">남자</label>
             <input type="radio"id="gender-man" name="gender" value="남" 
             style="max-width:120px;">
             <label for="gender-woman" style="display:inline-block;width:120px;">여자</label>
             <input type="radio"  id="gender-woman" name="gender" value="여">
           </div>
       </div>
       <input type="hidden" name="password" value="1111">
       <button type="submit" >추가하기</button>
       <button class="btn btn-light">취소</button>
     </form>  
  
  
</div>


<!-- main-panel ends -->
<script src="${contextPath}/js/addEmployee.js"></script>

<jsp:include page="../../admin/layout/footer.jsp" />