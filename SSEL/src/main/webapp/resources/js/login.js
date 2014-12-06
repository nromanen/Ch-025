jQuery(document).ready(function($) {

	$('#login_form').bootstrapValidator();
	
	$('#form_expired_account').bootstrapValidator();
	
	$("#btn_open_expired_modal").click(function(){
		var email = $("#email").val();
		$("#email_send").val(email);
		$('#form_expired_account').bootstrapValidator('revalidateField', 'email_send');
	});

	$("#form_expired_account").submit(function() {
		var email = $("#email_send").val();
		var message = $("#message").val();
		if (email != "") {
			var url = $(this).attr("action");
			var json = {
				"email" : email,
				"message" : message
			};
			$.ajax({
				url : url,
				data : JSON.stringify(json),
				contentType : 'application/json',
				type : "POST",
				success : function(response) {
					if (response["result"] == "success") {
						$("#modal_expired_account").modal("hide");
						window.location = response["url"];
					} else {
						// bootbox alert
						$("#email_send").val('');
						$('#form_expired_account').bootstrapValidator('revalidateField', 'email_send');
					}
				}
			});
		}
		return false;
	});
	
	$('[data-toggle="tooltip"]').tooltip();

});