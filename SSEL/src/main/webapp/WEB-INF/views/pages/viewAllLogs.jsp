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
		<h3 align="center">
			Since
			<fmt:formatDate pattern="dd-MM-yyyy" value="${startPeriod}" />
			to 
			<fmt:formatDate pattern="dd-MM-yyyy" value="${endPeriod}" />
		</h3>
		<h3>Logs in Query: ${logsInQuery}</h3>
		<h3>Logs per page: ${logsPerPage}</h3>
		<h3>Number of Pages: ${numberOfPages}</h3>
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
		</div>
		
		<form action="viewLogsByDate" method="get">
			<input type="text" name="logsPerPage">
			<input type="submit" value="show" />
		</form>

		<script>
			function getTown(selectObject) {
				document.getElementById('valuesPerPage').value.submit();
			}
		</script>
		<form method="get" action="viewLogsByDate" target="body"
			id="valuesPerPage">
			<select name="logsPerPage" onchange="this.form.submit()">
				<option selected value="${logsPerPage}" >${logsPerPage}</option> 
				<c:if test="${logsPerPage != 10}">
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
			
		<table class="table table-striped table-bordered table-hover">
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
								<a
									href="${pageContext.request.contextPath}/logDetails?LogId=${log.id}">Details</a>
							</c:when>
							<c:otherwise>
								        No exception
								    </c:otherwise>
						</c:choose></td>
				</tr>
			</c:forEach>
		</table>

		<div align="right">
				<nav>
					<ul class="pagination">
						<c:if test="${pageNumb < 1}">
							<li class="disabled"><a href="#">Previous</a></li>
						</c:if>
						<c:if test="${pageNumb > 0}">
							<li><a href="${pageContext.request.contextPath}/viewAllLogs?pageNumb=${pageNumb - 1}">Previous</a></li>
							<li><a href="${pageContext.request.contextPath}/viewAllLogs?pageNumb=0">1</a></li>
						</c:if>
						<c:if test="${pageNumb > 2}">
							<li><a href="#">...</a></li>
						</c:if>
						<c:if test="${pageNumb > 1}">
							<li><a href="${pageContext.request.contextPath}/viewAllLogs?pageNumb=${pageNumb - 1}">${pageNumb}</a></li>
						</c:if>
						
							<li class="active"><a href="#">${pageNumb + 1}</a></li>
						<c:if test="${pageNumb < (numberOfPages - 2)}">
							<li><a href="${pageContext.request.contextPath}/viewAllLogs?pageNumb=${pageNumb + 1}">${pageNumb + 2}</a></li>
						</c:if>	
						<c:if test="${pageNumb < (numberOfPages - 3)}">
							<li><a href="#">...</a></li>
						</c:if>
						<c:if test="${pageNumb < (numberOfPages - 1)}">
							<li><a href="${pageContext.request.contextPath}/viewAllLogs?pageNumb=${numberOfPages-1}">${numberOfPages}</a></li>
							<li><a href="${pageContext.request.contextPath}/viewAllLogs?pageNumb=${pageNumb + 1}">Next</a></li>
						</c:if>
						<c:if test="${pageNumb > (numberOfPages - 2)}">
							<li class="disabled"><a href="#">Next</a></li>
						</c:if>				
					</ul>
				</nav>
		</div>
	</div>
</div>
<script>
	$(document).ready(function() {
		$('#dataTables-example').dataTable();
	});
</script>