<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">SoftServe SSEL Academy</h1>
		</div>
		<div class="col-lg-12">
			<p>SoftServe SSEL Academy is better way for self-education. We
				choose the best courses for you!</p>
		</div>
		<div class="blocks">
			<c:forEach items="${subList}" var="subj">
				<div class="inline">
					<div class="title">${subj.name}</div>
					<div>${subj.description }</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>

