/**
 * 
 */	
$(document).ready(function() {
	localStorage.setItem("courseId",1);
	localStorage.setItem("seminarId",1);
	localStorage.setItem("classId",1);
	var classId=localStorage.getItem("classId");
	var seminarId=localStorage.getItem("seminarId");
	var courseId=localStorage.getItem("courseId");
	var classSeminarId;
	var className;
	var roundId;
	var roundName;
	var topic;
	var seminarSerial;
	var intro;
	var status;
	
	var isSub=0;
	
	$.ajax({
		type:"GET",
		url:'/course/'+courseId,
		dataType:"json",
		async: false,
		success:function(data){
			if(data['seminarMainCourse']!=null)isSub=1;
		},
		error:function(){
			alert("加载讨论课信息失败!");
		}
	});
	
	$.ajax({
		type:"GET",
		url:'/seminar/'+seminarId+'/class/'+classId,
		dataType:"json",
		async: false,
		success:function(data){
			classSeminarId=data['id'];
			roundId=data['seminar']['round']['id'];
			className=data['class1']['grade']+'-'+data['class1']['serial'];
			topic=data['seminar']['topic'];
			seminarSerial=data['seminar']['order'];
			intro=data['seminar']['intro'];
			status=data['status'];	
			roundId=data['seminar']['round']['id'];
		},
		error:function(){
			alert("加载讨论课信息失败!");
		}
	});
	
	$.ajax({
		type:"GET",
		url:'/seminar/'+seminarId+'/class/'+classId,
		dataType:"json",
		async: false,
		success:function(data){
			roundId=data['seminar']['round']['id'];
			className=data['class1']['grade']+'-'+data['class1']['serial'];
			topic=data['seminar']['topic'];
			seminarSerial=data['seminar']['order'];
			intro=data['seminar']['intro'];
			status=data['status'];	
			roundId=data['seminar']['round']['id'];
		},
		error:function(){
			alert("加载讨论课信息失败!");
		}
	});
	$.ajax({
		type:"GET",
		url:'/round/'+roundId,
		dataType:"json",
		async: false,
		success:function(data){
			roundName=data['orderName'];
		},
		error:function(){
			alert("加载讨论课信息失败!");
		}
	});
	$("#seminarClass").text(className);
	$("#roundName").text(roundName);
	$("#topic").text(topic);
	$("#order").text(seminarSerial);
	$("#intro").text(intro);
	if(status==-1)
	{
		$("#status").text("未开始");
		if(isSub)
			{
				html=
					'<div id="NOtStart"> '+
						'<button id="StartSeminar" class="but1">开始讨论课</button> '+				
					'</div> ';
			}
		else
		{
			html=
				'<div id="NOtStart"> '+
					'<button id="StartSeminar" class="but1">开始讨论课</button> '+
					'<button id="ChangeSeminar" class="but2" onclick=changeSeminar('+seminarId+')>修改讨论课信息</button> '+
				'</div> ';
		}
		$("#button").append(html);
	}
	
	if(status==0)
	{
		html=
			'<div id="ING"> '+
				'<button id="CinSeminar" class="but1">进入讨论课</button> '+
			'</div> ';
		$("#status").text("正在进行");
		$("#button").append(html);
	}
			
	if(status==1)
	{
		html=
			'<div id="Finish"> '+
				'<button id="Report" class="but1" onclick=toReport('+classSeminarId+')>书面报告</button> '+
				'<button id="LookGrade" class="but2">查看成绩</button> '+
			'</div> '; 
		$("#status").text("已结束");
		$("#bottom").append(html);
	}
	//开始讨论课
	$("#StartSeminar").click(function() {
		window.location.href = "Seminar.html";
	});
	//更改讨论课信息
	$("#ChangeSeminar").click(function() {
		window.location.href = "ChangeSeminar.html";
	});
	//查看书面报告
	$("#Report").click(function() {
		window.location.href = "Report.html";
	});
	//查看成绩
	$("#LookGrade").click(function() {
		window.location.href = "SeminarGrade.html";
	});
	//进入讨论课
	$("#CinSeminar").click(function() {
		window.location.href = "Seminar.html";
	}); 
});

function changeSeminar(seminarId)
{
	localStorage.setItem("seminarId",seminarId);
	window.location.href = "SeminarGrade.html";
}

function toReport(classSeminarId)
{
	localStorage.setItem("classSeminarId",classSeminarId);
	window.location.href = "Report.html";
}