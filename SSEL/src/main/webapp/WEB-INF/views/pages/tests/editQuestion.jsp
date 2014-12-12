<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
			<!--<spring:message code="label.teacher.editQuestion" /> -->
			Add question
		</h1>
	</div>
</div>
<div class="panel panel-default">
	<div class="panel-body">
	<form:form action="saveQuestion" method="POST" commandName="questionForm">
		<h3>${testName}</h3>
		<div class="form-group">
			<p>Question</p>
			<form:label path="question.question" />
			<form:input path="question.question" />
		</div>
		<div class="form-group">
		<p>Mark</p>
			<form:label path="question.mark" />
			<form:input path="question.mark" />
		</div>
		<table>
		<c:forEach items="${questionForm.answers}" var="answer" varStatus="status">
			<tr>
				<td>
				<div class="form-group">
					<p>Answer${status.index}</p>
					<form:label path="answers[${status.index}].answer" itemValue="id" itemLabel="name"/>
					<form:input path="answers[${status.index}].answer" />
					<form:checkbox path="answers[${status.index}].isRight" />
				</div>
				</td>
			</tr>
		</c:forEach>
		</table>
		<form:hidden path="testId"/>
		<form:hidden path="question.answersCount" />
		
		<input type="submit"  class="btn btn-primary btn-lg" value="Submit"/>
	</form:form>
	</div>
 </div>   