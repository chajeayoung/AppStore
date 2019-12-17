<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="/shoppingmall/css/style.css"/>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/shoppingmall/mngr/productProcess/prdlist.js"></script>

<c:if test="${empty sessionScope.id}">
  <meta http-equiv="Refresh" content="0;url=/shoppingmall/mg/managerMain.do" >
</c:if>

<div id="listHeader">
  <p>등록된 상품 목록(전체 상품:${count})
  <button id="regist">상품 등록</button>
  <button id="prdMain">관리자 메인으로</button>
</div>
<div id="prds">
  <c:if test="${count == 0}">
    <ul>
      <li>등록된 상품이 없습니다.
    </ul>
  </c:if>
  <c:if test="${count > 0}">
  <table> 
    <tr class="title"> 
      <td align="center"  width="30">번호</td> 
      <td align="center"  width="50">품목</td> 
      <td align="center"  width="120">이름</td>
      <td align="center"  width="70">가격</td> 
      <td align="center"  width="70">수량</td> 
      <td align="center"  width="90">이미지</td>
      <td align="center"  width="130">종료시간</td>
      <td align="center"  width="50">수정</td>
      <td align="center"  width="50">삭제</td>           
    </tr>

   <c:set var="number" value="${0}"/>
   <c:forEach var="prd" items="${prdList}">
   <tr>
    <td align="center"  width="50" >
      <c:set var="number" value="${number+1}"/>
	  <c:out value="${number}"/>
	</td>
    <td width="30">${prd.getPrd_kind()}</td> 
      <td width="100" align="left">
           ${prd.getPrd_name()}</td>
      <td width="50" align="right">${prd.getPrd_price()}</td> 
      <td width="50" align="right">
      <c:if test="${prd.getPrd_count() == 0}">
         <font color="red">일시품절</font>
      </c:if>
      <c:if test="${prd.getPrd_count() > 0}">
         ${prd.getPrd_count()}
      </c:if>
      </td> 
      <td width="50">${prd.getPrd_image()}</td>
      <td width="50">${prd.getEnd_time() }</td>
      <td width="50">
      <button id="edit" name="${prd.getPrd_id()},${prd.getPrd_kind()}" onclick="edit(this)">수정</button>
      </td>
	  <td width="50">
	  <button id="delete" name="${prd.getPrd_id()},${prd.getPrd_kind()}" onclick="del(this)">삭제</button>
	  </td> 
   </tr>
  </c:forEach>
</table>
</c:if>
</div>