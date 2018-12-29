
/**
 * @Description 对应SeminarEnroll.html的JS脚本,POST未完成!
 * @author Weilun Zhang
 * @version v1.0
 * @date 2018/12/16
 */
$(document).ready(function() {
	var classSeminarId=localStorage.getItem("classSeminarId");//获取相应的班级讨论课ID
	var signStatusOver=localStorage.getItem("signStatusOver");//获取此班级讨论课的状态
    var seminarId=localStorage.getItem("seminarId");//获取当前讨论课的ID 
    var classId=localStorage.getItem("classId");//获取当前课程班级的ID 
    var teamId=localStorage.getItem("teamId");//获取当前相应课程小组的ID 
	var attendanceId=localStorage.getItem("attendanceId");//获取小组报名情况Id
	var status=localStorage.getItem("status");//获取当前讨论课的状态
	var teamNumberLimit;
	//alert(seminarId);
	//alert(status);
	//alert(signStatusOver);
	
	$.ajax({
		url : '/seminar/'+seminarId,
		type : "GET",
		dataType : "json",
		async: false,
		success : function(result) {
			//alert("success!!!!!!!!");
			teamNumberLimit=result['teamNumLimit'];
		},
		error : function() {
			alert("加载讨论课信息失败！");
		}
	});
	
	html='';
	//alert(teamNumberLimit);
	for(var j=1;j<teamNumberLimit+1;j++)
		{
			html=
				'<p id="'+j+'" class="Team" style="background: white;"> '+
				'<span class="promopt" >第'+j+'组：</span> <span class="TeamNo" id="'+j+'"> '+
				'</span>可报名</p> ';
				$("#secondSection").append(html);
				bindSignUp(j);
		}
	
	$.ajax({
		url : '/attendance/'+classSeminarId+'/attendance',
		type : "GET",
		dataType : "json",
		async: false,
		success : function(result) {		
			//var teamNumberLimit=result[0]['classSeminar']['seminar']['teamNumLimit'];
			//alert(teamNumberLimit);			
			for(var i=0;i<result.length;i++)
				{
					var teamOrder=result[i]['teamOrder'];
					var classSerial=result[i]['classSeminar']['class1']['serial'];
					var teamSerial=result[i]['team']['serial'];
					var pptName=result[i]['pptName'];
					if(pptName==null)pptName='未提交';
					$('[class="TeamNo"][id="'+teamOrder+'"]').html(classSerial+'-'+teamSerial+pptName);
					bindPPT(teamOrder);
				}
			if(signStatusOver=="sign"&&status==-1)
				{		
					$("#cancel").append('<p><button id="delete" onclick=cancelSign('+attendanceId+')>取消报名</button>');
				}
			
			//alert("success");
		},	
		error : function() {
			alert("加载讨论课信息失败！");
		}
	});
		
	/*
	function signup(id)
	{
	    $('[class="Team"][id="'+id+'"]').click(function() {
	       alert("上传ppt!");
	    });
	}
	*/
    $("#a2").click(function() {
        window.location.href = "StudentMain.html";
    });
    $("#a3").click(function() {
        window.location.href = "SeminarMessage.html";
    });
    $("#top1").click(function() {
        window.history.back();
    });
    $("#top3").click(function() {
        $(".dropdown-content").slideToggle("slow");
    });
    $("button").click(function() {
        $("#confirm").hide();
    });
});

function bindSignUp(id)
{
    $('[class="Team"][id="'+id+'"]').click(function() {
        $("#confirm").show();
    });
}
function bindPPT(id)
{
    $('[class="Team"][id="'+id+'"]').click(function() {
       alert("上传ppt!");
    });
}

function cancelSign(attendanceId)
{
	alert("ajax");
	$.ajax({
		url : '/attendance/'+attendanceId,
		type : "DELETE",
		contentType: "application/json;charest=utf-8",
		dataType : "json",
		success : function(result) {
			localStorage.setItem("signStatusOver","noSign");
			alert(result['status']);
		},	
		error : function() {
			alert("取消报名失败！");
		}
	});
}

/*
$(document).ready(function() {
	/*
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.open("GET","/seminar/{seminarId}/class/{classId}/presentation",true);
    xmlhttp.send();
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            var presentation=JSON.parse(xmlhttp.responseText);
            $("#id").text(presentation.id);
            $("#presentationScore").text(presentation.presentationScore);
            $("#reportScore").text(presentation.reportScore);
            $("#presentationFileName").text(presentation.presentationFileName);
            $("#reportFileName").text(presentation.reportFileName);
            $("#presentationUrl").text(presentation.presentationUrl);
            $("#reportUrl").text(presentation.reportUrl);
            $("#presentationOrder").text(presentation.presentationOrder);

        }
    }
	


});
*/

