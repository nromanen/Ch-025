<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


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
	<div>
		<form action="category" method="get">
			<label for="sel1" style="margin-left:20px;"> <spring:message code="label.records_per_page"/></label> 
			<select	class="form-control" id="sel1" name="pageSize" onchange="this.form.submit()">
				<option value="5" <c:if test="${pageSize eq 5}"> selected </c:if> > 5</option>
				<option value="10" <c:if test="${pageSize eq 10}"> selected </c:if> >10</option>
				<option value="20" <c:if test="${pageSize eq 20}"> selected </c:if> >20</option>
				<option value="50" <c:if test="${pageSize eq 50}"> selected </c:if> >50</option>
				<option value="100" <c:if test="${pageSize eq 100}"> selected </c:if> >100</option>
			</select>
			<div class="btn-group" style="float: right;">
					<c:if test="${pageNumber gt 1}">
						<button name="pageNumber" value="${pageNumber - 1}" type="submit" class="btn btn-default"><spring:message code="label.prev"/></button>
						<button name="pageNumber" value="1" type="submit" class="btn btn-default">1</button>
					</c:if>
					<c:if test="${pageNumber gt 3}">
						<button class="btn btn-default" disabled="disabled">...</button>
						
					</c:if>
					<c:if test="${pageNumber gt 2}">
						<button name="pageNumber" value="${pageNumber - 1}" type="submit" class="btn btn-default">${pageNumber - 1}</button>
					</c:if>
					<button class="btn btn-default" disabled="disabled">${pageNumber}</button>
					
					<c:if test="${pageNumber lt (numberOfPages - 1)}">
						<button name="pageNumber" value="${pageNumber + 1}"  type="submit" class="btn btn-default">${pageNumber + 1}</button>
					</c:if>
					<c:if test="${pageNumber lt (numberOfPages - 2)}">
						<button class="btn btn-default" disabled="disabled">...</button>
					</c:if>
					<c:if test="${pageNumber le (numberOfPages - 1)}">
						 <button name="pageNumber" value="${numberOfPages}" type="submit" class="btn btn-default">${numberOfPages}</button> 
						<button name="pageNumber" value="${pageNumber + 1}" type="submit" class="btn btn-default"><spring:message code="label.next"/></button>
					</c:if>
					<c:if test="${pageNumber eq (numberOfPages)}">
						<button class="btn btn-default" disabled="disabled"><spring:message code="label.next"/></button>
					</c:if>
		</div>
			<input type="hidden" value="${categoryId}" name="categoryId">
		</form>
	</div>
	<form method="GET" action="course">
			<div class="blocks">
				<c:if test="${fn:length(subjList) gt 0}">
					<table class="table table-striped table-bordered table-hover">
						<tr>
							<th><spring:message code="label.course_name" /></th>
							<th><spring:message code="label.category" /></th>
							<th><spring:message code="label.start_date" /></th>
						</tr>
							<c:forEach items="${subjList}" var="subj">
								<tr>
								<td>
									<button value="${subj.id}" name="subjectId"
										class="btn btn-link" style="color: #428bca;">${subj.name}</button>
								</td>
								<td>
									${subj.category.name}</td>
								<td><c:forEach items="${schedule}" var="schedule">
										<c:if test="${schedule.subject.id eq subj.id}">
												<fmt:formatDate pattern='dd-MM-yyyy'
													value='${schedule.start}' />
										</c:if>
									</c:forEach></td> </tr>
							</c:forEach>
					</table>
				</c:if>
			</div>
			</form>
			<c:if test="${fn:length(subjList) eq 0}">
				<p style="margin-left: 30px;">
					<spring:message code="label.no_subjects" />
				</p>
			</c:if>
	<div>
		<form action="category" method="get">
			<label for="sel1" style="margin-left:20px;"> <spring:message code="label.records_per_page"/></label> 
			<select	class="form-control" id="sel1" name="pageSize" onchange="this.form.submit()">
				<option value="5" <c:if test="${pageSize eq 5}"> selected </c:if> > 5</option>
				<option value="10" <c:if test="${pageSize eq 10}"> selected </c:if> >10</option>
				<option value="20" <c:if test="${pageSize eq 20}"> selected </c:if> >20</option>
				<option value="50" <c:if test="${pageSize eq 50}"> selected </c:if> >50</option>
				<option value="100" <c:if test="${pageSize eq 100}"> selected </c:if> >100</option>
			</select>
			<div class="btn-group" style="float: right;">
					<c:if test="${pageNumber gt 1}">
						<button name="pageNumber" value="${pageNumber - 1}" type="submit" class="btn btn-default"><spring:message code="label.prev"/></button>
						<button name="pageNumber" value="1" type="submit" class="btn btn-default">1</button>
					</c:if>
					<c:if test="${pageNumber gt 3}">
						<button class="btn btn-default" disabled="disabled">...</button>
						
					</c:if>
					<c:if test="${pageNumber gt 2}">
						<button name="pageNumber" value="${pageNumber - 1}" type="submit" class="btn btn-default">${pageNumber - 1}</button>
					</c:if>
					<button class="btn btn-default" disabled="disabled">${pageNumber}</button>
					
					<c:if test="${pageNumber lt (numberOfPages - 1)}">
						<button name="pageNumber" value="${pageNumber + 1}"  type="submit" class="btn btn-default">${pageNumber + 1}</button>
					</c:if>
					<c:if test="${pageNumber lt (numberOfPages - 2)}">
						<button class="btn btn-default" disabled="disabled">...</button>
					</c:if>
					<c:if test="${pageNumber le (numberOfPages - 1)}">
						 <button name="pageNumber" value="${numberOfPages}" type="submit" class="btn btn-default">${numberOfPages}</button> 
						<button name="pageNumber" value="${pageNumber + 1}" type="submit" class="btn btn-default"><spring:message code="label.next"/></button>
					</c:if>
					<c:if test="${pageNumber eq (numberOfPages)}">
						<button class="btn btn-default" disabled="disabled"><spring:message code="label.next"/></button>
					</c:if>
		</div>
			<input type="hidden" value="${categoryId}" name="categoryId">
		</form>
	</div>
</div>


