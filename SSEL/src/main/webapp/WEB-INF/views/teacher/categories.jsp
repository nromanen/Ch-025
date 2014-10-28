<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Categories</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="panel panel-default">
	<div class="panel-body">
		<form action="saveCategory">
			<input type="hidden" name="categoryId" value="${category.id}">
			<div class="form-group">
				<label>Category name</label> <input class="form-control"
					name="categoryName" style="width: 40%" value="${category.name}">
				<p class="help-block">Input name for new category</p>
				<input type="submit" class="btn btn-primary btn-lg" value="Add">
			</div>
		</form>
	</div>




	<div class="table-responsive">
		<table class="table table-hover" style="width: 40%">
			<thead>
				<tr>
					<th><input type="checkbox" id="selectall" /></th>
					<th>Category name</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${catList}" var="category">

					<tr class="odd gradeA">
						<td><input type="checkbox" class="case" name="case"
							value="${category.id}" /></td>
						<td><a href="editCategory?categoryId=${category.id}">${category.name}</a></td>

					</tr>

				</c:forEach>
			</tbody>
		</table>
	</div>
	<button type="button" id="delButton"
		class="btn btn-primary btn-lg">Delete</button>
		<br>
		<br>


	<!-- .panel-body -->
</div>
<!-- /.panel -->


<script type="text/javascript">
	$(document).ready(function() {
		$("#delButton").click(function() {
			var checkeds = $(":checkbox:checked").map(function() {
				return this.value;
			}).get();
			//alert(checkeds);
			if (confirm('Are you sure?')) {
				$.ajax({
					type : "GET",
					url : "deleteCategories",
					data : "categoriesIds=" + checkeds,
					success : function(msg) {
						location.reload();
					}
				});
			}
		});
		// add multiple select / deselect functionality
		$("#selectall").click(function() {
			$('.case').attr('checked', this.checked);
		});
		// if all checkbox are selected, check the selectall checkbox  also        
		$(".case").click(function() {
			if ($(".case").length == $(".case:checked").length) {
				$("#selectall").attr("checked", "checked");
			} else {
				$("#selectall").removeAttr("checked");
			}
		});
	});
</script>

