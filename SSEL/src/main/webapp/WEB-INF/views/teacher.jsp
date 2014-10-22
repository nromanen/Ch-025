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
					<h1 class="page-header"><a href="teacher">Teacher</a></h1>
				</div>
				<div class="panel panel-default">
					Hi, Teacher.
					<br>
											<button type="button" class="btn btn-outline btn-primary btn-xs"
							onclick="location.href='editCategory'">Add
							new category</button>
						<button type="button" class="btn btn-outline btn-primary btn-xs"
							onclick="location.href='editSubject'">Add
							new course</button>
					<!-- .panel-body -->
				</div>
				<!-- /.panel -->
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!--  -->



		<!-- /.col-lg-12 -->
		</div>

		</div>
		<!-- /#page-wrapper -->
		<!-- /#wrapper -->

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
		<!-- script>
    $(document).ready(function() {
        $('#dataTables-example').dataTable();
    });
    </script-->
		</body>


	</tiles:putAttribute>
</tiles:insertDefinition>
</html>
