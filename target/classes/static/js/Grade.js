/**
 * 
 */
$(document).ready(function() {

	$(".seminar_all").click(function(e) {
		var $Team = $(this).next();
		$($Team).slideToggle("slow");
	});
	$(".p1").click(function(e) {
		var $SeminarAll = $(this).next();
		$($SeminarAll).slideToggle("slow");
	});
});