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
			<img src="resources/img/logo.png">
		</a>
	</div>
	<!-- /.navbar-header -->

	<ul class="nav navbar-top-links navbar-right">
		<li>
			<div>
				<img style="width: 20px; height: 20px; cursor: pointer;" src='<c:url value="/resources/img/ua.png" />' 
					onclick="localization('ua')"> 
				<img style="width: 20px; height: 20px; cursor: pointer;" src='<c:url value="/resources/img/en.png" />' 
					onclick="localization('en')"> 
				<img style="width: 20px; height: 20px; cursor: pointer;" src='<c:url value="/resources/img/ru.png" />' 
					onclick="localization('ru')"> 
			</div>
		</li>
	
		<li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 
				<c:choose>
	            	<c:when test="${empty sessionScope.image}">
	              		<img alt="User Pic" class="img-circle" style="height: 30px; width: 30px"
                			src="<c:url value="/resources/img/user_photo.png" />" /> 
	              	</c:when>
	              	<c:otherwise>
	              		<img alt="User Pic" class="img-circle" style="height: 30px; width: 30px"
                			src="data:image/png;base64,<c:out value="${sessionScope.image}" />" > 		
	              	</c:otherwise>
              	</c:choose>
				<i class="fa fa-caret-down"></i>
			</a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="profile"><i
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
				<li><a href="<c:url value="/" />" ><spring:message code="label.all_courses" /></a></li>
						<li class="nav nav-first-level"></li>
						<li><a href="#"><i class="fa fa-sitemap fa-fw"></i>
							<spring:message code="label.subscribed_courses" /></a>
						</li>
					<ul class="nav nav-second-level">
						<li id="future" ><a href="student?table=future" class="<c:if test="${table eq 'future'}" >active</c:if>">
								<spring:message code="label.future_courses" />
							</a></li>
						<li id="active" ><a href="student?table=active" class="<c:if test="${table eq 'active'}" >active</c:if>" >
								<spring:message code="label.active_courses" />
							</a></li>
						<li id="finished"><a href="student?table=finished" class="<c:if test="${table eq 'finished'}" >active</c:if>" >
								<spring:message code="label.finished_courses" />
							</a></li>
					</ul> <!-- /.nav-second-level -->
			</ul>
		</div>
		<br>
		<c:if test="${not empty nearest}">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title"> <spring:message code="label.student.incoming" />
					<button class="btn btn-info" onClick="$('.alert').hide()" style="position: relative"> <div class="glyphicon glyphicon-minus-sign"></div></button>
					<button class="btn btn-info" onClick="$('.alert').show()"><div class="glyphicon glyphicon-plus-sign"></div></button>
				</h3>
				
			</div>
			<div class="alert alert-dismissible" role="alert">
			<p><h5><spring:message code="label.student.open_module" />:</h5></p>
			<p>${nearest.name}</p>
			<p><h5><spring:message code="label.student.open_date" />:</h5></p>
			<p>${nearest.startTime}</p>
			</div>
			
			</div>
		</c:if>
			
		<!-- /.sidebar-collapse -->
	</div>
	<!-- /.navbar-static-side -->

</nav>
