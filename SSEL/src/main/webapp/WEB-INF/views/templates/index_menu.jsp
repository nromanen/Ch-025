<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation"
	style="margin-bottom: 0">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<c:choose>
			<c:when test="${sessionScope.user.role.role eq 'STUDENT'}">
				<c:set var="url" value="student" ></c:set>
			</c:when>
			<c:when test="${sessionScope.user.role.role eq 'TEACHER'}">
				<c:set var="url" value="teacher" ></c:set>
			</c:when>
			<c:otherwise>
				<c:set var="url" value="/" ></c:set>
			</c:otherwise>
		</c:choose>
		
		<a class="navbar-brand" href="<c:url value="${url}" />">
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
                			src="<c:url value="/resources/img/user_photo.png" />" > 
	              	</c:when>
	              	<c:otherwise>
	              		<img alt="User Pic" class="img-circle" style="height: 30px; width: 30px"
                			src="data:image/png;base64,<c:out value="${sessionScope.image}" />" > 		
	              	</c:otherwise>
              	</c:choose>
				<i class="fa fa-caret-down"></i>
			</a>
			<ul class="dropdown-menu dropdown-user">
				<c:if test="${sessionScope.user.role.role eq 'TEACHER'}">
					<li><a href="teacher"><i class="fa fa-user fa-fw"></i>
							<spring:message code="label.teacher_cabinet"/> </a></li>
					<li><a href="<c:url value="/profile" />"><i class="fa fa-user fa-fw"></i>
							<spring:message code="label.teacher_profile"/></a></li>
					<li class="divider"></li>
					<li><a href="logout"><i class="fa fa-sign-out fa-fw"></i>
							Logout</a></li>
				</c:if>
				<c:if test="${sessionScope.user.role.role eq 'STUDENT'}">
					<li>
						<a href="student?table=active"> <i
							class="fa fa-user fa-fw"></i> <spring:message code="label.student_cabinet"/>
						</a>
					</li>
					<li><a href="<c:url value="/profile" />"> <i
							class="fa fa-user fa-fw"></i> <spring:message code="label.student_profile"/>
					</a></li>
					<li class="divider"></li>
					<li>
						<a href="logout">
							<i class="fa fa-sign-out fa-fw"></i>
							<spring:message code="label.logout"/> 
						</a>
					</li>
				</c:if>
				<c:if test="${sessionScope.user.role.role eq 'ADMIN'}">
					<li>
						<a href="administrator"> <i
							class="fa fa-user fa-fw"></i> <spring:message code="label.admin.panel"/>
						</a>
					</li>
					<li><a href="<c:url value="/profile" />"> <i
							class="fa fa-user fa-fw"></i> <spring:message code="label.admin_profile"/>
					</a></li>
					<li class="divider"></li>
					<li>
						<a href="logout">
							<i class="fa fa-sign-out fa-fw"></i>
							<spring:message code="label.logout"/> 
						</a>
					</li>
				</c:if>
				<c:if test="${sessionScope.user.role.role ne 'TEACHER'
					 && sessionScope.user.role.role ne 'STUDENT' && sessionScope.user.role.role ne 'ADMIN'}">
				<!-- <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a></li>  -->
				<li>
					<a href="login">
						<i class="fa fa-sign-in fa-fw" ></i>
						<spring:message code="label.sing_in" />	
					</a>
				</li>
				<li>
					<a href="registration">
						<i class="fa fa-sign-in fa-fw"></i>
						<spring:message code="label.registration"/> 
					</a>
				</li>
				</c:if>
			</ul> <!-- /.dropdown-user -->
		</li>	
	</ul>
	<!-- /.navbar-top-links -->
	<div class="navbar-default sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">
			<ul class="nav" id="side-menu">
				<li><a href="#"><i class="fa fa-sitemap fa-fw"></i>
					<spring:message code="label.all_courses" /><span class="fa arrow"></span></a>
					<ul class="nav nav-second-level"  id="listCat">
						<c:forEach items="${catList}" var="cat">
							<li><a href="#"> ${cat.name} <span class="fa arrow"></span></a>
								<ul class="nav nav-third-level" id="listSubj">
									<c:forEach items="${cat.subjects}" var="subj">
										<li class="link"><a href="course?subjectId=${subj.id}"
											style="color: #428bca;"> ${subj.name}</a></li>
									</c:forEach>
								</ul>
						</c:forEach>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</nav>