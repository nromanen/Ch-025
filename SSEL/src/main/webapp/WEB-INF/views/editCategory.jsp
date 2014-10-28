<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Edit category</h1>


				</div>
				<!-- /.col-lg-12 -->
			</div>
			<div class="panel panel-default">
				<div class="panel-body">
					<!-- ololololololololololo -->

					<form action="saveCategory">
					<input type = "hidden" name = "categoryId" value = "${category.id}">
						<div class="form-group">
							<label>Category name</label> <input class="form-control" name="categoryName" value="${category.name}">
							<p class="help-block">Input or edit category name</p>
						</div>
						
						

						<br> <input type="submit" class="btn btn-primary btn-lg"
							value="Save">

					</form>


					<!-- /ololololololololololo -->
				</div>
			</div>

