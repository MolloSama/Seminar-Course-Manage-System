$(document).ready(function() {

var courseList;
var item;
var id;
localStorage.setItem('userId',1);
var userId=localStorage.getItem('userId');
//alert(userId);

  $.ajax({
    type: "GET",
    url: '/course/'+userId+'/student',
    dataType: "json",
    async: false,
    //data: "data",
    //dataType: "dataType",
    success: function(response) {

      //alert('success!');

      courseList = response;
      if (courseList.length == 0) $("#second_section").html("课程列表为空");
      for (var i in courseList) {
        item = courseList[i];
        id = item['id'];
        $("#second_section").append(
          '<div><p id="' +
            id +
            '" class="courseName">&nbsp;&nbsp;&nbsp;&nbsp;' +
            item.name +
            "</p>" +
            '<ul class="' +
            id +
            '" "style="display: block;" style="display: none;"><li class="grade" onclick=grade('+
            id+
            ')>我的成绩</li><li class="team">我的组队</li></ul></div><br/>'
        );
        solve(id);
      }
    },
    error: function() {
      alert("获取课程信息失败！");
    } 
    });
    
	$("#top3").click(function() {
		$(".dropdown-content").slideToggle("slow");
	});
	$("#top1").click(function() {
		window.history.back();
	});
	$("#ooad").click(function() {
		$(".ooad").slideToggle("slow");
	});
	$("#j2ee").click(function() {
		$(".j2ee").slideToggle("slow");
	});
	$("#a3").click(function() {
		window.location.href = "SeminarMessage.html";
	});
	$("#a2").click(function() {
		window.location.href = "StudentMain.html";
	});
	$("#a3").click(function() {
		window.location.href = "SeminarMessage.html";
	});
	$(".grade").click(function() {
		window.location.href = "Grade_student.html";
	});
	$(".team").click(function() {
		window.location.href = "Team.html";
	});
});
//点击弹出列表
function solve(id) {
  $('[id="'+id+'"][class="courseName"]').click(function() {
    $("." + id).slideToggle("slow");
  });
}

//选择课程
function grade(id)
{
	  if(!window.localStorage){
          alert("浏览器不支持localstorage!!!");
      }else{ 
    	localStorage.setItem("courseId",id);
    	//alert(id);
      }
	  window.location.href = "Grade_student.html";
}