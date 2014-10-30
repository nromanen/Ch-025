<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
					commandName="registration" role="form" action="${registration}">
					<div id="signupalert" style="display:none" class="alert alert-danger">
                    	<p>Error:</p>
                        <span><form:errors path="*" element="div"/></span>
                    </div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="inputEmailReg">
							<spring:message code="label.email" />
						</label>
						<div class="col-md-9">
							<spring:message code="placeholder.email" var="email"/>
							<form:input path="email" type="email" id="inputEmailReg" required="true" 
								placeholder="${email}" cssClass="form-control" />
							<form:errors path="email" element="div" style="padding: 2px; margin: 2px;" cssClass="alert alert-danger" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="inputPasswordReg">
							<spring:message code="label.password"/>
						</label>
						<div class="col-md-9">
							<spring:message code="placeholder.password" var="password"/>
							<form:input path="password" type="password" id="inputPasswordReg" required="true"
								placeholder="${password}" cssClass="form-control"/>
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
								id="confirmPasswordReg" placeholder="${confirm_password}" />
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
								id="firstNameReg" placeholder="${firstname}" required="true"/>
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
								id="lastNameReg" placeholder="${lastname}" required="true"/>
							<form:errors path="lastName" element="div" style="padding: 2px; margin: 2px;" cssClass="alert alert-danger"/>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-offset-3 col-md-9">
							<button type="submit" class="btn btn-success">
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