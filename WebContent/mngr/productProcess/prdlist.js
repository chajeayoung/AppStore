$(document).ready(function(){
	
	$("#regist").click(function(){//[책등록]버튼 클릭
		$(location).attr('href', "/shoppingmall/mg/prdRegisterForm.do");
	});
	
	$("#prdMain").click(function(){//[관리자 메인으로]버튼 클릭
		$(location).attr('href', "/shoppingmall/mg/managerMain.do");
	});
});

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