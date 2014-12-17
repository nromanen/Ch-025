<%@page import="org.springframework.ui.Model"%>
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:choose>
	<c:when test="${!empty tsByUserByTestList}">
		<br />
		<h2 align="center">
			<spring:message code="label.userTS.you_have_not_passed_this_test" />
		</h2>
	</c:when>
	<c:otherwise>
		<div class="row">
			<div class="col-md-10" align="center">
				<h2 align="center">
					Test: <strong>${test.name}</strong>
				</h2>

				<div align="center">
					<c:forEach items="${questions}" var="questionList">
						<table class="table table-bordered" id="${questionList.getId()}">
							<thead>
								<tr class="info">
									<th colspan="2">${questionList.getQuestion().getValue() }</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${questionList.getQuestion().getOptions()}"
									var="option" varStatus="ind">
									<tr class="success">
										<td class="col-md-11">${option.value}</td>
										<td class="col-md-1" align="center"><input
											type="checkbox" id="selecta" /></td>
									</tr>
								</c:forEach>
								<tr>
									<td colspan="2"><i>Earned ${testStatistic.userResult}
											from ${testStatistic.maxResult}</i></td>
								</tr>
							</tbody>
						</table>
						<br />
					</c:forEach>
					<button type="button" class="btn btn-info" id="submitTest">
						<spring:message code="label.teacher.save" />
					</button>
				</div>
			</div>
		</div>
	</c:otherwise>
</c:choose>

<h3>
	<a href="javascript: history.go(-1)">Go back</a>
</h3>
<br>

<script type="text/javascript">
	$(document).ready(function() {
		$("#submitTest").click(function() {
			//	if (confirm('Are you sure?')) {

			$('table').each(function(j, ele) {
				testId0 = '${test.id}';
				str = "";
				var choises = new Array();

				$(this).find(':checkbox').each(function(i, el) {
					if ($(el).prop('checked')) {
						str = str + '1';
						choises.push(1);
					} else {
						str = str + '0';
						choises.push(0);
					}
				});
				
				$.ajax({
					type : "POST",
					url : "submitTest",
					data : {
						testId : testId0,
						questionId : $(ele).attr('id'),
						'choices[]' : choises
					}
				});

			});

			window.location.href = "userTestStatistic?testId=" + testId0;

		});
	});
</script>
