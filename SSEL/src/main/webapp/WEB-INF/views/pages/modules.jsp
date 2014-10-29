<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="row">
	<div class="panel panel-default">
		<div class="col-lg-12">
			<h1 class="page-header">${subject.name}</h1>
	
			<form method="GET" action="subscribe">
					<button value="${subject.id}" name="subjectId"
							class="btn btn-warning" style="width: 200px; margin-top: 8px;">Unsubscribe
							from course</button>
					<input type="hidden" name="op" value="false">
			</form>
		<!--  <form method="GET" action="courseInformation">
				<button value="${subject.id}" name="subjectId" class="btn btn-info"
					style="width: 200px; margin-top: 8px;">Details</button>
		</form>-->
		<div class = "row">
			<h2>Course material</h2>
		</div>
		<div class="row">
			<div class="progress progress-striped">
  				<div class="bar" style="width: %;"></div>
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
<!-- /.col-lg-12 -->
