<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
		<div id="page-wrapper">



			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header"><a href="student?table=active">Back to cabinet</a></h1>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						Course: ${subject.name}
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
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${topicList}" var="topic">
															<c:if test="${block.id == topic.block.id}">
																<tr class="odd gradeA">
																	<td>${topic.id}</td>
																	<td class="center">${topic.alive}</td>
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
		<!--  -->