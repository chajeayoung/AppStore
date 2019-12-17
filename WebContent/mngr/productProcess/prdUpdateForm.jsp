<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="/shoppingmall/css/style.css"/>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/shoppingmall/js/jquery.form.min.js"></script>
<script src="/shoppingmall/mngr/productProcess/prdupdate.js"></script>

<c:if test="${empty sessionScope.id}">
  <meta http-equiv="Refresh" content="0;url=/shoppingmall/mg/managerMain.do" >
</c:if>

<div id="header">
  <button id="prdMain">관리자 메인으로</button>
  <button id="prdList">목록으로</button>
</div>
<form id="upForm1" action="/shoppingmall/mg/prdUpdatePro.do" 
          method="post" enctype="multipart/form-data">
<div id="prdUpdateForm" class="box">
   <ul>
      <li><label for="prd_kind">분류선택</label>
          <select id="prd_kind" name="prd_kind">
            <option value="100"
            <c:if test="${prd_kind == 100}">selected</c:if>
            >Mac</option>
            <option value="200"
            <c:if test="${prd_kind == 200}">selected</c:if>
            >iPad</option>
            <option value="300"
            <c:if test="${prd_kind == 300}">selected</c:if>
            >iPhone</option>
              <option value="400"
            <c:if test="${prd_kind == 400}">selected</c:if>
            >Time Market</option>
          </select>
      <li><label for="prd_name">상품이름</label>
          <input id="prd_name" name="prd_name" type="text" 
           size="50" maxlength="50" value="${prd.prd_name}">
          <input type="hidden" name="prd_id" value="${prd_id}">
      <li><label for="prd_price">가격</label>
          <input id="prd_price" name="prd_price" type="text" 
           size="10" maxlength="9" value="${prd.prd_price}">원
      <li><label for="prd_count">수량</label>
          <input id="prd_count" name="prd_count" type="text" 
           size="10" maxlength="5" value="${prd.prd_count}">권
      <li><label for="end_time">종료 시간</label> <input id="end_time"
				name="end_time" type="text" size="30" placeholder="년월일시분초"
				maxlength="22">
      <li><label for="prd_image">상품 이미지</label>
          <input id="prd_image" name="prd_image" type="file">${prd.prd_image}  
      <li><label for="prd_content">내용</label>
          <textarea id="prd_content" name="prd_content" 
                rows="13" cols="50">${prd.prd_content}</textarea>
 
      <li class="label2">
          <input type="submit" id="updatePrd" value="수정">
   </ul>
</div>
</form>
