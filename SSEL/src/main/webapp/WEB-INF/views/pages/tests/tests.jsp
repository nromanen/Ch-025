<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="row">
	<button><a href="editTest?subjectId=${param.subjectId}">Add new test</a></button>
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
