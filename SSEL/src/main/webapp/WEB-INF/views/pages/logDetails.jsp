<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="row">
	<div class="col-lg-12">
		<h1 align="center">Log details </h1>
	</div>
	<font size="+1">
	<b>Log's date:</b> ${log.eventDate}<br/><br/>
	<b>Log's level: </b>${log.level}<br/><br/>
	<b>Log's logger: </b>${log.logger} <br/><br/>
	<b>Log's message:</b><br/>
	${log.message}<br/><br/>
	<b>Log's exception: </b><br/>
	${log.exception}<br/><br/>
	<a href="javascript: history.go(-1)">Go back</a>
	</font>
</div>
