<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="row">
	<div class="panel-default">
	<div class="panel-header">
	<h1 class="page-header">${currentBlock.name}</h1>
	</div>
	<form action="editTest" method="GET">
		<button type="submit" 
		class="btn btn-outline btn-primary btn-xs"><spring:message code="label.tests.add_test" /></button>
		<input type="hidden" name="subjectId" value="${param.subjectId}" />
	</form>
	
	<div class="panel-body">

	<c:choose>
	<c:when test="${fn:length(testList) ne 0}">
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<td>
						<spring:message code="label.tests.test_name" />
					</td>
					<td>
						<spring:message code="label.tests.test_description" />
					</td>
					<td>
						<spring:message code="label.tests.test_edit" />
					</td>
					<td>
						<spring:message code="label.tests.test_delete" />
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
							<form action="editTest"
							 method="GET">
							 <input type="hidden" name="subjectId" value="${param.subjectId}" />
							 <input type="hidden" name="testId" value="${test.id}" />
								<button type="submit" class="btn btn-outline btn-primary btn-xs">
									<spring:message code="label.teacher.edit" /></button>
							</form>
						</td>
						<td>
							<form action="deleteTest?testId=${test.id}&blockId=${param.blockId}&subjectId=${param.subjectId}"
							 method="POST">
								<button type="submit" class="btn btn-outline btn-primary btn-xs">
									<spring:message code="label.teacher.delete" /></button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
    </c:when>
    <c:otherwise>
    	<div class="alert alert-info" role="alert">
    		<spring:message code="label.tests.no_test" />
    	</div>
    </c:otherwise>
	</c:choose>
		</div>
	</div>
	</div>
