<%@page import="org.springframework.ui.Model"
	import="com.softserve.entity.Log"%>
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script src="resources/datepicker/js/locales/bootstrap-datepicker.ru.js"></script>
<script src="resources/datepicker/js/locales/bootstrap-datepicker.ua.js"></script>

<div class="row">
	<script type="text/javascript">
		function deleteFunction() {
			var deleteDate = document.getElementById("deleteDate").value;
			window.location.href = "deleteOldLogs?deleteDate=" + deleteDate;
		}
	</script>
	
	<c:set value="${pageContext.response.locale}" var="local" />
	
	<!-- Modal window -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only"><spring:message
								code="label.close" /></span>
					</button>
					<h4 class="modal-title" align="center">
						<spring:message code="label.are_you_sure" />
					</h4>
				</div>
				<div class="modal-footer">
					<p align="center">
						<button type="button" id="mybtn" id="mybtn"
							class="btn btn-primary" onclick="deleteFunction()">
							<spring:message code="label.delete" />
						</button>
						<button type="button" class="btn btn-default" onClick=""
							data-dismiss="modal">
							<spring:message code="label.cancel" />
						</button>
					</p>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
	

<!-- Modal -->
<div class="modal fade bs-example-modal-lg" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h3 class="modal-title" align="center" id="myModalLabel">Stack trace</h3>
      </div>
      <div class="modal-body" style="word-wrap: break-word">
        
<p >
java.lang.IllegalStateException: Cannot call sendError() after the response has been committed at org.apache.catalina.connector.ResponseFacade.sendError(ResponseFacade.java:478) at javax.servlet.http.HttpServletResponseWrapper.sendError(HttpServletResponseWrapper.java:127) at org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver.sendServerError(DefaultHandlerExceptionResolver.java:313) at org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver.handleHttpMessageNotWritable(DefaultHandlerExceptionResolver.java:370) at org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver.doResolveException(DefaultHandlerExceptionResolver.java:140) at org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver.resolveException(AbstractHandlerExceptionResolver.java:138) at org.springframework.web.servlet.DispatcherServlet.processHandlerException(DispatcherServlet.java:1164) at org.springframework.web.servlet.DispatcherServlet.processDispatchResult(DispatcherServlet.java:1005) at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:959) at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:876) at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:961) at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:852) at javax.servlet.http.HttpServlet.service(HttpServlet.java:618) at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:837) at javax.servlet.http.HttpServlet.service(HttpServlet.java:725) at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:291) at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206) at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52) at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:239) at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206) at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:186) at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:160) at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:344) at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:261) at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:239) at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206) at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:88) at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:108) at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:239) at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206) at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:219) at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:106) at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:505) at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:142) at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:79) at org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:610) at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:88) at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:534) at org.apache.coyote.http11.AbstractHttp11Processor.process(AbstractHttp11Processor.java:1081) at org.apache.coyote.AbstractProtocol$AbstractConnectionHandler.process(AbstractProtocol.java:658) at org.apache.coyote.http11.Http11NioProtocol$Http11ConnectionHandler.process(Http11NioProtocol.java:222) at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1566) at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.run(NioEndpoint.java:1523) at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142) at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617) at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61) at java.lang.Thread.run(Thread.java:745) 
</p>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
    </div>
  </div>
