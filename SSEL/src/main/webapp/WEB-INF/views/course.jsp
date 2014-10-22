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
					<h3 class="title">
						About course
					</h3>
				<div>
					<div class="title"> ${subject.name}</div>
					<div style="padding-top:8px;"> Description: ${subject.description}</div>
					<form method="GET" action="subscribe">	
					<button value="${subject.id}" name="subjectId" class="btn-success"
					style="border-radius:5px; margin-top:8px;">Subscribe to course </button>
					</form>
					<form method="GET" action="courseInformation">	
					<button value="${subject.id}" name="subjectId" class="btn-info"
					style="border-radius:5px; margin-top:8px;"> Details </button>
					</form>
				</div>
				</div>
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
