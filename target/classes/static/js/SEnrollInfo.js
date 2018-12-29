$(document).ready(function() {
	var classSeminarId=localStorage.getItem("classSeminarId");
	//alert(classSeminarId);

	$.ajax({
		url : '/attendance/'+classSeminarId+'/attendance',
		type : "GET",
		dataType : "json",
		success : function(result) {		
			for(var i=0;i<result.length;i++)
				{
				    var serial=i+1;
				    var id=result[i]['id'];
					var teamOrder=result[i]['teamOrder'];
					var classSerial=result[i]['classSeminar']['class1']['serial'];
					var teamSerial=result[i]['team']['serial'];
					var pptName=result[i]['pptName'];
					
					html=	
					    '<div style="padding: 3%;"> '+
						'<span>第'+serial+'组</span><a ';
					if(pptName!=null)
						{
							html+='onclick=downloadPPT('+id+') ';
						}
					else 
						{
							pptName='未提交';
						}
					html+='>'+classSerial+'-'+teamSerial+pptName+'</a> '+
						'</div> ';
					$("#seminarNow").append(html);
				}
			//alert("success");
		},	
		error : function() {
			alert("加载讨论课信息失败！");
		}
	});
});

function downloadPPT(attendanceId)
{
	alert("下载attendanceId为"+attendanceId+"讨论课报告!");
}