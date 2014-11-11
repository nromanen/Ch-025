<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
			<spring:message code="label.teacher.editModule" />
		</h1>
	</div>
</div>

<div class="panel panel-default">
	<div class="panel-body">
		<form:form method="post" action="saveBlock" name="editBlockForm"
			id="editBlockForm" commandName="block">
			<form:hidden path="id" />

			<div class="form-group">
				<label><spring:message code="label.teacher.subject" /></label>
				<form:select class="form-control" items="${subjectList}"
					path="subject" itemValue="id" itemLabel="name" style="width: 40%" />
				<p class="help-block">
					<spring:message code="label.teacher.subject" />
				</p>
			</div>

			<div class="form-group">
				<label><spring:message code="label.teacher.name" /></label>
				<form:input class="form-control" path="name" style="width: 40%"
					value="${block.name}" />
				<form:errors path="name" cssClass="error" />
				<p class="help-block">
					<spring:message code="label.teacher.inputOrEditModuleName" />
				</p>
			</div>

			<fmt:formatDate value="${block.startTime}" var="startTime"
				pattern="dd-MM-yyyy" />
			<fmt:formatDate value="${block.endTime}" var="endTime"
				pattern="dd-MM-yyyy" />


			<div class="form-group">
				<label><spring:message code="label.teacher.startDate" /></label>
				<br>
				<form:input class="datepicker" path="startTime" style="width: 20%"
					value="${startTime}" placeholder="DD-MM-YYYY" />
				<form:errors path="startTime" cssClass="error" />

				<br> 
				<br>
				<label><spring:message code="label.teacher.endDate" /></label>
				<br>
				<form:input class="datepicker" path="endTime" style="width: 20%"
					value="${endTime}" placeholder="DD-MM-YYYY" />
				<form:errors path="endTime" cssClass="error" />
			</div>

			<br>
			<br>
			<input type="submit" class="btn btn-primary btn-lg"
				value=<spring:message code="label.teacher.save"/>>

		</form:form>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {

		$.validator.addMethod('regexp', function(value, element, regexp) {
			var re = new RegExp(regexp);
			return this.optional(element) || re.test(value);
		}, "Please check your input.");

		$("#editBlockForm").validate({

			rules : {

				blockName : {
					required : true,
					minlength : 4,
					maxlength : 30,
					regexp : "^[A-ZА-ЯІЇЄa-zа-яіїє0-9.,:_ ]{4,30}$"
				},

				blockOrder : {
					required : true,
					minlength : 1,
					maxlength : 2,
					regexp : "^[0-9]{1,2}$"
				},

				startDate : {
					required : true,
					regexp : "^[0-9]{2}\-[0-9]{2}\-[0-9]{4}"
				},

				endDate : {
					required : true,
					regexp : "^[0-9]{2}\-[0-9]{2}\-[0-9]{4}"
				},
			}

		});

	});
</script>
<style>
.error {
	color: red;
}
</style>

