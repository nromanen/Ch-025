<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>
<!doctype html>
<html>
<head>
<title>SSEL SoftServeAcademy | Main</title>

<!-- Bootstrap Core CSS -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="resources/styles/styles.css" rel="stylesheet" type="text/css">

</head>
<tiles:insertDefinition name="template_index">
	<tiles:putAttribute name="main-content_index">
		<div class="content">
			<div class="menu">
				<p> List of avaliable courses </p>
				<ul class="unstyled">
				<c:forEach items="${subjects}" var="s">
					<li>{s.name} </li>
				</c:forEach>
				</ul>
			</div>
			<div class="main-content">
					<p> Lorem ipsum </p>
			</div>
		</div>
		</tiles:putAttribute>
</tiles:insertDefinition>
</body>
</html>