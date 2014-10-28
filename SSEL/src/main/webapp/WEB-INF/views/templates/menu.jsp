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
		<li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 
				<i class="fa fa-user fa-fw"></i>
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
			</ul> <!-- /.dropdown-user -->
		</li>	
	</ul>
</nav>