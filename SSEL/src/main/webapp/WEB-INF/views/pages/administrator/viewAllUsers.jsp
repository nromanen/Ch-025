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
						var userId = $(this).data('id');

						document.getElementById("mybtn").onclick = function() {
							var newRole = document.getElementById("newRole").value;

							var elements = document
									.getElementById("elementsOnPage");
							if ('${searchText}' != ""
									&& '${searchOption}' != "") {
								if ('${sortBy}' != "" && '${sortMethod}' != "") {
									window.location.href = userId
											+ "&roleId="
											+ newRole
											+ "&currentPage=${currentPage}"
											+ "&elementsOnPage="
											+ elements.options[elements.selectedIndex].value
											+ "&searchText=${searchText}&searchOption=${searchOption}"
											+ "&sortBy=${sortBy}&sortMethod=${sortMethod}";
								} else {
									window.location.href = userId
											+ "&roleId="
											+ newRole
											+ "&currentPage=${currentPage}"
											+ "&elementsOnPage="
											+ elements.options[elements.selectedIndex].value
											+ "&searchText=${searchText}&searchOption=${searchOption}";
								}
							} else {
								if ('${sortBy}' != "" && '${sortMethod}' != "") {
									window.location.href = userId
											+ "&roleId="
											+ newRole
											+ "&currentPage=${currentPage}"
											+ "&elementsOnPage="
											+ elements.options[elements.selectedIndex].value
											+ "&sortBy=${sortBy}&sortMethod=${sortMethod}";
								} else {
									window.location.href = userId
											+ "&roleId="
											+ newRole
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
				window.location.href = "viewAllUsers?elementsOnPage="
						+ elements.options[elements.selectedIndex].value
						+ "&searchText=${searchText}&searchOption=${searchOption}&sortBy=${sortBy}&sortMethod=${sortMethod}";
			} else {
				window.location.href = "viewAllUsers?elementsOnPage="
						+ elements.options[elements.selectedIndex].value
						+ "&searchText=${searchText}&searchOption=${searchOption}";
			}
		} else {
			if ('${sortBy}' != "" && '${sortMethod}' != "") {
				window.location.href = "viewAllUsers?elementsOnPage="
						+ elements.options[elements.selectedIndex].value
						+ "&sortBy=${sortBy}&sortMethod=${sortMethod}";
			} else {
				window.location.href = "viewAllUsers?elementsOnPage="
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
				window.location.href = "viewAllUsers?currentPage="
						+ page
						+ "&elementsOnPage="
						+ elements.options[elements.selectedIndex].value
						+ "&searchText=${searchText}&searchOption=${searchOption}"
						+ "&sortBy=${sortBy}&sortMethod=${sortMethod}";
			} else {
				window.location.href = "viewAllUsers?currentPage="
						+ page
						+ "&elementsOnPage="
						+ elements.options[elements.selectedIndex].value
						+ "&searchText=${searchText}&searchOption=${searchOption}";
			}
		} else {
			if ('${sortBy}' != "" && '${sortMethod}' != "") {
				window.location.href = "viewAllUsers?currentPage=" + page
						+ "&elementsOnPage="
						+ elements.options[elements.selectedIndex].value
						+ "&sortBy=${sortBy}&sortMethod=${sortMethod}";
			} else {
				window.location.href = "viewAllUsers?currentPage=" + page
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
		window.location.href = "viewAllUsers?searchText=" + searchText
				+ "&searchOption=" + checkedSearchRadio;
	}
</script>

<script type="text/javascript">
	function changeSortFunction(field, method) {
		var elements = document.getElementById("elementsOnPage");
		if ('${searchText}' != "" && '${searchOption}' != "") {
			window.location.href = "viewAllUsers?elementsOnPage="
					+ elements.options[elements.selectedIndex].value
					+ "&searchText=${searchText}&searchOption=${searchOption}"
					+ "&sortBy=" + field + "&sortMethod=" + method;
		} else {
			window.location.href = "viewAllUsers?elementsOnPage="
					+ elements.options[elements.selectedIndex].value
					+ "&sortBy=" + field + "&sortMethod=" + method;
		}
	}
</script>

<script type="text/javascript">
	function changeStatusFunction(userId) {
		if ('${searchText}' != "" && '${searchOption}' != "") {
			if ('${sortBy}' != "" && '${sortMethod}' != "") {
				window.location.href = "changeUserStatus?userId="
						+ userId
						+ "&currentPage=${currentPage}"
						+ "&elementsOnPage=${elementsOnPage}"
						+ "&searchText=${searchText}&searchOption=${searchOption}"
						+ "&sortBy=${sortBy}&sortMethod=${sortMethod}";
			} else {
				window.location.href = "changeUserStatus?userId="
						+ userId
						+ "&currentPage=${currentPage}"
						+ "&elementsOnPage=${elementsOnPage}"
						+ "&searchText=${searchText}&searchOption=${searchOption}";
			}
		} else {
			if ('${sortBy}' != "" && '${sortMethod}' != "") {
				window.location.href = "changeUserStatus?userId=" + userId
						+ "&currentPage=${currentPage}"
						+ "&elementsOnPage=${elementsOnPage}"
						+ "&sortBy=${sortBy}&sortMethod=${sortMethod}";
			} else {
				window.location.href = "changeUserStatus?userId=" + userId
						+ "&currentPage=${currentPage}"
						+ "&elementsOnPage=${elementsOnPage}";
			}
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
					<span aria-hidden="true">&times;</span><span class="sr-only">
						<spring:message code="label.close" />
					</span>
				</button>
				<h4 class="modal-title" align="center">
					<spring:message code="label.choose_role" />
				</h4>
				<select multiple class="form-control" id="newRole">

					<c:forEach items="${roles}" var="role">
						<option value="${role.id}">${role.role}</option>
					</c:forEach>

				</select>
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
	<div class="col-lg-12">

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

		<div class="col-md-12">
			<h1 align="center">
				<spring:message code="label.users" />
				:
			</h1>

			<!-- search block -->
			<div class="col-md-12">
				<div class="input-group col-md-6">
					<div class="input-group-addon">
						<a href="#" data-toggle="dropdown" class="dropdown"> <span
							class="glyphicon glyphicon-cog"></span>
						</a>
						<ul class="dropdown-menu">
							<li><label> <input type="radio" name="searchOption"
									value="all" checked> <spring:message code="label.all" />
							</label></li>
							<li><label> <input type="radio" name="searchOption"
									value="userFirstName"> <spring:message
										code="label.user_name" />
							</label></li>
							<li><label> <input type="radio" name="searchOption"
									value="userLastName"> <spring:message
										code="label.user_last_name" />
							</label></li>
							<li><label> <input type="radio" name="searchOption"
									value="role"> <spring:message code="label.role" />
							</label></li>
						</ul>
					</div>
					<input type="text" class="form-control" id="searchText"
						placeholder="<spring:message code='label.search_text' />">
					<a href="#" class="input-group-addon"
						onclick="searchTextFunction()"><span
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
						<td class="col-md-3">
							<table class="col-md-12">
								<tr align="right">
									<td rowspan="2" align="center"><spring:message
											code="label.user_email" /></td>
									<td><c:if
											test="${sortBy ne 'email' or sortMethod ne 'desc'}">
											<a href="#" onclick="changeSortFunction('email','desc')"><span
												data-id="user" data-method="ASC"
												class="glyphicon glyphicon-chevron-up"></span></a>
										</c:if></td>
								</tr>
								<tr align="right">
									<td><c:if
											test="${sortBy ne 'email' or sortMethod ne 'asc'}">
											<a href="#" onclick="changeSortFunction('email','asc')"><span
												class="glyphicon glyphicon-chevron-down"></span></a>
										</c:if></td>
								</tr>
							</table>
						</td>
						<td class="col-md-2">
							<table class="col-md-12">
								<tr align="right">
									<td rowspan="2" align="center"><spring:message
											code="label.user_name" /></td>
									<td><c:if
											test="${sortBy ne 'userName' or sortMethod ne 'desc'}">
											<a href="#" onclick="changeSortFunction('userName','desc')"><span
												data-id="user" data-method="ASC"
												class="glyphicon glyphicon-chevron-up"></span></a>
										</c:if></td>
								</tr>
								<tr align="right">
									<td><c:if
											test="${sortBy ne 'userName' or sortMethod ne 'asc'}">
											<a href="#" onclick="changeSortFunction('userName','asc')"><span
												class="glyphicon glyphicon-chevron-down"></span></a>
										</c:if></td>
								</tr>
							</table>
						</td>
						<td class="col-md-2">
							<table class="col-md-12">
								<tr align="right">
									<td rowspan="2" align="center"><spring:message
											code="label.user_last_name" /></td>
									<td><c:if
											test="${sortBy ne 'userLastName' or sortMethod ne 'desc'}">
											<a href="#"
												onclick="changeSortFunction('userLastName','desc')"><span
												data-id="user" data-method="ASC"
												class="glyphicon glyphicon-chevron-up"></span></a>
										</c:if></td>
								</tr>
								<tr align="right">
									<td><c:if
											test="${sortBy ne 'userLastName' or sortMethod ne 'asc'}">
											<a href="#"
												onclick="changeSortFunction('userLastName','asc')"><span
												class="glyphicon glyphicon-chevron-down"></span></a>
										</c:if></td>
								</tr>
							</table>
						</td>
						<td class="col-md-2">
							<table class="col-md-12">
								<tr align="right">
									<td rowspan="2" align="center"><spring:message
											code="label.expired" /></td>
									<td><c:if
											test="${sortBy ne 'expired' or sortMethod ne 'desc'}">
											<a href="#" onclick="changeSortFunction('expired','desc')"><span
												data-id="user" data-method="ASC"
												class="glyphicon glyphicon-chevron-up"></span></a>
										</c:if></td>
								</tr>
								<tr align="right">
									<td><c:if
											test="${sortBy ne 'expired' or sortMethod ne 'asc'}">
											<a href="#" onclick="changeSortFunction('expired','asc')"><span
												class="glyphicon glyphicon-chevron-down"></span></a>
										</c:if></td>
								</tr>
							</table>
						</td>
						<td class="col-md-2">
							<table class="col-md-12">
								<tr align="right">
									<td rowspan="2" align="center"><spring:message
											code="label.role" /></td>
									<td><c:if
											test="${sortBy ne 'role' or sortMethod ne 'desc'}">
											<a href="#" onclick="changeSortFunction('role','desc')"><span
												class="glyphicon glyphicon-chevron-up"></span></a>
										</c:if></td>
								</tr>
								<tr align="right">
									<td><c:if
											test="${sortBy ne 'role' or sortMethod ne 'asc'}">
											<a href="#" onclick="changeSortFunction('role','asc')"><span
												class="glyphicon glyphicon-chevron-down"></span></a>
										</c:if></td>
								</tr>
							</table>
						</td>
						<td class="col-md-1">
							<table class="col-md-12">
								<tr align="right">
									<td rowspan="2" align="center"><spring:message
											code="label.blocked" /></td>
									<td><c:if
											test="${sortBy ne 'blocked' or sortMethod ne 'desc'}">
											<a href="#" onclick="changeSortFunction('blocked','desc')"><span
												data-id="user" data-method="ASC"
												class="glyphicon glyphicon-chevron-up"></span></a>
										</c:if></td>
								</tr>
								<tr align="right">
									<td><c:if
											test="${sortBy ne 'blocked' or sortMethod ne 'asc'}">
											<a href="#" onclick="changeSortFunction('blocked','asc')"><span
												class="glyphicon glyphicon-chevron-down"></span></a>
										</c:if></td>
								</tr>
							</table>
						</td>
					</tr>
					<c:forEach items="${users}" var="user">
						<tr>
							<td>${user.email}</td>
							<td>${user.firstName}</td>
							<td>${user.lastName}</td>
							<td align="center"><fmt:formatDate pattern="dd-MM-yyyy"
									value="${user.expired}" /></td>
							<td>
								<div id="category-${user.id}">
									${user.role.role} <a type="button" data-toggle="modal"
										data-id="changeUserRole?userId=${user.id}"
										data-category-id="${user.role.id}"
										class="btn btn-default openModalWindow" data-toggle="dropdown"
										href="#myModal"> <span class="caret"></span>
									</a>
								</div>

							</td>
							<td align="center"><c:if test="${user.blocked eq false}">
									<a href="#" onclick="changeStatusFunction('${user.id}')"><span
										class="glyphicon glyphicon-unchecked green"></span></a>
								</c:if> <c:if test="${user.blocked eq true}">
									<a href="#" onclick="changeStatusFunction('${user.id}')"><span
										class="glyphicon glyphicon-check red"></span></a>
								</c:if></td>
						</tr>
					</c:forEach>
				</table>

				<!-- Pagination block -->
				<c:if test="${pagesCount gt 1}">
					<nav>
						<ul class="pagination">
							<c:choose>
								<c:when test="${currentPage eq 1}">
									<li class="disabled"><a href="#">|&laquo;</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="#" onclick="changePageFunction('1')">|&laquo;</a></li>
								</c:otherwise>
							</c:choose>
							<c:forEach var="pageNumber" begin="1" end="${pagesCount}">
								<c:if
									test="${(currentPage-5) le pageNumber and (currentPage + 5) ge pageNumber}">
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
								</c:if>
							</c:forEach>
							<c:choose>
								<c:when test="${currentPage eq pagesCount}">
									<li class="disabled"><a href="#">&raquo;|</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="#"
										onclick="changePageFunction('${pagesCount}')">&raquo;|</a></li>
								</c:otherwise>
							</c:choose>
						</ul>
					</nav>
				</c:if>
				<!-- /Pagination block -->
			</div>
		</div>
	</div>
</div>
