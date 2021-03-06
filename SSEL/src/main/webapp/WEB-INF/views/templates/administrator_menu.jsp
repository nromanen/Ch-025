<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script src="<c:url value="/resources/js/bootstrapValidator.js" />" ></script>
<script src="<c:url value="/resources/js/admin.js" />" ></script>
<script src="<c:url value="/resources/js/Chart.js" />" ></script>

<script type="text/javascript">
	window.setTimeout(function() {
		$(".alert-dismissible").alert('close');
	}, 3000);
</script>

<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation"
	style="margin-bottom: 0">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="administrator"><img
			src="resources/img/logo.png"></a>
	</div>
	<!-- /.navbar-header -->

	<ul class="nav navbar-top-links navbar-right">
		<li>
			<div>
				<img style="width: 20px; height: 20px; cursor: pointer;"
					src='<c:url value="/resources/img/ua.png" />'
					onclick="localization('ua')"> <img
					style="width: 20px; height: 20px; cursor: pointer;"
					src='<c:url value="/resources/img/en.png" />'
					onclick="localization('en')"> <img
					style="width: 20px; height: 20px; cursor: pointer;"
					src='<c:url value="/resources/img/ru.png" />'
					onclick="localization('ru')">
			</div>
		</li>

		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#"> <c:choose>
					<c:when test="${empty sessionScope.image}">
						<img alt="User Pic" class="img-circle"
							style="height: 30px; width: 30px"
							src="<c:url value="/resources/img/user_photo.png" />">
					</c:when>
					<c:otherwise>
						<img alt="User Pic" class="img-circle"
							style="height: 30px; width: 30px"
							src="data:image/png;base64,<c:out value="${sessionScope.image}" />">
					</c:otherwise>
				</c:choose> <i class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-user">
			<li><a href="${pageContext.request.contextPath}/"><i
						class="fa fa-files-o fa-fw"></i> <spring:message code="label.all_courses" /></a></li>
			<li><a href="profile"><i
						class="fa fa-user fa-fw"></i> <spring:message code="label.admin_profile" /></a></li>
				<li class="divider"></li>
				<li><a href="logout"><i class="fa fa-sign-out fa-fw"></i> <spring:message
							code="label.logout" /></a></li>
			</ul> <!-- /.dropdown-user --></li>
		<!-- /.dropdown -->
	</ul>
	<!-- /.navbar-top-links -->

	<div class="navbar-default sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">
			<p align="center" class="alert alert-success">
				<strong><spring:message code="label.admin.panel" /></strong>
			</p>
			<ul class="nav admin">
				<li><a href="administrator"><spring:message
							code="label.admin.home" /></a></li>
				<li><a href="viewAllCategories"><spring:message
							code="label.admin.categories" /></a></li>
				<li><a href="viewAllSubjects"><spring:message
							code="label.admin.subjects" /></a></li>
				<li><a href="viewAllUsers"><spring:message
							code="label.admin.users" /></a></li>
				<li><a href="viewAllRequests"><spring:message
							code="label.requests" /> <c:if
							test="${activeTeacherRequests ne 0}">
							<span class="badge"> ${activeTeacherRequests}</span>
						</c:if> </a></li>
				<li><a href="viewLogs"><spring:message
							code="label.admin.logs" /></a></li>
			</ul>
		</div>
	</div>
</nav>