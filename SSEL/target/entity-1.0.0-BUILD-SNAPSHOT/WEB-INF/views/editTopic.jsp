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
					<h1 class="page-header">Edit topic</h1>


				</div>
				<!-- /.col-lg-12 -->
			</div>
			<div class="panel panel-default">
				<div class="panel-body">
					<!-- ololololololololololo -->

					<form action="saveTopic">
					<input type = "hidden" name = "topicId" value = "${topic.id}">
						<div class="form-group">
							<label>Topic name</label> <input class="form-control" name="topicName" value="${topic.name}">
							<p class="help-block">Input or edit topic name</p>
						</div>
						
						<div class="form-group">
							<label>Topic order</label> <input class="form-control" size=10 name="topicOrder" value="${topic.order}">
							<p class="help-block">Input or edit topic order</p>
						</div>
						
						                <div class="form-group">
                                            <label>Topic enable</label>
                                            <label class="radio-inline">
                                                <input type="radio" name="topicAlive" id="optionsRadiosInline1" value="1" checked>Enable
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="topicAlive" id="optionsRadiosInline2" value="0">Disable
                                            </label>
                                        </div>
						

						<textarea name="topicContent" id="topicContent" rows="15"
							cols="80">
                ${topic.content}
             
            </textarea>
						<script>
							// Replace the <textarea id="editor1"> with a CKEditor
							// instance, using default configuration.
							CKEDITOR.replace('topicContent');
						</script>
						<br>
						<div class="form-group">
							<label>Select module</label> <select class="form-control"
								name="blockId">
								<c:forEach items="${blockList}" var="block">
									<c:choose>
										<c:when test="${topic.block.id == block.id}">
											<option selected value="${block.id}">Module	${block.order}. ${block.name}</option>
										</c:when>
										<c:otherwise>
											<option value="${block.id}">Module ${block.order}.${block.name}</option>
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
