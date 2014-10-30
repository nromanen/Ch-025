<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="<c:url value="/resources/js/jquery.validate.min.js" />"></script>
<div class="container">
	<div id="signupbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
 		<div class="panel panel-info">
    		<div class="panel-heading">
            	<div class="panel-title">
            		<spring:message code="label.registration" />
            	</div>
                <div style="float:right; font-size: 85%; position: relative; top:-10px">
                	<a id="signinlink" href="<c:url value="/login" />">
                		<spring:message code="label.sing_in"/> 
                	</a>
                </div>
        	</div>  
	       	<div class="panel-body">
	       		<c:url value="/registration" var="registration"/>
				<form:form id="registration" class="form-horizontal" method="POST"
					commandName="registration" role="form" action="${registration}" >
					<div class="form-group">
						<spring:message code="dataerror.field_required" var="required"/>
						<spring:message code="dataerror.password_pattern" var="pattern"/>
						<spring:message code="dataerror.minimum_4_characters" var="minimum"/>
						<spring:message code="dataerror.email_example" var="email_example"/>
						<spring:message code="dataerror.passwords_do_not_match" var="passwords_do_not_match" />
						<spring:message code="dataerror.firstname" var="error_firstname"/>
						<spring:message code="dataerror.lastname" var="error_lastname"/>
						<spring:message code="dataerror.email_exist" var="email_exist" />
						
						<label class="col-md-3 control-label" for="inputEmailReg">
							<spring:message code="label.email" />
						</label>
						<div class="col-md-9">
							<spring:message code="placeholder.email" var="email"/>
							<form:input path="email" type="email" id="inputEmailReg" name="email" 
								placeholder="${email}" cssClass="form-control"  />
							<form:errors path="email" element="div" style="padding: 2px; margin: 2px;" cssClass="alert alert-danger" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="inputPasswordReg">
							<spring:message code="label.password"/>
						</label>
						<div class="col-md-9">
							<spring:message code="placeholder.password" var="password"/>
							<form:input path="password" type="password" id="inputPasswordReg" 
								placeholder="${password}" cssClass="form-control"  name="password"/>
							<form:errors path="password" element="div" style="padding: 2px; margin: 2px;" cssClass="alert alert-danger" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="confirmPasswordReg">
							<spring:message code="label.confirm_password" />
						</label>
						<div class="col-md-9">
							<spring:message code="placeholder.confirm_password" var="confirm_password"/>
							<form:input path="confirmPassword" type="password" cssClass="form-control"
								id="confirmPasswordReg" placeholder="${confirm_password}" name="confirmPassword"/>
							<form:errors path="confirmPassword" element="div" style="padding: 2px; margin: 2px;" cssClass="alert alert-danger" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="firstNameReg">
							<spring:message code="label.firstname" />
						</label>
						<div class="col-md-9">
							<spring:message code="placeholder.firstname" var="firstname"/>
							<form:input path="firstName" type="text" cssClass="form-control"
								id="firstNameReg" placeholder="${firstname}" name="firstName"/>
							<form:errors path="firstName" element="div" style="padding: 2px; margin: 2px;" cssClass="alert alert-danger" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="lastNameReg">
							<spring:message code="label.lastname" />
						</label>
						<div class="col-md-9">
							<spring:message code="placeholder.lastname" var="lastname"/>
							<form:input path="lastName" type="text" cssClass="form-control"
								id="lastNameReg" placeholder="${lastname}" name="lastName"/>
							<form:errors path="lastName" element="div" style="padding: 2px; margin: 2px;" cssClass="alert alert-danger"/>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-offset-3 col-md-9">
							<button type="submit" class="btn btn-success" disabled="disabled" id="btnsumbmit">
								<spring:message code="label.registration"/>
							</button>
							<button type="reset" class="btn btn-primary">
								<spring:message code="label.cancel" />
							</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
<script>
jQuery(document).ready(function ($) {
	
	$("#inputEmailReg").on("blur", function(){
		var json = {"email" : $('#inputEmailReg').val()};
		$.ajax({
			type: "POST",
			url: "registration_ajax",
			contentType: "application/json",
			data: JSON.stringify(json),
			success : function(response) {
				if (response == true) {
					$('#inputEmailReg').addClass("error").after("<label id='lbl' class='error'>${email_exist}</label>");
					$('#btnsumbmit').prop("disabled", true);
				}
				else { 
					$('#inputEmailReg').removeClass("error");
					$('#lbl').remove();
					$('#btnsumbmit').prop("disabled", false);
				}
			}
		});		
	});
	
	$.validator.addMethod(
		'regexp',
		function(value, element, regexp) {
			var re = new RegExp(regexp);
		    return this.optional(element) || re.test(value);
		},
		"Please check your input."
	);
	$("#registration").validate({
		rules: {
			password: {
				required: true,
				minlength: 4,
				regexp: "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[//@/./&/!#/$%/^/*/?])(?!.*\\s).{4,20}$"
			},
			email: {
				required: true,
				email: true
			},
			confirmPassword: {
				required: true,
				equalTo: "#inputPasswordReg"
			},
			firstName: {
				required: true,
				regexp: "^[A-ZА-ЯІЇЄ]{1}[a-zа-яіїє]{1,30}$"
			},
			lastName: {
				required: true,
				regexp: "^[A-ZА-ЯІЇЄ]{1}[a-zа-яіїє]{1,30}$"
			}
		},
		messages: {
			password: {
				required: "${required}",
				minlength: "${minimum}",
				regexp: "${pattern}"
			},
			email: {
				required: "${required}",
				email: "${email_example}"
			}, 
			confirmPassword: {
				required: "${required}",
				equalTo: "${passwords_do_not_match}"
			},
			firstName: {
				required: "${required}",
				regexp: "${error_firstname}"
			},
			lastName: {
				required: "${required}",
				regexp: "${error_lastname}"
			}
		}
	})
});
</script>