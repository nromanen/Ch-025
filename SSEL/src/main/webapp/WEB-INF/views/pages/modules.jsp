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
		<div class="col-lg-12">
						<div class="panel-body">
						<h1>${subject.name}
							
                            	<div id="morris-donut-chart"
                            		style="width: 130; height: 130; position: float"></div>
                        
						</h1>
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
			<h2>Course material</h2>
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
	  element: 'morris-donut-chart',
	  data: [
	    {label: "Rating", value: rating},
	    {label: "Progress", value: progress}
	  ],
	colors: [
		'#3399FF',
		'#66FF66'
	]
	});
		
</script>
