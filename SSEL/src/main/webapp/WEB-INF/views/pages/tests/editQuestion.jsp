<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="resources/ckeditor/ckeditor.js"></script>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
			<spring:message code="label.test.add_question" /> 
		</h1>
	</div>
</div>
<div class="panel panel-default">
	<div class="panel-body">
	<form:form action="saveQuestion?op=${op}" method="POST" commandName="questionForm">
		<div class="page-header">
			<h3>${testName}</h3>
		</div>
		<div class="form-group">
        	<form:label path="question.question"><h4><spring:message code="label.test.question" /> </h4></form:label>
        	<form:errors path="question.question" cssClass="alert alert-warning" cssStyle="position:float" />
        	<form:textarea id="question" path="question.question" rows="15" cols="80"/>
				<script>
					CKEDITOR.replace('question');
				</script>
    	</div>
     	<div class="form-group">
		
			<form:label path="question.mark" ><h4><spring:message code="label.test.mark" /> </h4></form:label>
			<form:input path="question.mark" cssClass="form-control" style="width:30%" />
		</div>
		<div class="list-group">
		<h3><spring:message code="label.test.answers" /> </h3>
		<c:forEach items="${questionForm.answers}" var="answer" varStatus="status">
				<div class="list-group-item" style="width:40%">
					<h4 class="list-group-item-heading" >Answer${status.index}</h4>
					<div class="list-group-item-text">
						<form:label path="answers[${status.index}].answer" itemValue="id" itemLabel="name"/>
						<div class="input-group" >
						<form:input path="answers[${status.index}].answer" cssClass="form-control" />
						<span class="input-group-addon" style="horizontal-align:left">	
							<form:checkbox path="answers[${status.index}].isRight" />
						</span>
						
						</div>
					</div>
				</div>
		</c:forEach>
		</div>
		<form:hidden path="testId"/>
		<form:hidden path="question.answersCount" />
		
		<input type="submit"  class="btn btn-primary btn-lg" value="Submit"/>
	</form:form>
	</div>
 </div>   