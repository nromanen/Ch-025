<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="<c:url value="/resources/js/jquery.validate.min.js" />" ></script>
<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">
			<div class="panel-heading">
              <h3 class="panel-title">Sheena Kristin A.Eschor</h3>
            </div>
            <div class="panel-body">
            	<div class="row">
              		<div class="col-md-3 col-lg-3 " align="center"> 
                		<img alt="User Pic" class="img-circle"
                			src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=100" > 
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
                        				<button data-original-title="Edit first name" data-toggle="tooltip"
                        					type="button" class="btn btn-sm btn-warning">
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
                        				<button data-original-title="Edit last name" data-toggle="tooltip"
                        					type="button" class="btn btn-sm btn-warning">
                        					<i class="glyphicon glyphicon-edit"></i>
                        				</button>
                        			</td>
	                      		</tr>
	                      		<tr>
	                        		<td>Date of registration</td>
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
              			<button class="btn btn-info" data-toggle="modal" data-target="#myModal">
							<spring:message code="label.change_password"/>
						</button>
                	</div>
              	</div>
            </div>
		</div>
	</div>
</div>

<!-- Modal window for change passwords -->
<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" 
    	id="myModal" aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<spring:message code="dataerror.field_required" var="required"/>
			<spring:message code="dataerror.password_pattern" var="pattern"/>
			<spring:message code="dataerror.passwords_do_not_match" var="passwords_do_not_match" />
			<spring:message code="dataerror.minimum_4_characters" var="minimum"/>	
			<spring:message code="dataerror.password_incorrect" var="password_incorrect"/>
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
				    	<span aria-hidden="true">&times;</span>
				       	<span class="sr-only">Close</span>
				    </button>
					<h4 class="modal-title" id="myModalLabel">
						<spring:message code="label.change_password"/>
					</h4>
				</div>
				<form id="form_change_password" class="form-horizontal" method="POST" role="form" action="">
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
						<button type="submit" class="btn btn-success">
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
<script>
jQuery(document).ready(function ($) {
    $('[data-toggle="tooltip"]').tooltip();
    
    $("#old_password").on("blur", function(){
    	var email = $("#email").text().trim();
		var json = {"old_password" : $('#old_password').val(), 
				"email" : email};
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
					//$('#btnsumbmit').prop("disabled", true);
				}
				else { 
					$('#old_password').removeClass("error");
					$('#lbl').remove();
					//$('#old_password').prop("disabled", false);
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
    })
});
</script> 