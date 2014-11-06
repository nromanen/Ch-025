<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="row">
	<div class="col-lg-12">
		<c:if test="${sessionScope.user.role.role ne 'STUDENT'}">
			<div class="alert alert-danger" role="alert" align="center">
				<span><spring:message code="label.course_subscribe"/> <a
					href="login" class="btn btn-primary"><spring:message code="label.sing_in"/></a></span>
			</div>
		</c:if>
		<h2 align="center">${subject.name}</h2>
		<table class="table table-bordered">
			<tr class="warning" align="center">
				<td><spring:message code="label.category" /></td>
				<td><spring:message code="label.course_start" /></td>
				<td><spring:message code="label.cource_end" /></td>
				<td><spring:message code="label.teacher" /></td>
			</tr>
			<tr class="info" align="center">
				<td>${category.name}</td>
				<td><fmt:formatDate pattern="dd-MM-yyyy"
						value="${schedule.start}" /></td>
				<td><fmt:formatDate pattern="dd-MM-yyyy"
						value="${schedule.end}" /></td>
				<td>${subject.user.firstName}${subject.user.lastName}</td>
			</tr>
		</table>
		<div class="courceInformation">
			<h4>
				<b><spring:message code="label.course_description" /></b>
			</h4>
			<p>${subject.description}</p>
			<hr>
			<table>

				<thead>
					<h4>
						<b><spring:message code="label.course_program" /></b>
					</h4>
				</thead>
				<c:forEach items="${blocks}" var="block">
					<tr>
						<td>
							<h4>
								<b><c:out value="${block.name}" /></b>
							</h4>
						</td>
					</tr>
					<c:forEach items="${topics}" var="topic">
						<c:if test="${topic.block.id eq block.id}">
							<tr>
								<td class="topic"><c:out value="${topic.name}" /></td>
							</tr>
						</c:if>
					</c:forEach>
				</c:forEach>
			</table>
			<c:if test="${sessionScope.user.role.role eq 'STUDENT'}">
				<form method="GET" action="subscribe">
					<c:if test="${isSubscribe eq true}">
						<button value="${subject.id}" name="subjectId"
							class="btn btn-success" style="width: 200px; margin-top: 8px;">
							<spring:message code="label.subscribe" />
						</button>
					</c:if>
					<c:if test="${isSubscribe eq false}">
						<button value="${subject.id}" name="subjectId"
							class="btn btn-warning" style="width: 200px; margin-top: 8px;">
							<spring:message code="label.unsubscribe" />
						</button>
					</c:if>
					<input type="hidden" name="op" value="${isSubscribe}">
				</form>
			</c:if>
		</div>
	</div>
</div>
