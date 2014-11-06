<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript">
	$(document).on("click", ".openModalWindow", function() {
		var deleteCategory = $(this).data('id');
		document.getElementById("mybtn").onclick = function() {
			window.location.href = deleteCategory;
		}
	});
</script>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only"><spring:message
							code="label.close" /></span>
				</button>
				<h4 class="modal-title" align="center">
					<spring:message code="label.admin_delete_record" />
				</h4>
			</div>
			<div class="modal-footer">
				<p align="center">
					<button type="button" id="mybtn" id="mybtn" class="btn btn-primary"
						onClick="">
						<spring:message code="label.delete" />
					</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<spring:message code="label.cancel" />
					</button>
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
		<c:if test="${not empty successMessage}">
			<div class="alert alert-success alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">&times;</span><span class="sr-only"><spring:message
							code="label.close" /></span>
				</button>
				<p align="center">${successMessage}</p>
			</div>
		</c:if>
		<c:if test="${not empty errorMessage}">
			<div class="alert alert-danger alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">&times;</span><span class="sr-only"><spring:message
							code="label.close" /></span>
				</button>
				<p align="center">${errorMessage}</p>
			</div>
		</c:if>
		<div class="col-md-3">
			<br>
			<form role="form" action="addCategory">
				<div class="form-group" align="center">
					<label for="addNewCategory"><spring:message
							code="label.admin_add_category" /></label> <input type="text"
						class="form-control" id="addNewCategory" name="category"
						placeholder="<spring:message code='label.input_category' />">
				</div>
				<button type="submit" class="btn btn-primary btn-sm btn-block">
					<spring:message code="label.add" />
				</button>
			</form>
		</div>
		<div class="col-md-6">
			<h1 align="center">
				<spring:message code="label.categories" />
			</h1>
			<table class="table table-bordered">
				<tr align="center" class="info">
					<td class="col-md-5"><spring:message
							code="label.category_name" /></td>
					<td class="col-md-1"><spring:message code="label.delete" /></td>
				</tr>
				<c:forEach items="${categories}" var="category">
					<tr>
						<td>${category.name}</td>
						<td align="center"><a data-toggle="modal"
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
