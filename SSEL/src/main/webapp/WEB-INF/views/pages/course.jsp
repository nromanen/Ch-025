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
			<div style="padding-top: 8px;">Description:
				${subject.description}</div>
			<c:if test="${sessionScope.user.role.role eq 'STUDENT'}">
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
				</form>
			</c:if>
			<form method="GET" action="courseInformation">
				<button value="${subject.id}" name="subjectId" class="btn btn-info"
					style="width: 200px; margin-top: 8px;"><spring:message code="label.details" />
					</button>
			</form>
		</div>
	</div>
</div>

