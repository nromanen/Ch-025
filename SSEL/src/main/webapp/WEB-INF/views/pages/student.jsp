<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Student cabinet</h1>
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
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${courses}" var="course">
						<tr>
							<td>
								${course.subject.name}	
							</td>
							<td>
								<fmt:formatDate pattern='dd-MM-yyyy' value='${course.start}' />
							</td>
							<!--<c:if test="${table ne 'future'}">
									<td><a href="modules?courseId=${course.subject.id}">${course.subject.name}</a>
									</td>
									<td>
										<fmt:formatDate pattern='dd-MM-yyyy' value='${course.end}' />
									</td>
									<td>
										${res.rating}
									</td>
									<td>
										${res.progress}
									</td>
							</c:if> -->
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>