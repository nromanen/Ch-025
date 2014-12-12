<%@page import="org.springframework.ui.Model"
	import="com.softserve.entity.TestStatistic"%>
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<h1 align="center">Hello</h1>
TestStatistic by user 7 size: ${fn:length(statisticList)} <br>
Test name ${statisticList[0].test.name }<br>
User name ${statisticList[0].user.firstName }
<p>
User1 testResult 1 : ${userRes1} <br>
User2 testResult 1 : ${userRes2} <br>
<%-- User3 test 2 : ${userRes3} <br>
User1 test 2 : ${userRes4} <br> --%>
</p>

<br>
group100Statistic size: ${fn:length(group100Statistic)} <br>
group100Statistic.get(0) (result) : ${group100Statistic[1]}
