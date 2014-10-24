<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

			<div class="row">
				<div class="col-lg-12">
					<h2 align="center">${subject.name}</h2>
					<table class="table table-bordered">
						<tr class="warning" align="center">
							<td>Category</td>
							<td>Course starts at:</td>
							<td>Course finishes at:</td>
						</tr>
						<tr class="info" align="center">
							<td>${category.name}</td>
							<td><fmt:formatDate pattern="dd-MM-yyyy" 
            value="${schedule.start}" /></td>
							<td><fmt:formatDate pattern="dd-MM-yyyy" 
            value="${schedule.end}" /></td>
						</tr>
					</table>
					<div class="courceInformation">
						<h4>
							<b>Course description:</b>
						</h4>
						<p>${subject.description}</p>
						<hr>
						<table>

							<thead>
								<h4>
									<b>Course program:</b>
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
											<td class="topic"><c:out
													value="${topic.name}" /></td>
										</tr>
									</c:if>
								</c:forEach>
							</c:forEach>
						</table>
						<form method="GET" action="subscribe">
					<c:if test="${sessionScope.user.role.role eq 'STUDENT'}">
					<c:if test="${isSubscribe eq true}">
					<button value="${subject.id}" name="subjectId" class="btn-success"
					style="border-radius:5px; margin-top:8px;">Subscribe to course </button>
					</c:if>
					<c:if test="${isSubscribe eq false}">
					<button value="${subject.id}" name="subjectId" class="btn-success"
					style="border-radius:5px; margin-top:8px;">Unsubscribe form course </button>
					</c:if>
					</c:if>
					<input type="hidden" name="op" value="${isSubscribe}">
					</form>
					</div>
				</div>
			</div>
