<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="row">
	<div class="col-lg-12">
		<h1 align="center">Users</h1>
		<div class="col-md-12" align="right">
			<label>On page <select id="elementsOnPage"
				onchange="changeElementsPerPageFunction(this)">
					<option value="1">10</option>
					<option value="2">25</option>
					<option value="5">50</option>
			</select>
		</div>
		<table class="table table-bordered">
			<tr align="center" class="info">
				<td class="col-md-5">User email</td>
				<td class="col-md-3">Expired</td>
				<td class="col-md-2">role</td>
				<td class="col-md-2">blocked</td>
			</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.email}</td>
					<td align="center">${user.expired}</td>
					<td align="center">${user.role.role}</td>
					<td align="center">
					<c:if test="${user.blocked eq false}">
					<a href="#"><span class="glyphicon glyphicon-unchecked green"></span></a>
					</c:if>
					<c:if test="${user.blocked eq true}">
					<a href="#"><span class="glyphicon glyphicon-check red"></span></a>
					</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div class="col-md-12" align="center">
				<ul class="pagination">
					<li><a href="#">&laquo;</a></li>
					<li><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li class="active"><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#">&raquo;</a></li>
				</ul>
			</div>
	</div>
</div>
