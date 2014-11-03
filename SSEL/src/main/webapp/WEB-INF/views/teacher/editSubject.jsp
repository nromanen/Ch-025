<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<script src="resources/ckeditor/ckeditor.js"></script>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header"><spring:message code="label.teacher.editSubject"/></h1>
	</div>
</div>

<div class="row">
	<div class="panel panel-default">
		<div class="panel-body">
			<form action="saveSubject" name="saveSubject" id="saveSubject">
				<input type="hidden" name="subjectId" value="${subject.id}">
				<div class="form-group">
					<label><spring:message code="label.teacher.category"/></label> <select class="form-control"
						name="subjectCategoryId" style="width:40%">
						<c:forEach items="${catList}" var="category">
							<c:choose>
								<c:when test="${subject.category.id == category.id}">
									<option selected value="${category.id}">${category.id}.
										${category.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${category.id}">${category.id}.
										${category.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
					<p class="help-block"><spring:message code="label.teacher.selectCategory"/></p>
				</div>

				<div class="form-group">
					<label><spring:message code="label.teacher.name"/></label> <input class="form-control"
						name="subjectName" id="subjectName" maxlength="30" style="width:40%" value="${subject.name}">
					<p class="help-block"><spring:message code="label.teacher.inputOrEditSubjectName"/></p>
				</div>
				<label>Content</label>
				<textarea name="subjectDescription" id="subjectDescription" rows="10" cols="80">
                ${subject.description}
         
            </textarea>
				<script>
					CKEDITOR.replace('subjectDescription');
				</script>
				<br>


				<div class="form-group">
					
					<label><spring:message code="label.teacher.startDate"/></label>
					<input name="startDate" class="src_date" type="textarea" placeholder="DD-MM-YYYY"
						value="<fmt:formatDate pattern='dd-MM-yyyy' value='${scheduler.start}' />" id="startDate">
					
					<label><spring:message code="label.teacher.endDate"/></label> 
					<input name="endDate" class="src_date" type="textarea" placeholder="DD-MM-YYYY" 
						value="<fmt:formatDate pattern='dd-MM-yyyy' value='${scheduler.end}' />" id="endDate">
						
				</div>
				<br> <input type="submit" class="btn btn-primary btn-lg" value=<spring:message code="label.teacher.save"/>>
			</form>
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