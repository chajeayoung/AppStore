<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet" href="/shoppingmall/css/style.css" />
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="/shoppingmall/js/jquery.form.min.js"></script>
<script src="/shoppingmall/mngr/productProcess/prdregist.js"></script>

<c:if test="${empty sessionScope.id}">
	<meta http-equiv="Refresh"
		content="0;url=/shoppingmall/mg/managerMain.do">
</c:if>

<div id="listHeader">
	<button id="prdMain">관리자 메인으로</button>
	<button id="prdList">목록으로</button>
</div>
<form id="upForm1" action="/shoppingmall/mg/prdRegisterPro.do"
	method="post" enctype="multipart/form-data">
	<div id="prdRegistForm" class="box">
		<ul>
			<li><label for="prd_kind">분류선택</label> <select id="prd_kind"
				name="prd_kind">
					<option value="100">Mac</option>
					<option value="200">iPad</option>
					<option value="300">iPhone</option>
					<option value="400">Time Market</option>
			</select>
			<li><label for="prd_name">상품이름</label> <input id="prd_name"
				name="prd_name" type="text" size="50" placeholder="상품이름"
				maxlength="50">
			<li><label for="prd_option1">옵션1</label> <input id="option1"
				name="option1" type="text" size="50" placeholder="옵션1"
				maxlength="50">
			<li><label for="prd_option2">옵션2</label> <input id="option2"
				name="option2" type="text" size="50" placeholder="옵션2"
				maxlength="50">
			<li><label for="prd_count">옵션수량</label> <input id="prd_count"
				name="prd_count" type="text" size="10" placeholder="수량"
				maxlength="5">개
			<li><label for="prd_price">가격</label> <input id="prd_price"
				name="prd_price" type="text" size="10" placeholder="가격"
				maxlength="9">원
			<li><label for="end_time">종료 시간</label> <input id="end_time"
				name="end_time" type="text" size="30" placeholder="ex) 20201010230000"
				maxlength="22">
			<li><label for="prd_image">상품 이미지</label> <input id="prd_image"
				name="prd_image" type="file">
			<li><label for="prd_content">내용</label> <textarea
					id="prd_content" name="prd_content" rows="13" cols="50"></textarea>
			<li class="label2"><input type="submit" id="registPrd"
				value="상품등록">
		</ul>
	</div>
</form>
