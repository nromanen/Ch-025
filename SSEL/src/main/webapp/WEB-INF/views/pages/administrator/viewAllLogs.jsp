<%@page import="org.springframework.ui.Model"
	import="com.softserve.entity.Log"%>
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="row">
	<div class="col-lg-12">
		<h1 align="center"><spring:message code="label.logs" /></h1>
		<h3 align="center">
			<spring:message code="label.since" />
			<fmt:formatDate pattern="dd-MM-yyyy" value="${startPeriod}" />
			<spring:message code="label.to" />
			<fmt:formatDate pattern="dd-MM-yyyy" value="${endPeriod}" />
		</h3>
		<div class="row">
		
			<!-- Picking logsPerPage parameter -->
			<div class="col-md-1" align="left">
				<form method="get" action="getParameters">
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
					</select> <input type="submit" style="visibility: hidden;">
				</form>
			</div>
			
			<!-- Selecting range of dates for viewing logs -->
			<div class="col-md-5" align="left">
				<form action="getRangeOfDates" method="get">
					<spring:message code="label.show_logs_from_date" /> <input type="text" name="startDate"
						placeholder="<spring:message code="label.placeholder" />" /> 
						<spring:message code="label.to" /> <input type="text" name="endDate"
						placeholder="<spring:message code="label.placeholder" />" /> 
						<input type="submit" value="<spring:message code="label.show" />" />
				</form>
			</div>
				
			<!-- Selecting date for deleting old logs -->
			<div class="col-md-4" align="left">
				<form action="deleteOldLogs" method="get">
					<spring:message code="label.delete_old_logs_to_date" /> <input type="text" name="deleteDate"
						placeholder="<spring:message code="label.placeholder" />" /> 
						<input type="submit" value="<spring:message code="label.delete" />" onclick="return confirm('<spring:message code="label.are_you_sure" />')" />
				</form>
			</div>
			
		<!-- Pagination scroll -->
		<div align="right">
			<nav>
				<ul class="pagination">
					<c:if test="${pageNumb < 1}">
						<li class="disabled"><a href="#"><spring:message code="label.previous" /></a></li>
					</c:if>
					<c:if test="${pageNumb > 0}">
						<li><a href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb - 1}"><spring:message code="label.previous" /></a></li>
						<li><a href="${pageContext.request.contextPath}/viewLogs?pageNumb=0">1</a></li>
					</c:if>
					<c:if test="${pageNumb > 2}">
						<li><a href="#">...</a></li>
					</c:if>
					<c:if test="${pageNumb > 1}">
						<li><a href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb - 1}">${pageNumb}</a></li>
					</c:if>
					<li class="active"><a href="#">${pageNumb + 1}</a></li>
					<c:if test="${pageNumb < (numberOfPages - 2)}">
						<li><a href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb + 1}">${pageNumb + 2}</a></li>
					</c:if>
					<c:if test="${pageNumb < (numberOfPages - 3)}">
						<li><a href="#">...</a></li>
					</c:if>
					<c:if test="${pageNumb < (numberOfPages - 1)}">
						<li><a href="${pageContext.request.contextPath}/viewLogs?pageNumb=${numberOfPages-1}">${numberOfPages}</a></li>
						<li><a href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb + 1}"><spring:message code="label.next" /></a></li>
					</c:if>
					<c:if test="${pageNumb > (numberOfPages - 2)}">
						<li class="disabled"><a href="#"><spring:message code="label.next" /></a></li>
					</c:if>
				</ul>
			</nav>
		</div>
	</div>

		<!-- Table with logs -->
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<!-- Head of table also includes sorting parameters -->
					<th><spring:message code="label.t_date" /><br /> <font size="-2"> 
						<a href="${pageContext.request.contextPath}/getParameters?orderBy=date-asc">Up</a>
						<a href="${pageContext.request.contextPath}/getParameters?orderBy=date-desc">Down</a>
					</font></th>
					<th><spring:message code="label.t_level" /><br /> <font size="-2"> 
						<a href="${pageContext.request.contextPath}/getParameters?orderBy=level-asc">Up</a>
						<a href="${pageContext.request.contextPath}/getParameters?orderBy=level-desc">Down</a>
					</font></th>
					<th><spring:message code="label.t_logger" /><br /></th>
					<th><spring:message code="label.t_message" /><br /></th>
					<th><spring:message code="label.t_exception" /><br /> <font size="-2"> 
						<a href="${pageContext.request.contextPath}/getParameters?orderBy=exception-asc">Up</a>
						<a href="${pageContext.request.contextPath}/getParameters?orderBy=exception-desc">Down</a>
					</font></th>
				</tr>
			</thead>
			<c:forEach items="${logs}" var="log">
				<tr>
					<td class="col-md-1">${log.eventDate}</td>
					<td class="col-md-0.8">${log.level}</td>
					<td class="col-md-3"><textarea rows="2" cols="45"
							readonly="readonly">${log.logger}</textarea></td>
					<td class="col-md-5.7"><textarea rows="2" cols="100"
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
		
		<!-- Pagination scroll -->
		<div align="right">
			<nav>
				<ul class="pagination">
					<c:if test="${pageNumb < 1}">
						<li class="disabled"><a href="#"><spring:message code="label.previous" /></a></li>
					</c:if>
					<c:if test="${pageNumb > 0}">
						<li><a href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb - 1}"><spring:message code="label.previous" /></a></li>
						<li><a href="${pageContext.request.contextPath}/viewLogs?pageNumb=0">1</a></li>
					</c:if>
					<c:if test="${pageNumb > 2}">
						<li><a href="#">...</a></li>
					</c:if>
					<c:if test="${pageNumb > 1}">
						<li><a href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb - 1}">${pageNumb}</a></li>
					</c:if>
					<li class="active"><a href="#">${pageNumb + 1}</a></li>
					<c:if test="${pageNumb < (numberOfPages - 2)}">
						<li><a href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb + 1}">${pageNumb + 2}</a></li>
					</c:if>
					<c:if test="${pageNumb < (numberOfPages - 3)}">
						<li><a href="#">...</a></li>
					</c:if>
					<c:if test="${pageNumb < (numberOfPages - 1)}">
						<li><a href="${pageContext.request.contextPath}/viewLogs?pageNumb=${numberOfPages-1}">${numberOfPages}</a></li>
						<li><a href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb + 1}"><spring:message code="label.next" /></a></li>
					</c:if>
					<c:if test="${pageNumb > (numberOfPages - 2)}">
						<li class="disabled"><a href="#"><spring:message code="label.next" /></a></li>
					</c:if>
				</ul>
			</nav>
		</div>
	</div>
</div>