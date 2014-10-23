<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>
<html>

<tiles:insertDefinition name="indexTemplate">

	<tiles:putAttribute name="main-content">
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">SoftServe SSEL Academy</h1>
				</div>
				<div class="col-lg-12">
					<p>SoftServe SSEL Academy is better way for self-education. We
						choose the best courses for you!</p>
				</div>
				<form method="GET" action="course">
					<div class="blocks">
						<c:forEach items="${subList}" var="subj">
							<div class="inline">
								<div class="title">
									<button value="${subj.id}" name="subjectId" class="btn-link"
										style="color: #428bca;">${subj.name}</button>
								</div>
								<div>${subj.description}</div>
							</div>
						</c:forEach>
					</div>
				</form>
			</div>
		</div>
		</body>


		<!-- jQuery Version 1.11.0 -->
		<script src="resources/js/jquery-1.11.0.js"></script>

		<!-- Bootstrap Core JavaScript -->
		<script src="resources/js/bootstrap.min.js"></script>

		<!-- Metis Menu Plugin JavaScript -->
		<script src="resources/js/plugins/metisMenu/metisMenu.min.js"></script>


		<!-- Custom Theme JavaScript -->
		<script src="resources/js/sb-admin-2.js"></script>

		<!-- DataTables JavaScript -->
		<script src="resources/js/plugins/dataTables/jquery.dataTables.js"></script>
		<script src="resources/js/plugins/dataTables/dataTables.bootstrap.js"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>
</html>
