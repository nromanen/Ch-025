<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
			<spring:message code="label.search_result" />
		</h1>
	</div>
	<div class="col-lg-12">
		<p>
			<spring:message code="label.quest_text" />
		</p>
		<div class="input-group custom-search-form">
			<form method="get" action="search">
				<input type="text" class="form-control inp" name="search" id="search"
					placeholder="<spring:message code="placeholder.search"/>">
				<span class="input-group-btn" style="display: inherit;">
					<button class="btn btn-default fix-height" type="submit">
						<i class="fa fa-search"></i>
					</button>
				</span>
			</form>
		</div>
	</div>
	<div>
		<form action="search" method="get">
			<label for="rowsPerPage">Choose number of records per page</label> 
			<select	id="rowsPerPage" name="pageSize" onchange="this.form.submit()">
				<option value="5" <c:if test="${pageSize eq 5}"> selected </c:if> > 5</option>
				<option value="10" <c:if test="${pageSize eq 10}"> selected </c:if> >10</option>
				<option value="20" <c:if test="${pageSize eq 20}"> selected </c:if> >20</option>
				<option value="50" <c:if test="${pageSize eq 50}"> selected </c:if> >50</option>
				<option value="100" <c:if test="${pageSize eq 100}"> selected </c:if> >100</option>
			</select>
			<div align="right">
			<nav>
				<ul class="pagination">
					<c:if test="${pageNumber lt 1}">
						<li class="disabled"><a href="#">Previous</a></li>
					</c:if>
					<c:if test="${pageNumber gt 0}">
						<li><button name="pageNumber" value="${pageNumber - 1}" type="submit">Previous</button></li>
						<li><button name="pageNumber" value="1" type="submit">1</button></li>
					</c:if>
					<c:if test="${pageNumb gt 2}">
						<li><a href="#">...</a></li>
					</c:if>
					<c:if test="${pageNumb gt 1}">
						<li><button name="pageNumber" value="${pageNumber}"  type="submit">${pageNumber}</button></li>
					</c:if>
					<li class="active"><a href="#">${pageNumber}</a></li>
					<c:if test="${pageNumber < (numberOfPages - 2)}">
						<li><button name="pageNumber" value="${pageNumber + 1}"  type="submit">${pageNumber + 2}</button></li>
					</c:if>
					<c:if test="${pageNumber lt (numberOfPages - 3)}">
						<li><a href="#">...</a></li>
					</c:if>
					<c:if test="${pageNumber lt (numberOfPages - 1)}">
						<li><button name="pageNumber" value="${pageNumber - 1}" type="submit">${numberOfPages}</button></li>
						<li><button name="pageNumber" value="${pageNumber + 1}" type="submit">Next</button></li>
					</c:if>
					<c:if test="${pageNumber gt (numberOfPages - 2)}">
						<li class="disabled"><a href="#">Next</a></li>
					</c:if>
				</ul>
			</nav>
		</div>
			<input type="hidden" value="${search}" name="search">
		</form>
	</div>
	<div class="clear"></div>
	<div style="margin-left: 20px; margin-top: 10px;">
		<div>
			<p>
				<spring:message code="label.categories" />
			</p>
			<form method="get" action="category">
				<div>
					<c:if test="${fn:length(catList) gt 0}">
						<c:forEach var="cat" items="${catList}">
							<div style="padding-left: 20px;">
								<button value="${cat.id}" name="categoryId" class="btn btn-link"
									style="color: #428bca;">${cat.name}</button>
							</div>
						</c:forEach>
					</c:if>
					<c:if test="${fn:length(catList) eq 0}">
						<p style="margin-left: 30px;">
							<spring:message code="label.no_categories" />
						</p>
					</c:if>
				</div>
			</form>
		</div>
		<p>
			<spring:message code="label.courses" />
		</p>
		<form method="GET" action="course">
			<div class="blocks">
				<c:if test="${fn:length(subjList) gt 0}">
					<table class="table table-striped table-bordered table-hover">
						<tr>
							<th>Course name</th>
							<th><spring:message code="label.category" /></th>
							<th><spring:message code="label.start_date" /></th>
						</tr>
						<tr>
							<c:forEach items="${subjList}" var="subj">
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
									</c:forEach></td>
							</c:forEach>
						</tr>
					</table>
				</c:if>
			</div>
			</form>
			<c:if test="${fn:length(subjList) eq 0}">
				<p style="margin-left: 30px;">
					<spring:message code="label.no_subjects" />
				</p>
			</c:if>
	</div>
</div>


