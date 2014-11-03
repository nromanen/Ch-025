<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js"></script>

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
                            <i class="fa fa-bar-chart-o fa-fw"></i> Rating
                </div>
				  
					
			</div>
			<div class="col-lg-2">
				<div id="morris-donut-progress"
                            		style="width: 100; height: 100; position: float"></div>
                <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i> Progress
                </div>
				        
			</div>
		
		</div>
		 <div class="row">
			<form method="GET" action="subscribe">
					<button value="${subject.id}" name="subjectId"
							class="btn btn-warning" style="width: 200px; margin-top: 8px;">Unsubscribe
							from course</button>
					<input type="hidden" name="op" value="false">
			</form>
		</div>
			<!--  <form method="GET" action="courseInformation">
				<button value="${subject.id}" name="subjectId" class="btn btn-info"
					style="width: 200px; margin-top: 8px;">Details</button>
		</form>-->
				        <!-- /.panel-body -->
   		<input type="hidden" id="rating" value="${rating}" />
		<input type="hidden" id="progress" value="${progress}" />
     
		
		<div class = "row">
			<h2><spring:message code="label.course_material"></spring:message></h2>
			<h5>
				<a href="ratings?courseId=${courseId}" ><spring:message code="label.course_statistic" /></a>
			</h5>
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
												<th>Topic name</th>
												<th>Alive</th>
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
													<td>${topic.alive}</td>
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

<script>
var rating = document.getElementById("rating").value;
var progress = document.getElementById("progress").value;
	Morris.Donut({
	  element: 'morris-donut-rating',
	  data: [
	    {label: "Success", value: rating},
	    {label: "Failed", value: 100-rating}
	  ],
	colors: [
		'#33CC33',
		'#FF3300'
	]
	});
	
	Morris.Donut({
		  element: 'morris-donut-progress',
		  data: [
		    {label: "Success", value: progress},
		    {label: "Failed", value: 100-progress}
		  ],
		colors: [
			'#33CC33',
			'#FF3300'
		]
		});
			
</script>