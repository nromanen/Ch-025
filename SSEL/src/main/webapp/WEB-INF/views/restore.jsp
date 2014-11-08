<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script src="<c:url value="/resources/js/bootstrapValidator.js" />" ></script>
<script src="<c:url value="/resources/js/restore.js" />" ></script>
<div class="container">
	<div id="signupbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
		<div class="panel panel-info">
			<div class="panel-heading">
            	<div class="panel-title">
            		<spring:message code="label.reset_password" />
            	</div>
        	</div>
        	<div class="panel-body">
        		<c:url value="/remind/pass" var="pass"/>
        		<form:form id="resetPassword" class="form-horizontal" method="POST"
					commandName="resetPassword" role="form" action="${pass}" 
					data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
			     	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
			     	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh"
			     	data-bv-submitbuttons='button[type="submit"]'
			     	data-bv-live="enabled">
					<form:hidden path="key" />
					<div class="form-group">
						<label class="col-md-3 control-label" for="inputPasswordReg">
							<spring:message code="label.new_password" />
						</label>
						<div class="col-md-9">
							<spring:message code="placeholder.new_password" var="new_password"/>
							<spring:message code="dataerror.field_required" var="field_required"/>
							<spring:message code="dataerror.password_pattern" var="password_pattern"/>
							<spring:message code="dataerror.minimum_4_characters" var="minimum_4_characters"/>
							<form:input path="password" type="password" id="inputPasswordReg"
								placeholder="${new_password}" cssClass="form-control" 
								data-toggle="tooltip" 
								data-placement="top"
								title="${new_password}" 
								data-bv-notempty="true"
                				data-bv-notempty-message="${field_required}"
                				pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[/@/./&/!#/$%/^/*/?])(?!.*\s).{4,20}$"
                				data-bv-regexp-message="${password_pattern}"
                				data-bv-stringlength="true"
                				data-bv-stringlength-min="4"
                				data-bv-stringlength-message="${minimum_4_characters}"/>
							<form:errors path="password" element="div" style="padding: 2px; margin: 2px;" 
								cssClass="alert alert-danger" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="confirmPasswordReg">
							<spring:message code="label.confirm_password" />
						</label>
						<div class="col-md-9">
							<spring:message code="placeholder.confirm_password" var="confirm_password"/>
							<spring:message code="dataerror.passwords_do_not_match" var="passwords_do_not_match" />
							<form:input path="confirmPassword" type="password" cssClass="form-control"
								id="confirmPasswordReg" placeholder="${confirm_password}" 
								data-bv-identical="true"
                				data-bv-identical-field="password"
               			 		data-bv-identical-message="${passwords_do_not_match}" 
               			 		data-toggle="tooltip" 
								data-placement="top"
								title="${confirm_password}"/>
							<form:errors path="confirmPassword" element="div" style="padding: 2px; margin: 2px;" 
								cssClass="alert alert-danger" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-offset-3 col-md-9">
							<button type="submit" class="btn btn-success">
								<spring:message code="label.accept" />
							</button>
							<button type="reset" class="btn btn-info">
								<spring:message code="label.cancel" />
							</button>
						</div>
					</div>
				</form:form>
        	</div>
		</div>
	</div>
</div>