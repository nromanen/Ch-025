<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="<c:url value="/resources/js/bootstrapValidator.js" />" ></script>
<script src="<c:url value="/resources/js/jquery.inputmask.js" />" ></script>
<div class="container">
	<spring:message code="label.processing" var="processing"/>
	<spring:message code="label.cancel" var="cancel"/>
	<spring:message code="label.upload" var="upload" />
	
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-7 col-lg-7 col-xs-offset-0 col-sm-offset-0 col-md-offset-2 col-lg-offset-2 toppad">
			<c:if test="${sessionScope.user.social ne 'REGISTRATION'}">
				<div class="alert alert-info alert-dismissible" role="alert">
	  				<button type="button" class="close" data-dismiss="alert">
	  					<span aria-hidden="true">&times;</span>
	  					<span class="sr-only">Close</span>
	  				</button>
	  				<spring:message code="label.go_to" />
	  				<a href="<c:url value="/student" />" class="alert-link">
	  					<spring:message code="label.student_cabinet"/> 
	  				</a>
	  				<spring:message code="message.social_network_password" />
				</div>
			</c:if>
            <div class="panel-body">
            	<div class="row">
              		<div class="col-md-4 col-lg-4 " align="center"> 
              			<c:choose>
	              			<c:when test="${empty sessionScope.image}">
	              				<img alt="User Pic" class="img-circle" style="height: 100px; width: 100px"
                					src="<c:url value="/resources/img/user_photo.png" />" > 
	              			</c:when>
	              			<c:otherwise>
	              				<img alt="User Pic" class="img-circle" style="height: 100px; width: 100px"
                					src="data:image/png;base64,<c:out value="${sessionScope.image}" />" > 
	              			</c:otherwise>
              			</c:choose>
                		<button type="button" class="btn btn-info" data-toggle="modal" 
                			data-target="#modal_load_photo" style="margin-top: 15px">
							<spring:message code="label.load_image" />
						</button>
                	</div>
                	<div class="col-md-8 col-lg-8 "> 
                		<form id="form_change_user_information" method="POST" role="form" 
							action="<c:url value="/changeUserInformation" />"
							data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
			      			data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
			      			data-bv-feedbackicons-validating="glyphicon glyphicon-refresh"
			      			data-bv-submitbuttons='button[type="submit"]'
			      			data-bv-live="enabled"
			      			class="form-horizontal">
			      			<input type="hidden" id="processing" value="${processing}">
			      			<input type="hidden" id="cancel" value="${cancel}">
			      			<input type="hidden" id="upload" value="${upload}">
	                		<div class="form-group">
	                			<label class="col-md-3 control-label" for="first_name">
	                        		<spring:message code="label.firstname" />:
	                        	</label>
	                        	<div class="col-md-7">
	                        		<div class="input-group">
		                       			<input type="text" id="first_name" class="form-control" name="first_name"
											placeholder="<spring:message code="placeholder.firstname" />" 
											value="<c:out value="${sessionScope.user.firstName}" />" 
											data-toggle="tooltip" 
											data-placement="top"
											title="<spring:message code="label.edit_first_name" />" 
											data-bv-notempty="true"
	                						data-bv-notempty-message="<spring:message code="dataerror.field_required" />"
	                						pattern="^[A-ZА-ЯІЇЄ]{1}[a-zа-яіїє]{1,30}$"
	                						data-bv-regexp-message="<spring:message code="dataerror.firstname" />">
                					</div>
                				</div>
	                        </div>
		                    <div class="form-group">
	                			<label class="col-md-3 control-label" for="last_name">
	                        		<spring:message code="label.lastname" />:
	                        	</label>
	                        	<div class="col-md-7">		
	                        		<div class="input-group">
		                        		<input type="text" id="last_name" class="form-control" name="last_name"
											placeholder="<spring:message code="placeholder.lastname" />"  
											value="<c:out value="${sessionScope.user.lastName}" />" 
											data-toggle="tooltip" 
											data-placement="top"
											title="<spring:message code="label.edit_last_name" />" 
											data-bv-notempty="true"
	                						data-bv-notempty-message="<spring:message code="dataerror.field_required" />"
	                						pattern="^[A-ZА-ЯІЇЄ]{1}[a-zа-яіїє]{1,30}$"
	                						data-bv-regexp-message="<spring:message code="dataerror.lastname" />">
                					</div>
	                        	</div>
	                        </div>
		                   	<div class="form-group">
		                   		<label class="col-md-3 control-label">
		                        	<spring:message code="label.registration_date" />:
		                        </label>
		                        <div class="col-md-6 control-label">		
	                        		<div class="input-group">
	                        			<label>
	                        				<fmt:formatDate value="${sessionScope.user.registration}" pattern="yyyy-MM-dd" />
		                        		</label>	
		                      		</div>
		                     	</div>
		                     </div>
		                     <div class="form-group">
		                   		<label class="col-md-3 control-label">
		                        	<spring:message code="label.email" />:
		                        </label>
		                        <div class="col-md-6 control-label">		
	                        		<div class="input-group">
	                        			<label>
		                        			<c:out value="${sessionScope.user.email}" />
		                        		</label>
		                        	</div>
		                        </div>	             
		                    </div>
							<div class="form-group">
		                   		<label class="col-md-3 control-label">
		                        	<spring:message code="label.expired_date" />:
		                        </label>
		                        <div class="col-md-6 control-label">		
	                        		<div class="input-group">
	                        			<label>
	                        				<fmt:formatDate value="${sessionScope.user.expired}" pattern="yyyy-MM-dd" />
		                        		</label>	
		                      		</div>
		                     	</div>
		                  	</div>
		                  	<div class="form-group">
	                			<label class="col-md-3 control-label">
	                        		<spring:message code="label.phone_number" />
	                        	</label>
	                        	<div class="col-md-5 control-label">		
	                        		<div class="input-group">
	                        			<label>
	                       					<c:out value="${sessionScope.user.phone}" />
		                        		</label>	
		                      		</div>                        	
	                        	</div>
	                        	<button title="<spring:message code="label.edit_phone_number" />" data-toggle="tooltip" 
	                        		class="btn btn-sm btn-warning" data-target="#modal_edit_phone" type="button" id="btn_phone_edit">
	                        		<i class="glyphicon glyphicon-edit"></i>
	                        	</button>
	                        </div>
		                    <div class="form-group">
		                    	<c:choose>
		                    		<c:when test="${sessionScope.user.social eq 'REGISTRATION'}">
		                    			<button type="button" class="btn btn-info" data-toggle="modal" 
		                    				data-target="#modal_change_password">
											<spring:message code="label.change_password"/>
										</button>
		                    		</c:when>
		                    		<c:otherwise>
		                    			<button type="button" id="btn_new_password" class="btn btn-info"  
		                    				data-target="#modal_new_password" data-toggle="modal">
											<spring:message code="label.create_password"/>
										</button>
		                    		</c:otherwise>
		                    	</c:choose>
								<button type="submit" class="btn btn-success">
									<spring:message code="label.save_changes" />
								</button>
							</div>
              			</form>
                	</div>
              	</div>
            </div>
		</div>
	</div>
