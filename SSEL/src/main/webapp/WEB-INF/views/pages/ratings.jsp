<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js"></script>

<div class="row">
	
</div>
<div class="panel panel-default">
	<div class="panel-body">
		<div class="row">
			<div class="col-lg-8">
				<h1>${name}</h1>
			</div>
			<div class="col-lg-2">
				<div id="morris-donut-rating"
                            		style="width: 100; height: 100; position: float"></div>
                <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i> <spring:message code="label.student.current_rating" />
                </div>
				  
					
			</div>
			<div class="col-lg-2">
				<div id="morris-donut-progress"
                            		style="width: 100; height: 100; position: float"></div>
                <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i> <spring:message code="label.student.current_progress" />
                </div>
				        
			</div>
		
		</div>
		<input type="hidden" value="${avgRating}" id="rating" />
		<input type="hidden" id="success" value="<spring:message code="label.student.success" />" />
   		<input type="hidden" id="failed" value="<spring:message code="label.student.failed" />" />
		<input type="hidden" value="${progress}" id="progress" />
		<div class="panel-body">
            <div class="col-lg-8">
                 <div id="morris-bar-chart"></div>
            </div>
            <div class="table-responsive">
                <table class="table" id="data">
                   <thead>
                   		<tr>
                   		<td>
                   			<spring:message code="label.module_name" />
                   		</td>
                   		<td>
                   			<spring:message code="label.module_rating" />
                   		</td>
                   		<td>
                   			<spring:message code="label.module_pass" />
                   		</td>
                   		</tr>
                   </thead> 
                   <tbody>
                   		
                   		<c:set var="index" value="0"></c:set>
                   		<c:forEach items="${blocks}" var="block">
                   		<tr>
                   			<td >
                   				${block.name}
                   			</td>
                   			<td>
                   				${ratings[index].mark}
                   			</td>
                   			<td>
                   				<c:choose>
                   					<c:when test="${ratings[index].mark >= 60}">
                   						<div class="alert alert-success" style="width: 50">
                   							<span class="glyphicon glyphicon-ok" ></span>
                   						</div>
                   					</c:when>
                   					<c:otherwise>
                   						<div class="alert alert-danger" style="width: 50">
                   						<span class="glyphicon glyphicon-remove" ></span>
                   						</div>
                   					</c:otherwise>
                   				</c:choose>
                   			</td>
                   			<c:set var="index" value="${index+1}"></c:set>
                   		</tr>
                   		</c:forEach>
                   		
                   </tbody>              
                </table>
            </div>	
	</div>
</div>
</div>
<script>
/**
 * Donouts for rating and progress 
 */
var rating = document.getElementById("rating").value;
var progress = document.getElementById("progress").value;
var success = document.getElementById("success").value;
var failed = document.getElementById("failed").value;
var data1 = [];
var data2 = [];
var colors = [];
if(rating == 100.0) {
	data1.push({label: success, value : 100});
} else if (rating > 0.0) {
	data1.push({label: success, value : rating});
	data1.push({label: failed, value : 100 - rating});
} else {
	data1.push({label: failed, value : 100 });
}
if (progress == 100.0) {
	data2.push({label: success, value : 100});
}else if(progress > 0.0) {
	data2.push({label: success, value : progress});
	data2.push({label: failed, value : 100-progress});
} else {
	data2.push({label:failed, value: 100});
}
	colors.push('#33CC33');
	colors.push('#FF3300');
	
	Morris.Donut({
		  element: 'morris-donut-progress',
		  data: data2,
		  colors: colors
		});
	
	Morris.Donut({
	  element: 'morris-donut-rating',
	  data: data1,
	  colors: colors
	});
	</script>