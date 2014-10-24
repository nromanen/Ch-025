<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				<a href="teacher">Teacher</a>
			</h1>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				Course: ${subject.name}
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
										${block.order}. ${block.name}</a>
									<button type="button"
										class="btn btn-outline btn-primary btn-xs"
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
													<th>Alive</th>
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
															<td class="center">
																<button type="button"
																	class="btn btn-outline btn-primary btn-xs"
																	onclick="location.href='changeTopicOrder&updown=up&topicId=${topic.id}'">Up</button>
																<button type="button"
																	class="btn btn-outline btn-primary btn-xs"
																	onclick="location.href='changeTopicOrder&updown=down&topicId=${topic.id}'">Down</button>
															</td>
															<td class="center">${topic.alive}</td>
															<td class="center">
																<button type="button"
																	class="btn btn-outline btn-primary btn-xs"
																	onclick="location.href='enableTopic&enable=false&topicId=${topic.id}'">Enable</button>
																<button type="button"
																	class="btn btn-outline btn-primary btn-xs"
																	onclick="location.href='enableTopic&enable=false&topicId=${topic.id}'">Disable</button>
																<button type="button"
																	class="btn btn-outline btn-primary btn-xs"
																	onclick="location.href='deleteTopic&topicId=${topic.id}'">Delete</button>
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
	<!-- /.col-lg-12 -->
</div>
