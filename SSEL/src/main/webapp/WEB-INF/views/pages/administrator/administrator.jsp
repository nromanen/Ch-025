<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script>
$(function() {
	//$(".sidebar").find("a").addClass("active");
	$(".sidebar").find($('a[href="administrator"]')).addClass("active");
	});
</script>

<div class="row">
	<div class="col-lg-12">
	</div>
</div>
