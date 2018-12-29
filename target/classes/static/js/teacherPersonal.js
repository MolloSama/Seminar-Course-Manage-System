/**
 * 
 */
$(document).ready(function() {
	$.ajax({
		type:"GET",
		url:"teacher/TeacherInformation",
		dataType:"json",
		success:function(data){
			if(data.success){
				$("#name").html(data.name);
				$("#email").html(data.email);
				$("#id").html(data.id);
				if(!window.localStorage){
		            alert("浏览器不支持localstorage");
		        }else{
		            var storage=window.localStorage;
		            storage.setItem("email",data.email);
		        }
			}
			else
				alert("出现错误"+data.msg);
		},
		error:function(jqXHR){
			alert("出现错误"+jqXHR.status);
		}
	});

	$("#top3").click(function() {
		$(".dropdown-content").slideToggle("slow");
	});
	$("#change1").click(function() {
		window.location.href = "ChangeEmail.html";
	});
	$("#change2").click(function() {
		window.location.href = "ChangePassword.html";
	});
	$("#logeout").click(function() {
		window.location.href = "../index.html";
	});
	$("#a2").click(function() {
		window.location.href = "teacher_main.html";
	});
	$("#a3").click(function() {
		window.location.href = "SeminarMessage.html";
	});
	$("#top1").click(function() {
		window.history.back();
	});
});