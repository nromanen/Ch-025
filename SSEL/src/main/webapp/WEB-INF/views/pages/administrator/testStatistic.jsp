<%@page import="org.springframework.ui.Model"
	import="com.softserve.entity.TestStatistic"%>
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<h1 align="center">Hello</h1>
${statisticList.size()}

<%-- <c:forEach items="${statisticList}" var="testStatistic">
	<p>${testStatistic.userId}</p>
	<p>${testStatistic.testId}</p>
	<p>${testStatistic.groupId}</p>
	<p>another element</p>
</c:forEach> --%>
<p>
User1 test 1 : ${userRes1} <br>
User2 test 1 : ${userRes2} <br>
User3 test 2 : ${userRes3} <br>
User1 test 2 : ${userRes4} <br>
</p>

<br>
group100Statistic size: ${group100Statistic.size()} <br>
group100Statistic.get(0): ${group100Statistic.get(0)}
