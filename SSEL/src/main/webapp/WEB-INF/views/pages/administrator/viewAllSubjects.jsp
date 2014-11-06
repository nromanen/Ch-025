<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript">
	$(document)
			.on(
					"click",
					".openModalWindow",
					function() {
						var subjectId = $(this).data('id');

						document.getElementById("mybtn").onclick = function() {
							var newCategory = document
									.getElementById("newCategory").value;

							var elements = document
									.getElementById("elementsOnPage");
							if ('${searchText}' != ""
									&& '${searchOption}' != "") {
								if ('${sortBy}' != "" && '${sortMethod}' != "") {
									window.location.href = subjectId
											+ "&categoryId="
											+ newCategory
											+ "&currentPage=${currentPage}"
											+ "&elementsOnPage="
											+ elements.options[elements.selectedIndex].value
											+ "&searchText=${searchText}&searchOption=${searchOption}"
											+ "&sortBy=${sortBy}&sortMethod=${sortMethod}";
								} else {
									window.location.href = subjectId
											+ "&categoryId="
											+ newCategory
											+ "&currentPage=${currentPage}"
											+ "&elementsOnPage="
											+ elements.options[elements.selectedIndex].value
											+ "&searchText=${searchText}&searchOption=${searchOption}";
								}
							} else {
								if ('${sortBy}' != "" && '${sortMethod}' != "") {
									window.location.href = subjectId
											+ "&categoryId="
											+ newCategory
											+ "&currentPage=${currentPage}"
											+ "&elementsOnPage="
											+ elements.options[elements.selectedIndex].value
											+ "&sortBy=${sortBy}&sortMethod=${sortMethod}";
								} else {
									window.location.href = subjectId
											+ "&categoryId="
											+ newCategory
											+ "&currentPage=${currentPage}"
											+ "&elementsOnPage="
											+ elements.options[elements.selectedIndex].value;
								}
							}
						}
					});
</script>

<script type="text/javascript">
	function changeElementsPerPageFunction(sel) {
		var elements = document.getElementById("elementsOnPage");
		if ('${searchText}' != "" && '${searchOption}' != "") {
			if ('${sortBy}' != "" && '${sortMethod}' != "") {
				window.location.href = "viewAllSubjects?elementsOnPage="
						+ elements.options[elements.selectedIndex].value
						+ "&searchText=${searchText}&searchOption=${searchOption}&sortBy=${sortBy}&sortMethod=${sortMethod}";
			} else {
				window.location.href = "viewAllSubjects?elementsOnPage="
						+ elements.options[elements.selectedIndex].value
						+ "&searchText=${searchText}&searchOption=${searchOption}";
			}
		} else {
			if ('${sortBy}' != "" && '${sortMethod}' != "") {
				window.location.href = "viewAllSubjects?elementsOnPage="
						+ elements.options[elements.selectedIndex].value
						+ "&sortBy=${sortBy}&sortMethod=${sortMethod}";
			} else {
				window.location.href = "viewAllSubjects?elementsOnPage="
						+ elements.options[elements.selectedIndex].value;
			}
		}
	}
</script>

<script type="text/javascript">
	function changePageFunction(page) {
		var elements = document.getElementById("elementsOnPage");
		if ('${searchText}' != "" && '${searchOption}' != "") {
			if ('${sortBy}' != "" && '${sortMethod}' != "") {
				window.location.href = "viewAllSubjects?currentPage="
						+ page
						+ "&elementsOnPage="
						+ elements.options[elements.selectedIndex].value
						+ "&searchText=${searchText}&searchOption=${searchOption}"
						+ "&sortBy=${sortBy}&sortMethod=${sortMethod}";
			} else {
				window.location.href = "viewAllSubjects?currentPage="
						+ page
						+ "&elementsOnPage="
						+ elements.options[elements.selectedIndex].value
						+ "&searchText=${searchText}&searchOption=${searchOption}";
			}
		} else {
			if ('${sortBy}' != "" && '${sortMethod}' != "") {
				window.location.href = "viewAllSubjects?currentPage=" + page
						+ "&elementsOnPage="
						+ elements.options[elements.selectedIndex].value
						+ "&sortBy=${sortBy}&sortMethod=${sortMethod}";
			} else {
				window.location.href = "viewAllSubjects?currentPage=" + page
						+ "&elementsOnPage="
						+ elements.options[elements.selectedIndex].value;
			}
		}
	}
</script>

<script type="text/javascript">
	function searchTextFunction(page) {
		var searchText = document.getElementById("searchText").value;
		var checkedSearchRadio = $('input:radio[name=searchOption]:checked')
				.val();
		window.location.href = "viewAllSubjects?searchText=" + searchText
				+ "&searchOption=" + checkedSearchRadio;
	}
</script>

