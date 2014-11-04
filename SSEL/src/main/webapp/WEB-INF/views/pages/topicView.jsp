<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
   
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="http://github.com/malsup/media/raw/master/jquery.media.js?v0.92"></script> 
<script type="text/javascript" src="jquery.metadata.js"></script> 


<div align="center">
<h1>${name}</h1>
 <a class="media" href="${content}"></a>
</div>

<script type="text/javascript">
 $(function() {
        $('a.media').media({width:500, height:500});
    });
</script> 
