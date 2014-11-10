<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
   
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="http://malsup.github.com/chili-1.7.pack.js"></script>
<script type="text/javascript" src="http://malsup.github.com/jquery.media.js"></script>
<script type="text/javascript" src="http://malsup.com/jquery/jquery.metadata.js"></script>
<script>
	function prepereModal(documentUrl, documentName)
        {
             document.getElementById("dok").href=documentUrl; 
             document.getElementById("myModalLabel").innerHtml=documentName; 
			 $('.media').media({width:550, height:800, src: documentUrl});
        }
</script>
<div align="center">
<h1>${block_name}. ${name}</h1>
<h2>${content}</h2>
<c:if test="${docs.size() ne 0}">
	<table class="table">
	<thead>
		<tr class="alert alert-success">
			<td>
				<spring:message code="label.student.file_name" />
			</td>
			<td>
				<spring:message code="label.student.view_in" />
			</td>
			<td>
				<spring:message code="label.student.download_file" />
			</td>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${docs}" var="doc">
		<tr class="alert alert-info">
			<td>
				${doc.name}
			</td>
			<td>
			<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal"
			onClick="prepereModal('<c:url value="/" />resources/tmp/${doc.name}','${doc.name}')">
  				<spring:message code="label.student.view_in" />
			</button>
							
			</td>
			<td>
			<form action="<c:url value="/file/${doc.id}" />" >
				<button type="submit" class="btn btn-primary btn-lg"> <spring:message code="label.student.download_file" />
				 </button>
			</form>
			</td>
		</tr>
	</c:forEach>
	</tbody>
	</table>
</c:if>
</div>


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" >
  <div class="modal-dialog" >
    <div class="modal-content">
      <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h2 class="modal-title" id="myModalLabel" ></h2>
      </div>
      <div class="modal-body">
        <a id="dok" class="media"  href="" ></a> 
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal")">
			<spring:message code="label.student.close_modal" />
		</button>
      </div>
    </div>
  </div>
</div>

    