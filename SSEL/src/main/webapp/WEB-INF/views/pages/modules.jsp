<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="row">
	<div class="panel panel-default">
		<div class="col-lg-12">
	<h1 class="page-header">Course: ${subject.name}. Rating: ${rating}. Progress: ${progress}%</h1>
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
