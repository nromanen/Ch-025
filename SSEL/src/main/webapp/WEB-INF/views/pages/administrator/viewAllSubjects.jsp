<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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

<script>
	function reloadCategoryFunction(subjectId) {
		var categoryId = document.getElementById("newCategory").value;
		//document.getElementById("demo").innerHTML = "You selected: " + categoryId;
		window.location.href = "changeSubjectCategory?subjectId=" + subjectId + "&categoryId=" + categoryId;
	}
</script>

<script type="text/javascript">
	function changeCategoryFunction(subjectId, subjectCategoryId) {
		var fieldNameElement = document.getElementById('category-' + subjectId);
		fieldNameElement.innerHTML = '<select class="form-control" id="newCategory" onchange="reloadCategoryFunction('+ subjectId +')">'+
		'<option value="0">-Choose category!-</option>'+
		'<c:forEach items="${categories}" var="category">'+
		'<c:if test="${category.id ne 0}">'+
		'<option value="${category.id}">${category.name}</option>'+
		'</c:if>'+
		'</c:forEach></select>';
	}
</script>

<script type="text/javascript">
	function changeElementsPerPageFunction(sel) {
		var elements = document.getElementById("elementsOnPage");
		//document.getElementById("demo").innerHTML = elements.options[elements.selectedIndex].value;
		window.location.href = "viewAllSubjects?elementsOnPage=" + elements.options[elements.selectedIndex].value;
	}
</script>
<p id="demo"></p>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" align="center">Please, choose category!</h4>
				<select multiple class="form-control" id="newCategory1">
					<c:forEach items="${categories}" var="category">
						<option value="${category.id}">${category.name}</option>
					</c:forEach>
				</select>
			</div>
			<div class="modal-footer">
				<p align="center">
					<button type="button" id="mybtn" id="mybtn" class="btn btn-primary"
						onClick="">Change</button>
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
		<div class="col-md-2">
			
		</div>
		<div class="col-md-8">
			<h1 align="center">Subjects:</h1>
			<div class="col-md-8">
				<form role="form" action="viewAllSubjects" method="GET">
				
					<div class="form-group col-md-8" align="center">
					<!-- 
					<input type="hidden" id="helementsOnPage" value="">
					<input type="hidden" id="hcurrentPage" value="">  
					-->
						<input type="text" class="form-control" name="searchText"
							placeholder="Search text"> <label> <input
							type="radio" name="searchOption" value="1" checked>
							subject
						</label> <label> <input type="radio" name="searchOption" value="2">
							category
						</label>
					</div>
					<div class="col-md-4">
						<button type="submit" class="btn btn-default">Search</button>
					</div>
				</form>
			</div>
			<div class="col-md-4"></div>
			<div class="col-md-12" align="right">
				<label>On page
				<select id="elementsOnPage" onchange="changeElementsPerPageFunction(this)">
					<option value="1">10</option>
					<option value="2">25</option>
					<option value="5">50</option>
				</select>
				</label>
			</div>
			<div class="col-md-12" align="center">
				<table class="table table-bordered">
					<tr align="center" class="info">
						<td class="col-md-6">Subject name</td>
						<td class="col-md-4">Category</td>
						<td class="col-md-1">Change category</td>
					</tr>
					<c:forEach items="${subjects}" var="subject">
						<tr>
							<td>${subject.name}</td>
							<td align="center">
								<div id="category-${subject.id}">
									${subject.category.name} <a type="button"
										class="btn btn-default dropdown-toggle" data-toggle="dropdown"
										href="#"
										onclick="changeCategoryFunction('${subject.id}','${subject.category.id}')">
										<span class="caret"></span>
									</a>
								</div>

							</td>
							<td align="center"><a data-toggle="modal"
								data-id="changeSubjectCategory?subjectId=${subject.id}"
								class="openModalWindow" href="#myModal"><span
									class="glyphicon glyphicon-pencil red"></span></a></td>
						</tr>
					</c:forEach>
				</table>
				<nav>
				<ul class="pagination">
					<li><a href="#">&laquo;</a></li>
					<li><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li class="active"><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#">&raquo;</a></li>
				</ul>
			</nav>
			</div>

		</div>
		<div class="col-md-2"></div>
	</div>
</div>
