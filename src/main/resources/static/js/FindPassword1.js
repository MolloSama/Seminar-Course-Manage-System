$(document).ready(function() {
	$("#sure").click(function() {
		alert("success");
		var data=
		{
			"account":$("#UserID").val()		
		}
		$.ajax({
			url : '/user/password',
			type : "POST",
			contentType: "application/json;charest=utf-8",
			data : JSON.stringify(data),
			success : function(result) {
				alert("密码已发送至您的邮箱");
			},	
			error : function() {
				alert("找回密码失败！");
			}
		});
	});
});