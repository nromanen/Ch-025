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
<<<<<<< HEAD:SSEL/target/SSEL-1.0/WEB-INF/views/index.jsp
<<<<<<< HEAD
<<<<<<< HEAD:SSEL/target/entity-1.0.0-BUILD-SNAPSHOT/WEB-INF/views/index.jsp
<<<<<<< HEAD
=======
>>>>>>> f189848190fe3855ca1b5ea8493246b0f6197e34
=======
>>>>>>> 9a5e1cfab97e60d7096ed59cc61c78600f5bf6b6:SSEL/target/SSEL-1.0/WEB-INF/views/index.jsp
=======
>>>>>>> anatoliy:SSEL/target/entity-1.0.0-BUILD-SNAPSHOT/WEB-INF/views/index.jsp
				<div class="col-lg-12">
					<h1 class="page-header">SoftServe SSEL Academy</h1>
				</div>
				<div class="col-lg-12">
					<p>SoftServe SSEL Academy is better way for self-education. We
						choose the best courses for you!</p>
				</div>
<<<<<<< HEAD:SSEL/target/SSEL-1.0/WEB-INF/views/index.jsp
<<<<<<< HEAD:SSEL/target/entity-1.0.0-BUILD-SNAPSHOT/WEB-INF/views/index.jsp
<<<<<<< HEAD
=======
>>>>>>> 9a5e1cfab97e60d7096ed59cc61c78600f5bf6b6:SSEL/target/SSEL-1.0/WEB-INF/views/index.jsp
				<div class="blocks">
					<c:forEach items="${subList}" var="subj">
						<div class="inline">
							<div class="title">${subj.name}</div>
							<div>${subj.description }</div>
						</div>
					</c:forEach>
				</div>
<<<<<<< HEAD:SSEL/target/entity-1.0.0-BUILD-SNAPSHOT/WEB-INF/views/index.jsp
=======
=======
>>>>>>> anatoliy:SSEL/target/entity-1.0.0-BUILD-SNAPSHOT/WEB-INF/views/index.jsp
				<form method="GET" action="/entity/course">
					<div class="blocks">
						<c:forEach items="${subList}" var="subj">
							<div class="inline">
								<div class="title">
									<button value="${subj.id}" name="courseId" class="btn-link"
										style="color: #428bca;">${subj.name}</button>
								</div>
								<div>${subj.description}</div>
							</div>
						</c:forEach>
					</div>
				</form>
<<<<<<< HEAD:SSEL/target/SSEL-1.0/WEB-INF/views/index.jsp
>>>>>>> f189848190fe3855ca1b5ea8493246b0f6197e34
=======
>>>>>>> 9a5e1cfab97e60d7096ed59cc61c78600f5bf6b6:SSEL/target/SSEL-1.0/WEB-INF/views/index.jsp
=======
>>>>>>> anatoliy:SSEL/target/entity-1.0.0-BUILD-SNAPSHOT/WEB-INF/views/index.jsp
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
<<<<<<< HEAD:SSEL/target/SSEL-1.0/WEB-INF/views/index.jsp
<<<<<<< HEAD:SSEL/target/entity-1.0.0-BUILD-SNAPSHOT/WEB-INF/views/index.jsp
<<<<<<< HEAD
=======
>>>>>>> 9a5e1cfab97e60d7096ed59cc61c78600f5bf6b6:SSEL/target/SSEL-1.0/WEB-INF/views/index.jsp


=======
				<h1 class="page-header">Description</h1>
			</div>
		</div>
>>>>>>> 43adb0790b125c7b3893adecc6e6bf88b241421f
<<<<<<< HEAD:SSEL/target/entity-1.0.0-BUILD-SNAPSHOT/WEB-INF/views/index.jsp
=======
		<h1 class="page-header">Description</h1>
>>>>>>> f189848190fe3855ca1b5ea8493246b0f6197e34
=======
>>>>>>> 9a5e1cfab97e60d7096ed59cc61c78600f5bf6b6:SSEL/target/SSEL-1.0/WEB-INF/views/index.jsp
=======
		<h1 class="page-header">Description</h1>
>>>>>>> anatoliy:SSEL/target/entity-1.0.0-BUILD-SNAPSHOT/WEB-INF/views/index.jsp
	</tiles:putAttribute>
</tiles:insertDefinition>
</html>
