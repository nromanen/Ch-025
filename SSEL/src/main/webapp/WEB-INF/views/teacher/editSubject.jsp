<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<script src="resources/ckeditor/ckeditor.js"></script>
<script src="resources/datepicker/js/locales/bootstrap-datepicker.ru.js"></script>
<script src="resources/datepicker/js/locales/bootstrap-datepicker.ua.js"></script>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header"><spring:message code="label.teacher.editSubject"/></h1>
	</div>
</div>

<div class="row">
	<div class="panel panel-default">
		<div class="panel-body">
			<form:form method="post" action="saveSubject" name="saveSubject" id="esaveSubject" commandName="subject">
				<form:hidden path="id"/>
				<input type="hidden" name="user" value="${subject.id}">
				<input type="hidden" name="subjectId" value="${subject.id}">
				<div class="form-group">
					<label><spring:message code="label.teacher.category"/></label>
					<form:select class="form-control" items="${catList}" path="category" itemValue="id" itemLabel="name" style="width: 40%"/>
					<p class="help-block">
						<spring:message code="label.teacher.selectCategory"/>
					</p>
				</div>

				<div class="form-group">
					<label><spring:message code="label.teacher.name"/></label>
					<form:input class="form-control" path="name" style="width: 40%" value="${subject.name}" />
					<form:errors path="name" cssClass="error" />
					<p class="help-block">
						<spring:message code="label.teacher.inputOrEditSubjectName"/>
					</p>
				</div>
				
				<label>
					<spring:message code="label.teacher.content"/>
				</label>
				<form:textarea id="description" path="description" rows="15" cols="80"/>
				
				<script>
					CKEDITOR.replace('description');
				</script>
				<br>

			<fmt:formatDate value="${scheduler.start}" var="startDate" pattern="dd-MM-yyyy" />
			<fmt:formatDate value="${scheduler.end}" var="endDate" pattern="dd-MM-yyyy" />

				<div class="form-group">
					
					<label>
						<spring:message code="label.teacher.startDate"/>
					</label>
					<br>
					<c:set value="${pageContext.response.locale}" var="local" />
					<input name="startDate" class="datepicker" placeholder="DD-MM-YYYY"
						value="${startDate}" id="startDate" data-date-language="${local}" 
						data-date-autoclose="true" readonly="readonly">
					<br>
					<br>
					<label><spring:message code="label.teacher.endDate"/></label>
					<br> 
					<input name="endDate" class="datepicker" placeholder="DD-MM-YYYY" 
						value="${endDate}" id="endDate" data-date-language="${local}"
						data-date-autoclose="true" readonly="readonly">
				</div>
				
				<br> <input type="submit" class="btn btn-primary btn-lg" value=<spring:message code="label.teacher.save"/>>
			</form:form>
		</div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	
	$.validator.addMethod(
			'regexp',
			function(value, element, regexp) {
				var re = new RegExp(regexp);
			    return this.optional(element) || re.test(value);
			},
			"Please check your input."
		);

    $("#saveSubject").validate({

       rules:{

            subjectName:{
                required: true,
                minlength: 4,
                maxlength: 30,
                regexp: "^[A-ZА-ЯІЇЄa-zа-яіїє0-9.,:_ ]{4,30}$"
            },

            startDate:{
                required: true,
                regexp: "^[0-9]{2}\-[0-9]{2}\-[0-9]{4}"
            },
            
            endDate:{
                required: true,
                regexp: "^[0-9]{2}\-[0-9]{2}\-[0-9]{4}"
            },
       }

    });

});
</script>
<style>
  
 .error {
  color:red;
 }
  
</style> 