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
		<h3 align="center"> Since ${showPeriod}</h3>
		<br />
		<div class="row">
			<form action="viewLogsByDate" method="get">
				Show logs since date: (dd.mm.yyyy)<input type="text" name="showDate" />
				<input type="submit" value="show" />
			</form>
			<form action="deleteOldLogs" method="get">
				Delete old logs to date: (dd.mm.yyyy)<input type="text"
					name="deleteDate" /> <input type="submit" value="delete" onclick="destroy()" />
			</form>
			
<script type="text/javascript"> 
function destroy() {
if (confirm ("Ви впевнені, що бажаєте видалити логи?")) 
	window.location = "${pageContext.request.contextPath}/deleteOldLogs";
	else 
		window.location = "javascript: history.go(-1)";
} 
</script>			
				
			<br />
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
								<td class="col-md-3"><textarea rows="2" cols="45">${log.logger}</textarea></td>
								<td class="col-md-5.7"><textarea rows="2" cols="100">${log.message}</textarea></td>
								<td class="col-md-1"><c:if
										test="${not empty log.exception}">
										<a href="http://localhost:8080/SSEL/logDetails?LogId=${log.id}">Details</a>
									</c:if></td>
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