<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="row">
	<div class="col-lg-12">
		<h1 align="center"><spring:message code="label.log_details" /></h1>
	</div>
	<font size="+1">
	<b><spring:message code="label.log_date" /></b> ${log.eventDate}<br/><br/>
	<b><spring:message code="label.log_level" /></b> ${log.level}<br/><br/>
	<b><spring:message code="label.log_logger" /></b> ${log.logger} <br/><br/>
	<b><spring:message code="label.log_message" /></b> <br/>
	${log.message}<br/><br/>
	<b><spring:message code="label.log_exception" /> </b><br/>
	${log.exception}<br/><br/>
	<a href="javascript: history.go(-1)"><spring:message code="label.log_goBack" /></a>
	</font>
</div>