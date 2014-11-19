<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="resources/css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet" type="text/css">
<link href="resources/css/sb-admin-2.css" rel="stylesheet" type="text/css">
<link href="resources/datepicker/css/datepicker.css" rel="stylesheet" type="text/css">
<link href="resources/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="resources/css/plugins/dataTables.bootstrap.css" rel="stylesheet" type="text/css">
<link href="resources/styles/styles.css" rel="stylesheet" type="text/css">

<script src="resources/js/jquery-1.11.1.min.js"></script>
<script src="resources/js/jquery.validate.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/plugins/metisMenu/metisMenu.min.js"></script>
<script src="resources/js/sb-admin-2.js"></script>
<script src="resources/js/plugins/dataTables/jquery.dataTables.js"></script>
<script src="resources/datepicker/js/bootstrap-datepicker.js"></script>
<script src="resources/js/datepicker.js"></script>
<script src="resources/js/plugins/dataTables/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="<c:url value="/resources/js/localization.js" />"></script>

<title><tiles:getAsString name="title" /></title>
</head>

<body>

	<tiles:insertAttribute name="header" />

	<div id="wrapper">
		<tiles:insertAttribute name="menu" />
		<div id="page-wrapper">
			<tiles:insertAttribute name="content" />
		</div>
	</div>

	<tiles:insertAttribute name="footer" />

</body>
</html>