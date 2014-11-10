<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">SoftServe SSEL Academy</h1>
	</div>
	<div class="col-lg-12">
		<h3 class="title"><spring:message code="label.about_course" /></h3>
		<div>
			<div class="title">${subject.name}</div>
			<div style="padding-top: 8px;"><spring:message code="label.description" />
				${subject.description}</div>
			<c:set var="now" value="<%=new java.util.Date()%>" />
			<c:if test="${sessionScope.user.role.role eq 'STUDENT'}">
				<c:if test="${schedule.start >= now}">
				<form method="GET" action="subscribe">
					<c:if test="${isSubscribe eq true}">
						<button value="${subject.id}" name="subjectId"
							class="btn btn-success" style="width: 200px; margin-top: 8px;">
							<spring:message code="label.subscribe" /></button>
					</c:if>
					<c:if test="${isSubscribe eq false}">
						<button value="${subject.id}" name="subjectId"
							class="btn btn-warning" style="width: 200px; margin-top: 8px;">
							<spring:message code="label.unsubscribe" /></button>
					</c:if>
					<input type="hidden" name="op" value="${isSubscribe}">
					<c:if test="${isSubscribed eq true}">
						<p> <spring:message code="label.subscribed"/> </p>
					</c:if>
					<c:if test="${isSubscribed eq false}">
						<p> <spring:message code="label.unsubscribed"/> </p>
					</c:if>
				</form>
				</c:if>
				<c:if test="${schedule.start < now }">
					<p><spring:message code="label.time_is_out" /></p>
				</c:if>
			</c:if>
			<form method="GET" action="courseInformation">
				<button value="${subject.id}" name="subjectId" class="btn btn-info"
					style="width: 200px; margin-top: 8px;"><spring:message code="label.details" />
					</button>
			</form>
		</div>
	</div>
</div>

