<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js"></script>
<script src="resources/js/student/donouts.js"></script>
<div class="row">
	<div class="panel panel-default">
		<div class="row">
			<div class="col-lg-8">
				<h1>${subject.name}</h1>
			</div>
			  	
			<div class="col-lg-2">
				<div id="morris-donut-rating"
                            		style="width: 100; height: 100; position: float"></div>
                <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i> <spring:message code="label.student.current_rating" />
                </div>
				  
					
			</div>

			<div class="col-lg-2">
				<div id="morris-donut-progress"
                            		style="width: 100; height: 100; position: float"></div>
                <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i> <spring:message code="label.student.current_progress" />
                </div>
				        
			</div>
			<div class="col-lg-12">
			<form method="GET" action="subscribe">
					<button value="${subject.id}" name="subjectId"
							class="btn btn-warning" style="width: 200px; margin-top: 8px;">
							<spring:message code="label.unsubscribe" /></button>
					<input type="hidden" name="op" value="false">
			</form>
			<form action="ratings" method="GET">
					<button  type="submit" class="btn btn-success" <c:if test="${rating == 0}" >disabled="true"</c:if> >
					<spring:message code="label.course_statistic" />
					</button>
					<input type="hidden" name="courseId" value="${courseId}" />
			</form>
		</div>

		</div>
				        <!-- /.panel-body -->
   		<input type="hidden" id="rating" value="${rating}" />
   		<input type="hidden" id="success" value="<spring:message code="label.student.success" />" />
   		<input type="hidden" id="failed" value="<spring:message code="label.student.failed" />" />
		<input type="hidden" id="progress" value="${progress}" />
     
		
		<div class = "row">
			<div class="col-lg-12">
			<h2><spring:message code="label.course_material"></spring:message></h2>
			</div>
		</div>
		<!-- .panel-heading -->
		<div class="panel-body">
			<div class="panel-group" id="accordion">
				<c:forEach items="${blockList}" var="block">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#s-${subject.id}-${block.order}">Module
									${block.order}. ${block.name} 
									(<fmt:formatDate pattern='dd-MM-yyyy' value='${block.startTime}' /> -
									<fmt:formatDate pattern='dd-MM-yyyy' value='${block.endTime}' />)
									</a>
							</h4>
						</div>
						<div id="s-${subject.id}-${block.order}"
							class="panel-collapse collapse">
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-hover">
										<thead>
											<tr>
												<th><spring:message code="label.teacher.topicName" /></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${block.topics}" var="topic">
												<tr class="odd gradeA">
													<td>
														<c:choose>
															<c:when test="${topic.alive}">
																<a href="topicView?topicId=${topic.id}">${topic.name}</a>
															</c:when>
															<c:otherwise>
																${topic.name}
															</c:otherwise>
														</c:choose>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>

			</div>
		</div>
		<!-- .panel-body -->
	</div>
	<!-- /.panel -->
</div>
</div>
<!-- /.col-lg-12 -->
<<script>
/**
 * Donouts for rating and progress 
 */
var rating = document.getElementById("rating").value;
var progress = document.getElementById("progress").value;
var success = document.getElementById("success").value;
var failed = document.getElementById("failed").value;
var data1 = [];
var data2 = [];
var colors = [];
if(rating == 100.0) {
	data1.push({label: success, value : 100});
} else if (rating > 0.0) {
	data1.push({label: success, value : rating});
	data1.push({label: failed, value : 100 - rating});
} else {
	data1.push({label: failed, value : 100 });
}
if (progress == 100.0) {
	data2.push({label: success, value : 100});
}else if(progress > 0.0) {
	data2.push({label: success, value : progress});
	data2.push({label: failed, value : 100-progress});
} else {
	data2.push({label:failed, value: 100});
}
	colors.push('#33CC33');
	colors.push('#FF3300');
	
	Morris.Donut({
		  element: 'morris-donut-progress',
		  data: data2,
		  colors: colors
		});
	
	Morris.Donut({
	  element: 'morris-donut-rating',
	  data: data1,
	  colors: colors
	});
	</script>