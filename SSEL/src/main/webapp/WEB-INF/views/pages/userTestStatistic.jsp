<%@page import="org.springframework.ui.Model"
	import="com.softserve.util.TestStatisticWithQuestion"%>
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1 align="center">Hello, this is your test statistic</h1>

<c:forEach items="${tsByUserByTestList}" var="testStatistic">
<div class="col-md-12" align="center">
	<table class="table table-striped table-bordered ">
		<thead>
			<tr>
				<th colspan="2">${testStatistic.question.getQuestion().value }</th>  <!-- .value  -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${testStatistic.question.getQuestion().options}" var="option" varStatus="ind">
				<tr> 
					<th>${option.value}</th> <!-- [ $ {fn:indexOf(testStatistic.question.questionText.options, option) } ] -->
					<th>
					<c:choose>
							<c:when test="${testStatistic.userAnswers[ind.index] != 0 }"> 
								<c:choose>
								<c:when test="${option.isCorrect}" >
										<span class="fa fa-check green"></span>
									</c:when>
									<c:otherwise>
										<span class="glyphicon glyphicon-remove red"></span>
								</c:otherwise>
								</c:choose>
							 </c:when> 
						</c:choose>
						
					</th>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</c:forEach>
