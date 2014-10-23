<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>
<html>

<tiles:insertDefinition name="teacherTemplate">

	<tiles:putAttribute name="main-content">

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Edit momdule</h1>


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
								name="blockName" value="${block.name}">
							<p class="help-block">Input or edit module name</p>
						</div>

						<div class="form-group">
							<label>Start date</label> <input name="date1" class="src_date"
								type="textarea" placeholder="DD-MM-YYYY" id="date1" required>
							<label>End date</label> <input name="date2" class="src_date"
								type="textarea" placeholder="DD-MM-YYYY" id="date2" required>
						</div>

						<br>
						<div class="form-group">
							<label>Select subject</label> <select class="form-control"
								name="blockId">
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
