<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script type="text/javascript">
	$(document).on("click", ".openModalWindow", function() {
		var subjectId = $(this).data('id');

		document.getElementById("mybtn").onclick = function() {
			var newCategory = document.getElementById("newCategory").value;
			window.location.href = subjectId + "&categoryId=" + newCategory;
		}
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
				<h4 class="modal-title" align="center">Please, choose category!</h4>
				<select multiple class="form-control" id="newCategory">
					<c:forEach items="${categories}" var="category">
						<option value="${category.id}">${category.name}</option>
					</c:forEach>
				</select>
			</div>
			<div class="modal-footer">
				<p align="center">
					<button type="button" id="mybtn" id="mybtn" class="btn btn-primary"
						onClick="">Change</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Cansel</button>
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
			<div class="alert alert-danger alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<strong>Attention! </strong>${message}
			</div>
		</c:if>
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<h1 align="center">Subjects:</h1>
			<table class="table table-bordered" align="center">
				<tr align="center" class="info">
					<td class="col-md-6">Subject name</td>
					<td class="col-md-4">Category</td>
					<td class="col-md-1">Change category</td>
				</tr>
				<c:forEach items="${subjects}" var="subject">
					<tr>
						<td>${subject.name}</td>
						<td align="center">${subject.category.name}</td>
						<td><a align="center"
							data-toggle="modal"
							data-id="changeSubjectCategory?subjectId=${subject.id}"
							class="openModalWindow" href="#myModal"><span
								class="glyphicon glyphicon-pencil red"></span></a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="col-md-3"></div>
	</div>
</div>
