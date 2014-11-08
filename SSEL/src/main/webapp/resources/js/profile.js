jQuery(document).ready(function ($) {
	
	$('#form_change_password').bootstrapValidator();
	$('#form_change_user_information').bootstrapValidator();
	
	$("#fileupload").click(function(){	
		$("#photo" ).remove();
	});
	
	$(".reveal").mousedown(function(){
		$("#old_password").attr('type','text');
    }).mouseup(function(){
    	$("#old_password").attr('type','password');
    }).mouseout(function(){
    	$("#old_password").attr('type','password');
    });
	
	$("#btn_change_email").click(function(){	
		$('#modal_change_email').modal();
	});
	
	$("#btn_load_photo").click(function(){	
		$('#modal_load_photo').modal();
	});
	
	$("#btn_change_email").click(function(){	
		$('#modal_change_email').modal();
	});
	
    $('[data-toggle="tooltip"]').tooltip();
    
    $("#form_change_user_information").submit(function(){
		var first_name = $("#first_name").val();
		var last_name = $("#last_name").val();
		if(first_name != "" && last_name != ""){
			var url = $(this).attr("action");
			var json = { "firstName" : first_name, "lastName" : last_name };
			$.ajax({
		        url: url,
		        data: JSON.stringify(json),
		        contentType: 'application/json',
		        type: "POST",
		        success: function(response) {
		        	if(response == "success"){	
		        		location.reload();
		        	} else {
		        		$("#first_name").val('');
		        		$("#last_name").val('');
		        	}
		        }
		    });
		}
		return false;
	});
    
    $("#old_password").on("blur", function(){
		var json = {"old_password" : $('#old_password').val()};
		$.ajax({
			type: "POST",
			url: "checkOldPassword",
			contentType: "application/json",
			data: JSON.stringify(json),
			success : function(response) {
				if (response == false) {
					$("#old_password_error").css("display", "block");
					$("#old_password_error").removeClass("has-success");
					$("#old_password_error").addClass("has-error");
					$('#btn_change_password_submit').prop("disabled", true);
				}
				else { 
					$("#old_password_error").css("display", "none");
					$('#btn_change_password_submit').prop("disabled", false);
				}
			}
		});		
	});
    
    $("#form_change_password").submit(function(){
		var old_password = $("#old_password").val();
		var new_password = $("#new_password").val();
		if((old_password != "") && (new_password != "")){
			var url = $(this).attr("action");
			var json = { "oldPassword" : old_password, "newPassword" : new_password };
			$.ajax({
		        url: url,
		        data: JSON.stringify(json),
		        contentType: 'application/json',
		        type: "POST",
		        success: function(response) {
		        	if(response == "success"){	
		        		$("#modal_change_password").modal("hide");
		        		
		        	} 
		        }
		    });
		}
		return false;
	}); 
	
});