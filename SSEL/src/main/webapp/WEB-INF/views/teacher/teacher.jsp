<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header"><spring:message code="label.teacher.teacherCourses"/> </h1>
	</div>
</div>

<div class="row">
	<button type="button" id="delButton"
		class="btn btn-info btn-primary btn-xs"><spring:message code="label.teacher.delete"/></button>
	<button type="button" class="btn btn-info btn-primary btn-xs"
		onclick="location.href='editSubject'"><spring:message code="label.teacher.addNewCourse"/></button>
</div>
<br>
<div class="row">
	<div class="panel panel-default">



		<div class="table-responsive">
			<table class="table table-hover">
				<thead>
					<tr>
						<th><input type="checkbox" id="selectall" /></th>
						<th><spring:message code="label.teacher.subjectName"/></th>
						<th><spring:message code="label.teacher.subjectCategory"/></th>
						<th><spring:message code="label.teacher.startDate"/></th>
						<th><spring:message code="label.teacher.endDate"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${schedulerList}" var="scheduler">

						<tr class="odd gradeA">
							<td><input type="checkbox" class="case" name="case"
								value="${scheduler.subject.id}" /></td>
							<td><a
								href="teacherCourse?subjectId=${scheduler.subject.id}">${scheduler.subject.name}</a></td>

							<td>${scheduler.subject.category.name}</td>
							<td><fmt:formatDate pattern='dd-MM-yyyy'
									value="${scheduler.start}" /></td>
							<td><fmt:formatDate pattern='dd-MM-yyyy'
									value="${scheduler.end}" /></td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
		</div>


		<!-- .panel-body -->
	</div>
	<!-- /.panel -->
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$("#delButton").click(function() {
			
			var checkeds = $(":checkbox:checked").map(function() {
		        return this.value;
		    }).get();
			//alert(checkeds);
			if (confirm('Are you sure? (It delete all toppics, blocks, schedulers, etc of this course.)')) {
			$.ajax({
				  type: "GET",
				  url: "deleteSubject",
				  data: "subjectIds="+checkeds,
				  success: function(msg){
					  location.reload(); 
				  }
				});
			}
		});
		// add multiple select / deselect functionality
		$("#selectall").click(function() {
			$('.case').attr('checked', this.checked);
		});
		// if all checkbox are selected, check the selectall checkbox  also        
		$(".case").click(function() {
			if ($(".case").length == $(".case:checked").length) {
				$("#selectall").attr("checked", "checked");
			} else {
				$("#selectall").removeAttr("checked");
			}
		});
	});
</script>