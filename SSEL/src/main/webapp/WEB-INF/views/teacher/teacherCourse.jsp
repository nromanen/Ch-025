<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">${subject.name}</h1>
	</div>
</div>

<div class="row">
	<div class="panel panel-default">
		<div class="panel-heading">

			<button type="button" class="btn btn-info btn-primary btn-xs"
				onclick="location.href='editSubject?subjectId=${subject.id}'">
				<spring:message code="label.teacher.edit" />
			</button>
			<button type="button" class="btn btn-info btn-primary btn-xs"
				onclick="location.href='editBlock?subjectId=${subject.id}'">
				<spring:message code="label.teacher.addModule" />
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
									href="#s-${subject.id}-${block.order}"> ${block.name} </a> (
								<fmt:formatDate pattern='dd-MM-yyyy' value='${block.startTime}' />
								--
								<fmt:formatDate pattern='dd-MM-yyyy' value='${block.endTime}' />
								)
								<button type="button" class="btn btn-info btn-primary btn-xs"
									style="float: right;"
									onclick="location.href='deleteBlock?blockId=${block.id}&subjectId=${subject.id}'">
									<spring:message code="label.teacher.delete" />
								</button>

								<button type="button" class="btn btn-info btn-primary btn-xs"
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
									<div align=left>
										<button type="button" id="delButton1"
											class="btn btn-info btn-primary btn-xs">
											<spring:message code="label.teacher.delete" />
										</button>
										<button type="button" id="enableButton"
											class="btn btn-info btn-primary btn-xs">
											<spring:message code="label.teacher.enable" />
										</button>
										<button type="button" id="disableButton"
											class="btn btn-info btn-primary btn-xs">
											<spring:message code="label.teacher.disable" />
										</button>

										<button type="button"
											class="btn btn-info btn-primary btn-xs"
											onclick="location.href='editTopic?subjectId=${subject.id}&blockId=${block.id}'">
											<spring:message code="label.teacher.addTopic" />
										</button>
										
										<button type="button"
											class="btn btn-info btn-primary btn-xs"
											onclick="location.href='tests?blockId=${block.id}&subjectId=${param.subjectId}'">
											<spring:message code="label.test.tests" />
										</button>

									</div>
									<table class="table table-hover">
										<thead>
											<tr>
												<th><input type="checkbox" id="selectall" /></th>
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
														<td><input type="checkbox" class="case" name="case"
															value="${topic.id}" /></td>

														<td><a
															href="editTopic?topicId=${topic.id}&subjectId=${subject.id}&blockId=${block.id}">${topic.name}</a></td>
														<td><c:choose>
																<c:when test="${counter == 0}">
																	<button type="button"
																		class="btn btn-info btn-primary btn-xs"
																		onclick="location.href='changeTopicOrder?updown=down&topicId=${topic.id}&subjectId=${subject.id}'">
																		<i class="glyphicon glyphicon-arrow-down"></i>
																	</button>
																	<c:set var="counter" value="${counter + 1}" />
																	<c:set var="count" value="${fn:length(topicList)}" />

																</c:when>

																<c:when test="${counter == blockSizesArray[block.id]-1}">
																	<button type="button"
																		class="btn btn-info btn-primary btn-xs"
																		onclick="location.href='changeTopicOrder?updown=up&topicId=${topic.id}&subjectId=${subject.id}'">
																		<i class="glyphicon glyphicon-arrow-up"></i>
																	</button>
																	<c:set var="counter" value="${counter + 1}" />
																</c:when>

																<c:otherwise>

																	<button type="button"
																		class="btn btn-info btn-primary btn-xs"
																		onclick="location.href='changeTopicOrder?updown=up&topicId=${topic.id}&subjectId=${subject.id}'">
																		<i class="glyphicon glyphicon-arrow-up"></i>
																	</button>
																	<button type="button"
																		class="btn btn-info btn-primary btn-xs"
																		onclick="location.href='changeTopicOrder?updown=down&topicId=${topic.id}&subjectId=${subject.id}'">
																		<i class="glyphicon glyphicon-arrow-down"></i>
																	</button>

																	<c:set var="counter" value="${counter + 1}" />
																</c:otherwise>
															</c:choose> <!-- ${topic.order}-->
														<td><c:choose>
																<c:when test="${topic.alive == true}">
																	<button type="button"
																		class="btn btn-info btn-success btn-xs"
																		onclick="location.href='enableTopic?enable=false&topicId=${topic.id}&subjectId=${subject.id}'">
																		<spring:message code="label.teacher.enable" />
																	</button>
																</c:when>
																<c:otherwise>
																	<button type="button"
																		class="btn btn-info btn-danger btn-xs"
																		onclick="location.href='enableTopic?enable=true&topicId=${topic.id}&subjectId=${subject.id}'">
																		<spring:message code="label.teacher.disable" />
																	</button>
																</c:otherwise>
															</c:choose></td>
														<td>
															<button type="button"
																class="btn btn-info btn-primary btn-xs"
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

<script type="text/javascript">
	$(document).ready(function() {
		$("#delButton1").click(function() {

			var checkeds = $(":checkbox:checked").map(function() {
				return this.value;
			}).get();
			//alert(checkeds);
			if (confirm('Are you sure?')) {
				$.ajax({
					type : "GET",
					url : "deleteTopics",
					data : "topicIds=" + checkeds,
					success : function(msg) {
						location.reload();
					}
				});
			}
		});

		$("#enableButton").click(function() {

			var checkeds = $(":checkbox:checked").map(function() {
				return this.value;
			}).get();
			//alert(checkeds);
			if (confirm('Are you sure?')) {
				$.ajax({
					type : "GET",
					url : "enableTopics",
					data : "topicIds=" + checkeds,
					success : function(msg) {
						location.reload();
					}
				});
			}
		});

		$("#disableButton").click(function() {

			var checkeds = $(":checkbox:checked").map(function() {
				return this.value;
			}).get();
			//alert(checkeds);
			if (confirm('Are you sure?')) {
				$.ajax({
					type : "GET",
					url : "disableTopics",
					data : "topicIds=" + checkeds,
					success : function(msg) {
						location.reload();
					}
				});
			}
		});

		// add multiple select / deselect functionality
		$("#selectall").click(function() {
			$('.case').attr('checked', this.checked);
		});
		// if all checkbox are selected, check the selectall checkbox  also        
		$(".case").click(function() {
			if ($(".case").length == $(".case:checked").length) {
				$("#selectall").attr("checked", "checked");
			} else {
				$("#selectall").removeAttr("checked");
			}
		});
	});
</script>

<script type="text/javascript">
	$(document).ready(function() {

		$("#s-${subject.id}-${minBlockOrder}").collapse('show');

	});
</script>

