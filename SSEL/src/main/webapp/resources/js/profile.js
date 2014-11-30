jQuery(document).ready(function($) {

	$('#form_change_password').bootstrapValidator();
	$('#form_change_user_information').bootstrapValidator();
	$('#form_edit_phone').bootstrapValidator();
	
	$("#fileupload").click(function() {
		$("#photo").remove();
	});

	$(".reveal").mousedown(function() {
		$("#old_password").attr('type', 'text');
	}).mouseup(function() {
		$("#old_password").attr('type', 'password');
	}).mouseout(function() {
		$("#old_password").attr('type', 'password');
	});

	$("#btn_load_photo").click(function() {
		$('#modal_load_photo').modal();
	});

	$('#modal_change_password').on('shown.bs.modal', function() {
	    $('#form_change_password').bootstrapValidator('resetForm', true);
	});
	
	$('#modal_new_password').on('shown.bs.modal', function() {
	    $('#form_new_password').bootstrapValidator('resetForm', true);
	});
	
	$('#modal_edit_phone').on('shown.bs.modal', function() {
	    $('#form_edit_phone').bootstrapValidator('resetForm', true);
	});
	
	$('#btn_phone_edit').click(function(){
		$('#modal_edit_phone').modal('show');
	});
	
	$('#phone').keypress(function(){
		$('#form_edit_phone').bootstrapValidator('revalidateField', '#phone');
	});
	
	$('[data-toggle="tooltip"]').tooltip();

	$("#form_change_user_information").submit(function() {
		var first_name = $("#first_name").val();
		var last_name = $("#last_name").val();
		if (first_name != "" && last_name != "") {
			var url = $(this).attr("action");
			var json = {
				"firstName" : first_name,
				"lastName" : last_name
			};
			$.ajax({
				url : url,
				data : JSON.stringify(json),
				contentType : 'application/json',
				type : "POST",
				success : function(response) {
					if (response == "success") {
						location.reload();
					} else {
						// bootbox alert
						$("#first_name").val('');
						$("#last_name").val('');
					}
				}
			});
		}
		return false;
	});
	
	$("#form_edit_phone").submit(function() {
		var phone = $("#phone").val();
		if (phone != "") {
			var url = $(this).attr("action");
			var json = {
				"phone" : phone
			};
			$.ajax({
				url : url,
				data : JSON.stringify(json),
				contentType : 'application/json',
				type : "POST",
				success : function(response) {
					if (response == "success") {
						location.reload();
					} else {
						// bootbox alert
						$("#phone").val('');
						$('#form_edit_phone').bootstrapValidator('revalidateField', '#phone');
					}
				}
			});
		}
		return false;
	});

	$("#form_change_password").submit(function() {
		var old_password = $("#old_password").val();
		var new_password = $("#new_password").val();
		if ((old_password != "") && (new_password != "")) {
			var url = $(this).attr("action");
			var json = {
				"oldPassword" : old_password,
				"newPassword" : new_password
			};
			$.ajax({
				url : url,
				data : JSON.stringify(json),
				contentType : 'application/json',
				type : "POST",
				success : function(response) {
					if (response == "success") {
						$("#modal_change_password").modal("hide");
					} else {
						// bootbox alert
					}
				}
			});
		}
		return false;
	});
	
	$("#form_new_password").submit(function() {
		var new_password = $("#new_password").val();
		if (new_password != "") {
			var url = $(this).attr("action");
			var json = {
				"newPassword" : new_password
			};
			$.ajax({
				url : url,
				data : JSON.stringify(json),
				contentType : 'application/json',
				type : "POST",
				success : function(response) {
					if (response == "success") {
						$("#modal_new_password").modal("hide");
						location.reload();
					} else {
						// bootbox alert
					}
				}
			});
		}
		return false;
	});
	
	$(function () {
		uploadButton = $('<button/>')
	    	.addClass('btn btn-primary')
	        .prop('disabled', true)
	        .text($("#processing").val())
	        .on('click', function () {
	        	var $this = $(this),
	            	data = $this.data();
	           	$this.off('click')
	           		.text($("#cancel").val())
	                .on('click', function () {
	                	$this.remove();
	                    data.abort();
	                });
	            data.submit().always(function () {
	            	$this.remove();
	            });
	    	});
	    $('#fileupload').fileupload({
	        url: 'uploadPhoto',
	        dataType: 'json',
	        autoUpload: false,
	        acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
	        maxFileSize: 5000000, 
	        disableImageResize: /Android(?!.*Chrome)|Opera/
	            .test(window.navigator.userAgent),
	        previewMaxWidth: 100,
	        previewMaxHeight: 100,
	        previewCrop: true
	    }).on('fileuploadadd', function (e, data) {
	        data.context = $('<div id="photo" />').appendTo('#files');
	        $.each(data.files, function (index, file) {
	            var node = $('<p/>')
	                    .append($('<span/>').text(file.name));
	            if (!index) {
	                node
	                    .append('<br>')
	                    .append(uploadButton.clone(true).data(data));
	            }
	            node.appendTo(data.context);
	        });
	    }).on('fileuploadprocessalways', function (e, data) {
	        var index = data.index,
	            file = data.files[index],
	            node = $(data.context.children()[index]);
	        if (file.preview) {
	            node
	                .prepend('<br>')
	                .prepend(file.preview);
	        }
	        if (file.error) {
	            node
	                .append('<br>')
	                .append($('<span class="text-danger"/>').text(file.error));
	        }
	        if (index + 1 === data.files.length) {
	            data.context.find('button')
	                .text($("#upload").val())
	                .prop('disabled', !!data.files.error);
	        }
	    }).on('fileuploadprogressall', function (e, data) {
	        var progress = parseInt(data.loaded / data.total * 100, 10);
	        $('#progress .progress-bar').css(
	            'width',
	            progress + '%'
	        );
	    }).on('fileuploaddone', function (e, data) {
	        $.each(data.result.files, function (index, file) {
	        	location.reload();
	            if (file.url) {
	                var link = $('<a>')
	                    .attr('target', '_blank')
	                    .prop('href', file.url);
	                $(data.context.children()[index])
	                    .wrap(link);
	            } else if (file.error) {
	                var error = $('<span class="text-danger"/>').text(file.error);
	                $(data.context.children()[index])
	                    .append('<br>')
	                    .append(error);
	            }
	        });
	    }).on('fileuploadfail', function (e, data) {
	        $.each(data.files, function (index) {
	        	location.reload();
	            var error = $('<span class="text-danger"/>'); // .text('File
																// upload
																// failed.')
	            $(data.context.children()[index])
	                .append('<br>')
	                .append(error);
	        });
	    }).prop('disabled', !$.support.fileInput)
	        .parent().addClass($.support.fileInput ? undefined : 'disabled');
	});
	

});