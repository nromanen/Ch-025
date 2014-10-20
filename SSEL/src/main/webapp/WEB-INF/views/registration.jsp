<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="span7 offset2">
	<div>
		<form:form id="registration" class="form-horizontal" method="POST"
			commandName="registration"
			action="${pageContext.request.contextPath}/registration">
			<div class="control-group">
					<label class="control-label" for="inputEmailReg">Email</label>
					<div class="controls">
						<form:input path="email" type="email" id="inputEmailReg" required="true" /><br>
						<form:errors path="email" cssClass="error"></form:errors>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label" for="inputPasswordReg">Password</label>
					<div class="controls">
						<form:input path="password" type="password" id="inputPasswordReg"
							placeholder="Password" required="true" /><br>
						<form:errors path="password" cssClass="error"></form:errors>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="confirmPasswordReg">Confirm password</label>
					<div class="controls">
						<form:input path="confirmPassword" type="password"
							id="confirmPasswordReg" placeholder="Password" /><br>
						<form:errors path="confirmPassword" cssClass="error" required="true"></form:errors>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label" for="confirmPasswordReg">First name</label>
					<div class="controls">
						<form:input path="firstName" type="text"
							id="firstNameReg" placeholder="First Name" required="true"/><br>
						<form:errors path="firstName" cssClass="error" required="true"></form:errors>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label" for="confirmPasswordReg">Confirm password</label>
					<div class="controls">
						<form:input path="lastName" type="text"
							id="lastNameReg" placeholder="Last Name" required="true"/><br>
						<form:errors path="lastName" cssClass="error" required="true"></form:errors>
					</div>
				</div>

				<div class="form-actions">
					<button type="submit" class="btn btn-primary">
						Registration
					</button>
				</div>
		</form:form>
	</div>
</div>
