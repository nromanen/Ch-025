<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
	
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/css/bootstrapValidator.css" />" rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/css/plugins/metisMenu/metisMenu.min.css" />" rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/css/sb-admin-2.css" />" rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/css/bootstrap-social.css" />" rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/font-awesome-4.1.0/css/font-awesome.min.css" />" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<c:url value="/resources/css/jquery.fileupload.css" />" type="text/css"/>
<link rel="stylesheet" href="<c:url value="/resources/css/jquery.fileupload-ui.css" />" type="text/css"/>
<link rel="stylesheet" href="<c:url value="/resources/css/animate.css" />" type="text/css"/>

<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/plugins/metisMenu/metisMenu.min.js" />"></script>
<script src="<c:url value="/resources/js/sb-admin-2.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/localization.js" />"></script>

<title><tiles:getAsString name="title" /></title>
</head>

<body>
	<tiles:insertAttribute name="header" />
	
	<tiles:insertAttribute name="menu" />
		
	<tiles:insertAttribute name="content" />

	<footer style="width:100%;position:absolute;bottom:0;left:0; height: 25">
		<tiles:insertAttribute name="footer" />
	</footer>
</body>
</html>
