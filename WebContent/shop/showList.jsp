<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<!-- ------value setting-------- -->
<c:if test="${prd_kind=='100'}">
	<c:set var="prd_kindName" value="Mac" />
</c:if>
<c:if test="${prd_kind=='200'}">
	<c:set var="prd_kindName" value="iPad" />
</c:if>
<c:if test="${prd_kind=='300'}">
	<c:set var="prd_kindName" value="iPhone" />
</c:if>

<c:if test="${prd_kind=='400'}">
	<c:set var="prd_kindName" value="TimeMarket" />
</c:if>

<!------------------------col-lg-9 ------------------------------>
<div class="col-lg-9">
	<c:if test="${prd_kind!='all'}">
		<c:set var="display" value="${prd_kindName} 분류의 목록" />
	</c:if>
	<p class="b">${display}:(${count}개)</p>
	<!-- /.row -->
	<div class="row">
	<c:forEach var="prd" items="${prdList}">
		<div class="col-lg-4 col-md-6 mb-4">
			<div class="card h-100">
				<a href="/shoppingmall/prdContent.do?prd_id=${prd.getPrd_id()}&prd_kind=${prd.getPrd_kind()}"><img class="card-img-top"
					src="/shoppingmall/prdImage/${prd.getPrd_image()}" alt=""></a>
				<div class="card-body">
					<h4 class="card-title">
						<a href="/shoppingmall/prdContent.do?prd_id=${prd.getPrd_id()}&prd_kind=${prd.getPrd_kind()}">${prd.getPrd_name()}</a>
					</h4>
					<h5>
					<c:set var="price" value="${prd.getPrd_price()}" />  
					<fmt:formatNumber value="${price}" type="number" pattern="#,##0" />원
					</h5>
					<p class="card-text">${prd.getPrd_content() }</p>
					<c:if test="${prd.getPrd_count()==0}">일시품절</c:if> 
					<c:if test="${prd.getPrd_count()!=0}">구매가능</c:if>
				</div>
				<div class="card-footer">
					<small class="text-muted">&#9733; &#9733; &#9733; &#9733;
						&#9734;</small>
				</div>
			</div>
		</div>
	</c:forEach>
	</div>	
	<!-- /.row -->
</div>
<!------------------ /.col-lg-9--------------------------->
