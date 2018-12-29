var courseId=1;
window.onload = function(){
	//*****getcourseId
	courseId=localStorage.getItem("courseId");
	$(".courseName").html(localStorage.getItem("courseName")) ;//界面最上方显示courseName
}
function createSeminar(){

	if(!checkinput())
		return ;

	var fixed;
	
	var newSeminar={
			name : $("#seminarName").val(),
			description : $("#seminarDescription").val(),
			fixed : fixed,
			signupStartTime : $("#startTime").val(),
            signupEndTime : $("#endTime").val(),
            teamNumLimit:$("#teamNumLimit").val(),
            visable:$("#visable").val(),
            order: $("#order").val(),
            roundOrder:$("#roundOrder").val()
            
	};
	$.ajax({			
		url:  "/seminar",
		type: "PUT",
		contentType: "application/json;charest=utf-8",
		data: JSON.stringify(newSeminar),
		success: function(data)
		{
				alert("修改成功");

		},
		error:function()
		{
			alert("修改失败");
		}
		});	
	
}
//检查输入是否有空
function checkinput(){
    var warn = "";
    var name =$("#seminarName").val();//讨论课名称
    var description = $("#seminarDescription").val();//讨论课说明
    var signupStartTime = $("#signupStartTime").val();//展示报名开始时间
    var signupEndTime = $("#signupEndTime").val();//展示报名结束时间
    var teamNumLimit=$("#teamNumLimit").val();//报名小组数限制
    var visable=$("#visable").val();//讨论课可见
    var order;//讨论课次顺序
    var roundOrder;//所属Round
    var courseId;//localStorage取

    if(warn == null || warn.length == 0){
        if(name == ""){
            warn = "名称不能为空。";
        }else if(description == ""){
            warn = "说明不能为空。";
        }else if(signupStartTime == null){
            warn = "请选择开始时间。";
        }else if( signupEndTime  == null){
            warn = "请选择结束时间。";
        }else if(teamNumLimit==null){
            warn="请选择报名小组数限制。"
        }else if(visable==null){
            warn="请设置讨论课可见。"
        }

        if(warn !=null && warn.length !=0){
            alert(warn);
            return false;
    }
        else
           return true;
    }
}
function reset(){
	window.location.reload();
}