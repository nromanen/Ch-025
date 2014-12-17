<%@page import="org.springframework.ui.Model"%>
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:choose>
	<c:when test="${empty tsByUserByTestList}">
		<br />
		<h2 align="center"><spring:message code="label.userTS.you_did_not_pass_this_test" /></h2>
	</c:when>
	<c:otherwise>
		<div class="row">
			<div class="col-md-12" align="center">
				<h2 align="center">
					<spring:message code="label.userTS.your_test_statistic_on_test" /> <strong>${tsByUserByTestList[0].test.name}</strong>
				</h2>
				<h3 align="center">
					<spring:message code="label.userTS.your_total_result_is" /> <strong><fmt:formatNumber
							type="number" maxFractionDigits="1" value="${totalUserResult}" /></strong>%
				</h3>
					<button type="button" class="btn btn-info" onclick="location.href='userTestStatistic?testId=${test.id}'">
						<spring:message code="label.details" />
					</button>

			</div>
		</div>
	</c:otherwise>
</c:choose>

<h3>
	<a href="javascript: history.go(-1)"><spring:message code="label.log_goBack" /></a>
</h3>
<br>
