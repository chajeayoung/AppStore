$(document).ready(function(){
	$("#conShopping").click(function(){//[쇼핑계속]버튼 클릭
		$(location).attr('href', "/shoppingmall/shopMain.do");
	});
	
	$("#shopMain").click(function(){//[메인으로]버튼 클릭
		$(location).attr('href', "/shoppingmall/index.do");
	});
});

function editSu(editBtn){//[수정]버튼 클릭
	
	var rStr = editBtn.name;
	var arr = rStr.split(",");
	var query = "/shoppingmall/cartUpdateForm.do?cart_id="+arr[0];
	query += "&buy_count="+arr[1];
	$(location).attr('href', query);
}

function delList(delBtn){//[삭제]버튼 클릭
	var rStr = delBtn.name;
	var query = "/shoppingmall/deleteCart.do?list="+rStr;
	$(location).attr('href', query);
}