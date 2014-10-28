<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="container">
	<div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
     	<div class="panel panel-info" >
     		<div class="panel-heading">
            	<div class="panel-title">
            		<spring:message code="label.forgot_password"/>
            	</div> 
            	<div style="float:right; font-size: 85%; position: relative; top:-10px">
             		<a id="signinlink" href="<c:url value="/login" />">
             			<spring:message code="label.sing_in" />
             		</a>
             	</div>
             </div>
		
			<div style="padding-top:30px" class="panel-body">
				<form role="form" action="<c:url value="/remind" />" accept-charset="UTF-8" method="POST">
					<span class="help-block">
         	 			Email address you use to log in to your account
          				<br>
          				We'll send you an email with instructions to choose a new password.
        			</span>
        			<div class="input-group">
						<span class="input-group-addon">@</span>  
						<input class="form-control" placeholder="<spring:message code="placeholder.email" />" 
							name="email" type="email" required="required" autofocus>      		
        			</div>
        			<div style="margin-top:10px" class="form-group">
						<button type="submit" class="btn btn-block btn-success">
							<spring:message code="label.send_instruction" />
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>