</div>

<!-- Modal window for new password -->
<div class="modal animated bounce bs-example-modal-lg" tabindex="-1" role="dialog" 
    id="modal_new_password" aria-labelledby="myLargeModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
				    <span aria-hidden="true">&times;</span>
				    <span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">
					<spring:message code="label.change_password"/>
				</h4>
			</div>
			<form id="form_new_password" class="form-horizontal" method="POST" role="form" 
				action="<c:url value="/newPassword" />" 
				data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
      			data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
      			data-bv-feedbackicons-validating="glyphicon glyphicon-refresh"
      			data-bv-submitbuttons='button[type="submit"]'
      			data-bv-live="enabled">
				<div class="modal-body">				
					<div class="panel panel-info">
        				<div class="panel-body">
							<div class="form-group">
								<label class="col-md-3 control-label" for="new_password">
									<spring:message code="label.new_password" />
								</label>
								<div class="col-md-6">
									<input type="password" id="new_password" class="form-control" name="new_password"
										placeholder="<spring:message code="placeholder.new_password"/>" 
										data-bv-notempty="true"
                						data-bv-notempty-message="<spring:message code="dataerror.field_required" />"
                						pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[/@/./&/!#/$%/^/*/?])(?!.*\s).{4,20}$"
                						data-bv-regexp-message="<spring:message code="dataerror.password_pattern" />"
                						data-bv-stringlength="true"
                						data-bv-stringlength-min="4"
                						data-bv-stringlength-message="<spring:message code="dataerror.minimum_4_characters" />"
                						data-bv-different-field="old_password"
                						data-bv-different-message="<spring:message code="dataerror.password_same" />"   
                						data-bv-different="true"
                						data-toggle="tooltip" 
										data-placement="top"
										title="<spring:message code="placeholder.new_password" />" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label" for="confirm_password">
									<spring:message code="label.confirm_password" />
								</label>
								<div class="col-md-6">
									<input type="password" class="form-control" id="confirm_password" name="confirm_password"
										placeholder="<spring:message code="placeholder.confirm_password"/>" 
										data-bv-notempty="true"
                						data-bv-notempty-message="<spring:message code="dataerror.field_required" />"
										data-bv-identical="true"
                						data-bv-identical-field="new_password"
               			 				data-bv-identical-message="<spring:message code="dataerror.passwords_do_not_match" />" 
               			 				data-toggle="tooltip" 
										data-placement="top"
										title="<spring:message code="placeholder.confirm_password" />" />
								</div>
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

<!-- Modal window for edit phone -->
<div class="modal animated bounce bs-example-modal-lg" tabindex="-1" role="dialog" 
    id="modal_edit_phone" aria-labelledby="myLargeModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
				    <span aria-hidden="true">&times;</span>
				    <span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">
					<spring:message code="label.edit_phone_number" />
				</h4>
			</div>
			<form id="form_edit_phone" class="form-horizontal" method="POST" role="form" 
				action="<c:url value="/editPhone" />" 
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
									<spring:message code="label.phone_number" />
								</label>
								<div class="col-md-6">
			                    	<input type="text" id="phone" class="form-control" name="phone"
										data-toggle="tooltip" 
										data-placement="top"
										placeholder="+__(___)___-__-__"
										data-inputmask="'mask': '+99(999)999-99-99'"
										title="<spring:message code="label.phone_number" />" 
										data-bv-notempty="true"
		                				data-bv-notempty-message="<spring:message code="dataerror.field_required" />"
		                				pattern="^\+(\d{1,2})(\(\d{1,3}\))(\d{3}-\d{2}-\d{2})$"
		                				data-bv-regexp-message="<spring:message code="dataerror.phone_number" />" >
	                			</div>
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

<!-- Modal window for load photo -->
<div class="modal animated bounce bs-example-modal-lg" tabindex="-1" role="dialog" 
    id="modal_load_photo" aria-labelledby="myLargeModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
				    <span aria-hidden="true">&times;</span>
				    <span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">
					<spring:message code="label.load_image" />
				</h4>
			</div>
			<div class="modal-body">	
				<span class="btn btn-success fileinput-button">
			        <i class="glyphicon glyphicon-plus"></i>
			        <span>
			        	<spring:message code="label.add_file" />
			        </span>
			        <input id="fileupload" type="file" name="files[]" 
			        	accept="image/*">
			    </span>
			    <div id="progress" class="progress" style="margin-top: 15px">
			        <div class="progress-bar progress-bar-success"></div>
			    </div>
			    <div id="files" class="files"></div>
			</div>
		</div>		
	</div> 
</div>

<!-- Modal window for change passwords -->
<div class="modal animated bounce bs-example-modal-lg" tabindex="-1" role="dialog" 
    id="modal_change_password" aria-labelledby="myLargeModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
				    <span aria-hidden="true">&times;</span>
				    <span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">
					<spring:message code="label.change_password"/>
				</h4>
			</div>
			<form id="form_change_password" class="form-horizontal" method="POST" role="form" 
				action="<c:url value="/changePassword" />" 
				data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
      			data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
      			data-bv-feedbackicons-validating="glyphicon glyphicon-refresh"
      			data-bv-submitbuttons='button[type="submit"]'
      			data-bv-live="enabled">
				<div class="modal-body">				
					<div class="panel panel-info">
        				<div class="panel-body">
        					<div class="form-group">
								<label class="col-md-3 control-label" for="old_password">
									<spring:message code="label.old_password" />
								</label>
								<div class="col-md-6">
									<div class="input-group">
										<span class="input-group-btn">
		            						<button class="btn btn-default reveal" style="height: 34px" type="button" >
		            							<i class="glyphicon glyphicon-eye-open"></i>
		            						</button>
	          							</span>
										<input type="password" id="old_password" class="form-control pwd" name="old_password"
											placeholder="<spring:message code="placeholder.old_password"/>"
											data-bv-trigger="blur" 
											data-bv-notempty="true"
                							data-bv-notempty-message="<spring:message code="dataerror.field_required" />" 
                							data-toggle="tooltip" 
											data-placement="top"
											title="<spring:message code="placeholder.old_password" />" 
											data-bv-remote="true"
											data-bv-remote-message="<spring:message code="dataerror.password_incorrect" />"
											data-bv-remote-type="get"
											data-bv-remote-name="oldPassword"
											data-bv-remote-url="checkOldPassword" /> 
          							</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label" for="new_password">
									<spring:message code="label.new_password" />
								</label>
								<div class="col-md-6">
									<input type="password" id="new_password" class="form-control" name="new_password"
										placeholder="<spring:message code="placeholder.new_password"/>" 
										data-bv-notempty="true"
                						data-bv-notempty-message="<spring:message code="dataerror.field_required" />"
                						pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[/@/./&/!#/$%/^/*/?])(?!.*\s).{4,20}$"
                						data-bv-regexp-message="<spring:message code="dataerror.password_pattern" />"
                						data-bv-stringlength="true"
                						data-bv-stringlength-min="4"
                						data-bv-stringlength-message="<spring:message code="dataerror.minimum_4_characters" />"
                						data-bv-different="true"
                						data-bv-different-field="old_password"
                						data-bv-different-message="<spring:message code="dataerror.password_same" />"   
                						data-toggle="tooltip" 
										data-placement="top"
										title="<spring:message code="placeholder.new_password" />" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label" for="confirm_password">
									<spring:message code="label.confirm_password" />
								</label>
								<div class="col-md-6">
									<input type="password" class="form-control" id="confirm_password" name="confirm_password"
										placeholder="<spring:message code="placeholder.confirm_password"/>" 
										data-bv-notempty="true"
                						data-bv-notempty-message="<spring:message code="dataerror.field_required" />"
										data-bv-identical="true"
                						data-bv-identical-field="new_password"
               			 				data-bv-identical-message="<spring:message code="dataerror.passwords_do_not_match" />" 
               			 				data-toggle="tooltip" 
										data-placement="top"
										title="<spring:message code="placeholder.confirm_password" />" />
								</div>
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

<script src="//blueimp.github.io/JavaScript-Load-Image/js/load-image.all.min.js"></script>
<script src="//blueimp.github.io/JavaScript-Canvas-to-Blob/js/canvas-to-blob.min.js"></script>
<script src="<c:url value="/resources/js/vendor/jquery.ui.widget.js" />" ></script>
<script src="<c:url value="/resources/js/jquery.iframe-transport.js" />" ></script>
<script src="<c:url value="/resources/js/jquery.fileupload.js" />" ></script>
<script src="<c:url value="/resources/js/jquery.fileupload-process.js" />"></script>
<script src="<c:url value="/resources/js/jquery.fileupload-image.js" />" ></script>
<script src="<c:url value="/resources/js/jquery.fileupload-validate.js" />" ></script>
<script src="<c:url value="/resources/js/profile.js" />" ></script>