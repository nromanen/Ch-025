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
		class="btn btn-outline btn-primary btn-xs">Add new test</button>
		<input type="hidden" name="subjectId" value="${param.subjectId}" />
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
					<td>
						Edit
					</td>
					<td>
						Delete
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
							<form action="editTest"
							 method="GET">
							 <input type="hidden" name="subjectId" value="${param.subjectId}" />
							 <input type="hidden" name="testId" value="${test.id}" />
								<button type="submit" class="btn btn-outline btn-primary btn-xs">
									edit</button>
							</form>
						</td>
						<td>
							<form action="deleteTest?testId=${test.id}&blockId=${param.blockId}&subjectId=${param.subjectId}"
							 method="POST">
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
    		No test for block
    	</div>
    </c:otherwise>
	</c:choose>
		</div>
	</div>
	</div>
