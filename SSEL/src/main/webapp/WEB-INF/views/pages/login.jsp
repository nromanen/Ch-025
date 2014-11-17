<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script src="<c:url value="/resources/js/bootstrapValidator.js" />" ></script>
<script src="<c:url value="/resources/js/login.js" />" ></script>
<div class="container">
	<div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
     	<div class="panel panel-info" >
        	<div class="panel-heading">
            	<div class="panel-title">
            		<spring:message code="label.sing_in" />
            	</div>
                <div style="float:right; font-size: 80%; position: relative; top:-10px">
                	<a href="<c:url value="/remind" />">
                		<spring:message code="label.forgot_password"/>
                	</a>
                </div>
         	</div>    
			<div style="padding-top:30px" class="panel-body">
				<c:if test="${error eq 'BadCredentialsException'}">
					<div class="alert alert-warning">
						<strong><spring:message code="label.warning" /></strong>
						<spring:message code="label.bad_redentials_exception" />
					</div>
				</c:if>
				<c:if test="${error eq 'userDisabled'}">
					<div class="alert alert-warning">
						<strong><spring:message code="label.warning" /></strong>
						<spring:message code="label.user_disabled" />
					</div>
				</c:if>
				<form role="form" action="<c:url value="/j_spring_security_check" />" method="POST"
					accept-charset="UTF-8" id ="login_form"
					data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
			     	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
			     	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh"
			     	data-bv-submitbuttons='button[type="submit"]'
			     	data-bv-live="enabled">
			     	<div class="form-group">
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
							<input class="form-control" placeholder="<spring:message code="placeholder.email" />" 
								name="j_username" type="email" autofocus id="email" 
								data-bv-notempty="true"
			                	data-bv-notempty-message="<spring:message code="dataerror.field_required" />" 
			                	data-bv-emailaddress-message="<spring:message code="dataerror.email_example" />" >
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
							<input class="form-control" placeholder="<spring:message code="placeholder.password"/>"
								name="j_password" type="password" required="required" id="password"
								data-bv-notempty="true"
			                	data-bv-notempty-message="<spring:message code="dataerror.field_required" />"  >
						</div>
					</div>
					<div class="input-group">
						<div class="checkbox">
							<label> 
								<input name="remember" type="checkbox" value="Remember Me" 
									name="_spring_security_remember_me"> <spring:message code="label.remember"/>
							</label>
						</div>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-success" id="submit_btn">
							<spring:message code="label.sing_in" />
						</button>
						<a href="<c:url value="/registration" />" class="btn btn-primary">
							<spring:message code="label.registration"/> 
						</a>
					</div>
					<div class="form-group">
						<a class="btn btn-social btn-twitter" href="#">
    						<i class="fa fa-twitter"></i> Sign in with Twitter
  						</a>
  						<a class="btn btn-social btn-facebook" href="#">
    						<i class="fa fa-facebook"></i> Sign in with Facebook
  						</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
