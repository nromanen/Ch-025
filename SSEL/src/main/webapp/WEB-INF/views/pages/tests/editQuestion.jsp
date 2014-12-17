<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="resources/ckeditor/ckeditor.js"></script>
<script src="resources/js/validateQuest.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
<div class="panel panel-default" style="border:none;">
	<h1 class="page-header">
			<spring:message code="label.test.add_question" /> 
		</h1>
</div>	
	<div class="panel-body">
	<form:form action="saveQuestion?op=${op}" method="POST" commandName="questionForm">
			<h3>${testName}</h3>
		<form:hidden path="question.id" />
		<div class="form-group">
        	<form:label path="name"><h4><spring:message code="label.test.question" /> </h4></form:label>
        	<form:errors path="name" cssClass="alert alert-warning" cssStyle="position:float" />
        	<form:textarea id="question" path="name" rows="15" cols="80"/>
				<script>
					CKEDITOR.replace('question');
				</script>
    	</div>
     	<div class="form-group">
		
			<form:label path="question.mark" ><h4><spring:message code="label.test.mark" /> </h4></form:label>
			<form:input path="question.mark" cssClass="form-control" style="width:30%" />
		</div>
		<div class="list-group" id="answers">
		<h3><spring:message code="label.test.answers" /> </h3>
		<div style="margin-bottom: 10px;">
			<button type="button" onClick="showElement()" class="btn btn-success"><div class="glyphicon glyphicon-plus-sign" ></div></button>
	<button type="button" onClick="removeElement()" class="btn btn-danger"><div class="glyphicon glyphicon-minus-sign" ></div></button>
		</div>
		<c:forEach  var="i" begin="0" 	end="${questionsCount}">
				<div id="div${i}" class="list-group-item" style="width:40%">
					<h4 class="list-group-item-heading" >Answer${i}</h4>
					<div class="list-group-item-text">
						<!--<form:hidden path="answers[${i}].value" itemValue="id" itemLabel="name"/>-->
						<div class="input-group" >
						<form:input path="answers[${i}].value" cssClass="form-control" />
						<span class="input-group-addon" style="horizontal-align:left">	
							<form:checkbox path="answers[${i}].isCorrect" />
						</span>
						
						</div>
					</div>
				</div>
		</c:forEach>
		</div>
		<form:hidden path="question.test.id"/>
		<button type="submit" class="btn btn-primary btn-lg" id="subm"> <spring:message code="label.teacher.save"/> </button>
	</form:form>
	</div>
	<input id="size" type="hidden" value="${questionsCount}" />
	<button type="button" onClick="window.history.back()" class="btn btn-default"><div class="glyphicon glyphicon-backward">
		</div></button>
		
		<script>

		var count = document.getElementById('size').value;
		var root = document.getElementById('answers');
		 	function showElement() {
		 		if (count < 6) {
		 			count++;
		 			var parent = root;
		 			var lgi = document.createElement('div');
		 			lgi.setAttribute('id', 'div'+count);
		 			lgi.setAttribute('class', 'list-group-item');
		 			lgi.setAttribute('style', 'width:40%');
		 			parent.appendChild(lgi);
		 			var h4 = document.createElement('h4');
		 			h4.setAttribute('class', 'list-group-item-heading');
		 			h4.innerHTML = 'Answer'+count;
		 			lgi.appendChild(h4);
		 			var div = document.createElement('div');
		 			div.setAttribute('class', 'list-group-item-text');
		 			lgi.appendChild(div);
		 			var innerDiv = document.createElement('div');
		 			innerDiv.setAttribute('class', 'input-group');
		 			div.appendChild(innerDiv);
		 			var formInput = document.createElement('input');
		 			formInput.setAttribute('name', 'answers['+count+'].value');
		 			formInput.setAttribute('id', 'answers'+count+'.value');
		 			formInput.setAttribute('class', 'form-control');
		 			formInput.setAttribute('type', 'text');
		 			innerDiv.appendChild(formInput);
		 			var span = document.createElement('span');
		 			span.setAttribute('class', 'input-group-addon');
		 			span.setAttribute('style', 'horizontal-align:left');
		 			innerDiv.appendChild(span);
		 			var checkbox = document.createElement('input');
		 			checkbox.setAttribute('type', 'checkbox');
		 			checkbox.setAttribute('name', 'answers['+count+'].isCorrect');
		 			checkbox.setAttribute('id', 'answers'+count+'.isCorrect');
		 			var hiddenCheck = document.createElement('input');
		 			hiddenCheck.setAttribute('type', 'hidden');
		 			hiddenCheck.setAttribute('name', '_answers['+count+'].isCorrect');
		 			hiddenCheck.setAttribute('value', 'on');
		 			span.appendChild(checkbox);
		 			span.appendChild(hiddenCheck);
		 		}
		 	}
		 	
		 	function removeElement() {
		 		if (count >= 2) {
		 			var element = document.getElementById('div'+count);
					root.removeChild(element);
					count--;
		 		}
		 	}
		</script>
	