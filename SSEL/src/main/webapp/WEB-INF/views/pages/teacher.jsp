<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				<a href="teacher">Teacher</a>
			</h1>
		</div>
		<div class="panel panel-default">
			Hi, Teacher. <br>
			<button type="button" class="btn btn-outline btn-primary btn-xs"
				onclick="location.href='editCategory'">Add new category</button>
			<button type="button" class="btn btn-outline btn-primary btn-xs"
				onclick="location.href='editSubject'">Add new course</button>
			<!-- .panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!--  -->


