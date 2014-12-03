<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
	<h2>${test.name}</h2>
	
	<br>
	<div class="panel panel-default">
			<ul>
			<c:forEach items="${questions}" var="question">
				<li>
					<a href="editQuestion?testId=${question.test.id}&questionId=${question.id}">${question.question}</a>
				</li>
			</c:forEach>
			</ul>
		<div>
		
		</div>
	</div>
</div>