
$(document).ready(function() {
	$.ajax({
		type:"GET",
		url:"teacher/CourseAll",
		dataType:"json",
		success:function(data){
			if(data.success){
				for(x in data)
					{
					$("<div ></div>")
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
	$("#ooad").click(function() {
		$(".ooad").slideToggle("slow");
	});
	$("#j2ee").click(function() {
		$(".j2ee").slideToggle("slow");
	});
	$("#newCourse").click(function() {
		window.location.href = "NewCourse.html";
	});
	$(".grade").click(function() {
		window.location.href = "grade.html";
	});
	$(".team").click(function() {
		window.location.href = "Team.html";
	});
	$(".course").click(function() {
		window.location.href = "CourseMessage.html";
	});
	$(".class").click(function() {
		window.location.href = "ClassMessage.html";
	});
	$(".seminar").click(function() {
		window.location.href = "SeminarMessage.html";
	});
	$(".share").click(function() {
		window.location.href = "Share.html";
	});
	$("#a3").click(function() {
		window.location.href = "SeminarMessage.html";
	});
	$("#a2").click(function() {
		window.location.href = "teacher_main.html";
	});
	$("#top1").click(function() {
		window.history.back();
	});
	$("#a3").click(function() {
		window.location.href = "SeminarMessage.html";
	});
});