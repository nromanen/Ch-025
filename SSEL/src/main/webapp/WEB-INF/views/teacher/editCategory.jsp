<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header"><spring:message code="label.teacher.editCategory"/></h1>
	</div>
</div>

<div class="panel panel-default">
	<div class="panel-body">

		<form action="saveCategory" name="editCategoryForm"
			id="editCategoryForm">
			<input type="hidden" name="categoryId" value="${category.id}">
			<div class="form-group">
				<label><spring:message code="label.teacher.name"/></label> <input class="form-control"
					name="categoryName" value="${category.name}">
				<p class="help-block"><spring:message code="label.teacher.inputOrEditCategoryName"/></p>
			</div>



			<br> <input type="submit" class="btn btn-primary btn-lg"
				value=<spring:message code="label.teacher.save"/>>

		</form>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {

		$.validator.addMethod('regexp', function(value, element, regexp) {
			var re = new RegExp(regexp);
			return this.optional(element) || re.test(value);
		}, "Please check your input.");

		$("#editCategoryForm").validate({

			rules : {

				categoryName : {
					required : true,
					minlength : 4,
					maxlength : 30,
					regexp : "^[A-ZА-ЯІЇЄa-zа-яіїє0-9.,:_ ]{4,30}$"
				},
			}

		});

	});
</script>
<style>
.error {
	color: red;
}
</style>

