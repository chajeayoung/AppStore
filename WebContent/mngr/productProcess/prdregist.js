$(document).ready(function(){
	$("#upForm1").ajaxForm({// 이미지를 포함한 상품등록
		success: function(data, status){// 업로드에 성공하면 수행
			$(location).attr('href', "/shoppingmall/mg/prdList.do?prd_kind=all");
   		}
    });
	
	$("#prdMain").click(function(){// [관리자 메인으로]버튼 클릭
		$(location).attr('href', "/shoppingmall/mg/managerMain.do");
	});
	
	$("#prdList").click(function(){// [목록으로]버튼 클릭
		$(location).attr('href', "/shoppingmall/mg/prdList.do?prd_kind=all");
	});
	

});