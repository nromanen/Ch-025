<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="container">
	<div id="signupbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
		<div class="panel panel-info">
			<div class="panel-heading">
            	<div class="panel-title">resetPassword</div>
        	</div>
        	<div class="panel-body">
        		<form:form id="resetPassword" class="form-horizontal" method="POST"
					commandName="resetPassword" role="form"
					action="${pageContext.request.contextPath}/remind/pass">
					<form:hidden path="key" />
					<div class="form-group">
						<label class="col-md-3 control-label" for="inputPasswordReg">Password</label>
						<div class="col-md-9">
							<form:input path="password" type="password" id="inputPasswordReg"
								placeholder="Password" required="true" cssClass="form-control"/>
							<form:errors path="password" element="div" style="padding: 2px; margin: 2px;" 
								cssClass="alert alert-danger" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="confirmPasswordReg">Confirm password</label>
						<div class="col-md-9">
							<form:input path="confirmPassword" type="password" cssClass="form-control"
								id="confirmPasswordReg" placeholder="Password" />
							<form:errors path="confirmPassword" element="div" style="padding: 2px; margin: 2px;" 
								cssClass="alert alert-danger" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-offset-3 col-md-9">
							<button type="submit" class="btn btn-info">Save</button>
							<button type="reset" class="btn btn-info">Cancel</button>
						</div>
					</div>
				</form:form>
        	</div>
		</div>
	</div>
</div>