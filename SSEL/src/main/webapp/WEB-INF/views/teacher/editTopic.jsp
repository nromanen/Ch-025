<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<script src="resources/ckeditor/ckeditor.js"></script>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Edit topic</h1>


	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="panel panel-default">
	<div class="panel-body">
		<!-- ololololololololololo -->

		<form action="saveTopic">
			<input type="hidden" name="topicId" value="${topic.id}">
			<div class="form-group">
				<label>Topic name</label> <input class="form-control"
					name="topicName" value="${topic.name}">
				<p class="help-block">Input or edit topic name</p>
			</div>

			<div class="form-group">
				<label>Topic order</label> <input class="form-control" size=10
					name="topicOrder" value="${topic.order}">
				<p class="help-block">Input or edit topic order</p>
			</div>

			<div class="form-group">
				<label>Topic enable</label> <label class="radio-inline"> <input
					type="radio" name="topicAlive" id="optionsRadiosInline1" value="1"
					checked>Enable
				</label> <label class="radio-inline"> <input type="radio"
					name="topicAlive" id="optionsRadiosInline2" value="0">Disable
				</label>
			</div>


			<textarea name="topicContent" id="topicContent" rows="15" cols="80">
                ${topic.content}
             
            </textarea>
			<script>
				// Replace the <textarea id="editor1"> with a CKEditor
				// instance, using default configuration.
				CKEDITOR.replace('topicContent');
			</script>
			<br>




			<div class="form-group">
				<label>Select module</label> <select class="form-control"
					name="blockId">
					<c:forEach items="${blockList}" var="block">
						<c:choose>
							<c:when test="${topic.block.id == block.id}">
								<option selected value="${block.id}">Module
									${block.order}. ${block.name}</option>
							</c:when>
							<c:otherwise>
								<option value="${block.id}">Module
									${block.order}.${block.name}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>

			<br> <input type="submit" class="btn btn-primary btn-lg"
				value="Save">

		</form>


		<!-- /ololololololololololo -->
	</div>
</div>


<!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
<link rel="stylesheet" href="resources/css/jquery.fileupload.css">
<link rel="stylesheet" href="resources/css/jquery.fileupload-ui.css">

<!-- The file upload form used as target for the file upload widget -->
<form id="fileupload" action='<s:url value="upload"/>' method="POST"
	enctype="multipart/form-data">

	<!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
	<div class="row fileupload-buttonbar">
		<div class="col-lg-7">
			<!-- The fileinput-button span is used to style the file input field as button -->
			<span class="btn btn-success fileinput-button"> <i
				class="glyphicon glyphicon-plus"></i> <span>Add files...</span> <input
				type="file" name="files[]" multiple>
			</span>
			<button type="submit" class="btn btn-primary start">
				<i class="glyphicon glyphicon-upload"></i> <span>Start upload</span>
			</button>
			<button type="reset" class="btn btn-warning cancel">
				<i class="glyphicon glyphicon-ban-circle"></i> <span>Cancel
					upload</span>
			</button>
			<button type="button" class="btn btn-danger delete">
				<i class="glyphicon glyphicon-trash"></i> <span>Delete</span>
			</button>

			<!-- The global file processing state -->
			<span class="fileupload-process"></span>
		</div>
		<!-- The global progress state -->
		<div class="col-lg-5 fileupload-progress fade">
			<!-- The global progress bar -->
			<div class="progress progress-striped active" role="progressbar"
				aria-valuemin="0" aria-valuemax="100">
				<div class="progress-bar progress-bar-success" style="width: 0%;"></div>
			</div>
			<!-- The extended global progress state -->
			<div class="progress-extended">&nbsp;</div>
		</div>
	</div>
	<!-- The table listing the files available for upload/download -->
	<table role="presentation" class="table table-striped">
		<tbody class="files"></tbody>
	</table>
</form>
<br>

<!-- The template to display files available for upload -->
<script id="template-upload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-upload fade">
        <td>
            <span class="preview"></span>
        </td>
        <td>
            <p class="name">{%=file.name%}</p>
            <strong class="error text-danger"></strong>
        </td>
        <td>
            <p class="size">Processing...</p>
            <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
        </td>
        <td>
            {% if (!i && !o.options.autoUpload) { %}
                <button class="btn btn-primary start">
                    <i class="glyphicon glyphicon-upload"></i>
                    <span>Start</span>
                </button>
            {% } %}
            {% if (!i) { %}
                <button class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>Cancel</span>
                </button>
            {% } %}
        </td>
    </tr>
{% } %}
</script>
<!-- The template to display files available for download -->
<script id="template-download" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-download fade">
        <td>
            <p class="name">
                {% if (file.url) { %}
                    <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}">{%=file.name%}</a>
                {% } else { %}
                    <span>{%=file.name%}</span>
                {% } %}
            </p>
            {% if (file.error) { %}
                <div><span class="label label-danger">Error</span> {%=file.error%}</div>
            {% } %}
        </td>
        <td>
            <span class="size">{%=o.formatFileSize(file.size)%}</span>
        </td>
        <td>
            {% if (file.deleteUrl) { %}
                <button class="btn btn-danger delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                    <i class="glyphicon glyphicon-trash"></i>
                    <span>Delete</span>
                </button>
                <input type="checkbox" name="delete" value="1" class="toggle">
            {% } else { %}
                <button class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>Cancel</span>
                </button>
            {% } %}
        </td>
    </tr>
{% } %}
</script>

<script src="resources/js/vendor/jquery.ui.widget.js"></script>
<!-- The Templates plugin is included to render the upload/download listings -->
<script
	src="http://blueimp.github.io/JavaScript-Templates/js/tmpl.min.js"></script>

<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
<script src="resources/js/jquery.iframe-transport.js"></script>
<!-- The basic File Upload plugin -->
<script src="resources/js/jquery.fileupload.js"></script>
<!-- The File Upload processing plugin -->
<script src="resources/js/jquery.fileupload-process.js"></script>
<!-- The File Upload image preview & resize plugin -->
<!-- script src="resources/js/jquery.fileupload-image.js"></script-->
<!-- The File Upload audio preview plugin -->
<script src="resources/js/jquery.fileupload-audio.js"></script>
<!-- The File Upload video preview plugin -->
<script src="resources/js/jquery.fileupload-video.js"></script>
<!-- The File Upload validation plugin -->
<script src="resources/js/jquery.fileupload-validate.js"></script>
<!-- The File Upload user interface plugin -->
<script src="resources/js/jquery.fileupload-ui.js"></script>
<!-- The main application script -->
<script src="resources/js/main.js"></script>


