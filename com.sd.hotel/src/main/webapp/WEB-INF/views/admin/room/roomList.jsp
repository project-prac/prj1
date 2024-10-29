<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<c:set var="dt" value="<%=System.currentTimeMillis()%>" />

<link rel="stylesheet" href="/resources/admin/css/sd-room.css">

<jsp:include page="../../admin/layout/header.jsp" />

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.3.16/themes/default/style.min.css">






<div class="main-panel">
  <div class="content-wrapper">
    <div class="row">
      <div class="col-sm-12">
        <div class="home-tab">
          <style>
          .tab-inner{width:100%;height:120px;background:#fff;}
          </style>
          <div class="tab-content tab-content-basic">
            <div class="card">
              <div class="card-body">
                <h4>객실 목록</h4>
                
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
                        <div id="imgModify-button">
                            내 PC
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
                <!-- 객실대분류 -->
                
                <div class="room-area" >
                  <div class="room-area-header">객실대분류 추가하기<span>▼</span></div>
                  <form id="roomNoRegister"
                       enctype="multipart/form-data">
                    <select id="roomNo-list" name="roomNo">
                      
                    </select>
                    <label for="roomNo-roomName">객실명</label>
                    <input type="text" id="roomNo-roomName" name="roomName">      
                    <input type="hidden" name="depth" value="0">    
                    <input type="hidden" name="parentName" value="#">    
                    
                    <button type="submit">객실대분류 추가하기</button>
                        
                  </form>
                </div>
                <!-- 객실유형분류 -->
                <div class="room-area">
                   <div class="room-area-header">객실유형 추가하기<span>▼</span></div>
                   <form id="roomTypeRegisterForm"
                         enctype="multipart/form-data">
                    <select id="roomNo-list2" name="roomNo"></select>    <br> 
                    <label for="roomType-roomName">객실명</label>
                    <input type="text" id="roomType-roomName" name="roomName">     <br> 
                    <label for="roomType-roomName">정보</label>
                    <input type="text" id="roomType-roomName" name="info"><br>
                    <label for="roomType-roomName">가격</label>
                    <input type="text" id="roomType-roomName" name="price"><br>
                    <label for="roomType-roomName">인원수</label>
                    <input type="text" id="roomType-roomName" name="people"><br>
                    <label for="roomNum">객실수</label>
                    <input type="text" id="roomNum" name="roomNum"><br>
                    
										<div class="file_area" id="file-area">
										    <div id="files-button">
										        내 PC
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
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- content-wrapper ends -->
  <!-- partial:partials/_footer.html -->
  <footer class="footer">
    <div
      class="d-sm-flex justify-content-center justify-content-sm-between">
      <span
        class="text-muted text-center text-sm-left d-block d-sm-inline-block">Premium
        <a href="https://www.bootstrapdash.com/" target="_blank">Bootstrap
          admin template</a> from BootstrapDash.
      </span> <span
        class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center">Copyright
        © 2021. All rights reserved.</span>
    </div>
  </footer>
  <!-- partial -->
</div>




<!-- main-panel ends

-->



<script src="${contextPath}/resources/js/roomList.js"></script>
<jsp:include page="../../admin/layout/footer.jsp" />




