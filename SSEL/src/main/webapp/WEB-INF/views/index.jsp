<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>
<html>
<tiles:insertDefinition name="indexTemplate">
	<tiles:putAttribute name="main-content">
			 <div id="page-wrapper">
            <div class="row">
                    <h1 class="page-header">Description</h1>
                </div>
                </div>
		</tiles:putAttribute>
</tiles:insertDefinition>
</body>
</html>