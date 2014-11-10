<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script>
	$(function() {
		//$(".sidebar").find("a").addClass("active");
		$(".sidebar").find($('a[href="viewAllRequests"]')).addClass("active");
	});
</script>

<script type="text/javascript">
	function changeRoleFunction(userId) {
		window.location.href = "changeUserRoleToAdmin?userId=" + userId;
	}
</script>

<script type="text/javascript">
	function deleteRequestFunction(userId) {
		window.location.href = "deleteTeacherRequest?userId=" + userId;
	}
</script>

<div class="row">
	<div class="col-lg-12">

		<!-- Message block -->
		<c:if test="${not empty successMessage}">
			<div class="alert alert-success alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">&times;</span><span class="sr-only"><spring:message
							code="label.close" /></span>
				</button>
				<p align="center">${successMessage}</p>
			</div>
		</c:if>

		<c:if test="${not empty errorMessage}">
			<div class="alert alert-danger alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">&times;</span><span class="sr-only"><spring:message
							code="label.close" /></span>
				</button>
				<p align="center">${errorMessage}</p>
			</div>
		</c:if>
		<!-- /Message block -->

		<div class="col-md-12">
			<br>
			<c:if test="${not empty teacherRequests}">
				<div class="col-md-6">
					<h3 align="center">
						<spring:message code="label.request_teaher_role" />
					</h3>
					<table class="table table-bordered">
						<tr class="info" align="center">
							<td><spring:message code="label.user_email" /></td>
							<td><spring:message code="label.accept" /></td>
							<td><spring:message code="label.delete" /></td>
						</tr>
						<c:forEach items="${teacherRequests}" var="teacherRequest">
							<tr>
								<td>${teacherRequest.user.email}</td>
								<td align="center"><a href="#"
									onclick="changeRoleFunction('${teacherRequest.user.id}')"><span
										class="glyphicon glyphicon-unchecked green"></span></a></td>
								<td align="center"><a href="#"
									onclick="deleteRequestFunction('${teacherRequest.user.id}')"><span
										class="glyphicon glyphicon-remove red"></span></a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</c:if>
		</div>
	</div>
</div>
