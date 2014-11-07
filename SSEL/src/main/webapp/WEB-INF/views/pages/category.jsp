<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">SoftServe SSEL Academy</h1>
	</div>
	<div class="col-lg-12">
		<p>
			<spring:message code="label.quest_text" />
		</p>
	<div class="input-group custom-search-form">
				<form method="get" action="search">
					<input type="text" class="form-control inp" name="search" 
						id="search" placeholder="<spring:message code="placeholder.search"/>">
					<span class="input-group-btn" style="display:inherit;">
						<button class="btn btn-default fix-height" type="submit">
							<i class="fa fa-search"></i>
						</button>
					</span>
				</form>
	</div>
	</div>
	<form method="GET" action="course">
		<div class="blocks">
			<c:forEach items="${subjList}" var="subj">
				<div class="inline">
					<div class="title">
						<button value="${subj.id}" name="subjectId" class="btn btn-link"
							style="color: #428bca;">${subj.name}</button>
					</div>
					<div>
						<spring:message code="label.category" />
						${subj.category.name}
					</div>
					<c:forEach items="${schedule}" var="schedule">
						<c:if test="${schedule.subject.id eq subj.id}">
							<div>
								<spring:message code="label.start_date" />
								<fmt:formatDate pattern='dd-MM-yyyy' value='${schedule.start}' />
							</div>
						</c:if>
					</c:forEach>
				</div>
			</c:forEach>
		</div>
	</form>
</div>


