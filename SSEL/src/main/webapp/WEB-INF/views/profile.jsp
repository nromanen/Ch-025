<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="<c:url value="/resources/js/jquery.validate.min.js" />" ></script>
<div class="container">
	<spring:message code="dataerror.field_required" var="required"/>
	<spring:message code="dataerror.password_pattern" var="pattern"/>
	<spring:message code="dataerror.passwords_do_not_match" var="passwords_do_not_match" />
	<spring:message code="dataerror.minimum_4_characters" var="minimum"/>	
	<spring:message code="dataerror.password_incorrect" var="password_incorrect"/>
	<spring:message code="dataerror.firstname" var="firstname"/>
	<spring:message code="dataerror.firstname" var="lastname"/>
	<spring:message code="dataerror.email_exist" var="email_exist" />
	<spring:message code="dataerror.email_example" var="email_example"/>
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">
			<div class="panel-heading">
              <h3 class="panel-title">Title</h3>
            </div>
            <div class="panel-body">
            	<div class="row">
              		<div class="col-md-3 col-lg-3 " align="center"> 
              			<c:choose>
	              			<c:when test="${empty sessionScope.image}">
	              				<img alt="User Pic" class="img-circle" style="height: 100px; width: 100px"
                					src="<c:url value="/resources/img/user_photo.png" />" > 
	              			</c:when>
	              			<c:otherwise>
	              				<img alt="User Pic" class="img-circle" style="height: 100px; width: 100px"
                					src="data:image/png;base64,<c:out value="${sessionScope.image}" />" > 
                					
	              			</c:otherwise>
              			</c:choose>
                		
                		<button type="button" class="btn btn-info" data-toggle="modal" 
                			data-target="#modal_load_photo" style="margin-top: 15px">
							Load Photo
						</button>
                	</div>
                	<div class=" col-md-9 col-lg-9 "> 
                		<form id="form_change_user_information" method="POST" role="form" 
							action="<c:url value="/changeUserInformation" />">
	                		<table class="table table-user-information">
	                    		<tbody>
	                      			<tr>
	                        			<td>
	                        				<spring:message code="label.firstname" />:
	                        			</td>
	                        			<td>
	                       					<input type="text" id="first_name" class="form-control" name="first_name"
												placeholder="<spring:message code="placeholder.firstname" />"  
												value="<c:out value="${sessionScope.user.firstName}" />" 
												data-toggle="tooltip" 
												title="<spring:message code="label.edit_first_name" />" />
	                        			</td>
	                      			</tr>
		                      		<tr>
		                        		<td>
	                        				<spring:message code="label.lastname" />:
	                        			</td>
	                        			<td>
	                        				<input type="text" id="last_name" class="form-control" name="last_name"
												placeholder="<spring:message code="placeholder.lastname" />"  
												value="<c:out value="${sessionScope.user.lastName}" />" 
												data-toggle="tooltip" 
												title="<spring:message code="label.edit_last_name" />" />
	                        			</td>
		                      		</tr>
		                      		<tr>
		                        		<td>
		                        			<spring:message code="label.registration_date" />:
		                        		</td>
		                        		<td>
		                        			<fmt:formatDate value="${sessionScope.user.registration}" pattern="yyyy-MM-dd" />
		                        		</td>
		                      		</tr>
		                     		<tr>
		                        		<td>
		                        			<spring:message code="label.email" />:
		                        		</td>
		                        		<td id="email">
		                        			<c:out value="${sessionScope.user.email}" />
		                        		</td>
		                        		<td>
	                        				<button data-original-title="Edit email" data-toggle="tooltip"
	                        					type="button" class="btn btn-sm btn-warning"
	                        					id="btn_change_email">
	                        					<i class="glyphicon glyphicon-edit"></i>
	                        				</button>
	                        			</td>
		                     		</tr>
		                        	<tr>
		                        		<td>Expired date</td>
		                        		<td>
		                        			<fmt:formatDate value="${sessionScope.user.expired}" pattern="yyyy-MM-dd" />
		                        		</td>
		                      		</tr>
		                    	</tbody>
	              			</table>
	              			<button type="button" class="btn btn-info" data-toggle="modal" data-target="#modal_change_password">
								<spring:message code="label.change_password"/>
							</button>
							<button type="submit" class="btn btn-success">
								Save
							</button>
              			</form>
                	</div>
              	</div>
            </div>
		</div>
	</div>
