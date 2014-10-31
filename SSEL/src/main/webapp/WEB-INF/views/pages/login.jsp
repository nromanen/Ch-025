<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script>
	$(document).ready(function () {
		$('#email, #password').on("keyup", function() {
			if ($('#email').val() != 0 && $('#password').val() != 0) {
				$('#submit_btn').removeAttr('disabled');
			} else {
				$('#submit_btn').attr("disabled", "disabled");
			}
		});
	});
</script>

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
					accept-charset="UTF-8">
					<div style="margin-bottom: 25px" class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
						<input class="form-control" placeholder="<spring:message code="placeholder.email" />" 
							name="j_username" type="email" autofocus required="required" id="email" >
					</div>
					<div style="margin-bottom: 10px" class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
						<input class="form-control" placeholder="<spring:message code="placeholder.password"/>"
							name="j_password" type="password" required="required" id="password">
					</div>
					<div class="input-group">
						<div class="checkbox">
							<label> 
								<input name="remember" type="checkbox" value="Remember Me" 
									name="_spring_security_remember_me"> <spring:message code="label.remember"/>
							</label>
						</div>
					</div>
					<div style="margin-top:10px" class="form-group">
						<button type="submit" class="btn btn-success" id="submit_btn" disabled>
							<spring:message code="label.sing_in" />
						</button>
						<a href="<c:url value="/registration" />" class="btn btn-primary">
							<spring:message code="label.registration"/> 
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
