 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="/shoppingmall/css/style.css"/>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/shoppingmall/mngr/managermain.js"></script>
    
<c:if test="${empty sessionScope.id}">
  <div id="mList"><p>로그인 하세요.
  </div>
</c:if>
<c:if test="${!empty sessionScope.id}">
  <div id="mList">
     <ul>
        <li>상품관련 작업
        <li><button id="registProduct">상품등록</button>
        <li><button id="updateProduct">상품수정/삭제</button>
     </ul>
     <ul>
        <li>구매된 상품관련 작업
        <li><button id="orderedProduct">전체주문목록 확인</button>
        <li><button id="memberList">회원 목록</button>
     </ul>
  </div>
  <div id="result">
  </div>
</c:if>     