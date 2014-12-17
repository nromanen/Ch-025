<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="resources/ckeditor/ckeditor.js"></script>
<script src="resources/js/validate.js"></script>

<div class="row">
	<div class="panel panel-default" style="border:none;">
		<div class="panel-body">
		<h1 class="page-header">
		<c:choose>
			<c:when test="${op}">
				<spring:message code="label.tests.add_test" />
			</c:when>
			<c:otherwise>
				<spring:message code="label.tests.test_edition" />
			</c:otherwise>
		</c:choose>
		</h1>
		<div style="margin-left:10px; border: none;">
   		<form:form method="POST" action="saveTest?op=${op}" cssClass="form-horizontal" commandName="test">
        <form:hidden path="id" />
        <div class="form-group">
        	<form:label path="name"><spring:message code="label.tests.test_name" /></form:label>
        	<form:errors path="name" cssClass="control-label" />		
        	<form:input path="name" cssClass="form-control" cssStyle="width:30%"/>
        			
        </div>
    	<div class="form-group">
    		<form:label path="block" ><spring:message code="label.teacher.module" /></form:label>
    		<form:select path="block" items="${blocks}" itemValue="id" itemLabel="name" 
    		cssClass="form-control" cssStyle="width:30%" />
    	</div>
    	<div class="form-group" style="margin-right:10px;">
        	<form:label path="description"><spring:message code="label.description" /></form:label>
        	<form:errors path="description" cssClass="alert alert-warning" cssStyle="position:float" />
        	<form:textarea id="description" path="description" rows="15" cols="80"/>
				<script>
					CKEDITOR.replace('description');
				</script>
    	</div>
     	<form:hidden path="isAlive" />
     	<form:hidden path="isDeleted" />
            <input type="submit" disabled="disabled" value="<spring:message code="label.teacher.save"/>"
             class="btn btn-primary btn-lg" style="margin-left: -15px;" id="subm"/>
		</form:form>
		</div>
		</div>
	</div>
</div>