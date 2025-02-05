<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<c:set var="dt" value="<%=System.currentTimeMillis()%>" />

<link rel="stylesheet" href="/resources/admin/css/sd-room.css">
<link rel="stylesheet" href="/resources/admin/css/sub.css">

<jsp:include page="../../admin/layout/header.jsp" />

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.3.16/themes/default/style.min.css">



<div class="sub_in">
  <div class="sub_title">객실관리</div>
  
  <!-- 객실 목록 -->
  <div class="section">
     <div class="section_title">객실 목록</div>
     <div class="list-area">
      
        <div class="lists">
          <div id="jstree"></div>
        </div>
        
        <div class="list-detail">
          <form id="room-info"
                enctype="multipart/form-data"
               >
            <div class="form-group">
              <input type="hidden" id="roomNo" name="roomNo">
              <label for="roomName">객실명</label>
              <input type="text" class="form-control" id="roomName" name="roomName" />
            </div>
            <div class="form-group">
              <label for="roomName">객실수</label>
              <input  class="form-control" id="new-roomNum" name="roomNum" />
              <input type="hidden" class="form-control" id="origin-roomNum" name="originNum" />
            </div>
            
            <div class="form-group">
              <label for="roomName">객실설명</label>
              <input type="text" class="form-control" id="info" name="info"/>
            </div>
            <div class="form-group">
              <label for="roomName">가격</label>
              <input type="text" class="form-control" id="price" name="price" />
            </div>
            <div class="form-group">
              <label for="roomName">인원수</label>
              <input type="text" class="form-control" id="people" name="people" />
            </div>
            <div class="roomImgListsArea">
              
              <div class="roomImgLists"></div>
              <div id="imgModify-button" class="img_click_btn">
                  이미지 올리기
                  <input type="file" name="files" id="modifyImgFile" multiple>
              </div>
              <div id="modifyImgLists">
              </div>
              <input type="hidden" id="clientImgNos" name="clientImgNos">
            </div>
            
            <button type="submit">수정완료</button>
          </form>
        </div>
     </div>  
  </div>
  
  
  <!-- 객실 대분류 추가 -->
  <div class="section">
    <div class="section_title">객실대분류 추가하기<span>▼</span></div>
	    <form id="roomNoRegister"
	         enctype="multipart/form-data"
	         action="${contextPath}/admin/room/roomNoRegister.do"
	         method="POST">
	      <select id="roomNo-list" name="roomNo">
	        
	      </select>
	      <label for="roomNo-roomName">객실명</label>
	      <input type="text" id="roomNo-roomName" name="roomName">      
	      <input type="hidden" name="depth" value="0">    
	      <input type="hidden" name="parentName" value="#">    
	      
	      <button type="submit">객실대분류 추가하기</button>
	          
	    </form>
  </div>
  
  <!-- 객실 유형 추가 -->
  <div class="section">
    <div class="section_title">객실유형 추가하기<span>▼</span></div>
	  <form id="roomTypeRegisterForm"
	        enctype="multipart/form-data">
	   <select id="roomNo-list2" name="roomNo"></select>    
	    <div class="form-group">
		    <label for="roomType-roomName">객실명</label>
	      <input type="text" id="roomType-roomName" name="roomName">  
	    </div>
      <div class="form-group">
        <label for="roomType-roomName">정보</label>
        <input type="text" id="roomType-roomName" name="info">
      </div>
      <div class="form-group">
        <label for="roomType-roomName">가격</label>
        <input type="text" id="roomType-roomName" name="price">
      </div>
      <div class="form-group">
        <label for="roomType-roomName">인원수</label>
        <input type="text" id="roomType-roomName" name="people">
      </div>
      <div class="form-group">
        <label for="roomNum">객실수</label>
        <input type="text" id="roomNum" name="totalRoom">
      </div>
	   
	   <div class="file_area" id="file-area">
	       <div id="files-button" class="img_click_btn">
	           이미지 올리기
	           <input type="file" name="files" id="files-input" multiple>
	       </div>
	   
	       <div id="file-lists">
	       </div>
	   </div>
	   <label for="files">첨부</label>
	   
	   <input type="hidden" name="depth" value="1">    
	   <input type="hidden" id="parent-Name" name="parentName" >     
	   
	   <button type="submit">객실유형추가하기</button>
	  </form>  
  </div>
  
</div>


<jsp:include page="../../admin/layout/footer.jsp" />




