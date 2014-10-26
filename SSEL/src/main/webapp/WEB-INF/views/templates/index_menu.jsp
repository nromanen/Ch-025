<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation"
	style="margin-bottom: 0">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="${pageContext.request.contextPath}/">SSEL SoftServe Academy</a>
	</div>
	<!-- /.navbar-header -->
	<ul class="nav navbar-top-links navbar-right">
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
				<i class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-user">
				<c:if test="${sessionScope.user.role.role eq 'TEACHER'}">
					<li><a href="teacher"><i class="fa fa-user fa-fw"></i>
							Teacher Profile</a></li>
					<li class="divider"></li>
					<li><a href="logout"><i class="fa fa-sign-out fa-fw"></i>
							Logout</a></li>
				</c:if>
				<c:if test="${sessionScope.user.role.role eq 'STUDENT'}">
					<li><a href="student?table=active"> <i
							class="fa fa-user fa-fw"></i> Student Profile
					</a></li>
					<li class="divider"></li>
					<li><a href="logout"><i class="fa fa-sign-out fa-fw"></i>
							Logout</a></li>
				</c:if>
				<c:if test="${sessionScope.user.role.role ne 'TEACHER'
					 && sessionScope.user.role.role ne 'STUDENT' && sessionScope.user.role.role ne 'ADMIN'}">
				<!-- <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a></li>  -->
				<li><a href="login"><i class="fa fa-sign-in fa-fw"></i>Sign
						in</a></li>
				<li><a href="registration"><i class="fa fa-sign-in fa-fw"></i>Sign
						up</a></li>
				</c:if>
			</ul> <!-- /.dropdown-user --></li>
	</ul>
	<!-- /.navbar-top-links -->
	<div class="navbar-default sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">
			<ul class="nav" id="side-menu">
				<li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <!-- <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            	</span>  -->
                            </div>
                            <!-- /input-group -->
                        </li>
				<li><a href="#"><i class="fa fa-sitemap fa-fw"></i> All
						Courses<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<c:forEach items="${catList}" var="cat">
							<li><a href="#"> ${cat.name} <span class="fa arrow"></span></a>
								<ul class="nav nav-third-level">
									<c:forEach items="${cat.subjects}" var="subj">
										<li class="link"><a href="course?subjectId=${subj.id}"
											style="color: #428bca;"> ${subj.name}</a></li>
									</c:forEach>
								</ul>
						</c:forEach>
					</ul></li>
			</ul>
		</div>
	</div>
</nav>