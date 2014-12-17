<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script>
	$(function() {
		//$(".sidebar").find("a").addClass("active");
		$(".sidebar").find($('a[href="viewAllUsers"]')).addClass("active");
	});
</script>

<script type="text/javascript">
	$(document)
			.on(
					"click",
					".openModalWindow",
					function() {
						var userId = $(this).data('id');

						var roleId = $(this).data('role-id');
						//alert(categoryId);
						$.ajax({
							type : "POST",
							url : "getRole",
							data : {
								"roleId" : roleId
							},
							error : function(xhr) {
								alert(xhr.statusText);
							},
							success : function(str) {

								obj = JSON.parse(str);
								$("#newRole").empty();
								for (i = 0; i < obj.roles.length; i++) {
									$("#newRole").append(
											'<option value="'+obj.roles[i].roleId+'">'
													+ obj.roles[i].roleName
													+ '</option>');
								}
							}
						});

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
	function searchTextFunction() {
		var searchText = document.getElementById("searchText").value;
		var div = document.createElement("div");
		div.innerHTML = searchText;
		searchText = div.textContent || div.innerText || "";
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

<script type="text/javascript">
function changeRoleFunction(userId,newRole) {
	var elements = document
	.getElementById("elementsOnPage");

	if ('${searchText}' != ""
			&& '${searchOption}' != "") {
		if ('${sortBy}' != "" && '${sortMethod}' != "") {
			window.location.href = "changeUserRole?userId=" + userId
					+ "&roleId="
					+ newRole
					+ "&currentPage=${currentPage}"
					+ "&elementsOnPage="
					+ elements.options[elements.selectedIndex].value
					+ "&searchText=${searchText}&searchOption=${searchOption}"
					+ "&sortBy=${sortBy}&sortMethod=${sortMethod}";
		} else {
			window.location.href = "changeUserRole?userId=" + userId
					+ "&roleId="
					+ newRole
					+ "&currentPage=${currentPage}"
					+ "&elementsOnPage="
					+ elements.options[elements.selectedIndex].value
					+ "&searchText=${searchText}&searchOption=${searchOption}";
		}
	} else {
		if ('${sortBy}' != "" && '${sortMethod}' != "") {
			window.location.href = "changeUserRole?userId=" + userId
					+ "&roleId="
					+ newRole
					+ "&currentPage=${currentPage}"
					+ "&elementsOnPage="
					+ elements.options[elements.selectedIndex].value
					+ "&sortBy=${sortBy}&sortMethod=${sortMethod}";
		} else {
			window.location.href = "changeUserRole?userId=" + userId
					+ "&roleId="
					+ newRole
					+ "&currentPage=${currentPage}"
					+ "&elementsOnPage="
					+ elements.options[elements.selectedIndex].value;
		}
	}
}

	$(document)
			.on(
					"click",
					".openRolesBlock",
					function() {
						var userId = $(this).data('id');

						var roleId = $(this).data('role-id');
						$.ajax({
							type : "POST",
							url : "getRole",
							data : {
								"roleId" : roleId
							},
							error : function(xhr) {
								alert(xhr.statusText);
							},
							success : function(str) {

								obj = JSON.parse(str);
								$(".changeUlRole ul").empty();
								for (i = 0; i < obj.roles.length; i++) {
									$(".changeUlRole ul").append('<li><a href="#" onclick="changeRoleFunction('+userId+','
											+ obj.roles[i].roleId+')">'
											+ obj.roles[i].roleName + '</a></li>');
								}
							}
						});
					});
</script>

<script type="text/javascript">
$(document).ready(function () {
    $('#searchText').on('input',function () {
if (('${searchText}' == "") || ('${searchText}' != $("#searchText").val())) {
	setIconClassFunction("search");
} else {
	setIconClassFunction("delete");
}
    });
});
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
				<select multiple class="form-control" id="newRole"></select>
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
			<div class="alert alert-success alert-dismissible alertBlock" role="alert">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">&times;</span><span class="sr-only"><spring:message
							code="label.close" /></span>
				</button>
				<p align="center">${successMessage}</p>
			</div>
		</c:if>

		<c:if test="${not empty errorMessage}">
			<div class="alert alert-danger alert-dismissible alertBlock" role="alert">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">&times;</span><span class="sr-only"><spring:message
							code="label.close" /></span>
				</button>
				<p align="center">${errorMessage}</p>
			</div>
		</c:if>
		<!-- /Message block -->
		<div class="col-md-12">
			<div class="row">
			<br>

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
						<c:choose>
						<c:when test="${not empty searchText}">
							<input type="text" maxlength="30" class="form-control" id="searchText"
								value="${searchText}"
								placeholder="<spring:message code='label.search_text' />">
								<div class="input-group-addon"><a href="#" id="searchIcon" class="glyphicon glyphicon-remove red"
						onclick="clearTextFunction()"></a></div>
						</c:when>
						<c:otherwise>
							<input type="text" maxlength="30" class="form-control" id="searchText" name="searchText"
								placeholder="<spring:message code='label.search_text' />">
								<div class="input-group-addon">
								<a href="#" id="searchIcon" class="glyphicon glyphicon-search"
									onclick="searchTextFunction()"></a>
							</div>
						</c:otherwise>
					</c:choose>
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
					<c:if test="${not empty users}">
						<label><spring:message code="label.records_per_page" />
							<c:set var="onPage">10,25,50,100</c:set> <select
							id="elementsOnPage"
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
						</select> </label>
					</c:if>
				</div>
			</div>
			<!-- /Elements on page block -->

		</div>
		<div class="col-md-12 centerTh" align="center">
			<c:if test="${not empty users}">
				<table class="table table-bordered">
					<tr class="info" valign="middle">
						<th class="col-md-3" valign="middle">
							<table class="col-md-12 centerTh">
								<thead>
									<tr>
										<th class="col-md-10" align="center"><spring:message
												code="label.user_email" /></th>
										<th class="col-md-2" align="center"><c:choose>
												<c:when test="${sortBy eq 'email' and sortMethod eq 'asc'}">
													<a href="#" onclick="changeSortFunction('email','desc')"><span
														class="fa fa-sort-alpha-asc fa-lg"></span></a>
												</c:when>
												<c:when test="${sortBy eq 'email' and sortMethod eq 'desc'}">
													<a href="#" onclick="changeSortFunction('email','asc')"><span
														class="fa fa-sort-alpha-desc fa-lg"></span></a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="changeSortFunction('email','asc')"><span
														class="fa fa-sort fa-lg"></span></a>
												</c:otherwise>
											</c:choose></th>
									</tr>
								</thead>
							</table>
						</th>
						<th class="col-md-2">
							<table class="col-md-12 centerTh">
								<thead>
									<tr>
										<th class="col-md-10" align="center"><spring:message
												code="label.user_name" /></th>
										<th class="col-md-2" align="center"><c:choose>
												<c:when
													test="${sortBy eq 'userName' and sortMethod eq 'asc'}">
													<a href="#" onclick="changeSortFunction('userName','desc')"><span
														class="fa fa-sort-alpha-asc fa-lg"></span></a>
												</c:when>
												<c:when
													test="${sortBy eq 'userName' and sortMethod eq 'desc'}">
													<a href="#" onclick="changeSortFunction('userName','asc')"><span
														class="fa fa-sort-alpha-desc fa-lg"></span></a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="changeSortFunction('userName','asc')"><span
														class="fa fa-sort fa-lg"></span></a>
												</c:otherwise>
											</c:choose></th>
									</tr>
								</thead>
							</table>
						</th>
						<th class="col-md-2">
							<table class="col-md-12 centerTh">
								<thead>
									<tr>
										<th class="col-md-10" align="center"><spring:message
												code="label.user_last_name" /></th>
										<th class="col-md-2" align="center"><c:choose>
												<c:when
													test="${sortBy eq 'userLastName' and sortMethod eq 'asc'}">
													<a href="#"
														onclick="changeSortFunction('userLastName','desc')"><span
														class="fa fa-sort-alpha-asc fa-lg"></span></a>
												</c:when>
												<c:when
													test="${sortBy eq 'userLastName' and sortMethod eq 'desc'}">
													<a href="#"
														onclick="changeSortFunction('userLastName','asc')"><span
														class="fa fa-sort-alpha-desc fa-lg"></span></a>
												</c:when>
												<c:otherwise>
													<a href="#"
														onclick="changeSortFunction('userLastName','asc')"><span
														class="fa fa-sort fa-lg"></span></a>
												</c:otherwise>
											</c:choose></th>
									</tr>
								</thead>
							</table>
						</th>
						<th class="col-md-2">
							<table class="col-md-12 centerTh">
								<thead>
									<tr align="center">
										<th class="col-md-10" align="center"><spring:message
												code="label.expired" /></th>
										<th class="col-md-2" align="center"><c:choose>
												<c:when
													test="${sortBy eq 'expired' and sortMethod eq 'asc'}">
													<a href="#" onclick="changeSortFunction('expired','desc')"><span
														class="fa fa-sort-numeric-asc fa-lg"></span></a>
												</c:when>
												<c:when
													test="${sortBy eq 'expired' and sortMethod eq 'desc'}">
													<a href="#" onclick="changeSortFunction('expired','asc')"><span
														class="fa fa-sort-numeric-desc fa-lg"></span></a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="changeSortFunction('expired','asc')"><span
														class="fa fa-sort fa-lg"></span></a>
												</c:otherwise>
											</c:choose></th>
									</tr>
								</thead>
							</table>
						</th>
						<th class="col-md-2">
							<table class="col-md-12 centerTh">
								<thead>
									<tr>
										<th class="col-md-10" align="center"><spring:message
												code="label.role" /></th>
										<th class="col-md-2" align="center"><c:choose>
												<c:when test="${sortBy eq 'role' and sortMethod eq 'asc'}">
													<a href="#" onclick="changeSortFunction('role','desc')"><span
														class="fa fa-sort-alpha-asc fa-lg"></span></a>
												</c:when>
												<c:when test="${sortBy eq 'role' and sortMethod eq 'desc'}">
													<a href="#" onclick="changeSortFunction('role','asc')"><span
														class="fa fa-sort-alpha-desc fa-lg"></span></a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="changeSortFunction('role','asc')"><span
														class="fa fa-sort fa-lg"></span></a>
												</c:otherwise>
											</c:choose></th>
									</tr>
								</thead>
							</table>
						</th>
						<th class="col-md-1">
							<table class="col-md-12 centerTh">
								<thead>
									<tr>
										<th class="col-md-10" align="center"><spring:message
												code="label.blocked" /></th>
										<th class="col-md-2" align="center"><c:choose>
												<c:when
													test="${sortBy eq 'blocked' and sortMethod eq 'asc'}">
													<a href="#" onclick="changeSortFunction('blocked','desc')"><span
														class="fa fa-sort-alpha-asc fa-lg"></span></a>
												</c:when>
												<c:when
													test="${sortBy eq 'blocked' and sortMethod eq 'desc'}">
													<a href="#" onclick="changeSortFunction('blocked','asc')"><span
														class="fa fa-sort-alpha-desc fa-lg"></span></a>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="changeSortFunction('blocked','asc')"><span
														class="fa fa-sort fa-lg"></span></a>
												</c:otherwise>
											</c:choose></th>
									</tr>
								</thead>
							</table>
						</th>
					</tr>
					<c:forEach items="${users}" var="user">
						<tr>
							<td>${user.email}</td>
							<td>${user.firstName}</td>
							<td>${user.lastName}</td>
							<td align="center"><fmt:formatDate pattern="dd-MM-yyyy"
									value="${user.expired}" /></td>
							<td>
								<table class="col-md-12">
									<tr>
										<td>${user.role.name}</td>
										<td class="col-md-2">
										<%-- Previus change role variant
										<a type="button" data-toggle="modal"
											data-id="changeUserRole?userId=${user.id}"
											data-user-id="${user.id}" data-role-id="${user.role.id}"
											class="btn btn-default openModalWindow"
											data-toggle="dropdown" href="#myModal"> <span
												class="caret"></span>
										</a>
										--%>
  <div class="dropdown changeUlRole">
   <a id="button" class="btn btn-default openRolesBlock" data-id="${user.id}"
											data-user-id="${user.id}" data-role-id="${user.role.id}"
											data-target="#" href="#" data-toggle="dropdown" aria-haspopup="true" role="button" aria-expanded="false">
  <span class="caret"></span>
  </a>
  <ul class="dropdown-menu changeToRole" role="menu" aria-labelledby="dLabel">
  </ul>
</div>
										</td>
									</tr>
								</table>
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
			</c:if>
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
</div>
