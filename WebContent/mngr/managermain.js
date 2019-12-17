
var status = true; 
//[수정]버튼을 클릭하면 자동실행
function edit(editBtn){
	var rStr = editBtn.name;
	var arr = rStr.split(",");
	var query = "/shoppingmall/mg/prdUpdateForm.do?prd_id="+arr[0];
	query += "&prd_kind="+arr[1];
	$(location).attr('href', query);
}

//[삭제]버튼을 클릭하면 자동실행
function del(delBtn){
	var rStr = delBtn.name;
	var arr = rStr.split(",");
	var query = "/shoppingmall/mg/prdDeletePro.do?prd_id="+arr[0];
	query += "&prd_kind="+arr[1];
	$(location).attr('href', query);
}

$(document).ready(function(){
	$("#registProduct").click(function(){// [상품등록]버튼 클릭
		$(location).attr('href', "/shoppingmall/mg/prdRegisterForm.do");
	});
	
	$("#updateProduct").click(function(){// [상품수정/삭제]버튼 클릭
		$.ajax({
			url : '/shoppingmall/prdListJson.api?prd_kind=all',
			type : 'GET',
			datatype : 'json'
		}).done(function(data){
			console.log("통신완료");
			var len = data.length;
			var count_status = "";
			var buffer = ['<table>'];
			buffer.push('<tr><td>번호</td><td>품목</td><td>이름</td><td>가격</td><td>수량</td><td>종료시간</td><td>수정</td><td>삭제</td>></tr>');
			for(var i=0; i<len; i++){
				buffer.push('<tr>');
				buffer.push('<td>', i+1, '</td>');
				buffer.push('<td>', data[i].prd_kind, '</td>');
				buffer.push('<td>', data[i].prd_name, '</td>');
				buffer.push('<td>', data[i].prd_price, '</td>');
				buffer.push('<td>', data[i].prd_count, '</td>');
				buffer.push('<td>', data[i].end_time, '</td>');
				buffer.push('<td><button id="edit" name="', data[i].prd_id,',',data[i].prd_kind, '" onclick="edit(this)">수정</button></td>');
				buffer.push('<td><button id="delete" name="',data[i].prd_id,',',data[i].prd_kind ,'" onclick="del(this)">삭제</button></td>');
				buffer.push('</tr>');
			}
			buffer.push( '</table>' );
			console.log(buffer);
			$('#result').html(buffer.join(''));
		});
	});
	
	$("#orderedProduct").click(function(){// [전체주문목록 확인]버튼 클릭
		$.ajax({
			url : '/shoppingmall/orderListJson.api',
			type : 'GET',
			datatype : 'json'
				
		}).done(function(data){
			console.log("통신완료");
			var len = data.length;
			var price = 0;
			var buffer = ['<table>'];
			buffer.push('<tr><td>주문번호</td><td>주문자</td><td>상품명</td><td>가격</td><td>주문수량</td><td>금액</td><td>주문일</td>',
					'<td>배송명</td><td>배송지전화</td><td>배송지주소</td><td>배송상황</td></tr>');
			for(var i=0; i<len; i++){
				price = parseInt(data[i].buy_price) * parseInt(data[i].buy_count);
				buffer.push('<tr>');
				buffer.push('<td>', data[i].buy_id, '</td>');
				buffer.push('<td>', data[i].buyer, '</td>');
				buffer.push('<td>', data[i].prd_name, '</td>');
				buffer.push('<td>', data[i].buy_price, '</td>');
				buffer.push('<td>', data[i].buy_count, '</td>');
				buffer.push('<td>', price, '</td>');
				buffer.push('<td>', data[i].buy_date, '</td>');
				buffer.push('<td>', data[i].deliveryName, '</td>');
				buffer.push('<td>', data[i].deliveryTel, '</td>');
				buffer.push('<td>', data[i].deliveryAddress, '</td>');
				buffer.push('<td>', data[i].sanction, '</td>');
				buffer.push('</tr>');
			}
			buffer.push( '</table>' );
			console.log(buffer);
			$('#result').html(buffer.join(''));
		});
	});
	
	$("#memberList").click(function(){
		$.ajax({
			url : '/shoppingmall/memberJson.api',
			type : 'GET',
			datatype : 'json'
		}).done(function(data){
			var len = data.length;
			var buffer = ['<table>'];
			buffer.push('<tr><td>아이디</td><td>이름</td><td>주소</td><td>가입일</td><td>폰번호</td></tr>');
			for(var i=0; i<len; i++){
				buffer.push('<tr>');
				buffer.push('<td>', data[i].id, '</td>');
				buffer.push('<td>', data[i].name, '</td>');
				buffer.push('<td>', data[i].address, '</td>');
				buffer.push('<td>', data[i].reg_date, '</td>');
				buffer.push('<td>', data[i].tel, '</td>'); 
				buffer.push('</tr>');
			}
			buffer.push( '</table>' );
			console.log(buffer);
			$('#result').html(buffer.join(''));
		});
	});
	
});