</div>
	
	
	
	
	
	
	
	
	
	
	

	<!-- Head -->
	<div class="col-lg-12">
		<h1 align="center">
			<spring:message code="label.logs" />
		</h1>
		<h3 align="center">
			<spring:message code="label.since" />
			<fmt:formatDate pattern="dd-MM-yyyy" value="${startPeriod}" />
			<spring:message code="label.to" />
			<fmt:formatDate pattern="dd-MM-yyyy" value="${endPeriod}" />
		</h3>
		<div class="row">

			<!-- Selecting range of dates for viewing logs -->
			<div class="col-md-7" align="center">
				<form action="getRangeOfDates" method="get">
					<spring:message code="label.show_logs_from_date" />
					<input type="text" name="startDate" class="datepicker"
						placeholder="<spring:message code="label.placeholder" />"
						readonly="readonly"  
						data-date-language="${local}" 
						data-date-autoclose="true" />
					<spring:message code="label.to" />
					<input type="text" name="endDate" class="datepicker"
						placeholder="<spring:message code="label.placeholder" />"
						readonly="readonly" 
						data-date-language="${local}" 
						data-date-autoclose="true" /> <input type="submit"
						class="btn btn-primary"
						value="<spring:message code="label.show" />" />
				</form>
			</div>

			<!-- Selecting date for deleting old logs -->
			<div class="col-md-5" align="center">
				<spring:message code="label.delete_old_logs_to_date" />
				<input type="text" name="deleteDate" class="datepicker"
					placeholder="<spring:message code="label.placeholder" />"
					readonly="readonly" id="deleteDate" 
					data-date-language="${local}" 
					data-date-autoclose="true"/>
				<button type="button" class="btn btn-danger" data-toggle="modal"
					data-target="#myModal">
					<spring:message code="label.l_delete" />
				</button>
			</div>
		</div>
		
		<div class="row" align="left">
		
		<!-- Used for visual improving -->
			<div class="col-md-2" align="left">
				<div class="col-md-12" align="left">
					<br /> <br />
				</div>
				
				<!-- Picking logsPerPage parameter -->
				<div class="col-md-12" align="left">
					<form method="get" action="getParameters">
						<spring:message code="label.records_per_page" />
						<select name="logsPerPage" onchange="this.form.submit()">
							<c:choose>
								<c:when test="${empty logsPerPage}">
									<option selected value="10">10</option>
								</c:when>
								<c:otherwise>
									<option selected value="${logsPerPage}">${logsPerPage}</option>
								</c:otherwise>
							</c:choose>
							<c:if test="${(logsPerPage != 10) or (empty logsPerPage)}">
								<option value="10">10</option>
							</c:if>
							<c:if test="${logsPerPage != 25}">
								<option value="25">25</option>
							</c:if>
							<c:if test="${logsPerPage != 50}">
								<option value="50">50</option>
							</c:if>
						</select>
					</form>
				</div>
			</div>
			
			<!-- Pagination scroll -->
			<div class="col-md-10" align="right">
				<nav>
					<ul class="pagination">
						<c:if test="${pageNumb < 1}">
							<li class="disabled"><a href="#"><spring:message
										code="label.previous" /></a></li>
						</c:if>
						<c:if test="${pageNumb > 0}">
							<li><a
								href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb - 1}"><spring:message
										code="label.previous" /></a></li>
							<li><a
								href="${pageContext.request.contextPath}/viewLogs?pageNumb=0">1</a></li>
						</c:if>
						<c:if test="${pageNumb > 3}">
							<li><a href="#">...</a></li>
						</c:if>
						<c:if test="${pageNumb > 2}">
							<li><a
								href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb - 2}">${pageNumb - 1}</a></li>
						</c:if>
						<c:if test="${pageNumb > 1}">
							<li><a
								href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb - 1}">${pageNumb}</a></li>
						</c:if>
						<li class="active"><a href="#">${pageNumb + 1}</a></li>
						<c:if test="${pageNumb < (numberOfPages - 2)}">
							<li><a
								href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb + 1}">${pageNumb + 2}</a></li>
						</c:if>
						<c:if test="${pageNumb < (numberOfPages - 3)}">
							<li><a
								href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb + 2}">${pageNumb + 3}</a></li>
						</c:if>
						<c:if test="${pageNumb < (numberOfPages - 4)}">
							<li><a href="#">...</a></li>
						</c:if>
						<c:if test="${pageNumb < (numberOfPages - 1)}">
							<li><a
								href="${pageContext.request.contextPath}/viewLogs?pageNumb=${numberOfPages-1}">${numberOfPages}</a></li>
							<li><a
								href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb + 1}"><spring:message
										code="label.next" /></a></li>
						</c:if>
						<c:if test="${pageNumb > (numberOfPages - 2)}">
							<li class="disabled"><a href="#"><spring:message
										code="label.next" /></a></li>
						</c:if>
					</ul>
				</nav>
			</div>
		</div>
	</div>
	<c:choose>
		<c:when test="${empty logs}">
			<h1 align="center"><spring:message code="label.no_data" /></h1>
		</c:when>
		<c:otherwise>
		
			<!-- Table with logs -->
			<table
				class="table table-striped table-bordered table-hover centerTh">
				<thead>
					<tr class="info">
						<!-- Head of table also includes sorting parameters -->
						<th>
						<!-- Soting logic and icons -->
							<table class="col-md-12 centerTh">
								<thead>
									<tr>
										<th class="col-md-10"><spring:message code="label.t_date" /></th>
										<th class="col-md-2"><c:choose>
												<c:when test="${orderBy eq 'eventDate ASC'}">
													<a
														href="${pageContext.request.contextPath}/getParameters?orderBy=date-desc"><span
														class="fa fa-sort-numeric-asc fa-lg"></span></a>
												</c:when>
												<c:when test="${orderBy eq 'eventDate DESC'}">
													<a
														href="${pageContext.request.contextPath}/getParameters?orderBy=date-asc"><span
														class="fa fa-sort-numeric-desc fa-lg"></span></a>
												</c:when>
												<c:otherwise>
													<a
														href="${pageContext.request.contextPath}/getParameters?orderBy=date-asc"><span
														class="fa fa-sort fa-lg"></span></a>
												</c:otherwise>
											</c:choose></th>
									</tr>
								</thead>
							</table>
						</th>
						<th>
							<!-- Soting logic and icons -->
							<table class="col-md-12 centerTh">
								<thead>
									<tr>
										<th class="col-md-10"><spring:message
												code="label.t_level" /></th>
										<th class="col-md-2"><c:choose>
												<c:when test="${orderBy eq 'level ASC'}">
													<a
														href="${pageContext.request.contextPath}/getParameters?orderBy=level-desc"><span
														class="fa fa-sort-alpha-asc fa-lg"></span></a>
												</c:when>
												<c:when test="${orderBy eq 'level DESC'}">
													<a
														href="${pageContext.request.contextPath}/getParameters?orderBy=level-asc"><span
														class="fa fa-sort-alpha-desc fa-lg"></span></a>
												</c:when>
												<c:otherwise>
													<a
														href="${pageContext.request.contextPath}/getParameters?orderBy=level-asc"><span
														class="fa fa-sort fa-lg"></span></a>
												</c:otherwise>
											</c:choose></th>
									</tr>
								</thead>
							</table>
						</th>
						<th>
						<!-- Soting logic and icons -->
							<table class="col-md-12 centerTh">
								<thead>
									<tr>
										<th class="col-md-12"><spring:message
												code="label.t_logger" /></th>
									</tr>
								</thead>
							</table>
						</th>
						<th>
							<table class="col-md-12 centerTh">
								<thead>
									<tr>
										<th class="col-md-12"><spring:message
												code="label.t_message" /></th>
									</tr>
								</thead>
							</table>
						<th>
						<!-- Soting logic and icons -->
							<table class="col-md-12 centerTh">
								<thead>
									<tr>
										<th class="col-md-10"><spring:message
												code="label.t_exception" /></th>
										<th class="col-md-2"><c:choose>
												<c:when test="${orderBy eq 'exception ASC'}">
													<a
														href="${pageContext.request.contextPath}/getParameters?orderBy=exception-desc"><span
														class="fa fa-sort-alpha-desc fa-lg"></span></a>
												</c:when>
												<c:when test="${orderBy eq 'exception DESC'}">
													<a
														href="${pageContext.request.contextPath}/getParameters?orderBy=exception-asc"><span
														class="fa fa-sort-alpha-asc fa-lg"></span></a>
												</c:when>
												<c:otherwise>
													<a
														href="${pageContext.request.contextPath}/getParameters?orderBy=exception-asc"><span
														class="fa fa-sort fa-lg"></span></a>
												</c:otherwise>
											</c:choose></th>
									</tr>
								</thead>
							</table>
						</th>
					</tr>
				</thead>
				
				<!-- Table content -->
				<c:forEach items="${logs}" var="log">
					<tr>
						<td class="col-md-1"><fmt:formatDate
								pattern="dd-MM-yyyy HH:mm:ss" value="${log.eventDate}" /></td>
						<td class="col-md-1">${log.level}</td>
						<td class="col-md-3"><textarea class="col-md-12" rows="2"
								readonly="readonly">${log.logger}</textarea></td>
						<td class="col-md-6"><textarea class="col-md-12" rows="2"
								readonly="readonly">${log.message}</textarea></td>
						<td class="col-md-1">
							<c:choose>
								<c:when test="${not empty log.exception}">
									<a href="${pageContext.request.contextPath}/logDetails?LogId=${log.id}">Details</a>
										<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal2">
										  Details
										</button> 						
								</c:when>
								<c:otherwise>
										No exception 
