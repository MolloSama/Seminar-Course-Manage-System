/**
 * 
 */
$(document).ready(function() {
	localStorage.setItem("courseId",1);
	var storage=window.localStorage;
	var courseId=storage.getItem("courseId");
	var url='/course/';
	url+=courseId;
	alert(url);
	$.ajax({
		type: "GET",
		url: url,
		//data: "data",
		dataType: "json",
		async: true,
		success: function(data) {
			var presentationWeight=data['presentationWeight'];
			var questionWeight=data['questionWeight'];
			var reportWeight=data['reportWeight'];
			var minMemberNumber=data['minMemberNumber'];
			var maxMemberNumber=data['maxMemberNumber'];
			var startTeamTime=data['startTeamTime'];
			var endTeamTime=data['endTeamTime'];
			$("#presentationWeight").text(presentationWeight);
			$("#questionWeight").text(questionWeight);
			$("#reportWeight").text(reportWeight);
			$("#startTeamTime").text(startTeamTime);
			$("#endTeamTime").text(endTeamTime);
			var teamCount='';
			teamCount+=minMemberNumber+'~'+maxMemberNumber;
			$("#teamCount").text(startTeamTime);
		},
		error: function() {
			alert("获取个人信息失败！");
		}
	});

	$(".mess4").click(function() {
		window.location.href = "TeamRequestShow.html";
	});
});