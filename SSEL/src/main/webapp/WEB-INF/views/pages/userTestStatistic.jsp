<%@page import="org.springframework.ui.Model"
	import="com.softserve.util.TestStatisticWithQuestion"%>
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1 align="center">Hello, this is your test statistic</h1>

<c:forEach items="${testStatWithQList}" var="testStatWithQ">
	<table class="table table-striped">
		<thead>
			<tr>
				<th colspan="2">${testStatWithQ.getQuestion().getQuestion() }</th>  <!-- REDOOOOOOOOOOOOO!!!!!!!!!! -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${testStatWithQ.getQuestion().getOptions()}" var="option"> <!-- REDOOOOOOOOOOOOO!!!!!!!!!! -->
				<tr> 
					<th>${option.value}</th>
					<th>
							<c:when test="${testStatWithQ.getTestStatistic().getUserAnswers().get(
testStatWithQ.getQuestion().getOptions().indexOf(option)) not eq 0}"> <!-- REDOOOOOOOOOOOOO!!!!!!!!!! -->
									<c:when test="${option.isCorrect} " >
										<span class="glyphicon glyphicons-ok green"></span>
									</c:when>
									<c:otherwise>
										<span class="glyphicon glyphicon-remove red"></span>
									</c:otherwise>
							</c:when>
					</th>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:forEach>
