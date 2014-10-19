<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>
<html>
<head>
<title>SSEL | Головна сторінка</title>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css"
	href="resources/styles/styles.css">
</head>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="menu">
		<div class="menu">
			<ul>
				<li>Курс 1</li>
				<li>Курс 2</li>
			</ul>
		</div>
	</tiles:putAttribute>
	<tiles:putAttribute name="main-content">
		<div class="main-content">
			<p>Lorem ipsum</p>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
</html>
