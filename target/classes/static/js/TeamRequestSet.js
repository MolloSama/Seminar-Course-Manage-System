/**
 * 
 */
$("document").ready(function(){
	var html1='<p><select class="Select1">'+
	'<option>J2EE(邱老师)</option>'+
	'<option>NET(杨老师)</option>'+
	'<option>软件工程(王老师)</option>'+
	'</select>'+
	'<button class="move2">删除</button></p>'
	var html2='<p class="mess5">'+
	'<select class="Select3">'+
	'<option>J2EE(邱老师)</option>'+
	'<option>NET(杨老师)</option>'+
	'<option>软件工程(王老师)</option>'+
	'</select> <select id="least1" class="least">'+
	'<option>1</option>'+
	'<option>2</option>'+
	'<option>3</option>'+
	'</select>~<select id="most1" class="most">'+
	'option>4</option>'+
	'<option>5</option>'+
	'<option>6</option>'+
	'</select>'+
	'<button class="move">删除</button>'+
	'</p>'
	var html3='<div class="div2_">'+html1+html1+
	'<p class="add2">'+
	'<button class="add_C">新增</button>'+
	'<button class="move3">删除</button>'+
	'</p></div>'
	$("#add1").click(function(){
		$(".div2").append(html3);
	});
	$(".add_C").click(function(){
		var $p=$(this).parent(); 
		$($p).before(html1);
	});
	$("#add3").click(function(){
		$("#div3_1_1").append(html2);
	});
	$(".move").click(function(){
		$(this).parent().remove();
	});
	$(".move2").click(function(){
		$(this).parent().remove();
	});
	$(".move3").click(function(){
		$(this).parent().parent().remove();
	});
});