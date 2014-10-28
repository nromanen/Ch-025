<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<script src="resources/ckeditor/ckeditor.js"></script>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Edit Subject</h1>


	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="row">
	<div class="panel panel-default">
		<div class="panel-body">
			<!-- ololololololololololo -->

			<form action="saveSubject">
				<input type="hidden" name="subjectId" value="${subject.id}">
				<div class="form-group">
					<label>Subject name</label> <input class="form-control"
						name="subjectName" value="${subject.name}">
					<p class="help-block">Input or edit subject name</p>
				</div>


				<label>Subject name</label>
				<textarea name="subjectDescription" id="subjectDescription"
					rows="10" cols="80">
                ${subject.description}
             
            </textarea>
				<script>
					// Replace the <textarea id="editor1"> with a CKEditor
					// instance, using default configuration.
					CKEDITOR.replace('subjectDescription');
				</script>
				<br>
				<div class="form-group">
					<label>Select category</label> <select class="form-control"
						name="subjectCategoryId">
						<c:forEach items="${catList}" var="category">
							<c:choose>
								<c:when test="${subject.category.id == category.id}">
									<option selected value="${category.id}">${category.id}.
										${category.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${category.id}">${category.id}.
										${category.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>

				<div class="form-group">
					<label>Start date</label> <input name="startDate" class="src_date"
						type="textarea" placeholder="DD-MM-YYYY"
						value="<fmt:formatDate pattern='dd-MM-yyyy' value='${scheduler.start}' />"
						id="startDate" required> <label>End date</label> <input
						name="endDate" class="src_date" type="textarea"
						placeholder="DD-MM-YYYY"
						value="<fmt:formatDate pattern='dd-MM-yyyy' value='${scheduler.end}' />"
						id="endDate" required>
				</div>

				<br> <input type="submit" class="btn btn-primary btn-lg"
					value="Save">

			</form>


			<!-- /ololololololololololo -->
		</div>
	</div>
</div>