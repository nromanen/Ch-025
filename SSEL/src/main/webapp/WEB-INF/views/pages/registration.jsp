<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 <div class="col-md-4 col-md-offset-4">
 	<div class="login-panel panel panel-default">
    	<div class="panel-heading">
        	<h3 class="panel-title">Registration</h3>
        </div>
        <div class="well span4 offset4">
			<form:form id="registration" class="form-inline" method="POST"
				commandName="registration" role="form"
				action="${pageContext.request.contextPath}/registration">
				 <fieldset>
					<div class="form-group">
						<label class="control-label" for="inputEmailReg">Email</label>
						<div class="controls">
							<form:input path="email" type="email" id="inputEmailReg" 
								required="true" cssClass="form-control" placeholder="Email"/>
							<form:errors path="email" cssClass="error"></form:errors>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label" for="inputPasswordReg">Password</label>
						<div class="controls">
							<form:input path="password" type="password" id="inputPasswordReg"
								placeholder="Password" required="true" cssClass="form-control"/>
							<form:errors path="password" cssClass="error"></form:errors>
						</div>
					</div>
		
						<div class="form-group">
							<label class="control-label" for="confirmPasswordReg">Confirm password</label>
							<div class="controls">
								<form:input path="confirmPassword" type="password" cssClass="form-control"
									id="confirmPasswordReg" placeholder="Password" />
								<form:errors path="confirmPassword" cssClass="error" required="true"></form:errors>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label" for="confirmPasswordReg">First name</label>
							<div class="controls">
								<form:input path="firstName" type="text" cssClass="form-control"
									id="firstNameReg" placeholder="First Name" required="true"/>
								<form:errors path="firstName" cssClass="error" required="true"></form:errors>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label" for="confirmPasswordReg">Last name</label>
							<div class="controls">
								<form:input path="lastName" type="text" cssClass="form-control"
									id="lastNameReg" placeholder="Last Name" required="true"/>
								<form:errors path="lastName" cssClass="error" required="true"></form:errors>
							</div>
						</div>
		
						<div class="form-actions">
							<button type="submit" class="btn btn-primary">
								Registration
							</button>
						</div>
					</fieldset>
			</form:form>
		</div>
	</div>
</div>