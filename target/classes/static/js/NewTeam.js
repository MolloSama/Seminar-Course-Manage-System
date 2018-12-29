$(document).ready(function() {
	var storage=window.localStorage;
	storage.setItem("courseId",1);
	var courseId=storage.getItem("courseId");
	var url='/course/'+courseId+'/class';
	//alert(url);
	$.ajax({
		type: "GET",
		url: url,
		//data: "data",
		dataType: "json",
		async: true,
		success: function(data) {
			html=
				'<p>'+
				' <span class="mess2">选择班级：</span> <select class="class"> ';
			for (var i=0;i<data.length;i++) {
				var id = data[i]['id'];
				var name = data[i]['name'];		 
				//alert("success!");
				html+='<option id="'+id+'">'+name+'</option> ';       
			}
			html+='</select> </p> ';
			$("#chooseClass").append(html); 	
		},
		error: function() {
			alert("获取班级信息失败！");
		}

	});

	$.ajax({
		type: "GET",
		url: '/course/'+courseId+'/noTeam',
		dataType: "json",
		async: true,
		success: function (data) {
			for (var i=0;i<data.length;i++) {
				var id = data[i]['id'];
				var account = data[i]['account'];
				var name = data[i]['name'];		 
				//alert("success!");
				html='<p class="student"> ';
				html+=
					'<input name="student" id="'+id+'" type="checkbox"/> <span'+
					' class="StudentID">'+account+'</span><span'+
					' class="StudentName">'+name+'</span> ';  
				html+='</p>';
				$("#students").append(html);
			}
		},
		error: function (message) {
			alert("加载未组队学生失败！");
		}   
	});
	sub();

	$("#top1").click(function() {
		window.history.back();
	});
});


function sub() {
	$("#sub").click(function() {
		localStorage.setItem("userId",1);
		var storage=window.localStorage;
		var userId=storage.getItem("userId");
		var students=document.getElementsByName("student");

		var name=$(".teamName").text();
		var courseId=storage.getItem('courseId');
		var classId=$(".class").find("option:selected").attr('id');
		var leader=
		{
				"id":userId
		};
		//alert("leaderId:"+userId+" classId:"+classId+" courseId:"+courseId);
		var members=[];
		for(var i=0;i<students.length;i++){
			if(students[i].checked)
				//alert(students[i].getAttribute("id"));
				var student=
				{
					"id":students[i].getAttribute("id")
				};
			members.push(student);	
		}
		var json=
		{
				"name":name,
				"courseId":courseId,
				"classId":classId,
				"leader":leader,
				"members":members
		};
		$.ajax({
			type: "POST",
			url: '/team',
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(json),
			dataType: "json",
			success: function (message) {
				alert(message['status']);
			},
			error: function (message) {
				alert("创建小组失败！");
			}
		});

	})
}