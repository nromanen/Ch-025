<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script>
$(function() {
	//$(".sidebar").find("a").addClass("active");
	$(".sidebar").find($('a[href="administrator"]')).addClass("active");
	});
</script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#changeCategoryForm').bootstrapValidator({
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				category : {
					validators : {
						notEmpty : {
							message : "<spring:message code='dataerror.email_example'/>"
						},
						regexp : {
							regexp : '^[^@\\s]+@([^@\\s]+\\.)+[^@\\s]+$',
							message : "<spring:message code='dataerror.email_example'/>"
						}
					}
				}
			}
		}).on('success.field.bv', function(e, data) {
			if (data.bv.isValid()) {
				data.bv.disableSubmitButtons(false);
			}
		});
	});
</script>

<script type="text/javascript">
function changeCategoryName(categoryId){

	$('#inputChangeCategory').val('');
	$("#changeCategoryBtn").attr("disabled", "disabled");
	$('#changeCategoryId').val(categoryId);
	$('#changeCategoryNameModal').modal('show');

}
</script>
<div id="changeCategoryNameModal" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only"><spring:message
							code="label.close" /></span>
				</button>
			</div>
			<div class="modal-footer">
			<form id="changeCategoryForm" method="POST" role="form"
				action="changeCategory" class="form-horizontal">
				<input type="hidden" name="categoryId" id="changeCategoryId" value="" />
			<div class="form-group" align="center">
					<p><label class="control-label" for="inputChangeCategory"><spring:message
							code="label.new_email" /></label></p>
					<div class="col-md-12">
						<input type="text" class="form-control" id="inputChangeCategory"
							name="category"
							placeholder="<spring:message code='dataerror.email_example' />">
					</div>
				</div>
				<p align="center">
					<button type="submit" class="btn btn-primary" id="changeCategoryBtn" disabled>
					<spring:message code="label.change" /></button>
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<spring:message code="label.cancel" />
					</button>
				</p>
				</form>
			</div>
    </div>
  </div>
</div>


<div class="row">
	<div class="col-md-12">
	<br>
	<div class="col-md-8">
	<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title" align="center"><spring:message code="label.general_information" /></h3>
  </div>
  <div class="panel-body">
    <h4><spring:message code="label.admin.subjects" />: ${subjectsCount} </h4>
	<h4><spring:message code="label.admin.categories" />: ${categoriesCount}</h4>
	<h4><spring:message code="label.admin.users" />: ${usersCount}</h4>
  </div>
</div>

<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title" align="center">User registration activity</h3>
  </div>
  <div class="panel-body">

<div>
				<canvas id="canvas"></canvas>
			</div>

<script>
		var randomScalingFactor = function(){ return Math.round(Math.random()*100)};
		var lineChartData = {
			labels : ["January","February","March","April","May","June","July"],
			datasets : [
				{
					label: "My Second dataset",
					fillColor : "rgba(151,187,205,0.2)",
					strokeColor : "rgba(151,187,205,1)",
					pointColor : "rgba(151,187,205,1)",
					pointStrokeColor : "#fff",
					pointHighlightFill : "#fff",
					pointHighlightStroke : "rgba(151,187,205,1)",
					data : [randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor()]
				}
			]
		}
	window.onload = function(){
		var ctx = document.getElementById("canvas").getContext("2d");
		window.myLine = new Chart(ctx).Line(lineChartData, {
			responsive: true
		});
	}
	</script>

  </div>
</div>

</div>

<div class="col-md-4">
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title" align="center"><spring:message code="label.support_email" /></h3>
  </div>
  <div class="panel-body">
  <table>
  <tr>
  <td class="col-md-10"><b>${supportEmail}</b></td>
  <td class="col-md-2" align="center">
  <a data-toggle="modal" href="#" onclick="changeCategoryName(${category.id})"><span class="glyphicon glyphicon-pencil"></span></a>
  </td>
  </tr>
  </table>
  </div>
</div>

<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title" align="center"><spring:message code="label.temporary_files" /></h3>
  </div>
  <div class="panel-body">
    Some inf
  </div>
</div>

<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title" align="center"><spring:message code="label.distribution_options" /></h3>
  </div>
  <div class="panel-body">
    Some inf
  </div>
</div>

</div>

	</div>
</div>
