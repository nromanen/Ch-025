<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
			<spring:message code="label.teacher.subject" />
			: ${subject.name}
		</h1>
	</div>
</div>

<div class="row">
	<div class="panel panel-default">
		<div class="panel-heading">

			<button type="button" class="btn btn-outline btn-primary btn-xs"
				onclick="location.href='editSubject?subjectId=${subject.id}'">
				<spring:message code="label.teacher.edit" />
			</button>
			<button type="button" class="btn btn-outline btn-primary btn-xs"
				onclick="location.href='editBlock?subjectId=${subject.id}'">
				<spring:message code="label.teacher.addModule" />
			</button>
			<button type="button" class="btn btn-outline btn-primary btn-xs"
				onclick="location.href='editTopic?subjectId=${subject.id}'">
				<spring:message code="label.teacher.addTopic" />
			</button>
		</div>
		<!-- .panel-heading -->

		<div class="panel-body">
			<div class="panel-group" id="accordion">
				<c:forEach items="${blockList}" var="block">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#s-${subject.id}-${block.order}"><spring:message
										code="label.teacher.module" /> ${block.order}. ${block.name}
								</a> (
								<fmt:formatDate pattern='dd-MM-yyyy' value='${block.startTime}' />
								--
								<fmt:formatDate pattern='dd-MM-yyyy' value='${block.endTime}' />
								)
								<button type="button" class="btn btn-outline btn-primary btn-xs"
									style="float: right;"
									onclick="location.href='deleteBlock?blockId=${block.id}'">
									<spring:message code="label.teacher.delete" />
								</button>
								
								<button type="button" class="btn btn-outline btn-primary btn-xs"
									style="float: right;"
									onclick="location.href='editBlock?subjectId=${subject.id}&blockId=${block.id}'">
									<spring:message code="label.teacher.edit" />
								</button>



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
												<th><spring:message code="label.teacher.order" /></th>
												<th><spring:message code="label.teacher.status" /></th>
												<th><spring:message code="label.teacher.action" /></th>
											</tr>
										</thead>
										<tbody>
											<c:set var="counter" value="0" />
											<c:forEach items="${topicList}" var="topic">
												<c:if test="${block.id == topic.block.id}">
													<tr class="odd gradeA">
														<td><a
															href="editTopic?topicId=${topic.id}&subjectId=${subject.id}">${topic.name}</a></td>
														<td><c:choose>
																<c:when test="${counter == 0}">
																	<button type="button"
																		class="btn btn-outline btn-primary btn-xs"
																		onclick="location.href='changeTopicOrder?updown=down&topicId=${topic.id}&subjectId=${subject.id}'">
																		<spring:message code="label.teacher.down" />
																	</button>
																	<c:set var="counter" value="${counter + 1}" />
																	<c:set var="count" value="${fn:length(topicList)}" />

																</c:when>
																
																<c:when test="${counter == fn:length(topicList)-1}">
																	<button type="button"
																		class="btn btn-outline btn-primary btn-xs"
																		onclick="location.href='changeTopicOrder?updown=up&topicId=${topic.id}&subjectId=${subject.id}'">
																		<spring:message code="label.teacher.up" />
																	</button>
																	<c:set var="counter" value="${counter + 1}" />
																</c:when>

																<c:otherwise>

																	<button type="button"
																		class="btn btn-outline btn-primary btn-xs"
																		onclick="location.href='changeTopicOrder?updown=up&topicId=${topic.id}&subjectId=${subject.id}'">
																		<spring:message code="label.teacher.up" />
																	</button>
																	<button type="button"
																		class="btn btn-outline btn-primary btn-xs"
																		onclick="location.href='changeTopicOrder?updown=down&topicId=${topic.id}&subjectId=${subject.id}'">
																		<spring:message code="label.teacher.down" />
																	</button>

																	<c:set var="counter" value="${counter + 1}" />
																</c:otherwise>
															</c:choose> <!-- ${topic.order}-->
														<td><c:choose>
																<c:when test="${topic.alive == true}">
																	<button type="button"
																		class="btn btn-outline btn-success btn-xs"
																		onclick="location.href='enableTopic?enable=false&topicId=${topic.id}&subjectId=${subject.id}'">
																		<spring:message code="label.teacher.enable" />
																	</button>
																</c:when>
																<c:otherwise>
																	<button type="button"
																		class="btn btn-outline btn-danger btn-xs"
																		onclick="location.href='enableTopic?enable=true&topicId=${topic.id}&subjectId=${subject.id}'">
																		<spring:message code="label.teacher.disable" />
																	</button>
																</c:otherwise>
															</c:choose></td>
														<td>
															<button type="button"
																class="btn btn-outline btn-primary btn-xs"
																onclick="location.href='deleteTopic?topicId=${topic.id}&subjectId=${subject.id}'">
																<spring:message code="label.teacher.delete" />
															</button>


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

