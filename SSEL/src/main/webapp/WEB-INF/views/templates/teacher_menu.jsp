<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation"
	style="margin-bottom: 0">

	<div class="navbar-header">

		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>

		<a class="navbar-brand" href="teacher"><img
			src="resources/img/logo.png"></a>
	</div>
	<!-- /.navbar-header -->

	<ul class="nav navbar-top-links navbar-right">

		<li>
			<div>
				<a href="?lang=ua" style="padding: 0px"> <img
					style="width: 20px; height: 20px;"
					src='<c:url value="/resources/img/ua.png" />'>
				</a> <a href="?lang=en" style="padding: 0px"> <img
					style="width: 20px; height: 20px;"
					src='<c:url value="/resources/img/en.png" />'>
				</a> <a href="?lang=ru" style="padding: 0px"> <img
					style="width: 20px; height: 20px;"
					src='<c:url value="/resources/img/ru.png" />'>
				</a>
			</div>
		</li>

		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
				<i class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="teacher"><i class="fa fa-user fa-fw"></i>Teacher
						Profile</a></li>
				<!-- <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a></li>  -->
				<li class="divider"></li>
				<li><a href="logout"><i class="fa fa-sign-out fa-fw"></i>Logout</a></li>
			</ul> <!-- /.dropdown-user --></li>
		<!-- /.dropdown -->
	</ul>
	<!-- /.navbar-top-links -->




	<div class="navbar-default sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">
			<ul class="nav" id="side-menu">

				<li><a href="${pageContext.request.contextPath}/"><i
						class="fa fa-files-o fa-fw"></i> <spring:message code="label.teacher.allCourses"/><span class="fa arrow"></span></a></li>

				<li><a href="teacher"><i class="fa fa-sitemap fa-fw"></i>
						<spring:message code="label.teacher.myCourses"/><span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<c:forEach items="${catList}" var="cat">
							<li><a href="#"> ${cat.name} <span class="fa arrow"></span></a>
								<ul class="nav nav-third-level">
									<c:forEach items="${cat.subjects}" var="subj">
										<li><a href="teacherCourse?subjectId=${subj.id}">${subj.name}</a>
										</li>
									</c:forEach>
								</ul></li>
						</c:forEach>
					</ul></li>


				<li><a href="categories"><i class="fa fa-sitemap fa-fw"></i>
						<spring:message code="label.teacher.categories"/><span class="fa arrow"></span></a></li>


			</ul>
		</div>
		<!-- /.sidebar-collapse -->
	</div>
	<!-- /.navbar-static-side -->



</nav>
