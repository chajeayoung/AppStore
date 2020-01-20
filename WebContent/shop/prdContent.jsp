<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="/shoppingmall/css/style.css"/>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/shoppingmall/shop/prdcontent.js"></script>


<div id="showPrd">
	<input type="hidden" id="end_time" value="${prd.getEnd_time()}">
	<c:if test="${prd.getEnd_time() != null}">
		남은시간 : <span class="timer" id="timer"> </span>
	</c:if>

	<table class="vhcenter">
		<tr height="30">
			<td rowspan="6" width="150"><img
				src="/shoppingmall/prdImage/${prd.getPrd_image()}"
				class="contentimage"></td>
			<td width="500"><b>${prd.getPrd_name()}</b></td>
		</tr>

		<tr class="ch">
			<td colspan="2" class="hleft">${prd.getPrd_content()}</td>
		</tr>

		<tr>
			<td width="500"><c:set var="price" value="${prd.getPrd_price()}" />
				정가 : <fmt:formatNumber value="${price}" type="number"
					pattern="#,##0" />원
		</tr>
		<tr>

			
		</tr>

		<tr>
			<td width="500"><c:if test="${!empty sessionScope.id}">
					<c:if test="${prd.getPrd_count()==0}">
						<p>일시품절
					</c:if>
					<c:if test="${prd.getPrd_count()>=1}">
         수량 : <input type="text" size="5" id="buy_count" value="1"> 개
       </c:if>
					<input type="hidden" id="prd_id" value="${prd_id}">
					<input type="hidden" id="prd_image" value="${prd.getPrd_image()}">
					<input type="hidden" id="prd_name" value="${prd.getPrd_name()}">
					<input type="hidden" id="buy_price" value="${price}">
					<input type="hidden" id="prd_kind" value="${prd_kind}">
					<input type="hidden" id="buyer" value="${sessionScope.id}">
					<button id="insertCart">장바구니에 담기</button>
				</c:if> <c:if test="${empty sessionScope.id}">
					<c:if test="${prd.getPrd_count()==0}">
						<p>일시품절
					</c:if>
					<p>제품을 구매하시려면 로그인 하세요.
				</c:if>
				<button id="list">목록으로</button>
				<button id="shopMain">메인으로</button></td>
		</tr>


	</table>
</div>