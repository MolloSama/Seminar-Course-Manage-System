/**
 * 
 */
$(document).ready(function() {
	$.ajax({
		type:"GET",
		url:"teacher/ChangePassword",
		dataType:"json",
		success:function(data){
			if(data.success){
				$("#MyEmail").html(data.email);}
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
	$("#top1").click(function() {
		window.history.back();
	});
	$("#sure").click(function() {
		window.location.href = "TeacherPersonal.html";
	});
	$("#a2").click(function() {
		window.location.href = "teacher_main.html";
	});
	$("#a3").click(function() {
		window.location.href = "SeminarMessage.html";
	});
});