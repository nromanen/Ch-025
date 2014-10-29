<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="row">
	<div class="col-lg-12">
		<div class="col-md-3">
		<br>
		<form role="form" action="addCategory" align="center">
		<div class="form-group">
    <label for="addNewCategory">Add new category</label>
	<input type="text" class="form-control" id="addNewCategory" name="category" placeholder="Input category">
	</div>
	<button type="submit" class="btn btn-primary btn-sm btn-block">Add category</button>
	</form>
		</div>
		<div class="col-md-6">
		<h1 align="center">Categories:</h1>
		<table class="table table-bordered" align="center">
		<tr align="center" class="info">
		<td class="col-md-5">Category name</td>
		<td class="col-md-1">Delete</td>
		</tr>
		<c:forEach items="${categories}" var="category">
		<tr>
		<td>${category.name}</td>
		<td align="center"><a href="deleteCategory?categoryId=${category.id}">X</a></td>
		</tr>
		</c:forEach>
		</table>
		</div>
		<div class="col-md-3"></div>
	</div>
</div>
