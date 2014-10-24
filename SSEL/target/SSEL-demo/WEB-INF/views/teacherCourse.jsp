<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>

<div class="row">
<div class="col-lg-12">
	<h1 class="page-header">Subject: ${subject.name}</h1>
</div>
</div>

<div class="row">
<div class="panel panel-default">
	<div class="panel-heading">

		<button type="button" class="btn btn-outline btn-primary btn-xs"
			onclick="location.href='editSubject?subjectId=${subject.id}'">Edit</button>
		<button type="button" class="btn btn-outline btn-primary btn-xs"
			onclick="location.href='editBlock?subjectId=${subject.id}'">Add
			module</button>
		<button type="button" class="btn btn-outline btn-primary btn-xs"
			onclick="location.href='editTopic?subjectId=${subject.id}'">Add
			topic</button>
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
								${block.order}. ${block.name} </a> (<fmt:formatDate pattern='dd-MM-yyyy' value='${block.startTime}' />  --  <fmt:formatDate pattern='dd-MM-yyyy' value='${block.endTime}' />)
							<button type="button" class="btn btn-outline btn-primary btn-xs" style="float:right;"
								onclick="location.href='editBlock?subjectId=${subject.id}&blockId=${block.id}'">Edit</button>



						</h4>
					</div>
					<div id="s-${subject.id}-${block.order}"
						class="panel-collapse collapse">
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-hover">
									<thead>
										<tr>
											<th>Id</th>
											<th>Topic name</th>
											<th>Order</th>
											<th>Status</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${topicList}" var="topic">
											<c:if test="${block.id == topic.block.id}">
												<tr class="odd gradeA">
													<td>${topic.id}</td>
													<td><a
														href="editTopic?topicId=${topic.id}&subjectId=${subject.id}">${topic.name}</a></td>
													<td>
													${topic.order}
														<!-- button type="button"
															class="btn btn-outline btn-primary btn-xs"
															onclick="location.href='changeTopicOrder?updown=up&topicId=${topic.id}&subjectId=${subject.id}'">Up</button>
														<button type="button"
															class="btn btn-outline btn-primary btn-xs"
															onclick="location.href='changeTopicOrder?updown=down&topicId=${topic.id}&subjectId=${subject.id}'">Down</button-->



													</td>
													<td><c:choose>
															<c:when test="${topic.alive == true}">
																<button type="button"
																	class="btn btn-outline btn-success btn-xs"
																	onclick="location.href='enableTopic?enable=false&topicId=${topic.id}&subjectId=${subject.id}'">Enabled</button>
															</c:when>
															<c:otherwise>
																<button type="button"
																	class="btn btn-outline btn-danger btn-xs"
																	onclick="location.href='enableTopic?enable=true&topicId=${topic.id}&subjectId=${subject.id}'">Disabled</button>
															</c:otherwise>
														</c:choose></td>
													<td>
														<button type="button"
															class="btn btn-outline btn-primary btn-xs"
															onclick="location.href='deleteTopic?topicId=${topic.id}&subjectId=${subject.id}'">Delete</button>


													</td>
												</tr>
											</c:if>
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
