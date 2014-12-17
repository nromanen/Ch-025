$(document).ready(function(){
	$("#name").css("border-color", "#a94442");
	$("#name").css("border-width", "1px");
	$( "#name" ).on('input', function() {
		var $nameVal = $("#name").val();
		var $namePatt = /[A-ZА-ЯІЇЄa-zа-яіїє\\s]{1,}/;
		if ($namePatt.test($nameVal)) {
			$("#subm").attr("disabled", false);
			$("#name").css("border-color", "#ccc");
			$("#name").css("border-width", "1px");
		} else {
			$("#subm").attr("disabled", true);
			$("#name").css("border-color", "#a94442");
			$("#name").css("border-width", "2px");
		}
	});
});