</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	
	<!-- Pagination scroll -->
	<div align="right">
		<nav>
			<ul class="pagination">
				<c:if test="${pageNumb < 1}">
					<li class="disabled"><a href="#"><spring:message
								code="label.previous" /></a></li>
				</c:if>
				<c:if test="${pageNumb > 0}">
					<li><a
						href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb - 1}"><spring:message
								code="label.previous" /></a></li>
					<li><a
						href="${pageContext.request.contextPath}/viewLogs?pageNumb=0">1</a></li>
				</c:if>
				<c:if test="${pageNumb > 3}">
					<li><a href="#">...</a></li>
				</c:if>
				<c:if test="${pageNumb > 2}">
					<li><a
						href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb - 2}">${pageNumb - 1}</a></li>
				</c:if>
				<c:if test="${pageNumb > 1}">
					<li><a
						href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb - 1}">${pageNumb}</a></li>
				</c:if>
				<li class="active"><a href="#">${pageNumb + 1}</a></li>
				<c:if test="${pageNumb < (numberOfPages - 2)}">
					<li><a
						href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb + 1}">${pageNumb + 2}</a></li>
				</c:if>
				<c:if test="${pageNumb < (numberOfPages - 3)}">
					<li><a
						href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb + 2}">${pageNumb + 3}</a></li>
				</c:if>
				<c:if test="${pageNumb < (numberOfPages - 4)}">
					<li><a href="#">...</a></li>
				</c:if>
				<c:if test="${pageNumb < (numberOfPages - 1)}">
					<li><a
						href="${pageContext.request.contextPath}/viewLogs?pageNumb=${numberOfPages-1}">${numberOfPages}</a></li>
					<li><a
						href="${pageContext.request.contextPath}/viewLogs?pageNumb=${pageNumb + 1}"><spring:message
								code="label.next" /></a></li>
				</c:if>
				<c:if test="${pageNumb > (numberOfPages - 2)}">
					<li class="disabled"><a href="#"><spring:message
								code="label.next" /></a></li>
				</c:if>
			</ul>
		</nav>

	</div>
</div>
