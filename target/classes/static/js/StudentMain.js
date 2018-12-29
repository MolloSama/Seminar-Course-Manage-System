/**
 * 
 */
$(document).ready(function() {
		var xmlhttp;
		if (window.XMLHttpRequest)
		  {// code for IE7+, Firefox, Chrome, Opera, Safari
		  xmlhttp=new XMLHttpRequest();
		  }
		else
		  {// code for IE6, IE5
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		xmlhttp.open("GET","/user/information",true);
		xmlhttp.send();
		xmlhttp.onreadystatechange=function()
		  {
		  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    {
		    var user=JSON.parse(xmlhttp.responseText);  
		    $("#name").text(user.name);
		    $("#account").text(user.account);
		    }
		  }	
		$("#course").click(function() {
			window.location.href = "CourseAll_Student.html";
		});
		$("#Personal").click(function() {
			window.location.href = "StudentPersonal.html";
		});
	});