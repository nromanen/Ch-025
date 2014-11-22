<%@page import="org.springframework.ui.Model"
	import="com.softserve.entity.Log"%>
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script src="resources/datepicker/js/locales/bootstrap-datepicker.ru.js"></script>
<script src="resources/datepicker/js/locales/bootstrap-datepicker.ua.js"></script>

<div class="row">
	<script type="text/javascript">
		function deleteFunction() {
			var deleteDate = document.getElementById("deleteDate").value;
			window.location.href = "deleteOldLogs?deleteDate=" + deleteDate;
		}
	</script>
	
	<c:set value="${pageContext.response.locale}" var="local" />
	
	<!-- Modal window -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only"><spring:message
								code="label.close" /></span>
					</button>
					<h4 class="modal-title" align="center">
						<spring:message code="label.are_you_sure" />
					</h4>
				</div>
				<div class="modal-footer">
					<p align="center">
						<button type="button" id="mybtn" id="mybtn"
							class="btn btn-primary" onclick="deleteFunction()">
							<spring:message code="label.delete" />
						</button>
						<button type="button" class="btn btn-default" onClick=""
							data-dismiss="modal">
							<spring:message code="label.cancel" />
						</button>
					</p>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<!-- Head -->
	<div class="col-lg-12">
		<h1 align="center">
			<spring:message code="label.logs" />
		</h1>
		<h3 align="center">
			<spring:message code="label.since" />
			<fmt:formatDate pattern="dd-MM-yyyy" value="${startPeriod}" />
			<spring:message code="label.to" />
			<fmt:formatDate pattern="dd-MM-yyyy" value="${endPeriod}" />
		</h3>
		<div class="row">

			<!-- Selecting range of dates for viewing logs -->
			<div class="col-md-7" align="center">
				<form action="getRangeOfDates" method="get">
					<spring:message code="label.show_logs_from_date" />
					<input type="text" name="startDate" class="datepicker"
						placeholder="<spring:message code="label.placeholder" />"
						readonly="readonly"  
						data-date-language="${local}" 
						data-date-autoclose="true" />
					<spring:message code="label.to" />
					<input type="text" name="endDate" class="datepicker"
						placeholder="<spring:message code="label.placeholder" />"
						readonly="readonly" /> <input type="submit"
						class="btn btn-primary"
						value="<spring:message code="label.show" />" 
						data-date-language="${local}" 
						data-date-autoclose="true" />
				</form>
			</div>

			<!-- Selecting date for deleting old logs -->
			<div class="col-md-5" align="center">
				<spring:message code="label.delete_old_logs_to_date" />
				<input type="text" name="deleteDate" class="datepicker"
					placeholder="<spring:message code="label.placeholder" />"
					readonly="readonly" id="deleteDate" 
					data-date-language="${local}" 
					data-date-autoclose="true"/>
				<button type="button" class="btn btn-danger" data-toggle="modal"
					data-target="#myModal">
					<spring:message code="label.l_delete" />
				</button>
			</div>
		</div>
		
		<div class="row" align="left">
		
		<!-- Used for visual improving -->
			<div class="col-md-2" align="left">
				<div class="col-md-12" align="left">
					<br /> <br />
				</div>
				
				<!-- Picking logsPerPage parameter -->
				<div class="col-md-12" align="left">
					<form method="get" action="getParameters">
						<spring:message code="label.records_per_page" />
						<select name="logsPerPage" onchange="this.form.submit()">
							<c:choose>
								<c:when test="${empty logsPerPage}">
									<option selected value="10">10</option>
								</c:when>
								<c:otherwise>
									<option selected value="${logsPerPage}">${logsPerPage}</option>
								</c:otherwise>
							</c:choose>
							<c:if test="${(logsPerPage != 10) or (empty logsPerPage)}">
								<option value="10">10</option>
							</c:if>
							<c:if test="${logsPerPage != 25}">
								<option value="25">25</option>
							</c:if>
							<c:if test="${logsPerPage != 50}">
								<option value="50">50</option>
							</c:if>
						</select>
					</form>
				</div>
			</div>
			
			<!-- Pagination scroll -->
			<div class="col-md-10" align="right">
				<nav>
					<ul class="pagination">
						<c:if test="${pageNumb < 1}">
							<li class="disabled"><a href="#"><spring:message
										code="label.previous" /></a></li>
						</c:if>
						<c:if test="${pageNumb > 0}">
							<li><a
								href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb - 1}"><spring:message
										code="label.previous" /></a></li>
							<li><a
								href="${pageContext.request.contextPath}/viewLogs?pageNumb=0">1</a></li>
						</c:if>
						<c:if test="${pageNumb > 3}">
							<li><a href="#">...</a></li>
						</c:if>
						<c:if test="${pageNumb > 2}">
							<li><a
								href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb - 2}">${pageNumb - 1}</a></li>
						</c:if>
						<c:if test="${pageNumb > 1}">
							<li><a
								href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb - 1}">${pageNumb}</a></li>
						</c:if>
						<li class="active"><a href="#">${pageNumb + 1}</a></li>
						<c:if test="${pageNumb < (numberOfPages - 2)}">
							<li><a
								href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb + 1}">${pageNumb + 2}</a></li>
						</c:if>
						<c:if test="${pageNumb < (numberOfPages - 3)}">
							<li><a
								href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb + 2}">${pageNumb + 3}</a></li>
						</c:if>
						<c:if test="${pageNumb < (numberOfPages - 4)}">
							<li><a href="#">...</a></li>
						</c:if>
						<c:if test="${pageNumb < (numberOfPages - 1)}">
							<li><a
								href="${pageContext.request.contextPath}/viewLogs?pageNumb=${numberOfPages-1}">${numberOfPages}</a></li>
							<li><a
								href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb + 1}"><spring:message
										code="label.next" /></a></li>
						</c:if>
						<c:if test="${pageNumb > (numberOfPages - 2)}">
							<li class="disabled"><a href="#"><spring:message
										code="label.next" /></a></li>
						</c:if>
					</ul>
				</nav>
			</div>
		</div>
	</div>
	<c:choose>
		<c:when test="${empty logs}">
			<h1 align="center"><spring:message code="label.no_data" /></h1>
		</c:when>
		<c:otherwise>
		
			<!-- Table with logs -->
			<table
				class="table table-striped table-bordered table-hover centerTh">
				<thead>
					<tr class="info">
						<!-- Head of table also includes sorting parameters -->
						<th>
						<!-- Soting logic and icons -->
							<table class="col-md-12 centerTh">
								<thead>
									<tr>
										<th class="col-md-10"><spring:message code="label.t_date" /></th>
										<th class="col-md-2"><c:choose>
												<c:when test="${orderBy eq 'eventDate ASC'}">
													<a
														href="${pageContext.request.contextPath}/getParameters?orderBy=date-desc"><span
														class="fa fa-sort-numeric-asc fa-lg"></span></a>
												</c:when>
												<c:when test="${orderBy eq 'eventDate DESC'}">
													<a
														href="${pageContext.request.contextPath}/getParameters?orderBy=date-asc"><span
														class="fa fa-sort-numeric-desc fa-lg"></span></a>
												</c:when>
												<c:otherwise>
													<a
														href="${pageContext.request.contextPath}/getParameters?orderBy=date-asc"><span
														class="fa fa-sort fa-lg"></span></a>
												</c:otherwise>
											</c:choose></th>
									</tr>
								</thead>
							</table>
						</th>
						<th>
							<!-- Soting logic and icons -->
							<table class="col-md-12 centerTh">
								<thead>
									<tr>
										<th class="col-md-10"><spring:message
												code="label.t_level" /></th>
										<th class="col-md-2"><c:choose>
												<c:when test="${orderBy eq 'level ASC'}">
													<a
														href="${pageContext.request.contextPath}/getParameters?orderBy=level-desc"><span
														class="fa fa-sort-alpha-asc fa-lg"></span></a>
												</c:when>
												<c:when test="${orderBy eq 'level DESC'}">
													<a
														href="${pageContext.request.contextPath}/getParameters?orderBy=level-asc"><span
														class="fa fa-sort-alpha-desc fa-lg"></span></a>
												</c:when>
												<c:otherwise>
													<a
														href="${pageContext.request.contextPath}/getParameters?orderBy=level-asc"><span
														class="fa fa-sort fa-lg"></span></a>
												</c:otherwise>
											</c:choose></th>
									</tr>
								</thead>
							</table>
						</th>
						<th>
						<!-- Soting logic and icons -->
							<table class="col-md-12 centerTh">
								<thead>
									<tr>
										<th class="col-md-12"><spring:message
												code="label.t_logger" /></th>
									</tr>
								</thead>
							</table>
						</th>
						<th>
							<table class="col-md-12 centerTh">
								<thead>
									<tr>
										<th class="col-md-12"><spring:message
												code="label.t_message" /></th>
									</tr>
								</thead>
							</table>
						<th>
						<!-- Soting logic and icons -->
							<table class="col-md-12 centerTh">
								<thead>
									<tr>
										<th class="col-md-10"><spring:message
												code="label.t_exception" /></th>
										<th class="col-md-2"><c:choose>
												<c:when test="${orderBy eq 'exception ASC'}">
													<a
														href="${pageContext.request.contextPath}/getParameters?orderBy=exception-desc"><span
														class="fa fa-sort-alpha-desc fa-lg"></span></a>
												</c:when>
												<c:when test="${orderBy eq 'exception DESC'}">
													<a
														href="${pageContext.request.contextPath}/getParameters?orderBy=exception-asc"><span
														class="fa fa-sort-alpha-asc fa-lg"></span></a>
												</c:when>
												<c:otherwise>
													<a
														href="${pageContext.request.contextPath}/getParameters?orderBy=exception-asc"><span
														class="fa fa-sort fa-lg"></span></a>
												</c:otherwise>
											</c:choose></th>
									</tr>
								</thead>
							</table>
						</th>
					</tr>
				</thead>
				
				<!-- Table content -->
				<c:forEach items="${logs}" var="log">
					<tr>
						<td class="col-md-1"><fmt:formatDate
								pattern="dd-MM-yyyy HH:mm:ss" value="${log.eventDate}" /></td>
						<td class="col-md-1">${log.level}</td>
						<td class="col-md-3"><textarea class="col-md-12" rows="2"
								readonly="readonly">${log.logger}</textarea></td>
						<td class="col-md-6"><textarea class="col-md-12" rows="2"
								readonly="readonly">${log.message}</textarea></td>
						<td class="col-md-1">
							<c:choose>
								<c:when test="${not empty log.exception}">
									<a href="${pageContext.request.contextPath}/logDetails?LogId=${log.id}">Details</a>
								</c:when>
								<c:otherwise>
										No exception
								</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	
	<!-- Pagination scroll -->
	<div align="right">
		<nav>
			<ul class="pagination">
				<c:if test="${pageNumb < 1}">
					<li class="disabled"><a href="#"><spring:message
								code="label.previous" /></a></li>
				</c:if>
				<c:if test="${pageNumb > 0}">
					<li><a
						href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb - 1}"><spring:message
								code="label.previous" /></a></li>
					<li><a
						href="${pageContext.request.contextPath}/viewLogs?pageNumb=0">1</a></li>
				</c:if>
				<c:if test="${pageNumb > 3}">
					<li><a href="#">...</a></li>
				</c:if>
				<c:if test="${pageNumb > 2}">
					<li><a
						href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb - 2}">${pageNumb - 1}</a></li>
				</c:if>
				<c:if test="${pageNumb > 1}">
					<li><a
						href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb - 1}">${pageNumb}</a></li>
				</c:if>
				<li class="active"><a href="#">${pageNumb + 1}</a></li>
				<c:if test="${pageNumb < (numberOfPages - 2)}">
					<li><a
						href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb + 1}">${pageNumb + 2}</a></li>
				</c:if>
				<c:if test="${pageNumb < (numberOfPages - 3)}">
					<li><a
						href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb + 2}">${pageNumb + 3}</a></li>
				</c:if>
				<c:if test="${pageNumb < (numberOfPages - 4)}">
					<li><a href="#">...</a></li>
				</c:if>
				<c:if test="${pageNumb < (numberOfPages - 1)}">
					<li><a
						href="${pageContext.request.contextPath}/viewLogs?pageNumb=${numberOfPages-1}">${numberOfPages}</a></li>
					<li><a
						href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb + 1}"><spring:message
								code="label.next" /></a></li>
				</c:if>
				<c:if test="${pageNumb > (numberOfPages - 2)}">
					<li class="disabled"><a href="#"><spring:message
								code="label.next" /></a></li>
				</c:if>
			</ul>
		</nav>

	</div>
</div>