</div>

<!-- Modal window for load photo -->
<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" 
    id="modal_load_photo" aria-labelledby="myLargeModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
				    <span aria-hidden="true">&times;</span>
				    <span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">
					Load photo
				</h4>
			</div>
			<div class="modal-body">	
				<span class="btn btn-success fileinput-button">
			        <i class="glyphicon glyphicon-plus"></i>
			        <span>Add files...</span>
			        <input id="fileupload" type="file" name="files[]" 
			        	accept="image/png, image/jpeg, image/gif, image/jpg">
			    </span>
			    <div id="progress" class="progress" style="margin-top: 15px">
			        <div class="progress-bar progress-bar-success"></div>
			    </div>
			    <div id="files" class="files"></div>
			</div>
		</div>		
	</div> 
</div>

<script type="text/javascript">
$(function () {
        uploadButton = $('<button/>')
            .addClass('btn btn-primary')
            .prop('disabled', true)
            .text('Processing...')
            .on('click', function () {
                var $this = $(this),
                    data = $this.data();
                $this
                    .off('click')
                    .text('Abort')
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
                .text('Upload')
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
            var error = $('<span class="text-danger"/>'); //.text('File upload failed.')
            $(data.context.children()[index])
                .append('<br>')
                .append(error);
        });
    }).prop('disabled', !$.support.fileInput)
        .parent().addClass($.support.fileInput ? undefined : 'disabled');
});
</script>

<!-- Modal window for change passwords -->
<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" 
    id="modal_change_password" aria-labelledby="myLargeModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
				    <span aria-hidden="true">&times;</span>
				    <span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">
					<spring:message code="label.change_password"/>
				</h4>
			</div>
			<form id="form_change_password" class="form-horizontal" method="POST" role="form" 
				action="<c:url value="/changePassword" />">
				<div class="modal-body">				
					<div class="panel panel-info">
        				<div class="panel-body">
        					<div class="form-group">
								<label class="col-md-3 control-label" for="old_password">
									<spring:message code="label.old_password" />
								</label>
								<div class="col-md-6">
									<div class="input-group">
										<input type="password" id="old_password" class="form-control pwd" name="old_password"
											placeholder="<spring:message code="placeholder.old_password"/>" />
										<span class="input-group-btn">
	            							<button class="btn btn-default reveal" style="height: 34px" type="button">
	            								<i class="glyphicon glyphicon-eye-open"></i>
	            							</button>
	          							</span> 
          							</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label" for="new_password">
									<spring:message code="label.new_password" />
								</label>
								<div class="col-md-6">
									<input type="password" id="new_password" class="form-control" name="new_password"
										placeholder="<spring:message code="placeholder.new_password"/>" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label" for="confirm_password">
									<spring:message code="label.confirm_password" />
								</label>
								<div class="col-md-6">
									<input type="password" class="form-control" id="confirm_password" name="confirm_password"
										placeholder="<spring:message code="placeholder.confirm_password"/>" />
								</div>
							</div>
	        			</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" id="btn_change_password_submit"  
						class="btn btn-success" disabled="disabled" >
						<spring:message code="label.accept"/>
					</button>
					<button type="reset" id="btn_form_close" class="btn btn-info" data-dismiss="modal">
						<spring:message code="label.cancel" />
					</button>
				</div>
			</form>
		</div>		
	</div> 
</div>

<script src="//blueimp.github.io/JavaScript-Load-Image/js/load-image.all.min.js"></script>
<script src="//blueimp.github.io/JavaScript-Canvas-to-Blob/js/canvas-to-blob.min.js"></script>
<script src="<c:url value="/resources/js/vendor/jquery.ui.widget.js" />" ></script>
<script src="<c:url value="/resources/js/jquery.iframe-transport.js" />" ></script>
<script src="<c:url value="/resources/js/jquery.fileupload.js" />" ></script>
<script src="<c:url value="/resources/js/jquery.fileupload-process.js" />"></script>
<script src="<c:url value="/resources/js/jquery.fileupload-image.js" />" ></script>
<script src="<c:url value="/resources/js/jquery.fileupload-validate.js" />" ></script>

