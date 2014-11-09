<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="<c:url value="/resources/js/bootstrapValidator.js" />" ></script>
<script src="<c:url value="/resources/js/registration.js" />" ></script>
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
				<form:form id="registration_form" class="form-horizontal" method="POST"
					commandName="registration" role="form" action="${registration}" 
					data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
			     	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
			     	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh"
			     	data-bv-submitbuttons='button[type="submit"]'
			     	data-bv-live="enabled">
					<div class="form-group">
						<spring:message code="dataerror.field_required" var="field_required"/>
						<spring:message code="dataerror.password_pattern" var="password_pattern"/>
						<spring:message code="dataerror.passwords_do_not_match" var="passwords_do_not_match" />
						<spring:message code="dataerror.minimum_4_characters" var="minimum_4_characters"/>
						<spring:message code="dataerror.email_example" var="email_example"/>
						<spring:message code="dataerror.firstname" var="error_firstname"/>
						<spring:message code="dataerror.lastname" var="error_lastname"/>
						<spring:message code="dataerror.email_exist" var="email_exist" />
						<spring:message code="label.email_free" var="email_free"/>
						
						<label class="col-md-3 control-label" for="inputEmailReg">
							<spring:message code="label.email" />
						</label>
						<div class="col-md-9">
							<spring:message code="placeholder.email" var="email"/>
							<form:input path="email" type="email" id="inputEmailReg" name="email" 
								placeholder="${email}" cssClass="form-control"  
								data-bv-trigger="blur"
								data-bv-notempty="true"
		                		data-bv-notempty-message="${field_required}" 
		                		data-bv-emailaddress-message="${email_example}" 
		                		data-bv-remote="true"
								data-bv-remote-message="${email_exist}"
								data-bv-remote-type="get"
								data-bv-remote-name="email"
								data-bv-remote-url="isExistsEmail"
								data-bv-valid-message="${email_free}" />
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
								placeholder="${password}" cssClass="form-control"  name="password" 
								data-bv-notempty="true"
                				data-bv-notempty-message="${field_required}"
								pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[/@/./&/!#/$%/^/*/?])(?!.*\s).{4,20}$"
                				data-bv-regexp-message="${password_pattern}"
                				data-bv-stringlength="true"
                				data-bv-stringlength-min="4"
                				data-bv-stringlength-message="${minimum_4_characters}" />
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
								id="confirmPasswordReg" placeholder="${confirm_password}" name="confirmPassword" 
								data-bv-notempty="true"
                				data-bv-notempty-message="${field_required}"
								data-bv-identical="true"
                				data-bv-identical-field="password"
               			 		data-bv-identical-message="${passwords_do_not_match}" />
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
								id="firstNameReg" placeholder="${firstname}" name="firstName" 
								data-bv-notempty="true"
	                			data-bv-notempty-message="${field_required}"
	                			pattern="^[A-ZА-ЯІЇЄ]{1}[a-zа-яіїє]{1,30}$"
	                			data-bv-regexp-message="${error_firstname}" />
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
								id="lastNameReg" placeholder="${lastname}" name="lastName" 
								data-bv-notempty="true"
	                			data-bv-notempty-message="${field_required}"
	                			pattern="^[A-ZА-ЯІЇЄ]{1}[a-zа-яіїє]{1,30}$"
	                			data-bv-regexp-message="${error_lastname}" />
							<form:errors path="lastName" element="div" style="padding: 2px; margin: 2px;" cssClass="alert alert-danger"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="confirmPasswordReg">
						</label>
						<div class="col-md-9">
							<form:checkbox path="teacher" name="teacher" id="teacher"/> 
							<label class="control-label" for="teacher">
								<spring:message code="label.registration_as_teacher" />
							</label>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-offset-3 col-md-9">
							<button type="submit" class="btn btn-success" id="btnsumbmit">
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