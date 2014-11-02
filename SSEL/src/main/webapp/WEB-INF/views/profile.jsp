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
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">
			<div class="panel-heading">
              <h3 class="panel-title">Title</h3>
            </div>
            <div class="panel-body">
            	<div class="row">
              		<div class="col-md-3 col-lg-3 " align="center"> 
                		<img alt="User Pic" class="img-circle"
                			src="<c:url value="/resources/img/user_photo.png" />" > 
                		<button class="btn btn-info" style="margin-top: 10px">Load Image</button>
                	</div>
                	<div class=" col-md-9 col-lg-9 "> 
                		<table class="table table-user-information">
                    		<tbody>
                      			<tr>
                        			<td>
                        				<spring:message code="label.firstname" />:
                        			</td>
                        			<td>
                        				<c:out value="${sessionScope.user.firstName}" />
                        			</td>
                        			<td>
                        				<button data-original-title="<spring:message code="label.edit_first_name" />"
                        					 data-toggle="tooltip" type="button" class="btn btn-sm btn-warning" 
                        					 id="btn_change_first_name">
                        					<i class="glyphicon glyphicon-edit"></i>
                        				</button>
                        			</td>
                      			</tr>
	                      		<tr>
	                        		<td>
                        				<spring:message code="label.lastname" />:
                        			</td>
                        			<td>
                        				<c:out value="${sessionScope.user.lastName}" />
                        			</td>
                        			<td>
                        				<button data-original-title="<spring:message code="label.edit_last_name" />" 
                        					data-toggle="tooltip" type="button" class="btn btn-sm btn-warning"
                        					 id="btn_change_last_name">
                        					<i class="glyphicon glyphicon-edit"></i>
                        				</button>
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
                        					type="button" class="btn btn-sm btn-warning">
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
              			<button class="btn btn-info" data-toggle="modal" data-target="#modal_change_password">
							<spring:message code="label.change_password"/>
						</button>
                	</div>
              	</div>
            </div>
		</div>
	</div>
</div>

<!-- Modal window for change first name -->
<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" 
    id="modal_change_first_name" aria-labelledby="myLargeModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
				    <span aria-hidden="true">&times;</span>
				    <span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">
					<spring:message code="label.edit_first_name" />
				</h4>
			</div>
			<form id="form_change_first_name" class="form-horizontal" method="POST" role="form" 
				action="<c:url value="/changeFirstName" />">
				<div class="modal-body">				
					<div class="panel panel-info">
        				<div class="panel-body">
        					<div class="form-group">
								<label class="col-md-3 control-label" for="first_name">
									<spring:message code="label.firstname" /> 
								</label>
								<div class="col-md-6">
									<input type="text" id="first_name" class="form-control" name="first_name"
										placeholder="<spring:message code="placeholder.firstname" />"  
										value="<c:out value="${sessionScope.user.firstName}" />"/>
								</div>
							</div>
	        			</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-success" >
						<spring:message code="label.accept"/>
					</button>
					<button type="reset" class="btn btn-info" data-dismiss="modal">
						<spring:message code="label.cancel" />
					</button>
				</div>
			</form>
		</div>		
	</div> 
</div>

<!-- Modal window for change last name -->
<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" 
    id="modal_change_last_name" aria-labelledby="myLargeModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
				    <span aria-hidden="true">&times;</span>
				    <span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">
					<spring:message code="label.edit_last_name" />
				</h4>
			</div>
			<form id="form_change_last_name" class="form-horizontal" method="POST" role="form" 
				action="<c:url value="/changeLastName" />">
				<div class="modal-body">				
					<div class="panel panel-info">
        				<div class="panel-body">
        					<div class="form-group">
								<label class="col-md-3 control-label" for="first_name">
									<spring:message code="label.lastname" /> 
								</label>
								<div class="col-md-6">
									<input type="text" id="last_name" class="form-control" name="last_name"
										placeholder="<spring:message code="placeholder.lastname" />"  
										value="<c:out value="${sessionScope.user.lastName}" />"/>
								</div>
							</div>
	        			</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-success" >
						<spring:message code="label.accept"/>
					</button>
					<button type="reset" class="btn btn-info" data-dismiss="modal">
						<spring:message code="label.cancel" />
					</button>
				</div>
			</form>
		</div>		
	</div> 
</div>

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
									<input type="password" id="old_password" class="form-control" name="old_password"
										placeholder="<spring:message code="placeholder.old_password"/>" />
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


<script>
jQuery(document).ready(function ($) {
	
	$("#btn_change_first_name").click(function(){	
		$('#modal_change_first_name').modal();
	})
	
	$("#btn_change_last_name").click(function(){	
		$('#modal_change_last_name').modal();
	})
	
    $('[data-toggle="tooltip"]').tooltip();
    
	$("#form_change_first_name").submit(function(){
		var first_name = $("#first_name").val();
		if(first_name != ""){
			var url = $(this).attr("action");
			var json = { "firstName" : first_name };
			$.ajax({
		        url: url,
		        data: JSON.stringify(json),
		        contentType: 'application/json',
		        type: "POST",
		        success: function(response) {
		        	if(response == "success"){	
		        		location.reload();
		        		$("#modal_change_first_name").modal("hide");
		        	} else {
		        		$("#first_name").val('');
		        	}
		        }
		    });
		}
		return false;
	});
	
	$("#form_change_last_name").submit(function(){
		var last_name = $("#last_name").val();
		if(last_name != ""){
			var url = $(this).attr("action");
			var json = { "lastName" : last_name };
			$.ajax({
		        url: url,
		        data: JSON.stringify(json),
		        contentType: 'application/json',
		        type: "POST",
		        success: function(response) {
		        	if(response == "success"){	
		        		location.reload();
		        		$("#modal_change_last_name").modal("hide");
		        	} else {
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
    
    $("#form_change_first_name").validate({
    	rules: {
    		first_name: {
    			required: true,
    			regexp: "^[A-ZА-ЯІЇЄ]{1}[a-zа-яіїє]{1,30}$"
    		}
    	},
    	messages: {
    		first_name: {
    			required: "${required}",
				regexp: "${firstname}"
    		}
    	}
    });
    
    $("#form_change_last_name").validate({
    	rules: {
    		last_name: {
    			required: true,
    			regexp: "^[A-ZА-ЯІЇЄ]{1}[a-zа-яіїє]{1,30}$"
    		}
    	},
    	messages: {
    		last_name: {
    			required: "${required}",
				regexp: "${lastfirstname}"
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