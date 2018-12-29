$(document).ready(function() {
	$("#top1").click(function() {
		window.history.back();
	});
	$("#top3").click(function() {
		$(".dropdown-content").slideToggle("slow");
	});
	$("#a2").click(function() {
		window.location.href = "StudentMain.html";
	});
	$("#a3").click(function() {
		window.location.href = "StudentSeminar1.html";
	});
});