<%@page import="org.springframework.ui.Model"
	import="com.softserve.entity.Log" import="java.util.List"
	import="java.util.Date"%>
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="row">
	<div class="col-lg-12">
		<h1 align="center">Logs, My Lord!</h1>
		<h3 align="center">Since
			<c:choose>
				<c:when test="${not empty startPeriod}">
					<fmt:formatDate pattern="dd-MM-yyyy" value="${startPeriod}" />
					to 
					<fmt:formatDate pattern="dd-MM-yyyy" value="${endPeriod}" />
				</c:when>
				<c:otherwise>
					last 24 hours.
				 </c:otherwise>
			</c:choose></h3>
		<div class="row">
			<div class="col-md-5" align="center">
				<form action="viewLogsByDate" method="get">
					Show logs from date: <input type="text" name="startDate"
						placeholder="DD-MM-YYYY" /> to: <input type="text" name="endDate"
						placeholder="DD-MM-YYYY" /> <input type="submit" value="show" />
				</form>
			</div>
			<div class="col-md-5" align="center">
				<form action="deleteOldLogs" method="get">
					Delete old logs to date: <input type="text" name="deleteDate"
						placeholder="DD-MM-YYYY" /> <input type="submit" value="delete" />
				</form>
			</div>

			<div class="col-md-2">
				<c:if
					test="${((pageNumb == 0) and (logs.size() == 50)) or pageNumb > 0}">
			Your request includes lot of information.
			You can browse<br /> through it here:
				<nav>
						<ul class="pagination">
							<c:if test="${pageNumb > 0}">
								<li><a href="${pageContext.request.contextPath}/viewAllLogs?pageNumb=${pageNumb - 1}">Previous</a></li>
								<li><a href="${pageContext.request.contextPath}/viewAllLogs?pageNumb=${pageNumb - 1}">${pageNumb}</a></li>
							</c:if>
							<li class="active"><a href="#">${pageNumb + 1}</a></li>
							<c:if test="${logs.size() == 50}">
								<li><a href="${pageContext.request.contextPath}/viewAllLogs?pageNumb=${pageNumb + 1}">${pageNumb + 2}</a></li>
								<li><a href="${pageContext.request.contextPath}/viewAllLogs?pageNumb=${pageNumb + 1}">Next</a></li>
							</c:if>
						</ul>
					</nav>
				</c:if>
			</div>
		</div>

		<div class="panel panel-default">
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover"
						id="dataTables-example">

						<thead>
							<tr>
								<th>Date</th>
								<th>Level</th>
								<th>Logger</th>
								<th>Message</th>
								<th>Exception</th>
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
								<td class="col-md-1"><c:choose>
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
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	$(document).ready(function() {
		$('#dataTables-example').dataTable();
	});
</script>