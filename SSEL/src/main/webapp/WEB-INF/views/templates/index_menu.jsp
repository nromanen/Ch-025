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
		<a class="navbar-brand" href="<c:url value="/" />">SSEL SoftServe Academy</a>
	</div>
	<!-- /.navbar-header -->
	<ul class="nav navbar-top-links navbar-right">
		<li>
			<div>
				<c:set var="params" value="${requestScope['javax.servlet.forward.query_string']}"> </c:set>
 				<c:set var="strlenparams" value="${fn:length(params) - 7}"> </c:set> 
				<c:if test="${fn:endsWith(params, 'lang=en') || 
							  fn:endsWith(params, 'lang=ru') || 
							  fn:endsWith(params, 'lang=ua')}">
					<c:set var="params" value="${fn:substring(params,0, strlenparams)}"></c:set>			
				</c:if> 
					<a  href="${requestScope['javax.servlet.forward.request_uri']}?${params}&lang=ua" 
					style="padding: 0px"> 
					<img style="width: 20px; height: 20px;" src='<c:url value="/resources/img/ua.png" />'> 
				</a>
				<a href="${requestScope['javax.servlet.forward.request_uri']}?${params}&lang=en" 
					style="padding: 0px"> 
					<img style="width: 20px; height: 20px;" src='<c:url value="/resources/img/en.png" />'> 
				</a>
				<a href="${requestScope['javax.servlet.forward.request_uri']}?${params}&lang=ru" 
					style="padding: 0px"> 
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
							Teacher cabinet</a></li>
					<li><a href="#"><i class="fa fa-user fa-fw"></i>
							Teacher profile</a></li>
					<li class="divider"></li>
					<li><a href="logout"><i class="fa fa-sign-out fa-fw"></i>
							Logout</a></li>
				</c:if>
				<c:if test="${sessionScope.user.role.role eq 'STUDENT'}">
					<li>
						<a href="student?table=active"> <i
							class="fa fa-user fa-fw"></i> Student cabinet
						</a>
					</li>
					<li><a href="#"> <i
							class="fa fa-user fa-fw"></i> Student profile
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