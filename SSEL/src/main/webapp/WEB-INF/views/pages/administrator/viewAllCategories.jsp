<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript">
$(document).on("click", ".openModalWindow", function () {
    var deleteCategory = $(this).data('id');
    document.getElementById("mybtn").onclick = function() { window.location.href = deleteCategory; }
});
</script>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" align="center">Do you want delete
						record?</h4>
				</div>
				<div class="modal-footer">
					<p align="center">
					<button type="button" id="mybtn" id="mybtn"
							class="btn btn-primary" onClick="">Delete</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					</p>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
<div class="row">
	<div class="col-lg-12">
		<c:if test="${not empty message}">
			<div class="alert alert-success alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<p align="center">${message}</p>
			</div>
		</c:if>
		<div class="col-md-3">
			<br>
			<form role="form" action="addCategory" align="center">
				<div class="form-group">
					<label for="addNewCategory">Add new category</label> <input
						type="text" class="form-control" id="addNewCategory"
						name="category" placeholder="Input category">
				</div>
				<button type="submit" class="btn btn-primary btn-sm btn-block">Add
					category</button>
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
						<td align="center">
						<a data-toggle="modal"
									data-id="deleteCategory?categoryId=${category.id}"
									class="openModalWindow" href="#myModal"><span
										class="glyphicon glyphicon-remove red"></span></a>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="col-md-3"></div>
	</div>
</div>
