<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation"
	style="margin-bottom: 0">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="<c:url value="student" />">
		SSEL SoftServe Academy</a>
	</div>
	<!-- /.navbar-header -->

	<ul class="nav navbar-top-links navbar-right">
		<li>
			<div>
				<a  href="?lang=ua" style="padding: 0px"> 
					<img style="width: 20px; height: 20px;" src='<c:url value="/resources/img/ua.png" />'> 
				</a>
				<a href="?lang=en" style="padding: 0px"> 
					<img style="width: 20px; height: 20px;" src='<c:url value="/resources/img/en.png" />'> 
				</a>
				<a href="?lang=ru" style="padding: 0px"> 
					<img style="width: 20px; height: 20px;" src='<c:url value="/resources/img/ru.png" />'> 
				</a>
			</div>
		</li>
	
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
				<i class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="student?table=active"><i
						class="fa fa-user fa-fw"></i> <spring:message code="label.student_profile" /></a></li>
				<!-- <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a></li>  -->
				<li class="divider"></li>
				<li><a href="logout"><i class="fa fa-sign-out fa-fw"></i>
						<spring:message code="label.logout" /></a></li>
			</ul> <!-- /.dropdown-user --></li>
		<!-- /.dropdown -->
	</ul>
	<!-- /.navbar-top-links -->

	<div class="navbar-default sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">
			<ul class="nav" id="side-menu">
				<li><a href="<c:url value="/" />" ><spring:message code="label.all_cources" /></a></li>
						<li class="nav nav-first-level"></li>
						<li><a href="#"><i class="fa fa-sitemap fa-fw"></i>
							<spring:message code="label.subscribed_courses" /></a>
						</li>
					<ul class="nav nav-second-level">
						<li><a href="student?table=future">
								<spring:message code="label.future_courses" />
							</a></li>
						<li class="active" ><a href="student?table=active">
								<spring:message code="label.active_courses" />
							</a></li>
						<li><a href="student?table=finished">
								<spring:message code="label.finished_courses" />
							</a></li>
					</ul> <!-- /.nav-second-level -->
			</ul>
		</div>
		<!-- /.sidebar-collapse -->
	</div>
	<!-- /.navbar-static-side -->
</nav>