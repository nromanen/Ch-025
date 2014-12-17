$(document).ready(function(){
	$("#question\\.mark").css("border-color", "#a94442");
	$("#question\\.mark").css("border-width", "1px");
	$( "#question\\.mark" ).on('input', function() {
		var $question = $("#question\\.mark").val();
		var $questionPatt = /^\d*\.?\d+$/;
		if ($questionPatt.test($question)) {
			$("#subm").attr("disabled", false);
			$("#question\\.mark").css("border-color", "#ccc");
			$("#question\\.mark").css("border-width", "1px");
		} else {
			$("#subm").attr("disabled", true);
			$("#question\\.mark").css("border-color", "#a94442");
			$("#question\\.mark").css("border-width", "2px");
		}
	});
});
