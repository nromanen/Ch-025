<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script>
	$(function() {
		$(".sidebar").find($('a[href="viewAllSubjects"]')).addClass("active");
	});
</script>

<script type="text/javascript">
	$(document)
			.on(
					"click",
					".openModalWindow",
					function() {
						var subjectId = $(this).data('id');
						var categoryId = $(this).data('category-id');

						$
								.ajax({
									type : "POST",
									url : "getCategory",
									data : {
										"categoryId" : categoryId
									},
									error : function(xhr) {
										alert(xhr.statusText);
									},
									success : function(str) {

										obj = JSON.parse(str);
										$("#newCategory").empty();
										for (i = 0; i < obj.categories.length; i++) {
											$("#newCategory")
													.append(
															'<option value="'+obj.categories[i].categoryId+'">'
																	+ obj.categories[i].categoryName
																	+ '</option>');
										}
									}
								});

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
		var newPage = elements.options[elements.selectedIndex].value;

		if ('${searchText}' != "" && '${searchOption}' != "") {
			if ('${sortBy}' != "" && '${sortMethod}' != "") {
				window.location.href = "c"
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
		var div = document.createElement("div");
		div.innerHTML = searchText;
		searchText = div.textContent || div.innerText || "";
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


<!-- Modal window -->
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
					<spring:message code="label.choose_category" />
				</h4>
				<p id="demo"></p>
				<select multiple class="form-control" id="newCategory"></select>
			</div>
			<div class="modal-footer">
				<p align="center">
					<button type="button" id="mybtn" id="mybtn" class="btn btn-primary"
						onClick="">
						<spring:message code="label.change" />
					</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<spring:message code="label.cancel" />
					</button>
				</p>
			</div>
		</div>
	</div>
</div>
<!-- /Modal window -->

<div class="row">
	<div class="col-md-12">
		<!-- Message block -->
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
		<!-- /Message block -->
	</div>
</div>

<br>
<div class="row">
	<div class="col-md-10 col-md-offset-1">

		<div class="row">

			<!-- search block -->
			<div class="col-md-5" align="left">
				<div class="input-group admTopM">
					<div class="input-group-addon">
						<a href="#" data-toggle="dropdown" class="dropdown"> <span
							class="glyphicon glyphicon-cog"></span>
						</a>
						<ul class="dropdown-menu">
							<li><label> <input type="radio" name="searchOption"
									value="all" checked> <spring:message code="label.all" />
							</label></li>
							<li><label> <input type="radio" name="searchOption"
									value="subject"> <spring:message code="label.subject" />
							</label></li>
							<li><label> <input type="radio" name="searchOption"
									value="category"> <spring:message
										code="label.admin_category" />
							</label></li>
						</ul>
					</div>
					<c:choose>
						<c:when test="${not empty searchText}">
							<input type="text" class="form-control" id="searchText"
								value="${searchText}"
								placeholder="<spring:message code='label.search_text' />">
						</c:when>
						<c:otherwise>
							<input type="text" class="form-control" id="searchText"
								placeholder="<spring:message code='label.search_text' />">
						</c:otherwise>
					</c:choose>
					<a href="#" class="input-group-addon"
						onclick="searchTextFunction()"><span
						class="glyphicon glyphicon-search"></span></a>
				</div>
			</div>
			<!-- /search block-->

			<!-- Pagination block -->
			<div class="col-md-7" align="right">
				<c:if test="${pagesCount gt 1}">
					<c:set var="paginationLarge" value="2" />
					<nav>
						<ul class="pagination">
							<c:choose>
								<c:when test="${currentPage eq 1}">
									<li class="disabled"><a href="#"><spring:message
												code="label.previous" /></a></li>
								</c:when>
								<c:otherwise>
									<li><a href="#"
										onclick="changePageFunction('${currentPage - 1}')"><spring:message
												code="label.previous" /></a></li>
								</c:otherwise>
							</c:choose>
							<c:forEach var="pageNumber" begin="1" end="${pagesCount}">
								<c:choose>
									<c:when
										test="${(currentPage-paginationLarge) le pageNumber and (currentPage + paginationLarge) ge pageNumber}">
										<c:choose>
											<c:when test="${pageNumber eq currentPage}">
												<li class="active"><a href="#">${pageNumber}</a></li>
											</c:when>
											<c:otherwise>
												<li><a href="#"
													onclick="changePageFunction('${pageNumber}')">${pageNumber}</a>
												</li>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:when test="${pageNumber eq 1}">
										<li><a href="#"
											onclick="changePageFunction('${pageNumber}')">${pageNumber}</a>
										</li>
									</c:when>
									<c:when test="${pageNumber eq pagesCount}">
										<li><a href="#"
											onclick="changePageFunction('${pageNumber}')">${pageNumber}</a>
										</li>
									</c:when>
									<c:when
										test="${pageNumber eq 2 and currentPage gt (paginationLarge + 2)}">
										<li><a href="#">...</a></li>
									</c:when>
									<c:when
										test="${pageNumber eq (pagesCount - 1) and currentPage lt (pagesCount -paginationLarge)}">
										<li><a href="#">...</a></li>
									</c:when>
								</c:choose>

							</c:forEach>
							<c:choose>
								<c:when test="${currentPage eq pagesCount}">
									<li class="disabled"><a href="#"><spring:message
												code="label.next" /></a></li>
								</c:when>
								<c:otherwise>
									<li><a href="#"
										onclick="changePageFunction('${currentPage+1}')"><spring:message
												code="label.next" /></a></li>
								</c:otherwise>
							</c:choose>
						</ul>
					</nav>
				</c:if>
			</div>
			<!-- /Pagination block -->
		</div>

		<!-- Elements on page block -->
		<div class="row">
			<div class="col-md-12" align="left">
				<c:if test="${not empty subjects}">
					<label><spring:message code="label.records_per_page" /><c:set var="onPage">1,3,5,10,25,50,100</c:set>
						<select id="elementsOnPage"
						onchange="changeElementsPerPageFunction(this)">
							<c:forTokens items="${onPage}" delims="," var="element">
								<c:choose>
									<c:when test="${elementsOnPage eq element}">
										<option value="${element}" selected>${element}</option>
									</c:when>
									<c:otherwise>
										<option value="${element}">${element}</option>
									</c:otherwise>
								</c:choose>
							</c:forTokens>
					</select>
					</label>
				</c:if>
			</div>
		</div>
		<!-- /Elements on page block -->
		<div class="row">
			<div class="col-md-12" align="center">
				<c:if test="${not empty subjects}">
					<table class="table table-bordered">
						<thead>
							<tr align="center" class="info">
								<th class="col-md-7">
									<table class="col-md-12 centerTh">
										<thead>
											<tr>
												<th class="col-md-10" align="center"><spring:message
														code="label.subject_name" /></th>
												<th class="col-md-2" align="center">
												<c:choose>
														<c:when
															test="${sortBy eq 'subject' and sortMethod eq 'asc'}">
															<a href="#"
																onclick="changeSortFunction('subject','desc')"><span
																class="fa fa-sort-alpha-asc fa-lg"></span></a>
														</c:when>
														<c:when
															test="${sortBy eq 'subject' and sortMethod eq 'desc'}">
															<a href="#" onclick="changeSortFunction('subject','asc')"><span
																class="fa fa-sort-alpha-desc fa-lg"></span></a>
														</c:when>
														<c:otherwise>
															<a href="#" onclick="changeSortFunction('subject','asc')"><span
																class="fa fa-sort fa-lg"></span></a>
														</c:otherwise>
												</c:choose>
													</th>
											</tr>
										</thead>
									</table>
								</th>
								<th class="col-md-5">
									<table class="col-md-12 centerTh">
										<thead>
											<tr>
												<th class="col-md-10" align="center"><spring:message
														code="label.admin_category" /></th>
												<th class="col-md-2" align="center"><c:choose>
														<c:when
															test="${sortBy eq 'category' and sortMethod eq 'asc'}">
															<a href="#"
																onclick="changeSortFunction('category','desc')"><span
																class="fa fa-sort-alpha-asc fa-lg"></span></a>
														</c:when>
														<c:when
															test="${sortBy eq 'category' and sortMethod eq 'desc'}">
															<a href="#"
																onclick="changeSortFunction('category','asc')"><span
																class="fa fa-sort-alpha-desc fa-lg"></span></a>
														</c:when>
														<c:otherwise>
															<a href="#"
																onclick="changeSortFunction('category','asc')"><span
																class="fa fa-sort fa-lg"></span></a>
														</c:otherwise>
													</c:choose></th>
											</tr>
										</thead>
									</table>
								</th>
							</tr>
						</thead>
						<c:forEach items="${subjects}" var="subject">
							<tr>
								<td>${subject.name}</td>
								<td class="leftBorder">
									<table class="col-md-12">
										<tr>
											<td>${subject.category.name}</td>
											<td class="col-md-2"><a type="button"
												data-toggle="modal"
												data-id="changeSubjectCategory?subjectId=${subject.id}"
												data-category-id="${subject.category.id}"
												class="btn btn-default openModalWindow"
												data-toggle="dropdown" href="#myModal"> <span
													class="caret"></span></a></td>
										</tr>
									</table>
								</td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
			</div>
		</div>

		<!-- Pagination block -->
		<%-- <c:set var="paginationLarge" value="2" /> --%>
		<div class="row">
			<div class="col-md-12" align="right">
				<c:if test="${pagesCount gt 1}">
					<nav>
						<ul class="pagination">
							<c:choose>
								<c:when test="${currentPage eq 1}">
									<li class="disabled"><a href="#"><spring:message
												code="label.previous" /></a></li>
								</c:when>
								<c:otherwise>
									<li><a href="#"
										onclick="changePageFunction('${currentPage - 1}')"><spring:message
												code="label.previous" /></a></li>
								</c:otherwise>
							</c:choose>
							<c:forEach var="pageNumber" begin="1" end="${pagesCount}">
								<c:choose>
									<c:when
										test="${(currentPage-paginationLarge) le pageNumber and (currentPage + paginationLarge) ge pageNumber}">
										<c:choose>
											<c:when test="${pageNumber eq currentPage}">
												<li class="active"><a href="#">${pageNumber}</a></li>
											</c:when>
											<c:otherwise>
												<li><a href="#"
													onclick="changePageFunction('${pageNumber}')">${pageNumber}</a>
												</li>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:when test="${pageNumber eq 1}">
										<li><a href="#"
											onclick="changePageFunction('${pageNumber}')">${pageNumber}</a>
										</li>
									</c:when>
									<c:when test="${pageNumber eq pagesCount}">
										<li><a href="#"
											onclick="changePageFunction('${pageNumber}')">${pageNumber}</a>
										</li>
									</c:when>
									<c:when
										test="${pageNumber eq 2 and currentPage gt (paginationLarge + 2)}">
										<li><a href="#">...</a></li>
									</c:when>
									<c:when
										test="${pageNumber eq (pagesCount - 1) and currentPage lt (pagesCount -paginationLarge)}">
										<li><a href="#">...</a></li>
									</c:when>
								</c:choose>

							</c:forEach>
							<c:choose>
								<c:when test="${currentPage eq pagesCount}">
									<li class="disabled"><a href="#"><spring:message
												code="label.next" /></a></li>
								</c:when>
								<c:otherwise>
									<li><a href="#"
										onclick="changePageFunction('${currentPage+1}')"><spring:message
												code="label.next" /></a></li>
								</c:otherwise>
							</c:choose>
						</ul>
					</nav>
				</c:if>
			</div>
		</div>
		<!-- /Pagination block -->

	</div>
</div>