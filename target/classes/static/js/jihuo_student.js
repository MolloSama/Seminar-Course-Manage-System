$(document).ready(function()
{
	$("#sure").click(function() {
		var data=
		{
			"password": $("#password1").val(),
			"email": $("#email").val()
		}
		$.ajax({
			url : '/student/active',
			type : "PUT",
			contentType: "application/json;charest=utf-8",
			data : JSON.stringify(data),
			dataType : "json",
			success : function(result) {
				alert(result["status"]);
			},	
			error : function() {
				alert("激活账号失败！");
			}
		});
	});
});