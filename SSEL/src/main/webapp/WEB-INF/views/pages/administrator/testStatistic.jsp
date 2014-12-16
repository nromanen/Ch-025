<%@page import="org.springframework.ui.Model"
	import="com.softserve.entity.TestStatistic"%>
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<h1 align="center">Hello</h1>
tsByUserByTest: <br>
lenght - ${fn:length(tsByUserByTest)} <br>
UserName - ${tsByUserByTest[0].user.firstName}<br>
TestName - ${tsByUserByTest[0].test.name}<br>
UserResult - ${tsByUserByTest[0].userResult}<br>
UserAnswer on first question - ${tsByUserByTest[0].userAnswers[0]}<br>

<br><br>

tsByGroupByTest:<br>
lenght - ${fn:length(tsByGroupByTest)} <br>
UserName - ${tsByGroupByTest[4].user.firstName}<br>
TestName - ${tsByGroupByTest[4].test.name}<br>
UserResult - ${tsByGroupByTest[4].userResult}<br>

<br><br>

UserResultByTest1: ${firstUserResultByTest1} <br>
UserResultByTest2: ${firstUserResultByTest2} <br>
<br><br>

GroupResultByTest: <br>
lenght - ${fn:length(GroupResultByTest)} <br>
firstUserResult - ${GroupResultByTest[0]} <br>
<br>

<h2>The End </h2>
<font size =+2>
<a href="${pageContext.request.contextPath}/userTestStatistic?userId=1&testId=1">Show user1 statistic</a>
</font>


