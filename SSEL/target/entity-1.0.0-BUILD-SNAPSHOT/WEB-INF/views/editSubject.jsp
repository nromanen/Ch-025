<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>
<html>
<script src="resources/ckeditor/ckeditor.js"></script>
<tiles:insertDefinition name="teacherTemplate">

	<tiles:putAttribute name="main-content">

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Edit Subject</h1>


				</div>
				<!-- /.col-lg-12 -->
			</div>
			<div class="panel panel-default">
				<div class="panel-body">
					<!-- ololololololololololo -->

					<form action="saveSubject">
					<input type = "hidden" name = "subjectId" value = "${subject.id}">
						<div class="form-group">
							<label>Subject name</label> <input class="form-control" name="subjectName" value="${subject.name}">
							<p class="help-block">Input or edit subject name</p>
						</div>

						

						<textarea name="subjectDescription" id="subjectDescription" rows="10"
							cols="80">
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
								name="SubjectCategoryId">
								<c:forEach items="${categoryList}" var="category">
									<c:choose>
										<c:when test="${subject.category.id == category.id}">
											<option selected value="${category.id}">${category.id}. ${category.name}</option>
										</c:when>
										<c:otherwise>
											<option value="${category.id}">${category.id}. ${category.name}</option>
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
		</div>
		<!-- /#page-wrapper -->

		</div>
		<!-- /#wrapper -->



		<script src="resources/ckeditor/ckeditor.js"></script>

		<!-- jQuery Version 1.11.0 -->
		<script src="resources/js/jquery-1.11.0.js"></script>

		<!-- Bootstrap Core JavaScript -->
		<script src="resources/js/bootstrap.min.js"></script>

		<!-- Metis Menu Plugin JavaScript -->
		<script src="resources/js/plugins/metisMenu/metisMenu.min.js"></script>


		<!-- Custom Theme JavaScript -->
		<script src="resources/js/sb-admin-2.js"></script>



		</body>


	</tiles:putAttribute>
</tiles:insertDefinition>
</html>
