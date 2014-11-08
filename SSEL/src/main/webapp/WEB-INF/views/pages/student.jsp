<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">${title}</h1>
	</div>
</div>
<div class="panel panel-default">
	<div class="panel-body">
		<div class="table-responsive">
			<table class="table table-striped table-bordered table-hover"
				id="dataTables-example">
				<thead>
					<tr>
						<td>Subject name</td>
						<td>Start time</td>
						<td>End time</td>
						<c:if test="${table ne 'future'}">
							<td>Current rating</td>
							<td>Current progress</td>
						</c:if>
					</tr>
				</thead>
				<tbody>
					<c:set var="index" value="0"/>
					<c:forEach items="${courses}" var="course">
							<tr>
									<td>
									<c:choose>	
										<c:when test="${table eq 'active'}">
											<a href="modules?courseId=${course.subject.id}">${course.subject.name}</a>
										</c:when>
										<c:when test="${table eq 'finished'}">
											<a href="ratings?courseId=${course.subject.id}">${course.subject.name}</a>
										</c:when>
										<c:otherwise>
											<a href="courseInformation?subjectId=${course.subject.id}">${course.subject.name}
											</a>
										</c:otherwise>
									</c:choose>
									</td>
									<td>
										<fmt:formatDate pattern='dd-MM-yyyy' value='${course.start}' />
									</td>
									<td>
										<fmt:formatDate pattern='dd-MM-yyyy' value='${course.end}' />
									</td>
								<c:if test="${table ne 'future'}">
									<td>
										${ratings.get(index)}
									</td>
									<td>
										${progreses.get(index)}
									</td>
								<c:set var="index" value="${index+1}"/>
							</c:if> 
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>