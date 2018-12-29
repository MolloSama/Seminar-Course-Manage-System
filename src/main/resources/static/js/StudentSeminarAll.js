$(document).ready(function() {
	
	localStorage.setItem("courseId",1);//获取当前课程的ID 
	var storage=window.localStorage;
	var courseId=storage.getItem("courseId");
	var teamId;
	var classId;
	$.ajax({
		type: "GET",
        url: '/course/'+courseId+'/myTeam',
        //data: "data",
        dataType: "json",
        async: false,
        success: function(result){
        	teamId=result['id'];
        	classId=result['class1']['id'];
        },
		error: function() {
			alert("加载讨论课信息失败!");
		}
	});	
	//alert('classId:'+classId+'teamId:'+teamId)
	localStorage.setItem("classId",classId);//获取当前用户所在的队伍的班级
	localStorage.setItem("teamId",teamId);//获取当前用户的队伍ID 
	
	//alert('classId:'+classId+"teamId:"+teamId);
	
	var url='/round/'+classId+'/class';
	//alert(url);

	
    $.ajax({
        type: "GET",
        url: url,
        //data: "data",
        dataType: "json",
        async: false,
        success: function(data) {
        	
          for (var i=0;i<data.length;i++) {
            var id = data[i]['id'];
            var orderName = data[i]['orderName'];
            //alert(id+orderName);
            
			var html=
				'<p id="'+id+'" class="seminar_all" orderName="'+orderName+'">'+orderName+'</p> '+
				'<div id="'+id+'" class="first" style="display: none;"> '; 
				
				$.ajax({
					type: "GET",
			        url: '/round/'+id+'/seminar',
			        //data: "data",
			        dataType: "json",
			        async: false,
			        success: function(result){
			        	var seminar='';
			        	for(var j=0;j<result.length;j++)
						{
				        	var seminarId=result[j]['id'];
				        	var topic=result[j]['topic'];
				        	var order=result[j]['order'];
				        	//alert(seminarId+topic+order);
				        	seminar+=
				        		'<p id="'+seminarId+'" order="'+order+'" class="seminar" onclick=toSeminar('+seminarId+','+order+','+'"'+orderName+'"'+')>'+topic+'</p> ';
						}
			        	html+=seminar;
			            html+='</div> ';	
			            $("#second_section").append(html);
			            bindRoundSlide(id);	
			        },
					error: function() {
						alert("加载讨论课信息失败!");
					}
				});		
          }
        },
        error: function() {
          alert("获轮次信息失败！");
        }
    });
    $("#top3").click(function() {
        $(".dropdown-content").slideToggle("slow");
    });
    $("#a2").click(function() {
        window.location.href = "StudentMain.html";
    });
    $("#a3").click(function() {
        window.location.href = "SeminarMessage.html";
    });
    $("#top1").click(function() {
        window.history.back();
    });
});

function bindRoundSlide(id)
{
    $('[class="seminar_all"][id="'+id+'"]').click(function() {
        $('[class="first"][id="'+id+'"]').slideToggle("slow");
    });
}


function toSeminar(seminarId,roundId,roundOrderName)
{
    localStorage.setItem("seminarId",seminarId);//获取当前讨论课的ID 
    localStorage.setItem("roundId",roundId);//获取当前轮次的ID 
    localStorage.setItem("roundOrderName",roundOrderName);//获取当前轮次序列名 
    //alert("seminarId:"+seminarId+"roundId:"+roundId+"roundOrderName:"+roundOrderName);
    window.location.href = "StudentSeminar1.html";
}
