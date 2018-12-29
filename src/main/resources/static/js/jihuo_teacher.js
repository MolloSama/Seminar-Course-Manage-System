$(document).ready(function()
{
	$("#sure").click(function() {
		var data=
		{
			"password": $("#password1").val()
		}
		$.ajax({
			url : '/teacher/active',
			type : "PUT",
			contentType: "application/json;charest=utf-8",
			data : JSON.stringify(data),
			dataType : "json",
			success : function(result) {
				alert("success");
			},	
			error : function() {
				alert("激活账号失败！");
			}
		});
	});
});