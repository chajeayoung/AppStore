




function msg_time() {
	var date = $("#end_time").val();
	var rdate = date.substr(0, 19);
	var stDate = new Date().getTime();
	var edDate = new Date(rdate).getTime(); // 종료날짜
	var RemainDate = edDate - stDate;
	var hours = Math.floor((RemainDate % (1000 * 60 * 60 * 24))
			/ (1000 * 60 * 60));
	var miniutes = Math.floor((RemainDate % (1000 * 60 * 60)) / (1000 * 60));
	var seconds = Math.floor((RemainDate % (1000 * 60)) / 1000);

	m = hours + ":" + miniutes + ":" + seconds; // 남은 시간 text형태로 변경

	document.all.timer.innerHTML = m; // div 영역에 보여줌

	if (RemainDate < 0) {
		// 시간이 종료 되었으면..
		clearInterval(tid); // 타이머 해제
		$("#insertCart").attr('disabled', 'disabled');
		document.all.timer.innerHTML = "판매종료!!";
	} else {
		RemainDate = RemainDate - 1000; // 남은시간 -1초
	}
}
function edit(editBtn) {// [수정]버튼 클릭
	var rStr = editBtn.name;
	var arr = rStr.split(",");
	var query = "/shoppingmall/qnaUpdateForm.do?qna_id=" + arr[0];
	query += "&prd_kind=" + arr[1];
	$(location).attr('href', query);
}

function del(delBtn) {// [삭제]버튼 클릭
	var rStr = delBtn.name;
	var arr = rStr.split(",");

	var query = {
		qna_id : arr[0]
	};
	$.ajax({
		type : "POST",
		url : "/shoppingmall/qnaDeletePro.do",
		data : query,
		success : function(data) {
			var str1 = '<p id="ck">';
			var loc = data.indexOf(str1);
			var len = str1.length;
			var check = data.substr(loc + len, 1);
			if (check == "1") {//
				alert("QnA가 삭제 되었습니다.");
				var query = "/shoppingmall/prdContent.do?prd_id=" + arr[1];
				query += "&prd_kind=" + arr[2];
				$(location).attr('href', query);
			} else
				// 사용할 수 있는 아이디
				alert("QnA가 삭제 실패");
		}
	});
}

$(document).ready(function() {

	tid = setInterval('msg_time()', 1000); // 타이머 1초간격으로 수행

	$("#insertCart").click(function() {// [장바구니에 담기]버튼 클릭
		var buyer = $("#buyer").val();
		var prd_kind = $("#prd_kind").val();
		var query = {
			prd_id : $("#prd_id").val(),
			buy_count : $("#buy_count").val(),
			prd_image : $("#prd_image").val(),
			prd_name : $("#prd_name").val(),
			buy_price : $("#buy_price").val(),
			buyer : buyer
		};
		$.ajax({
			type : "POST",
			url : "/shoppingmall/insertCart.do",
			data : query,
			success : function(data) {
				alert("장바구니에 담겼습니다.");
			}
		});
	});

	$("#list").click(function() {// [목록으로]버튼 클릭
		$(location).attr('href', "/shoppingmall/list.do?prd_kind=all");
	});

	$("#shopMain").click(function() {// [메인으로]버튼 클릭
		$(location).attr('href', "/shoppingmall/index.do");
	});


});
