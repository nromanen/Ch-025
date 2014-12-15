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

				<div class="col-md-10 col-md-offset-1" align="center">
					<c:forEach items="${tsByUserByTestList}" var="testStatistic">
						<table class="table table-bordered">
							<thead>
								<tr class="info">
									<th colspan="2">${testStatistic.question.getQuestion().value }</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach
									items="${testStatistic.question.getQuestion().options}"
									var="option" varStatus="ind">
									<c:choose>
										<c:when test="${testStatistic.userAnswers[ind.index] != 0 }">
											<c:choose>
												<c:when test="${option.isCorrect }">
													<tr class="success">
														<td class="col-md-11">${option.value}</td>
														<td class="col-md-1" align="center"><span
															class="fa fa-check green"></span></td>
													</tr>
												</c:when>
												<c:otherwise>
													<tr class="danger">
														<td class="col-md-11">${option.value}</td>
														<td class="col-md-1" align="center"><span
															class="glyphicon glyphicon-remove red"></span></td>
													</tr>
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											<tr>
												<td class="col-md-11">${option.value}</td>
												<td class="col-md-1"></td>
											</tr>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<tr>
									<td colspan="2"><i><spring:message code="label.userTS.earned" /> ${testStatistic.userResult}
											<spring:message code="label.userTS.from" /> ${testStatistic.maxResult}</i></td>
								</tr>
							</tbody>
						</table>
						<br />
					</c:forEach>
				</div>
			</div>
		</div>
	</c:otherwise>
</c:choose>

<h3>
	<a href="javascript: history.go(-1)"><spring:message code="label.log_goBack" /></a>
</h3>
<br>
