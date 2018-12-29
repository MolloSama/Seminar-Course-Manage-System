$(document).ready(function() {
	var roundOrderName=localStorage.getItem("roundOrderName");//获取当前轮次序列名 
	var classSeminarId=localStorage.getItem("classSeminarId");//获取当前班级讨论课的ID 
    var teamOrder=localStorage.getItem("teamOrder");//获取当前队伍的队伍序列
    var classId=localStorage.getItem("classId");//获取当前班级ID
    var seminarId=localStorage.getItem("seminarId");//获取当前讨论课ID
    var topic;
    var seminarOrder;
    var intro;
    var className;
    var reportScore;
    var questionScore;
    var presentationScore;
    var totalScore;
    //alert('seminarId:'+seminarId+'classId:'+classId);
	$.ajax({
		type: "GET",
        url: '/seminar/'+seminarId+'/class/'+classId,
        //data: "data",
        dataType: "json",
        async: false,
        success: function(result){
        	topic=result['seminar']['topic'];
        	seminarOrder=result['seminar']['order'];
        	intro=result['seminar']['intro'];
        	className=result['class1']['name'];
        },
		error: function() {
			alert("加载讨论课信息失败!");
		}
	});	
	$.ajax({
		type: "GET",
        url: '/seminarscore/'+classSeminarId,
        //data: "data",
        dataType: "json",
        async: false,
        success: function(result){
        	reportScore=result['reportScore'];
        	questionScore=result['questionScore'];
        	presentationScore=result['presentationScore'];
        	totalScore=result['totalScore'];
        },
		error: function() {
			alert("加载讨论课信息失败!");
		}
	});	
	$("#roundOrderName").text(roundOrderName);
	$("#topic").text(topic);
	$("#order").text('第'+seminarOrder+'次');
	$("#intro").text(intro);
	$("#className").text(className);
	$("#ShowRound").text(teamOrder);
	$("#presentation").text(presentationScore);
	$("#question").text(questionScore);
	$("#report").text(reportScore);
	$("#total").text(totalScore);
});