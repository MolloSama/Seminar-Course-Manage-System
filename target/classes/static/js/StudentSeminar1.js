	$(document).ready(function() {
		var roundOrderName=localStorage.getItem("roundOrderName");//获取当前轮次序列名 
	    var seminarId=localStorage.getItem("seminarId");//获取当前讨论课的ID 
	    var classId=localStorage.getItem("classId");//获取当前课程班级的ID 
	    var teamId=localStorage.getItem("teamId");//获取当前相应课程小组的ID 
	    //var url='/seminar/'+seminarId+'/class/'+classId;
	    //alert(url);
		$.ajax({
			type: "GET",
	        url: '/seminar/'+seminarId+'/class/'+classId,
	        //data: "data",
	        dataType: "json",
	        async: false,
	        success: function(result){
	        	
	        	id=result['id'];
	        	seminar=result['seminar'];
	        	class1=result['class1'];
	        	status=result['status'];
	        	var reportDDL=result['reportDDL'];
	        	var topic=seminar['topic'];
	        	var intro=seminar['intro'];
	        	var order=seminar['order'];
	        	var signupStartTime=seminar['signupStartTime'];
	        	var signupEndTime=seminar['signupEndTime'];
	        	var className=class1["name"];
	        	var signStatusOver;
	        	var pptNameOver;
	        	var reportNameOver;
	    	    var attendanceId=0;
	    	    var teamOrder=0;
	        	
	        	//alert('/attendance/'+teamId+'/team/'+id+'/classSeminar');
	        	$.ajax({
	        		type: "GET",
	                url: '/attendance/'+teamId+'/team/'+id+'/classSeminar',
	                //data: "data",
	                dataType: "json",
	                async: false,
	                success: function(result){
	                	var signStatus=result['status'];
	                	//alert('/attendance/'+teamId+'/team/'+id+'/classSeminar');
	                	signStatusOver=signStatus;
	                	if(signStatus=="sign")
	                		{
	                			attendanceId=result['attendance']['id'];
	                			teamOrder=result['attendance']['teamOrder'];
	                			var pptName=result['attendance']['pptName'];
	                			var	reportName=result['attendance']['reportName'];
	                			pptNameOver=pptName;
	                			reportNameOver=reportName;     			
	                		    signHtml=
	                				'<label> <span '+
	                				'id="ClassName">'+className+'</span>&nbsp;&nbsp;&nbsp;&nbsp;第<span '+
	                				'id="ShowRound">'+teamOrder+'</span>组 '+
	                				'</label> ';
	                			$("#signStatus").append(signHtml);
	                			if(pptName!=null)
	                				{
	                					$("#ppt_position").text(pptName);
	                				}
	                			else
	                				{
	                					$("#ppt_position").text("未提交");
	                				}
	                			if(reportName!=null)
                				{
                					$("#report_position").text(reportName);
                				}
	                			else
                				{
                					$("#report_position").text("未提交");
                				}
	                		}
	                	else 
	                		{
	                			$("#signStatus").text("未报名");
	                			$("#ppt_position").text("未提交");
	                			$("#report_position").text("未提交");
	                		}
	                	
	                },
	        		error: function() {
	        			alert("加载队伍报名信息失败!");
	        		}
	        	});	
				//alert("fuck you!");
	        	$("#roundOrderName").text(roundOrderName);
	        	$("#topic").text(topic);
	        	$("#order").text("第"+order+"次");
	        	$("#intro").text(intro);
	        	$("#signupStartTime").text(signupStartTime);
	        	$("#signupEndTime").text(signupEndTime);
	        	buttonHtml='';
	        	//alert(status);
	        	//alert(signStatusOver);
	        	if(status==-1)
	        		{
	        			$("#status").text("未开始");
	        			//alert('id:'+id+'status:'+status+'attendanceId:'+attendanceId);
	        			//alert(signStatusOver);
	        			if(signStatusOver=="noSign")
	        				{
	        				$("#selectButton").append('<button id="enroll" onclick=signUp('+id+','+status+','+attendanceId+',"'+signStatusOver+'")>报名</button> ');
	        				}
	        			else
	        				{
				        		buttonHtml=
				        			'<div id="HandIn"> '+
				        				'<button id="sub1" style="background: rgb(20, 255, 70); color: white;" onclick=signUp('+id+','+status+','+attendanceId+',"'+signStatusOver+'")>上传PPT</button> '+
				        			'</div> ';
				        		$("#selectButton").append(buttonHtml);
	        				}
	        		}
	        	
	            if(status==0)
	            	{
	            		$("#status").text("正在进行");
	            		if(signStatusOver=="noSign")
	            			{	
		            			$("#selectButton").append('<button id="SCinSeminar" onclick=toSeminar('+id+',"'+signStatusOver+'")>进入讨论课</button> ');
	            			}
	            		if(signStatusOver=="sign")
	            			{
			        			buttonHtml=
			        				'<div id="HandIn"> '+
			        					'<button id="sub1" style="background: rgb(20, 255, 70); color: white;" onclick=signUp('+id+','+status+','+attendanceId+',"'+signStatusOver+'")>上传PPT</button> '+
			        				'</div> ';
			        			$("#selectButton").append(buttonHtml);
	            			}
	            			
	            	}
	        	if(status==1)
	        		{
	        			$("#status").text("已结束");
	        			var tNow=(new Date()).valueOf();
	        			var reportDeadLine=(new Date(reportDDL)).valueOf();
	        			if(reportDeadLine-tNow>0&&reportNameOver==null)
	        			{
	        				buttonHtml=
	        					'<div id="HandIn"> '+
	        					'<button id="sub2" style="background: white; color: rgb(20, 255, 70);">上传书面报告</button> '+
	        					'</div> ';
	        			}
	        			buttonHtml+='<button id="Grade" onclick=toGrade('+id+','+teamOrder+')>查看成绩</button> ';
	        			$("#selectButton").append(buttonHtml);	
	        		}
	        	
	        },
			error: function() {
				alert("加载讨论课信息失败!");
			}
	       
		});	
		$("#top1").click(function() {
			window.history.back();
		});
		$("#a2").click(function() {
			window.location.href = "teacher_main.html";
		});
		$("#a3").click(function() {
			window.location.href = "SeminarMessage.html";
		});
		
	});
	function signUp(classSeminarId,status,attendanceId,signStatusOver)
	{
		localStorage.setItem("classSeminarId",classSeminarId);
		localStorage.setItem("attendanceId",attendanceId);
		localStorage.setItem("status",status);
		localStorage.setItem("signStatusOver",signStatusOver);
		window.location.href = "SeminarEnroll.html";
	}

	function toSeminar(classSeminarId,signStatusOver)
	{
		localStorage.setItem("classSeminarId",classSeminarId);
		localStorage.setItem("signStatusOver",signStatusOver);
		if(signStatusOver=="sign")
			{
				window.location.href = "SEnrollInfo.html";
			}
		else window.location.href = "SSeminarShowing.html";
	}
	
	function toGrade(classSeminarId,teamOrder)
	{
		localStorage.setItem("classSeminarId",classSeminarId);
		localStorage.setItem("teamOrder",teamOrder);
		window.location.href = "SSeminarGrade.html";
	}
	
	/*
	function uploadPPT()
	function uploadReport()
	*/