jQuery(document).ready(function ($) {
	
	$(function(){var b="fadeInLeft";var c;var a;d($("#myTab1 a"),$("#tab-content1"));function d(e,f,g){e.click(function(i){i.preventDefault();$(this).tab("show");var h=$(this).data("easein");if(c){c.removeClass(a);}if(h){f.find("div.active").addClass("animated "+h);a=h;}else{if(g){f.find("div.active").addClass("animated "+g);a=g;}else{f.find("div.active").addClass("animated "+b);a=b;}}c=f.find("div.active");});}$("a[rel=popover]").popover().click(function(f){f.preventDefault();if($(this).data("easein")!=undefined){$(this).next().removeClass($(this).data("easein")).addClass("animated "+$(this).data("easein"));}else{$(this).next().addClass("animated "+b);}});});
	
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
		        		//bootbox alert
		        		$("#first_name").val('');
		        		$("#last_name").val('');
		        	}
		        }
		    });
		}
		return false;
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
		        	} else {
		        		//bootbox alert
		        	}
		        }
		    });
		}
		return false;
	}); 
	
});