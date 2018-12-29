/**
 * 
 */
$(document).ready(function(){
	$.ajax({
        type: "GET",
        url: '/course',
        dataType: "json",
        async: false,
        success: function (message) {
			for(var i=0;i<message.length;i++)
				{
					//alert($("#shareType").find("option:selected").attr('shareType'));
					var id=message[i]['id'];
					var name=message[i]['name'];
					html=
						'<p class="Course" onclick=toSeminar('+id+')>'+
						'<img src="./Picture/book1.png" class="img1">'+name+
						'</p>';
					$("#second_section").append(html);
				}	
        },
        error: function (message) {
            alert("获取课程信息失败！");
        }
    });
});

function toSeminar(id)
{
	localStorage.setItem("courseId",id);
	window.location.href = "TeacherSeminarAll.html";
}

