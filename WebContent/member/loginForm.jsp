<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/shoppingmall/member/login.js"></script>

<c:if test="${empty sessionScope.id}">
	<div class="list-group">

		<a><label for="cid">I D</label> <input id="cid" name="cid"
			type="email" size="5" maxlength="50"></a> <a><label
			for="cpasswd">PW</label> <input id="cpasswd" name="cpasswd"
			type="password" size="5" maxlength="16"></a> <a><button
				id="uLogin">로그인</button> </a> <a><button id="uRes">등 록</button>
		</a> <a><button id="mLogin">관리자</button></a>

	</div>
</c:if>
<c:if test="${!empty sessionScope.id}">
	<div id="lStatus">
		${sessionScope.id}님이로그인하셨습니다.
		<div class="list-group">
			<a><button id="uLogout">로그아웃</button></a>
			<a><button id="uUpdate">회원정보</button></a>
			
			<form id="cartForm" method="post" action="/shoppingmall/cartList.do">
				<input type="hidden" name="buyer" value="${sessionScope.id}">
				<input type="submit" name="cart" value="장바구니">
			</form>
			
			
			<form id="buyForm" method="post" action="/shoppingmall/buyList.do">
				<input type="hidden" name="buyer" value="${sessionScope.id}">
				<input type="submit" name="buy" value="구매내역">
			</form>
			

		</div>

	</div>
</c:if>