<script type="text/javascript">
	function changeSortFunction(field, method) {
		var elements = document.getElementById("elementsOnPage");
		if ('${searchText}' != "" && '${searchOption}' != "") {
			window.location.href = "viewAllSubjects?elementsOnPage="
					+ elements.options[elements.selectedIndex].value
					+ "&searchText=${searchText}&searchOption=${searchOption}"
					+ "&sortBy=" + field + "&sortMethod=" + method;
		} else {
			window.location.href = "viewAllSubjects?elementsOnPage="
					+ elements.options[elements.selectedIndex].value
					+ "&sortBy=" + field + "&sortMethod=" + method;
		}
	}
</script>

<p id="demo"></p>
<!-- Modal window -->
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
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				</p>
			</div>
		</div>
	</div>
</div>
<!-- /Modal window -->

<div class="row">
	<div class="col-lg-12">

		<!-- Message block -->
		<c:if test="${not empty successMessage}">
			<div class="alert alert-success alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<p align="center">${successMessage}</p>
			</div>
		</c:if>

		<c:if test="${not empty errorMessage}">
			<div class="alert alert-danger alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<p align="center">${errorMessage}</p>
			</div>
		</c:if>
		<!-- /Message block -->

		<div class="col-md-2"></div>
		<div class="col-md-8">
			<h1 align="center">Subjects:</h1>

			<!-- search block -->
			<div class="col-md-12">
				<div class="input-group col-md-6">
					<div class="input-group-addon">
						<a href="#" data-toggle="dropdown" class="dropdown"> <span
							class="glyphicon glyphicon-cog"></span>
						</a>
						<ul class="dropdown-menu">
							<li><label> <input type="radio" name="searchOption"
									value="all" checked>All
							</label></li>
							<li><label> <input type="radio" name="searchOption"
									value="subject">Subject
							</label></li>
							<li><label> <input type="radio" name="searchOption"
									value="category">Category
							</label></li>
						</ul>
					</div>
					<input type="text" class="form-control" id="searchText"
						placeholder="Search text"> <a href="#"
						class="input-group-addon" onclick="searchTextFunction()"><span
						class="glyphicon glyphicon-search"></span></a>
				</div>
				<!-- /search block-->

				<!-- Elements on page block -->
				<div align="right">
					<label>On page <c:set var="onPage">1,3,5,10,25,50,100</c:set>
						<select id="elementsOnPage"
						onchange="changeElementsPerPageFunction(this)">
							<c:forTokens items="${onPage}" delims="," var="element">
								<option value="${element}"
									<c:if test="${elementsOnPage eq element}">
				selected
				</c:if>>${element}</option>
							</c:forTokens>
					</select>
					</label>
				</div>
				<!-- /Elements on page block -->

			</div>
			<div class="col-md-12" align="center">
				<table class="table table-bordered">
					<tr align="center" class="info">
						<td class="col-md-6">
							<table class="col-md-12">
								<tr align="right">
									<td rowspan="2" align="center">Subject name</td>
									<td><a href="#"
										onclick="changeSortFunction('subject','desc')"><span
											data-id="subject" data-method="ASC"
											class="glyphicon glyphicon-chevron-up"></span></a></td>
								</tr>
								<tr align="right">
									<td><a href="#"
										onclick="changeSortFunction('subject','asc')"><span
											class="glyphicon glyphicon-chevron-down"></span></a></td>
								</tr>
							</table>
						</td>
						<td class="col-md-4">
							<table class="col-md-12">
								<tr align="right">
									<td rowspan="2" align="center">Category</td>
									<td><a href="#"
										onclick="changeSortFunction('category','desc')"><span
											class="glyphicon glyphicon-chevron-up"></span></a></td>
								</tr>
								<tr align="right">
									<td><a href="#"
										onclick="changeSortFunction('category','asc')"><span
											class="glyphicon glyphicon-chevron-down"></span></a></td>
								</tr>
							</table>
						</td>
					</tr>
					<c:forEach items="${subjects}" var="subject">
						<tr>
							<td>${subject.name}</td>
							<td align="center">
								<div id="category-${subject.id}">
									${subject.category.name} <a type="button" data-toggle="modal"
										data-id="changeSubjectCategory?subjectId=${subject.id}"
										data-category-id="${subject.category.id}"
										class="btn btn-default openModalWindow" data-toggle="dropdown"
										href="#myModal"> <span class="caret"></span>
									</a>
								</div>

							</td>
						</tr>
					</c:forEach>
				</table>

				<!-- Pagination block -->
				<c:if test="${pagesCount gt 1}">
					<nav>
						<ul class="pagination">
							<li><a href="#">&laquo;</a></li>
							<c:forEach var="pageNumber" begin="1" end="${pagesCount}">
								<li
									<c:if test="${pageNumber eq currentPage}">class="active"</c:if>>
									<a href="#" onclick="changePageFunction('${pageNumber}')">${pageNumber}</a>
								</li>
							</c:forEach>
							<!-- <li class="active"><a href="#">3</a></li>  -->
							<li><a href="#">&raquo;</a></li>
						</ul>
					</nav>
				</c:if>
				<!-- /Pagination block -->

			</div>

		</div>
		<div class="col-md-2"></div>
	</div>
</div>

