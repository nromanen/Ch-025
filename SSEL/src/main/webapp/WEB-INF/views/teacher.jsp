<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>

<div class="row">
<div class="col-lg-12">
	<h1 class="page-header">Teacher subjects</h1>
</div>
</div>

<div class="row">
<div class="panel panel-default">


	<div class="table-responsive">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Id</th>
					<th>Subject name</th>
					<th>Subject category</th>
					<th>Start date</th>
					<th>End date</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${schedulerList}" var="scheduler">

					<tr class="odd gradeA">
						<td>${scheduler.subject.id}</td>
						<td><a href="teacherCourse?subjectId=${scheduler.subject.id}">${scheduler.subject.name}</a></td>

						<td>${scheduler.subject.category.name}</td>
						<td><fmt:formatDate pattern='dd-MM-yyyy' value="${scheduler.start}" /></td>
						<td><fmt:formatDate pattern='dd-MM-yyyy' value="${scheduler.end}" /></td>
						<td>
							<button type="button" class="btn btn-outline btn-primary btn-xs"
								onclick="location.href='deleteSubject?subjectId=${scheduler.subject.id}'">Delete</button>
						</td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
	</div>

	<br>
	<button type="button" class="btn btn-outline btn-primary btn-xs"
		onclick="location.href='editCategory'">Add new category</button>
	<button type="button" class="btn btn-outline btn-primary btn-xs"
		onclick="location.href='editSubject'">Add new course</button>
	<br>
	<!-- .panel-body -->
</div>
<!-- /.panel -->
</div>


