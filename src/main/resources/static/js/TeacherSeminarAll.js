/**
		<div class="round" id="">
			<p id="1" class="RoundNO">第一轮</p>
			<div class="SeminarAll">
				<p class="SeminarRoundSet">
					<img src="./picture/set.png" class="SetP"> 该轮次讨论课设置
				</p>
				<P class="Seminar">业务流程分析</P>
				<div class="ClassAll">
					<p class="Class">2016-1</p>
					<p class="Class">2016-2</p>
					<p class="Class">2016-3</p>
				</div>
			</div>
		</div>
 * 
 */


$(document).ready(function() {
	localStorage.setItem("courseId",1);
	var courseId=localStorage.getItem("courseId");
	document.getElementsByName("student");
	$.ajax({
		type:"GET",
		url:"/course/"+courseId,
		dataType:"json",
		async: false,
		success:function(data){
			if(data['seminarMainCourse']!=null)
				{
				$("#NewSeminar").remove();
				}
		},
		error:function(){
			alert("出现错误");
		}
	});
	$.ajax({
		type:"GET",
		url:"/course/"+courseId+'/round',
		dataType:"json",
		async: false,
		success:function(data){
			for(var i=0;i<data.length;i++)
				{
					//alert(data.length);
					var roundId=data[i]['id'];
					var roundName=data[i]['orderName'];
					html='<div class="round" id="'+roundId+'"> '+		
						 '<p onclick=showRound('+roundId+') id="'+roundId+'" class="RoundNO">'+roundName+'</p> '+ 	
						 '<div id="'+roundId+'" class="SeminarAll"> '+
							'<p class="SeminarRoundSet"> '+
							'<img src="./picture/set.png" class="SetP"> 该轮次讨论课设置  '+
							'</p> '+
						  '</div> '+	
						 '</div> ';	
					$("#second_section").append(html);
					$.ajax({
						type:"GET",
						url:"/course/"+courseId+"/seminar",
						dataType:"json",
						async: false,
						success:function(seminars){
							for(var j=0;j<seminars.length;j++)
								{
									var seminarId=seminars[j]['id'];
									var topic=seminars[j]['topic'];
									var seminarRoundId=seminars[j]['round']['id'];
									html=
										'<P seminarId="'+seminarId+'" class="Seminar">'+topic+'</P> '+
										'<div class="ClassAll" seminarId="'+seminarId+'"> '+
										'</div> ';
									if(seminarRoundId==roundId)
									{
									$('[id="'+roundId+'"][class="SeminarAll"]').append(html);
									$.ajax({
										type:"GET",
										url:"/course/"+courseId+'/class',
										dataType:"json",
										async: false,
										success:function(classes){
											//alert(JSON.stringify(classes));
											for(var k=0;k<classes.length;k++)
												{
												var classId=classes[k]['id'];
												var classGrade=classes[k]['grade'];
												var classSerial=classes[k]['serial'];
												html='<p onclick=toClassSeminar('+classId+','+seminarId+') id="'+classId+'" class="Class">'+classGrade+'-'+classSerial+'</p> ';
												$('[class="ClassAll"][seminarId="'+seminarId+'"]').append(html);
												}
			
										},
										error:function(){
											alert("出现错误");
										}
									});
									}
									
								}
							
						},
						error:function(){
							alert("出现错误");
						}
					});
				}
			
		},
		error:function(jqXHR){
			alert("出现错误"+jqXHR.status);
		}
	});
	$(".Seminar").click(function(){
		var show1=$(this).next().slideToggle("slow");
	});
	$("#NewSeminar").click(function(){
		window.location.href = "NewSeminar.html";
	});
	$(".SeminarRoundSet").click(function(){
		window.location.href = "SeminarRoundSet.html";
	});
	
});
function showRound(id)
{
	$('[id="'+id+'"][class="SeminarAll"]').slideToggle("slow");
}

function toClassSeminar(classId,seminarId)
{
	localStorage.setItem("classId",classId);
	localStorage.setItem("seminarId",seminarId);
	window.location.href = "TeacherSeminar1.html";
}
