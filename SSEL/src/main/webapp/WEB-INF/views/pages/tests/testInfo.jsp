<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="row">
<div class="panel panel-info">
	<div class="panel-body">
			<div class="list-group">
  		  		<h2 class="list-group-item-heading">${test.name}</h2>
    			<p class="list-group-item-text">${test.description}</p>
			</div>
		<div id="acordion" class="panel-group" style="width:60%">
				<div class="panel panel-default">
				<div class="panel-heading">
				<h2 class="panel-title">
				 <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">Test questions</a>
				</h2>
				<form action="editQuestion" method="GET">
					<button type="submit" 
						class="btn btn-outline btn-primary btn-xs">Add new question</button>
					<input type="hidden" name="testId" value="${param.testId}" />
				</form>
				</div>
				
				<div id="collapseOne" class="panel-collapse collapse">
				<div class="panel-body">
				<div class="panel panel-default">
				<c:choose>
				<c:when test="${fn:length(questions) gt 0}">
				<table class="table table-hover">
					<thead>
						<tr>
							<td>
								Question
							</td>
							<td>
								Mark
							</td>
							<td>
								Delete
							</td>
						</tr>
					</thead>
					
					<tbody class="table table-striped">
						<c:forEach items="${questions}" var="question" varStatus="status">
						<tr>
							<td>
								<a href="editQuestion?testId=${question.test.id}&questionId=${question.id}">
										${texts[status.index].value}
								</a>
							</td>
								<td>
									${question.mark}
								</td>
								<td>
									<form action="deleteQuestion?questionId=${question.id}&testId=${param.testId}" method="POST">
										<button type="submit" class="btn btn-outline btn-primary btn-xs">
											delete question</button>
									</form>
								</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				</c:when>
				<c:otherwise>
					<h1>Test has no questions</h1>
				</c:otherwise>
				</c:choose>
				</div>
				</div>
				</div>
				</div>
			</div>
			</div>
</div>
</div>
