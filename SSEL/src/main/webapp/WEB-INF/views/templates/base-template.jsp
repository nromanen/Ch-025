<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">

	<link href="resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="resources/css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet" type="text/css">
	<link href="resources/css/sb-admin-2.css" rel="stylesheet" type="text/css">
	<link href="resources/styles/styles.css" rel="stylesheet" type="text/css">
	<link href="resources/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="resources/css/plugins/dataTables.bootstrap.css" rel="stylesheet" type="text/css">
	<script src="resources/js/jquery-1.11.0.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/plugins/metisMenu/metisMenu.min.js"></script>
	<script src="resources/js/sb-admin-2.js"></script>
	<script src="resources/js/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="resources/js/plugins/dataTables/dataTables.bootstrap.js"></script>
    <title><tiles:getAsString name="title" /></title>			
</head>

<body>

<tiles:insertAttribute name="header" />

<div id="page-wrapper">
    <div class="row">
        <tiles:insertAttribute name="menu" />
    </div>
    <div class="row">
        <div class="col-lg-12">
            <tiles:insertAttribute name="content" />
        </div>
    </div>
</div>

<tiles:insertAttribute name="footer" />

</body>
</html>