<%@page import="org.springframework.ui.Model"
	import="com.softserve.entity.Log" import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="row">
	<div class="col-lg-12">
		<h1 align="center">Logs, My Lord!</h1>
		<br />
		<div class="col-md-12">
				<table class="table table-bordered">
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
							<td class="col-md-0.5">${log.level}</td>
							<td class="col-md-3"><textarea rows="3" cols="45">${log.logger}</textarea></td>
							<td class="col-md-4"><textarea rows="3" cols="65">${log.message}</textarea></td>
							<td class="col-md-3"><textarea rows="3" cols="45">${log.exception}</textarea></td>
						</tr>
					</c:forEach>
				</table>
		</div>
		<br>
	</div>
</div>
