<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="/shoppingmall/css/style.css"/>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<div id="cata">
  <ul>
    <li><a href="/shoppingmall/list.do?prd_kind=100">Mac</a></li>
    <li><a href="/shoppingmall/list.do?prd_kind=200">iPad</a></li>
    <li><a href="/shoppingmall/list.do?prd_kind=300">iPhone</a></li>
    <li><a href="/shoppingmall/list.do?prd_kind=400">TimeMarket</a></li>
  </ul>
</div>

<div id="shop" class="box2">
  <c:if test="${prd_kind=='100'}">
    <c:set var="prd_kindName" value="Mac"/>
  </c:if>
  <c:if test="${prd_kind=='200'}">
    <c:set var="prd_kindName" value="iPad"/>
  </c:if>
  <c:if test="${prd_kind=='300'}">
    <c:set var="prd_kindName" value="iPhone"/>
  </c:if>
  
  <c:if test="${prd_kind=='400'}">
    <c:set var="prd_kindName" value="TimeMarket"/>
  </c:if>
  

  <c:if test="${prd_kind!='all'}">
    <c:set var="display" value="${prd_kindName} 분류의 목록"/>
  </c:if>
  
  <p class="b">${display} : (${count}개)</p>
  <c:forEach var="prd" items="${prdList}">
    <table class="vhcenter">
      <tr height="30"> 
        <td rowspan="4"  width="100">
          <a href="/shoppingmall/prdContent.do?prd_id=${prd.getPrd_id()}&prd_kind=${prd.getPrd_kind()}">
             <img src="/shoppingmall/prdImage/${prd.getPrd_image()}" class="listimage"></a></td>
        <td width="350" class="vhcenter">
          <a href="/shoppingmall/prdContent.do?prd_id=${prd.getPrd_id()}&prd_kind=${prd.getPrd_kind()}" class="b">
              ${prd.getPrd_name()}</a></td>
        <td rowspan="4" width="100">
          <c:if test="${prd.getPrd_count()==0}">
            일시품절
          </c:if>
          <c:if test="${prd.getPrd_count()!=0}">
            구매가능
          </c:if>
       </td>      
       </tr>
       <tr height="100">
       	
        <td width="350">${prd.getPrd_content() }</td></tr>
        
       <tr height="30">
        <td width="350"><c:set var="price" value="${prd.getPrd_price()}"/>
            <c:set var="rate" value=""/>

         <strong class="bred">판매가:<fmt:formatNumber value="${price}" type="number" pattern="#,##0"/>원</strong>
        </td></tr> 
     </table>
  </c:forEach>
</div>