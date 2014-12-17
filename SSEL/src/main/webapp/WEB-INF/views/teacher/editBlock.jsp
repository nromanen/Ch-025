<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<script src="resources/datepicker/js/locales/bootstrap-datepicker.ru.js"></script>
<script src="resources/datepicker/js/locales/bootstrap-datepicker.ua.js"></script>

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
				<c:set value="${pageContext.response.locale}" var="local" />
				<form:input class="datepicker" path="startTime" style="width: 20%"
					value="${startTime}" placeholder="DD-MM-YYYY" data-date-language="${local}" 
						data-date-autoclose="true" readonly="readonly" />
				<form:errors path="startTime" cssClass="error" />

				<br> 
				<br>
				<label><spring:message code="label.teacher.endDate" /></label>
				<br>
				<form:input class="datepicker" path="endTime" style="width: 20%"
					value="${endTime}" placeholder="DD-MM-YYYY" data-date-language="${local}" 
						data-date-autoclose="true" readonly="readonly"/>
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

function parseDate(input) {
	  var parts = input.match(/(\d+)/g);
	  return new Date(parts[2], parts[1]-1, parts[0]);
	}

jQuery.validator.addMethod("greaterThan", 
		function(value, element, params) {
	myTime = $("#startTime").val();
	myTime = parseDate(myTime);
	endTime = new Date(value);
	//t = new Date(getYear(), getMonth(), getDay());
	
	s = value.split("-");
	//alert(value +"   "+new Date(s[1]+"-"+s[0]+"-"+s[2]));

	//alert(new Date("12-5-2014"));
	
	//myTime = myTime.toString('dd.MM.yyyy')
	//alert(myTime);
	//myTime.format.date(new Date, 'dd-mm-yyyy')
	//alert(new Date(value) +"    "+ myTime);
return new Date(s[1]+"-"+s[0]+"-"+s[2]) > myTime;
		   /* if (!/Invalid|NaN/.test(new Date(value))) {
		    	alert("1"+value);
		        return new Date(value) > new Date($(params).val());
		    }
alert("2"+value);
		    return isNaN(value) && isNaN($(params).val()) 
		        || (Number(value) > Number($(params).val())); */
		},'Must be greater than {0}.');





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

				startTime : {
					required : true,
					regexp : "^[0-9]{2}\-[0-9]{2}\-[0-9]{4}"
				},

				endTime : {
					required : true,
					regexp : "^[0-9]{2}\-[0-9]{2}\-[0-9]{4}",
					greaterThan: '$("#startTime")'
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

