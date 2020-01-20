<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/shop-homepage.css" rel="stylesheet">
<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>


<!-- -----------------css -->
<div class="col-lg-9">

	<div id="carouselExampleIndicators" class="carousel slide my-4"
		data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target="#carouselExampleIndicators" data-slide-to="0"
				class="active"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
		</ol>
		<div class="carousel-inner" role="listbox">
			<div class="carousel-item active">
				<img class="d-block img-fluid" src="images/iPad1.jpg"
					alt="First slide">
			</div>
			<div class="carousel-item">
				<img class="d-block img-fluid" src="images/iPad2.jpg"
					alt="Second slide">
			</div>
			<div class="carousel-item">
				<img class="d-block img-fluid" src="images/Mac1.png"
					alt="Third slide">
			</div>
		</div>
		<a class="carousel-control-prev" href="#carouselExampleIndicators"
			role="button" data-slide="prev"> <span
			class="carousel-control-prev-icon" aria-hidden="true"></span> <span
			class="sr-only">Previous</span>
		</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
			role="button" data-slide="next"> <span
			class="carousel-control-next-icon" aria-hidden="true"></span> <span
			class="sr-only">Next</span>
		</a>
	</div>

	<div id="shop" class="box2">
		<c:forEach var="prdList" items="${prdLists}">
			<c:set var="prd_kind" value="${prdList[0].getPrd_kind()}" />
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
			<p class="b">
				[${prd_kindName}] 분류의 신제품:<a
					href="/shoppingmall/list.do?prd_kind=${prd_kind}">더보기</a>
			</p>

			<div class="row">
				<c:forEach var="prd" items="${prdList}">
					<div class="col-lg-4 col-md-6 mb-4">
						<div class="card h-100">
							<a
								href="/shoppingmall/prdContent.do?prd_id=${prd.getPrd_id()}&prd_kind=${prd.getPrd_kind()}"><img
								class="card-img-top"
								src="/shoppingmall/prdImage/${prd.getPrd_image()}" alt=""></a>
							<div class="card-body">
								<h4 class="card-title">
									<a
										href="/shoppingmall/prdContent.do?prd_id=${prd.getPrd_id()}&prd_kind=${prd.getPrd_kind()}">
										${prd.getPrd_name()}</a>
								</h4>
								<h5>
									<c:set var="price" value="${prd.getPrd_price()}" />
									<fmt:formatNumber value="${price}" type="number"
										pattern="#,##0" />
									원
								</h5>
								<p class="card-text">
									<c:if test="${prd.getEnd_time() != null }">
										<tr height="30">
											<td width="350">${prd.getPrd_content() }</td>
										</tr>
									</c:if>
									<c:if test="${prd.getEnd_time() == null }">
										<tr height="30">
											<td width="350">${prd.getPrd_content() }</td>
										</tr>
									</c:if>
									<c:if test="${prd.getPrd_count()==0}">
            일시품절
          </c:if>
									<c:if test="${prd.getPrd_count()!=0}">
            구매가능
          </c:if>
								</p>
							</div>
							<div class="card-footer">
								<small class="text-muted">&#9733; &#9733; &#9733;
									&#9733; &#9734;</small>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>


		</c:forEach>
	</div>

	<!-- /.row -->

</div>
<!-- /.col-lg-9 -->