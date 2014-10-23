<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">SoftServe SSEL Academy</h1>
		</div>
		<div class="col-lg-12">
			<h3 class="title">About course</h3>
			<div>
				<div class="title">${subject.name}</div>
				<div style="padding-top: 8px;">Description:
					${subject.description}</div>
				<form method="GET" action="subscribe">
					<c:if test="${isSubscribe eq true}">
						<button value="${subject.id}" name="subjectId" class="btn-success"
							style="border-radius: 5px; margin-top: 8px;">Subscribe
							to course</button>
					</c:if>
					<c:if test="${isSubscribe eq false}">
						<button value="${subject.id}" name="subjectId" class="btn-success"
							style="border-radius: 5px; margin-top: 8px;">Unsubscribe
							from course</button>
					</c:if>
					<input type="hidden" name="op" value="${isSubscribe}">
				</form>
				<form method="GET" action="courseInformation">
					<button value="${subject.id}" name="subjectId" class="btn-info"
						style="border-radius: 5px; margin-top: 8px;">Details</button>
				</form>
			</div>
		</div>
	</div>
</div>
