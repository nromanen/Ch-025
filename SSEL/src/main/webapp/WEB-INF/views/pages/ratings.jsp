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
                            <i class="fa fa-bar-chart-o fa-fw"></i> Rating
                </div>
				  
					
			</div>
			<div class="col-lg-2">
				<div id="morris-donut-progress"
                            		style="width: 100; height: 100; position: float"></div>
                <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i> Progress
                </div>
				        
			</div>
		
		</div>
		<input type="hidden" value="${avgRating}" id="rating" />
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
                   				${ratings.get(index).mark}
                   			</td>
                   			<td>
                   				<c:choose>
                   					<c:when test="${ratings.get(index).mark > 60}">
                   						<div class="alert alert-success"><spring:message code="label.test_success" />
                   						</div>
                   					</c:when>
                   					<c:otherwise>
                   						<div class="alert alert-danger"><spring:message code="label.test_failed" />
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
var rating = document.getElementById("rating").value;
var progress = document.getElementById("progress").value;
	Morris.Donut({
	  element: 'morris-donut-rating',
	  data: [
	    {label: "Success", value: rating},
	    {label: "Failed", value: 100-rating}
	  ],
	colors: [
		'#33CC33',
		'#FF3300'
	]
	});
	
	Morris.Donut({
		  element: 'morris-donut-progress',
		  data: [
		    {label: "Success", value: progress},
		    {label: "Failed", value: 100-progress}
		  ],
		colors: [
			'#33CC33',
			'#FF3300'
		]
		});
			
</script>
<script>
var table = document.getElementById("data");
var l = [];
for (var i = 0, row; row = table.rows[i]; i ++) {
	
	l.push({y: row.cells[0].textContent, a: row.cells[1].textContent});
}
Morris.Bar({
	  element: 'bar-example',
	  data: l,
	  xkey: 'y',
	  ykeys: ['a'],
	  labels: ['Series A']
	});
</script>