<script>
jQuery(document).ready(function ($) {
	
	$("#fileupload").click(function(){	
		$( "#photo" ).remove();
	});
	
	$(".reveal").mousedown(function() {
	    $(".pwd").replaceWith($('.pwd').clone().attr('type', 'text'));
	})
	.mouseup(function() {
		$(".pwd").replaceWith($('.pwd').clone().attr('type', 'password'));
	})
	.mouseout(function() {
		$(".pwd").replaceWith($('.pwd').clone().attr('type', 'password'));
	});
	
	$('#upload').fileupload({
	    dataType: 'json',
	    done: function (e, data) {
	        $.each(data.result, function (index, file) {
	            $("#addingResultsData").show();
	            $('body').data('filelist').push(file);
	            $('#filename').append(formatFileDisplay(file));
	        });
	        $("#addingResultsData").hide();
	        alert('hits on each file added');
	    }
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
		        		$('#old_password').removeClass("error");
						$('#lbl').remove();
		        		$("#modal_change_password").modal("hide");
		        		
		        	} else {
		        		$("#old_password").addClass("error");
						if($("#lbl").length == 0) {
							$('#old_password').after("<label id='lbl' class='error'>${password_incorrect}</label>");
						}
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
					$('#old_password').addClass("error");
					if($("#lbl").length == 0) {
						$('#old_password').after("<label id='lbl' class='error'>${password_incorrect}</label>");
					}
					$('#btn_change_password_submit').prop("disabled", true);
				}
				else { 
					$('#old_password').removeClass("error");
					$('#lbl').remove();
					$('#btn_change_password_submit').prop("disabled", false);
				}
			}
		});		
	});
    
    $.validator.addMethod(
    	'regexp',
    	function(value, element, regexp) {
    		var re = new RegExp(regexp);
    		return this.optional(element) || re.test(value);
    	}, "Please check your input."
    );
    
    $.validator.addMethod(
    		'notEqual', 
    		function(value, element, param) {
    	  		return this.optional(element) || value != $(param).val();
    	}, "Please specify a different (non-default) value"
    );
    
    $("#form_change_user_information").validate({
    	rules: {
    		first_name: {
    			required: true,
    			regexp: "^[A-ZА-ЯІЇЄ]{1}[a-zа-яіїє]{1,30}$"
    		},
    		last_name: {
    			required: true,
    			regexp: "^[A-ZА-ЯІЇЄ]{1}[a-zа-яіїє]{1,30}$"
    		}
    	},
    	messages: {
    		first_name: {
    			required: "${required}",
				regexp: "${firstname}"
    		},
    		last_name: {
    			required: "${required}",
				regexp: "${lastfirstname}"
    		}
    	}
    });
    
    $("#form_change_email").validate({
    	rules: {
    		email: {
    			required: true,
    			email: true
    		}
    	},
    	messages: {
    		email: {
    			required: "${required}",
    			email: "${email_example}"
    		}
    	}
    });
    
    $("#form_change_password").validate({
    	rules: {
    		old_password: {
    			required: true
    		},
    		new_password: {
    			required: true,
    			minlength: 4,
    			regexp: "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[//@/./&/!#/$%/^/*/?])(?!.*\\s).{4,20}$",
    			notEqual: "#old_password"
    			
    		},
    		confirm_password: {
    			required: true,
    			equalTo: "#new_password"
    		}
    	},
    	messages: {
    		old_password: {
    			required: "${required}"
    		},
    		new_password: {
    			required: "${required}",
    			minlength: "${minimum}",
    			regexp: "${pattern}",
    			notEqual: "Must be different"
    		}, 
    		confirm_password: {
    			required: "${required}",
    			equalTo: "${passwords_do_not_match}"
    		}
    	}
    });
});
</script> 