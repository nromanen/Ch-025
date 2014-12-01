<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript">
	$(document)
			.on(
					"click",
					".openModalWindow",
					function() {
						var deleteCategory = $(this).data('id');

						var categoryId = $(this).data('id');
						$
								.ajax({
									type : "POST",
									url : "checkCategory",
									data : {
										"categoryId" : categoryId
									},
									error : function(xhr) {
										$("#demo").text(xhr.statusText);
									},
									success : function(str) {
										var json = JSON.parse(str);

										count = json["count"];
										if (count != null && count > 0) {
											$("#labelDeleteInf")
													.html(
															'<spring:message code="label.admin.delete_because"/>'
																	+ ' ('
																	+ '<spring:message code="label.admin.subjects_number"/>'
																	+ ': '
																	+ json["count"]
																	+ ')');
											$("#mybtn")
													.html(
															'<spring:message code="label.details"/>');
											$("#mybtn")
													.attr(
															"href",
															"viewAllSubjects?searchText="
																	+ json["categoryName"]
																	+ "&searchOption=category");
										} else {
											$("#labelDeleteInf")
													.html(
															'<spring:message code="label.admin_delete_record" />');
											$("#mybtn")
													.html(
															'<spring:message code="label.delete" />');
											$("#mybtn")
													.attr(
															"href",
															"deleteCategory?categoryId="
																	+ json["categoryId"]);
										}
									}
								});
					});
</script>

<script>
	$(function() {
		$(".sidebar").find($('a[href="viewAllCategories"]')).addClass("active");
	});
</script>

<script type="text/javascript">
	function validCategoryFunction() {
		categoryId = $("#demo").text();
		$.ajax({
			type : "POST",
			url : "checkCategory",
			data : {
				"categoryId" : categoryId
			},
			success : function(text) {
				document.getElementById("demo").innerHTML = text;
			}
		});

	}
</script>

<script type="text/javascript">
	$(function() {
		$('#addNewCategoryForm').submit(function() {
			var categoryName = document.getElementById("addNewCategory").value;
			var div = document.createElement("div");
			div.innerHTML = categoryName;
			categoryName = div.textContent || div.innerText || "";
			document.getElementById("addNewCategory").value = categoryName;
			return true;
		});
	});
</script>


<script type="text/javascript">
	$(document).ready(function() {
		$('#addNewCategoryForm').bootstrapValidator({
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				category : {
					validators : {
						notEmpty : {
							message : "<spring:message code='label.category_name_empty'/>"
						},
						regexp : {
							regexp : /^[A-ZА-ЯІЇЄ]{1}[a-zа-яіїє0-9_-\s]{1,30}$/i,
							message : "<spring:message code='label.category_name_invalid'/>"
						}
					}
				}
			}
		}).on('success.field.bv', function(e, data) {
			if (data.bv.isValid()) {
				data.bv.disableSubmitButtons(false);
			}
		});
	});
</script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#changeCategoryForm').bootstrapValidator({
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				category : {
					validators : {
						notEmpty : {
							message : "<spring:message code='label.category_name_empty'/>"
						},
						regexp : {
							regexp : /^[A-ZА-ЯІЇЄ]{1}[a-zа-яіїє0-9_-\s]{1,30}$/i,
							message : "<spring:message code='label.category_name_invalid'/>"
						}
					}
				}
			}
		}).on('success.field.bv', function(e, data) {
			if (data.bv.isValid()) {
				data.bv.disableSubmitButtons(false);
			}
		});
	});
</script>

<script type="text/javascript">
function changeCategoryName(categoryId){

	$('#inputChangeCategory').val('');
	$("#changeCategoryBtn").attr("disabled", "disabled");
	$('#changeCategoryId').val(categoryId);
	$('#changeCategoryNameModal').modal('show');

}
</script>
<div id="changeCategoryNameModal" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only"><spring:message
							code="label.close" /></span>
				</button>
			</div>
			<div class="modal-footer">
			<form id="changeCategoryForm" method="POST" role="form"
				action="changeCategory" class="form-horizontal">
				<input type="hidden" name="categoryId" id="changeCategoryId" value="" />
			<div class="form-group" align="center">
					<label class="control-label" for="inputChangeCategory"><spring:message
							code="label.new_category_name" /></label>
					<div class="col-md-12">
						<input type="text" class="form-control" id="inputChangeCategory"
							name="category"
							placeholder="<spring:message code='label.input_category' />">
					</div>
				</div>
				<p align="center">
					<button type="submit" class="btn btn-primary" id="changeCategoryBtn" disabled>
					<spring:message code="label.rename" /></button>
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<spring:message code="label.cancel" />
					</button>
				</p>
				</form>
			</div>
    </div>
  </div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only"><spring:message
							code="label.close" /></span>
				</button>
				<h4 class="modal-title" align="center" id="labelDeleteInf"></h4>
			</div>
			<div class="modal-footer">
				<p align="center">
					<a type="button" id="mybtn" class="btn btn-primary"
						onClick=""></a>
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
			<div class="alert alert-success alert-dismissible alertBlock"
				role="alert">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">&times;</span><span class="sr-only"><spring:message
							code="label.close" /></span>
				</button>
				<p align="center">${successMessage}</p>
			</div>
		</c:if>
		<c:if test="${not empty errorMessage}">
			<div class="alert alert-danger alert-dismissible alertBlock"
				role="alert">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">&times;</span><span class="sr-only"><spring:message
							code="label.close" /></span>
				</button>
				<p align="center">${errorMessage}</p>
			</div>
		</c:if>
		<div class="col-md-3">
			<br>

			<form id="addNewCategoryForm" method="post" role="form"
				action="addCategory" class="form-horizontal">
				<div class="form-group" align="center">
					<label class="control-label" for="addNewCategory"><spring:message
							code="label.admin_add_category" /></label>
					<div class="col-md-12">
						<input type="text" class="form-control" id="addNewCategory"
							name="category"
							placeholder="<spring:message code='label.input_category' />">
					</div>
				</div>
				<button type="submit" class="btn btn-primary btn-sm btn-block"
					disabled>
					<spring:message code="label.add" />
				</button>
			</form>
		</div>
		<div class="col-md-8">
			<br>
			<table class="table table-bordered">
				<tr align="center" class="info">
					<td class="col-md-6"><spring:message
							code="label.category_name" /></td>
					<td class="col-md-1"><spring:message code="label.rename" /></td>
					<td class="col-md-1"><spring:message code="label.delete" /></td>
				</tr>
				<c:forEach items="${categories}" var="category">
					<tr>
						<td>${category.name}</td>
						<td align="center"><a data-toggle="modal" href="#" onclick="changeCategoryName(${category.id})"><span class="glyphicon glyphicon-pencil"></span></a>
						</td>
						<td align="center"><a data-toggle="modal"
							data-id="${category.id}" class="openModalWindow" data-target="#myModal" href="#"><span
								class="glyphicon glyphicon-remove red"></span></a>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="col-md-1"></div>
	</div>
</div>
