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
				<c:if test="${error eq 'AccountExpiredException'}">
					<div class="alert alert-warning">
						<strong><spring:message code="label.warning" /></strong>
						<spring:message code="label.account_expired_exception" />
						<button class="btn btn-info" type="button" data-toggle="modal" 
							data-target="#modal_expired_account" id="btn_open_expired_modal">
							<spring:message code="label.continue" />
						</button>
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
				</form>
			</div>
		</div>
		<div class="panel panel-primary col-md-6 col-md-offset-3" style="padding: 0">
			<div class="panel-heading">
        		<h3 class="panel-title">
        			<spring:message code="label.sign_in_via_social" />
        		</h3>
      		</div>
	        <div class="panel-body">   
	        	<div class="form-inline" role="form">
	        	<div class="row social-button-row" align="center">
	            	<form class="form-group" action="<c:url value="/auth/facebook" />" method="POST" role="form" >
						<button class="btn btn-social-icon btn-facebook" type="submit">
		                    <i class="fa fa-facebook"></i>
		                </button>
		                <input type="hidden" name="scope" value="email,publish_stream,offline_access,user_birthday" />
	             	</form>
	             	<form class="form-group" action="<c:url value="/auth/linkedin" />" method="POST" role="form" >
	                    <button class="btn btn-social-icon btn-linkedin" type="submit">
	                    	<i class="fa fa-linkedin"></i>
	                    </button>
	                </form>
				</div>
				</div>
	        </div>
	    </div>
	</div>
</div>

<!-- Modal window for load photo -->
<div class="modal animated bounce bs-example-modal-sm" tabindex="-1" role="dialog" 
    id="modal_expired_account" aria-labelledby="mySmallModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
				    <span aria-hidden="true">&times;</span>
				    <span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">
					<spring:message code="label.continue_account_date" />
				</h4>
			</div>
			<div class="modal-body">	
				<form id="form_expired_account" class="form-horizontal" method="POST" role="form" 
					action="<c:url value="/expiredAccount" />" 
					data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	      			data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	      			data-bv-feedbackicons-validating="glyphicon glyphicon-refresh"
	      			data-bv-submitbuttons='button[type="submit"]'
	      			data-bv-live="enabled">
					<div class="modal-body">				
						<div class="panel panel-info">
	        				<div class="panel-body">
								<div class="form-group">
									<label class="col-md-3 control-label" for="phone">
										<spring:message code="label.email" />
									</label>
									<div class="col-md-6">
				                    	<input type="email" id="email_send" class="form-control" name="email_send"
											data-bv-notempty="true"
					                		data-bv-notempty-message="<spring:message code="dataerror.field_required" />" 
					                		data-bv-emailaddress-message="<spring:message code="dataerror.email_example" />" 
					                		data-toggle="tooltip" 
											data-placement="top"
											title="<spring:message code="placeholder.email" />" >
		                			</div>
	                			</div>
		        			</div>
		        			<div class="form-group">
		        				<c:set value="${pageContext.response.locale}" var="local" />
		        				<div class="col-md-offset-1 col-md-10"> <spring:message code="label.message" />
		                			<textarea rows="3" id="message" class="form-control" placeholder="<spring:message code="label.message" />"
		                				style="resize: none" data-toggle="tooltip" title="<spring:message code="label.message" />"
		                				lang="${local}" ><spring:message code="message.please_continue_term"/></textarea> 
		                		</div>
		        			</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" id="btn_change_password_submit"  
							class="btn btn-success" >
							<spring:message code="label.accept"/>
						</button>
						<button type="reset" id="btn_form_close" class="btn btn-info" data-dismiss="modal">
							<spring:message code="label.cancel" />
						</button>
					</div>
				</form>
			</div>
		</div>		
	</div> 
</div>
