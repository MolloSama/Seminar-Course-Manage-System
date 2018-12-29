$(document).ready(function(){
	localStorage.setItem("courseId", 1);
	var storage = window.localStorage;
	var courseId = storage.getItem("courseId");
	var url = '/course/' + courseId + '/share';
	alert(url);

	$.ajax({
		type : "GET",
		url : url,
		//data: "data",
		dataType : "json",
		async : true,
		success : function(data) {
			html = '';
			for (var i = 0; i < data.length; i++) {
				var shareId = data[i]['shareId'];
				var shareType = data[i]['shareType'];
				var masterCourse = data[i]['masterCourse'];
				var recieveCourse = data[i]['recieveCourse'];
				var masterCourseId = masterCourse['masterCourseId'];
				var recieveCourseId = recieveCourse['recieveCourseId'];
				var masterTeacherName = masterCourse['teacherName'];
				var recieveTeacherName = recieveCourse['teacherName'];
				var masterCourseName = masterCourse['courseName'];
				var recieveCourseName = recieveCourse['courseName'];
				if (courseId == masterCourseId) {
					html += '<div id="'+recieveCourseId+'" class="share">'
					+ ' <p class="courseName" id="'+recieveCourseId+'">'
					+ recieveCourseName
					+ '('
					+ recieveTeacherName
					+ ')</p>'
					+ ' <div class="'+recieveCourseId+'">'
					+ ' <p class="ele">'
					+ ' <span class="mess1">共享类型：</span><span>'
					+ shareType
					+ '</span>'
					+ ' </p>'
					+ ' <p class="ele">'
					+ ' <span class="mess1">共享情况：</span><span>主课程</span>'
					+ ' </p>'
					+ ' <p class="mess2">'
					+ ' <input type="button" class="cancle" id="'
					+ recieveCourseId
					+ '"'
					+ ' value="取消共享" onclick=deleteShare('
					+ recieveCourseId
					+ ')>'
					+ ' </p>'
					+ ' </div>' + ' </div>';
				} else {
					html += '<div id="'+masterCourseId+'" class="share">'
					+ ' <p class="courseName" id="'+masterCourseId+'">'
					+ masterCourseName
					+ '('
					+ masterTeacherName
					+ ')</p>'
					+ ' <div class="'+masterCourseId+'">'
					+ ' <p class="ele">'
					+ ' <span class="mess1">共享类型：</span><span>'
					+ shareType
					+ '</span>'
					+ ' </p>'
					+ ' <p class="ele">'
					+ ' <span class="mess1">共享情况：</span><span>从课程</span>'
					+ ' </p>'
					+ ' <p class="mess2">'
					+ ' <input type="button" class="cancle" id="'
					+ masterCourseId
					+ '"' 
					+ ' value="取消共享" onclick=deleteShare('
					+ masterCourseId
					+ ')>'
					+ ' </p>'
					+ ' </div>'
					+ ' </div>';
				}
			}
			$("#courses").append(html);

		},
		error : function() {
			alert("获取共享信息失败！");
		}
	});
	/*
						$("#ooad").click(function() {
							$(".ooad").slideToggle("slow");
						});
						$("#j2ee").click(function() {
							$(".j2ee").slideToggle("slow");
						});
	 */
	$("#NewShare").click(function() {
		window.location.href = "NewShare.html";
	});
});

function deleteShare(passiveCourseId) {
	var courseId = localStorage.getItem("courseId");
	if (confirm("是否取消与该课程的共享")) {
		alert("delete share!");
		//url=
		//alert(url);

		$.ajax({
			url : '/course/' + courseId + '/share/' + passiveCourseId,
			type : "POST",
			data : {
				_method : "DELETE"
			},
			dataType : "json",
			success : function(result) {
				alert(result['status']);

				var condition = '[id="' + passiveCourseId
				+ '"][class="share"]';
				$(condition).remove();

			},
			error : function() {
				alert("取消共享失败！");
			}

		});
	}
}