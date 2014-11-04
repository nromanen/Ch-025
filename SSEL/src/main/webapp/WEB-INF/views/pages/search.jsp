<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header"> <spring:message code="label.search_result" /> </h1>
	</div>
	<div class="col-lg-12">
		<p>
			<spring:message code="label.quest_text" />
		</p>
	<div class="input-group custom-search-form">
				<form method="get" action="search">
					<input type="text" class="form-control" name="search" 
						id="search" placeholder="Search...">
					<span class="input-group-btn">
						<button class="btn btn-default fix-height" type="submit">
							<i class="fa fa-search"></i>
						</button>
					</span>
				</form>
	</div>
	</div>
	<div class="clear"></div>
	<div style="margin-left:20px; margin-top:10px;">
	<div>
		<p> <spring:message code="label.categories" /> </p>
		<form method="get" action="category">
		<div>
		<c:if test="${fn:length(categories) gt 0}"> 
		<c:forEach var="cat" items="${categories}">
			<div style="padding-left:20px;">
				<button value="${cat.id}" name="categoryId" class="btn btn-link"
				style="color: #428bca;"> ${cat.name}</button>
			</div>
		</c:forEach>
		</c:if>
		<c:if test="${fn:length(categories) eq 0}">
			<p style="color:red;"> <spring:message code="label.no_categories"/>  </p>
		</c:if>
		</div>
		</form>
	</div>
	<p> <spring:message code="label.courses" /> </p>
	<form method="GET" action="course">
		<div class="blocks">
			<c:if test="${fn:length(subjects) gt 0}"> 
			<c:forEach items="${subjects}" var="subj">
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
			</c:if>
			<c:if test="${fn:length(subjects) eq 0}">
			<p style="color:red;"> <spring:message code="label.no_subjects"/> </p>
		</c:if>
		</div>
	</form> 
	</div> 
</div>


