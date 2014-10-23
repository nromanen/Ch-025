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
<<<<<<< HEAD
        <div class="panel-body">
			<form:form id="registration" class="form-horizontal" method="POST"
=======
        <div class="well span4 offset4">
			<form:form id="registration" class="form-inline" method="POST"
>>>>>>> 37484fc3bcc2c3a6a28617c65ac720821725ff7a
				commandName="registration" role="form"
				action="${pageContext.request.contextPath}/registration">
				 <fieldset>
					<div class="form-group">
						<label class="control-label" for="inputEmailReg">Email</label>
						<div class="controls">
							<form:input path="email" type="email" id="inputEmailReg" 
<<<<<<< HEAD
								required="true" cssClass="form-control" placeholder="Email"/><br>
							<form:errors path="email" cssClass="alert"></form:errors>
=======
								required="true" cssClass="form-control" placeholder="Email"/>
							<form:errors path="email" cssClass="error"></form:errors>
>>>>>>> 37484fc3bcc2c3a6a28617c65ac720821725ff7a
						</div>
					</div>
					<div class="form-group">
						<label class="control-label" for="inputPasswordReg">Password</label>
						<div class="controls">
							<form:input path="password" type="password" id="inputPasswordReg"
<<<<<<< HEAD
								placeholder="Password" required="true" cssClass="form-control"/><br>
							<form:errors path="password" cssClass="alert"></form:errors>
=======
								placeholder="Password" required="true" cssClass="form-control"/>
							<form:errors path="password" cssClass="error"></form:errors>
>>>>>>> 37484fc3bcc2c3a6a28617c65ac720821725ff7a
						</div>
					</div>
		
						<div class="form-group">
							<label class="control-label" for="confirmPasswordReg">Confirm password</label>
							<div class="controls">
								<form:input path="confirmPassword" type="password" cssClass="form-control"
<<<<<<< HEAD
									id="confirmPasswordReg" placeholder="Password" /><br>
=======
									id="confirmPasswordReg" placeholder="Password" />
>>>>>>> 37484fc3bcc2c3a6a28617c65ac720821725ff7a
								<form:errors path="confirmPassword" cssClass="error" required="true"></form:errors>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label" for="confirmPasswordReg">First name</label>
							<div class="controls">
								<form:input path="firstName" type="text" cssClass="form-control"
<<<<<<< HEAD
									id="firstNameReg" placeholder="First Name" required="true"/><br>
=======
									id="firstNameReg" placeholder="First Name" required="true"/>
>>>>>>> 37484fc3bcc2c3a6a28617c65ac720821725ff7a
								<form:errors path="firstName" cssClass="error" required="true"></form:errors>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label" for="confirmPasswordReg">Last name</label>
							<div class="controls">
								<form:input path="lastName" type="text" cssClass="form-control"
<<<<<<< HEAD
									id="lastNameReg" placeholder="Last Name" required="true"/><br>
=======
									id="lastNameReg" placeholder="Last Name" required="true"/>
>>>>>>> 37484fc3bcc2c3a6a28617c65ac720821725ff7a
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
