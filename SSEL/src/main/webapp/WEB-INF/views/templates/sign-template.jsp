<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
	
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/css/plugins/metisMenu/metisMenu.min.css" />" rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/css/sb-admin-2.css" />" rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/styles/styles.css" />" rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/font-awesome-4.1.0/css/font-awesome.min.css" />" rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/css/plugins/dataTables.bootstrap.css" />" rel="stylesheet" type="text/css">

<script src="<c:url value="/resources/js/jquery-1.11.0.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/plugins/metisMenu/metisMenu.min.js" />"></script>
<script src="<c:url value="/resources/js/sb-admin-2.js" />"></script>

<title><tiles:getAsString name="title" /></title>
</head>

<body>

	<tiles:insertAttribute name="header" />
	
	<tiles:insertAttribute name="content" />

	<tiles:insertAttribute name="footer" />

</body>
</html>