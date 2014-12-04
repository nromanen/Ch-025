<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="row">
	<div class="panel-default">
	<h1 class="page-header">${subName}.Tests</h1>
	<form action="editTest" method="GET">
		<input type="hidden" name="subjectId" value="${param.subjectId}" />
		<button type="submit" 
		class="btn btn-outline btn-primary btn-xs">Add new test</button>
	</form>
	
		<div class="panel-body">

	<c:choose>
	<c:when test="${fn:length(testList) ne 0}">
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<td>
						Name
					</td>
					<td>
						Description
					</td>
					<td>
						Block
					</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${testList}" var="test"> 
					<tr>
						<td>
							<a href="testInfo?testId=${test.id}">${test.name}</a>
						</td>
						<td>
							${test.description}
						</td>
						<td>
							${test.block.name}
						</td>
						<td>
							<form action="deleteTest?testId=${test.id}">
								<button type="submit" class="btn btn-outline btn-primary btn-xs">
									Delete test</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
    </c:when>
    <c:otherwise>
    	<div class="alert alert-info" role="alert">
    		No test for subject
    	</div>
    </c:otherwise>
	</c:choose>
		</div>
	</div>
	</div>
</div>
