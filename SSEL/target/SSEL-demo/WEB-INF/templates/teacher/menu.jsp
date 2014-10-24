<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> 
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="teacher">SSEL SoftServe Academy</a>
	</div>
	<!-- /.navbar-header -->
	
	
	

	<ul class="nav navbar-top-links navbar-right">
		<li class="dropdown">
		<a class="dropdown-toggle" data-toggle="dropdown" href="#">
		<i class="fa fa-user fa-fw"></i>
		<i class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a></li>
				<li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a></li>
				<li class="divider"></li>
				<li><a href="login"><i class="fa fa-sign-out fa-fw"></i>Logout</a></li>
			</ul> 
			<!-- /.dropdown-user -->
		</li>
		<!-- /.dropdown -->
	</ul>
	<!-- /.navbar-top-links -->
	
	
	

	<div class="navbar-default sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">
				<ul class="nav" id="side-menu">
					<li><a href="#"><i class="fa fa-sitemap fa-fw"></i> All Courses<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<c:forEach items="${catList}" var="cat">
								<li>
									<a href="#"> ${cat.name} <span class="fa arrow"></span></a>
									<ul class="nav nav-third-level">
										<c:forEach items="${cat.subjects}" var="subj">
											<li>
												<a href="teacherCourse?subjectId=${subj.id}">${subj.name}</a>
											</li>
										</c:forEach>
									</ul>
								</li>
							</c:forEach>
						</ul>
					</li>
				</ul>
		</div>
		<!-- /.sidebar-collapse -->
	</div>
	<!-- /.navbar-static-side -->
</nav>