<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Edit module</h1>


	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="panel panel-default">
	<div class="panel-body">
		<!-- ololololololololololo -->

		<form action="saveBlock">
			<input type="hidden" name="blockId" value="${block.id}">
			<div class="form-group">
				<label>Module name</label> <input class="form-control"
					name="blockName" style="width:40%" value="${block.name}">
				<p class="help-block">Input or edit module name</p>
			</div>
			
			<div class="form-group">
				<label>Module order</label> <input class="form-control"
					name="blockName" style="width:5%" value="${block.order}">
				<p class="help-block">Input or edit module order</p>
			</div>

			<div class="form-group">
				<label>Start date</label> <input name="startDate" class="src_date"
					type="textarea" placeholder="DD-MM-YYYY"  value="<fmt:formatDate pattern='dd-MM-yyyy' value='${block.startTime}' />" id="startDate" required>
				<label>End date</label> <input name="endDate" class="src_date"
					type="textarea" placeholder="DD-MM-YYYY" value="<fmt:formatDate pattern='dd-MM-yyyy' value='${block.endTime}' />" id="endDate"  required>
			</div>

			<br>
			<div class="form-group">
				<label>Select subject</label> <select class="form-control"
					name="subjectId">
					<c:forEach items="${subjectList}" var="subject">
						<c:choose>
							<c:when test="${block.subject.id == subject.id}">
								<option selected value="${subject.id}">${subject.id}.
									${subject.name}</option>
							</c:when>
							<c:otherwise>
								<option value="${subject.id}">${subject.id}.
									${subject.name}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>

			<br> <input type="submit" class="btn btn-primary btn-lg"
				value="Save">

		</form>


		<!-- /ololololololololololo -->
	</div>
</div>

