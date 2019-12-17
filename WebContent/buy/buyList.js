$(document).ready(function(){
	$("#conShopping").click(function(){//[쇼핑계속]버튼 클릭
		$(location).attr('href', "/shoppingmall/shopMain.do");
	});
	
	$("#shopMain").click(function(){//[메인으로]버튼 클릭
		$(location).attr('href', "/shoppingmall/index.do");
	});
});
