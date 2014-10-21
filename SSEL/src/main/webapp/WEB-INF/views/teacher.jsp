<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>
<html>

<tiles:insertDefinition name="teacherTemplate">

	<tiles:putAttribute name="main-content">
<<<<<<< HEAD

=======
>>>>>>> 0e912669b17b792cb21f08f86e2fe69cd3b76b28
         <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Teacher</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
                <div class="panel panel-default">
					<div class="panel-body">
                      <!-- ololololololololololo -->
					  
					        <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                        	<th>Id</th>
                                            <th>Topic name</th>
                                            <th>Block</th>
                                            <th>Order</th>
                                            <th>Date</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach items="${topicList}" var="topic">
                                        <tr class="odd gradeA">
                                        	<td>${topic.id}</td>
                                            <td><a href="editTopic?topicId=${topic.id}">${topic.name}</a></td>
                                            <td class="center">${topic.block.order}</td>
                                            <td class="center">${topic.order}</td>
                                            <td class="center">A</td>
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.table-responsive -->
					  
					  
					  <!-- /ololololololololololo -->
                    </div>
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
    <script>
    $(document).ready(function() {
        $('#dataTables-example').dataTable();
    });
    </script>
</body>
		
		
	</tiles:putAttribute>
</tiles:insertDefinition>
</html>