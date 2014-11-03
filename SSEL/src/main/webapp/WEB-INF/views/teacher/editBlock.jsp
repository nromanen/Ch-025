<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header"><spring:message code="label.teacher.editModule"/></h1>
	</div>
</div>

<div class="panel panel-default">
	<div class="panel-body">
		<form action="saveBlock" name="editBlockForm" id="editBlockForm">
			<input type="hidden" name="blockId" value="${block.id}">

			<div class="form-group">
				<label><spring:message code="label.teacher.subject"/></label> <select class="form-control"
					name="subjectId" style="width: 40%">
					<c:forEach items="${subjectList}" var="subject">
						<c:choose>
							<c:when test="${block.subject.id == subject.id}">
								<option selected value="${subject.id}"> ${subject.name}</option>
							</c:when>
							<c:otherwise>
								<option value="${subject.id}"> ${subject.name}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
				<p class="help-block"><spring:message code="label.teacher.subject"/></p>
			</div>

			<div class="form-group">
				<label><spring:message code="label.teacher.name"/></label> <input class="form-control"
					name="blockName" id="blockName" style="width: 40%" value="${block.name}">
				<p class="help-block">Input or edit module name</p>
			</div>

			<div class="form-group">
				<label><spring:message code="label.teacher.order"/></label> <input class="form-control"
					name="blockOrder" id="blockOrder" style="width: 5%" value="${block.order}">
				<p class="help-block">Input or edit module order</p>
			</div>

			<div class="form-group">
				<label><spring:message code="label.teacher.startDate"/></label> <input name="startDate" class="src_date"
					type="textarea" placeholder="DD-MM-YYYY"
					value="<fmt:formatDate pattern='dd-MM-yyyy' value='${block.startTime}' />"
					id="startDate" required> <label><spring:message code="label.teacher.endDate"/></label> <input
					name="endDate" class="src_date" type="textarea"
					placeholder="DD-MM-YYYY"
					value="<fmt:formatDate pattern='dd-MM-yyyy' value='${block.endTime}' />"
					id="endDate" required>
			</div>

			<br> <br> <input type="submit"
				class="btn btn-primary btn-lg" value="Save">

		</form>
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

